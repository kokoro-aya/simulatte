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

/**
 * The mixin interface that implements default behaviors of a Character, to be inherited by Player and Specialist.
 * The concept is that this kind of classes serves only as an entrypoint to be called by user.
 * Every method is delegated to their implementation in Managers
 */
interface Character {
    val manager: AbstractManager?
    val id: Int

    val isOnGem: Boolean
        get() = manager?.isOnGem(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isOnOpenedSwitch: Boolean
        get() = manager?.isOnOpenedSwitch(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isOnClosedSwitch: Boolean
        get() = manager?.isOnClosedSwitch(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isOnBeeper: Boolean
        get() = manager?.isOnBeeper(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isOnPortal: Boolean
        get() = manager?.isOnPortal(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isOnPlatform: Boolean
        get() = manager?.isOnPlatform(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isBlocked: Boolean
        get() = manager?.isBlocked(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isBlockedLeft: Boolean
        get() = manager?.isBlockedLeft(id) ?: throw NullPointerException("Character:: uninitialized character")
    val isBlockedRight: Boolean
        get() = manager?.isBlockedRight(id) ?: throw NullPointerException("Character:: uninitialized character")
    val collectedGem: Int
        get() = manager?.collectedGem(id) ?: throw NullPointerException("Character:: uninitialized character")

    fun turnLeft() {
        manager?.turnLeft(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun turnRight() {
        manager?.turnRight(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun moveForward() {
        manager?.moveForward(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun collectGem() {
        manager?.collectGem(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun toggleSwitch() {
        manager?.toggleSwitch(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun takeBeeper() {
        manager?.takeBeeper(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dropBeeper() {
        manager?.dropBeeper(id) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dance1() {
        manager?.dance(id, 1) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dance2() {
        manager?.dance(id, 2) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dance3() {
        manager?.dance(id, 3) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dance4() {
        manager?.dance(id, 4) ?: throw NullPointerException("Character:: uninitialized character")
    }

    fun dance5() {
        manager?.dance(id, 5) ?: throw NullPointerException("Character:: uninitialized character")
    }
}