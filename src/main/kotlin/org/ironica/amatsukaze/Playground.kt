package org.ironica.amatsukaze

import com.sun.java.accessibility.util.EventID.CONTAINER
import org.ironica.amatsukaze.Direction.*
import org.ironica.amatsukaze.Block.*
import org.ironica.amatsukaze.Item.*
enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class Block {
    OPEN, BLOCKED, WATER, TREE, DESERT, HOME,
}

enum class Item {
    NONE, GEM, CLOSEDSWITCH, OPENEDSWITCH, BEEPER, LOCK,
}

data class Tile(var color: String, var level: Int, val liftable: Boolean)

typealias Grid = Array<Array<Block>>
typealias Layout = Array<Array<Item>>
typealias SecondLayout = Array<Array<Tile>>

data class Coordinate(var x: Int, var y: Int) {
    fun incrementX() { x += 1 }
    fun decrementX() { x -= 1 }
    fun incrementY() { y += 1 }
    fun decrementY() { y -= 1 }
}

open class Player(val coo: Coordinate, var dir: Direction) {

    lateinit var grid: Grid
    lateinit var layout: Layout
    lateinit var layout2s: SecondLayout

    var stamina = 90

    var collectedGem = 0
    var beeperInBag = 0

    private fun isBlockedYPlus() =
        coo.y < 1 || grid[coo.y - 1][coo.x] == BLOCKED || grid[coo.y - 1][coo.x] == WATER
                || layout2s[coo.y - 1][coo.x].level != layout2s[coo.y][coo.x].level
    private fun isBlockedYMinus() =
        coo.y > grid.size - 2 || grid[coo.y + 1][coo.x] == BLOCKED || grid[coo.y + 1][coo.x] == WATER
                || layout2s[coo.y + 1][coo.x].level != layout2s[coo.y][coo.x].level
    private fun isBlockedXMinus() =
        coo.x < 1 || grid[coo.y][coo.x - 1] == BLOCKED || grid[coo.y][coo.x - 1] == WATER
                || layout2s[coo.y][coo.x - 1].level != layout2s[coo.y][coo.x - 1].level
    private fun isBlockedXPlus() =
        coo.x > grid[0].size - 2 || grid[coo.y][coo.x + 1] == BLOCKED || grid[coo.y][coo.x + 1] == WATER
                || layout2s[coo.y][coo.x + 1].level != layout2s[coo.y][coo.x + 1].level

    val isOnGem = { layout[coo.y][coo.x] == GEM }
    val isOnOpenedSwitch = { layout[coo.y][coo.x] == OPENEDSWITCH }
    val isOnClosedSwitch = { layout[coo.y][coo.x] == CLOSEDSWITCH }
    val isOnBeeper = { layout[coo.y][coo.x] == BEEPER }

    val isAtHome = { grid[coo.y][coo.x] == HOME }
    val isInDesert = { grid[coo.y][coo.x] == DESERT}
    val isInForest = { grid[coo.y][coo.x] == TREE }

    val isBlocked = { when (dir) {
        UP -> isBlockedYPlus()
        DOWN -> isBlockedYMinus()
        LEFT -> isBlockedXMinus()
        RIGHT -> isBlockedXPlus()
    }}
    val isBlockedLeft = { when (dir) {
        RIGHT ->isBlockedYPlus()
        LEFT -> isBlockedYMinus()
        UP -> isBlockedXMinus()
        DOWN -> isBlockedXPlus()
    }}
    val isBlockedRight = { when (dir) {
        LEFT -> isBlockedYPlus()
        RIGHT -> isBlockedYMinus()
        DOWN -> isBlockedXMinus()
        UP -> isBlockedXPlus()
    }}

    fun turnLeft() { dir = when(dir) {
        UP -> LEFT
        LEFT -> DOWN
        DOWN -> RIGHT
        RIGHT -> UP
    }}
    fun moveForward(): Boolean {
        if (isInDesert()) stamina -= 2
        if (isInForest()) stamina -= 1
        if (!isBlocked() && stamina > 0) {
            when (dir) {
                UP -> coo.decrementY()
                LEFT -> coo.decrementX()
                DOWN -> coo.incrementY()
                RIGHT -> coo.incrementX()
            }
            stamina -= 1
            return true
        }
        return false
    }
    fun collectGem(): Boolean {
        if (isInDesert()) stamina -= 2
        if (isInForest()) stamina -= 1
        if (isOnGem() && stamina > 0) {
            collectedGem += 1
            layout[coo.y][coo.x] = NONE
            stamina -= 1
            return true
        }
        return false
    }
    fun toggleSwitch(): Boolean {
        if (isInDesert()) stamina -= 2
        if (isInForest()) stamina -= 1
        if (isOnOpenedSwitch() && stamina > 0) {
            layout[coo.y][coo.x] = CLOSEDSWITCH
            stamina -= 1
            return true
        }
        if (isOnClosedSwitch() && stamina > 0) {
            layout[coo.y][coo.x] = OPENEDSWITCH
            stamina -= 1
            return true
        }
        return false
    }

