package com.example.learncoroutineretrofit.RoomMvvM.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.learncoroutineretrofit.R
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom
import com.example.learncoroutineretrofit.RoomMvvM.viewmodel.NoteViewModelRoom
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private val noteViewModelRoom: NoteViewModelRoom by lazy {
        ViewModelProvider(
            this,
            NoteViewModelRoom.NoteViewModelFactory(this.application)
        )[NoteViewModelRoom::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnAdd.setOnClickListener {
            val note = NoteRoom(edtTitle.text.toString(), edtDesc.text.toString())
            noteViewModelRoom.insert(note)
            finish()
        }
    }
}