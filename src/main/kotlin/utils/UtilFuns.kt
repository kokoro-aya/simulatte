/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package utils

import org.ironica.simulatte.playground.Biome
import org.ironica.simulatte.playground.Color
import org.ironica.simulatte.playground.Direction

fun <A, B, C, D>zip(
    first: List<List<A>>,
    second: List<List<B>>,
    third: List<List<C>>,
    with: (A, B, C) -> D
): List<List<D>> {
    val x = first.size
    val y = first[0].size
    check (second.size == x && third.size == x
            && second[0].size == y && third[0].size == y
    ) { "three arrays should have same dimensions" }
    return first.mapIndexed { i1, p ->
        p.mapIndexed { i2, a ->
            with(a, second[i1][i2], third[i1][i2])
        }
    }
}

val <E: StringRepresentable> List<E>.stringRepresentation: String
    get() = buildString {
        if (this@stringRepresentation::class == MutableList::class){
            appendLine("mutableListOf(")
        } else {
            appendLine("listOf(")
        }
        this@stringRepresentation.forEachIndexed { i, line ->
            append("\t")
            append(line.stringRepresentation)
            if (i < this@stringRepresentation.size - 1) appendLine(", ")
        }
        appendLine("\t)")
    }



val Direction.stringRepresentation: String
    get() = "Direction.$this"

val Color.stringRepresentation: String
    get() = "Color.$this"

val Biome.stringRepresentation: String
    get() = "Biome.$this"

val <E: StringRepresentable, F: StringRepresentable> Map<E, F>.stringRepresentation: String
    get() = buildString {
        appendLine("mapOf(")
        this@stringRepresentation.forEach {
            append("\t")
            append("${it.key.stringRepresentation} to ${it.value.stringRepresentation}")
            appendLine(", ")
        }
        appendLine(")")
    }