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
import org.ironica.amatsukaze.playground.data.Coordinate
import org.ironica.amatsukaze.bridge.LockData
import org.ironica.amatsukaze.bridge.PortalData
import org.ironica.amatsukaze.bridge.StairData
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Direction
import org.ironica.amatsukaze.playground.Items
import org.ironica.amatsukaze.playground.characters.Player
import java.io.FileInputStream
import java.io.InputStream

fun main(args: Array<String>) {
    val inputstream: InputStream = if (args.isNotEmpty()) FileInputStream(args[0]) else System.`in`
    val input: CharStream = fromStream(inputstream)
    val lexer = amatsukazeGrammarLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = amatsukazeGrammarParser(tokens)
    val tree: ParseTree = parser.top_level()

//    val grid = listOf(
//        listOf(Block.OPEN, Block.OPEN, Block.OPEN, Block.DESERT, Block.OPEN, Block.OPEN, Block.OPEN, Block.DESERT, Block.DESERT),
//        listOf(Block.BLOCKED, Block.TREE, Block.BLOCKED, Block.DESERT, Block.WATER, Block.OPEN, Block.WATER, Block.OPEN, Block.BLOCKED)
//    )
//    val layout = listOf(
//        listOf(Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE),
//        listOf(Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE)
//    )
//    val layout2s = List (2) { List (9) { Tile() } }

    val grid = listOf(
        listOf(Blocks.OPEN, Blocks.OPEN, Blocks.OPEN, Blocks.OPEN, Blocks.OPEN),
        listOf(Blocks.OPEN, Blocks.OPEN, Blocks.STAIR, Blocks.OPEN, Blocks.OPEN),
        listOf(Blocks.OPEN, Blocks.OPEN, Blocks.STAIR, Blocks.OPEN, Blocks.OPEN),
        listOf(Blocks.OPEN, Blocks.OPEN, Blocks.STAIR, Blocks.OPEN, Blocks.OPEN)
    )
    val layout = listOf(
        listOf(Items.NONE, Items.NONE, Items.NONE, Items.NONE, Items.NONE),
        listOf(Items.NONE, Items.NONE, Items.NONE, Items.NONE, Items.NONE),
        listOf(Items.NONE, Items.NONE, Items.NONE, Items.NONE, Items.NONE),
        listOf(Items.NONE, Items.NONE, Items.NONE, Items.NONE, Items.GEM)
    )
    val colors = listOf(
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
    )
    val levels = listOf(
        listOf(2, 2, 2, 2, 2),
        listOf(1, 1, 3, 1 ,1),
        listOf(1, 1, 3, 1, 1),
        listOf(1, 1, 2, 1 ,1),
    )

    val locks = listOf<LockData>()
    val portals = listOf<PortalData>()
    val stairs = listOf(
        StairData(Coordinate(2, 1), Direction.UP),
        StairData(Coordinate(2, 2), Direction.DOWN),
        StairData(Coordinate(2, 3), Direction.DOWN),
    )
    val players = listOf(
        Player(
        1,
        Coordinate(2, 0),
        Direction.DOWN,
        1000
    )
    )
    val playground = Playground(
        grid.convertToMutableList(),
        layout.convertToMutableList(),
        convertJsonToMiscLayout(colors, levels, "colorfulmountainous", grid.size to grid[0].size).convertToMutableList(),
        portals, locks, stairs, players.toMutableList(), 4)
    val manager = ColorfulManager(playground,
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