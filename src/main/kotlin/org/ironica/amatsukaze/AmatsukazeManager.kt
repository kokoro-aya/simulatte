package org.ironica.amatsukaze

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class PlaygroundFunction(
    val type: PF, val self: PFType, val arg1: PFType, val arg2: PFType, val ret: PFType
)

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

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.Color, arg2 = PFType.None)
    fun changeColor(id: Int, c: Color) {
        getPlayer(id).changeColor(c)
        printGrid()
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
            for (j in playground.layout2s[0].indices) {
                currentLayout2s[i][j].color = playground.layout2s[i][j].color
                currentLayout2s[i][j].level = playground.layout2s[i][j].level
            }
        val currentPortals = Array(playground.portals.size) { Portal() }
        for (i in playground.portals.indices)
            currentPortals[i] = playground.portals[i]
        val serializedPlayers = playground.players.map {
            SerializedPlayer(it.id, it.coo.x, it.coo.y, it.dir, if (it is Specialist) Role.SPECIALIST else Role.PLAYER) }.toTypedArray()
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