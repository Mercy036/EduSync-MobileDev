package com.edu.sync.data.models

data class EduSyncFile(
    val id: String,
    val name: String,
    val url: String
)

data class YouTubeLink(
    val title: String,
    val url: String,
    val addedAt: String
)

data class UserProfile(
    val uid: String,
    val name: String?,
    val email: String?,
    val avatar: String?,
    val phoneNo: String? = null,
    val youtubeLinks: List<YouTubeLink> = emptyList()
)

data class MarketplaceListing(
    val id: String?,
    val title: String,
    val price: Double,
    val category: String,
    val location: String,
    val description: String,
    val image: String,
    val sellerName: String,
    val sellerId: String,
    val sellerJoined: String = "2024"
)

data class ChatMessage(
    val role: String, // "user" or "assistant"
    val content: String
)

data class RagQueryRequest(
    val question: String,
    val top_k: Int = 4,
    val source_type: String? = null
)

data class RagQueryResponse(
    val answer: String,
    val sources: List<String> = emptyList()
)
