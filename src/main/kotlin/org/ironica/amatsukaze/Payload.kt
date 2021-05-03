package org.ironica.amatsukaze

import kotlinx.serialization.Serializable

@Serializable
data class SerializedPlayer(val id: Int, val x: Int, val y: Int, val dir: Direction, val role: Role, val stamina: Int)

@Serializable
data class SerializedPlayground(val grid: Grid, val layout: Layout, val misc: SecondLayout)

@Serializable
data class Payload(
    val grid: Grid,
    val layout: Layout,
    val misc: SecondLayout,
    val players: Array<SerializedPlayer>,
    val portals: Array<Portal>,
    val consoleLog: String,
    val special: String
    ) {
    constructor(
        players: Array<SerializedPlayer>,
        portals: Array<Portal>,
        grid: SerializedPlayground,
        consoleLog: String,
        special: String
    ): this(grid.grid, grid.layout, grid.misc, players, portals, consoleLog, special)
}


val payloadStorage = mutableListOf<Payload>()

enum class Status { OK, ERROR }

@Serializable
sealed class Message
@Serializable
data class NormalMessage(val status: Status, val payload: List<Payload>): Message()
@Serializable
data class ErrorMessage(val status: Status, val msg: String): Message()