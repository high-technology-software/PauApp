package com.high_technology_software.android.pauapp;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import adaptadors.BitmapTransform;
import adaptadors.SoundPlayer;


public class ActivitySonidoImagenText extends AppCompatActivity {
    private ImageView im;
    private Button bot;
    private TextView tex;

    //imagen mida
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    //sonido
    private SoundPlayer mPlayer = SoundPlayer.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sonido_imagen_text);
        //de itemsCategoryHay que traerse datos


        //referencias al xml
        im = (ImageView) findViewById(R.id.myImageView);
        bot = (Button) findViewById(R.id.botSound);
        tex = (TextView) findViewById(R.id.texteActivitySoundImg);

        //obtener path de la imagen TODO provisional estatica
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU/item_prueba/minion.png";
        File ruta = new File(path);

        //obtener el path del sonido TODO provisional estatica
        final String pathSound = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU/item_prueba/criatura.mp3";
        final Uri uriSound = stringToUri(pathSound);
        int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));
        Picasso.with(im.getContext())
                .load(ruta)
                .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .skipMemoryCache()
                .resize(size, size)
                .centerInside()
                .into(im);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.sonido(v, uriSound);
            }
        });
    }


    //metodos para convertir tipos
    private Uri stringToUri(String string){
        Uri uri;
        uri = Uri.parse(string);
        return uri;
    }
    private String uriToString(Uri uri){
        String stringUri;
        stringUri = uri.toString();
        return stringUri;
    }


}
