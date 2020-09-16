package org.ironica.playground

import amatsukazeGrammarVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

sealed class DataType
object _INT
object _DOUBLE
object _CHARACTER
object _STRING
object _BOOL
object _VOID
object _CALL
object _FUNCTION
object _STRUCT
object _ENUM
data class _ARRAY(val type: DataType)

sealed class Proto
sealed class TypeL: Proto()
data class IntegerL(val variability: Variability, val content: Int, val prototype: Prototype): TypeL()
data class DoubleL(val variability: Variability, val content: Double, val prototype: Prototype): TypeL()
data class BooleanL(val variability: Variability, val content: Boolean, val prototype: Prototype): TypeL()
data class CharacterL(val variability: Variability, val content: Char, val prototype: Prototype): TypeL()
data class StringL(val variability: Variability, val content: String, val prototype: Prototype): TypeL()

data class ReturnedL(val content: Any): Throwable()
data class StructL(val variability: Variability, val body: MutableMap<String, Any>, var prototype: Prototype): TypeL()
data class FunctionL(val variability: Variability, var head: FuncHead, var body: ParseTree, var prototype: Prototype): TypeL()
data class ArrayL(val variability: Variability, val size: Int, val content: Array<TypeL> = Array(size) { NullL }): TypeL()
object NullL: TypeL()
data class Prototype (
    val members: MutableMap<String, TypeL>,
    var prototype: Proto,
    var ctor: TypeL
): Proto()

data class FuncHead(val name: String, val params: List<String>, val types: List<DataType>, val ret: DataType) {
    fun pseudoEquals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FuncHead

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

        other as FuncHead

        if (name != other.name) return false
        if (params != other.params) return false
        if (types != other.types) return false
        if (ret != other.ret) return false

        return true
    }
}

class AmatsukazeVisitor(private val manager: PlaygroundManager): amatsukazeGrammarVisitor<Any> {

    private val typeTable = mutableMapOf<String, Prototype>()
    private val variableTable = mutableMapOf<String, TypeL>()
    private val internalVariables = listOf<Map<String, TypeL>>()

    private fun initialize() {
        val Null = NullL
        val IntProto = Prototype(mutableMapOf(), Null, Null)
        val DoubleProto = Prototype(mutableMapOf(), Null, Null)
        val BooleanProto = Prototype(mutableMapOf(), Null, Null)
        val CharacterProto = Prototype(mutableMapOf(), Null, Null)
        val StringProto = Prototype(mutableMapOf(), Null, Null)
        val StructProto = Prototype(mutableMapOf(), Null, Null)
        val FunctionProto = Prototype(mutableMapOf(), Null, Null)
        val ArrayProto = Prototype(mutableMapOf(), Null, Null)
        typeTable.putAll(mapOf("Int" to IntProto, "Double" to DoubleProto, "Boolean" to BooleanProto, "Character" to CharacterProto,
        "String" to StringProto, "Struct" to StructProto, "Function" to FunctionProto, "Array" to ArrayProto))
        /*
        String -> size, replace, substr, toCharArray
        Boolean -> not, and, or, xor
        Character -> isDigit, isAlpha, isUpper, isLower, isWhite, toInt
        Struct -> varDump, count, erase, clear
        Array -> size, clear, swap, first, last, push_back, push_front, pop_back, pop_front
        Certain functions can be done by adding extensions on prototype chain.
         */

    }

    override fun visit(tree: ParseTree?): Any {
        return tree?.accept(this) ?: throw Exception("Encountered error while visiting AST")
    }

