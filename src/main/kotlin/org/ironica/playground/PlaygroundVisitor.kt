package org.ironica.playground

import org.antlr.v4.runtime.tree.*
import playgroundGrammarParser
import playgroundGrammarVisitor

class PlaygroundVisitor(val player: Player): playgroundGrammarVisitor<Any> {

    val variableTable = mutableMapOf<String, Int>()
    val constantTable = mutableMapOf<String, Int>()

    var _break = false
    var _continue = false

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
        return visit(ctx?.range_expression())
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
        val lower = visit(ctx?.children?.get(0)) as Int
        val upper = visit(ctx?.children?.get(2)) as Int
        // println("lower $lower upper $upper")
        if (ctx?.op?.type == playgroundGrammarParser.UNTIL) {
            return (lower until upper)
        }
        if (ctx?.op?.type == playgroundGrammarParser.THROUGH) {
            return (lower .. upper)
        }
        return -1
    }

    override fun visitStatements(ctx: playgroundGrammarParser.StatementsContext?): Any {
        for (child in ctx?.children!!) {
            if (_break || _continue)
                return -3
            visit(child)
        }
        return 0
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
        return 0
    }

    override fun visitBranch_statement(ctx: playgroundGrammarParser.Branch_statementContext?): Any {
        return visit(ctx?.if_statement())
    }

    override fun visitElse_clause(ctx: playgroundGrammarParser.Else_clauseContext?): Any {
        return visit(ctx?.children?.get(1))
    }

    override fun visitErrorNode(node: ErrorNode?): Any {
        return -4
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
        return 0
    }

    override fun visitControl_transfer_statement(ctx: playgroundGrammarParser.Control_transfer_statementContext?): Any {
        return visit(ctx?.children?.get(0))
    }

    override fun visitDeclaration(ctx: playgroundGrammarParser.DeclarationContext?): Any {
        return visit(ctx?.children?.get(0))
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
        return when (ctx?.boolean_literal()?.text) {
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
        return 0
    }

    override fun visitBreak_statement(ctx: playgroundGrammarParser.Break_statementContext?): Any {
        _break = true
        return -1;
    }

    override fun visitContinue_statement(ctx: playgroundGrammarParser.Continue_statementContext?): Any {
        _continue = true
        return -2;
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
        var ret: Any? = node?.text?.toIntOrNull()
        if (ret != null) return (ret as Int)
        else {
            if (node.toString() == "true") return true
            if (node.toString() == "false") return false
            return -4
        }
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

    override fun visitIsNegativeCondition(ctx: playgroundGrammarParser.IsNegativeConditionContext?): Any {
        return !(visit(ctx?.children?.get(1)) as Boolean)
    }

    override fun visitBoolean_literal(ctx: playgroundGrammarParser.Boolean_literalContext?): Any {
        return ctx?.text == "true"
    }

    override fun visitFunction_name(ctx: playgroundGrammarParser.Function_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitFunction_result_type(ctx: playgroundGrammarParser.Function_result_typeContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter_clause(ctx: playgroundGrammarParser.Parameter_clauseContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter_list(ctx: playgroundGrammarParser.Parameter_listContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParameter(ctx: playgroundGrammarParser.ParameterContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitParam_name(ctx: playgroundGrammarParser.Param_nameContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType_annotation(ctx: playgroundGrammarParser.Type_annotationContext?): Any {
        TODO("Not yet implemented")
    }

    override fun visitType(ctx: playgroundGrammarParser.TypeContext?): Any {
        TODO("Not yet implemented")
    }

}