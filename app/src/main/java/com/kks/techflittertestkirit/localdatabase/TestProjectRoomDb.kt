package com.kks.techflittertestkirit.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kks.techflittertestkirit.localdatabase.dao.UserDao
import com.kks.techflittertestkirit.model.UserItem


/**
 * Room Database Class
 *
 * @author : Kirit Khant
 * @since : 05-11-2020
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Database(
    entities = [UserItem::class], version = 1)

abstract class TestProjectRoomDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: TestProjectRoomDb? = null

        fun getDatabase(context: Context): TestProjectRoomDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestProjectRoomDb::class.java,
                    "TestProjectRoomDb"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}