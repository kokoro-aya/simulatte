/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.playground.data

import org.ironica.simulatte.playground.Biome
import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.characters.AbstractCharacter
import utils.StringRepresentable
import utils.stringRepresentation

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
    var players: MutableList<AbstractCharacter> = mutableListOf()
): StringRepresentable {
    override val stringRepresentation: String
        get() = "\tSquare(" +
                "\n\t\t" + this.block.stringRepresentation + ", " +
                "\n\t\t" + this.color.stringRepresentation + ", " + this.level + ", " + this.biome.stringRepresentation + ", " +
                "\n\t\t" + (if (this.switch == null) "null, " else "Switch(${this.switch!!.on}), ") +
                "\n\t\t" + (if (this.gem == null) "null, " else "Gem(), ") +
                "\n\t\t" + (if (this.beeper == null) "null, " else "Beeper(), ") +
                "\n\t\t" + (if (this.portal == null) "null, " else "Portal(" + this.portal!!.let {
            "${it.coo.stringRepresentation}, ${it.dest.stringRepresentation}, ${it.color}, ${it.isActive}, ${it.energy}"
        } + "), ") +
                "\n\t\t" + (if (this.platform == null) "null, " else "Platform(" + this.platform!!.let {
            "${it.level}, mutableListOf(${it.players.map { "${it.stringRepresentation}, " }})"
        } + "), ") +
                "\n\t\t" + "mutableListOf(" + this.players.map { "${it.stringRepresentation}, " } + ")" +
                ")"
}
