package com.example.learncoroutineretrofit.RoomMvvM.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learncoroutineretrofit.RoomMvvM.database.repository.NoteRepositoryRoom
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModelRoom(app: Application): ViewModel() {

    private val noteRepositoryRoom: NoteRepositoryRoom = NoteRepositoryRoom(app)

    fun insert(noteRoom: NoteRoom) = viewModelScope.launch {
        noteRepositoryRoom.insert(noteRoom)
    }

    fun update(noteRoom: NoteRoom) = viewModelScope.launch {
        noteRepositoryRoom.update(noteRoom)
    }

    fun delete(noteRoom: NoteRoom) = viewModelScope.launch {
        noteRepositoryRoom.delete(noteRoom)
    }

    fun getAllNote(): LiveData<List<NoteRoom>> = noteRepositoryRoom.getAllNote()

    class NoteViewModelFactory(private val application: Application): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NoteViewModelRoom::class.java)) {
                return NoteViewModelRoom(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }

}