package com.example.learncoroutineretrofit.RoomMvvM.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learncoroutineretrofit.RoomMvvM.database.dao.NoteDaoRoom
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom

@Database(entities = [NoteRoom::class], version = 1)
abstract class NoteDatabaseRoom: RoomDatabase() {

    abstract fun getNoteDao(): NoteDaoRoom

    companion object {
        @Volatile
        var INSTANCE: NoteDatabaseRoom? = null

        @Synchronized //đảm bảo rằng chỉ một luồng có thể thực thi mã
        fun getInstance(context: Context):NoteDatabaseRoom {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDatabaseRoom::class.java,
                    "note.db"
                ).fallbackToDestructiveMigration() //muốn xây lại csdl nhưng vẫn giữ dl bên trong csdl đó
                    .build()
            }
            return INSTANCE as NoteDatabaseRoom
        }
    }
}