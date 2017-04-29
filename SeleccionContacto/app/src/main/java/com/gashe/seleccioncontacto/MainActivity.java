package com.gashe.seleccioncontacto;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        Cursor cursor = null;
        String phone = null;
        String nombre = null;
        Uri uri = null;

        if(resultCode == RESULT_OK){

            try{

                uri = data.getData();
                Log.d(getClass().getCanonicalName(), "URI_CONTACTO: " + uri);

                cursor = getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();

                int numcoltel = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int numcolnombre = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

                phone = cursor.getString(numcoltel);
                nombre = cursor.getString(numcolnombre);

                TextView textView = (TextView) findViewById(R.id.nombrecontacto);
                textView.setText(nombre);

                TextView textView2 = (TextView) findViewById(R.id.telcontacto);
                textView2.setText(phone);

                cursor.close();

            }catch(Exception e){

                Log.e(getClass().getCanonicalName(), "ERROR ", e);

            }

        }else{
            Log.e(getClass().getCanonicalName(), "Algo ha ido mal");
        }

    }

}
