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
import org.ironica.simulatte.playground.Direction
import org.ironica.simulatte.playground.Playground
import org.ironica.simulatte.playground.datas.Coordinate
import utils.StringRepresentable

/**
 * Declares and implements actions that a character should be capable to do.
 * `playground` field should be injected after initialization otherwise it will be null.
 * This interface conforms to StringRepresentable interface but no default impl. is provided.
 */
interface AbstractCharacter: StringRepresentable {
    val id: Int
    var dir: Direction
    var stamina: Int

    var playground: Playground?

    var collectedGem: Int
    var beeperInBag: Int
    var inWaterForTurns: Int
    var inLavaForTurns: Int

    val walkedTiles: MutableSet<Coordinate>
    var repassed: Boolean

    fun isOnGem() = playground?.playerIsOnGem(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isOnOpenedSwitch() = playground?.playerIsOnOpenedSwitch(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isOnClosedSwitch() = playground?.playerIsOnClosedSwitch(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isOnBeeper() = playground?.playerIsOnBeeper(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun isOnPortal() = playground?.playerIsOnPortal(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isOnPlatform() = playground?.playerIsOnPlatform(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun isBlocked() = playground?.playerIsBlocked(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isBlockedLeft() = playground?.playerIsBlockedLeft(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun isBlockedRight() = playground?.playerIsBlockedRight(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun isBeforeMonster() = playground?.playerIsBeforeMonster(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun turnLeft() = playground?.playerTurnLeft(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun turnRight() = playground?.playerTurnRight(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun moveForward() = playground?.playerMoveForward(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun stepIntoPortal() = playground?.playerStepIntoPortal(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun collectGem() = playground?.playerCollectGem(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")
    fun toggleSwitch() = playground?.playerToggleSwitch(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun takeBeeper() = playground?.playerTakeBeeper(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun dropBeeper() = playground?.playerDropBeeper(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun kill() = playground?.playerKill(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    fun attackMonster() = playground?.playerAttack(this) ?: throw NullPointerException("AbstractCharacter:: Uninitialized character")

    val isAlive: Boolean
        get() = stamina > 0
    val isDead: Boolean
        get() = !isAlive

    override val stringRepresentation: String
        get() = throw NotImplementedError("AbstractCharacter:: String Representation is unsupported on interface")

}