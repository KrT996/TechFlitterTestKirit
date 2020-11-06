package com.kks.techflittertestkirit.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.adapter.TabAdapter
import com.kks.techflittertestkirit.api.UserApiViewModel
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.extension.*
import com.kks.techflittertestkirit.model.UserItem
import kotlinx.android.synthetic.main.fragment_user_details.*

/**
 * User Details Fragment
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class UserDetailsFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var adapter: TabAdapter
    private lateinit var userApiViewModel : UserApiViewModel

    private var isApiCallNeeded = true

    /////////////////////////////////////////////////// Overridden Methods //////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user_details, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    /////////////////////////////////////////////////// Private Methods ////////////////////////////////////////////////////////

    private fun init() {
        userApiViewModel = ViewModelProvider(this).get(UserApiViewModel::class.java)

        isApiCallNeeded = getPref(Constant.PREF_CREATE_API_CALL, true)

        showToolbar(true)
        setToolbarTitle(getString(R.string.txt_user_details))

        addFragments()
        setClickListeners()

        if (isApiCallNeeded) {
            apiCallGetUsers()
        }
    }

    /*
        click listeners
     */
    private fun setClickListeners() {

    }

    /*
        method to set up viewpager
     */
    private fun addFragments() {

        tabLayout.setupWithViewPager(viewPager)

        adapter = TabAdapter(childFragmentManager)

        adapter.addFragment(UserFragment(), getString(R.string.txt_user))
        adapter.addFragment(UserDataFragment(), getString(R.string.txt_user_data))

        viewPager.adapter = adapter

        viewPager.offscreenPageLimit

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //currentPos = position
            }

            override fun onPageSelected(position: Int) {

                viewPager.currentItem = position

                /*val oldPosition = viewPager.currentItem
                val oldFragment : Fragment = viewPager.adapter?.instantiateItem(viewPager, oldPosition) as Fragment
                oldFragment.onPause() // Hint: do as here call onPause
                val newFragment = viewPager.adapter?.instantiateItem(viewPager, position) as Fragment
                newFragment.onResume() // Hint: do as here call onResume*/

            }

        })
    }

    ///////////////////////////////////////////////////////// Api Calls ///////////////////////////////////////////////////

    /**
     * API call to fetch UserDetails
     */
    private fun apiCallGetUsers() {
        userApiViewModel.callRunning.observe(viewLifecycleOwner, Observer { value ->
            value?.let { data ->
                //  showBtnLoader(data.isRunning, pbLoader, llSignIn)

                showProgressLoader(data.isRunning)
            }
        })

        userApiViewModel
            .getAllUsers(0).observe(
                viewLifecycleOwner,
                Observer { value ->
                    value?.let { data ->
                        if (data != null) {

                            Log.e("User Data", "" + data)

                            val gson = Gson()
                            val userDetails = gson.fromJson(data.mData, Array<UserItem>::class.java)

                            getViewModel()?.insertAllUser(ArrayList(userDetails.asList()))

                            savePref(Constant.PREF_CREATE_API_CALL, false)

                            Log.e("Call","call success")
                        } else {
                            Log.e("Call","call fail")
                        }
                    }
                })
    }
}