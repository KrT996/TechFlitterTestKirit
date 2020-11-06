package com.kks.techflittertestkirit.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.helper.OnApiResponseCallback
import com.kks.techflittertestkirit.helper.WebServicesHelper
import com.kks.techflittertestkirit.model.ApiCallUiModel
import com.kks.techflittertestkirit.model.WebResponseModel

/**
 * Api viewmodel for User deletion
 *
 * @author : Kirit Khant
 * @since : 06-11-2020
 */
class DeleteUserApiViewModel : ViewModel() {

    // to handle UI when api call is running
    private var isCallRunning = MutableLiveData<ApiCallUiModel>()
    val callRunning: MutableLiveData<ApiCallUiModel>
        get() = isCallRunning

    /**
     * delete user
     *
     */
    fun deleteUser(apiId: Int): MutableLiveData<WebResponseModel> {

        isCallRunning.postValue(ApiCallUiModel(true, apiId))

        val mData: MutableLiveData<WebResponseModel> = MutableLiveData()

        try {

            val helper = WebServicesHelper(Constant.DELETE_USERS)
            helper.generateHeaders()
            helper.delete(object : OnApiResponseCallback {
                override fun onSuccess(response: WebResponseModel) {
                    isCallRunning.postValue(ApiCallUiModel(false, apiId))
                    mData.postValue(response)
                    isCallRunning = MutableLiveData()
                }

                override fun onError(response: WebResponseModel) {
                    isCallRunning.postValue(ApiCallUiModel(false, apiId))
                    mData.postValue(response)
                    isCallRunning = MutableLiveData()
                }

                override fun onFailure(message: String) {
                    isCallRunning.postValue(ApiCallUiModel(false, apiId))
                    Log.e("Call Fail", message)
                    isCallRunning = MutableLiveData()
                }
            })

        } catch (e: Exception) {
            Log.e("ErrorLog", e.printStackTrace().toString())
        }
        return mData
    }
}