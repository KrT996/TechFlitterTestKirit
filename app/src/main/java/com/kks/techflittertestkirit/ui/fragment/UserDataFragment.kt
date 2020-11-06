package com.kks.techflittertestkirit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.extension.getPref
import com.kks.techflittertestkirit.extension.getViewModel
import kotlinx.android.synthetic.main.fragment_user_data.*

/**
 * User Data Fragment
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class UserDataFragment : Fragment() {

    private lateinit var mView: View
    private var userId = ""

    /////////////////////////////////////////////// Overridden Methods /////////////////////////////////////////////////////////

    override fun onResume() {
        super.onResume()

        userId = getPref(Constant.PREF_SELECTED_USER, "")
        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user_data, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    ////////////////////////////////////////////////// Private Functions ////////////////////////////////////////////////////////

    private fun init() {
        userId = getPref(Constant.PREF_SELECTED_USER, "")

        setData()
    }

    /*
        method to set data
     */
    private fun setData() {
        if (userId != "") {
            clUserData.visibility = View.VISIBLE

            getViewModel()?.getUserFromId(userId)?.observe(viewLifecycleOwner, Observer { data ->
                data?.let {
                    if (data != null) {
                        tvId.text = "Id : " + data.id
                        tvName.text = "Name : " + data.name
                        tvEmail.text = "Email : " + data.email
                        tvGender.text = "Gender : " + data.gender
                        tvStatus.text = "Status : " + data.status
                        tvCreatedAt.text = "Created At : " + data.createdAt
                        tvUpdatedAt.text = "Updated At : " + data.updatedAt
                    } else {

                    }
                }
            })
        } else {
            clUserData.visibility = View.GONE
        }

    }
}