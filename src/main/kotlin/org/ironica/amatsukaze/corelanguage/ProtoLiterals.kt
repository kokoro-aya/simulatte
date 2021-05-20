/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.amatsukaze.corelanguage

import org.antlr.v4.runtime.tree.ParseTree

sealed class Proto {
    abstract val prototype: Proto?
}

sealed class Literal(): Proto() {
    abstract val variability: Variability
}

data class IntegerLiteral(override val variability: Variability, var content: Int, override val prototype: Prototype): Literal()
data class DoubleLiteral(override val variability: Variability, var content: Double, override val prototype: Prototype): Literal()
data class BooleanLiteral(override val variability: Variability, var content: Boolean, override val prototype: Prototype): Literal()
data class CharacterLiteral(override val variability: Variability, var content: Char, override val prototype: Prototype): Literal()
data class StringLiteral(override val variability: Variability, var content: String, override val prototype: Prototype): Literal()
data class ReturnedLiteral(val content: Any): Throwable()
data class StructLiteral(override val variability: Variability, var body: MutableMap<String, Literal>, override var prototype: Prototype): Literal()
data class FunctionLiteral(override val variability: Variability, var head: FuncHead, var body: ParseTree, var closureScope: List<Scope>, override var prototype: Prototype): Literal()
data class ArrayLiteral(override val variability: Variability, var subType: DataType, var content: MutableList<Literal> = mutableListOf(), override var prototype: Prototype): Literal()
data class Prototype (
    val members: MutableMap<String, Literal>,
    override var prototype: Proto? = null,
    var ctor: Literal? = null
): Proto()

data class PlayerLiteral(override val variability: Variability, var id: Int, override val prototype: Prototype): Literal()
data class SpecialistLiteral(override val variability: Variability, var id: Int, override val prototype: Prototype): Literal()