package com.gashe.apptotal;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SERVICIO_TERMINADO");

        BroadCastReceiver1 br = new BroadCastReceiver1();
        registerReceiver(br, intentFilter); // asocio el receiver al SERVICIO TERMINADO

        Intent intent_service = new Intent(this, ChequeoService.class);
        startService(intent_service);


    }
}
