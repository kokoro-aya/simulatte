// Generated from /Users/irony/IdeaProjects/playground/src/main/playgroundGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link playgroundGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface playgroundGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#top_level}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop_level(playgroundGrammarParser.Top_levelContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(playgroundGrammarParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#numeric_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_literal(playgroundGrammarParser.Numeric_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#char_sequence_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_sequence_literal(playgroundGrammarParser.Char_sequence_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#integer_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_literal(playgroundGrammarParser.Integer_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#double_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_literal(playgroundGrammarParser.Double_literalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpr(playgroundGrammarParser.AssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolComparativeExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolComparativeExpr(playgroundGrammarParser.BoolComparativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exponentExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentExpr(playgroundGrammarParser.ExponentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(playgroundGrammarParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ariAssignmentExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAriAssignmentExpr(playgroundGrammarParser.AriAssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code function_call}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(playgroundGrammarParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpr(playgroundGrammarParser.MemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ariComparativeExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAriComparativeExpr(playgroundGrammarParser.AriComparativeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalValueExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralValueExpr(playgroundGrammarParser.LiteralValueExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpr(playgroundGrammarParser.MulDivModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpr(playgroundGrammarParser.VariableExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNestedCondition}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNestedCondition(playgroundGrammarParser.IsNestedConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNegativeCondition}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNegativeCondition(playgroundGrammarParser.IsNegativeConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rangeExpression}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpression(playgroundGrammarParser.RangeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExpr}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpr(playgroundGrammarParser.ParenthesisExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#assignment_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_expression(playgroundGrammarParser.Assignment_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#literal_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral_expression(playgroundGrammarParser.Literal_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#member_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember_expression(playgroundGrammarParser.Member_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#parenthesized_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesized_expression(playgroundGrammarParser.Parenthesized_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#variable_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_expression(playgroundGrammarParser.Variable_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_call_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call_expression(playgroundGrammarParser.Function_call_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#call_argument_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_argument_clause(playgroundGrammarParser.Call_argument_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#call_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_argument(playgroundGrammarParser.Call_argumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#boolean_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_literal(playgroundGrammarParser.Boolean_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(playgroundGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(playgroundGrammarParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#loop_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_statement(playgroundGrammarParser.Loop_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#for_in_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_in_statement(playgroundGrammarParser.For_in_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(playgroundGrammarParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#repeat_while_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat_while_statement(playgroundGrammarParser.Repeat_while_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#branch_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch_statement(playgroundGrammarParser.Branch_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(playgroundGrammarParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#else_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_clause(playgroundGrammarParser.Else_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#control_transfer_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControl_transfer_statement(playgroundGrammarParser.Control_transfer_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#break_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_statement(playgroundGrammarParser.Break_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#continue_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_statement(playgroundGrammarParser.Continue_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(playgroundGrammarParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(playgroundGrammarParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#code_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode_block(playgroundGrammarParser.Code_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#constant_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant_declaration(playgroundGrammarParser.Constant_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(playgroundGrammarParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(playgroundGrammarParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(playgroundGrammarParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_signature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_signature(playgroundGrammarParser.Function_signatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_result_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_result_type(playgroundGrammarParser.Function_result_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#function_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_body(playgroundGrammarParser.Function_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#parameter_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_clause(playgroundGrammarParser.Parameter_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_list(playgroundGrammarParser.Parameter_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(playgroundGrammarParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#param_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_name(playgroundGrammarParser.Param_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#type_annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_annotation(playgroundGrammarParser.Type_annotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(playgroundGrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(playgroundGrammarParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#wildcard_pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard_pattern(playgroundGrammarParser.Wildcard_patternContext ctx);
	/**
	 * Visit a parse tree produced by {@link playgroundGrammarParser#identifier_pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier_pattern(playgroundGrammarParser.Identifier_patternContext ctx);
}