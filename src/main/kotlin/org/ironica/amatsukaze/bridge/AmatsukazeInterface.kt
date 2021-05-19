/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.bridge

import amatsukazeGrammarLexer
import amatsukazeGrammarParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.ironica.amatsukaze.corelanguage.AmatsukazeVisitor
import org.ironica.amatsukaze.manager.ColorfulManager
import org.ironica.amatsukaze.manager.ColorfulMountainManager
import org.ironica.amatsukaze.manager.MountainousManager
import org.ironica.amatsukaze.playground.*
import org.ironica.amatsukaze.playground.data.*
import org.ironica.amatsukaze.playground.characters.Player

class AmatsukazeInterface(
    val type: String,
    val code: String,
    val grid: Grid,
    val itemLayout: ItemLayout,
    val miscLayout: TileLayout,
    val portals: List<Portal>,
    val locks: List<Lock>,
    val stairs: List<Stair>,
    val players: List<Player>,
    val debug: Boolean, val stdout: Boolean
) {
    fun start() {
        val input: CharStream = CharStreams.fromString(code)
        val lexer = amatsukazeGrammarLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = amatsukazeGrammarParser(tokens)
        val tree: ParseTree = parser.top_level()
        val playground = Playground(grid, itemLayout, miscLayout, portals, locks, stairs, players.toMutableList(), calculateInitialGem(itemLayout))
        val manager = when (type) {
            "colorful" -> ColorfulManager(playground, debug, stdout)
            "mountainous" -> MountainousManager(playground, debug, stdout)
            "colorfulmountainous" -> ColorfulMountainManager(playground, debug, stdout)
            else -> throw Exception("Unsupported game module")
        }
        manager.appendEntry() // Store the initial state of playground into payload list
        val exec = AmatsukazeVisitor(manager)
        exec.visit(tree)
    }
}