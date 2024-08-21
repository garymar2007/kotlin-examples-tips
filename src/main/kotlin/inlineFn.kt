package org.gary

fun main() {
    inlineFunction { println("Hello, Kotlin!") }
}

//higher order function
inline fun inlineFunction(block: () -> Unit) {
    println("Before calling block")
    block()
    println("After calling block")
}