    fun atHome(): Boolean {
        if (isAtHome()) {
            stamina += 25
            return true
        }
        return false
    }

    fun takeBeeper(): Boolean {
        if (isInDesert()) stamina -= 2
        if (isInForest()) stamina -= 1
        if (isOnBeeper() && stamina > 0) {
            layout[coo.y][coo.x] = NONE
            beeperInBag += 1
            stamina -= 1
            return true
        }
        return false
    }

    fun dropBeeper(): Boolean {
        if (beeperInBag > 0) {
            if (isInDesert()) stamina -= 2
            if (isInForest()) stamina -= 1
            layout[coo.y][coo.x] = BEEPER
            beeperInBag -= 1
            stamina -= 1
            return true
        }
        return false
    }
}

class Specialist(coo: Coordinate, dir: Direction): Player(coo, dir) {
    val isBeforeLock = {
        when (dir) {
            UP -> coo.y >= 1 && layout[coo.y - 1][coo.x] == LOCK
            DOWN -> coo.y <= grid.size - 2 && layout[coo.y + 1][coo.x] == LOCK
            LEFT -> coo.x >= 1 && layout[coo.y][coo.x - 1] == LOCK
            RIGHT -> coo.x <= grid[0].size - 2 && layout[coo.y][coo.x + 1] == LOCK
        }
    }

    fun turnLockUp(): Boolean {

    }

    fun turnLockDown(): Boolean {

    }
}

class Playground(val grid: Grid, val layout: Layout, val layout2s: SecondLayout, val players: Array<Player>, private val initialGem: Int) {

    init {
        players.map { it.grid = grid }
        players.map { it.layout = layout }
        players.map { it.layout2s = layout2s }
    }

    fun win(): Boolean {
        return layout.flatMap { it.filter { it == GEM } }.isEmpty() && layout.flatMap { it.filter { it == CLOSEDSWITCH } }.isEmpty()
    }
    fun gemCount(): Int {
        return initialGem - layout.flatMap { it.filter { it == GEM } }.size
    }
    fun switchCount(): Int {
        return layout.flatMap { it.filter { it == OPENEDSWITCH } }.size
    }

    private fun printTile(x: Int, y: Int): String {
        var ret = ""
        players.forEach { if (it.coo.x == x && it.coo.y == y) {
            ret += when (it.dir) {
                UP -> "U"
                DOWN -> "D"
                LEFT -> "L"
                RIGHT -> "R"
            }
        } }
        return when {
            ret != "" -> ret
            layout[y][x] == NONE -> {
                when (grid[y][x]) {
                    OPEN -> "_"
                    BLOCKED -> "B"
                    WATER -> "W"
                    TREE -> "T"
                    DESERT -> "S"
                    HOME -> "H"
                }
            }
            else -> {
                when (layout[y][x]) {
                    GEM -> "G"
                    CLOSEDSWITCH -> "C"
                    OPENEDSWITCH -> "O"
                    BEEPER -> "V"
                    LOCK -> "A"
                    NONE -> throw Exception("This is impossible")
                }
            }
        }
    }

    fun printGrid() {
        grid.forEachIndexed { i, row -> row.forEachIndexed { j, _ ->
                printTile(j, i)
            }
            println()
        }
        println()
    }
}

fun main() {
    val grid = arrayOf(
            arrayOf(OPEN, OPEN, BLOCKED, BLOCKED, BLOCKED),
            arrayOf(OPEN, OPEN, OPEN, TREE, BLOCKED),
            arrayOf(BLOCKED, OPEN, BLOCKED, DESERT, BLOCKED)
    )
    val layout = arrayOf(
        arrayOf(NONE, CLOSEDSWITCH, NONE, NONE, NONE),
        arrayOf(CLOSEDSWITCH, NONE, NONE, NONE, NONE),
        arrayOf(NONE, GEM, NONE, GEM, BEEPER),
    )
    val player = Player(
        Coordinate(0, 0),
        RIGHT
    )

    val playground = Playground(grid, layout, arrayOf(player), 2)
    playground.printGrid()

    player.moveForward()
    if (player.moveForward()) println("moved forward") else println("cannot move forward!")
    if (player.toggleSwitch()) println("toggled switch")
    player.turnLeft(); player.turnLeft(); player.turnLeft()
    playground.printGrid()
    player.moveForward(); player.moveForward()
    if (player.collectGem()) println("Collected gem")
    player.turnLeft(); player.turnLeft()
    playground.printGrid()
    player.moveForward(); player.turnLeft(); player.moveForward()
    if (player.toggleSwitch()) println("toggled switch")
    player.turnLeft(); player.turnLeft()
    playground.printGrid()
    player.moveForward()
    playground.printGrid()

    player.moveForward()
    playground.printGrid()
    player.moveForward()
    player.turnLeft(); player.turnLeft(); player.turnLeft()
    player.moveForward()

    if (player.collectGem()) println("Collected gem")
    player.turnLeft(); player.turnLeft(); player.moveForward()

    playground.printGrid()

    println(playground.win())
    println(playground.gemCount())
    println(playground.switchCount())

}