    override fun visitChildren(node: RuleNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitTerminal(node: TerminalNode?): Any {
        if (node?.text != null) return node.text
        throw Exception("Encountered error while visiting terminal node")
    }

    override fun visitErrorNode(node: ErrorNode?): Any {
        throw Exception("Return from ErrorNode")
    }

    override fun visitTop_level(ctx: amatsukazeGrammarParser.Top_levelContext?): Any {
        return visit(ctx?.statements())
    }

    override fun visitLiteral(ctx: amatsukazeGrammarParser.LiteralContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitNumeric_literal(ctx: amatsukazeGrammarParser.Numeric_literalContext?): Any {
        return when {
            ctx?.childCount == 1 -> visit(ctx.getChild(0))
            ctx?.integer_literal()?.isEmpty!! -> - (visit(ctx.getChild(1)) as Double)
            else -> - (visit(ctx.getChild(1)) as Int)
        }
    }

    override fun visitBoolean_literal(ctx: amatsukazeGrammarParser.Boolean_literalContext?): Any {
        return when (ctx?.text) {
            "true" -> true
            "false" -> false
            else -> throw Exception("Cannot parse literal to boolean type")
        }
    }

    override fun visitChar_sequence_literal(ctx: amatsukazeGrammarParser.Char_sequence_literalContext?): Any {
        return if (ctx?.STRING_LITERAL()?.text?.isEmpty()!!)
            ctx.getChild(0).text[0]
        else
            ctx?.getChild(0)?.text!!
    }

    override fun visitNil_literal(ctx: amatsukazeGrammarParser.Nil_literalContext?): Any {
        return NullL
    }

    override fun visitInteger_literal(ctx: amatsukazeGrammarParser.Integer_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL())}".toInt()
    }

    override fun visitDouble_literal(ctx: amatsukazeGrammarParser.Double_literalContext?): Any {
        return "${visit(ctx?.DECIMAL_LITERAL(0))}.${visit(ctx?.DECIMAL_LITERAL(1))}".toDouble()
    }

