package com.example.paging3jetpackcompose.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val links: UserLinks,
    @SerialName("name")
    val name: String
)
