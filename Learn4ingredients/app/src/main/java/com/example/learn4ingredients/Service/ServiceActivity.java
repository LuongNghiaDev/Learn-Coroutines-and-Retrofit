package com.example.learn4ingredients.Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learn4ingredients.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void StopService(View view) {
        Intent intent = new Intent(this, MyStartedService.class);
        stopService(intent);
    }

    public void StartService(View view) {
        Intent intent = new Intent(this, MyStartedService.class);
        startService(intent);
    }

}