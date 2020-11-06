package com.kks.techflittertestkirit.localdatabase.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kks.techflittertestkirit.model.UserItem


@Dao
interface UserDao {

    @Query("SELECT * from User")
    fun getAllUsers(): LiveData<List<UserItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<UserItem>)

    @Query("SELECT * FROM User WHERE id =:id")
    fun getUserFromId(id : String) : LiveData<UserItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(data : UserItem)

    @Query("DELETE FROM User WHERE id=:id")
    fun deleteUserById(id: String)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("UPDATE User SET isClicked=0")
    fun resetIsClicked()

    @Query("UPDATE User SET isClicked = 1 WHERE id = :id")
    fun updateUserById(id: String)
}
