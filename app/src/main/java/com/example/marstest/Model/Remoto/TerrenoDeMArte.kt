package com.example.marstest.Model.Remoto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mars_table")
data class TerrenoDeMArte(
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("price")
    val price : Long,
    @SerializedName("type")
    val type : String,
    @SerializedName("img_src")
    val img_src: String


)