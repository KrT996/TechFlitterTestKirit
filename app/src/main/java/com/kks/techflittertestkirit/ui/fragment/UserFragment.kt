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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.adapter.UserAdapter
import com.kks.techflittertestkirit.api.DeleteUserApiViewModel
import com.kks.techflittertestkirit.api.UserApiViewModel
import com.kks.techflittertestkirit.constant.Constant
import com.kks.techflittertestkirit.extension.getViewModel
import com.kks.techflittertestkirit.extension.savePref
import com.kks.techflittertestkirit.extension.showProgressLoader
import com.kks.techflittertestkirit.listener.OnRecyclerItemClick
import com.kks.techflittertestkirit.model.UserItem
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * User Fragment
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */
class UserFragment : Fragment(), OnRecyclerItemClick, View.OnClickListener {

    private lateinit var mView: View
    private lateinit var adapter : UserAdapter
    private val list = ArrayList<UserItem>()

    private lateinit var deleteUserApiViewModel: DeleteUserApiViewModel

    private var pos = 0

    ///////////////////////////////////////////////// Overridden Methods ////////////////////////////////////////////////////

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnCreateUser -> createUserClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    override fun onRecyclerItemClick(view: View, position: Int, type: String) {
        pos = position
        when(view.id) {
            R.id.clUser -> userClicked(position)
            R.id.btnDelete -> deleteClicked()
        }
    }

    //////////////////////////////////////////////// Private Methods ////////////////////////////////////////////////////////

    private fun init() {
        adapter = UserAdapter(list, activity as AppCompatActivity, this)
        deleteUserApiViewModel = ViewModelProvider(this).get(DeleteUserApiViewModel::class.java)

        setRecyclerView()
        setClickListeners()
        setRadio()
        getList()
    }

    /*
        method to set recyclerView
     */
    private fun setRecyclerView() {
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(activity as AppCompatActivity, RecyclerView.VERTICAL, false)
    }

    /*
        method to set clickListeners
     */
    private fun setClickListeners() {
        btnCreateUser.setOnClickListener(this)
    }

    /*
        method to set radio buttons
     */
    private fun setRadio() {
        rgSort.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbName -> sortByName()
                R.id.rbEmail -> sortByEmail()
            }
        }
    }

    /*
        method to get list
     */
    private fun getList() {
        getViewModel()?.mAllUser?.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
                sortByName()
            }
        })
    }

    /*
        method to sort list by name
     */
    private fun sortByName() {
        list.sortBy { it.name }
        adapter.notifyDataSetChanged()
    }

    /*
        method to sort list by mail
     */
    private fun sortByEmail() {
        list.sortBy { it.email }
        adapter.notifyDataSetChanged()
    }

    /*
        create user click
     */
    private fun createUserClick() {
        findNavController().navigate(R.id.action_userDetailsFragment_to_createUserFragment)
    }

    /*
        on list user click
     */
    private fun userClicked(position: Int) {

        /*for (i in list) {
            i.isClicked = false
        }
        list[position].isClicked = true*/
        getViewModel()?.updateUserById(list[position].id.toString())
        savePref(Constant.PREF_SELECTED_USER, list[position].id.toString())
        //adapter.notifyDataSetChanged()
    }

    /*
        on delete user click
     */
    private fun deleteClicked() {
        apiCallDeleteUser()
    }

    ///////////////////////////////////////////////////////// Api Calls ///////////////////////////////////////////////////

    /**
     * API call to delete user
     */
    private fun apiCallDeleteUser() {
        deleteUserApiViewModel.callRunning.observe(viewLifecycleOwner, Observer { value ->
            value?.let { data ->
                showProgressLoader(data.isRunning)
            }
        })

        deleteUserApiViewModel
            .deleteUser(0).observe(
                viewLifecycleOwner,
                Observer { value ->
                    value?.let { data ->
                        if (data != null) {

                            Log.e("User Data", "" + data)

                            Toast.makeText(activity as AppCompatActivity, R.string.msg_user_deleted, Toast.LENGTH_SHORT).show()

                            getViewModel()?.deleteUserById(list[pos].id.toString())

                            Log.e("Call","call success")
                        } else {
                            Log.e("Call","call fail")
                        }
                    }
                })
    }
}