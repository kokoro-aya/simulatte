package org.ironica.amatsukaze

import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser

@Serializable
data class PlayerData(val id: Int, val x: Int, val y: Int, val dir: String)

@Serializable
data class Data(val code: String, val grid: Array<Array<String>>, val layout: Array<Array<String>>, val players: Array<PlayerData>)

fun convertJsonToGrid(array: Array<Array<String>>): Grid {
    return array.map { it.map { when (it) {
        "OPEN" -> Block.OPEN
        "BLOCKED" -> Block.BLOCKED
        "WATER" -> Block.WATER
        "TREE" -> Block.TREE
        "DESERT" -> Block.DESERT
        "HOME" -> Block.HOME
        else -> throw Exception("Cannot parse data to grid")
    } }.toTypedArray() }.toTypedArray()
}

fun convertJsonToLayout(array: Array<Array<String>>): Layout {
    return array.map { it.map { when (it) {
        "NONE" -> Item.NONE
        "GEM" -> Item.GEM
        "BEEPER" -> Item.BEEPER
        "OPENEDSWITCH" -> Item.OPENEDSWITCH
        "CLOSEDSWITCH" -> Item.CLOSEDSWITCH
        else -> throw Exception("Cannot parse data to layout")
    } }.toTypedArray()}.toTypedArray()
}

fun convertJsonToPlayers(array: Array<>)

fun calculateInitialGem(layout: Layout): Int = layout.flatten().filter { it == Item.GEM }.size

class PlaygroundInterface(code: String, grid: Grid, layout: Layout) {
    private val input: CharStream = CharStreams.fromString(code)
    private val lexer = playgroundGrammarLexer(input)
    private val tokens = CommonTokenStream(lexer)
    private val parser = playgroundGrammarParser(tokens)
    private val tree: ParseTree = parser.top_level()
    private val player = Player(
        Coordinate(0, 0),
        Direction.RIGHT
    )
    val playground = Playground(grid, layout, arrayOf(player), calculateInitialGem(layout))
    private val manager = PlaygroundManager(playground)
    private val exec = PlaygroundVisitor(manager)
    fun start() {
        exec.visit(tree)
    }
}