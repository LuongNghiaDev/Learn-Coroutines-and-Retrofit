package com.example.learncoroutineretrofit.RoomMvvM.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learncoroutineretrofit.R
import com.example.learncoroutineretrofit.RoomMvvM.adapter.NoteRoomAdapter
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom
import com.example.learncoroutineretrofit.RoomMvvM.viewmodel.NoteViewModelRoom
import kotlinx.android.synthetic.main.activity_note_room.*

class NoteRoomActivity : AppCompatActivity() {

    private val noteViewModelRoom: NoteViewModelRoom by lazy {
        ViewModelProvider(
            this,
            NoteViewModelRoom.NoteViewModelFactory(this.application)
        )[NoteViewModelRoom::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_room)

        initControls()
        initEvent()
    }

    private fun initEvent() {
        btnAddNoteMain.setOnClickListener {
            startActivity(Intent(this , AddActivity::class.java))
        }
    }

    private fun initControls() {
        val roomAdapter: NoteRoomAdapter = NoteRoomAdapter(this, onItemClick, onDelete)
        rvNoteList.setHasFixedSize(true)
        rvNoteList.layoutManager = LinearLayoutManager(this)
        rvNoteList.adapter = roomAdapter

        noteViewModelRoom.getAllNote().observe(this, Observer {
            roomAdapter.setNote(it)
        })
    }

    private val onItemClick: (NoteRoom) -> Unit = {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)
    }

    private val onDelete: (NoteRoom) -> Unit = {
        noteViewModelRoom.delete(it)
    }
}