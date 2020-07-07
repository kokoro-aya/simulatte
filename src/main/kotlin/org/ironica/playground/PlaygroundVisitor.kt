package org.ironica.playground

import org.antlr.v4.runtime.tree.*
import playgroundGrammarParser

import org.ironica.playground.SpecialRetVal.*
import org.ironica.playground.Type.*
import org.ironica.playground.Variability.*
import playgroundGrammarVisitor
import kotlin.math.exp
import kotlin.math.pow

enum class SpecialRetVal {
    Interr, Loop, Statements, Declaration, Branch, Error, Empty, ReDef, NotDef, CallOnSuccess
}

enum class Type {
    INT, DOUBLE, CHARACTER, STRING, BOOL, VOID, CALL
}

enum class Variability {
    VAR, LET
}

sealed class TypedLiteral
data class IntLiteral(val variability: Variability, val content: Int): TypedLiteral()
data class DoubleLiteral(val variability: Variability, val content: Double): TypedLiteral()
data class BooleanLiteral(val variability: Variability, val content: Boolean): TypedLiteral()
data class CharacterLiteral(val variability: Variability, val content: Char): TypedLiteral()
data class StringLiteral(val variability: Variability, val content: String): TypedLiteral()

typealias TypedParam = Pair<String, Type>
typealias TypedArgum = Pair<Any, Type>

data class FunctionHead(val name: String, val params: List<String>, val types: List<Type>, val ret: Type) {
    fun pseudoEquals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FunctionHead

        if (name != other.name) return false
        if (types != other.types) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + types.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FunctionHead

        if (name != other.name) return false
        if (params != other.params) return false
        if (types != other.types) return false
        if (ret != other.ret) return false

        return true
    }
}

class PlaygroundVisitor(private val ground: Playground): playgroundGrammarVisitor<Any> {

    private val variableTable = mutableMapOf<String, TypedLiteral>()

    private val functionTable = mutableMapOf<FunctionHead, ParseTree>()

    private var _break = false
    private var _continue = false

    private val internalVariables = mutableMapOf<String, TypedLiteral>()

    private var _internal = false

    private fun print(body: Any) {
        println(body)
    }

    override fun visitIsNestedCondition(ctx: playgroundGrammarParser.IsNestedConditionContext?): Any {
        val left: Boolean = visit(ctx?.expression(0)) as Boolean
        val right = visit(ctx?.expression(1)) as Boolean
        return if (ctx?.op?.type == playgroundGrammarParser.AND) left && right else left || right
    }

    override fun visitStatements(ctx: playgroundGrammarParser.StatementsContext?): Any {
        for (child in ctx?.children!!) {
            if (_break || _continue)
                break
            visit(child)
        }
        return Statements
    }

