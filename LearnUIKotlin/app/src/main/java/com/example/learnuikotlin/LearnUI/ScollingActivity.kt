package com.example.learnuikotlin.LearnUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnuikotlin.R
import com.q42.android.scrollingimageview.ScrollingImageView

class ScollingActivity : AppCompatActivity() {

    private lateinit var siv: ScrollingImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scolling)

        siv = findViewById(R.id.siv)
        siv.start()
    }
}