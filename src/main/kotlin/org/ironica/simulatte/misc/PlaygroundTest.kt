/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.misc

import kotlinx.coroutines.*
import org.ironica.simulatte.internal.Player
import org.ironica.simulatte.internal.Specialist
import org.ironica.simulatte.internal.play
import org.ironica.simulatte.manager.DefaultManager
import org.ironica.simulatte.payloads.payloadStorage
import org.ironica.simulatte.payloads.statusStorage
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.playground.datas.*

public val squares: List<List<Square>> = listOf(
    listOf(
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
    )


public val portals: Map<Portal, Coordinate> = mapOf(
        )


public val locks: Map<Coordinate, Lock> = mapOf(
        )


public val players: Map<AbstractCharacter, Coordinate> = mapOf(
            InstantializedPlayer(1, Direction.DOWN, 90) to Coordinate(0, 0),
        InstantializedSpecialist(2, Direction.DOWN, 120) to Coordinate(1, 0),
        InstantializedPlayer(3, Direction.DOWN, 100) to Coordinate(2, 0),
    )


public val playground: Playground = Playground(squares, portals.toMutableMap(),
        locks.toMutableMap(), players.toMutableMap(), null)

public val manager: DefaultManager = DefaultManager(playground, false, false)

fun main() {
    payloadStorage.set(mutableListOf())
    statusStorage.set(GameStatus.PENDING)
    val ___game = play(manager) {
        val a = Player()
        val b = Player()
        val c = Specialist()

        suspend fun move(p: Any) {
            for (i in 1..3) {
                when (p) {
                    is Player -> p.moveForward()
                    is Specialist -> p.moveForward()
                    else -> {
                    }
                }
            }
        }

        runBlocking {

            val jobA = launch {
                move(a)
            }
            val jobB = launch {
                move(b)
            }
            val jobC = launch {
                move(c)
            }
            joinAll(jobA, jobB, jobC)
        }
    }.test()

    ___game.printGrid()

    payloadStorage.get().size.let { println(it) }
}

//val squares: List<List<Square>> = listOf(
//        listOf(
//                Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, Portal(Coordinate(6, 0),
//        Coordinate(9, 5), Color.WHITE, true, 100), null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
//    ,
//    listOf(
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()))
//    ,
//    listOf(
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(true), null, null, null, null,
//        mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
//    ,
//    listOf(
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()))
//    ,
//    listOf(
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(true), null, null, null, null,
//        mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
//    ,
//    listOf(
//        Square(Open, 1, Biome.PLAINS, null, null, null, Portal(Coordinate(0, 5),
//        Coordinate(4, 5), Color.WHITE, true, 100), null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, Portal(Coordinate(4, 5),
//        Coordinate(0, 5), Color.WHITE, true, 100), null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, Switch(false), null, null, null, null,
//        mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Blocked, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
//        Square(Open, 1, Biome.PLAINS, null, null, null, Portal(Coordinate(9, 5),
//        Coordinate(6, 5), Color.WHITE, true, 100), null, mutableListOf()))
//    )
//
//
//public val portals: Map<Portal, Coordinate> = mapOf(
//            Portal(Coordinate(0, 5), Coordinate(4, 5), Color.WHITE, true, 100) to Coordinate(0, 5),
//        Portal(Coordinate(4, 5), Coordinate(0, 5), Color.WHITE, true, 100) to Coordinate(4, 5),
//        Portal(Coordinate(9, 5), Coordinate(6, 5), Color.WHITE, true, 100) to Coordinate(9, 5),
//        Portal(Coordinate(6, 0), Coordinate(9, 5), Color.WHITE, true, 100) to Coordinate(6, 0),
//    )
//
//
//public val locks: Map<Coordinate, Lock> = mapOf(
//        )
//
//
//public val players: Map<AbstractCharacter, Coordinate> = mapOf(
//            InstantializedPlayer(1, Direction.DOWN, 500) to Coordinate(0, 0),
//        InstantializedPlayer(2, Direction.DOWN, 500) to Coordinate(9, 0),
//    )
//
//
//public val playground: Playground = Playground(squares, portals.toMutableMap(),
//        locks.toMutableMap(), players.toMutableMap())
//
//public val manager: DefaultManager = DefaultManager(playground, false, false)
//
//fun main() {
//    payloadStorage.set(mutableListOf())
//    statusStorage.set(GameStatus.PENDING)
//    runBlocking {
//        val ___game = play(manager) {
//            val a = Player()
//            val b = Player()
//            fun turnBack(p: Player) {
//                p.turnLeft()
//                p.turnLeft()
//            }
//
//            fun turnRight(p: Player) {
//                turnBack(p)
//                p.turnLeft()
//            }
//            for (i in 1..5) {
//                a.moveForward()
//                if (a.isOnClosedSwitch) {
//                    a.toggleSwitch()
//                }
//            }
//            turnBack(a)
//            a.moveForward()
//            a.turnLeft()
//            a.moveForward()
//            a.collectGem()
//            turnRight(a)
//            a.moveForward()
//            a.turnLeft()
//            a.moveForward()
//            a.collectGem()
//            turnBack(a)
//
//            for (i in 1..2) {
//                a.moveForward()
//            }
//            a.turnLeft()
//            a.moveForward()
//            a.toggleSwitch()
//            a.moveForward()
//            a.turnLeft()
//            for (i in 1..2) {
//                a.moveForward()
//            }
//            a.collectGem()
//            turnBack(a)
//            a.moveForward()
//            a.turnLeft()
//            a.moveForward()
//            a.collectGem()
//
//            for (i in 1..5) {
//                b.moveForward()
//                if (b.isOnGem) {
//                    b.collectGem()
//                }
//            }
//
//            fun process() {
//                b.turnLeft()
//                b.moveForward()
//                b.collectGem()
//                turnBack(b)
//                b.moveForward()
//                b.turnLeft()
//            }
//
//            b.moveForward()
//            process()
//            b.moveForward()
//            b.moveForward()
//            process()
//            b.moveForward()
//            b.moveForward()
//            b.toggleSwitch()
//
//        }.run()
//
//         println(___game.size)
//    }
//
//    println(manager.playground.status)
//    println(manager.gemCount())
//    println(manager.gemLeft())
//    println(manager.switchCount())
//    println(manager.closedSwitchCount())
//
//    payloadStorage.get() to statusStorage.get()
//
//}