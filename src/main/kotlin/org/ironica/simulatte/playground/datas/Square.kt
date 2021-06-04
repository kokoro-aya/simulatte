/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.playground.datas

import org.ironica.simulatte.playground.Biome
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.utils.StringRepresentable
import org.ironica.utils.stringRepresentation

/**
 * Represents a tile of playground. Each item is represented with a nullable entry
 */
data class Square(
    var block: BlockObject,
    var level: Int,
    var biome: Biome,
    var switch: SwitchItem?,
    var gem: GemItem?,
    var beeper: BeeperItem?,
    var portal: PortalItem?,
    var monster: Boolean?,
    var platform: PlatformItem?,
    var players: MutableList<AbstractCharacter> = mutableListOf()
): StringRepresentable {
    override val stringRepresentation: String
        get() = buildString {
            append("Square(")
            append("${this@Square.block.stringRepresentation}, ")
            append("${this@Square.level}, ")
            append("${this@Square.biome.stringRepresentation}, ")
            if (this@Square.switch == null) append("null, ")
            else append("SwitchItem(${this@Square.switch!!.on}), ")
            if (this@Square.gem == null) append("null, ")
            else append("GemItem(), ")
            if (this@Square.beeper == null) append("null, ")
            else append("BeeperItem(), ")
            if (this@Square.portal == null) append("null, ")
            else {
                append("PortalItem(")
                this@Square.portal?.let {
                    append("${it.coo.stringRepresentation}, ")
                    append("${it.dest.stringRepresentation}, ")
                    append("${it.color.stringRepresentation}, ")
                    append("${it.isActive}, ")
                    append("${it.energy}")
                }
                append("), ")
            }
            if (this@Square.monster == null) append("null, ")
            else {
                append("${this@Square.monster}, ")
            }
            if (this@Square.platform == null) append("null, ")
            else {
                append("PlatformItem(")
                this@Square.platform?.let {
                    append("${it.level}, ")
                    append("mutableListOf(")
                    it.players.forEachIndexed { i, e ->
                        append(e.stringRepresentation)
                        if (i < it.players.size - 1) append(", ")
                    }
                    append(")), ")
                }
            }
            append("mutableListOf(")
            this@Square.players.forEachIndexed { i, e ->
                append(e.stringRepresentation)
                if (i < this@Square.players.size - 1) append(", ")
            }
            append("))")
        }
}
