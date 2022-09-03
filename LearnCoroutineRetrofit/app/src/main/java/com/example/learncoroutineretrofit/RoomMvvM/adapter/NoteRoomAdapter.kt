package com.example.learncoroutineretrofit.RoomMvvM.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.learncoroutineretrofit.R
import com.example.learncoroutineretrofit.RoomMvvM.model.NoteRoom
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteRoomAdapter(
    private val context: Context,
    private val onClick: (NoteRoom) -> Unit,
    private val onDelete: (NoteRoom) -> Unit
):RecyclerView.Adapter<NoteRoomAdapter.MyHolder>() {

    private var noteRooms: List<NoteRoom> = listOf()

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var txtTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var txtDesc: TextView = itemView.findViewById(R.id.tvDesc)
        private var btnDelete: FloatingActionButton = itemView.findViewById(R.id.btnDelete)
        private var layoutItem: ConstraintLayout = itemView.findViewById(R.id.note_item_layout)
        fun onBind(noteRoom: NoteRoom) {
            txtTitle.text = noteRoom.title
            txtDesc.text = noteRoom.des

            btnDelete.setOnClickListener { onDelete(noteRoom) }
            layoutItem.setOnClickListener { onClick(noteRoom) }
        }
    }

    fun setNote(noteRooms: List<NoteRoom>) {
        this.noteRooms = noteRooms
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRoomAdapter.MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NoteRoomAdapter.MyHolder, position: Int) {
        holder.onBind(noteRooms[position])
    }

    override fun getItemCount(): Int {
        return noteRooms.size
    }
}