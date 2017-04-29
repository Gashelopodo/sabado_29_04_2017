package com.gashe.apptotal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class ChequeoService extends Service {

    private String mensaje_remoto;

    public ChequeoService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private String obtenerMensajeRemoto(){

        String str_dev = null;

        str_dev = (Math.random() > 0.5) ? "HOLA PERICO" : "";

        return str_dev; // aquí llamaría al asynctask.get{}
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //aquí iría el código que ejecuta el servicio

        Log.d(getClass().getCanonicalName(), "Servicio iniciado, StartID: " + startId);

        mensaje_remoto = obtenerMensajeRemoto();
        stopSelf(startId); // matar al servicio a pelo

        return Service.START_NOT_STICKY; // cuando el sistema mate al servicio le indico que no lo reinicie
    }

    @Override
    public void onDestroy() {
        //código al finalizar el servicio
        Log.d(getClass().getCanonicalName(), "Servicio detenido");
        super.onDestroy();

        Intent intent_receiver = new Intent("SERVICIO_TERMINADO");
        intent_receiver.putExtra("MENSAJE", mensaje_remoto);

        sendBroadcast(intent_receiver);

    }
}
