package org.ironica.playground

import kotlinx.serialization.Serializable

@Serializable
data class SerializedPlayer(val x: Int, val y: Int, val dir: Direction)

@Serializable
data class SerializedGrid(val grid: Grid)

@Serializable
data class Payload(val serializedPlayer: SerializedPlayer, val serializedGrid: SerializedGrid)


val payloadStorage = mutableListOf<Payload>()