package com.example.learn4ingredients.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBoundService extends Service {

    private final LocalBinder localBinder = new LocalBinder();
    public static final String TAG = MyBoundService.class.getSimpleName();

    class LocalBinder extends Binder {

        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: run on"+ Thread.currentThread().getName());
        return localBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: run on"+ Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: run on"+ Thread.currentThread().getName());
    }

    public int sum(int a, int b) {
        return a+b;
    }

    public int sub(int a, int b) {
        return a-b;
    }
}
