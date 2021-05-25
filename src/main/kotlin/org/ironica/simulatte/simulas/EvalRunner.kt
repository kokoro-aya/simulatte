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

import org.ironica.simulatte.payloads.Status
import org.jetbrains.kotlinx.ki.shell.KotlinShell
import org.jetbrains.kotlinx.ki.shell.OnEval
import org.jetbrains.kotlinx.ki.shell.Shell
import org.jetbrains.kotlinx.ki.shell.bound
import org.jetbrains.kotlinx.ki.shell.wrappers.ResultWrapper
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.*
import kotlin.script.experimental.util.LinkedSnippet

/**
 * Implementation of Eval Runner with help of @author Dylech30th
 */
object EvalRunner {

    private val defaultImportKotlinPackages = listOf(
        "kotlin.collections.*",
        "kotlin.comparisons.*",
        "kotlin.concurrent.*",
        "kotlin.coroutines.*",
        "kotlinx.coroutines.*",
        "kotlin.coroutines.intrinsics.*",
        "kotlin.io.*",
        "kotlin.io.path.*",
        "kotlin.jvm.*",
        "kotlin.math.*",
        "kotlin.random.*",
        "kotlin.reflect.*",
        "kotlin.reflect.full.*",
        "kotlin.reflect.jvm.*",
        "kotlin.sequences.*",
        "kotlin.streams.*",
        "kotlin.text.*",
        "kotlin.time.*",
    )

    private val defaultImportJavaPackages = listOf(
        "java.io.*",
        "java.lang.*",
        "java.math.*",
        "java.net.*",
        "java.util.*",
        "java.lang.ref.*",
        "java.lang.reflect.*",
        "java.lang.invoke.*",
        "java.nio.*",
        "java.nio.channels.*",
        "java.nio.file.*",
        "java.util.concurrent.*",
        "java.util.function.*",
        "java.util.regex.*",
        "java.util.stream.*",
        "sun.misc.*",
    )

    private val defaultImportSimulattePackage = listOf(
        "org.ironica.simulatte.playground.*",
        "org.ironica.simulatte.playground.datas.*",
        "org.ironica.simulatte.playground.characters.*",
        "org.ironica.simulatte.internal.*",
        "org.ironica.simulatte.payloads.payloadStorage",
        "org.ironica.simulatte.payloads.statusStorage",

        )

    private val shell = Shell(
        KotlinShell.configuration(),
        defaultJvmScriptingHostConfiguration,
        ScriptCompilationConfiguration {
            jvm {
                dependenciesFromCurrentContext(wholeClasspath = true)
                defaultImports(
                    defaultImportKotlinPackages + defaultImportJavaPackages + defaultImportSimulattePackage
                )
            }
        },
        ScriptEvaluationConfiguration {
            jvm {
                baseClassLoader(Thread.currentThread().contextClassLoader)
            }
        }
    )

    init {
        shell.initEngine()
    }

    fun evalSnippet(source: String): Pair<Any?, Status> {
        val time = System.nanoTime()
        val result = shell.eval(source)
        shell.evaluationTimeMillis = (System.nanoTime() - time) / 1_000_000
        return when (result.getStatus()) {
            ResultWrapper.Status.SUCCESS -> {
                shell.incompleteLines.clear()
                handleSuccessSub(result.result as ResultWithDiagnostics.Success<*>) to Status.OK
            }
            ResultWrapper.Status.ERROR -> {
                shell.incompleteLines.clear()
                handleError(result.result, result.isCompiled) to Status.ERROR
            }
            ResultWrapper.Status.INCOMPLETE -> {
                shell.incompleteLines.add(source)
                null to Status.INCOMPLETE
            }
        }
    }

    private fun handleSuccess(result: ResultWithDiagnostics.Success<*>): String? {
        val snippets = result.value as LinkedSnippet<KJvmEvaluatedSnippet>
        shell.eventManager.emitEvent(OnEval(snippets))
        return when (val evalResultValue = snippets.get().result) {
            is ResultValue.Value -> "${evalResultValue.name}${shell.renderResultType(evalResultValue)} = ${evalResultValue.value}".bound(shell.settings.maxResultLength)
            is ResultValue.Error -> renderError(evalResultValue)
            is ResultValue.Unit -> "Kotlin.Unit"
            ResultValue.NotEvaluated -> null
        }
    }

    private fun handleSuccessSub(result: ResultWithDiagnostics.Success<*>): Any? {
        val snippets = result.value as LinkedSnippet<KJvmEvaluatedSnippet>
        shell.eventManager.emitEvent(OnEval(snippets))
        return when (val evalResultValue = snippets.get().result) {
            is ResultValue.Value -> evalResultValue.value
            is ResultValue.Error -> renderError(evalResultValue)
            is ResultValue.Unit -> "Kotlin.Unit"
            ResultValue.NotEvaluated -> null
        }
    }

    private fun renderError(value: ResultValue.Error): String {
        val fullTrace = value.error.stackTrace
        return if (value.wrappingException == null
            || fullTrace.size < value.wrappingException!!.stackTrace.size) {
            value.error.stackTraceToString()
        } else {
            buildString {
                appendLine(value.error)
                val scriptTraceSize = fullTrace.size - value.wrappingException!!.stackTrace.size
                for (i in 0 until scriptTraceSize) {
                    appendLine("\tat " + fullTrace[i])
                }
            }
        }
    }

    private fun handleError(result: ResultWithDiagnostics<*>, isCompiled: Boolean): String {
        return buildString {
            result.reports.forEach {
                appendLine(it.render(withStackTrace = isCompiled))
            }
        }
    }
}