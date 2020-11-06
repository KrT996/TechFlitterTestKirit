package com.kks.techflittertestkirit.util

import java.util.regex.Pattern

/**
 * Application's utility class. all utility functions will be defined here
 *
 * @author : Kirit Khant
 * @since : 06-11-2020
 */

object Utils {

    /**
     *  to check if email is valid or not
     *
     *  @param email: string user's email
     *  @return Boolean : if email is valid or not
     */
    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

}