    override fun visitAssignmentExpr(ctx: amatsukazeGrammarParser.AssignmentExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitThisExpr(ctx: amatsukazeGrammarParser.ThisExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBoolComparativeExpr(ctx: amatsukazeGrammarParser.BoolComparativeExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_call(ctx: amatsukazeGrammarParser.Struct_callContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAriAssignmentExpr(ctx: amatsukazeGrammarParser.AriAssignmentExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitExponentExpr(ctx: amatsukazeGrammarParser.ExponentExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAddSubExpr(ctx: amatsukazeGrammarParser.AddSubExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_call(ctx: amatsukazeGrammarParser.Function_callContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMemberExpr(ctx: amatsukazeGrammarParser.MemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAriComparativeExpr(ctx: amatsukazeGrammarParser.AriComparativeExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitLiteralValueExpr(ctx: amatsukazeGrammarParser.LiteralValueExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMulDivModExpr(ctx: amatsukazeGrammarParser.MulDivModExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariableExpr(ctx: amatsukazeGrammarParser.VariableExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsNestedCondition(ctx: amatsukazeGrammarParser.IsNestedConditionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitExprFuncDeclExpr(ctx: amatsukazeGrammarParser.ExprFuncDeclExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsNegativeCondition(ctx: amatsukazeGrammarParser.IsNegativeConditionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRangeExpression(ctx: amatsukazeGrammarParser.RangeExpressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitSubscriExpr(ctx: amatsukazeGrammarParser.SubscriExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParenthesisExpr(ctx: amatsukazeGrammarParser.ParenthesisExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAssignment_expression(ctx: amatsukazeGrammarParser.Assignment_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitLiteral_expression(ctx: amatsukazeGrammarParser.Literal_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitArray_literal(ctx: amatsukazeGrammarParser.Array_literalContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitArray_literal_item(ctx: amatsukazeGrammarParser.Array_literal_itemContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitThis_expression(ctx: amatsukazeGrammarParser.This_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMember_expression(ctx: amatsukazeGrammarParser.Member_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitSubscript_expression(ctx: amatsukazeGrammarParser.Subscript_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitSubscript(ctx: amatsukazeGrammarParser.SubscriptContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariable_expression(ctx: amatsukazeGrammarParser.Variable_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_call_expression(ctx: amatsukazeGrammarParser.Function_call_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCall_argument_clause(ctx: amatsukazeGrammarParser.Call_argument_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCall_argument(ctx: amatsukazeGrammarParser.Call_argumentContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_call_expression(ctx: amatsukazeGrammarParser.Struct_call_expressionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitDot_operator(ctx: amatsukazeGrammarParser.Dot_operatorContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatement(ctx: amatsukazeGrammarParser.StatementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatements(ctx: amatsukazeGrammarParser.StatementsContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitLoop_statement(ctx: amatsukazeGrammarParser.Loop_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFor_in_statement(ctx: amatsukazeGrammarParser.For_in_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitWhile_statement(ctx: amatsukazeGrammarParser.While_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRepeat_while_statement(ctx: amatsukazeGrammarParser.Repeat_while_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBranch_statement(ctx: amatsukazeGrammarParser.Branch_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIf_statement(ctx: amatsukazeGrammarParser.If_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitElse_clause(ctx: amatsukazeGrammarParser.Else_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitControl_transfer_statement(ctx: amatsukazeGrammarParser.Control_transfer_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBreak_statement(ctx: amatsukazeGrammarParser.Break_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitContinue_statement(ctx: amatsukazeGrammarParser.Continue_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitReturn_statement(ctx: amatsukazeGrammarParser.Return_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitYield_statement(ctx: amatsukazeGrammarParser.Yield_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitConstantDecl(ctx: amatsukazeGrammarParser.ConstantDeclContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariableDecl(ctx: amatsukazeGrammarParser.VariableDeclContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunctionDecl(ctx: amatsukazeGrammarParser.FunctionDeclContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnumDecl(ctx: amatsukazeGrammarParser.EnumDeclContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStructDecl(ctx: amatsukazeGrammarParser.StructDeclContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCode_block(ctx: amatsukazeGrammarParser.Code_blockContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitConstant_declaration(ctx: amatsukazeGrammarParser.Constant_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariable_declaration(ctx: amatsukazeGrammarParser.Variable_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_declaration(ctx: amatsukazeGrammarParser.Function_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_name(ctx: amatsukazeGrammarParser.Function_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_signature(ctx: amatsukazeGrammarParser.Function_signatureContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_result_type(ctx: amatsukazeGrammarParser.Function_result_typeContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_body(ctx: amatsukazeGrammarParser.Function_bodyContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitExpressional_function_declaration(ctx: amatsukazeGrammarParser.Expressional_function_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitArrowfun_declaration(ctx: amatsukazeGrammarParser.Arrowfun_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter_clause(ctx: amatsukazeGrammarParser.Parameter_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter_list(ctx: amatsukazeGrammarParser.Parameter_listContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter(ctx: amatsukazeGrammarParser.ParameterContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParam_name(ctx: amatsukazeGrammarParser.Param_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_declaration(ctx: amatsukazeGrammarParser.Enum_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_name(ctx: amatsukazeGrammarParser.Enum_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_members(ctx: amatsukazeGrammarParser.Enum_membersContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitEnum_member(ctx: amatsukazeGrammarParser.Enum_memberContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_declaration(ctx: amatsukazeGrammarParser.Struct_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_name(ctx: amatsukazeGrammarParser.Struct_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_body(ctx: amatsukazeGrammarParser.Struct_bodyContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_initializer(ctx: amatsukazeGrammarParser.Struct_initializerContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitAttribute_assignment(ctx: amatsukazeGrammarParser.Attribute_assignmentContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStruct_member(ctx: amatsukazeGrammarParser.Struct_memberContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType_inheritance_clause(ctx: amatsukazeGrammarParser.Type_inheritance_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType_annotation(ctx: amatsukazeGrammarParser.Type_annotationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitPattern(ctx: amatsukazeGrammarParser.PatternContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitWildcard_pattern(ctx: amatsukazeGrammarParser.Wildcard_patternContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIdentifier_pattern(ctx: amatsukazeGrammarParser.Identifier_patternContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMember_expression_pattern(ctx: amatsukazeGrammarParser.Member_expression_patternContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitSubscript_expression_pattern(ctx: amatsukazeGrammarParser.Subscript_expression_patternContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitNumberedExpMemberExpr(ctx: amatsukazeGrammarParser.NumberedExpMemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitNamedExpMemberExpr(ctx: amatsukazeGrammarParser.NamedExpMemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFuncExpMemberExpr(ctx: amatsukazeGrammarParser.FuncExpMemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitMemberOfFuncCallExpr(ctx: amatsukazeGrammarParser.MemberOfFuncCallExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRecursiveExpMemberExpr(ctx: amatsukazeGrammarParser.RecursiveExpMemberExprContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitSimpleType(ctx: amatsukazeGrammarParser.SimpleTypeContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitArrayType(ctx: amatsukazeGrammarParser.ArrayTypeContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitArrayTypeSub(ctx: amatsukazeGrammarParser.ArrayTypeSubContext?): Any {
        TODO("Not yet implemented")
    }

}