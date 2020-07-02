package org.ironica.playground

import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import playgroundGrammarParser
import playgroundGrammarVisitor

class PlaygroundVisitor(val player: Player): playgroundGrammarVisitor<Any> {

    val variableTable = mutableMapOf<String, Int>()
    val constantTable = mutableMapOf<String, Int>()

    override fun visitMoveForward(ctx: playgroundGrammarParser.MoveForwardContext?): Any {
        return player.moveForward()
    }

    override fun visitTurnLeft(ctx: playgroundGrammarParser.TurnLeftContext?): Any {
        return player.turnLeft()
    }

    override fun visitToggleSwitch(ctx: playgroundGrammarParser.ToggleSwitchContext?): Any {
        return player.toggleSwitch()
    }

    override fun visitCheckTruth(ctx: playgroundGrammarParser.CheckTruthContext?): Any {
        return visit(ctx?.conditional_expression())
    }

    override fun visitRangedStep(ctx: playgroundGrammarParser.RangedStepContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitIsOnGem(ctx: playgroundGrammarParser.IsOnGemContext?): Any {
        return player.isOnGem()
    }

    override fun visitIsOnClosedSwitch(ctx: playgroundGrammarParser.IsOnClosedSwitchContext?): Any {
        return player.isOnClosedSwitch()
    }

    override fun visitIsNestedCondition(ctx: playgroundGrammarParser.IsNestedConditionContext?): Any {
        val left: Boolean = visit(ctx?.conditional_expression(0)) as Boolean
        val right = visit(ctx?.conditional_expression(1)) as Boolean
        return if (ctx?.op?.type == playgroundGrammarParser.AND) left && right else left || right
    }

    override fun visitIsBlockedRight(ctx: playgroundGrammarParser.IsBlockedRightContext?): Any {
        return player.isBlockedRight()
    }

    override fun visitRangeHandler(ctx: playgroundGrammarParser.RangeHandlerContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitStatements(ctx: playgroundGrammarParser.StatementsContext?): Any {
        ctx?.children?.forEach { visit(it) }
        return true
    }

    override fun visitLoop_statement(ctx: playgroundGrammarParser.Loop_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFor_in_statement(ctx: playgroundGrammarParser.For_in_statementContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitBranch_statement(ctx: playgroundGrammarParser.Branch_statementContext?): Any {
        return visit(ctx?.if_statement())
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
        return tree?.accept(this)!!
    }

    override fun visitTop_level(ctx: playgroundGrammarParser.Top_levelContext?): Any {
        return visit(ctx?.statements())
    }

    override fun visitCollectGem(ctx: playgroundGrammarParser.CollectGemContext?): Any {
        return player.collectGem()
    }

    override fun visitIsBoolean(ctx: playgroundGrammarParser.IsBooleanContext?): Any {
        return when (ctx?.BOOLEAN_LITERAL()?.text) {
            "true" -> true
            "false"-> false
            else -> false
        }
    }

    override fun visitIsBlocked(ctx: playgroundGrammarParser.IsBlockedContext?): Any {
        return player.isBlocked()
    }

    override fun visitIsOnOpenedSwitch(ctx: playgroundGrammarParser.IsOnOpenedSwitchContext?): Any {
        return player.isOnOpenedSwitch()
    }

    override fun visitIsBlockedLeft(ctx: playgroundGrammarParser.IsBlockedLeftContext?): Any {
        return player.isBlockedLeft()
    }

    override fun visitStatement(ctx: playgroundGrammarParser.StatementContext?): Any {
        return visit(ctx?.children?.get(0))
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
        val left = visit(ctx?.pattern()) as String
        val right = visit(ctx?.expression()) as Int
        if (!constantTable.containsKey(left)) {
            constantTable[left] = right
            return true
        }
        return false
    }

    override fun visitVariable_declaration(ctx: playgroundGrammarParser.Variable_declarationContext?): Any {
        val left = visit(ctx?.pattern()) as String
        val right = visit(ctx?.expression()) as Int
        if (!variableTable.containsKey(left)) {
            variableTable[left] = right
            return true
        }
        return false
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
        return false
    }

    override fun visitPattern(ctx: playgroundGrammarParser.PatternContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitWildcard_pattern(ctx: playgroundGrammarParser.Wildcard_patternContext?): Any {
        return "_"
    }

    override fun visitIdentifier_pattern(ctx: playgroundGrammarParser.Identifier_patternContext?): Any {
        return ctx?.IDENTIFIER()?.text ?: ""
    }

}