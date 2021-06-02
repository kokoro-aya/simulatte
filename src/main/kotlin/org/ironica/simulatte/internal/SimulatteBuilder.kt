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
import org.ironica.simulatte.manager.CreativeManager
import org.ironica.simulatte.payloads.Payload
import org.ironica.simulatte.payloads.returnedPayloads
import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.Playground

class SimulatteBuilder(private val manager: AbstractManager) {

    companion object {

        var singletonWorld: World? = null

        fun world(sb: SimulatteBuilder): World {
            if (sb.manager is CreativeManager && singletonWorld == null)
                singletonWorld = World(sb.manager)
            return singletonWorld ?: throw NullPointerException("SimulatteBuilder:: null world encountered, you might be not in creative game mode")
        }
    }

    val world: World
        get() = world(this)

    fun Player(): Player {
        return Player(manager, manager.nextPlayerId)
    }

    fun Specialist(): Specialist {
        return Specialist(manager, manager.nextPlayerId)
    }

    fun Gem(): Gem {
        return Gem
    }

    fun Beeper(): Beeper {
        return Beeper
    }

    fun Switch(on: Boolean): SwitchP {
        return SwitchP(on)
    }

    fun Portal(color: Color = Color.WHITE): PortalP {
        return PortalP(color)
    }

    fun Block(blocked: Boolean = false): BlockP {
        return BlockP(blocked)
    }

    fun Stair(): Stair {
        return Stair
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

    fun run(): List<Payload> {
        return returnedPayloads()
    }

    fun test(): Playground {
        return manager.playground
    }
}

fun play(manager: AbstractManager, initializer: SimulatteBuilder.() -> Unit): SimulatteBuilder {
    return SimulatteBuilder(manager).apply(initializer)
}