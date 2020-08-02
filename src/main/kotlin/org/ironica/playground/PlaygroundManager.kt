package org.ironica.playground

class PlaygroundManager(val playground: Playground) {

    init {
        appendEntry()
    }

    val isOnGem = playground.player.isOnGem
    val isOnOpenedSwitch = playground.player.isOnOpenedSwitch
    val isOnClosedSwitch = playground.player.isOnClosedSwitch
    val isBlocked = playground.player.isBlocked
    val isBlockedLeft = playground.player.isBlockedLeft
    val isBlockedRight = playground.player.isBlockedRight
    val collectedGem = playground.player.collectedGem

    fun turnLeft() {
        playground.player.turnLeft()
        appendEntry()
    }
    fun moveForward() {
        playground.player.moveForward()
        appendEntry()
    }
    fun collectGem() {
        playground.player.collectGem()
        appendEntry()
    }
    fun toggleSwitch() {
        playground.player.toggleSwitch()
        appendEntry()
    }

    fun win(): Boolean {
        return playground.win()
    }
    fun gemCount(): Int {
        return playground.gemCount()
    }
    fun switchCount(): Int {
        return playground.switchCount()
    }
    fun printGrid() {
        return playground.printGrid()
    }

    private fun appendEntry() {
        if (payloadStorage.size > 1000)
            throw Exception("Too many entries!")
        val payload = Payload(
            SerializedPlayer(playground.player.coo.x, playground.player.coo.y, playground.player.dir),
            SerializedGrid(playground.grid)
        )
        payloadStorage.add(payload)
    }

}