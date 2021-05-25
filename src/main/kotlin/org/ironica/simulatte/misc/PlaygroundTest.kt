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

import kotlinx.coroutines.runBlocking
import org.ironica.simulatte.internal.play
import org.ironica.simulatte.manager.ColorfulManager
import org.ironica.simulatte.payloads.payloadStorage
import org.ironica.simulatte.payloads.statusStorage
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.playground.datas.*

public val squares: List<List<Square>> = listOf(
        listOf(
                Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, Gem(), null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
    ,
    listOf(
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()),
        Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null, mutableListOf()))
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
        locks.toMutableMap(), players.toMutableMap())

public val manager: ColorfulManager = ColorfulManager(playground, false, false)

fun main() {
    payloadStorage.set(mutableListOf())
    statusStorage.set(GameStatus.PENDING)
    runBlocking {
        val ___game = play(manager) {
            val a = Player()
            val b = Player()
            val c = Specialist()
            for (i in 1 .. 3) {
                a.changeColor(Color.RED)
                a.moveForward()
                b.changeColor(Color.BLACK)
                b.moveForward()
                c.changeColor(Color.GREEN)
                c.moveForward()
            }
        }.test()

        ___game.squares.forEach {
            it.forEach { println(it.color) }
            println()
        }

        payloadStorage.get().size.let { println(it) }
    }
}
    