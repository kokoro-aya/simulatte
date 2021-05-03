package org.ironica.amatsukaze

import java.lang.Exception

interface AbstractManager {
    val playground: Playground
    var consoleLog: String
    var special: String

    val firstId: Int

    fun getPlayer(id: Int): Player {
        val player = playground.players.filter { it.id == id }
        if (player.isEmpty()) throw Exception("No player with given id found")
        return player[0]
    }

    fun printGrid() {
        return playground.printGrid()
    }

    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isOnGem(id: Int) = getPlayer(id).isOnGem
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isOnOpenedSwitch(id: Int) = getPlayer(id).isOnOpenedSwitch
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isOnClosedSwitch(id: Int) = getPlayer(id).isOnClosedSwitch
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isOnBeeper(id: Int) = getPlayer(id).isOnBeeper
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isAtHome(id: Int) = getPlayer(id).isAtHome
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isInDesert(id: Int) = getPlayer(id).isInDesert
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isInForest(id: Int) = getPlayer(id).isInForest
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isOnPortal(id: Int) = getPlayer(id).isOnPortal
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isBlocked(id: Int) = getPlayer(id).isBlocked
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isBlockedLeft(id: Int) = getPlayer(id).isBlockedLeft
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun isBlockedRight(id: Int) = getPlayer(id).isBlockedRight
    @PlaygroundFunction(type = PF.Property, ret = PFType.Bool ,self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun collectedGem(id: Int) = getPlayer(id).collectedGem

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun turnLeft(id: Int) {
        getPlayer(id).turnLeft()
        printGrid()
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
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
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun collectGem(id: Int) {
        getPlayer(id).collectGem()
        printGrid()
        this.special = "GEM"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun toggleSwitch(id: Int) {
        getPlayer(id).toggleSwitch()
        printGrid()
        this.special = "SWITCH"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun takeBeeper(id: Int) {
        getPlayer(id).takeBeeper()
        printGrid()
        this.special = "TAKEBEEPER"
        appendEntry()
    }
    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.None, arg2 = PFType.None)
    fun dropBeeper(id: Int) {
        getPlayer(id).dropBeeper()
        printGrid()
        this.special = "DROPBEEPER"
        appendEntry()
    }

    fun print(lmsg: List<String>) {
        lmsg.forEach { print("$it ")}
        println()
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
        return if (playground.players.isEmpty()) {
            special = "GAMEOVER"
            appendEntry()
            true
        } else false
    }

    fun gemCount(): Int {
        return playground.gemCount()
    }

    fun switchCount(): Int {
        return playground.switchCount()
    }

    fun appendEntry()
}