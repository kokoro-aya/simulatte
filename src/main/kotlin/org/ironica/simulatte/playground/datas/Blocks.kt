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

import org.ironica.simulatte.playground.Direction
import utils.StringRepresentable
import utils.stringRepresentation

sealed class Block: StringRepresentable
object Open: Block() {
    override val stringRepresentation: String
        get() = "Open"
}
object Blocked: Block() {
    override val stringRepresentation: String
        get() = "Blocked"
}

data class Lock(val id: Int, val controlled: MutableList<Coordinate>, var isActive: Boolean, var energy: Int): Block() {
    override val stringRepresentation: String
        get() = "Lock($id, mutableL${controlled.stringRepresentation.drop(1)}, $isActive, $energy)"
}

// TODO add rules on lock energy and deactivation
data class Stair(val dir: Direction): Block() {
    override val stringRepresentation: String
        get() = "Stair(${dir.stringRepresentation})"
}

object Void: Block() {
    override val stringRepresentation: String
        get() = "Void"
}
