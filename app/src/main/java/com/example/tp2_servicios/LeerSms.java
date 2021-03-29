package com.example.tp2_servicios;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class LeerSms extends Service {
    private Uri smsRecibido;
    private ContentResolver contenedor;

    public LeerSms(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       smsRecibido = Uri.parse("content://sms/inbox");
       contenedor = getContentResolver();

       while (true){
           mostrarSms();
           try {
               Thread.sleep(9000);
           } catch (InterruptedException e) {
               e.printStackTrace();
               return super.onStartCommand(intent, flags, startId);
           }
       }
    }

    public void mostrarSms() {

        Uri smsRecibido = Uri.parse("content://sms/inbox");
        ContentResolver contenedor = getContentResolver();
        Cursor cursor = contenedor.query(smsRecibido,null,null,null,null);


        if (cursor.getCount()==0) Log.d("mjeRec","No hay mensajes");

        while (cursor.moveToNext() && cursor.getPosition()<5){

            Log.d("mensaje", " "+"\n num: "+ cursor.getString(2)
                                                        +"\n fecha: "+ cursor.getString(4)
                                                        +"\n Mje: "+cursor.getString(12));


        }
        cursor.close();
   }
}