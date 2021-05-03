package org.ironica.amatsukaze

class ColorfulManager(override val playground: Playground): AbstractManager {
    init {
        appendEntry()
    }

    override var consoleLog: String = ""
    override var special: String = ""

    override val firstId = playground.players.map { it.id }.sorted()[0]

    @PlaygroundFunction(type = PF.Method, ret = PFType.None, self = PFType.Self, arg1 = PFType.Color, arg2 = PFType.None)
    fun changeColor(id: Int, c: Color) {
        getPlayer(id).changeColor(c)
        printGrid()
        appendEntry()
    }

    override fun appendEntry() {
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
        val currentMiscLayout = Array(playground.layout2s.size) { Array<Tile>(playground.layout2s[0].size) { ColorfulTile(Color.WHITE) } }
        for (i in playground.layout2s.indices)
            for (j in playground.layout2s[0].indices) {
                currentMiscLayout[i][j] = playground.layout2s[i][j]
            }
        val currentPortals = Array(playground.portals.size) { Portal() }
        for (i in playground.portals.indices)
            currentPortals[i] = playground.portals[i]
        val serializedPlayers = playground.players.map {
            SerializedPlayer(it.id, it.coo.x, it.coo.y, it.dir, if (it is Specialist) Role.SPECIALIST else Role.PLAYER) }.toTypedArray()
        val payload = Payload(
            serializedPlayers,
            currentPortals,
            SerializedPlayground(currentGrid, currentLayout, currentMiscLayout),
            this.consoleLog,
            this.special
        )
        payloadStorage.add(payload)
        this.special = ""
    }
}