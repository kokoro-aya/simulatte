/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.app

import org.ironica.simulatte.payloads.Status
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.Direction
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.datas.*
import org.ironica.simulatte.simulas.Cocoa
import org.ironica.simulatte.simulas.EvalRunner
import org.ironica.simulatte.simulas.wrapCode
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream

/**
 * This main function is exposed via CalcKt, in purpose of testing the evaluation of Kotlin DSL features
 */
fun main(args: Array<String>) {

    val debug = args.any { it == "debug" }
    val stdout = args.any { it == "stdout" }

    val nameArgs = args.filterNot { it == "debug" || it == "stdout" }

    val inputStream: InputStream = if (nameArgs.isNotEmpty()) FileInputStream(args[0]) else System.`in`

    val squares = List(5) { List(5) { Square(OpenBlock, 1, Biome.PLAINS, null, null, null, null, null, null) } }
    val player = InstantializedPlayer(0, Direction.RIGHT, 500)
    squares[4][4].gem = GemItem()
    for (i in squares[0].indices) squares[0][i].level = 2
    squares[1][2].level = 3; squares[2][2].level = 3; squares[3][2].level = 2
    squares[1][2].block = StairBlock(Direction.UP)
    squares[2][2].block = StairBlock(Direction.DOWN)
    squares[3][2].block = StairBlock(Direction.LEFT)
    /*
     2 2 2  2 2
     1 1 3^ 1 1
     1 1 3v 1 1
     1 1 2< 1 1
     1 1 1  1 1
     */
    val portals = mutableMapOf<PortalItem, Coordinate>()
    val locks = mutableMapOf<Coordinate, LockBlock>()
    val players = mutableMapOf<AbstractCharacter, Coordinate>(player to Coordinate(0, 0))

    val code = inputStream.bufferedReader().use(BufferedReader::readText)

    val codeGen = StringBuilder()
    val cocoa = Cocoa()
    cocoa.feed(squares)
        .feed(locks)
        .feed(portals)
        .feed(players)
        .feed(null, true)
        .thenFeed("default", debug, stdout)
        .generate(codeGen)
    codeGen.append("\n")
    codeGen.append(code.wrapCode())

    val gen = codeGen.toString()

    if (debug) println(gen)

    EvalRunner().evalSnippet(gen).let {
        println(it.first)
        println()
        println(when (it.second) {
            Status.OK -> "[1]Code executed successfully"
            Status.ERROR -> "[2]Some error occurred while executing your code"
            Status.INCOMPLETE -> "[3]Your code is not complete"
        })
    }

}