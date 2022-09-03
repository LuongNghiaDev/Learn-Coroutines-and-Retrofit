package com.example.learn4ingredients.BroadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.learn4ingredients.R;

public class BroadCastActivity extends AppCompatActivity {

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        localBroadcastManager = LocalBroadcastManager.getInstance(BroadCastActivity.this);
        IntentFilter intentFilter = new IntentFilter("com.example.learn4ingredients.BroadcastReceiver.localbroadcast");
        localBroadcastManager.registerReceiver(myReceiver,intentFilter);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(BroadCastActivity.class.getSimpleName(), "Local broadcast");
        }
    };

    public void sendLocalBroadCast() {
        Intent intent = new Intent("com.example.learn4ingredients.BroadcastReceiver.localbroadcast");
        localBroadcastManager.sendBroadcast(intent);
    }
}