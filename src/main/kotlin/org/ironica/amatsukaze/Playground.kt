package org.ironica.amatsukaze

import kotlinx.serialization.Serializable
import org.ironica.amatsukaze.Direction.*
import org.ironica.amatsukaze.Block.*
import org.ironica.amatsukaze.Item.*

@Serializable
sealed class Tile
@Serializable
data class ColorfulTile(var color: Color): Tile()
@Serializable
data class MountainTile(var level: Int?): Tile() {
    init {
        if (level != null && level!! < 0) {
            level = null
        }
    }
}

typealias Grid = Array<Array<Block>>
typealias Layout = Array<Array<Item>>
typealias SecondLayout = Array<Array<Tile>>

@Serializable
data class Coordinate(var x: Int, var y: Int) {
    fun incrementX() { x += 1 }
    fun decrementX() { x -= 1 }
    fun incrementY() { y += 1 }
    fun decrementY() { y -= 1 }
}

@Serializable
data class Portal(val coo: Coordinate = Coordinate(0, 0), val dest: Coordinate = Coordinate(0, 0), var isActive: Boolean = false)

@Serializable
data class Lock(val coo: Coordinate, val controlled: Array<Coordinate>)

open class Player(val id: Int, val coo: Coordinate, var dir: Direction, var stamina: Int?) {

    lateinit var grid: Grid
    lateinit var layout: Layout
    lateinit var misc: SecondLayout
    lateinit var portals: Array<Portal>
    lateinit var playground: Playground

    var collectedGem = 0
    var beeperInBag = 0

    private fun isBlockedYPlus() =
        coo.y < 1 || grid[coo.y - 1][coo.x] == BLOCKED || grid[coo.y - 1][coo.x] == WATER
                || grid[coo.y - 1][coo.x] == MOUNTAIN || grid[coo.y - 1][coo.x] == STONE
                || grid[coo.y - 1][coo.x] == LOCK
                || if (misc[0][0] is MountainTile) {
                        val forward = misc[coo.y - 1][coo.x] as MountainTile
                        val current = misc[coo.y][coo.x] as MountainTile
                        if (forward.level == null && grid[coo.y - 1][coo.x] == STAIRVERTICAL) false
                        else if (current.level == null && grid[coo.y][coo.x] == STAIRVERTICAL) false
                        else forward.level != null && current.level != null && forward.level != current.level
                    } else false
    private fun isBlockedYMinus() =
        coo.y > grid.size - 2 || grid[coo.y + 1][coo.x] == BLOCKED || grid[coo.y + 1][coo.x] == WATER
                || grid[coo.y + 1][coo.x] == MOUNTAIN || grid[coo.y + 1][coo.x] == STONE
                || grid[coo.y + 1][coo.x] == LOCK
                || if (misc[0][0] is MountainTile) {
                        val forward = misc[coo.y + 1][coo.x] as MountainTile
                        val current = misc[coo.y][coo.x] as MountainTile
                        if (forward.level == null && grid[coo.y + 1][coo.x] == STAIRVERTICAL) false
                        else if (current.level == null && grid[coo.y][coo.x] == STAIRVERTICAL) false
                        else forward.level != null && current.level != null && forward.level != current.level
                    } else false
    private fun isBlockedXMinus() =
        coo.x < 1 || grid[coo.y][coo.x - 1] == BLOCKED || grid[coo.y][coo.x - 1] == WATER
                || grid[coo.y][coo.x - 1] == MOUNTAIN || grid[coo.y][coo.x - 1] == STONE
                || grid[coo.y][coo.x - 1] == LOCK
                || if (misc[0][0] is MountainTile) {
                        val forward = misc[coo.y][coo.x - 1] as MountainTile
                        val current = misc[coo.y][coo.x] as MountainTile
                        if (forward.level == null && grid[coo.y][coo.x - 1] == STAIRHORIZONTAL) false
                        else if (current.level == null && grid[coo.y][coo.x] == STAIRHORIZONTAL) false
                        else forward.level != null && current.level != null && forward.level != current.level
                    } else false
    private fun isBlockedXPlus() =
        coo.x > grid[0].size - 2 || grid[coo.y][coo.x + 1] == BLOCKED || grid[coo.y][coo.x + 1] == WATER
                || grid[coo.y][coo.x + 1] == MOUNTAIN || grid[coo.y][coo.x + 1] == STONE
                || grid[coo.y][coo.x + 1] == LOCK
                || if (misc[0][0] is MountainTile) {
                        val forward = misc[coo.y][coo.x + 1] as MountainTile
                        val current = misc[coo.y][coo.x] as MountainTile
                        if (forward.level == null && grid[coo.y][coo.x + 1] == STAIRHORIZONTAL) false
                        else if (current.level == null && grid[coo.y][coo.x] == STAIRHORIZONTAL) false
                        else forward.level != null && current.level != null && forward.level != current.level
                    } else false

