package com.example.paging3jetpackcompose.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLinks(
    @SerialName("html")
    val html: String
)
