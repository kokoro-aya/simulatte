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

import org.ironica.simulatte.payloads.Payload
import org.ironica.simulatte.payloads.Status
import org.ironica.simulatte.payloads.payloadStorage
import org.ironica.simulatte.payloads.statusStorage
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.datas.*
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.simulas.Cocoa
import org.ironica.simulatte.simulas.EvalRunner
import org.ironica.simulatte.simulas.wrapCode
import utils.prettyPrint
import utils.zip

class SimulatteBridge(
    private val type: String,
    private val code: String,
    private val grid: List<List<GridData>>,
    private val gemdatas: List<Coordinate>,
    private val beeperdatas: List<Coordinate>,
    private val switchdatas: List<SwitchData>,
    private val portaldatas: List<PortalData>,
    private val lockdatas: List<LockData>,
    private val stairdatas: List<StairData>,
    private val platformdatas: List<PlatformData>,
    playerdatas: List<PlayerData>,
    val debug: Boolean, val stdout: Boolean, val output: Boolean
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
            } }.toList().sortedBy { it.first.id }.toMap()

        val platforms = platformdatas.associate { Platform(it.level) to it.coo }

        var homeId = 0
        val blocks = grid.mapIndexed { i, line ->
            line.mapIndexed { j, b ->
                when (b.block) {
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

        val levels = grid.map { it.map { it.level } }
        val biomes = grid.map { it.map { it.biome } }

        squares = zip(blocks, levels, biomes) { block, lev, biom ->
            Square(block, lev, biom, null, null, null, null, null)
        }

        // Assignment for items
        gemdatas.forEach {
            validPositionChecks(it, "#gems")
            squares[it.y][it.x].gem = Gem()
        }
        beeperdatas.forEach {
            validPositionChecks(it, "#beepers")
            squares[it.y][it.x].beeper = Beeper()
        }
        switchdatas.forEach {
            validPositionChecks(it.coo, "#switches")
            squares[it.coo.y][it.coo.x].switch = Switch(it.on)
        }

        portals.forEach {
            validPositionChecks(it.value, "#portals")
            squares[it.value.y][it.value.x].portal = it.key
        }
        platforms.forEach {
            validPositionChecks(it.value, "#platforms")
            squares[it.value.y][it.value.x].platform = it.key
        }

        // Player assignment into playground will be delayed within the instance of playground, due to the usage
        // of KotlinPoet.

    }

    private fun validPositionChecks(pos: Coordinate, phase: String): Unit {
        check (pos.y in grid.indices && pos.x in grid[0].indices) { "Initialization:: boundary check failed in $phase" }
    }

    private fun preInitChecks() {
        check (grid.size in 1 .. 50 && grid[0].size in 1 .. 50) {
            "Initialization:: Size of playground must between 1 and 50 (inclusive)!"
        }



        check (lockdatas.all { it.coo.let { grid[it.y][it.x].block == Blocks.LOCK } }) {
            "Initialization:: A Lock registration corresponding to a tile which is not a Lock"
        }

        check (lockdatas.all { it.controlled.all { pos -> platformdatas.any { it.coo == pos } } }) {
            "Initialization:: A coordinate controlled by a Lock has no Platform"
        }
        // In contrast, a platform could be controlled by no lock

        check (stairdatas.all { it.coo.let { grid[it.y][it.x].block == Blocks.STAIR } }) {
            "Initialization:: A Stair registration corresponding to a tile which is not a Stair"
        }

        grid.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                if (grid[i][j].block == Blocks.LOCK) {
                    check (lockdatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A tile Lock has no corresponding registration in data"
                    }
                }
                if (grid[i][j].block == Blocks.STAIR) {
                    check (stairdatas.any { it.coo.x == j && it.coo.y == i }) {
                        "Initialization:: A tile Stair has no corresponding registration in data"
                    }
                }
            }
        }
    }

    fun start(): Pair<Any?, Status> {
        val codeGen = StringBuilder()
        val cocoa = Cocoa()
        cocoa.feed(squares)
            .feed(portals)
            .feed(locks)
            .feed(players)
            .thenFeed(type, debug, stdout)
            .generate(codeGen)
        codeGen.append("\n")
        codeGen.append(code.wrapCode())

        val gen = codeGen.toString()

        println("Processing request, please wait...")

        if (output) gen.prettyPrint()

        EvalRunner().evalSnippet(gen).let {
//            println(it.first)
            println()
            println(when (it.second) {
                Status.OK -> "[1]Code executed successfully"
                Status.ERROR -> "[2]Some error occurred while executing your code"
                Status.INCOMPLETE -> "[3]The code is not complete"
            })

            return it
        }
    }
}