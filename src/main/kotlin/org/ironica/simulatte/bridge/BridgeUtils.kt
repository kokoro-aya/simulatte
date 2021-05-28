/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.bridge

import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.datas.ItemLayout
import org.ironica.simulatte.playground.datas.AdditionalTileInfo
import org.ironica.simulatte.playground.datas.TileLayout
import org.ironica.simulatte.playground.Items

fun convertJsonToMiscLayout(colors: List<List<Color>>, levels: List<List<Int>>, using: String, defaultSize: Pair<Int, Int>): TileLayout {
    return when (using) {
        "colorful" -> {
            if (colors.size == defaultSize.first && colors[0].size == defaultSize.second)
                colors.map { it.map { AdditionalTileInfo(it) }.toMutableList() }.toMutableList()
            else
                MutableList(defaultSize.first) { MutableList(defaultSize.second) { AdditionalTileInfo(Color.WHITE) } }
        }
        "mountainous" -> {
            if (levels.size == defaultSize.first && levels[0].size == defaultSize.second)
                levels.map { it.map { AdditionalTileInfo(level = it) }.toMutableList() }.toMutableList()
            else
                MutableList(defaultSize.first) { MutableList(defaultSize.second) { AdditionalTileInfo() } }
        }
        "colorfulmountainous" -> {
            val cc = if (colors.size == defaultSize.first && colors[0].size == defaultSize.second) colors
            else List(defaultSize.first) { List(defaultSize.second) { Color.WHITE } }
            val ll = if (levels.size == defaultSize.first && levels[0].size == defaultSize.second) levels
            else List(defaultSize.first) { List(defaultSize.second) { 1 } }

            cc.mapIndexed { i, line ->
                line.mapIndexed { j, it -> AdditionalTileInfo(it, ll[i][j]) }.toMutableList()
            }.toMutableList()
        }
        else -> throw Exception("Unsupported game module")
    }
}
