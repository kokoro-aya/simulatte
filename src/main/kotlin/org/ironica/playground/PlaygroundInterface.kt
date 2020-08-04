package org.ironica.playground

import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser

@Serializable
data class Code(val value: String)

class PlaygroundInterface(code: String) {
    private val input: CharStream = CharStreams.fromString(code)
    private val lexer = playgroundGrammarLexer(input)
    private val tokens = CommonTokenStream(lexer)
    private val parser = playgroundGrammarParser(tokens)
    private val tree: ParseTree = parser.top_level()
//    private val grid = arrayOf(
//        arrayOf(Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN),
//        arrayOf(Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED)
//    )
    private val grid = arrayOf(
        arrayOf(Block.OPEN, Block.OPEN, Block.BLOCKED, Block.OPENEDSWITCH, Block.OPEN, Block.BLOCKED, Block.BLOCKED, Block.BLOCKED, Block.OPEN),
        arrayOf(Block.BLOCKED, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN),
        arrayOf(Block.GEM, Block.OPEN, Block.BLOCKED, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.OPEN, Block.BLOCKED, Block.GEM),
        arrayOf(Block.BLOCKED, Block.OPENEDSWITCH, Block.GEM, Block.BLOCKED, Block.BLOCKED, Block.GEM, Block.CLOSEDSWITCH, Block.BLOCKED, Block.BLOCKED),
        arrayOf(Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.BLOCKED, Block.BLOCKED, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.BLOCKED)
    )
    private val player = Player(
        Coordinate(0, 0),
        Direction.RIGHT
    )
    val playground = Playground(grid, player, 2)
    private val manager = PlaygroundManager(playground)
    private val exec = PlaygroundVisitor(manager)
    fun start() {
        exec.visit(tree)
    }
}