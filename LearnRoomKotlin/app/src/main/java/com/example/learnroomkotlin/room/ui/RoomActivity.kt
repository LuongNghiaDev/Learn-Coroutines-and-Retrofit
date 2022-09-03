package com.example.learnroomkotlin.room.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.learnroomkotlin.R
import com.example.learnroomkotlin.databinding.ActivityRoomBinding
import com.example.learnroomkotlin.room.adapter.NoteAdapter
import com.example.learnroomkotlin.room.db.NoteDatabase
import com.example.learnroomkotlin.room.utils.Constants

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private val noteDB: NoteDatabase by lazy {
        Room.databaseBuilder(this, NoteDatabase::class.java, Constants.NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this@RoomActivity, AddNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    private fun checkItem() {
        binding.apply {
            if(noteDB.noteDao().getAllNotes().isNotEmpty()) {
                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
                noteAdapter.differ.submitList(noteDB.noteDao().getAllNotes())
                setUpRecyclerView()
            } else {
                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager = LinearLayoutManager(this@RoomActivity)
            adapter = noteAdapter
        }
    }

}