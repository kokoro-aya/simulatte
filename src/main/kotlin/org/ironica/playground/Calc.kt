package org.ironica.playground

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams.fromStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import playgroundGrammarLexer
import playgroundGrammarParser
import java.io.FileInputStream
import java.io.InputStream

fun main(args: Array<String>) {
    val inputstream: InputStream = if (args.isNotEmpty()) FileInputStream(args[0]) else System.`in`
    val input: CharStream = fromStream(inputstream)
    val lexer = playgroundGrammarLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = playgroundGrammarParser(tokens)
    val tree: ParseTree = parser.top_level()
}