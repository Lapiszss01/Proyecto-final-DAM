package com.example.proyectofinaldam.ui.Canciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.MyMediaPlayer;
import com.example.proyectofinaldam.data.model.Cancion;

import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayerActivity extends AppCompatActivity {

    int posicionCancion;
    int x = 0;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    Cancion currentSong;
    ArrayList<Cancion> listaCanciones;

    NotificationManager notificationManager;

    TextView tituloCancion;
    SeekBar seekBar;
    ImageView pauseBtn, clave, nextBtn, previousBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        tituloCancion = findViewById(R.id.tituloCancion);
        seekBar = findViewById(R.id.seekBar);
        pauseBtn = findViewById(R.id.pauseBtn);
        clave = findViewById(R.id.clave);
        nextBtn = findViewById(R.id.nextBtn);
        previousBtn = findViewById(R.id.prevBtn);

        tituloCancion.setSelected(true);


        listaCanciones = (ArrayList<Cancion>) getIntent().getSerializableExtra("LIST");


        setInformacion();

        MediaPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){

                    seekBar.setProgress(mediaPlayer.getCurrentPosition());

                    if(mediaPlayer.isPlaying()){

                        //pauseBtn.setImageResource(R.drawable.pause);
                        clave.setRotation(x++);
                    } else{
                        //pauseBtn.setImageResource(R.drawable.play);
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Notificacion música

        //creamosNotificacion();

    }

    /*private void creamosNotificacion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            crearCanal();

            String titulo = currentSong.getTitulo();
            String artista = currentSong.getArtista();

            //CrearNotificacion.crearNotificacion(MediaPlayerActivity.this, titulo,artista,posicionCancion);
        }
    }


    private void crearCanal() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel canal = new NotificationChannel(CrearNotificacion.CANAL_ID,
                    "Noti", NotificationManager.IMPORTANCE_LOW);

            notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(canal);
            }
        }

    }*/

    void setInformacion(){

        currentSong = listaCanciones.get(MyMediaPlayer.currentIndex);

        tituloCancion.setText(currentSong.getTitulo());


        pauseBtn.setOnClickListener(v -> pausePlay());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());

        playMusic();


    }

    private void playMusic() {

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void pausePlay() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }

    private void playNextSong() {


        if(MyMediaPlayer.currentIndex == listaCanciones.size()-1)
            return;

        MyMediaPlayer.currentIndex +=1;
        mediaPlayer.reset();

        setInformacion();
        //creamosNotificacion();

    }

    private void playPreviousSong(){


        if(MyMediaPlayer.currentIndex == 0)
            return;

        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();

        setInformacion();
        //creamosNotificacion();

    }

    @Override
    public void onBackPressed() {
        //do your action
        mediaPlayer.stop();
        finish();
    }

}
