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
import org.ironica.simulatte.playground.Biome
import org.ironica.simulatte.playground.Blocks
import org.ironica.simulatte.playground.Color

@Serializable
data class GridData(
    val block: Blocks,
    val level: Int,
    val color: Color = Color.WHITE,
    val biome: Biome,
)