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

import kotlinx.serialization.Serializable
import org.ironica.amatsukaze.playground.*
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Items

@Serializable
data class IncomingData(
    val type: String,
    val code: String,
    val grid: List<List<Blocks>>,
    val layout: List<List<Items>>,
    val colors: List<List<Color>>,
    val levels: List<List<Int>>,
    val biomes: List<List<Biome>>,
    val portals: List<PortalData>,
    val locks: List<LockData>,
    val stairs: List<StairData>,
    val platforms: List<PlatformData>,
    val players: List<PlayerData>
)