package com.kks.techflittertestkirit.localdatabase.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.kks.techflittertestkirit.localdatabase.dao.UserDao
import com.kks.techflittertestkirit.model.UserItem

class UserDataRepo(private val userDao: UserDao) {

    val allData: LiveData<List<UserItem>> = userDao.getAllUsers()

    @WorkerThread
    suspend fun insert(data: List<UserItem>) {
        userDao.deleteAll()
        userDao.insertAll(data)
    }

    fun getUserFromId(id : String) : LiveData<UserItem> {
        return userDao.getUserFromId(id)
    }

    fun deleteData() {
        userDao.deleteAll()
    }

    @WorkerThread
    suspend fun insertUser(data : UserItem) {
        userDao.insertUser(data)
    }

    fun deleteUserById(id: String) {
        userDao.deleteUserById(id)
    }

    @WorkerThread
    suspend fun updateUser(id: String) {
        userDao.resetIsClicked()
        userDao.updateUserById(id)
    }
}