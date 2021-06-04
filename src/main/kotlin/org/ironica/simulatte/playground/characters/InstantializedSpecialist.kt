/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.playground.characters

import org.ironica.simulatte.playground.Direction
import org.ironica.simulatte.playground.Playground
import org.ironica.simulatte.playground.datas.Coordinate
import org.ironica.utils.StringRepresentable
import org.ironica.utils.stringRepresentation

/**
 * Specialist implementation of AbstractCharacter, added some proper actions.
 * `playground` field should be injected after initialization otherwise it will be null.
 * This class conforms to StringRepresentable interface.
 */
data class InstantializedSpecialist(override val id: Int): AbstractCharacter, StringRepresentable {

    override var dir: Direction = Direction.UP
    override var stamina: Int = 500

    constructor(id: Int, dir: Direction, stamina: Int) : this(id) {
        this.dir = dir
        this.stamina = stamina
    }

    override var playground: Playground? = null

    override var collectedGem = 0
    override var beeperInBag = 0

    override val walkedTiles: MutableSet<Coordinate> = mutableSetOf()
    override var repassed: Boolean = false

    override var inWaterForTurns: Int = 0
    override var inLavaForTurns: Int = 0

    fun isBeforeLock() = playground?.specialistIsBeforeLock(this) ?: NullPointerException("Specialist:: Uninitialized character")

    fun turnLockUp() = playground?.specialistTurnLockUp(this) ?: NullPointerException("Specialist:: Uninitialized character")

    fun turnLockDown() = playground?.specialistTurnLockDown(this) ?: NullPointerException("Specialist:: Uninitialized character")

    override val stringRepresentation: String
        get() = "InstantializedSpecialist($id, ${dir.stringRepresentation}, $stamina)"
}