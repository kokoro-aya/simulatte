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

import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.Playground
import org.ironica.simulatte.playground.Direction
import utils.StringRepresentable
import utils.stringRepresentation

/**
 * Player implementation of AbstractCharacter.
 * `playground` field should be injected after initialization otherwise it will be null.
 * This class conforms to StringRepresentable interface.
 */
data class InstantializedPlayer(override val id: Int): AbstractCharacter, StringRepresentable {

    override var dir: Direction = Direction.UP
    override var stamina: Int = 500

    constructor(id: Int, dir: Direction, stamina: Int) : this(id) {
        this.dir = dir
        this.stamina = stamina
    }

    override var playground: Playground? = null

    override var collectedGem = 0
    override var beeperInBag = 0

    override var inWaterForTurns: Int = 0
    override var inLavaForTurns: Int = 0

    override val stringRepresentation: String
        get() = "InstantializedPlayer($id, ${dir.stringRepresentation}, $stamina)"
}