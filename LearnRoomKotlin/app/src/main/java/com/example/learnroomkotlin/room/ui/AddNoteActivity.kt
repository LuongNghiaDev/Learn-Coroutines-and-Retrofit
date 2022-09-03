package com.example.learnroomkotlin.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.learnroomkotlin.R
import com.example.learnroomkotlin.databinding.ActivityAddNoteBinding
import com.example.learnroomkotlin.room.db.NoteDatabase
import com.example.learnroomkotlin.room.db.NoteEntity
import com.example.learnroomkotlin.room.utils.Constants
import com.google.android.material.snackbar.Snackbar

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddNoteBinding
    private lateinit var noteEntity: NoteEntity
    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if(title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(0, title, desc)
                    noteDB.noteDao().insertNote(noteEntity)
                    finish()

                } else {
                    Snackbar.make(it, "Please enter title or desc", Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }
}