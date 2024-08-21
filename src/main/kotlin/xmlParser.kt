package org.gary

import java.io.File
import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.transform.stream.StreamSource

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val jaxbContext = JAXBContext.newInstance(Product::class.java)
    val unmarshaller = jaxbContext.createUnmarshaller()
    val product: Product = unmarshaller.unmarshal("src/main/resources/product.xml")

    inlineFunction { println("Hello, Kotlin!") }
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
