package com.kks.techflittertestkirit.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.extension.getViewModel
import com.kks.techflittertestkirit.extension.hideKeyboard
import com.kks.techflittertestkirit.extension.savePref
import com.kks.techflittertestkirit.localdatabase.viewmodel.ViewModel

/**
 * Main Activity
 *
 * @author Kirit Khant
 * @since 05-11-2020
 */
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var host : NavHostFragment
    private lateinit var navController : NavController
    private lateinit var navGraph : NavGraph
    lateinit var mViewModel: ViewModel

    //////////////////////////////////////////////////// Overridden Methods ///////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        hideKeyboard()
    }

    ///////////////////////////////////////////// Private Methods /////////////////////////////////////////////////////

    private fun init() {
        mViewModel = getViewModel()
        setNavHost()

        savePref(Constant.PREF_CREATE_API_CALL, true)
        savePref(Constant.PREF_SELECTED_USER, "")
    }

    /*
        method to set navHost
     */
    private fun setNavHost() {
        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment??: return

        navController = host.navController
        navGraph = navController.graph
        navController.addOnDestinationChangedListener(this)
    }
}