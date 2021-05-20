/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.playground.characters

import org.ironica.amatsukaze.bridge.PortalData
import org.ironica.amatsukaze.bridge.StairData
import org.ironica.amatsukaze.playground.Color
import org.ironica.amatsukaze.playground.Playground
import org.ironica.amatsukaze.playground.data.*
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Direction
import org.ironica.amatsukaze.playground.Items

open class Player(val id: Int, var dir: Direction, var stamina: Int) {

    lateinit var playground: Playground

    var collectedGem = 0
    var beeperInBag = 0

    var inWaterForTurns: Int = 0
    var inLavaForTurns: Int = 0
    var hasJustSteppedIntoPortal = false

    val isOnGem = { playground.playerIsOnGem(this) }
    val isOnOpenedSwitch = { playground.playerIsOnOpenedSwitch(this) }
    val isOnClosedSwitch = { playground.playerIsOnClosedSwitch(this) }
    val isOnBeeper = { playground.playerIsOnBeeper(this) }

    val isAtHome = { playground.playerIsAtHome(this) }
    val isInDesert = { playground.playerIsInDesert(this) }
    val isInForest = { playground.playerIsInForest(this) }
    val isOnHill = { playground.playerIsOnHill(this) }

    val isOnPortal = { playground.playerIsOnPortal(this) }
    val isOnPlatform = { playground.playerIsOnPlatform(this) }

    val isBlocked = { playground.playerIsBlocked(this) }
    val isBlockedLeft = { playground.playerIsBlockedLeft(this) }
    val isBlockedRight = { playground.playerIsBlockedRight(this) }

    fun turnLeft() = playground.playerTurnLeft(this)

    fun turnRight() = playground.playerTurnRight(this)

    fun moveForward() = playground.playerMoveForward(this)
    fun stepIntoPortal() = playground.playerStepIntoPortal(this)
    fun collectGem() = playground.playerCollectGem(this)
    fun toggleSwitch() = playground.playerToggleSwitch(this)

    fun takeBeeper() = playground.playerTakeBeeper(this)

    fun dropBeeper() = playground.playerDropBeeper(this)

    fun changeColor(c: Color) = playground.playerChangeColor(this, c)

    fun kill() = playground.playerKill(this)

    fun dance1(): Boolean {
        // TODO
        return true
    }

    fun dance2(): Boolean {
        // TODO
        return true
    }

    fun dance3(): Boolean {
        // TODO
        return true
    }

    fun dance4(): Boolean {
        // TODO
        return true
    }

    fun dance5(): Boolean {
        // TODO
        return true
    }

    val isAlive: Boolean
        get() = stamina > 0
    val isDead: Boolean
        get() = !isAlive
}