    val isOnGem = { layout[coo.y][coo.x] == GEM }
    val isOnOpenedSwitch = { layout[coo.y][coo.x] == OPENEDSWITCH }
    val isOnClosedSwitch = { layout[coo.y][coo.x] == CLOSEDSWITCH }
    val isOnBeeper = { layout[coo.y][coo.x] == BEEPER }

    val isAtHome = { grid[coo.y][coo.x] == HOME }
    val isInDesert = { grid[coo.y][coo.x] == DESERT}
    val isInForest = { grid[coo.y][coo.x] == TREE }

    val isOnPortal = { layout[coo.y][coo.x] == PORTAL }

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
        if (isInDesert()) stamina = stamina?.minus(2)
        if (isInForest()) stamina = stamina?.minus(1)
        if (!isBlocked() && (stamina == null || (stamina != null && stamina!! > 0))) {
            when (dir) {
                UP -> coo.decrementY()
                LEFT -> coo.decrementX()
                DOWN -> coo.incrementY()
                RIGHT -> coo.incrementX()
            }
            stamina = stamina?.minus(1)
            if (isAtHome()) {
                stamina = stamina?.plus(25)
            }
            return true
        }
        if (stamina != null && stamina!! <= 0) {
            playground.kill(this)
        }
        return false
    }
    fun stepIntoPortal(): Boolean {
        assert(isOnPortal())
        val p = portals.filter { it.coo.x == coo.x && it.coo.y == coo.y}[0]
        if (p.isActive) {
            coo.x = p.dest.x
            coo.y = p.dest.y
            return true
        }
        return false
    }
    fun collectGem(): Boolean {
        if (isInDesert()) stamina = stamina?.minus(2)
        if (isInForest()) stamina = stamina?.minus(1)
        if (isOnGem() && (stamina == null || (stamina != null && stamina!! > 0))) {
            collectedGem += 1
            layout[coo.y][coo.x] = NONE
            stamina = stamina?.minus(1)
            return true
        }
        if (stamina != null && stamina!! <= 0) {
            playground.kill(this)
        }
        return false
    }
    fun toggleSwitch(): Boolean {
        if (isInDesert()) stamina = stamina?.minus(2)
        if (isInForest()) stamina = stamina?.minus(1)
        if (isOnOpenedSwitch() && (stamina == null || (stamina != null && stamina!! > 0))) {
            layout[coo.y][coo.x] = CLOSEDSWITCH
            stamina = stamina?.minus(1)
            return true
        }
        if (isOnClosedSwitch() && (stamina == null || (stamina != null && stamina!! > 0))) {
            layout[coo.y][coo.x] = OPENEDSWITCH
            stamina = stamina?.minus(1)
            return true
        }
        if (stamina != null && stamina!! <= 0) {
            playground.kill(this)
        }
        return false
    }

    fun takeBeeper(): Boolean {
        if (isInDesert()) stamina = stamina?.minus(2)
        if (isInForest()) stamina = stamina?.minus(1)
        if (isOnBeeper() && (stamina == null || (stamina != null && stamina!! > 0))) {
            layout[coo.y][coo.x] = NONE
            beeperInBag += 1
            stamina = stamina?.minus(1)
            return true
        }
        if (stamina != null && stamina!! <= 0) {
            playground.kill(this)
        }
        return false
    }

    fun dropBeeper(): Boolean {
        if (beeperInBag > 0 && layout[coo.y][coo.x] == NONE) {
            if (isInDesert()) stamina = stamina?.minus(2)
            if (isInForest()) stamina = stamina?.minus(1)
            layout[coo.y][coo.x] = BEEPER
            beeperInBag -= 1
            stamina = stamina?.minus(1)
            return true
        }
        if (stamina != null && stamina!! <= 0) {
            playground.kill(this)
        }
        return false
    }

    fun changeColor(c: Color) {
        if (misc[coo.y][coo.x] is ColorfulTile)
            (misc[coo.y][coo.x] as ColorfulTile).color = c
    }
}

