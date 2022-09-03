package com.example.learncoroutineretrofit.RoomMvvM.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.learncoroutineretrofit.RoomMvvM.database.NoteDatabaseRoom
import com.example.learncoroutineretrofit.RoomMvvM.database.dao.NoteDaoRoom
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom

class NoteRepositoryRoom(app: Application) {

    private val noteDaoRoom: NoteDaoRoom

    init {
        val noteDatabaseRoom: NoteDatabaseRoom = NoteDatabaseRoom.getInstance(app)
        noteDaoRoom = noteDatabaseRoom.getNoteDao()
    }

    suspend fun insert(noteRoom: NoteRoom) = noteDaoRoom.insert(noteRoom)
    suspend fun update(noteRoom: NoteRoom) = noteDaoRoom.update(noteRoom)
    suspend fun delete(noteRoom: NoteRoom) = noteDaoRoom.delete(noteRoom)

    fun getAllNote(): LiveData<List<NoteRoom>> = noteDaoRoom.getAllNote()
}