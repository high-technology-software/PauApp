package com.high_technology_software.android.pauapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ScrollingTabContainerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.manage.ManageMenuActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adaptadors.ButtonAdapter;

public class ItemsCategoryActivity extends AppCompatActivity {

    private GridView gridLayoutItems;
    private TextView tvAdd;
    private TextView tvDelete;
    private TextView tvAtras;

    private EditText tvName;
    ItemDAO baseDatosItems = new ItemDAO(this);

    //array botones por cambiar por otra cosa
    private ArrayList<Button> botones = new ArrayList<Button>();

    private int numeroBoton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_category);

//        tvAdd = (TextView) findViewById(R.id.tabBarAdd);
//        tvDelete = (TextView) findViewById(R.id.tabBarDelete);
//        tvAtras = (TextView) findViewById(R.id.tabBarAtras);
        gridLayoutItems = (GridView) findViewById(R.id.gridPrincipalItem);
        //recuperacion del id
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            numeroBoton = extras.getInt("boton");
        }
/*
        Log.e("NUMERO", String.valueOf(getIntent().getExtras().getInt("boton")));
        numeroBoton = getIntent().getExtras().getInt("boton");
*/
        ItemDAO dao = new ItemDAO(this);
        List<ItemVO> listCategory = dao.read(numeroBoton);

        //pruebas del Adaptador de Botones
        Button botonesPan = null;
        String desc = "";
        int id = 0;
        for (int i = 0; i < listCategory.size(); i++){
            id = listCategory.get(i).getId();
            desc = listCategory.get(i).getName();
            botonesPan = new Button(this);
            botonesPan.setText(desc);
            //añadimos el boton al listener
            botonesPan.setOnClickListener(botonesPanel);
            botonesPan.setId(id);
            botones.add(botonesPan);

        }
        gridLayoutItems.setAdapter(new ButtonAdapter(botones, this));

//
//        tvAtras.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ActivityMainPrincipal.class);
//                startActivity(intent);
//            }
//        });
//
//        //cliclando sobre el textView permitira añadir un elemento en el gridView
//        tvAdd.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //al pulsar saldrá un alert auxiliar para llenar los campos necesarios para crear
//                //el elemento en el GridView
//                final ItemDAO items = new ItemDAO(getApplicationContext());
//                AlertDialog.Builder builder = new AlertDialog.Builder(ItemsCategoryActivity.this);
//                LayoutInflater inflater = ItemsCategoryActivity.this.getLayoutInflater();
//                final View dialogView = inflater.inflate(R.layout.dialog_form, null);
//                builder.setView(dialogView);
//                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        tvName = (EditText) dialogView.findViewById(R.id.tvName);
//
//                        //cogemos el valor de los items que hemos instanciado
//                        String name = tvName.getText().toString();
//                        //obtener ultimo id
//                        int ultimoId = items.getLastId();
//                        ultimoId = ultimoId + 1;
//
//                        ItemVO cat = new ItemVO();
//                        cat.setId(ultimoId);
//                        cat.setName(name);
//
//                        baseDatosItems.create(cat, numeroBoton);
//
//                        //reiniciamos el activity para ver nuestro nuevo elemento
//                        onRestart();
//
//                    }
//                });
//                builder.show();
//            }
//        });
    }


    View.OnClickListener botonesPanel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Donde o lo que queremos que haga al pulsar el boton
            //habria que mirar si el usuario que se loguea es admin ya que habria que lanzar otro activity o ocultar elementos
            Intent intent = new Intent(getApplicationContext(), ActivitySonidoImagenText.class);
            //guardamos el id del boton
            intent.putExtra("boton", v.getId());
            finish();
            startActivity(intent);
            //Log.d("ID DEL BOTON", String.valueOf(v.getId()));
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final android.support.v7.app.ActionBar supportAB = getSupportActionBar();
        supportAB.setHomeAsUpIndicator(R.drawable.back);
        supportAB.setDisplayHomeAsUpEnabled(true);
        return true;
    }
    //si contraseña es correcte
    boolean passCorrecte = false;
    String pass;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        Intent intent = new Intent(getApplicationContext(), ActivityMainPrincipal.class);
        intent.putExtra("boton", numeroBoton);
        startActivity(intent);
        return true;
    }
    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(ItemsCategoryActivity.this, ItemsCategoryActivity.class);
        //asi no perdemos el valor del padre
        i.putExtra("boton", numeroBoton);
        startActivity(i);
        finish();

    }


}
