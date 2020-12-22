package org.ironica.amatsukaze

import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser

@Serializable
data class PlayerData(val id: Int, val x: Int, val y: Int, val dir: String, val role: String)

@Serializable
data class TileInfo(val x: Int, val y: Int, val color: String, val level: Int, val liftable: Boolean)

@Serializable
data class Data(val code: String, val grid: Array<Array<String>>, val layout: Array<Array<String>>, val layout2s: Array<TileInfo>, val portals: Array<Portal>, val players: Array<PlayerData>)

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

fun convertJsonToPlayers(array: Array<PlayerData>): Array<Player> {
    return array.map { when(it.role) {
        "PLAYER" -> Player(it.id, Coordinate(it.x, it.y), when(it.dir) {
            "UP" -> Direction.UP
            "DOWN" -> Direction.DOWN
            "LEFT" -> Direction.LEFT
            "RIGHT" -> Direction.RIGHT
            else -> throw Exception("Cannot parse data to player")
        })
        "SPECIALIST" -> Specialist(it.id, Coordinate(it.x, it.y), when(it.dir) {
            "UP" -> Direction.UP
            "DOWN" -> Direction.DOWN
            "LEFT" -> Direction.LEFT
            "RIGHT" -> Direction.RIGHT
            else -> throw Exception("Cannot parse data to specialist")
        })
        else -> throw Exception("Cannot parse data to player list")
    } }.toTypedArray()
}

fun convertJsonToLayout2s(array: Array<TileInfo>, height: Int, width: Int): SecondLayout {
    val ar = Array(height) { Array(width) { Tile() } }
    array.forEach { ar[it.y][it.x] = convertDataToTile(it) }
    return ar
}

private fun convertDataToTile(ti: TileInfo): Tile {
    val color = when (ti.color) {
        "BLACK" -> Color.BLACK
        "SILVER" -> Color.SILVER
        "GREY" -> Color.GREY
        "WHITE" -> Color.WHITE
        "RED" -> Color.RED
        "ORANGE" -> Color.ORANGE
        "GOLD" -> Color.GOLD
        "PINK" -> Color.PINK
        "YELLOW" -> Color.YELLOW
        "BEIGE" -> Color.BEIGE
        "BROWN" -> Color.BROWN
        "GREEN" -> Color.GREEN
        "AZURE" -> Color.AZURE
        "CYAN" -> Color.CYAN
        "ALICEBLUE" -> Color.ALICEBLUE
        "PURPLE" -> Color.PURPLE
        else -> throw Exception("Cannot parse data to color")
    }
    return Tile(color, ti.level, ti.liftable)
}

fun calculateInitialGem(layout: Layout): Int = layout.flatten().filter { it == Item.GEM }.size

class PlaygroundInterface(code: String, grid: Grid, layout: Layout, layout2s: SecondLayout, portals: Array<Portal>, players: Array<Player>) {
    private val input: CharStream = CharStreams.fromString(code)
    private val lexer = playgroundGrammarLexer(input)
    private val tokens = CommonTokenStream(lexer)
    private val parser = playgroundGrammarParser(tokens)
    private val tree: ParseTree = parser.top_level()
    val playground = Playground(grid, layout, layout2s, portals, players, calculateInitialGem(layout))
    private val manager = PlaygroundManager(playground)
    private val exec = PlaygroundVisitor(manager)
    fun start() {
        exec.visit(tree)
    }
}