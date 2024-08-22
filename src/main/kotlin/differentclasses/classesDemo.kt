package org.gary.differentclasses

import kotlinx.serialization.Serializable

const val postJson = """
    {
    "id": 1,
    "text": "Hello, Kotlin!"
    "metrics": {
        "impressions": 100,
        "clicks": 10,
        "likes": 10,
        "shares": 5
    }
    """

// Nested classes
@Serializable
class SocialMediaPost(
    val id: Int,
    val text: String,
    val metrics: Metrics
){
    @Serializable
    class Metrics(
        val impressions: Int,
        val clicks: Int,
        val likes: Int,
        val shares: Int
    )
}

// nested class could not access the outer class properties
class FavoriteStrings(val first: String, val second: String, val third: String): Iterable<String> {
    override fun iterator(): Iterator<String> = FavoriteStringsIterator()

    inner class FavoriteStringsIterator: Iterator<String> {
        private var currentIndex = 0

        override fun hasNext() = currentIndex < 3

        override fun next() = when(currentIndex) {
            0 -> first
            1 -> second
            2 -> third
            else -> throw IndexOutOfBoundsException("Invalid index $currentIndex")
        }.also { currentIndex++ }
    }
}

data class Book(val id: Int, val title: String, val author: String, val year: Int)

@JvmInline
value class BookId(val value: Int)
@JvmInline
value class BookTitle(val value: String)
@JvmInline
value class BookAuthor(val value: String)
@JvmInline
value class BookYear(val value: Int)

data class Book2(val id: BookId, val title: BookTitle, val author: BookAuthor, val year: BookYear)

fun main() {
    val metrics = SocialMediaPost.Metrics(100, 10, 10, 5);
    val post = SocialMediaPost(1, "Hello, Kotlin!", metrics)
    println(post.metrics.impressions)

    val myBook = Book(1, "The Joy of Kotlin", "Pierre-Yves Saumont", 2018)
    println(myBook)

    val (_, title, author, year) = myBook
    println("Title: $title, Author: $author, Year: $year")

    val myBook2 = Book2(BookId(1), BookTitle("The Joy of Kotlin"), BookAuthor("Pierre-Yves Saumont"), BookYear(2018))
    println(myBook2)

    val (_, title2, author2, year2) = myBook2
    println("Title: ${title2.value}, Author: ${author2.value}, Year: ${year2.value}")
}

