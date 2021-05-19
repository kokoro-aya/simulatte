/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.playground.data

import org.ironica.amatsukaze.playground.Color
import org.ironica.amatsukaze.playground.characters.Player

sealed class Item
data class Switch(var on: Boolean): Item()
data class Gem(val disappearIn: Int = 0): Item()
data class Beeper(val disappearIn: Int = 0): Item()
data class Portal(
    val coo: Coordinate,
    val dest: Coordinate,
    val color: Color,
    var isActive: Boolean,
    var energy: Int = 100
): Item()
data class Platform(
    var level: Int,
    val players: MutableList<Player> = mutableListOf()
)