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
import org.ironica.simulatte.playground.GameStatus

class SimulatteBuilder(private val manager: AbstractManager) {

    fun Player(): Player {
        return Player(manager, manager.nextPlayerId)
    }

    fun Specialist(): Specialist {
        return Specialist(manager, manager.nextPlayerId)
    }

    val allGemCollected: Int
        get() = manager.gemCount()

    val allGemLeft: Int
        get() = manager.gemLeft()

    val allBeeperCollected: Int
        get() = manager.beeperCount()

    val allBeeperLeft: Int
        get() = manager.beeperLeft()

    val allOpenedSwitch: Int
        get() = manager.switchCount()
    val allClosedSwitch: Int
        get() = manager.closedSwitchCount()

    fun kill(player: Player) {
        manager.kill(player.id)
    }

    fun start(): GameStatus {
        return manager.playground.status
    }
}

fun play(manager: AbstractManager, initializer: SimulatteBuilder.() -> Unit): SimulatteBuilder {
    return SimulatteBuilder(manager).apply(initializer)
}