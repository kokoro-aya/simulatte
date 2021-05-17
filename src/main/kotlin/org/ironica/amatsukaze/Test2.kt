package org.ironica.amatsukaze

open class ABC

class DEF: ABC()

fun main() {
    val a = DEF()
    val b = listOf(a)
    b.filterIsInstance<ABC>()
}