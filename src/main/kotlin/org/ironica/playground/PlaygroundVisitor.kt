package org.ironica.playground

import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import playgroundGrammarParser
import playgroundGrammarVisitor

class PlaygroundVisitor(val player: Player): playgroundGrammarVisitor<Any> {

    override fun visitMoveForward(ctx: playgroundGrammarParser.MoveForwardContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitTurnLeft(ctx: playgroundGrammarParser.TurnLeftContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitToggleSwitch(ctx: playgroundGrammarParser.ToggleSwitchContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCheckTruth(ctx: playgroundGrammarParser.CheckTruthContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRangedStep(ctx: playgroundGrammarParser.RangedStepContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsOnGem(ctx: playgroundGrammarParser.IsOnGemContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsOnClosedSwitch(ctx: playgroundGrammarParser.IsOnClosedSwitchContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsNestedCondition(ctx: playgroundGrammarParser.IsNestedConditionContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsBlockedRight(ctx: playgroundGrammarParser.IsBlockedRightContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRangeHandler(ctx: playgroundGrammarParser.RangeHandlerContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatements(ctx: playgroundGrammarParser.StatementsContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitLoop_statement(ctx: playgroundGrammarParser.Loop_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFor_in_statement(ctx: playgroundGrammarParser.For_in_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBranch_statement(ctx: playgroundGrammarParser.Branch_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitElse_clause(ctx: playgroundGrammarParser.Else_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitErrorNode(node: ErrorNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitRepeat_while_statement(ctx: playgroundGrammarParser.Repeat_while_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitControl_transfer_statement(ctx: playgroundGrammarParser.Control_transfer_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitDeclaration(ctx: playgroundGrammarParser.DeclarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_signature(ctx: playgroundGrammarParser.Function_signatureContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visit(tree: ParseTree?): Any {
        TODO("Not yet implemented")
    }

    override fun visitTop_level(ctx: playgroundGrammarParser.Top_levelContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCollectGem(ctx: playgroundGrammarParser.CollectGemContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsBoolean(ctx: playgroundGrammarParser.IsBooleanContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsBlocked(ctx: playgroundGrammarParser.IsBlockedContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsOnOpenedSwitch(ctx: playgroundGrammarParser.IsOnOpenedSwitchContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsBlockedLeft(ctx: playgroundGrammarParser.IsBlockedLeftContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatement(ctx: playgroundGrammarParser.StatementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitWhile_statement(ctx: playgroundGrammarParser.While_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIf_statement(ctx: playgroundGrammarParser.If_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBreak_statement(ctx: playgroundGrammarParser.Break_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitContinue_statement(ctx: playgroundGrammarParser.Continue_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitCode_block(ctx: playgroundGrammarParser.Code_blockContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitConstant_declaration(ctx: playgroundGrammarParser.Constant_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitVariable_declaration(ctx: playgroundGrammarParser.Variable_declarationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitTerminal(node: TerminalNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_body(ctx: playgroundGrammarParser.Function_bodyContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitChildren(node: RuleNode?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_declaration(ctx: playgroundGrammarParser.Function_declarationContext?): Any {
        TODO("Not yet implemented")
    }

}