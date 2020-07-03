package org.ironica.playground

import org.antlr.v4.codegen.model.decl.Decl
import org.antlr.v4.runtime.tree.*
import playgroundGrammarBaseVisitor
import playgroundGrammarParser

import org.ironica.playground.SpecialRetVal.*
import playgroundGrammarVisitor

enum class SpecialRetVal {
    Interr, Loop, Statements, Declaration, Branch, Error, Empty, ReDef, NotDef
}

typealias TypedParam = Pair<String, String>

data class FunctionHead(val name: String, val list: List<TypedParam>, val ret: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FunctionHead

        if (name != other.name) return false
        if (list != other.list) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + list.hashCode()
        return result
    }
}

class PlaygroundVisitor(private val ground: Playground): playgroundGrammarVisitor<Any> {

    private val variableTable = mutableMapOf<String, Int>()
    private val constantTable = mutableMapOf<String, Int>()

    private val functionTable = mutableMapOf<FunctionHead, ParseTree>()

    private var _break = false
    private var _continue = false

    override fun visitMoveForward(ctx: playgroundGrammarParser.MoveForwardContext?): Any {
        ground.printGrid()
        return ground.player.moveForward()
    }

    override fun visitTurnLeft(ctx: playgroundGrammarParser.TurnLeftContext?): Any {
        ground.printGrid()
        return ground.player.turnLeft()
    }

    override fun visitToggleSwitch(ctx: playgroundGrammarParser.ToggleSwitchContext?): Any {
        ground.printGrid()
        return ground.player.toggleSwitch()
    }

    override fun visitCheckTruth(ctx: playgroundGrammarParser.CheckTruthContext?): Any {
        return visit(ctx?.conditional_expression())
    }

    override fun visitRangedStep(ctx: playgroundGrammarParser.RangedStepContext?): Any {
        return visit(ctx?.range_expression())
    }

    override fun visitIsOnGem(ctx: playgroundGrammarParser.IsOnGemContext?): Any {
        return ground.player.isOnGem()
    }

    override fun visitIsOnClosedSwitch(ctx: playgroundGrammarParser.IsOnClosedSwitchContext?): Any {
        return ground.player.isOnClosedSwitch()
    }

    override fun visitIsNestedCondition(ctx: playgroundGrammarParser.IsNestedConditionContext?): Any {
        val left: Boolean = visit(ctx?.conditional_expression(0)) as Boolean
        val right = visit(ctx?.conditional_expression(1)) as Boolean
        return if (ctx?.op?.type == playgroundGrammarParser.AND) left && right else left || right
    }

    override fun visitIsBlockedRight(ctx: playgroundGrammarParser.IsBlockedRightContext?): Any {
        return ground.player.isBlockedRight()
    }

    override fun visitRangeHandler(ctx: playgroundGrammarParser.RangeHandlerContext?): Any {
        val lower = visit(ctx?.children?.get(0)) as Int
        val upper = visit(ctx?.children?.get(2)) as Int
        // println("lower $lower upper $upper")
        if (ctx?.op?.type == playgroundGrammarParser.UNTIL) {
            return (lower until upper)
        }
        if (ctx?.op?.type == playgroundGrammarParser.THROUGH) {
            return (lower .. upper)
        }
        return Error
    }

    override fun visitStatements(ctx: playgroundGrammarParser.StatementsContext?): Any {
        for (child in ctx?.children!!) {
            if (_break || _continue)
                return -3
            visit(child)
        }
        return Statements
    }

