/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.playground

import kotlinx.serialization.Serializable
import org.ironica.amatsukaze.playground.Direction.*
import org.ironica.amatsukaze.playground.Block.*
import org.ironica.amatsukaze.playground.Item.*

data class Tile(var color: Color = Color.WHITE, var level: Int = 1) {
    init {
        if (level < 0) {
            level = 0
        }
    }
}

typealias Grid = MutableList<MutableList<Block>>
typealias ItemLayout = MutableList<MutableList<Item>>
typealias TileLayout = MutableList<MutableList<Tile>>

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
data class Lock(val coo: Coordinate, val controlled: List<Coordinate>)

@Serializable
data class Stair(val coo: Coordinate, val dir: Direction)

open class Player(val id: Int, val coo: Coordinate, var dir: Direction, var stamina: Int?) {

    lateinit var grid: Grid
    lateinit var itemLayout: ItemLayout
    lateinit var misc: TileLayout
    lateinit var portals: List<Portal>
    lateinit var stairs: List<Stair>
    lateinit var playground: Playground

    var collectedGem = 0
    var beeperInBag = 0

    private fun sameLevelAccessible(dest: Coordinate): Boolean {
        return grid[dest.y][dest.x] == OPEN || grid[dest.y][dest.x] == TREE || grid[dest.y][dest.x] == HOME || grid[dest.y][dest.x] == STAIR
    }

    private fun sameLevel(coo: Coordinate, dest: Coordinate): Boolean {
        return misc[coo.y][coo.x] == misc[dest.y][dest.x]
    }

