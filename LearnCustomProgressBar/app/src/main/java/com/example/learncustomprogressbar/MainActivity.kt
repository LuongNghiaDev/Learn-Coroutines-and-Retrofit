package com.example.learncustomprogressbar

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var progr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateProgressBar()

        btn_incr.setOnClickListener {
            if(progr <= 90) {
                progr += 10
                updateProgressBar()
            }
        }
        btn_decr.setOnClickListener {
            if(progr >= 10) {
                progr -= 10
                updateProgressBar()
            }
        }

        btn_gradient.setOnClickListener {
            startActivity(Intent(this@MainActivity, GradientActivity::class.java))
        }

        btn_dialog.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.custom_dialog, null)

            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()

            val btnDialog = dialogBinding.findViewById<Button>(R.id.btnOk)
            btnDialog.setOnClickListener {
                myDialog.dismiss()
            }

        }
    }

    private fun updateProgressBar() {
        progressBar.progress = progr
        txtProgressBar.text = "$progr%"
    }
}