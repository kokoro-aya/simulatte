package org.ironica.amatsukaze

import amatsukazeGrammarLexer
import amatsukazeGrammarParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams.fromStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
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
        listOf(Block.OPEN, Block.STAIR, Block.STAIR, Block.STAIR, Block.STAIR),
        listOf(Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN),
        listOf(Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN),
        listOf(Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN, Block.OPEN)
    )
    val layout = listOf(
        listOf(Item.NONE, Item.NONE, Item.NONE, Item.NONE, Item.NONE),
        listOf(Item.NONE, Item.NONE, Item.NONE, Item.NONE, Item.NONE),
        listOf(Item.NONE, Item.NONE, Item.NONE, Item.NONE, Item.NONE),
        listOf(Item.NONE, Item.NONE, Item.NONE, Item.NONE, Item.GEM)
    )
    val colors = listOf(
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
        listOf(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, ),
    )
    val levels = listOf(
        listOf(1, 2, 3, 3, 2),
        listOf(1, 2, 2, 2, 2),
        listOf(2, 1, 2, 2, 2),
        listOf(0, 1, 2, 2 ,2),
    )

    val locks = listOf<Lock>()
    val portals = listOf<Portal>()
    val stairs = listOf(
        Stair(Coordinate(1, 0), Direction.LEFT),
        Stair(Coordinate(2, 0), Direction.LEFT),
        Stair(Coordinate(3, 0), Direction.RIGHT),
        Stair(Coordinate(4, 0), Direction.RIGHT)
    )
    val players = listOf(Player(
        1,
        Coordinate(0, 0),
        Direction.RIGHT,
        1000
    ))
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