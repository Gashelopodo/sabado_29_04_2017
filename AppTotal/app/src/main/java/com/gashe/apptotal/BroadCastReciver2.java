package com.gashe.apptotal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadCastReciver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try
        {
            Intent intent_serv = new Intent(context, ChequeoService.class);

            context.startService(intent_serv);
        }
        catch (Throwable t)
        {
            Log.e(getClass().getCanonicalName(), "ERRORAZO", t);
        }


    }
}
