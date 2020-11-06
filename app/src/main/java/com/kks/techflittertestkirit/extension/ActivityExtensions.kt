package com.kks.techflittertestkirit.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kks.techflittertestkirit.helper.PrefsHelper
import com.kks.techflittertestkirit.localdatabase.viewmodel.ViewModel

/**
 * Activity's extensions's file.
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * method to getViewModel
 */
fun AppCompatActivity.getViewModel(): ViewModel {
    return ViewModelProvider(this).get(ViewModel::class.java)
}


/*
    To Close Soft keyboard
 */
fun AppCompatActivity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.savePref(key: String, data: String) {
    PrefsHelper.putValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.savePref(key: String, data: Int) {
    PrefsHelper.putValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */

fun AppCompatActivity.savePref(key: String, data: Double) {
    PrefsHelper.putValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */

fun AppCompatActivity.savePref(key: String, data: Boolean) {
    PrefsHelper.putValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.getPref(key: String, data: String): String {
    return PrefsHelper.getValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.getPref(key: String, data: Int): Int {
    return PrefsHelper.getValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.getPref(key: String, data: Double): Double {
    return PrefsHelper.getValue(key, data)
}

/**
 * save preference
 *
 * @param key string  preference key
 * @param data string preference data
 */
fun AppCompatActivity.getPref(key: String, data: Boolean): Boolean {
    return PrefsHelper.getValue(key, data)
}


/*
   clear preference
 */
fun AppCompatActivity.clearPref() {
    PrefsHelper.removeAllPrefs()
}


/**
 * to delete preference
 *
 * @param key string  preference key
 */
fun AppCompatActivity.removePref(key: String) {
    return PrefsHelper.removePref(key)
}