package org.ironica.amatsukaze

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class PlaygroundFunction(
    val type: PF, val self: PFType, val arg1: PFType, val arg2: PFType, val ret: PFType
)