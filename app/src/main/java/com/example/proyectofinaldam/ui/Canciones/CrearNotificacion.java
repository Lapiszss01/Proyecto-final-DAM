package com.example.proyectofinaldam.ui.Canciones;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.proyectofinaldam.R;

public class CrearNotificacion {

    public static final String CANAL_ID = "canal1";

    public static Notification notificacion;

    public static void crearNotificacion(Context context, String titulo, String artista, int id) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

            Bitmap icono = BitmapFactory.decodeResource(context.getResources(), R.drawable.cancion);


            //Creamos la notificacion

            notificacion = new NotificationCompat.Builder(context, CANAL_ID)
                    .setSmallIcon(R.drawable.clave)
                    .setContentTitle(titulo)
                    .setContentText(artista)
                    .setLargeIcon(icono)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            notificationManagerCompat.notify(1, notificacion);

        }

    }

}
