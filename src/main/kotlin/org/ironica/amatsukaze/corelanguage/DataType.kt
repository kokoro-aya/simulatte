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

sealed class DataType
object _SOME: DataType() // TODO just a placeholder for complicated array type, should be fixed
object _INT: DataType()
object _DOUBLE: DataType()
object _CHARACTER: DataType()
object _STRING: DataType()
object _BOOL: DataType()
object _VOID: DataType()
object _CALL: DataType()
object _FUNCTION: DataType()
object _STRUCT: DataType()
object _ENUM: DataType()
object _PLAYER: DataType()
object _SPECIALIST: DataType()
data class _ARRAY(val type: DataType): DataType()