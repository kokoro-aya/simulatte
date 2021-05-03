package org.ironica.amatsukaze

import amatsukazeGrammarLexer
import amatsukazeGrammarParser
import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

@Serializable
data class PlayerData(val id: Int, val x: Int, val y: Int, val dir: String, val role: String)

@Serializable
data class TileInfo(val x: Int, val y: Int, val color: String, val level: Int)

@Serializable
data class Data(
    val type: String,
    val code: String,
    val grid: Array<Array<String>>,
    val layout: Array<Array<String>>,
    val misc: Array<Array<String>>,
    val portals: Array<Portal>,
    val locks: Array<Lock>,
    val players: Array<PlayerData>
)

fun convertJsonToGrid(array: Array<Array<String>>): Grid {
    return array.map { it.map { when (it) {
        "OPEN" -> Block.OPEN
        "BLOCKED" -> Block.BLOCKED
        "WATER" -> Block.WATER
        "TREE" -> Block.TREE
        "DESERT" -> Block.DESERT
        "HOME" -> Block.HOME
        "MOUNTAIN" -> Block.MOUNTAIN
        "STONE" -> Block.STONE
        "LOCK" -> Block.LOCK
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
        "PORTAL" -> Item.PORTAL
        "PLATFORM" -> Item.PLATFORM
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

fun convertJsonToMiscLayout(array: Array<Array<String>>, using: String): SecondLayout {
    return when (using) {
        "colorful" -> array.map { it.map { ColorfulTile(convertDataToColor(it)) as Tile }.toTypedArray() }.toTypedArray()
        "mountainous" -> array.map { it.map { MountainTile(it.toIntOrNull()) as Tile }.toTypedArray() }.toTypedArray()
        else -> throw Exception("Unsupported game module")
    }
}

private fun convertDataToColor(data: String): Color {
    return when (data) {
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
}

fun calculateInitialGem(layout: Layout): Int = layout.flatten().filter { it == Item.GEM }.size

class AmatsukazeInterface(type: String, code: String, grid: Grid, layout: Layout, miscLayout: SecondLayout, portals: Array<Portal>, locks: Array<Lock>, players: Array<Player>) {
    private val input: CharStream = CharStreams.fromString(code)
    private val lexer = amatsukazeGrammarLexer(input)
    private val tokens = CommonTokenStream(lexer)
    private val parser = amatsukazeGrammarParser(tokens)
    private val tree: ParseTree = parser.top_level()
    private val playground = Playground(grid, layout, miscLayout, portals, locks, players.toMutableList(), calculateInitialGem(layout))
    private val manager = when (type) {
        "colorful" -> ColorfulManager(playground)
        "mountainous" -> MountainousManager(playground)
        else -> throw Exception("Unsupported game module")
    }
    private val exec = AmatsukazeVisitor(manager)
    fun start() {
        exec.visit(tree)
    }
}