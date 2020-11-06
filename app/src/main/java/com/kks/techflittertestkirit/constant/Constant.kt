package com.kks.techflittertestkirit.constant

/**
 * Constant Class
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class Constant {
    companion object {
        const val MAIN_ACTIVITY = "MainActivity"

        private const val APP_NAME = "TechFlitterTestKirit"

        /* headers */
        const val AUTH = "Authorization"
        const val AUTH_TOKEN = "Bearer df770ba95557f63ed585a2d41d172e5a3e04053d1b6b32119f598c0fe47a14bc"
        //const val AUTH_TOKEN = "772545160aa8485a1239d100ae503dae96c5f597c49255c7ec93164103269fbd"

        const val NAME = "name"
        const val EMAIL = "email"
        const val GENDER = "gender"
        const val STATUS = "status"


        ///////////////////////////////////////

        const val BASE_URL = "https://gorest.co.in/"
        const val GET_USERS =  BASE_URL + "public-api/users/"
        const val DELETE_USERS =  BASE_URL + "public-api/users/123"

        ////////////////////////////////

        const val PREF = APP_NAME + "_Pref"
        const val PREF_SELECTED_USER = "pref_selected_user"
        const val PREF_CREATE_API_CALL = "pref_create_api_call"
    }
}