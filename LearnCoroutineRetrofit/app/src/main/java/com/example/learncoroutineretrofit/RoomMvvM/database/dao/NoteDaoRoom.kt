package com.example.learncoroutineretrofit.RoomMvvM.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom

@Dao
interface NoteDaoRoom {

    @Insert
    suspend fun insert(noteRoom: NoteRoom)

    @Update
    suspend fun update(noteRoom: NoteRoom)

    @Delete
    suspend fun delete(noteRoom: NoteRoom)

    @Query("select * from note_table")
    fun getAllNote(): LiveData<List<NoteRoom>>

}