package org.ironica.amatsukaze

import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser

@Serializable
data class Data(val code: String, val grid: Array<Array<String>>)

fun convertJsonToGrid(array: Array<Array<String>>): Grid {
    return array.map { it.map { when (it) {
        "OPEN" -> Block.OPEN
        "BLOCKED" -> Block.BLOCKED
        "GEM" -> Block.GEM
        "OPENEDSWITCH" -> Block.OPENEDSWITCH
        "CLOSEDSWITCH" -> Block.CLOSEDSWITCH
        else -> throw Exception("Cannot parse data to grid")
    } }.toTypedArray() }.toTypedArray()
}

fun calculateInitialGem(grid: Grid): Int = grid.flatten().filter { it == Block.GEM }.size

class PlaygroundInterface(code: String, grid: Grid) {
    private val input: CharStream = CharStreams.fromString(code)
    private val lexer = playgroundGrammarLexer(input)
    private val tokens = CommonTokenStream(lexer)
    private val parser = playgroundGrammarParser(tokens)
    private val tree: ParseTree = parser.top_level()
    private val player = Player(
        Coordinate(0, 0),
        Direction.RIGHT
    )
    val playground = Playground(grid, player, calculateInitialGem(grid))
    private val manager = PlaygroundManager(playground)
    private val exec = PlaygroundVisitor(manager)
    fun start() {
        exec.visit(tree)
    }
}