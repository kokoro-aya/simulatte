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
import org.ironica.amatsukaze.playground.data.Portal
import org.ironica.amatsukaze.playground.enums.Block
import org.ironica.amatsukaze.playground.enums.Item
import org.ironica.amatsukaze.playground.enums.Role
import org.ironica.amatsukaze.playground.payloads.Payload
import org.ironica.amatsukaze.playground.payloads.SerializedPlayer
import org.ironica.amatsukaze.playground.payloads.SerializedPlayground
import org.ironica.amatsukaze.playground.payloads.payloadStorage
import org.ironica.amatsukaze.playground.characters.Specialist

class MountainousManager(override val playground: Playground, override val debug: Boolean,
                         override val stdout: Boolean): AbstractManager {

    override var consoleLog: String = ""
    override var special: String = ""

    override val firstId = playground.players.map { it.id }.sorted()[0]

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

    override fun appendEntry() {
        if (payloadStorage.size > 1000)
            throw Exception("Too many entries!")
        val currentGrid = MutableList(playground.grid.size) { MutableList(playground.grid[0].size) { Block.OPEN } }
        for (i in playground.grid.indices)
            for (j in playground.grid[0].indices)
                currentGrid[i][j] = playground.grid[i][j]
        val currentLayout = MutableList(playground.itemLayout.size) { MutableList(playground.itemLayout[0].size) { Item.NONE } }
        for (i in playground.itemLayout.indices)
            for (j in playground.itemLayout[0].indices)
                currentLayout[i][j] = playground.itemLayout[i][j]
        val currentColorLayout = List(playground.tileLayout.size) { List(playground.tileLayout[0].size) { Color.WHITE } }
        val currentLevelLayout = MutableList(playground.tileLayout.size) { MutableList(playground.tileLayout[0].size) { 1 } }
        for (i in playground.tileLayout.indices)
            for (j in playground.tileLayout[0].indices) {
                currentLevelLayout[i][j] = playground.tileLayout[i][j].level
            }
        val currentPortals = MutableList(playground.portals.size) { Portal() }
        for (i in playground.portals.indices)
            currentPortals[i] = playground.portals[i]
        val serializedPlayers = playground.players.map {
            SerializedPlayer(it.id, it.coo.x, it.coo.y, it.dir, if (it is Specialist) Role.SPECIALIST else Role.PLAYER, it.stamina ?: 0) }
        val payload = Payload(
            serializedPlayers,
            currentPortals,
            SerializedPlayground(currentGrid, currentLayout, currentColorLayout, currentLevelLayout),
            this.consoleLog,
            this.special
        )
        payloadStorage.add(payload)
        this.special = ""
    }

}