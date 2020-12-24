package org.ironica.amatsukaze

class AmatsukazeManager(val playground: Playground) {

    private var consoleLog = ""
    private var special = ""

    init {
        appendEntry()
    }

    val firstId = playground.players.map { it.id }.sorted()[0]

    private fun getPlayer(id: Int): Player {
        val player = playground.players.filter { it.id == id }
        if (player.isEmpty()) throw Exception("No player with given id found")
        return player[0]
    }

    fun isOnGem(id: Int) = getPlayer(id).isOnGem
    fun isOnOpenedSwitch(id: Int) = getPlayer(id).isOnOpenedSwitch
    fun isOnClosedSwitch(id: Int) = getPlayer(id).isOnClosedSwitch
    fun isOnBeeper(id: Int) = getPlayer(id).isOnBeeper
    fun isAtHome(id: Int) = getPlayer(id).isAtHome
    fun isInDesert(id: Int) = getPlayer(id).isInDesert
    fun isInForest(id: Int) = getPlayer(id).isInForest
    fun isOnPortal(id: Int) = getPlayer(id).isOnPortal
    fun isBlocked(id: Int) = getPlayer(id).isBlocked
    fun isBlockedLeft(id: Int) = getPlayer(id).isBlockedLeft
    fun isBlockedRight(id: Int) = getPlayer(id).isBlockedRight
    fun collectedGem(id: Int) = getPlayer(id).collectedGem

    fun turnLeft(id: Int) {
        getPlayer(id).turnLeft()
        printGrid()
        appendEntry()
    }
    fun moveForward(id: Int) {
        getPlayer(id).moveForward()
        printGrid()
        appendEntry()
    }
    fun collectGem(id: Int) {
        getPlayer(id).collectGem()
        printGrid()
        this.special = "GEM"
        appendEntry()
    }
    fun toggleSwitch(id: Int) {
        getPlayer(id).toggleSwitch()
        printGrid()
        this.special = "SWITCH"
        appendEntry()
    }

    fun takeBeeper(id: Int) {
        getPlayer(id).takeBeeper()
        printGrid()
        this.special = "TAKEBEEPER"
        appendEntry()
    }

    fun dropBeeper(id: Int) {
        getPlayer(id).dropBeeper()
        printGrid()
        this.special = "DROPBEEPER"
        appendEntry()
    }

    fun changeColor(id: Int, c: Color) {
        getPlayer(id).changeColor(c)
        printGrid()
        appendEntry()
    }

    fun turnLockUp(id: Int) {
        if (getPlayer(id) !is Specialist) throw Exception("Only specialist could turn locks up")
        (getPlayer(id) as Specialist).turnLockUp()
        printGrid()
        appendEntry()
    }

    fun turnLockDown(id: Int) {
        if (getPlayer(id) !is Specialist) throw Exception("Only specialist could turn locks down")
        (getPlayer(id) as Specialist).turnLockDown()
        printGrid()
        appendEntry()
    }

    fun print(lmsg: List<String>) {
        lmsg.forEach { print("$it ") }
        println()
        lmsg.forEach { consoleLog += "$it " }
        consoleLog += "\n"
        appendEntry()
    }

    fun win(): Boolean {
        return if (playground.win()) {
            this.special = "WIN"
            appendEntry()
            true
        } else false
    }
    fun dead(): Boolean {
        return if (playground.players.isEmpty()) {
            this.special = "GAMEOVER"
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
    private fun printGrid() {
        return playground.printGrid()
    }

    private fun appendEntry() {
        if (payloadStorage.size > 1000)
            throw Exception("Too many entries!")
        val currentGrid = Array(playground.grid.size) { Array(playground.grid[0].size) { Block.OPEN } }
        for (i in playground.grid.indices)
            for (j in playground.grid[0].indices)
                currentGrid[i][j] = playground.grid[i][j]
        val currentLayout = Array(playground.layout.size) { Array(playground.layout[0].size) { Item.NONE } }
        for (i in playground.layout.indices)
            for (j in playground.layout[0].indices)
                currentLayout[i][j] = playground.layout[i][j]
        val currentLayout2s = Array(playground.layout2s.size) { Array(playground.layout2s[0].size) { Tile() } }
        for (i in playground.layout2s.indices)
            for (j in playground.layout2s[0].indices)
                currentLayout2s[i][j] = playground.layout2s[i][j]
        val currentPortals = Array(playground.portals.size) { Portal() }
        for (i in playground.portals.indices)
            currentPortals[i] = playground.portals[i]
        val serializedPlayers = playground.players.map {
            SerializedPlayer(it.coo.x, it.coo.y, it.dir, if (it is Specialist) Role.SPECIALIST else Role.PLAYER) }.toTypedArray()
        val payload = Payload(
            serializedPlayers,
            currentPortals,
            SerializedPlayground(currentGrid, currentLayout, currentLayout2s),
            this.consoleLog,
            this.special
        )
        payloadStorage.add(payload)
        this.special = ""
    }

}