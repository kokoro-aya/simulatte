/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.manager

import org.ironica.simulatte.internal.play
import org.ironica.simulatte.payloads.*
import org.ironica.simulatte.playground.*
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.characters.InstantializedPlayer
import org.ironica.simulatte.playground.characters.InstantializedSpecialist
import org.ironica.simulatte.playground.datas.*

/**
 * The mixin interface that implements logics handling players, playground interactions and persistence of current playground frame.
 * This interface handles player actions and delegates them to playground implementations.
 * The appendEntry() method is called after each action to ensure that the current frame is saved into payload.
 */
interface AbstractManager {
    val playground: Playground
    var consoleLog: String
    var special: String

    val firstId: Int // Conventionally all character's first id must be 0
    val debug: Boolean
    val stdout: Boolean

    var attributedPlayerId: Int
    var attributedSpecialistId: Int

    val nextPlayerId: Int
        get() = nextPlayerIdOrNull ?: throw NoSuchElementException("AbstractManager:: No slot for new player available")

    val nextPlayerIdOrNull: Int?
        get() = playground.characters.keys.filterIsInstance<InstantializedPlayer>().firstOrNull { it.id > attributedPlayerId }?.id?.also {
            attributedPlayerId = it
        }

    val nextSpecialistId: Int
        get() = nextSpecialistIdOrNull ?: throw NoSuchElementException("AbstractManager:: No slot for new specialist available")

    val nextSpecialistIdOrNull: Int?
        get() = playground.characters.keys.filterIsInstance<InstantializedSpecialist>().firstOrNull { it.id > attributedSpecialistId }?.id?.also {
            attributedSpecialistId = it
        }

    val lastPlayerId: Int
        get() = playground.characters.keys.maxOfOrNull { it.id } ?: -1

    fun getPlayer(id: Int): AbstractCharacter {
        val player = playground.characters.keys.filter { it.id == id }
        if (player.isEmpty()) throw Exception("No player with given id found")
        return player[0]
    }

    fun printGrid() {
        if (debug) return playground.printGrid()
        return
    }

    fun isAlive(id: Int) = getPlayer(id).isAlive

    fun isOnGem(id: Int) = getPlayer(id).isOnGem()
    fun isOnOpenedSwitch(id: Int) = getPlayer(id).isOnOpenedSwitch()
    fun isOnClosedSwitch(id: Int) = getPlayer(id).isOnClosedSwitch()
    fun isOnBeeper(id: Int) = getPlayer(id).isOnBeeper()
    fun isOnPortal(id: Int) = getPlayer(id).isOnPortal()
    fun isOnPlatform(id: Int) = getPlayer(id).isOnPlatform()
    fun isBlocked(id: Int) = getPlayer(id).isBlocked()
    fun isBlockedLeft(id: Int) = getPlayer(id).isBlockedLeft()
    fun isBlockedRight(id: Int) = getPlayer(id).isBlockedRight()
    fun collectedGem(id: Int) = getPlayer(id).collectedGem
    fun collectedBeeper(id: Int) = getPlayer(id).beeperInBag

    fun isBeforeMonster(id: Int) = getPlayer(id).isBeforeMonster()

    fun isBeforeLock(id: Int) = getPlayer(id).isBeforeLock()

    fun turnLeft(id: Int) {
        getPlayer(id).turnLeft()
        printGrid()
        appendEntry()
    }
    fun turnRight(id: Int) {
        getPlayer(id).turnRight()
        printGrid()
        appendEntry()
    }
    fun moveForward(id: Int) {
        getPlayer(id).moveForward()
        printGrid()
        appendEntry()
        if (getPlayer(id).isOnPortal()) {
            getPlayer(id).stepIntoPortal()
            printGrid()
            appendEntry()
        }
    }
    fun collectGem(id: Int) {
        getPlayer(id).collectGem()
        printGrid()
        this.special += "GEM "
        appendEntry()
    }
    fun toggleSwitch(id: Int) {
        getPlayer(id).toggleSwitch()
        printGrid()
        this.special += "SWITCH "
        appendEntry()
    }
    fun takeBeeper(id: Int) {
        getPlayer(id).takeBeeper()
        printGrid()
        this.special += "TAKEBEEPER "
        appendEntry()
    }
    fun dropBeeper(id: Int) {
        getPlayer(id).dropBeeper()
        printGrid()
        this.special += "DROPBEEPER "
        appendEntry()
    }

