package com.high_technology_software.android.pauapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;

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
    private static ButtonAdapter botPrueba;

    private int numeroBoton;
    private int mCategory;

    private List<ItemVO> mList;
    private ItemDAO mDao;

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
            mCategory = extras.getInt("category");
        }
/*
        Log.e("NUMERO", String.valueOf(getIntent().getExtras().getInt("boton")));
        numeroBoton = getIntent().getExtras().getInt("boton");
*/
        mDao = new ItemDAO(this);
        mList = mDao.read(mCategory);

        //pruebas del Adaptador de Botones
        Button botonesPan = null;
        String desc = "";
        int id = 0;
        for (int i = 0; i < mList.size(); i++){
            id = mList.get(i).getId();
            desc = mList.get(i).getName();
            botonesPan = new Button(this);
            botonesPan.setText(desc);
            //añadimos el boton al listener
            botonesPan.setOnClickListener(botonesPanel);
            botonesPan.setId(id);
            botones.add(botonesPan);

        }
        botPrueba = new ButtonAdapter(botones, this);
        gridLayoutItems.setAdapter(botPrueba);

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
            intent.putExtra("category", mCategory);
            intent.putExtra("boton", v.getId());
            startActivityForResult(intent, RESULT_OK);
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
//        Intent intent = new Intent(getApplicationContext(), ActivityMainPrincipal.class);
//        intent.putExtra("boton", numeroBoton);
//        intent.putExtra("category", numeroBoton);
//        startActivity(intent);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("TAG", "onSaveInstanceState(Bundle savedInstanceState)");
        savedInstanceState.putInt("category", numeroBoton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("category")) {
                mCategory = data.getExtras().getInt("category");
                mList = mDao.read(mCategory);
            }
        }
    }

}
