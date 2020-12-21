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

    val grid = arrayOf(
        arrayOf(Block.OPEN, Block.OPEN, Block.OPEN, Block.DESERT, Block.OPEN, Block.OPEN, Block.OPEN, Block.DESERT, Block.DESERT),
        arrayOf(Block.BLOCKED, Block.TREE, Block.BLOCKED, Block.DESERT, Block.WATER, Block.OPEN, Block.WATER, Block.OPEN, Block.BLOCKED)
    )
    val layout = arrayOf(
        arrayOf(Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE, Item.CLOSEDSWITCH, Item.NONE),
        arrayOf(Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE, Item.GEM, Item.NONE),
    )
    val layout2s = Array (2) { Array (9) { Tile() } }
    val portals = arrayOf<Portal>()
    val players = arrayOf(Player(
        1,
        Coordinate(0, 0),
        Direction.RIGHT
    ))
    val playground = Playground(grid, layout, layout2s, portals, players, 4)
    val manager = PlaygroundManager(playground)
    val exec = AmatsukazeVisitor(manager)
    exec.visit(tree)
    playground.printGrid()
    println( if (playground.win()) "stage cleared!" else "not finished...")
}