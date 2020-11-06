package com.kks.techflittertestkirit.helper

import android.content.Context
import android.content.SharedPreferences
import com.kks.techflittertestkirit.application.TechFlitterTestKirit
import com.kks.techflittertestkirit.constant.Constant

/**
 * Shared Preference helper class
 *
 * @author Kirit Khant
 * @since 06-11-2020
 */
object PrefsHelper {

    private lateinit var sharedPref : SharedPreferences

    /**
     * Returns SharedPreference Instance
     *
     * @return SharedPreferences instance
     */
    private fun getInstance(): SharedPreferences {
        sharedPref = TechFlitterTestKirit.getContext().getSharedPreferences(Constant.PREF,Context.MODE_PRIVATE)
        return sharedPref
    }

    /**
     * Method to remove Particular Preference by Tag or Key
     *
     * @param key Key
     */
    fun removePref(key : String) {
        getInstance().edit().remove(key).apply()
    }

    /**
     * Method to remove all Prefs from the application
     */
    fun removeAllPrefs() {
        getInstance().edit().clear().apply()
    }

    /**
     * Saves Int value to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value : Int) {
        getInstance().edit().putInt(key,value).apply()
    }

    /**
     * Retrieves Int value to Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return int
     */
    fun getValue(key: String, defValue: Int) : Int {
        return getInstance().getInt(key, defValue)
    }

    /**
     * Saves String value to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value: String?) {
        getInstance().edit().putString(key, value).apply()
    }

    /**
     * Retrieves String value to Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return string
     */
    fun getValue(key: String, defValue: String) : String {
        return if (!getInstance().getString(key,defValue).isNullOrEmpty())
            getInstance().getString(key,defValue)!!
        else
            ""
    }

    /**
     * Saves Boolean value to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value: Boolean) {
        getInstance().edit().putBoolean(key,value).apply()
    }

    /**
     * Retrieves boolean value to Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return boolean
     */
    fun getValue(key: String, defValue: Boolean) : Boolean {
        return getInstance().getBoolean(key,defValue)
    }

    /**
     * Saves float value to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value: Float) {
        getInstance().edit().putFloat(key,value).apply()
    }

    /**
     * Retrieves float value to Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return float
     */
    fun getValue(key: String, defValue: Float) : Float {
        return getInstance().getFloat(key, defValue)
    }

    /**
     * Saves long value to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value: Long) {
        getInstance().edit().putLong(key, value).apply()
    }

    /**
     * Retrieves long value to Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return long
     */
    fun getValue(key: String, defValue: Long) : Long {
        return getInstance().getLong(key, defValue)
    }

    /**
     * Saves double value to Preference
     *
     * @param key String Key
     * @param value Value to be stored
     */
    fun putValue(key: String, value: Double) {
        getInstance().edit().putLong(key, value.toLong()).apply()
    }

    /**
     * Retrieve double value from Preference
     *
     * @param key String Key
     * @param defValue Default value for preference
     * @return double
     */
    fun getValue(key: String, defValue: Double) : Double {
        return getInstance().getLong(key,defValue.toLong()).toDouble()
    }

    /**
     * Saves String set values to Preference
     *
     * @param key   Shared Pref Tag
     * @param value Value to be stored
     */
    fun putValue(key: String, value: Set<String>) {
        getInstance().edit().putStringSet(key,value).apply()
    }

    /**
     * Retrieves String set values from Preference
     *
     * @param key      Shared Pref Tag
     * @param defValue default Value for preference
     * @return set of string values
     */
    fun getValue(key: String, defValue: Set<String>) : Set<String>? {
        return getInstance().getStringSet(key,defValue)
    }
}