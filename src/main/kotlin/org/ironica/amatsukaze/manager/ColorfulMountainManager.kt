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

import org.ironica.amatsukaze.internal.PF
import org.ironica.amatsukaze.internal.PFType
import org.ironica.amatsukaze.internal.PlaygroundFunction
import org.ironica.amatsukaze.playground.*
import org.ironica.amatsukaze.bridge.PortalData
import org.ironica.amatsukaze.payloads.*
import org.ironica.amatsukaze.playground.Blocks
import org.ironica.amatsukaze.playground.Items
import org.ironica.amatsukaze.playground.Role
import org.ironica.amatsukaze.playground.characters.Specialist
import org.ironica.amatsukaze.playground.data.*

class ColorfulMountainManager(override val playground: Playground, override val debug: Boolean, override val stdout: Boolean):
    AbstractManager {

    override var consoleLog: String = ""
    override var special: String = ""

    override val firstId = playground.characters.keys.map { it.id }.sorted()[0]

    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isOnGem(id: Int) = getPlayer(id).isOnGem
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isOnOpenedSwitch(id: Int) = getPlayer(id).isOnOpenedSwitch
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isOnClosedSwitch(id: Int) = getPlayer(id).isOnClosedSwitch
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isOnBeeper(id: Int) = getPlayer(id).isOnBeeper
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isAtHome(id: Int) = getPlayer(id).isAtHome
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isInDesert(id: Int) = getPlayer(id).isInDesert
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isInForest(id: Int) = getPlayer(id).isInForest
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isOnPortal(id: Int) = getPlayer(id).isOnPortal
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isBlocked(id: Int) = getPlayer(id).isBlocked
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isBlockedLeft(id: Int) = getPlayer(id).isBlockedLeft
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun isBlockedRight(id: Int) = getPlayer(id).isBlockedRight
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun collectedGem(id: Int) = getPlayer(id).collectedGem

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun turnLeft(id: Int) {
        getPlayer(id).turnLeft()
        printGrid()
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun moveForward(id: Int) {
        getPlayer(id).moveForward()
        printGrid()
        appendEntry()
        if (getPlayer(id).isOnPortal()) {
            getPlayer(id).stepIntoPortal()
            printGrid()
            appendEntry()
        }
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun collectGem(id: Int) {
        getPlayer(id).collectGem()
        printGrid()
        this.special = "GEM"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun toggleSwitch(id: Int) {
        getPlayer(id).toggleSwitch()
        printGrid()
        this.special = "SWITCH"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun takeBeeper(id: Int) {
        getPlayer(id).takeBeeper()
        printGrid()
        this.special = "TAKEBEEPER"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    override fun dropBeeper(id: Int) {
        getPlayer(id).dropBeeper()
        printGrid()
        this.special = "DROPBEEPER"
        appendEntry()
    }

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun turnLockUp(id: Int) {
        if (getPlayer(id) !is Specialist) throw Exception("Only specialist could turn locks up")
        (getPlayer(id) as Specialist).turnLockUp()
        printGrid()
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun turnLockDown(id: Int) {
        if (getPlayer(id) !is Specialist) throw Exception("Only specialist could turn locks down")
        (getPlayer(id) as Specialist).turnLockDown()
        printGrid()
        appendEntry()
    }

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.Color, arg2 = PFType.None)
    fun changeColor(id: Int, c: Color) {
        getPlayer(id).changeColor(c)
        printGrid()
        appendEntry()
    }


//    override fun printGrid() {
        // TODO ("TBA")
//    }

    override fun appendEntry() {
        if (payloadStorage.get().size > 1000)
            throw Exception("Too many entries!")

        with (playground.squares) {
            val currentGrid = this.map { it.map {
                when (it.block) {
                    Desert -> Blocks.DESERT
                    Hill -> Blocks.HILL
                    is Home -> Blocks.HOME
                    is Lock -> Blocks.LOCK
                    Mountain -> Blocks.MOUNTAIN
                    Open -> Blocks.OPEN
                    is Stair -> Blocks.STAIR
                    Stone -> Blocks.STONE
                    Tree -> Blocks.TREE
                    Void -> Blocks.VOID
                    Water -> Blocks.WATER
                }
            } }
            val currentColors = this.map { it.map { it.color } }
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
            }.flatten().map { SerializedPortalOrLock(it.first, it.second!!.isActive, it.second!!.energy) }
            val platforms = this.mapIndexed { i, line ->
                line.mapIndexed { j, sq -> Coordinate(j, i) to sq.platform }.filter { it.second != null }
            }.flatten().map { SerializedPlatform(it.first, it.second!!.level) }
            val locks = playground.locks.map { SerializedPortalOrLock(it.key, it.value.isActive, it.value.energy) }
            val players = playground.characters.map {
                val (x, y) = it.value
                with (it.key) {
                    SerializedPlayer(
                        this.id, x, y, this.dir,
                        if (this is Specialist) Role.SPECIALIST else Role.PLAYER,
                        this.stamina,
                    )
                }
            }
            val payload = Payload(
                currentGrid, currentColors, currentLevels,
                gems, beepers, switches, portals, platforms, locks,
                players, this@ColorfulMountainManager.consoleLog, this@ColorfulMountainManager.special
            )

            payloadStorage.get().add(payload)
            this@ColorfulMountainManager.special = ""
        }
    }
}
