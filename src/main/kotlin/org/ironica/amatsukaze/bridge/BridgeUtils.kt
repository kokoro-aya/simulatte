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

import org.ironica.amatsukaze.playground.*
import org.ironica.amatsukaze.playground.data.Coordinate
import org.ironica.amatsukaze.playground.data.ItemLayout
import org.ironica.amatsukaze.playground.data.Tile
import org.ironica.amatsukaze.playground.data.TileLayout
import org.ironica.amatsukaze.playground.enums.Direction
import org.ironica.amatsukaze.playground.enums.Item
import org.ironica.amatsukaze.playground.characters.Player
import org.ironica.amatsukaze.playground.characters.Specialist

fun convertJsonToLayout(array: List<List<String>>): ItemLayout {
    return array.map { it.map { when (it) {
        "NONE" -> Item.NONE
        "GEM" -> Item.GEM
        "BEEPER" -> Item.BEEPER
        "OPENEDSWITCH" -> Item.OPENEDSWITCH
        "CLOSEDSWITCH" -> Item.CLOSEDSWITCH
        "PORTAL" -> Item.PORTAL
        "PLATFORM" -> Item.PLATFORM
        else -> throw Exception("Cannot parse data to layout")
    } }.toMutableList()}.toMutableList()
}

fun convertJsonToPlayers(array: List<PlayerData>): List<Player> {
    return array.map { when(it.role) {
        "PLAYER" -> Player(
            it.id, Coordinate(it.x, it.y), when (it.dir) {
                "UP" -> Direction.UP
                "DOWN" -> Direction.DOWN
                "LEFT" -> Direction.LEFT
                "RIGHT" -> Direction.RIGHT
                else -> throw Exception("Cannot parse data to player")
            }, it.stamina.toIntOrNull()
        )
        "SPECIALIST" -> Specialist(
            it.id, Coordinate(it.x, it.y), when (it.dir) {
                "UP" -> Direction.UP
                "DOWN" -> Direction.DOWN
                "LEFT" -> Direction.LEFT
                "RIGHT" -> Direction.RIGHT
                else -> throw Exception("Cannot parse data to specialist")
            }, it.stamina.toIntOrNull()
        )
        else -> throw Exception("Cannot parse data to player list")
    } }
}

fun convertJsonToMiscLayout(colors: List<List<Color>>, levels: List<List<Int>>, using: String, defaultSize: Pair<Int, Int>): TileLayout {
    return when (using) {
        "colorful" -> {
            if (colors.size == defaultSize.first && colors[0].size == defaultSize.second)
                colors.map { it.map { Tile(it) }.toMutableList() }.toMutableList()
            else
                MutableList(defaultSize.first) { MutableList(defaultSize.second) { Tile(Color.WHITE) } }
        }
        "mountainous" -> {
            if (levels.size == defaultSize.first && levels[0].size == defaultSize.second)
                levels.map { it.map { Tile(level = it) }.toMutableList() }.toMutableList()
            else
                MutableList(defaultSize.first) { MutableList(defaultSize.second) { Tile() } }
        }
        "colorfulmountainous" -> {
            val cc = if (colors.size == defaultSize.first && colors[0].size == defaultSize.second) colors
            else List(defaultSize.first) { List(defaultSize.second) { Color.WHITE } }
            val ll = if (levels.size == defaultSize.first && levels[0].size == defaultSize.second) levels
            else List(defaultSize.first) { List(defaultSize.second) { 1 } }

            cc.mapIndexed { i, line ->
                line.mapIndexed { j, it -> Tile(it, ll[i][j]) }.toMutableList()
            }.toMutableList()
        }
        else -> throw Exception("Unsupported game module")
    }
}

private fun convertDataToColor(data: String): Color {
    return when (data) {
        "BLACK" -> Color.BLACK
        "SILVER" -> Color.SILVER
        "GREY" -> Color.GREY
        "WHITE" -> Color.WHITE
        "RED" -> Color.RED
        "ORANGE" -> Color.ORANGE
        "GOLD" -> Color.GOLD
        "PINK" -> Color.PINK
        "YELLOW" -> Color.YELLOW
        "BEIGE" -> Color.BEIGE
        "BROWN" -> Color.BROWN
        "GREEN" -> Color.GREEN
        "AZURE" -> Color.AZURE
        "CYAN" -> Color.CYAN
        "ALICEBLUE" -> Color.ALICEBLUE
        "PURPLE" -> Color.PURPLE
        else -> throw Exception("Cannot parse data to color")
    }
}

fun calculateInitialGem(itemLayout: ItemLayout): Int = itemLayout.flatten().filter { it == Item.GEM }.size