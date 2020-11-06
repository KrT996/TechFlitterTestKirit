package com.kks.techflittertestkirit.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.extension.setStatusBarColor
import com.kks.techflittertestkirit.extension.showToolbar

/**
 * Splash Fragment
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class SplashFragment : Fragment() {

    private lateinit var mView: View

    ////////////////////////////////////////////////////// Overridden Methods /////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_splash, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    ////////////////////////////////////////////////////// Private Methods ////////////////////////////////////////////////////////

    private fun init() {
        // Basic Settings
        setStatusBarColor(R.color.colorPrimary)
        showToolbar(false) //hides action Bar
        setSplashFrag()
    }

    /*
        method to open next screen
     */
    private fun setSplashFrag() {
        Handler().postDelayed({
            if (isAdded) {
                findNavController().navigate(R.id.action_splashFragment_to_userDetailsFragment)
            }
        }, 2000)
    }

}