/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.bridge

import org.ironica.simulatte.payloads.Status
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.data.*
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.simulas.Cocoa
import org.ironica.simulatte.simulas.EvalRunner
import org.ironica.simulatte.simulas.wrapCode
import utils.zip

class SimulatteBridge(
    private val type: String,
    private val code: String,
    private val grid: Grid,
    private val itemLayout: ItemLayout,
    private val colors: List<List<Color>>,
    private val levels: List<List<Int>>,
    private val biomes: List<List<Biome>>,
    private val portaldatas: List<PortalData>,
    private val lockdatas: List<LockData>,
    private val stairdatas: List<StairData>,
    private val platformdatas: List<PlatformData>,
    playerdatas: List<PlayerData>,
    val debug: Boolean, val stdout: Boolean,
) {

    var squares: List<List<Square>>
    var locks: Map<Coordinate, Lock>
    var portals: Map<Portal, Coordinate>
    var players: Map<AbstractCharacter, Coordinate>

    // Pre-initialization of data structures to be passed to playground
    // = Pre-init Checks
    //   - Dimension check -
    //   - Check every lock block corresponds to a lock* registration and vice versa -
    //   - Check every platform item corresponds to a platform registration and vice versa -
    //   - Check every controlled tiles in lock has a platform (platformdata will be consumed) -
    //   - Check every portal item corresponds to a portal registration and vice versa -
    //   - Check every stairs tiles corresponds to a stair* registration and vice versa -
    // = Pre-initialization phase
    // - prepare portals, locks and players data structures for playground -
    // - Convert grid to 2d block array (lockdata and stairdata will be consumed) -
    // - Integrate arrays of block layout and misc layout to square arrays -
    // - Assign items to square arrays (portaldata will be consumed) -
    // * Note: Players will NOT be assigned to playground in this period, the assignment will be delayed to the init
    //         block within Playground instance.

    // *: this will be delayed into the loop of squares creation
    init {
        preInitChecks()

        var lockIds = 0 // To distinguish different locks with the same controlled platforms (and eventually same colors)

        portals = portaldatas.associate { Portal(it.coo, it.dest, isActive = true, color = Color.WHITE) to it.coo }
        // TODO add colors on portals
        locks = lockdatas.associate { it.coo to Lock(lockIds++, it.controlled.toMutableList(), isActive = true, energy = 15) }

        players = playerdatas.associate {
            (if (it.role == Role.SPECIALIST) InstantializedSpecialist(it.id, it.dir, it.stamina)
                else InstantializedPlayer(it.id, it.dir, it.stamina)).let {
                p -> p to Coordinate(it.x, it.y)
            } }

        val platforms = platformdatas.associate { Platform(it.level) to it.coo }

        var homeId = 0
        val blocks = grid.mapIndexed { i, line ->
            line.mapIndexed { j, b ->
                when (b) {
                    Blocks.OPEN -> Open
                    Blocks.HILL -> Hill
                    Blocks.WATER -> Water
                    Blocks.TREE -> Tree
                    Blocks.DESERT -> Desert
                    Blocks.HOME -> Home(homeId++)
                    Blocks.MOUNTAIN -> Mountain
                    Blocks.STONE -> Stone
                    Blocks.LOCK -> locks[Coordinate(j, i)]
                        ?: throw Exception("Initialization:: A tile declared as Lock without lock info registered")
                    Blocks.STAIR -> stairdatas.firstOrNull { it.coo == Coordinate(j, i) }?.let { Stair(it.dir) }
                        ?: throw Exception("Initialization:: A tile declared as Stair without Stair info registered")
                    Blocks.VOID -> Void
                }
            }
        }

        val miscLayout = convertJsonToMiscLayout(colors, levels, type, grid.size to grid[0].size)
        squares = zip(blocks, miscLayout, biomes) { block, addi, biom ->
            Square(block, addi.color, addi.level, biom, null, null, null, null, null)
        }

        // This loop is a temporary solution for assigning items over squares
        itemLayout.forEachIndexed { i, line ->
            line.forEachIndexed { j, it ->
                when (it) {
                    Items.NONE -> {
                    }
                    Items.GEM -> squares[i][j].gem = Gem()
                    Items.CLOSEDSWITCH -> squares[i][j].switch = Switch(on = false)
                    Items.OPENEDSWITCH -> squares[i][j].switch = Switch(on = true)
                    Items.BEEPER -> squares[i][j].beeper = Beeper()
                    Items.PORTAL -> squares[i][j].portal = portals.entries.firstOrNull {
                        it.value == Coordinate(j, i)
                    }?.key
                    Items.PLATFORM -> squares[i][j].platform = platforms.entries.firstOrNull {
                        it.value == Coordinate(j, i)
                    }?.key
                }
            }
        }

        // Player assignment into playground will be delayed within the instance of playground, due to the usage
        // of KotlinPoet.

    }

    private fun preInitChecks() {
        check (grid.size == itemLayout.size && grid[0].size == itemLayout[0].size)
        { "Initialization:: layouts must have same dimension [0]" }
        check (grid.size == colors.size && grid[0].size == colors[0].size)
        { "Initialization:: layouts must have same dimension [1]" }
        check (grid.size == levels.size && grid[0].size == levels[0].size)
        { "Initialization:: layouts must have same dimension [2]" }
        check (grid.size == biomes.size && grid[0].size == biomes[0].size)
        { "Initialization:: layouts must have same dimension [3]" }

        check (lockdatas.all { it.coo.let { grid[it.y][it.x] == Blocks.LOCK } }) {
            "Initialization:: A Lock registration corresponding to a tile which is not a Lock"
        }

        check (platformdatas.all { it.coo.let { itemLayout[it.y][it.x] == Items.PLATFORM } }) {
            "Initialization:: A Platform registration corresponding to a coordinate which has no Platform"
        }

        check (lockdatas.all { it.controlled.all { itemLayout[it.y][it.x] == Items.PLATFORM } }) {
            "Initialization:: A coordinate controlled by a Lock has no Platform"
        }

        check (portaldatas.all { it.coo.let { itemLayout[it.y][it.x] == Items.PORTAL } }) {
            "Initialization:: A Portal registration corresponding to a coordinate which has no portal"
        }

        check (stairdatas.all { it.coo.let { grid[it.y][it.x] == Blocks.STAIR } }) {
            "Initialization:: A Stair registration corresponding to a tile which is not a Stair"
        }

        grid.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                if (grid[i][j] == Blocks.LOCK) {
                    check (lockdatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A tile Lock has no corresponding registration in data"
                    }
                }
                if (grid[i][j] == Blocks.STAIR) {
                    check (stairdatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A tile Stair has no corresponding registration in data"
                    }
                }
                if (itemLayout[i][j] == Items.PORTAL) {
                    check (portaldatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A coordinate contains Portal but not registered in data"
                    }
                }

                if (itemLayout[i][j] == Items.PLATFORM) {
                    check (platformdatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A coordinate contains Platform but not registered in data"
                    }
                }
            }
        }
        // In the future we might remove the itemLayout and introduce new arrays for each items
    }

    fun start(): Status {
        val codeGen = StringBuilder()
        val cocoa = Cocoa()
        cocoa.feed(squares)
            .feed(portals)
            .feed(locks)
            .feed(players)
            .feed(type, debug, stdout)
            .generate(codeGen)
        codeGen.append("\n")
        codeGen.append(code.wrapCode())

        val gen = codeGen.toString()

        println(gen)

        EvalRunner.evalSnippet(gen).let {
            println(it.first)
            return it.second
        }
    }
}