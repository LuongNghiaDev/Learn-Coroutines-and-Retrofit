package com.example.learn4ingredients.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AirPlaneBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(AirPlaneBroadCast.class.getSimpleName(), "AirPlane");
    }
}
