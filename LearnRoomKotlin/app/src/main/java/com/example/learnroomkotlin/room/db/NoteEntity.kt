package com.example.learnroomkotlin.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnroomkotlin.room.utils.Constants

@Entity(tableName = Constants.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId:Int,
    @ColumnInfo(name = "note_title")
    val noteTitle: String,
    @ColumnInfo(name = "note_desc")
    val noteDesc: String
)
