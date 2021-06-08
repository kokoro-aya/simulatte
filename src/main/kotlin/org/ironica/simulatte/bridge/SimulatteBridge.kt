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

import org.ironica.simulatte.bridge.rules.GamingCondition
import org.ironica.simulatte.payloads.Status
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.datas.*
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.simulas.Cocoa
import org.ironica.simulatte.simulas.EvalRunner
import org.ironica.simulatte.simulas.wrapCode
import org.ironica.utils.prettyPrint
import org.ironica.utils.zip

class SimulatteBridge(
    private val type: String,
    private val code: String,
    private val grid: List<List<GridData>>,
    gemdatas: List<Coordinate>,
    beeperdatas: List<Coordinate>,
    switchdatas: List<SwitchData>,
    portaldatas: List<PortalData>,
    monsterdatas: List<Coordinate>,
    private val lockdatas: List<LockData>,
    private val stairdatas: List<StairData>,
    private val platformdatas: List<PlatformData>,
    playerdatas: List<PlayerData>,

    private val gamingCondition: GamingCondition?,
    private val userCollision: Boolean,

    val debug: Boolean, val stdout: Boolean, val output: Boolean
) {

    var squares: List<List<Square>>
    var locks: Map<Coordinate, LockBlock>
    var portals: Map<PortalItem, Coordinate>
    var players: Map<AbstractCharacter, Coordinate>

    // Pre-initialization of data structures to be passed to playground
    // = Pre-init Checks
    //   - Dimension check -
    //   - Check every lock block corresponds to a lock* registration and vice versa -
    //   - Check every platform item corresponds to a platform registration and vice versa -
    //   - Check every platform are higher than the level of coordinate -
    //   - Check every controlled tiles in lock has a platform (platformdata will be consumed) -
    //   - Check every portal item corresponds to a portal registration and vice versa -
    //   - Check every stairs tiles corresponds to a stair* registration and vice versa -

    // = Pre-init Rules check
    //   - If the two lists in gamingCondition isn't empty, then check all coordinates are in grid -

    // = Pre-initialization phase
    // - prepare portals, locks and players data structures for playground -
    // - check if players data conforms to collision condition -
    // - Convert grid to 2d block array (lockdata and stairdata will be consumed) -
    // - Integrate arrays of block layout and misc layout to square arrays -
    // - Assign items to square arrays (portaldata will be consumed) -
    // * Note: Players will NOT be assigned to playground in this period, the assignment will be delayed to the init
    //         block within Playground instance.

    // *: this will be delayed into the loop of squares creation
    init {
        preInitChecks()

        preInitRuleCheck()

        var lockIds = 0 // To distinguish different locks with the same controlled platforms (and eventually same colors)
        var portalIds = 0 // To distinguish different portals

        portals = portaldatas.associate { PortalItem(portalIds++, it.coo, it.dest, isActive = true, color = Color.WHITE) to it.coo }
        // TODO add colors on portals
        locks = lockdatas.associate { it.coo to LockBlock(lockIds++, it.controlled.toMutableList(), isActive = true, energy = 15) }

        players = playerdatas.associate {
            val coo = Coordinate(it.x, it.y)
            validPositionChecks(coo, "#players")
            (if (it.role == Role.SPECIALIST) InstantializedSpecialist(it.id, it.dir, it.stamina)
                else InstantializedPlayer(it.id, it.dir, it.stamina)).let {
                p -> p to coo
            } }.toList().sortedBy { it.first.id }.toMap()


        check(userCollision && players.values.notContainsDuplications() || !userCollision) {
            "Initialization:: Collision is set to true while players data contains several players in a coordinate"
        }

        val platforms = platformdatas.associate { PlatformItem(it.level) to it.coo }

        val blocks = grid.mapIndexed { i, line ->
            line.mapIndexed { j, b ->
                when (b.block) {
                    Blocks.OPEN -> OpenBlock
                    Blocks.BLOCKED -> BlockedBlock
                    Blocks.LOCK -> locks[Coordinate(j, i)]
                        ?: throw Exception("Initialization:: A tile declared as Lock without lock info registered")
                    Blocks.STAIR -> stairdatas.firstOrNull { it.coo == Coordinate(j, i) }?.let { StairBlock(it.dir) }
                        ?: throw Exception("Initialization:: A tile declared as Stair without Stair info registered")
                    Blocks.VOID -> VoidBlock
                }
            }
        }

        val levels = grid.map { it.map { it.level } }
        val biomes = grid.map { it.map { it.biome } }

        squares = zip(blocks, levels, biomes) { block, lev, biom ->
            Square(block, lev, biom, null, null, null, null, null, null)
        }

        // Assignment for items
        gemdatas.forEach {
            validPositionChecks(it, "#gems")
            squares[it.y][it.x].gem = GemItem()
        }
        beeperdatas.forEach {
            validPositionChecks(it, "#beepers")
            squares[it.y][it.x].beeper = BeeperItem()
        }
        switchdatas.forEach {
            validPositionChecks(it.coo, "#switches")
            squares[it.coo.y][it.coo.x].switch = SwitchItem(it.on)
        }

        portals.forEach {
            validPositionChecks(it.value, "#portals")
            squares[it.value.y][it.value.x].portal = it.key
        }
        platforms.forEach {
            validPositionChecks(it.value, "#platforms")
            validPlatformHeightChecks(it.value, it.key.level)
            squares[it.value.y][it.value.x].platform = it.key
        }

        monsterdatas.forEach {
            validPositionChecks(it, "#monsters")
            squares[it.y][it.x].monster = true
        }

        // Player assignment into playground will be delayed within the instance of playground, due to the usage
        // of KotlinPoet.

    }

    /**
     * Private helper to detect if a collection doesn't have several same entries
     */
    private fun Collection<Coordinate>.notContainsDuplications(): Boolean {
        return this.toSet().size == this.size
    }

    // We use the grid's dimensions for position validation check
    private fun validPositionChecks(pos: Coordinate, phase: String): Unit {
        check (pos.y in grid.indices && pos.x in grid[0].indices) { "Initialization:: boundary check failed in $phase" }
    }

    private fun validPlatformHeightChecks(pos: Coordinate, platformHeight: Int): Unit {
        check (grid[pos.y][pos.x].level < platformHeight) { "Initialization:: height check failed in #platforms" }
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

    // We check if the entries in rules themselves are valid i.e. within boundary of playground
    private fun preInitRuleCheck() {
        gamingCondition?.arriveAt?.forEach { validPositionChecks(it, "#rule.arriveAt") }
//        gamingCondition?.putBeepersAt?.forEach { validPositionChecks(it, "#rule.putBeepersAt") }
    }

    /**
     * Build up the playground DSL code, launch the eval engine and return a Pair<Any?, Status> which contains the following info:
     * - if the eval server is launched successfully, two scenario could be possible, with status as Status.OK
     *     - everything goes well, the first element of tuple will be Pair<Payload, GameStatus>
     *     - something goes wrong, the first element will be a string indicating the message of error
     * - if the eval server encountered an error, the first element will be a string indicating the message of error, with status as Status.ERROR
     * - if the code is not completed, the first element will be null and the second will be Status.INCOMPLETE
     *
     * You can configure the function to pretty print the generated DSL code by passing `output` to Bridge
     */
    fun start(): Pair<Any?, Status> {
        val codeGen = StringBuilder()
        val cocoa = Cocoa()
        cocoa.feed(squares)
            .feed(portals)
            .feed(locks)
            .feed(players)
            .feed(gamingCondition, userCollision)
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