package com.kks.techflittertestkirit.model

import com.google.gson.annotations.SerializedName

data class WebResponseModel(

	@field:SerializedName("code")
	val code: Int? = null,

	//@field:SerializedName("data")
	//var data: List<UserItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	var mData : String = ""
)