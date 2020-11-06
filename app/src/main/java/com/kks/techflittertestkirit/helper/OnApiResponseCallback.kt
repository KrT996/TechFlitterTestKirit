package com.kks.techflittertestkirit.helper

import com.kks.techflittertestkirit.model.WebResponseModel

interface OnApiResponseCallback {

    fun onSuccess(response: WebResponseModel)
    fun onFailure(message : String)
    fun onError(response : WebResponseModel)
}