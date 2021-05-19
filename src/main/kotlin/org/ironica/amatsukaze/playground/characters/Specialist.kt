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

import org.ironica.amatsukaze.playground.data.Coordinate
import org.ironica.amatsukaze.bridge.LockData
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Direction

class Specialist(id: Int, coo: Coordinate, dir: Direction, stamina: Int?): Player(id, coo, dir, stamina) {

    lateinit var locks: List<LockData>

    val isBeforeLock = {
        when (this.dir) {
            Direction.UP -> coo.y >= 1 && grid[coo.y - 1][coo.x] == Blocks.LOCK
            Direction.DOWN -> coo.y <= grid.size - 2 && grid[coo.y + 1][coo.x] == Blocks.LOCK
            Direction.LEFT -> coo.x >= 1 && grid[coo.y][coo.x - 1] == Blocks.LOCK
            Direction.RIGHT -> coo.x <= grid[0].size - 2 && grid[coo.y][coo.x + 1] == Blocks.LOCK
        }
    }

    val lockCoo = {
        assert(isBeforeLock())
        when (this.dir) {
            Direction.UP -> Coordinate(coo.x, coo.y - 1)
            Direction.DOWN -> Coordinate(coo.x, coo.y + 1)
            Direction.LEFT -> Coordinate(coo.x - 1, coo.y)
            Direction.RIGHT -> Coordinate(coo.x + 1, coo.y)
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