package com.high_technology_software.android.pauapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.squareup.picasso.Picasso;

import java.io.File;

import adaptadors.BitmapTransform;
import adaptadors.SoundPlayer;


public class ActivitySonidoImagenText extends AppCompatActivity {

    private ItemDAO mDAO;
    private ImageView im;
    private Button bot;
    private TextView tex;

    //imagen mida
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    //sonido
    //obtener el path del sonido TODO provisional estatica
    private SoundPlayer mPlayer;

    private int numeroBoton;
    private int mCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sonido_imagen_text);
        //de itemsCategoryHay que traerse datos
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            numeroBoton = extras.getInt("boton");
            mCategory = extras.getInt("category");
        }

        mDAO = new ItemDAO(getApplicationContext());
        ItemVO vo = mDAO.readById(numeroBoton);

        //referencias al xml
        im = (ImageView) findViewById(R.id.myImageView);
        bot = (Button) findViewById(R.id.botSound);
        tex = (TextView) findViewById(R.id.texteActivitySoundImg);
        tex.setText(vo.getName());

        bot.setBackgroundResource(R.drawable.sonido2);
        //bot.setBackgroundResource(R.drawable.sound_audio_devices);
        //obtener path de la imagen TODO provisional estatica
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU/item_prueba/minion.png";
        File ruta = new File(vo.getImage());

        //sonido
        String pathSound = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU/item_prueba/prueba.m4a";
        final Uri uriSound = stringToUri(vo.getAudio());
        mPlayer = new SoundPlayer(this, uriSound);

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
                if (!mPlayer.isPlaying()) {
                    mPlayer.play();
                }
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


    //toolbar
    //TODO ir a otra activity desde el toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final android.support.v7.app.ActionBar supportAB = getSupportActionBar();
        //seleccionamos el tipo de icono a mostrar
        supportAB.setHomeAsUpIndicator(R.drawable.back);
        //dibujamos la flecha
        supportAB.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!mPlayer.isPlaying()){
            //volvemos a la pantalla de inicio
//            Intent intent = new Intent(getApplicationContext(), ItemsCategoryActivity.class);
//            intent.putExtra("boton", numeroBoton);
//            startActivity(intent);

            Intent data = new Intent();
            data.putExtra("category", mCategory);
            setResult(RESULT_OK, data);
            finish();
        }
        return true;
    }

}
