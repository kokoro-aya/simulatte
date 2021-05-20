/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.payloads

import kotlinx.serialization.Serializable
import org.ironica.amatsukaze.playground.Color
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.data.Coordinate

@Serializable
data class Payload(
    val grid: List<List<Blocks>>,
    val colors: List<List<Color>>,
    val levels: List<List<Int>>,
    val gems: List<Coordinate>,
    val beepers: List<Coordinate>,
    val switches: List<SerializedSwitch>,
    val portals: List<SerializedPortalOrLock>,
    val platforms: List<SerializedPlatform>,
    val locks: List<SerializedPortalOrLock>,
    val players: List<SerializedPlayer>,
    val consoleLog: String,
    val special: String
    ) {
}

