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

import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.utils.StringRepresentable
import org.ironica.utils.stringRepresentation

/**
 * Item objects, used within each square
 */
sealed class ItemObject
data class SwitchItem(var on: Boolean): ItemObject()
data class GemItem(val disappearIn: Int = 0): ItemObject()
data class BeeperItem(val disappearIn: Int = 0): ItemObject()

/**
 * This class conforms to StringRepresentable in order to be able to be codegen
 */
data class PortalItem(
    val id: Int,
    val coo: Coordinate,
    val dest: Coordinate,
    val color: Color,
    var energy: Int = 10
): ItemObject(), StringRepresentable {
    var isActive: Boolean
        get() = energy > 0
        set(value) {
            energy = if (value) 10 else 0
        }
    override val stringRepresentation: String
        get() = "PortalItem($id, ${coo.stringRepresentation}, ${dest.stringRepresentation}, ${color.stringRepresentation}, $energy)"
}
data class PlatformItem(
    var level: Int,
    val players: MutableList<AbstractCharacter> = mutableListOf()
)