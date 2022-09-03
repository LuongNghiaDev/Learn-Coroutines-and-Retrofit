package com.example.learncoroutineretrofit.RoomMvvM.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.learncoroutineretrofit.R
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom
import com.example.learncoroutineretrofit.RoomMvvM.viewmodel.NoteViewModelRoom
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private val noteViewModelRoom: NoteViewModelRoom by lazy {
        ViewModelProvider(
            this,
            NoteViewModelRoom.NoteViewModelFactory(this.application)
        )[NoteViewModelRoom::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val note = intent.getSerializableExtra("UPDATE_NOTE") as NoteRoom
        edtTitle.setText(note.title)
        edtDesc.setText(note.des)

        btnUpdate.setOnClickListener {
            note.title = edtTitle.text.toString()
            note.des = edtDesc.text.toString()
            noteViewModelRoom.update(note)
            finish()
        }
    }
}