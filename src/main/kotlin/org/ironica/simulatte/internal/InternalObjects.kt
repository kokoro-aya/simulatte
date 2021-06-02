/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.internal

import org.ironica.simulatte.manager.AbstractManager
import org.ironica.simulatte.playground.Color

data class PortalP private constructor(internal val color: Color, internal var id: Int?) {

    var manager: AbstractManager? = null

    constructor(color: Color): this(color, null)

    var isActive: Boolean
        get() = manager?.playground?.portals?.keys?.firstOrNull { it.id == id }?.isActive ?: throw NullPointerException("Portal:: uninitialized portal or no such portal")
        set(value) {
            manager?.playground?.portals?.keys?.firstOrNull { it.id == id }?.isActive = value
        }
}

object Stair