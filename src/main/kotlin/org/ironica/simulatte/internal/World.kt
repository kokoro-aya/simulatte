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

import org.ironica.simulatte.manager.CreativeManager
import org.ironica.simulatte.playground.Direction
import org.ironica.simulatte.playground.Role
import org.ironica.simulatte.playground.datas.Coordinate

data class World(internal val manager: CreativeManager) {

    fun place(item: Item, at: Coordinate) = manager.worldPlace(item, at)
    fun place(block: Block, at: Coordinate) = manager.worldPlace(block, at)
    fun place(portal: Portal, atStart: Coordinate, atEnd: Coordinate) = manager.worldPlace(portal, atStart, atEnd)
    fun place(stair: Stair, facing: Direction, at: Coordinate) = manager.worldPlace(stair, facing, at)
    fun placeCharacter(kind: Role, at: Coordinate) = manager.worldPlaceCharacter(kind, at)

    val allPossibleCoordinates: Array<Coordinate>
        get() = manager.worldAllPossibleCoordinates

    fun removeAllBlocks(at: Coordinate) = manager.worldRemoveAllBlocks(at)

    fun existingCharacters(at: Array<Coordinate>): Array<Character> {
        return manager.worldExistingCharacters(at)
    }

    fun digDown(at: Coordinate) = manager.worldDigDown(at)

    fun pileUp(at: Coordinate) = manager.worldPileUp(at)
}