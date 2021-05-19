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

import org.ironica.amatsukaze.playground.data.*
import org.ironica.amatsukaze.playground.enums.Direction.*
import org.ironica.amatsukaze.playground.enums.Block.*
import org.ironica.amatsukaze.playground.enums.Item.*
import org.ironica.amatsukaze.playground.characters.Player
import org.ironica.amatsukaze.playground.characters.Specialist

class Playground(val grid: Grid,
                 val itemLayout: ItemLayout,
                 val tileLayout: TileLayout,
                 val portals: List<Portal>,
                 val locks: List<Lock>,
                 val stairs: List<Stair>,
                 val players: MutableList<Player>,
                 private val initialGem: Int) {

    // Initialization phase for playground object
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

