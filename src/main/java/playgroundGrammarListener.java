// Generated from /Users/irony/IdeaProjects/playground/src/main/playgroundGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link playgroundGrammarParser}.
 */
public interface playgroundGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#top_level}.
	 * @param ctx the parse tree
	 */
	void enterTop_level(playgroundGrammarParser.Top_levelContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#top_level}.
	 * @param ctx the parse tree
	 */
	void exitTop_level(playgroundGrammarParser.Top_levelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moveForward}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMoveForward(playgroundGrammarParser.MoveForwardContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moveForward}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMoveForward(playgroundGrammarParser.MoveForwardContext ctx);
	/**
	 * Enter a parse tree produced by the {@code turnLeft}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTurnLeft(playgroundGrammarParser.TurnLeftContext ctx);
	/**
	 * Exit a parse tree produced by the {@code turnLeft}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTurnLeft(playgroundGrammarParser.TurnLeftContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toggleSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterToggleSwitch(playgroundGrammarParser.ToggleSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toggleSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitToggleSwitch(playgroundGrammarParser.ToggleSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collectGem}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCollectGem(playgroundGrammarParser.CollectGemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collectGem}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCollectGem(playgroundGrammarParser.CollectGemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkTruth}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCheckTruth(playgroundGrammarParser.CheckTruthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkTruth}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCheckTruth(playgroundGrammarParser.CheckTruthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rangedStep}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRangedStep(playgroundGrammarParser.RangedStepContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rangedStep}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRangedStep(playgroundGrammarParser.RangedStepContext ctx);
	/**
	 * Enter a parse tree produced by the {@code function_call}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(playgroundGrammarParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by the {@code function_call}
	 * labeled alternative in {@link playgroundGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(playgroundGrammarParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_call_expression}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call_expression(playgroundGrammarParser.Function_call_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_call_expression}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call_expression(playgroundGrammarParser.Function_call_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#call_argument_clause}.
	 * @param ctx the parse tree
	 */
	void enterCall_argument_clause(playgroundGrammarParser.Call_argument_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#call_argument_clause}.
	 * @param ctx the parse tree
	 */
	void exitCall_argument_clause(playgroundGrammarParser.Call_argument_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#call_argument}.
	 * @param ctx the parse tree
	 */
	void enterCall_argument(playgroundGrammarParser.Call_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#call_argument}.
	 * @param ctx the parse tree
	 */
	void exitCall_argument(playgroundGrammarParser.Call_argumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isOnGem}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsOnGem(playgroundGrammarParser.IsOnGemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isOnGem}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsOnGem(playgroundGrammarParser.IsOnGemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isOnClosedSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsOnClosedSwitch(playgroundGrammarParser.IsOnClosedSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isOnClosedSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsOnClosedSwitch(playgroundGrammarParser.IsOnClosedSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isBoolean}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsBoolean(playgroundGrammarParser.IsBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isBoolean}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsBoolean(playgroundGrammarParser.IsBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNestedCondition}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNestedCondition(playgroundGrammarParser.IsNestedConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNestedCondition}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNestedCondition(playgroundGrammarParser.IsNestedConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isBlocked}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsBlocked(playgroundGrammarParser.IsBlockedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isBlocked}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsBlocked(playgroundGrammarParser.IsBlockedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNegativeCondition}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNegativeCondition(playgroundGrammarParser.IsNegativeConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNegativeCondition}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNegativeCondition(playgroundGrammarParser.IsNegativeConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isOnOpenedSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsOnOpenedSwitch(playgroundGrammarParser.IsOnOpenedSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isOnOpenedSwitch}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsOnOpenedSwitch(playgroundGrammarParser.IsOnOpenedSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isBlockedRight}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsBlockedRight(playgroundGrammarParser.IsBlockedRightContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isBlockedRight}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsBlockedRight(playgroundGrammarParser.IsBlockedRightContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isBlockedLeft}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void enterIsBlockedLeft(playgroundGrammarParser.IsBlockedLeftContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isBlockedLeft}
	 * labeled alternative in {@link playgroundGrammarParser#conditional_expression}.
	 * @param ctx the parse tree
	 */
	void exitIsBlockedLeft(playgroundGrammarParser.IsBlockedLeftContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_literal(playgroundGrammarParser.Boolean_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_literal(playgroundGrammarParser.Boolean_literalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rangeHandler}
	 * labeled alternative in {@link playgroundGrammarParser#range_expression}.
	 * @param ctx the parse tree
	 */
	void enterRangeHandler(playgroundGrammarParser.RangeHandlerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rangeHandler}
	 * labeled alternative in {@link playgroundGrammarParser#range_expression}.
	 * @param ctx the parse tree
	 */
	void exitRangeHandler(playgroundGrammarParser.RangeHandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(playgroundGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(playgroundGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(playgroundGrammarParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(playgroundGrammarParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop_statement(playgroundGrammarParser.Loop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop_statement(playgroundGrammarParser.Loop_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#for_in_statement}.
	 * @param ctx the parse tree
	 */
	void enterFor_in_statement(playgroundGrammarParser.For_in_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#for_in_statement}.
	 * @param ctx the parse tree
	 */
	void exitFor_in_statement(playgroundGrammarParser.For_in_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(playgroundGrammarParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#while_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(playgroundGrammarParser.While_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#repeat_while_statement}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_while_statement(playgroundGrammarParser.Repeat_while_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#repeat_while_statement}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_while_statement(playgroundGrammarParser.Repeat_while_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#branch_statement}.
	 * @param ctx the parse tree
	 */
	void enterBranch_statement(playgroundGrammarParser.Branch_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#branch_statement}.
	 * @param ctx the parse tree
	 */
	void exitBranch_statement(playgroundGrammarParser.Branch_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(playgroundGrammarParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(playgroundGrammarParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void enterElse_clause(playgroundGrammarParser.Else_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#else_clause}.
	 * @param ctx the parse tree
	 */
	void exitElse_clause(playgroundGrammarParser.Else_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#control_transfer_statement}.
	 * @param ctx the parse tree
	 */
	void enterControl_transfer_statement(playgroundGrammarParser.Control_transfer_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#control_transfer_statement}.
	 * @param ctx the parse tree
	 */
	void exitControl_transfer_statement(playgroundGrammarParser.Control_transfer_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void enterBreak_statement(playgroundGrammarParser.Break_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void exitBreak_statement(playgroundGrammarParser.Break_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void enterContinue_statement(playgroundGrammarParser.Continue_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void exitContinue_statement(playgroundGrammarParser.Continue_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(playgroundGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(playgroundGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#code_block}.
	 * @param ctx the parse tree
	 */
	void enterCode_block(playgroundGrammarParser.Code_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#code_block}.
	 * @param ctx the parse tree
	 */
	void exitCode_block(playgroundGrammarParser.Code_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#constant_declaration}.
	 * @param ctx the parse tree
	 */
	void enterConstant_declaration(playgroundGrammarParser.Constant_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#constant_declaration}.
	 * @param ctx the parse tree
	 */
	void exitConstant_declaration(playgroundGrammarParser.Constant_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(playgroundGrammarParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(playgroundGrammarParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(playgroundGrammarParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(playgroundGrammarParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(playgroundGrammarParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(playgroundGrammarParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_signature}.
	 * @param ctx the parse tree
	 */
	void enterFunction_signature(playgroundGrammarParser.Function_signatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_signature}.
	 * @param ctx the parse tree
	 */
	void exitFunction_signature(playgroundGrammarParser.Function_signatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_result_type}.
	 * @param ctx the parse tree
	 */
	void enterFunction_result_type(playgroundGrammarParser.Function_result_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_result_type}.
	 * @param ctx the parse tree
	 */
	void exitFunction_result_type(playgroundGrammarParser.Function_result_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#function_body}.
	 * @param ctx the parse tree
	 */
	void enterFunction_body(playgroundGrammarParser.Function_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#function_body}.
	 * @param ctx the parse tree
	 */
	void exitFunction_body(playgroundGrammarParser.Function_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#parameter_clause}.
	 * @param ctx the parse tree
	 */
	void enterParameter_clause(playgroundGrammarParser.Parameter_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#parameter_clause}.
	 * @param ctx the parse tree
	 */
	void exitParameter_clause(playgroundGrammarParser.Parameter_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_list(playgroundGrammarParser.Parameter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_list(playgroundGrammarParser.Parameter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(playgroundGrammarParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(playgroundGrammarParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#param_name}.
	 * @param ctx the parse tree
	 */
	void enterParam_name(playgroundGrammarParser.Param_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#param_name}.
	 * @param ctx the parse tree
	 */
	void exitParam_name(playgroundGrammarParser.Param_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#type_annotation}.
	 * @param ctx the parse tree
	 */
	void enterType_annotation(playgroundGrammarParser.Type_annotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#type_annotation}.
	 * @param ctx the parse tree
	 */
	void exitType_annotation(playgroundGrammarParser.Type_annotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(playgroundGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(playgroundGrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(playgroundGrammarParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(playgroundGrammarParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#wildcard_pattern}.
	 * @param ctx the parse tree
	 */
	void enterWildcard_pattern(playgroundGrammarParser.Wildcard_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#wildcard_pattern}.
	 * @param ctx the parse tree
	 */
	void exitWildcard_pattern(playgroundGrammarParser.Wildcard_patternContext ctx);
	/**
	 * Enter a parse tree produced by {@link playgroundGrammarParser#identifier_pattern}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_pattern(playgroundGrammarParser.Identifier_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link playgroundGrammarParser#identifier_pattern}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_pattern(playgroundGrammarParser.Identifier_patternContext ctx);
}