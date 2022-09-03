package com.example.learncoroutineretrofit.RoomMvvM.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
class NoteRoom(
    @ColumnInfo(name = "title_col") var title: String = "",
    @ColumnInfo(name = "des_col") var des: String = ""
): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name =  "note_id_col")
    var id: Int = 0
}