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

import org.ironica.amatsukaze.playground.characters.Player
import org.ironica.amatsukaze.playground.Biome
import org.ironica.amatsukaze.playground.Color

data class Square(
    var block: Block,
    var color: Color,
    var level: Int,
    var biome: Biome,
    var switch: Switch?,
    var gem: Gem?,
    var beeper: Beeper?,
    var portal: Portal?,
    var platform: Platform?,
    var players: MutableList<Player> = mutableListOf()
)
