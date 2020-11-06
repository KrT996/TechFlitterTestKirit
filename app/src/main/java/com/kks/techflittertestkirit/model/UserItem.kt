package com.kks.techflittertestkirit.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class UserItem(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	var isClicked : Boolean = false
)