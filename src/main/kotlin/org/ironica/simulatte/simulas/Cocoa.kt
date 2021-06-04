/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.simulas

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asTypeName
import org.ironica.simulatte.bridge.rules.GamingCondition
import org.ironica.simulatte.manager.DefaultManager
import org.ironica.simulatte.playground.Playground
import org.ironica.simulatte.playground.characters.AbstractCharacter
import org.ironica.simulatte.playground.datas.Coordinate
import org.ironica.simulatte.playground.datas.LockBlock
import org.ironica.simulatte.playground.datas.PortalItem
import org.ironica.simulatte.playground.datas.Square
import utils.*

class Cocoa {
    private val fs = FileSpec.builder("org.ironica.simulatte.Simulatte", "Simulatte")

    init {
    }

    // Each feed function have different parameters but they need to be specified at JVM level different names

    @JvmName("feedSquares")
    fun feed(squares: List<List<Square>>): Cocoa {
        fs.addProperty(PropertySpec.builder("squares",
            List::class.asTypeName()
                .parameterizedBy(List::class.asTypeName()
                    .parameterizedBy(Square::class.asTypeName())))
            .initializer(squares.generateTemplate())
            .build()
        )
        return this
    }

    @JvmName("feedPortals")
    fun feed(portals: Map<PortalItem, Coordinate>): Cocoa {
        fs.addProperty(PropertySpec.builder("portals",
            Map::class.asTypeName()
                .parameterizedBy(PortalItem::class.asTypeName(),
                    Coordinate::class.asTypeName()))
            .initializer(portals.stringRepresentation)
            .build()
        )
        return this
    }

    @JvmName("feedLocks")
    fun feed(locks: Map<Coordinate, LockBlock>): Cocoa {
        fs.addProperty(PropertySpec.builder("locks",
            Map::class.asTypeName()
                .parameterizedBy(Coordinate::class.asTypeName(),
                    LockBlock::class.asTypeName()))
            .initializer(locks.stringRepresentation)
            .build()
        )
        return this
    }

    @JvmName("feedPlayers")
    fun feed(players: Map<AbstractCharacter, Coordinate>): Cocoa {
        fs.addProperty(PropertySpec.builder("players",
            Map::class.asTypeName()
                .parameterizedBy(AbstractCharacter::class.asTypeName(),
                    Coordinate::class.asTypeName()))
            .initializer(players.stringRepresentation)
            .build()
        )
        return this
    }

    @JvmName("feedRules")
    fun feed(gamingCondition: GamingCondition?, userCollision: Boolean): Cocoa {
        fs.addProperty(PropertySpec.builder("gamingCondition",
            GamingCondition::class.asTypeName().copy(nullable = true))
            .initializer(gamingCondition?.stringRepresentation ?: "null")
            .build()
        )
        fs.addProperty(PropertySpec.builder("userCollision",
            Boolean::class)
            .initializer(userCollision.toString())
            .build()
        )
        return this
    }

    fun thenFeed(managerType: String, debug: Boolean, stdout: Boolean): Cocoa {
        fs.addProperty(PropertySpec.builder("playground", Playground::class)
            .initializer("Playground(squares, portals.toMutableMap(), locks.toMutableMap(), players.toMutableMap(), gamingCondition, userCollision)")
            .build()
        )
        fs.addProperty(PropertySpec.builder("manager", when(managerType) {
            "default" -> DefaultManager::class
            else -> throw UnsupportedOperationException("Cocoa:: The Manager type is unknown")
        })
            .initializer(when (managerType) {
                "default" -> "DefaultManager(playground, $debug, $stdout)"
                else -> throw UnsupportedOperationException("Cocoa:: The Manager type is unknown")
            })
            .build()
        )
        return this
    }

    private fun List<List<Square>>.generateTemplate(): String {
        return buildString {
            appendLine("listOf(")
            this@generateTemplate.forEachIndexed { i, l ->
                append(l.stringRepresentation)
                if (i < this@generateTemplate.size - 1) appendLine(", ")
            }
            appendLine(")")
        }
    }

    fun generate(out: Appendable) {
        fs.build().writeTo(out)
    }
}

/**
 * wrap user's code with boilerplate for Builder
 */
fun String.wrapCode(): String {
    return buildString {
        appendLine("payloadStorage.set(mutableListOf())\n" +
                "        statusStorage.set(GameStatus.PENDING)")

        appendLine("runBlocking { ")
        appendLine("\tval ___game = play(manager) { ")
        appendLine(this@wrapCode.split("\n").joinToString("\n") { "\t\t$it" })
        appendLine("\t}.run()\n")
        appendLine("\t// println(___game.size)")
        appendLine("}")
        appendLine("payloadStorage.get() to statusStorage.get()")
    }
}