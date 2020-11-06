package com.kks.techflittertestkirit.helper

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.model.WebResponseModel
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

/**
 * Helper Class to make api calls .
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class WebServicesHelper(private val url : String, mContext : Context ? = null) {

    private lateinit var mClient : OkHttpClient

    private val formBody = FormBody.Builder()
    private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
    private val request = Request.Builder()


    fun generateHeaders() {
        request.addHeader(Constant.AUTH, Constant.AUTH_TOKEN)
    }


    fun addHeaders(key : String, value : String) {
        request.addHeader(key, value)
    }

    fun addBodyParam(key: String, value: String) {
        formBody.add(key, value)
    }

    /**
     * To add form body param while making post api call with form-body
     *
     * @param name : name of param
     * @param value : value of param
     */
    fun addBodyPram(params: MutableMap<String, String>) {
        for ((key, value) in params) {
            formBody.add(key, value)
        }
    }

    ///////////////////////////////////////////////////////////// Make Calls /////////////////////////////////////////////////////

    fun get(onApiResponseCallback: OnApiResponseCallback) {
        val mRequest = request.url(url).build()
        createClient(mRequest, onApiResponseCallback)
    }

    fun postFormBody(onApiResponseCallback: OnApiResponseCallback) {
        val mFormBody = formBody.build()
        val mRequest = request.url(url).post(mFormBody).build()
        createClient(mRequest, onApiResponseCallback)
    }

    fun delete(onApiResponseCallback: OnApiResponseCallback) {
        val request = request.url(url).delete().build()
        createClient(request, onApiResponseCallback)
    }


    //////////////////////////////////////////////////////////// Create Client ///////////////////////////////////////////////////

    private fun createClient(
        mRequest: Request,
        onApiResponseCallback: OnApiResponseCallback
    ) {
        mClient = OkHttpClient()
        mClient.newCall(mRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onApiResponseCallback.onFailure(e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val resP : String = response.body?.string()?:""
                    if (response.code == 404) {
                        onApiResponseCallback.onFailure("404 page not found")
                    } else {

                        val webResponseModel = parseResponse(resP)

                        onApiResponseCallback.onSuccess(webResponseModel)
                    }
                } catch (e : Exception) {
                    e.printStackTrace()
                }
            }

        })
    }

    private fun parseResponse(response : String) : WebResponseModel {
        val mGson = Gson()
        //var webResponseModel = WebResponseModel()

        Log.e("Response", response)

        val webResponseModel = mGson.fromJson(response, WebResponseModel::class.java)

        try {

            val dataa = JSONObject(response)

            if (dataa.has("data")) {
                val mData =  dataa.getString("data")
                if (!mData.isNullOrEmpty()) {
                    webResponseModel.mData = mData
                }
            }
        } catch (e : Exception) {
            Log.e("Error", "Error Parsing Data : " + e.printStackTrace())
            e.printStackTrace()
        }
        return webResponseModel
    }
}