package com.example.jetreader.model

data class RUser(
    val userId: String,
    val username: String?,
    val email: String?,
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "user_id" to this.userId,
            "username" to this.username,
            "email" to this.email
        )
    }
}
