package org.gary

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toImmutableList

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val toc = tableOfContents(
        "The Joy of Kotlin",
        listOf(
            Content("Introduction", 1),
            Content("Chapter 1: Basics", 5),
            Content("Chapter 2: Functions", 10),
            Content("Chapter 3: Classes", 20),
            Content("Chapter 4: Advanced Topics", 30),
            Content("Conclusion", 38)
        ),
        "2018, Pierre-Yves Saumont"
    )
    val newToc = (toc as PersistentList<String>).set(0, "This is a mutable list")
    newToc.forEach(::println)
    println("#################################################")
    toc.forEach(::println)
}

data class Content(val title: String, val pageNumber: Int)

fun tableOfContents(bookTitle: String,
                           contents: List<Content>,
                           copyright: String?): ImmutableList<String> = buildList {
    add(bookTitle.uppercase())
    add("Table of Contents")
    add("------------------------------------------")
    contents.mapIndexedTo(this) {
        num, c -> "${num + 1}. ${c.title} - ${c.pageNumber}"
    }
    add("------------------------------------------")
    if (copyright != null) {
        add("")
        add("© $copyright")
    }
}.toImmutableList()