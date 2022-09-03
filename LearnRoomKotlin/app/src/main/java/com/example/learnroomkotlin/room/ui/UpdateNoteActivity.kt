package com.example.learnroomkotlin.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.learnroomkotlin.R
import com.example.learnroomkotlin.databinding.ActivityAddNoteBinding
import com.example.learnroomkotlin.databinding.ActivityUpdateNoteBinding
import com.example.learnroomkotlin.room.db.NoteDatabase
import com.example.learnroomkotlin.room.db.NoteEntity
import com.example.learnroomkotlin.room.utils.Constants
import com.google.android.material.snackbar.Snackbar

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity
    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId = it.getInt(Constants.BUNDLE_NOTE_ID)
        }

        binding.apply {
            defaultTitle = noteDB.noteDao().getNote(noteId).noteTitle
            defaultDesc = noteDB.noteDao().getNote(noteId).noteDesc

            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteId, defaultTitle, defaultDesc)
                noteDB.noteDao().deleteNote(noteEntity)
                finish()
            }

            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if(title.isNotEmpty() || desc.isNotEmpty()) {
                    noteEntity = NoteEntity(noteId, title, desc)
                    noteDB.noteDao().updateNote(noteEntity)
                    finish()

                } else {
                    Snackbar.make(it, "Please enter title or desc", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }
}