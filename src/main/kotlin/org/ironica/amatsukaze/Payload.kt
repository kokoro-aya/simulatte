package org.ironica.amatsukaze

import kotlinx.serialization.Serializable

@Serializable
data class SerializedPlayer(val id: Int, val x: Int, val y: Int, val dir: Direction, val role: Role, val stamina: Int)

@Serializable
data class SerializedPlayground(val grid: Grid, val layout: Layout, val colors: List<List<Color>>, val levels: List<List<Int>>)

@Serializable
data class Payload(
    val grid: Grid,
    val layout: Layout,
    val colors: List<List<Color>>,
    val levels: List<List<Int>>,
    val players: List<SerializedPlayer>,
    val portals: List<Portal>,
    val consoleLog: String,
    val special: String
    ) {
    constructor(
        players: List<SerializedPlayer>,
        portals: List<Portal>,
        grid: SerializedPlayground,
        consoleLog: String,
        special: String
    ): this(grid.grid, grid.layout, grid.colors, grid.levels, players, portals, consoleLog, special)
}

val payloadStorage = mutableListOf<Payload>()

enum class Status { OK, ERROR }

@Serializable
sealed class Message
@Serializable
data class NormalMessage(val status: Status, val payload: List<Payload>): Message()
@Serializable
data class ErrorMessage(val status: Status, val msg: String): Message()