    override fun visitLoop_statement(ctx: playgroundGrammarParser.Loop_statementContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitFor_in_statement(ctx: playgroundGrammarParser.For_in_statementContext?): Any {
        val range = visit(ctx?.expression()) as IntRange
        val pattern = visit(ctx?.pattern()) as String
        // println(range)
        for (x in range) {
            _internal = true
            if (pattern != "_") {
                internalVariables[pattern] = IntLiteral(VAR, x)
            }
            if (_break) {
                _break = false
                break
            }
            if (_continue) {
                _continue = false
                continue
            }
            visit(ctx?.code_block())
        }
        internalVariables.remove(pattern)
        _internal = false
        return Loop
    }

    override fun visitBranch_statement(ctx: playgroundGrammarParser.Branch_statementContext?): Any {
        return visit(ctx?.if_statement())
    }

    override fun visitElse_clause(ctx: playgroundGrammarParser.Else_clauseContext?): Any {
        return visit(ctx?.children?.get(1))
    }

    override fun visitErrorNode(node: ErrorNode?): Any {
        return Error
    }

    override fun visitRepeat_while_statement(ctx: playgroundGrammarParser.Repeat_while_statementContext?): Any {
         do {
            if (_break) {
                _break = false
                break
            }
            if (_continue) {
                _continue = false
                continue
            }
            visit(ctx?.code_block())
        } while (visit(ctx?.expression()) == true)
        return Loop
    }

    override fun visitControl_transfer_statement(ctx: playgroundGrammarParser.Control_transfer_statementContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitDeclaration(ctx: playgroundGrammarParser.DeclarationContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitFunction_signature(ctx: playgroundGrammarParser.Function_signatureContext?): Any {
        val paramClause = visit(ctx?.parameter_clause()) as List<TypedParam>
        val resultType = if (ctx?.childCount == 1) VOID else visit(ctx?.function_result_type()) as Type
        return Pair(paramClause, resultType)
    }

    override fun visit(tree: ParseTree?): Any {
        return tree?.accept(this) ?: Error
    }

    override fun visitTop_level(ctx: playgroundGrammarParser.Top_levelContext?): Any {
        return visit(ctx?.statements())
    }

    override fun visitStatement(ctx: playgroundGrammarParser.StatementContext?): Any {
//        ground.printGrid()
        return visit(ctx?.children?.get(0))
    }

    override fun visitWhile_statement(ctx: playgroundGrammarParser.While_statementContext?): Any {
        while (visit(ctx?.expression()) == true) {
            if (_break) {
                _break = false
                break
            }
            if (_continue) {
                _continue = false
                break
            }
            visit(ctx?.code_block())
        }
        return Loop
    }

    override fun visitIf_statement(ctx: playgroundGrammarParser.If_statementContext?): Any {
        if (visit(ctx?.expression()) == true) {
            visit(ctx?.code_block())
        } else {
            if (ctx?.else_clause() != null) {
                visit(ctx.else_clause())
            }
        }
        return Branch
    }

    override fun visitBreak_statement(ctx: playgroundGrammarParser.Break_statementContext?): Any {
        _break = true
        return Interr
    }

    override fun visitContinue_statement(ctx: playgroundGrammarParser.Continue_statementContext?): Any {
        _continue = true
        return Interr
    }

    override fun visitCode_block(ctx: playgroundGrammarParser.Code_blockContext?): Any {
        return visit(ctx?.statements())
    }

    private fun declareOrAssignConstantOrVariable(left: String, right: Any, constant: Boolean): Boolean {
        if (right is Int) {
            if (!variableTable.containsKey(left) || (variableTable[left] as IntLiteral).variability == VAR) {
                variableTable[left] = if (constant) IntLiteral(LET, right) else IntLiteral(VAR, right)
                return true
            }
        }
        if (right is Double) {
            if (!variableTable.containsKey(left) || (variableTable[left] as DoubleLiteral).variability == VAR) {
                variableTable[left] = if (constant) DoubleLiteral(LET, right) else DoubleLiteral(VAR, right)
                return true
            }
        }
        if (right is Boolean) {
            if (!variableTable.containsKey(left) || (variableTable[left] as BooleanLiteral).variability == VAR) {
                variableTable[left] = if (constant) BooleanLiteral(LET, right) else BooleanLiteral(VAR, right)
                return true
            }
        }
        if (right is Char) {
            if (!variableTable.containsKey(left) || (variableTable[left] as CharacterLiteral).variability == VAR) {
                variableTable[left] = if (constant) CharacterLiteral(LET, right) else CharacterLiteral(VAR, right)
                return true
            }
        }
        if (right is String) {
            if (!variableTable.containsKey(left) || (variableTable[left] as StringLiteral).variability == VAR) {
                variableTable[left] = if (constant) StringLiteral(LET, right) else StringLiteral(VAR, right)
                return true
            }
        }
        return false
    }

    override fun visitConstant_declaration(ctx: playgroundGrammarParser.Constant_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) return Error
        val right = visit(ctx?.expression())
        // println("right: $right")
        if (declareOrAssignConstantOrVariable(left, right, true)) return Declaration
        return Error
    }

    override fun visitVariable_declaration(ctx: playgroundGrammarParser.Variable_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) return Error
        val right = visit(ctx?.expression())
        if (declareOrAssignConstantOrVariable(left, right, false)) return Declaration
        return Error
    }

    override fun visitTerminal(node: TerminalNode?): Any {
        var ret: Any? = node?.text?.toIntOrNull()
        if (ret != null) return (ret as Int)
        else {
            ret = node?.text?.toDoubleOrNull()
            if (ret != null) return (ret as Double)
            else {
                if (node?.text.toString() == "true") return true
                if (node?.text.toString() == "false") return false
                if (node?.text != null) {
                    return if (node.text?.length == 1)
                        node.text[0]
                    else
                        node.text
                }
                return Error
            }
        }
    }

    override fun visitFunction_body(ctx: playgroundGrammarParser.Function_bodyContext?): Any {
        return visit(ctx?.code_block())
    }

    override fun visitChildren(node: RuleNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_declaration(ctx: playgroundGrammarParser.Function_declarationContext?): Any {
        try {
            val functionName = visit(ctx?.function_name()) as String
            val functionSignature = visit(ctx?.function_signature()) as Pair<List<TypedParam>, Type>
            val functionHead = FunctionHead(functionName, functionSignature.first.map { it.first }, functionSignature.first.map { it.second }, functionSignature.second)
            for (key in functionTable.keys) {
                if (functionHead.pseudoEquals(key))
                    return ReDef
            }
            functionTable[functionHead] = ctx?.function_body()!!
            return Declaration
        } catch (e: Exception) {
            return Error
        }
    }

    override fun visitPattern(ctx: playgroundGrammarParser.PatternContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitWildcard_pattern(ctx: playgroundGrammarParser.Wildcard_patternContext?): Any {
        return "_"
    }

    override fun visitIdentifier_pattern(ctx: playgroundGrammarParser.Identifier_patternContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: Error
    }

    override fun visitIsNegativeCondition(ctx: playgroundGrammarParser.IsNegativeConditionContext?): Any {
        return !(visit(ctx?.children?.get(1)) as Boolean)
    }

    override fun visitBoolean_literal(ctx: playgroundGrammarParser.Boolean_literalContext?): Any {
        return when (ctx?.text) {
            "true" -> true
            "false"-> false
            else -> Error
        }
    }

    override fun visitFunction_name(ctx: playgroundGrammarParser.Function_nameContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: Error
    }

    override fun visitFunction_result_type(ctx: playgroundGrammarParser.Function_result_typeContext?): Any {
        return when (val text = ctx?.type()?.text) {
            "Int" -> INT
            "Bool" -> BOOL
            "Double" -> DOUBLE
            "Character" -> CHARACTER
            "String" -> STRING
            "Void", "" -> VOID
            else -> Error
        }
    }

    override fun visitParameter_clause(ctx: playgroundGrammarParser.Parameter_clauseContext?): Any {
        if (ctx?.children?.size == 1)
            return listOf<TypedParam>()
        return visit(ctx?.parameter_list())
    }

    override fun visitParameter_list(ctx: playgroundGrammarParser.Parameter_listContext?): Any {
        return ctx?.parameter()?.map { (visit(it) as TypedParam) }!!
    }

    override fun visitParameter(ctx: playgroundGrammarParser.ParameterContext?): Any {
        val param = visit(ctx?.param_name()) as String
        val type = visit(ctx?.type_annotation()) as Type
        return Pair(param, type) as TypedParam
    }

    override fun visitParam_name(ctx: playgroundGrammarParser.Param_nameContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: Error
    }

    override fun visitType_annotation(ctx: playgroundGrammarParser.Type_annotationContext?): Any {
        return visit(ctx?.type())
    }

    override fun visitType(ctx: playgroundGrammarParser.TypeContext?): Any {
        return when (ctx?.text) {
            "Int" -> INT
            "Bool" -> BOOL
            "Double" -> DOUBLE
            "Character" -> CHARACTER
            "String" -> STRING
            "Void", "" -> VOID
            else -> Error
        }
    }

    override fun visitFunction_call(ctx: playgroundGrammarParser.Function_callContext?): Any {
        return visit(ctx?.function_call_expression())
    }

    override fun visitFunction_call_expression(ctx: playgroundGrammarParser.Function_call_expressionContext?): Any {
        try {
            val functionName = visit(ctx?.function_name()) as String
            val funcArgument =
                if (ctx?.childCount == 2) listOf() else visit(ctx?.call_argument_clause()) as List<TypedArgum>
            val functionHead = FunctionHead(functionName, listOf(), funcArgument.map { it.second }, CALL)
            if (functionHead.name == "moveForward" && functionHead.types.isEmpty()) {
                ground.printGrid()
                return ground.player.moveForward()
            } else if (functionHead.name == "turnLeft" && functionHead.types.isEmpty()) {
                ground.printGrid()
                return ground.player.turnLeft()
            } else if (functionHead.name == "toggleSwitch" && functionHead.types.isEmpty()) {
                ground.printGrid()
                return ground.player.toggleSwitch()
            } else if (functionHead.name == "collectGem" && functionHead.types.isEmpty()) {
                ground.printGrid()
                return ground.player.collectGem()
            } else if (functionHead.name == "print") {
                println(funcArgument[0].first)
                return Empty
            } else {
                for (key in functionTable.keys) {
                    if (functionHead.pseudoEquals(key)) {
                        val realHead = key
                        if (realHead.params.isEmpty()) {
                            visit(functionTable[realHead])
                            return CallOnSuccess
                        } else {
                            val paramContents = funcArgument.map { it.first }.map {
                                when (val content = it) {
                                    is Int -> IntLiteral(VAR, content)
                                    is Double -> DoubleLiteral(VAR, content)
                                    is Boolean -> BooleanLiteral(VAR, content)
                                    is Char -> CharacterLiteral(VAR, content)
                                    is String -> StringLiteral(VAR, content)
                                    else -> return Error
                                }
                            }
                            val paramNames = realHead.params
                            _internal = true
                            for (idx in paramNames.indices) {
                                internalVariables[paramNames[idx]] = paramContents[idx]
                            }
                            visit(functionTable[realHead])
                            for (name in paramNames) {
                                internalVariables.remove(name)
                            }
                            _internal = false
                            return CallOnSuccess
                        }
                    }
                }
                return NotDef
            }
        } catch (e: Exception) {
            return Error
        }
    }

    override fun visitCall_argument_clause(ctx: playgroundGrammarParser.Call_argument_clauseContext?): Any {
        return ctx?.call_argument()?.map { visit(it) }!! as List<TypedArgum>
    }

    override fun visitCall_argument(ctx: playgroundGrammarParser.Call_argumentContext?): Any {
        val callval = visit(ctx?.expression())
        val type: Type = when (callval) {
            is Int -> INT
            is Double -> DOUBLE
            is Boolean -> BOOL
            is Char -> CHARACTER
            is String -> STRING
            else -> return Error
        }
        return Pair(callval, type) as TypedArgum
    }

    override fun visitLiteral(ctx: playgroundGrammarParser.LiteralContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitNumeric_literal(ctx: playgroundGrammarParser.Numeric_literalContext?): Any {
        return when {
            ctx?.childCount == 1 -> visit(ctx.getChild(0))
            ctx?.integer_literal()?.isEmpty!! -> - (visit(ctx.getChild(1)) as Double)
            else -> - (visit(ctx.getChild(1)) as Int)
        }
    }

    override fun visitChar_sequence_literal(ctx: playgroundGrammarParser.Char_sequence_literalContext?): Any {
        return if (ctx?.getChild(0)?.text?.length == 1)
            ctx.getChild(0).text[0]
        else
            ctx?.getChild(0)?.text!!
    }

    override fun visitAssignmentExpr(ctx: playgroundGrammarParser.AssignmentExprContext?): Any {
        return visit(ctx?.assignment_expression())
    }

    override fun visitBoolComparativeExpr(ctx: playgroundGrammarParser.BoolComparativeExprContext?): Any {
        val left = visit(ctx?.expression(0))
        val right = visit(ctx?.expression(1))
        if (left is Boolean && right is Boolean) {
            return if (ctx?.op?.type == playgroundGrammarParser.EQ) left == right else left != right
        }
        if ((left is Int || left is Double) && right is Boolean) {
            return if (ctx?.op?.type == playgroundGrammarParser.EQ) (left != 0) == right else (left != 0) != right
        }
        if (left is Boolean && (right is Int || right is Double)) {
            return if (ctx?.op?.type == playgroundGrammarParser.EQ) left == (right != 0) else left != (right != 0)
        }
        if ((left is Int || left is Double) && (right is Int || right is Double)) {
            return if (ctx?.op?.type == playgroundGrammarParser.EQ) (left != 0) == (right != 0) else (left != 0) != (right != 0)
        }
        return Error
    }

    override fun visitExponentExpr(ctx: playgroundGrammarParser.ExponentExprContext?): Any {
        val left = visit(ctx?.expression(0)).toString().toDouble()
        val right = visit(ctx?.expression(1)).toString().toDouble()
        return left.pow(right)
    }

    override fun visitAddSubExpr(ctx: playgroundGrammarParser.AddSubExprContext?): Any {
        var left = visit(ctx?.expression(0))
        var right = visit(ctx?.expression(1))
        if ((left is Boolean || left is Int) && (right is Boolean || right is Int)) {
            if (left is Boolean)
                left = if (left == true) 1 else 0
            if (right is Boolean)
                right = if (right == true) 1 else 0
            return when (ctx?.op?.type) {
                playgroundGrammarParser.ADD -> left as Int + right as Int
                else -> left as Int - right as Int
            }
        }
        if ((left is Boolean || left is Double) && (right is Boolean || right is Double)) {
            if (left is Boolean)
                left = if (left == true) 1 else 0
            if (right is Boolean)
                right = if (right == true) 1 else 0
            return when (ctx?.op?.type) {
                playgroundGrammarParser.ADD -> left as Double + right as Double
                else -> left as Double - right as Double
            }
        }
        return Error
    }

    override fun visitAriAssignmentExpr(ctx: playgroundGrammarParser.AriAssignmentExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMemberExpr(ctx: playgroundGrammarParser.MemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAriComparativeExpr(ctx: playgroundGrammarParser.AriComparativeExprContext?): Any {
        var left = visit(ctx?.expression(0))
        var right = visit(ctx?.expression(1))
        if (left is Int && right is Double)
            left = left.toDouble()
        if (left is Double && right is Int)
            right = right.toDouble()
        if (left is Int && right is Int) {
            return when (ctx?.op?.type) {
                playgroundGrammarParser.GT -> left > right
                playgroundGrammarParser.LT -> left < right
                playgroundGrammarParser.GEQ -> left >= right
                else -> left <= right
            }
        }
        if (left is Double && right is Double) {
            return when (ctx?.op?.type) {
                playgroundGrammarParser.GT -> left > right
                playgroundGrammarParser.LT -> left < right
                playgroundGrammarParser.GEQ -> left >= right
                else -> left <= right
            }
        }
        return Error
    }

    override fun visitLiteralValueExpr(ctx: playgroundGrammarParser.LiteralValueExprContext?): Any {
        return visit(ctx?.literal_expression())
    }

    override fun visitMulDivModExpr(ctx: playgroundGrammarParser.MulDivModExprContext?): Any {
        var left = visit(ctx?.expression(0))
        var right = visit(ctx?.expression(1))
        if ((left is Boolean || left is Int) && (right is Boolean || right is Int)) {
            if (left is Boolean)
                left = if (left == true) 1 else 0
            if (right is Boolean)
                right = if (right == true) 1 else 0
            return when (ctx?.op?.type) {
                playgroundGrammarParser.MUL -> left as Int * right as Int
                else -> left as Int / right as Int
            }
        }
        if ((left is Boolean || left is Double) && (right is Boolean || right is Double)) {
            if (left is Boolean)
                left = if (left == true) 1 else 0
            if (right is Boolean)
                right = if (right == true) 1 else 0
            return when (ctx?.op?.type) {
                playgroundGrammarParser.MUL -> left as Double * right as Double
                else -> left as Double / right as Double
            }
        }
        return Error
    }

    override fun visitVariableExpr(ctx: playgroundGrammarParser.VariableExprContext?): Any {
        return visit(ctx?.variable_expression())
    }

    override fun visitRangeExpression(ctx: playgroundGrammarParser.RangeExpressionContext?): Any {
        val lower = visit(ctx?.expression(0)) as Int
        val upper = visit(ctx?.expression(1)) as Int
        // println("lower $lower upper $upper")
        if (ctx?.op?.type == playgroundGrammarParser.UNTIL) {
            return (lower until upper)
        }
        if (ctx?.op?.type == playgroundGrammarParser.THROUGH) {
            return (lower .. upper)
        }
        return Error
    }

    override fun visitParenthesisExpr(ctx: playgroundGrammarParser.ParenthesisExprContext?): Any {
        return visit(ctx?.expression())
    }

    override fun visitAssignment_expression(ctx: playgroundGrammarParser.Assignment_expressionContext?): Any {
        val left = visit(ctx?.pattern()) as String
        if (!(left[0].isLetter() || left[0] == '_')) return Error
        val right = visit(ctx?.expression())
        if (declareOrAssignConstantOrVariable(left, right, false)) return Statements
        return Error
    }

    override fun visitLiteral_expression(ctx: playgroundGrammarParser.Literal_expressionContext?): Any {
        return visit(ctx?.literal())
    }

    override fun visitMember_expression(ctx: playgroundGrammarParser.Member_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariable_expression(ctx: playgroundGrammarParser.Variable_expressionContext?): Any {
        return when (val name = visit(ctx?.IDENTIFIER()).toString()) {
            "isOnGem" -> ground.player.isOnGem()
            "isOnOpenedSwitch" -> ground.player.isOnOpenedSwitch()
            "isOnClosedSwitch" -> ground.player.isOnClosedSwitch()
            "isBlocked" -> ground.player.isBlocked()
            "isBlockedLeft" -> ground.player.isBlockedLeft()
            "isBlockedRight" -> ground.player.isBlockedRight()
            else -> {
                if (_internal) {
                    if (internalVariables.containsKey(name)) {
                        when (val literal = internalVariables[name]!!) {
                            is IntLiteral -> literal.content
                            is DoubleLiteral -> literal.content
                            is CharacterLiteral -> literal.content
                            is StringLiteral -> literal.content
                            is BooleanLiteral -> literal.content
                        }
                    } else {
                        NotDef
                    }
                } else {
                    if (variableTable.containsKey(name)) {
                        when (val literal = variableTable[name]!!) {
                            is IntLiteral -> literal.content
                            is DoubleLiteral -> literal.content
                            is CharacterLiteral -> literal.content
                            is StringLiteral -> literal.content
                            is BooleanLiteral -> literal.content
                        }
                    } else {
                        NotDef
                    }
                }
            }
        }
    }

    override fun visitReturn_statement(ctx: playgroundGrammarParser.Return_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitInteger_literal(ctx: playgroundGrammarParser.Integer_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL())}".toInt()
    }

    override fun visitDouble_literal(ctx: playgroundGrammarParser.Double_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL(0))}.${visit(ctx?.DECIMAL_LITERAL(1))}".toDouble()
    }
}