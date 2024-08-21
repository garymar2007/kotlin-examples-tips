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

fun main() {
    val metrics = SocialMediaPost.Metrics(100, 10, 10, 5);
    val post = SocialMediaPost(1, "Hello, Kotlin!", metrics)
    println(post.metrics.impressions)
}

