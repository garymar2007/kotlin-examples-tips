package org.gary

import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeParseException
import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.transform.stream.StreamSource

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   try {
      val date: LocalDate = LocalDate.parse(("2024-13-01"))
      println("Date: $date")
   } catch (e: DateTimeParseException) {
      println("Unbale to parse. ${e.message}")
   }

   val result: Result<LocalDate> = runCatching { LocalDate.parse("2024-13-01") }

   result.onSuccess { date -> println("Day of the Week: ${date.dayOfWeek}") }
   result.onFailure { e -> println("Unable to parse. ${e.message}") }

   val listOfStrings: List<String> = listOf("one", "two", "three")
    println("Second item: ${secondItemOf(listOfStrings)}")

   val listOfInts: List<Int> = listOf(1, 2, 3)
    println("Second item: ${secondItemOf(listOfInts)}")

   val list: List<Any> = listOf(1, "two", 3)
    println("Second item has type String: ${secondItemHasType<String>(list)}")
    println("Second item has type Int: ${secondItemHasType<Int>(list)}")

    val jaxbContext = JAXBContext.newInstance(Product::class.java)
    val unmarshaller = jaxbContext.createUnmarshaller()
    val product: Product = unmarshaller.unmarshal("src/main/resources/product.xml")
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