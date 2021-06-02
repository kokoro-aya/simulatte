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

import org.ironica.simulatte.internal.*
import org.ironica.simulatte.payloads.statusStorage
import org.ironica.simulatte.playground.Direction
import org.ironica.simulatte.playground.GameStatus
import org.ironica.simulatte.playground.Playground
import org.ironica.simulatte.playground.Role
import org.ironica.simulatte.playground.datas.Coordinate

class CreativeManager(override val playground: Playground, override val debug: Boolean, override val stdout: Boolean):
    AbstractManager{
    override var consoleLog: String = ""
    override var special: String = ""
    override val firstId = playground.characters.keys.map { it.id }.sorted()[0]
    override var attributedPlayerId: Int = -1
    override var attributedSpecialistId: Int = -1

    fun worldPlace(item: Item, at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldPlace(item, at)
            printGrid()
            this.special += "PLACE#${when (item) {
                Beeper -> "BEEPER"
                Gem -> "GEM"
                is SwitchP -> "SWITCH"
            }}@(${at.x}, ${at.y}) "
            appendEntry()
        }
    }

    fun worldPlace(block: BlockP, at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldPlace(block, at)
            printGrid()
            this.special += "PLACE#BLOCK(${if (block.blocked) "BLOCKED" else "OPEN"})@(${at.x}, ${at.y})"
            appendEntry()
        }
    }

    fun worldPlace(portal: PortalP, atStart: Coordinate, atEnd: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldPlace(portal, atStart, atEnd)
            printGrid()
            this.special += "PLACE#PORTAL:@(${atStart.x},${atStart.y}):@(${atEnd.x},${atEnd.y})"
            appendEntry()
        }
    }

    fun worldPlace(stair: Stair, facing: Direction, at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldPlace(stair, facing, at)
            printGrid()
            this.special += "PLACE#STAIR:$facing:@(${at.x},${at.y})"
            appendEntry()
        }
    }

    fun worldPlaceCharacter(kind: Role, facing: Direction, at: Coordinate): Character {
        val char = when (kind) {
            Role.PLAYER -> Player(this, lastPlayerId + 1)
            Role.SPECIALIST -> Specialist(this, lastPlayerId + 1)
        }
        playground.worldPlaceCharacter(char, facing, at)
        return char
    }

    val worldAllPossibleCoordinates: Array<Coordinate>
        get() = playground.worldAllPossibleCoordinates

    fun worldRemoveAllBlocks(at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldRemoveAllBlocks(at)
            printGrid()
            this.special += "REMOVEBLOCKS#@(${at.x},${at.y})"
            appendEntry()
        }
    }

    fun worldExistingCharacters(at: Array<Coordinate>): Array<Character> {
        val ans = playground.worldExistingCharacters(at)
        ans.forEach { when (it) {
            is Player -> it.manager = this
            is Specialist -> it.manager = this
        } }
        return ans
    }

    fun worldDigDown(at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldDigDown(at)
            printGrid()
            this.special += "DIGDOWN#@(${at.x},${at.y})"
            appendEntry()
        }
    }

    fun worldPileUp(at: Coordinate) {
        if (statusStorage.get() == GameStatus.PENDING) {
            playground.worldPileUp(at)
            printGrid()
            this.special += "PILEUP#@(${at.x},${at.y})"
            appendEntry()
        }
    }
}
