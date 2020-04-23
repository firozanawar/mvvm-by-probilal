package com.firozanwar.mvvmbybilal.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firozanwar.mvvmbybilal.data.db.entities.CURRENT_USER_ID
import com.firozanwar.mvvmbybilal.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long // inserted row id as a long

    @Query("SELECt * from user WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}