/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.playground

import kotlinx.serialization.Serializable

@Serializable
data class SerializedPlayer(val id: Int, val x: Int, val y: Int, val dir: Direction, val role: Role, val stamina: Int)

@Serializable
data class SerializedPlayground(val grid: Grid, val itemLayout: ItemLayout, val colors: List<List<Color>>, val levels: List<List<Int>>)

@Serializable
data class Payload(
    val grid: Grid,
    val itemLayout: ItemLayout,
    val colors: List<List<Color>>,
    val levels: List<List<Int>>,
    val players: List<SerializedPlayer>,
    val portals: List<Portal>,
    val consoleLog: String,
    val special: String
    ) {
    constructor(
        players: List<SerializedPlayer>,
        portals: List<Portal>,
        grid: SerializedPlayground,
        consoleLog: String,
        special: String
    ): this(grid.grid, grid.itemLayout, grid.colors, grid.levels, players, portals, consoleLog, special)
}

val payloadStorage = mutableListOf<Payload>()

enum class Status { OK, ERROR }

@Serializable
sealed class Message
@Serializable
data class NormalMessage(val status: Status, val payload: List<Payload>): Message()
@Serializable
data class ErrorMessage(val status: Status, val msg: String): Message()