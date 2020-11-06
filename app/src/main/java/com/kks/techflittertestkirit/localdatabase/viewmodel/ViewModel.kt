package com.kks.techflittertestkirit.localdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kks.techflittertestkirit.localdatabase.TestProjectRoomDb
import com.kks.techflittertestkirit.localdatabase.repository.UserDataRepo
import com.kks.techflittertestkirit.model.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel
 *
 * @author : Kirit Khant
 * @since : 31-10-2020
 */
class ViewModel(application: Application) : AndroidViewModel(application) {

    private val userDataRepo: UserDataRepo

    val mAllUser: LiveData<List<UserItem>>


    init {
        val userDao = TestProjectRoomDb.getDatabase(application).userDao()

        userDataRepo = UserDataRepo(userDao)

        mAllUser = userDataRepo.allData
    }

    fun insertAllUser(data: ArrayList<UserItem>) = viewModelScope.launch(Dispatchers.IO) {
            userDataRepo.insert(data)
        }

    fun deleteAllUser() = viewModelScope.launch(Dispatchers.IO) {
        userDataRepo.deleteData()
    }

    fun getUserFromId(id : String) : LiveData<UserItem> {
        return userDataRepo.getUserFromId(id)
    }

    fun insertUser(data : UserItem) = viewModelScope.launch(Dispatchers.IO) {
        userDataRepo.insertUser(data)
    }

    fun deleteUserById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        userDataRepo.deleteUserById(id)
    }

    fun updateUserById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        userDataRepo.updateUser(id)
    }

}