/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.app

import amatsukazeGrammarLexer
import amatsukazeGrammarParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams.fromStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.ironica.amatsukaze.bridge.convertJsonToMiscLayout
import org.ironica.amatsukaze.corelanguage.AmatsukazeVisitor
import org.ironica.amatsukaze.manager.ColorfulManager
import org.ironica.amatsukaze.playground.*
import org.ironica.amatsukaze.bridge.LockData
import org.ironica.amatsukaze.bridge.PortalData
import org.ironica.amatsukaze.bridge.StairData
import org.ironica.amatsukaze.manager.ColorfulMountainManager
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Direction
import org.ironica.amatsukaze.playground.Items
import org.ironica.amatsukaze.playground.characters.Player
import org.ironica.amatsukaze.playground.data.*
import java.io.FileInputStream
import java.io.InputStream

fun main(args: Array<String>) {

    val debug = args.any { it == "debug" }
    val stdout = args.any { it == "stdout" }

    val inputstream: InputStream = if (args.isNotEmpty()) FileInputStream(args[0]) else System.`in`
    val input: CharStream = fromStream(inputstream)
    val lexer = amatsukazeGrammarLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = amatsukazeGrammarParser(tokens)
    val tree: ParseTree = parser.top_level()

    val squares = List(5) { List(5) { Square(Open, Color.WHITE, 1, Biome.PLAINS, null, null, null, null, null) } }
    val player = Player(0, Direction.RIGHT, 500)
    squares[0][0].players.add(player)
    squares[4][4].gem = Gem()
    for (i in squares[0].indices) squares[0][i].level = 2
    squares[1][2].level = 3; squares[2][2].level = 3; squares[3][2].level = 2
    squares[1][2].block = Stair(Direction.UP)
    squares[2][2].block = Stair(Direction.DOWN)
    squares[3][2].block = Stair(Direction.LEFT)
    /*
     2 2 2  2 2
     1 1 3^ 1 1
     1 1 3v 1 1
     1 1 2< 1 1
     1 1 1  1 1
     */
    val portals = mutableMapOf<Portal, Coordinate>()
    val locks = mutableMapOf<Coordinate, Lock>()
    val players = mutableMapOf(player to Coordinate(0, 0))

    val playground = Playground(squares, portals, locks, players)
    val manager = ColorfulMountainManager(playground,
        debug = true, stdout = true
    )
    val exec = AmatsukazeVisitor(manager)

    println()

    exec.visit(tree)
    playground.printGrid()
    println( if (playground.win()) "stage cleared!" else "not finished...")
}

fun <T> List<List<T>>.convertToMutableList(): MutableList<MutableList<T>> {
    return this.map { it.toMutableList() }.toMutableList()
}