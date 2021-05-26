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

import org.ironica.simulatte.playground.*

class ColorfulManager(override val playground: Playground, override val debug: Boolean, override val stdout: Boolean):
    AbstractManager {

    override var consoleLog: String = ""
    override var special: String = ""

    override val firstId = playground.characters.keys.map { it.id }.sorted()[0]
    override var attributedPlayerId: Int = -1
    override var attributedSpecialistId: Int = -1

    override fun turnLockDown(id: Int) {
        throw NotImplementedError("ColorfulManager:: This action is not supported")
    }

    override fun turnLockUp(id: Int) {
        throw NotImplementedError("ColorfulManager:: This action is not supported")
    }

    override fun printGrid() {
        if (debug) {
            playground.squares.forEach { line ->
                line.forEach { tile ->
                    val t = tile.color
                    print(
                        when (t) {
                            Color.WHITE -> '白'
                            Color.BLACK -> '黑'
                            Color.SILVER -> '银'
                            Color.GREY -> '灰'
                            Color.RED -> '红'
                            Color.ORANGE -> '橙'
                            Color.GOLD -> '金'
                            Color.PINK -> '粉'
                            Color.YELLOW -> '黄'
                            Color.BEIGE -> '米'
                            Color.BROWN -> '棕'
                            Color.GREEN -> '绿'
                            Color.AZURE -> '碧'
                            Color.CYAN -> '青'
                            Color.ALICEBLUE -> '蓝'
                            Color.PURPLE -> '紫'
                        }
                    )
                }
                println()
            }
            println()
        }
    }
}
