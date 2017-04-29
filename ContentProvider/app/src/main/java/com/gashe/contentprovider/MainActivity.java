package com.gashe.contentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // TODO: 29/4/17 verificar que el permiso ha sido concedido GRANTED



        ContentResolver contentResolver = getContentResolver();

        /*
        Uri uri_contactos = ContactsContract.Contacts.CONTENT_URI; //content://com.android.contacts/contacts

        Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.DISPLAY_NAME + " = 'Marina'", null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.HAS_PHONE_NUMBER +" = 1", null, null);
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        //String [] prefijo = {"M%"};
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, null, ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?", prefijo, null);

        //String [] columnas = {ContactsContract.Contacts.DISPLAY_NAME};
        //Cursor cursor_contactos = contentResolver.query(uri_contactos, columnas, null, null, null);

        if(cursor_contactos.moveToFirst()){
            Log.d(getClass().getCanonicalName(), "NUMERO DE CONTACTOS: " + cursor_contactos.getCount());

            int numcolid = cursor_contactos.getColumnIndex(ContactsContract.Contacts._ID);
            int numcolname = cursor_contactos.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            Cursor cursor_raw = null;
            Cursor cursor_data = null;
            long idaux = 0;
            String straux = null;

            do{
                idaux = cursor_contactos.getLong(numcolid);
                straux = cursor_contactos.getString(numcolname);

                Log.d(getClass().getCanonicalName(), "CONTACT_ID : " + idaux + " NOMBRE_CONTACTO: " + straux);
                cursor_raw = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, null, ContactsContract.RawContacts.CONTACT_ID + " = " + idaux, null, null);

                if(cursor_raw.moveToFirst()){


                    do {

                        long id_raw_raux = cursor_raw.getLong(cursor_raw.getColumnIndex(ContactsContract.RawContacts._ID));
                        String nombre_cuenta = cursor_raw.getString(cursor_raw.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_NAME));
                        String tipo_cuenta = cursor_raw.getString(cursor_raw.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE));
                        Log.d(getClass().getCanonicalName(), "RAW NOMBRE CUENTA: " + nombre_cuenta + " TIPO CUENTA: " + tipo_cuenta);
                        cursor_data = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.Data.RAW_CONTACT_ID + "=" + id_raw_raux, null, null);

                        if(cursor_data.moveToFirst()){

                            do {
                                String mime_type = cursor_data.getString(cursor_data.getColumnIndex(ContactsContract.Data.MIMETYPE));
                                String data = cursor_data.getString(cursor_data.getColumnIndex(ContactsContract.Data.DATA1));

                                Log.d(getClass().getCanonicalName(), "DATA MIME: " + mime_type + " DATA 1: " + data);

                            }while(cursor_data.moveToNext());

                        }

                        cursor_data.close();

                    }while (cursor_raw.moveToNext());
                }

                cursor_raw.close();

            }while (cursor_contactos.moveToNext());

        }

        cursor_contactos.close();


        */

        // consulta solo de los telefonos
        Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while(phones.moveToNext()){
            String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            Log.d(getClass().getCanonicalName(), "TELEFONO: " + number + " TYPE: " + type);
        }

        phones.close();

    }
}
