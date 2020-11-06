package com.kks.techflittertestkirit.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.api.CreateUserApiViewModel
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.extension.getViewModel
import com.kks.techflittertestkirit.extension.showProgressLoader
import com.kks.techflittertestkirit.model.UserItem
import com.kks.techflittertestkirit.ui.activity.MainActivity
import com.kks.techflittertestkirit.util.Utils
import kotlinx.android.synthetic.main.fragment_create_user.*

/**
 * Create User Fragment
 *
 * @author : Kirit Khant
 * @since : 06-11-2020
 */
class CreateUserFragment : Fragment(), View.OnClickListener {

    private lateinit var mView: View
    private lateinit var createUserApiViewModel : CreateUserApiViewModel
    private var gender = "Male"
    private var status = "Active"

    ///////////////////////////////////////////////////// Overridden Methods ///////////////////////////////////////////////

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSubmit -> submitBtnClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_create_user, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    ///////////////////////////////////////////////// Private Methods /////////////////////////////////////////////////////

    private fun init() {

        createUserApiViewModel = ViewModelProvider(this).get(CreateUserApiViewModel::class.java)

        setClickListeners()
        setRadios()
    }

    /*
        method to set click listeners
     */
    private fun setClickListeners() {
        btnSubmit.setOnClickListener(this)
    }

    /*
        method to set radio buttons
     */
    private fun setRadios() {
        rgGender.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbMale -> gender = "Male"
                R.id.rbFemale -> gender = "Female"
            }
        }

        rgStatus.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbActive -> status = "Active"
                R.id.rbInActive -> status = "Inactive"
            }
        }
    }

    /*
        submit btn click
     */
    private fun submitBtnClick() {
        if (checkValidations()) {
            apiCallCreateUser()
        }
    }

    /**
     * mathod to check validations
     */
    private fun checkValidations(): Boolean {

        if (edtName.text?.toString().isNullOrEmpty()) {
            edtName.error = getText(R.string.msg_add_name)
            return false
        }

        if (edtEmail.text?.toString().isNullOrEmpty() || !Utils.isEmailValid(edtEmail.text.toString())) {
            edtEmail.error = getText(R.string.msg_add_email)
            return false
        }

        return true
    }

    /**
     * method to get parameters for api call
     */
    private fun getParams(): MutableMap<String, String> {
        val params: MutableMap<String, String> = mutableMapOf()

        params[Constant.NAME] = edtName.text.toString()
        params[Constant.EMAIL] = edtEmail.text.toString()
        params[Constant.GENDER] = gender
        params[Constant.STATUS] = status

        return params
    }

    ///////////////////////////////////////////////////////// Api Calls ///////////////////////////////////////////////////

    /**
     * API call to fetch UserDetails
     */
    private fun apiCallCreateUser() {
        createUserApiViewModel.callRunning.observe(viewLifecycleOwner, Observer { value ->
            value?.let { data ->
                //  showBtnLoader(data.isRunning, pbLoader, llSignIn)

                showProgressLoader(data.isRunning)
            }
        })

        createUserApiViewModel
            .createUser(0, getParams()).observe(
                viewLifecycleOwner,
                Observer { value ->
                    value?.let { data ->
                        if (data != null) {

                            Log.e("User Data", "" + data)

                            Toast.makeText(activity as AppCompatActivity, R.string.msg_user_created, Toast.LENGTH_SHORT).show()

                            val mData = Gson().fromJson(data.mData, UserItem::class.java)

                            getViewModel()?.insertUser(mData)

                            (activity as MainActivity).onBackPressed()

                            Log.e("Call","call success")
                        } else {
                            Log.e("Call","call fail")
                        }
                    }
                })
    }
}