    private fun isAccessibleYPlus() =
        coo.y >= 1 && (sameLevelAccessible(Coordinate(coo.x, coo.y - 1)) && sameLevel(coo, Coordinate(coo.x, coo.y - 1))
                || grid[coo.y][coo.x] == STAIR && (misc[coo.y - 1][coo.x].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == UP})
                || grid[coo.y - 1][coo.x] == STAIR && (misc[coo.y - 1][coo.x].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x, coo.y - 1) && it.dir == DOWN })
                )

    private fun isAccessibleYMinus() =
        coo.y <= grid.size - 2 && (sameLevelAccessible(Coordinate(coo.x, coo.y + 1)) && sameLevel(coo, Coordinate(coo.x, coo.y + 1))
                || grid[coo.y][coo.x] == STAIR && (misc[coo.y + 1][coo.x].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == DOWN })
                || grid[coo.y + 1][coo.x] == STAIR && (misc[coo.y + 1][coo.x].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x, coo.y + 1) && it.dir == UP })
                )

    private fun isAccessibleXMinus() =
        coo.x >= 1 && (sameLevelAccessible(Coordinate(coo.x - 1, coo.y)) && sameLevel(coo, Coordinate(coo.x - 1, coo.y))
                || grid[coo.y][coo.x] == STAIR && (misc[coo.y][coo.x - 1].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == LEFT })
                || grid[coo.y][coo.x - 1] == STAIR && (misc[coo.y][coo.x - 1].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x - 1, coo.y) && it.dir == RIGHT })
                )

    private fun isAccessibleXPlus() =
        coo.x <= grid[0].size - 2 && (sameLevelAccessible(Coordinate(coo.x + 1, coo.y)) && sameLevel(coo, Coordinate(coo.x + 1, coo.y))
                || grid[coo.y][coo.x] == STAIR && (misc[coo.y][coo.x + 1].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == RIGHT })
                || grid[coo.y][coo.x + 1] == STAIR && (misc[coo.y][coo.x + 1].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x + 1, coo.y) && it.dir == LEFT })
                )

    val isOnGem = { itemLayout[coo.y][coo.x] == GEM }
    val isOnOpenedSwitch = { itemLayout[coo.y][coo.x] == OPENEDSWITCH }
    val isOnClosedSwitch = { itemLayout[coo.y][coo.x] == CLOSEDSWITCH }
    val isOnBeeper = { itemLayout[coo.y][coo.x] == BEEPER }

    val isAtHome = { grid[coo.y][coo.x] == HOME }
    val isInDesert = { grid[coo.y][coo.x] == DESERT}
    val isInForest = { grid[coo.y][coo.x] == TREE }

    val isOnPortal = { itemLayout[coo.y][coo.x] == PORTAL }

    val isBlocked = { when (dir) {
        UP -> !isAccessibleYPlus()
        DOWN -> !isAccessibleYMinus()
        LEFT -> !isAccessibleXMinus()
        RIGHT -> !isAccessibleXPlus()
    }}
    val isBlockedLeft = { when (dir) {
        RIGHT -> !isAccessibleYPlus()
        LEFT -> !isAccessibleYMinus()
        UP -> !isAccessibleXMinus()
        DOWN -> !isAccessibleXPlus()
    }}
    val isBlockedRight = { when (dir) {
        LEFT -> !isAccessibleYPlus()
        RIGHT -> !isAccessibleYMinus()
        DOWN -> !isAccessibleXMinus()
        UP -> !isAccessibleXPlus()
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
            itemLayout[coo.y][coo.x] = NONE
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
            itemLayout[coo.y][coo.x] = CLOSEDSWITCH
            stamina = stamina?.minus(1)
            return true
        }
        if (isOnClosedSwitch() && (stamina == null || (stamina != null && stamina!! > 0))) {
            itemLayout[coo.y][coo.x] = OPENEDSWITCH
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
            itemLayout[coo.y][coo.x] = NONE
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
        if (beeperInBag > 0 && itemLayout[coo.y][coo.x] == NONE) {
            if (isInDesert()) stamina = stamina?.minus(2)
            if (isInForest()) stamina = stamina?.minus(1)
            itemLayout[coo.y][coo.x] = BEEPER
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
        misc[coo.y][coo.x].color = c
    }
}

class Specialist(id: Int, coo: Coordinate, dir: Direction, stamina: Int?): Player(id, coo, dir, stamina) {

    lateinit var locks: List<Lock>

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
        if (isBeforeLock()) {
            locks.filter { it.coo == lockCoo() }[0].controlled.forEach { c ->
                misc[c.y][c.x].let {
                    if (it.level in 0 .. 15) {
                        if (up) it.level += 1
                        else it.level -= 1
                    }
                }
            }
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

class Playground(val grid: Grid,
                 val itemLayout: ItemLayout,
                 val tileLayout: TileLayout,
                 val portals: List<Portal>,
                 val locks: List<Lock>,
                 val stairs: List<Stair>,
                 val players: MutableList<Player>,
                 private val initialGem: Int) {

    init {

        assert (portals.all { it.coo.let { itemLayout[it.y][it.x] == PORTAL } })
        assert (locks.all { it.coo.let { grid[it.y][it.x] == LOCK } })
        assert (stairs.all { it.coo.let { grid[it.y][it.x] == STAIR } })

        grid.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                if (grid[i][j] == LOCK) {
                    assert (locks.any { it.coo.x == j && it.coo.y == i })
                }
                if (grid[i][j] == STAIR) {
                    assert (stairs.any { it.coo.x == j && it.coo.y == i })
                }
                if (itemLayout[i][j] == PORTAL) {
                    assert (portals.any { it.coo.x == j && it.coo.y == i })
                }
            }
        }

        assert (locks.all { it.controlled.all { itemLayout[it.y][it.x] == PLATFORM } })

        players.map { it.grid = grid }
        players.map { it.itemLayout = itemLayout }
        players.map { it.misc = tileLayout }
        players.map { it.portals = portals }
        players.map { it.stairs = stairs }
        players.filterIsInstance<Specialist>().map { it.locks = locks }
        players.map { it.playground = this }
    }

    fun kill(player: Player) {
        players.remove(player)
    }

    fun win(): Boolean {
        return itemLayout.flatMap { it.filter { it == GEM } }.isEmpty() && itemLayout.flatMap { it.filter { it == CLOSEDSWITCH } }.isEmpty()
    }
    fun gemCount(): Int {
        return initialGem - itemLayout.flatMap { it.filter { it == GEM } }.size
    }
    fun switchCount(): Int {
        return itemLayout.flatMap { it.filter { it == OPENEDSWITCH } }.size
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
            itemLayout[y][x] == NONE -> {
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
                    STAIR -> "梯"
                }
            }
            else -> {
                when (itemLayout[y][x]) {
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