    override fun visitLoop_statement(ctx: playgroundGrammarParser.Loop_statementContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitFor_in_statement(ctx: playgroundGrammarParser.For_in_statementContext?): Any {
        val range = visit(ctx?.expression()) as IntRange
        // println(range)
        for (x in range) {
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
                break
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
        val resultType = if (ctx?.childCount == 1) null else visit(ctx?.function_result_type()) as String
        return Pair(paramClause, resultType)
    }

    override fun visit(tree: ParseTree?): Any {
        return tree?.accept(this)!!
    }

    override fun visitTop_level(ctx: playgroundGrammarParser.Top_levelContext?): Any {
        return visit(ctx?.statements())
    }

    override fun visitCollectGem(ctx: playgroundGrammarParser.CollectGemContext?): Any {
        ground.printGrid()
        return ground.player.collectGem()
    }

    override fun visitIsBoolean(ctx: playgroundGrammarParser.IsBooleanContext?): Any {
        return when (ctx?.boolean_literal()?.text) {
            "true" -> true
            "false"-> false
            else -> false
        }
    }

    override fun visitIsBlocked(ctx: playgroundGrammarParser.IsBlockedContext?): Any {
        return ground.player.isBlocked()
    }

    override fun visitIsOnOpenedSwitch(ctx: playgroundGrammarParser.IsOnOpenedSwitchContext?): Any {
        return ground.player.isOnOpenedSwitch()
    }

    override fun visitIsBlockedLeft(ctx: playgroundGrammarParser.IsBlockedLeftContext?): Any {
        return ground.player.isBlockedLeft()
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
        return 0
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

    override fun visitConstant_declaration(ctx: playgroundGrammarParser.Constant_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        val right = visit(ctx?.expression()) as Int
        // println("right: $right")
        if (!constantTable.containsKey(left)) {
            constantTable[left] = right
            return Declaration
        }
        return Error
    }

    override fun visitVariable_declaration(ctx: playgroundGrammarParser.Variable_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        val right = visit(ctx?.expression()) as Int
        if (!variableTable.containsKey(left)) {
            variableTable[left] = right
            return Declaration
        }
        return Error
    }

    override fun visitTerminal(node: TerminalNode?): Any {
        var ret: Any? = node?.text?.toIntOrNull()
        if (ret != null) return (ret as Int)
        else {
            if (node.toString() == "true") return true
            if (node.toString() == "false") return false
            return Error
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
            val functionSignature = visit(ctx?.function_signature()) as Pair<List<TypedParam>, String?>
            val functionHead = FunctionHead(functionName, functionSignature.first, functionSignature.second ?: "Void")
            if (functionTable.keys.contains(functionHead))
                return ReDef
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
        return if (ctx?.text == "true") true else if (ctx?.text == "false") false else Error
    }

    override fun visitFunction_name(ctx: playgroundGrammarParser.Function_nameContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: Error
    }

    override fun visitFunction_result_type(ctx: playgroundGrammarParser.Function_result_typeContext?): Any {
        return when (val text = ctx?.type()?.text) {
            "Bool", "Int" -> text
            else -> Error
        }
    }

    override fun visitParameter_clause(ctx: playgroundGrammarParser.Parameter_clauseContext?): Any {
        if (ctx?.children?.size == 1)
            return listOf<TypedParam>()
        return visit(ctx?.parameter_list())
    }

    override fun visitParameter_list(ctx: playgroundGrammarParser.Parameter_listContext?): Any {
        val ret = mutableListOf<TypedParam>()
        ctx?.parameter()?.forEach { ret += (visit(it) as TypedParam) }
        return ret
    }

    override fun visitParameter(ctx: playgroundGrammarParser.ParameterContext?): Any {
        return Pair(visit(ctx?.param_name()) as String, visit(ctx?.type_annotation()) as String)
    }

    override fun visitParam_name(ctx: playgroundGrammarParser.Param_nameContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: Error
    }

    override fun visitType_annotation(ctx: playgroundGrammarParser.Type_annotationContext?): Any {
        return visit(ctx?.type())
    }

    override fun visitType(ctx: playgroundGrammarParser.TypeContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_call(ctx: playgroundGrammarParser.Function_callContext?): Any {
        return visit(ctx?.function_call_expression())
    }

    override fun visitFunction_call_expression(ctx: playgroundGrammarParser.Function_call_expressionContext?): Any {
        return try {
            val functionName = visit(ctx?.function_name()) as String
            val functionPara = if (ctx?.childCount == 2) listOf() else visit(ctx?.call_argument_clause()) as List<TypedParam>
            val functionHead = FunctionHead(functionName, functionPara, "Call")
            if (functionTable.containsKey(functionHead))
                visit(functionTable[functionHead])
            else
                NotDef
        } catch (e: Exception) {
            Error
        }
    }

    override fun visitCall_argument_clause(ctx: playgroundGrammarParser.Call_argument_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCall_argument(ctx: playgroundGrammarParser.Call_argumentContext?): Any {
        TODO("Not yet implemented")
    }
}