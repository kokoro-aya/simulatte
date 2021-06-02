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
        get() {
            val next = playground.characters.keys.filterIsInstance<InstantializedPlayer>().firstOrNull { it.id > attributedPlayerId }?.id
                ?: throw NoSuchElementException("AbstractManager:: No slot for new player available")
            attributedPlayerId = next
            return next
        }

    val nextSpecialistId: Int
        get() {
            val next = playground.characters.keys.filterIsInstance<InstantializedSpecialist>().firstOrNull { it.id > attributedSpecialistId }?.id
                ?: throw NoSuchElementException("AbstractManager:: No slot for new specialist available")
            attributedSpecialistId = next
            return next
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

    fun turnLeft(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).turnLeft()
            printGrid()
            appendEntry()
        }
    }
    fun turnRight(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).turnRight()
            printGrid()
            appendEntry()
        }
    }
    fun moveForward(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).moveForward()
            printGrid()
            appendEntry()
            if (getPlayer(id).isOnPortal()) {
                getPlayer(id).stepIntoPortal()
                printGrid()
                appendEntry()
            }
        }
    }
    fun collectGem(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).collectGem()
            printGrid()
            this.special += "GEM "
            appendEntry()
        }
    }
    fun toggleSwitch(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).toggleSwitch()
            printGrid()
            this.special += "SWITCH "
            appendEntry()
        }
    }
    fun takeBeeper(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).takeBeeper()
            printGrid()
            this.special += "TAKEBEEPER "
            appendEntry()
        }
    }
    fun dropBeeper(id: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            getPlayer(id).dropBeeper()
            printGrid()
            this.special += "DROPBEEPER "
            appendEntry()
        }
    }

    fun print(lmsg: List<String>) {
        if (statusStorage.get() == GameStatus.PENDING) {
            if (stdout) {
                lmsg.forEach { print("$it ") }
                println()
            }
            lmsg.forEach { consoleLog += it }
            consoleLog += "\n"
            appendEntry()
        }
    }

    fun win() {
        if (statusStorage.get() == GameStatus.PENDING) {
            if (playground.win()) {
                appendEntry()
            }
        }
    }

    fun dead() {
        if (statusStorage.get() == GameStatus.PENDING) {
            if (playground.lose()) {
                appendEntry()
            }
        }
    }

    fun dance(id: Int, action: Int) {
        if (statusStorage.get() == GameStatus.PENDING) {
            special += "PLAYER@${id}#DANCE#$action "
            playground.incrementATurn()
            appendEntry()
        }
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
            val currentGrid = this.map { it.map {
                SerializedBlock(
                    when (it.block) {
                        Open -> Blocks.OPEN
                        Blocked -> Blocks.BLOCKED
                        Void -> Blocks.VOID
                        is StairBlock -> Blocks.STAIR
                        is LockBlock -> Blocks.LOCK
                    }, it.level
                )
            } }
            val currentLevels = this.map { it.map { it.level } }
            val gems = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.gem }.filter { it.second != null }
            }.flatten().map { it.first }
            val beepers = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.beeper }.filter { it.second != null }
            }.flatten().map { it.first }
            val switches = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.switch }.filter { it.second != null }
            }.flatten().map { SerializedSwitch(it.first, it.second!!.on) }
            val portals = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.portal }.filter { it.second != null }
            }.flatten().map { SerializedPortal(it.first, it.second!!.coo, it.second!!.isActive, it.second!!.energy) }
            val platforms = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.platform }.filter { it.second != null }
            }.flatten().map { SerializedPlatform(it.first, it.second!!.level) }
            val locks = playground.locks.map { SerializedLock(it.key, it.value.isActive, it.value.energy) }
            val players = playground.characters.map {
                val (x, y) = it.value
                with (it.key) {
                    SerializedPlayer(
                        this.id, x, y, this.dir,
                        if (this is InstantializedSpecialist) Role.SPECIALIST else Role.PLAYER,
                        this.stamina,
                    )
                }
            }
            val payload = Payload(
                currentGrid,
                gems, beepers, switches, portals, platforms, locks,
                players, this@AbstractManager.consoleLog, this@AbstractManager.special
            )

            payloadStorage.get().add(payload)
            this@AbstractManager.special = ""
        }
    }
}