    /**
     * This method provide a log() method for the DSL
     * Only by calling this log() method you could append your frame into the data.
     */
    fun log(vararg arg: Any) {
        if (stdout) {
            for (x in arg) {
                print("$x ")
            }
        }
        for (x in arg) {
            consoleLog += "$x "
        }
        appendEntry()
    }

    fun win() {
        if (playground.win()) {
            appendEntry()
        }
    }

    fun dead() {
        if (playground.lose()) {
            appendEntry()
        }
    }

    fun dance(id: Int, action: Int) {
        special += "PLAYER@${id}#DANCE#$action "
        playground.incrementATurn()
        appendEntry()
    }

    fun gemCount(): Int {
        return playground.allGemCollected
    }

    fun gemLeft(): Int {
        return playground.allGemLeft
    }

    fun beeperCount(): Int {
        return playground.allBeeperCollected
    }

    fun beeperLeft(): Int {
        return playground.allBeeperLeft
    }

    fun switchCount(): Int {
        return playground.allOpenedSwitch
    }

    fun closedSwitchCount(): Int {
        return playground.allClosedSwitch
    }

    fun kill(id: Int) {
        getPlayer(id).kill()
    }

    fun attackMonster(id: Int) {
        getPlayer(id).attackMonster()
        printGrid()
        this.special += "ATTACK "
        appendEntry()
    }

    fun turnLockUp(id: Int) {
        if (getPlayer(id) !is InstantializedSpecialist) throw Exception("AbstractManager:: Only specialist could turn locks up")
        (getPlayer(id) as InstantializedSpecialist).turnLockUp()
        printGrid()
        appendEntry()
    }
    fun turnLockDown(id: Int) {
        if (getPlayer(id) !is InstantializedSpecialist) throw Exception("AbstractManager:: Only specialist could turn locks down")
        (getPlayer(id) as InstantializedSpecialist).turnLockDown()
        printGrid()
        appendEntry()
    }

    fun appendEntry() {
        if (payloadStorage.get().size > 5000)
            throw Exception("Too many entries!")

        with (playground.squares) {
            // Usage of map/mapIndexed to create new instances of data instead of passing the original data
            // so that they will be deep-copied into payload
            val currentGrid = this.map { it.map {
                SerializedBlock(
                    when (it.block) {
                        OpenBlock -> Blocks.OPEN
                        BlockedBlock -> Blocks.BLOCKED
                        VoidBlock -> Blocks.VOID
                        is StairBlock -> Blocks.STAIR
                        is LockBlock -> Blocks.LOCK
                    }, it.level
                )
            } }

            val flattenGridToCoors =this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq }
            }.flatten()

            val gems = flattenGridToCoors.filter { it.second.gem != null }.map { it.first }
            val beepers = flattenGridToCoors.filter { it.second.beeper != null }.map { it.first }
            val switches = flattenGridToCoors.filter { it.second.switch != null }.map {
                SerializedSwitch(it.first, it.second.switch!!.on)
            }
            val portals = flattenGridToCoors.filter { it.second.portal != null }.map {
                with (it.second.portal!!) {
                    SerializedPortal(it.first, this.dest, this.color, this.energy)
                }
            }
            val monsters = flattenGridToCoors.filter { it.second.monster == true }.map { it.first }

            val platforms = flattenGridToCoors.filter { it.second.platform != null }.map {
                SerializedPlatform(it.first, it.second.platform!!.level)
            }
            val locks = playground.locks.map { SerializedLock(it.key, it.value.isActive, it.value.energy) }
            val players = playground.characters.filter { it.key.isAlive }.map { // send only alive players to front-end
                val (x, y) = it.value
                with (it.key) {
                    SerializedPlayer(
                        this.id, x, y, this.dir,
                        if (this is InstantializedSpecialist) Role.SPECIALIST else Role.PLAYER,
                        this.stamina,
                        this.collectedGem,
                        this.beeperInBag,
                    )
                }
            }


            val payload = Payload(
                currentGrid,
                gems, beepers, switches, portals, platforms, locks, monsters,
                players, this@AbstractManager.consoleLog, this@AbstractManager.special,
                playground.playgroundCurrentTurn,
            )

            payloadStorage.get().add(payload)
            this@AbstractManager.special = ""
        }
    }
}