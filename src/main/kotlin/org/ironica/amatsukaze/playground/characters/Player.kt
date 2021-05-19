/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.playground.characters

import org.ironica.amatsukaze.bridge.PortalData
import org.ironica.amatsukaze.bridge.StairData
import org.ironica.amatsukaze.playground.Color
import org.ironica.amatsukaze.playground.Playground
import org.ironica.amatsukaze.playground.data.*
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Direction
import org.ironica.amatsukaze.playground.Items

open class Player(val id: Int, val coo: Coordinate, var dir: Direction, var stamina: Int?) {

    lateinit var grid: Grid
    lateinit var itemLayout: ItemLayout
    lateinit var misc: TileLayout
    lateinit var portals: List<PortalData>
    lateinit var stairs: List<StairData>
    lateinit var playground: Playground

    var collectedGem = 0
    var beeperInBag = 0

    private fun sameLevelAccessible(dest: Coordinate): Boolean {
        return grid[dest.y][dest.x] == Blocks.OPEN || grid[dest.y][dest.x] == Blocks.TREE || grid[dest.y][dest.x] == Blocks.HOME || grid[dest.y][dest.x] == Blocks.STAIR
    }

    private fun sameLevel(coo: Coordinate, dest: Coordinate): Boolean {
        return misc[coo.y][coo.x] == misc[dest.y][dest.x]
    }

    private fun isAccessibleYPlus() =
        coo.y >= 1 && (sameLevelAccessible(Coordinate(coo.x, coo.y - 1)) && sameLevel(coo, Coordinate(coo.x, coo.y - 1))
                || grid[coo.y][coo.x] == Blocks.STAIR && (misc[coo.y - 1][coo.x].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == Direction.UP })
                || grid[coo.y - 1][coo.x] == Blocks.STAIR && (misc[coo.y - 1][coo.x].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x, coo.y - 1) && it.dir == Direction.DOWN })
                )

    private fun isAccessibleYMinus() =
        coo.y <= grid.size - 2 && (sameLevelAccessible(Coordinate(coo.x, coo.y + 1)) && sameLevel(coo,
            Coordinate(coo.x, coo.y + 1)
        )
                || grid[coo.y][coo.x] == Blocks.STAIR && (misc[coo.y + 1][coo.x].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == Direction.DOWN })
                || grid[coo.y + 1][coo.x] == Blocks.STAIR && (misc[coo.y + 1][coo.x].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x, coo.y + 1) && it.dir == Direction.UP })
                )

    private fun isAccessibleXMinus() =
        coo.x >= 1 && (sameLevelAccessible(Coordinate(coo.x - 1, coo.y)) && sameLevel(coo, Coordinate(coo.x - 1, coo.y))
                || grid[coo.y][coo.x] == Blocks.STAIR && (misc[coo.y][coo.x - 1].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == Direction.LEFT })
                || grid[coo.y][coo.x - 1] == Blocks.STAIR && (misc[coo.y][coo.x - 1].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x - 1, coo.y) && it.dir == Direction.RIGHT })
                )

    private fun isAccessibleXPlus() =
        coo.x <= grid[0].size - 2 && (sameLevelAccessible(Coordinate(coo.x + 1, coo.y)) && sameLevel(coo,
            Coordinate(coo.x + 1, coo.y)
        )
                || grid[coo.y][coo.x] == Blocks.STAIR && (misc[coo.y][coo.x + 1].level + 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == coo && it.dir == Direction.RIGHT })
                || grid[coo.y][coo.x + 1] == Blocks.STAIR && (misc[coo.y][coo.x + 1].level - 1 == misc[coo.y][coo.x].level
                    && stairs.any { it.coo == Coordinate(coo.x + 1, coo.y) && it.dir == Direction.LEFT })
                )

    val isOnGem = { itemLayout[coo.y][coo.x] == Items.GEM }
    val isOnOpenedSwitch = { itemLayout[coo.y][coo.x] == Items.OPENEDSWITCH }
    val isOnClosedSwitch = { itemLayout[coo.y][coo.x] == Items.CLOSEDSWITCH }
    val isOnBeeper = { itemLayout[coo.y][coo.x] == Items.BEEPER }

    val isAtHome = { grid[coo.y][coo.x] == Blocks.HOME }
    val isInDesert = { grid[coo.y][coo.x] == Blocks.DESERT }
    val isInForest = { grid[coo.y][coo.x] == Blocks.TREE }

    val isOnPortal = { itemLayout[coo.y][coo.x] == Items.PORTAL }

    val isBlocked = { when (dir) {
        Direction.UP -> !isAccessibleYPlus()
        Direction.DOWN -> !isAccessibleYMinus()
        Direction.LEFT -> !isAccessibleXMinus()
        Direction.RIGHT -> !isAccessibleXPlus()
    }}
    val isBlockedLeft = { when (dir) {
        Direction.RIGHT -> !isAccessibleYPlus()
        Direction.LEFT -> !isAccessibleYMinus()
        Direction.UP -> !isAccessibleXMinus()
        Direction.DOWN -> !isAccessibleXPlus()
    }}
    val isBlockedRight = { when (dir) {
        Direction.LEFT -> !isAccessibleYPlus()
        Direction.RIGHT -> !isAccessibleYMinus()
        Direction.DOWN -> !isAccessibleXMinus()
        Direction.UP -> !isAccessibleXPlus()
    }}

    fun turnLeft() { dir = when(dir) {
        Direction.UP -> Direction.LEFT
        Direction.LEFT -> Direction.DOWN
        Direction.DOWN -> Direction.RIGHT
        Direction.RIGHT -> Direction.UP
    }}
    fun moveForward(): Boolean {
        if (isInDesert()) stamina = stamina?.minus(2)
        if (isInForest()) stamina = stamina?.minus(1)
        if (!isBlocked() && (stamina == null || (stamina != null && stamina!! > 0))) {
            when (dir) {
                Direction.UP -> coo.decrementY()
                Direction.LEFT -> coo.decrementX()
                Direction.DOWN -> coo.incrementY()
                Direction.RIGHT -> coo.incrementX()
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
            itemLayout[coo.y][coo.x] = Items.NONE
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
            itemLayout[coo.y][coo.x] = Items.CLOSEDSWITCH
            stamina = stamina?.minus(1)
            return true
        }
        if (isOnClosedSwitch() && (stamina == null || (stamina != null && stamina!! > 0))) {
            itemLayout[coo.y][coo.x] = Items.OPENEDSWITCH
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
            itemLayout[coo.y][coo.x] = Items.NONE
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
        if (beeperInBag > 0 && itemLayout[coo.y][coo.x] == Items.NONE) {
            if (isInDesert()) stamina = stamina?.minus(2)
            if (isInForest()) stamina = stamina?.minus(1)
            itemLayout[coo.y][coo.x] = Items.BEEPER
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