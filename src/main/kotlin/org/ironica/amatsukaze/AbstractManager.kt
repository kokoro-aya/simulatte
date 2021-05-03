package org.ironica.amatsukaze

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