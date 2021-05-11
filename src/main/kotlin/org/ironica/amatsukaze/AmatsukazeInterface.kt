package org.ironica.amatsukaze

import amatsukazeGrammarLexer
import amatsukazeGrammarParser
import kotlinx.serialization.Serializable
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

@Serializable
data class PlayerData(val id: Int, val x: Int, val y: Int, val dir: String, val role: String, val stamina: String)


@Serializable
data class Data(
    val type: String,
    val code: String,
    val grid: List<List<String>>,
    val layout: List<List<String>>,
    val misc: List<List<String>>,
    val portals: List<Portal>,
    val locks: List<Lock>,
    val players: List<PlayerData>
)

fun convertJsonToGrid(array: List<List<String>>): Grid {
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
    } }.toMutableList() }.toMutableList()
}

fun convertJsonToLayout(array: List<List<String>>): Layout {
    return array.map { it.map { when (it) {
        "NONE" -> Item.NONE
        "GEM" -> Item.GEM
        "BEEPER" -> Item.BEEPER
        "OPENEDSWITCH" -> Item.OPENEDSWITCH
        "CLOSEDSWITCH" -> Item.CLOSEDSWITCH
        "PORTAL" -> Item.PORTAL
        "PLATFORM" -> Item.PLATFORM
        else -> throw Exception("Cannot parse data to layout")
    } }.toMutableList()}.toMutableList()
}

fun convertJsonToPlayers(array: List<PlayerData>): List<Player> {
    return array.map { when(it.role) {
        "PLAYER" -> Player(it.id, Coordinate(it.x, it.y), when(it.dir) {
            "UP" -> Direction.UP
            "DOWN" -> Direction.DOWN
            "LEFT" -> Direction.LEFT
            "RIGHT" -> Direction.RIGHT
            else -> throw Exception("Cannot parse data to player")
        }, it.stamina.toIntOrNull())
        "SPECIALIST" -> Specialist(it.id, Coordinate(it.x, it.y), when(it.dir) {
            "UP" -> Direction.UP
            "DOWN" -> Direction.DOWN
            "LEFT" -> Direction.LEFT
            "RIGHT" -> Direction.RIGHT
            else -> throw Exception("Cannot parse data to specialist")
        }, it.stamina.toIntOrNull())
        else -> throw Exception("Cannot parse data to player list")
    } }
}

fun convertJsonToMiscLayout(array: List<List<String>>, using: String): SecondLayout {
    return when (using) {
        "colorful" -> array.map { it.map { ColorfulTile(convertDataToColor(it)) as Tile }.toMutableList() }.toMutableList()
        "mountainous" -> array.map { it.map { MountainTile(it.toIntOrNull()) as Tile }.toMutableList() }.toMutableList()
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

class AmatsukazeInterface(
    val type: String,
    val code: String,
    val grid: Grid,
    val layout: Layout,
    val miscLayout: SecondLayout,
    val portals: List<Portal>,
    val locks: List<Lock>,
    val players: List<Player>) {
    fun start() {
        val input: CharStream = CharStreams.fromString(code)
        val lexer = amatsukazeGrammarLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = amatsukazeGrammarParser(tokens)
        val tree: ParseTree = parser.top_level()
        val playground = Playground(grid, layout, miscLayout, portals, locks, players.toMutableList(), calculateInitialGem(layout))
        val manager = when (type) {
            "colorful" -> ColorfulManager(playground)
            "mountainous" -> MountainousManager(playground)
            else -> throw Exception("Unsupported game module")
        }
        manager.appendEntry() // Store the initial state of playground into payload list
        val exec = AmatsukazeVisitor(manager)
        exec.visit(tree)
    }
}