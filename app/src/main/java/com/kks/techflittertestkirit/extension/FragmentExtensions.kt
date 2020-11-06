package com.kks.techflittertestkirit.extension

import android.app.Activity
import android.os.Build
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.helper.PrefsHelper
import com.kks.techflittertestkirit.localdatabase.viewmodel.ViewModel
import com.kks.techflittertestkirit.ui.activity.MainActivity

/**
 * Extension's file for fragment
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * to set color of status bar
 *
 * @param color : color to set in statusBar
 */
fun Fragment.setStatusBarColor(color: Int) {
    if (isAdded && activity != null) {
        val mColor = ContextCompat.getColor((activity as AppCompatActivity), color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity?.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = mColor
        }
    }
}

/**
 * To show toolbar
 *
 * @param showActonBar bool to show/hide tool bar
 */
fun Fragment.showToolbar(showActonBar: Boolean) {
    if (isAdded && activity != null) {
        if (showActonBar) {
            activity?.findViewById<AppBarLayout>(R.id.appBar)
                ?.visibility = View.VISIBLE
        } else {
            activity?.findViewById<AppBarLayout>(R.id.appBar)
                ?.visibility = View.GONE
        }
    }
}

/**
 *  to set tool bar title
 *
 *  @param title : sting toolbar title
 */
fun Fragment.setToolbarTitle(title: String) {
    if (isAdded && activity != null) {
        activity?.findViewById<AppCompatTextView>(R.id.tvToolbarTitle)?.text = title
    }
}

/**
 * Method to show custom alert
 *
 * @param title : alert Title
 * @param msg : alert message
 */
/*fun Fragment.showCustomAlert(title: Int = 0, msg: Int) {

    var mTitle = getString(R.string.txt_alert)

    if (title != 0) {
        mTitle = getString(title)
    }

    val mMsg = getString(msg)

    if (isAdded && activity != null) {
        if (isAdded && activity != null) {
            when ((activity as AppCompatActivity)::class.java.simpleName) {
                Constant.MAIN_ACTIVITY -> (activity as MainActivity).showCustomAlert(mTitle, mMsg)
            }
        }
    }
}*/

/**
 * method to show/hide password toggle
 */
fun Fragment.passwordToggle(iv: AppCompatImageView, et: AppCompatEditText) {
    if (iv.isSelected) {
        iv.isSelected = false
        et.transformationMethod = PasswordTransformationMethod.getInstance()
    } else {
        iv.isSelected = true
        et.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }
}

/**
 * Method to getViewmodel
 */
fun Fragment.getViewModel(): ViewModel? {
    if (isAdded && activity != null) {
        when ((activity as AppCompatActivity)::class.java.simpleName) {
            Constant.MAIN_ACTIVITY -> return (activity as MainActivity).mViewModel
        }
    }
    return null
}

/**
 *  show progress loader
 *
 *  @param showLoader:Boolean
 */
fun Fragment.showProgressLoader(showLoader: Boolean) {

    hideKeyboard()

    val mView = this.view

    if (mView != null) {

        if (mView.findViewById<ConstraintLayout>(R.id.clPb) != null) {
            val pbLoader = this.view?.findViewById<ConstraintLayout>(R.id.clPb)
            if (showLoader) {
                pbLoader?.visibility = View.VISIBLE
            } else {
                pbLoader?.visibility = View.GONE
            }
        } else {

            if (showLoader) {
                val cl = mView.findViewById<ConstraintLayout>(R.id.clMain)
                if (cl != null) {

                    val child = this.layoutInflater.inflate(R.layout.layout_progress, null)
                    cl.addView(child)
                    val set = ConstraintSet()
                    set.constrainHeight(
                        child.id,
                        ConstraintSet.MATCH_CONSTRAINT
                    )
                    set.constrainWidth(
                        child.id,
                        ConstraintSet.MATCH_CONSTRAINT
                    )

                    set.connect(
                        child.id, ConstraintSet.LEFT,
                        ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0
                    )
                    set.connect(
                        child.id, ConstraintSet.RIGHT,
                        ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0
                    )
                    set.connect(
                        child.id, ConstraintSet.TOP,
                        ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0
                    )
                    set.connect(
                        child.id, ConstraintSet.BOTTOM,
                        ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0
                    )
                    set.applyTo(cl)
                }
            }
        }
    }

}


/**
 *  to hide soft keyboard
 */
fun Fragment.hideKeyboard() {

    val activity = activity
    if (isAdded && activity != null) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}



//////////////////////////////////////// Shared Preferences //////////////////////////////////////////////////

/**
 * save string preference
 *
 * @param key String  preference key
 * @param data String preference data
 */
fun Fragment.savePref(key: String, data: String) {
    PrefsHelper.putValue(key, data)
}

/**
 * save int preference
 *
 * @param key String  preference key
 * @param data Int preference data
 */
fun Fragment.savePref(key: String, data: Int) {
    PrefsHelper.putValue(key, data)
}

/**
 * save boolean preference
 *
 * @param key String  preference key
 * @param data boolean preference data
 */
fun Fragment.savePref(key: String, data: Boolean) {
    PrefsHelper.putValue(key, data)
}

/**
 * save double preference
 *
 * @param key String  preference key
 * @param data Double preference data
 */
fun Fragment.savePref(key: String, data: Double) {
    PrefsHelper.putValue(key, data)
}

/**
 * save long preference
 *
 * @param key String  preference key
 * @param data long preference data
 */
fun Fragment.savePref(key: String, data: Long) {
    PrefsHelper.putValue(key, data)
}

/**
 * save float preference
 *
 * @param key String  preference key
 * @param data float preference data
 */
fun Fragment.savePref(key: String, data: Float) {
    PrefsHelper.putValue(key, data)
}

/**
 * save double preference
 *
 * @param key String  preference key
 * @param data set of string preference data
 */
fun Fragment.savePref(key: String, data: Set<String>) {
    PrefsHelper.putValue(key, data)
}

/**
 * get string preference
 *
 * @param key String  preference key
 * @param data String preference data
 * @return string
 */
fun Fragment.getPref(key: String, data: String) : String {
    return PrefsHelper.getValue(key, data)
}

/**
 * get int preference
 *
 * @param key String  preference key
 * @param data Int preference data
 * @return int
 */
fun Fragment.getPref(key: String, data: Int) : Int {
    return PrefsHelper.getValue(key, data)
}

/**
 * get boolean preference
 *
 * @param key String  preference key
 * @param data boolean preference data
 * @return boolean
 */
fun Fragment.getPref(key: String, data: Boolean) : Boolean {
    return PrefsHelper.getValue(key, data)
}

/**
 * get double preference
 *
 * @param key String  preference key
 * @param data double preference data
 * @return double
 */
fun Fragment.getPref(key: String, data: Double) : Double {
    return PrefsHelper.getValue(key, data)
}

/**
 * get long preference
 *
 * @param key String  preference key
 * @param data long preference data
 * @return long
 */
fun Fragment.getPref(key: String, data: Long) : Long {
    return PrefsHelper.getValue(key, data)
}

/**
 * get float preference
 *
 * @param key String  preference key
 * @param data float preference data
 * @return float
 */
fun Fragment.getPref(key: String, data: Float) : Float {
    return PrefsHelper.getValue(key, data)
}

/**
 * get string preference
 *
 * @param key String  preference key
 * @param data set of string preference data
 * @return set of string
 */
fun Fragment.getPref(key: String, data: Set<String>) : Set<String>? {
    return PrefsHelper.getValue(key, data)
}