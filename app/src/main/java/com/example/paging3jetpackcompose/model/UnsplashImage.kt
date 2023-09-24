package com.example.paging3jetpackcompose.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3jetpackcompose.util.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @SerialName("urls")
    @Embedded
    val urls: Urls,
    @SerialName("likes")
    val likes: Int,
//    @SerialName("user")
    @Embedded
    val user: User
)
