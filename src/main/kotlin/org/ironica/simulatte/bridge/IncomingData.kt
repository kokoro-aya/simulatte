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

import kotlinx.serialization.Serializable
import org.ironica.simulatte.bridge.rules.GamingCondition
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.Blocks
import org.ironica.simulatte.playground.Items
import org.ironica.simulatte.playground.datas.Coordinate

@Serializable
data class IncomingData(
    val type: String,
    val code: String,
    val grid: List<List<GridData>>, // [{ biome: Biome, level: Int, block: Blocks, }]
    val gems: List<Coordinate>, // [{ x: Int, y: Int, }], same as below
    val beepers: List<Coordinate>,
    val switches: List<SwitchData>, // [{ coo: { x: Int, y: Int, }, on: Boolean }]
    val portals: List<PortalData>, // [{ coo: { x: Int, y: Int, }, dest: { x: Int, y: Int, }, isActive: Boolean }]
    val locks: List<LockData>, // [{ coo: Coordinate, controlled: [Coordinate], energy: Int = 0 }]
    val stairs: List<StairData>, // [{ coo: Coordinate, dir: Direction }]
    val platforms: List<PlatformData>, // [{ coo: Coordinate, level: Int }]
    val players: List<PlayerData>, // [{ id: Int, x: Int, y: Int, dir: Direction, role: Role, stamina: Int }]
    val gamingCondition: GamingCondition? = null, // see GamingCondition for more info, default is set to null
    val userCollision: Boolean = true, // control if one tile can have only one user, default is true
)