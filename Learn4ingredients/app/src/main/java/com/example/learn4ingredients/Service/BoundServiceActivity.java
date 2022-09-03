package com.example.learn4ingredients.Service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learn4ingredients.R;

public class BoundServiceActivity extends AppCompatActivity {

    private EditText edt1, edt2;
    private TextView txt;
    private MyBoundService myBoundService;
    private boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        edt1.findViewById(R.id.edt1);
        edt2.findViewById(R.id.edt2);
        txt.findViewById(R.id.textView);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.LocalBinder localBinder = (MyBoundService.LocalBinder) iBinder;
            myBoundService = localBinder.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(!isBind) {
            Intent intent = new Intent(this, MyBoundService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBind)
            unbindService(connection);
    }

    public void sub(View view) {
        int a = Integer.parseInt(edt1.getText().toString());
        int b = Integer.parseInt(edt2.getText().toString());
        txt.setText(("" +myBoundService.sum(a,b)));
    }

    public void sum(View view) {
        int a = Integer.parseInt(edt1.getText().toString());
        int b = Integer.parseInt(edt2.getText().toString());
        txt.setText(("" +myBoundService.sub(a,b)));
    }
}