/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.manager

import org.ironica.amatsukaze.playground.characters.Player
import org.ironica.amatsukaze.playground.Playground

interface AbstractManager {
    val playground: Playground
    var consoleLog: String
    var special: String

    val firstId: Int
    val debug: Boolean
    val stdout: Boolean

    fun getPlayer(id: Int): Player {
        val player = playground.characters.keys.filter { it.id == id }
        if (player.isEmpty()) throw Exception("No player with given id found")
        return player[0]
    }

    fun printGrid() {
        if (debug) return playground.printGrid()
        return
    }

    fun isOnGem(id: Int): () -> Boolean
    fun isOnOpenedSwitch(id: Int): () -> Boolean
    fun isOnClosedSwitch(id: Int): () -> Boolean
    fun isOnBeeper(id: Int): () -> Boolean
    fun isAtHome(id: Int): () -> Boolean
    fun isInDesert(id: Int): () -> Boolean
    fun isInForest(id: Int): () -> Boolean
    fun isOnPortal(id: Int): () -> Boolean
    fun isBlocked(id: Int): () -> Boolean
    fun isBlockedLeft(id: Int): () -> Boolean
    fun isBlockedRight(id: Int): () -> Boolean
    fun collectedGem(id: Int): Int

    fun turnLeft(id: Int)
    fun moveForward(id: Int)
    fun collectGem(id: Int)
    fun toggleSwitch(id: Int)
    fun takeBeeper(id: Int)
    fun dropBeeper(id: Int)

    fun print(lmsg: List<String>) {
        if (stdout) {
            lmsg.forEach { print("$it ") }
            println()
        }
        lmsg.forEach { consoleLog += it }
        consoleLog += "\n"
        appendEntry()
    }

    fun win(): Boolean {
        return if (playground.win()) {
            special = "WIN"
            appendEntry()
            true
        } else false
    }

    fun dead(): Boolean {
        return if (playground.characters.keys.all { it.isDead }) {
            special = "GAMEOVER"
            appendEntry()
            true
        } else false
    }

    fun gemCount(): Int {
        return playground.allGemCollected
    }

    fun switchCount(): Int {
        return playground.allOpenedSwitch
    }

    fun appendEntry()
}