package org.gary

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toImmutableList
import java.io.File
import javax.xml.bind.Unmarshaller
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.transform.stream.StreamSource

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//   try {
//      val date: LocalDate = LocalDate.parse(("2024-13-01"))
//      println("Date: $date")
//   } catch (e: DateTimeParseException) {
//      println("Unbale to parse. ${e.message}")
//   }
//
//   val result: Result<LocalDate> = runCatching { LocalDate.parse("2024-13-01") }
//
//   result.onSuccess { date -> println("Day of the Week: ${date.dayOfWeek}") }
//   result.onFailure { e -> println("Unable to parse. ${e.message}") }
//
//   val listOfStrings: List<String> = listOf("one", "two", "three")
//    println("Second item: ${secondItemOf(listOfStrings)}")
//
//   val listOfInts: List<Int> = listOf(1, 2, 3)
//    println("Second item: ${secondItemOf(listOfInts)}")
//
//   val list: List<Any> = listOf(1, "two", 3)
//    println("Second item has type String: ${secondItemHasType<String>(list)}")
//    println("Second item has type Int: ${secondItemHasType<Int>(list)}")
//
//    val jaxbContext = JAXBContext.newInstance(Product::class.java)
//    val unmarshaller = jaxbContext.createUnmarshaller()
//    val product: Product = unmarshaller.unmarshal("src/main/resources/product.xml")

    //inlineFunction { println("Hello, Kotlin!") }

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

fun <T> secondItemOf(list: List<T>): T {
   return list[1]
}

inline fun <reified T> secondItemHasType(list: List<*>): Boolean {
    return list[1] is T
}

inline fun <reified T> Unmarshaller.unmarshal(filePath: String): T {
    return unmarshal(StreamSource(File(filePath)), T::class.java).value
}

@XmlRootElement(name = "event")
data class Product @JvmOverloads constructor(
    @XmlElement var id: String = "",
    @XmlElement var name: String = "",
    @XmlElement var price: Int = 0
)

//higher order function
inline fun inlineFunction(block: () -> Unit) {
    println("Before calling block")
    block()
    println("After calling block")
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