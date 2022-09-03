package com.example.learn4ingredients.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.learn4ingredients.R;

public class CustomViewActivity extends AppCompatActivity {

    private Dialog dialog;
    private Button ShowDialog;
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;
    private int CurrentProgressCircle = 0;
    private ProgressBar progressBarCircle;
    private Button StartProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        ShowDialog = findViewById(R.id.dialog_btn);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


        //progress
        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.start_progress);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentProgress = CurrentProgress + 10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
            }
        });

        // progress Circle
        progressBarCircle = findViewById(R.id.progressBarCircle);
        StartProgressCircle = findViewById(R.id.startProgessCircle);

        final CountDownTimer countDownTimer = new CountDownTimer(11*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                CurrentProgress = CurrentProgress + 10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);

            }

            @Override
            public void onFinish() {

            }
        };

        StartProgressCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer.start();
            }
        });
    }
}