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

data class Player(internal val manager: AbstractManager, internal val id: Int) {

    val isOnGem: Boolean
        get() = manager.isOnGem(id)
    val isOnOpenedSwitch: Boolean
        get() = manager.isOnOpenedSwitch(id)
    val isOnClosedSwitch: Boolean
        get() = manager.isOnClosedSwitch(id)
    val isOnBeeper: Boolean
        get() = manager.isOnBeeper(id)
    val isAtHome: Boolean
        get() = manager.isAtHome(id)
    val isInDesert: Boolean
        get() = manager.isInDesert(id)
    val isInForest: Boolean
        get() = manager.isInForest(id)
    val isOnHill: Boolean
        get() = manager.isOnHill(id)
    val isOnPortal: Boolean
        get() = manager.isOnPortal(id)
    val isOnPlatform: Boolean
        get() = manager.isOnPlatform(id)
    val isBlocked: Boolean
        get() = manager.isBlocked(id)
    val isBlockedLeft: Boolean
        get() = manager.isBlockedLeft(id)
    val isBlockedRight: Boolean
        get() = manager.isBlockedRight(id)
    val collectedGem: Int
        get() = manager.collectedGem(id)

    fun turnLeft() {
        manager.turnLeft(id)
    }

    fun turnRight() {
        manager.turnRight(id)
    }

    fun moveForward() {
        manager.moveForward(id)
    }

    fun collectGem() {
        manager.collectGem(id)
    }

    fun toggleSwitch() {
        manager.toggleSwitch(id)
    }

    fun takeBeeper() {
        manager.takeBeeper(id)
    }

    fun dropBeeper() {
        manager.dropBeeper(id)
    }

    fun dance1() {
        manager.dance(id, 1)
    }

    fun dance2() {
        manager.dance(id, 2)
    }

    fun dance3() {
        manager.dance(id, 3)
    }

    fun dance4() {
        manager.dance(id, 4)
    }

    fun dance5() {
        manager.dance(id, 5)
    }

}
