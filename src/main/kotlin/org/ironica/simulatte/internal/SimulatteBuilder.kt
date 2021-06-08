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
import org.ironica.simulatte.misc.manager
import org.ironica.simulatte.payloads.Payload
import org.ironica.simulatte.payloads.returnedPayloads
import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.Playground

/**
 * The DSL Builder class that serves as the carrier of user code
 * Every methods or properties exposed to user must have an implementation here, or an initializer to the object that carries them
 *
 */
class SimulatteBuilder(private val manager: AbstractManager) {

    /**
     * Usage of companion object ensures that this world is a singleton
     */
    companion object {

        var singletonWorld: World? = null

        var singletonConsole: Console? = null
        var lastSb: SimulatteBuilder? = null // Check if last Builder is the same as supplied Builder, so that it could be renewed
        // each time the builder changes, however, manager might be the same.

        fun world(sb: SimulatteBuilder): World {
            if (sb.manager is CreativeManager && (singletonWorld == null || lastSb != sb))
                singletonWorld = World(sb.manager)
            return singletonWorld ?: throw NullPointerException("SimulatteBuilder:: null world encountered, you might be not in creative game mode")
        }

        fun console(sb: SimulatteBuilder): Console {
            if (singletonConsole == null || lastSb != sb)
                singletonConsole = Console(sb.manager)
            return singletonConsole ?: throw NullPointerException("SimulatteBuilder:: null world encountered, this should not happen")
        }
    }

    // Begin of exposed APIs //

    /**
     * The world object that reflects the playground and allow to interact with
     */
    val world: World
        get() = world(this)

    val console: Console
        get() = console(this)

    /**
     * Initialize a new player using available slot
     * @throws NoSuchElementException if no more slot left
     */
    fun Player(): Player {
        return Player(manager, manager.nextPlayerId)
    }

    /**
     * Safe version of initializing a new player using available slot
     * Returns null if no more slot left
     */
    fun PlayerOrNull(): Player? {
        return manager.nextPlayerIdOrNull?.let { Player(manager, it) }
    }

    /**
     * Initializing a new specialist using available slot
     * @throws NoSuchElementException if no more slot left
     */
    fun Specialist(): Specialist {
        return Specialist(manager, manager.nextSpecialistId)
    }

    /**
     * Safe version of initializing a new specialist using available slot
     * Returns null if no more slot left
     */
    fun SpecialistOrNull(): Specialist? {
        return manager.nextSpecialistIdOrNull?.let { Specialist(manager, it) }
    }

    /**
     * Call the Gem object
     */
    fun Gem(): Gem {
        return Gem
    }

    /**
     * Call the Beeper object
     */
    fun Beeper(): Beeper {
        return Beeper
    }

    /**
     * Instantialize the SwitchP object
     */
    fun Switch(on: Boolean): Switch {
        return Switch(on, "#Switch")
    }

    /**
     * Instantialize the PortalP object
     */
    fun Portal(color: Color = Color.WHITE): Portal {
        return Portal(color, "#Portal")
    }

    /**
     * Instantialize the BlockP object
     */
    fun Block(blocked: Boolean = false): Block {
        return Block(blocked, "#Block")
    }

    /**
     * Call the Stair object
     */
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

    fun kill(char: Character) {
        manager.kill(char.id)
    }

    // End of Exposed APIs //

    /**
     * To be used in eval runner and cocoa.
     * Return a list of Payload to be passed into the returning procedure of eval runner.
     * We do this because eval runs on a different thread and we cannot get resources of this thread from ktor thread
     */
    fun run(): List<Payload> {
        return returnedPayloads()
    }

    /**
     * To be used in tests.
     * Return a Playground instance to be exposed to the test
     */
    fun test(): Playground {
        return manager.playground
    }
}

/**
 * Receives a playground manager to be passed into SimulatteBuilder, and a callback that should be the user code.
 * use `.apply(initializer)` method to pass initializer, which will be evaluated in the context of SimulatteBuilder.
 */
fun play(manager: AbstractManager, initializer: SimulatteBuilder.() -> Unit): SimulatteBuilder {
    return SimulatteBuilder(manager).apply(initializer)
}