package org.ironica.playground

import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser
import java.io.FileInputStream
import java.io.InputStream

@Serializable
data class Code(val code: String)

class PlaygroundInterface(code: String) {
        private val input: CharStream = CharStreams.fromString(code)
        private val lexer = playgroundGrammarLexer(input)
        private val tokens = CommonTokenStream(lexer)
        private val parser = playgroundGrammarParser(tokens)
        private val tree: ParseTree = parser.top_level()
    private val grid = arrayOf(
        arrayOf(Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN, Block.CLOSEDSWITCH, Block.OPEN),
        arrayOf(Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED, Block.GEM, Block.BLOCKED)
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