class Specialist(id: Int, coo: Coordinate, dir: Direction, stamina: Int?): Player(id, coo, dir, stamina) {

    lateinit var locks: Array<Lock>

    val isBeforeLock = {
        when (this.dir) {
            UP -> coo.y >= 1 && grid[coo.y - 1][coo.x] == LOCK
            DOWN -> coo.y <= grid.size - 2 && grid[coo.y + 1][coo.x] == LOCK
            LEFT -> coo.x >= 1 && grid[coo.y][coo.x - 1] == LOCK
            RIGHT -> coo.x <= grid[0].size - 2 && grid[coo.y][coo.x + 1] == LOCK
        }
    }

    val lockCoo = {
        assert(isBeforeLock())
        when (this.dir) {
            UP -> Coordinate(coo.x, coo.y - 1)
            DOWN -> Coordinate(coo.x, coo.y + 1)
            LEFT -> Coordinate(coo.x - 1, coo.y)
            RIGHT -> Coordinate(coo.x + 1, coo.y)
        }
    }

    private fun turnLock(up: Boolean): Boolean {
        if (isBeforeLock() && misc[0][0] is MountainTile) {
            locks.filter { it.coo == lockCoo() }[0].controlled.forEach { c ->
                val grid = misc[c.y][c.x] as MountainTile; if (grid.level in 1..15) {
                if (up) grid.level = grid.level?.plus(1)
                else grid.level = grid.level?.minus(1)
            } }
            return true
        }
        return false
    }

    fun turnLockUp(): Boolean {
        if (turnLock(up = true)) {
            if (stamina != null && stamina!! < 0) playground.kill(this)
            return true
        }
        return false
    }

    fun turnLockDown(): Boolean {
        if (turnLock(up = false)) {
            if (stamina != null && stamina!! < 0) playground.kill(this)
            return true
        }
        return false
    }
}

class Playground(val grid: Grid, val layout: Layout, val layout2s: SecondLayout, val portals: Array<Portal>, val locks: Array<Lock>, val players: MutableList<Player>, private val initialGem: Int) {

    init {
        players.map { it.grid = grid }
        players.map { it.layout = layout }
        players.map { it.misc = layout2s }
        players.map { it.portals = portals }
        players.filterIsInstance<Specialist>().map { it.locks = locks }
        players.map { it.playground = this }
    }

    fun kill(player: Player) {
        players.remove(player)
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

    private fun prePrintTile(x: Int, y: Int): String {
        var ret = ""
        players.forEach { if (it.coo.x == x && it.coo.y == y) {
            ret += when (it.dir) {
                UP -> "上"
                DOWN -> "下"
                LEFT -> "左"
                RIGHT -> "右"
            }
        } }
        return when {
            ret != "" -> ret
            layout[y][x] == NONE -> {
                when (grid[y][x]) {
                    OPEN -> "空"
                    BLOCKED -> "障"
                    WATER -> "水"
                    TREE -> "林"
                    DESERT -> "漠"
                    HOME -> "屋"
                    MOUNTAIN -> "山"
                    STONE -> "石"
                    LOCK -> "锁"
                    STAIRVERTICAL -> "竖"
                    STAIRHORIZONTAL -> "横"
                }
            }
            else -> {
                when (layout[y][x]) {
                    GEM -> "钻"
                    CLOSEDSWITCH -> "关"
                    OPENEDSWITCH -> "开"
                    BEEPER -> "器"
                    PORTAL -> "门"
                    PLATFORM -> "台"
                    NONE -> throw Exception("This is impossible")
                }
            }
        }
    }

    fun printGrid() {
        grid.forEachIndexed { i, row -> row.forEachIndexed { j, _ ->
                print(prePrintTile(j, i))
            }
            println()
        }
        println()
    }
}

