/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.internal

import org.ironica.simulatte.manager.AbstractManager

/**
 * This class handles the console output that will appended to Manager.consoleLog and
 * exposes an api to the SimulatteBuilder
 */
class Console(val manager: AbstractManager) {
    fun log(vararg arg: Any) {
        return manager.log(*arg)
    }

    fun logln(vararg arg: Any) {
        return manager.log(*arg, "\n")
    }
}