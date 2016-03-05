package com.high_technology_software.android.pauapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adaptadors.ButtonAdapter;

public class ActivityMainPrincipal extends AppCompatActivity {

    //private Context mContext = getApplicationContext();
    private DrawerLayout drawerLayout;
    //variables referentes al xml
    private GridView gvPanelPal;
    private TextView tvAdd, tvDelete;
    private EditText tvName, tvOther,COMMENT;
    private Button botonesPan = null;
    //base de datos
    //private SqlControllerBaseDatosBotones sqlControllerBDB =  new SqlControllerBaseDatosBotones(this);
    CategoryDAO baseDatosCategory = new CategoryDAO(this);
    //botones
    private ArrayList<Button> botones = new ArrayList<Button>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main_principal);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        tvAdd = (TextView) findViewById(R.id.tabBarAdd);
        tvDelete = (TextView) findViewById(R.id.tabBarDelete);
        gvPanelPal = (GridView) findViewById(R.id.gridPrincipal);
        gvPanelPal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("asd", "asd");
                return false;
            }
        });

        Log.d("ASD", "HERE");
        CategoryDAO dao = new CategoryDAO(this);
        List<CategoryVO> listCategory = dao.read();


        //pruebas del Adaptador de Botones

        int id = 0;
        for (int i = 0; i < listCategory.size(); i++){
            id = listCategory.get(i).getId();
            botonesPan = new Button(this);
            botonesPan.setText(Integer.toString(id));
            //añadimos el boton al listener
            botonesPan.setOnClickListener(botonesPanel);
            botonesPan.setId(id);
            botones.add(botonesPan);

        }
        final ButtonAdapter botPrueba = new ButtonAdapter(botones);
        gvPanelPal.setAdapter(botPrueba);

        //cliclando sobre el textView permitira añadir un elemento en el gridView
        tvAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //al pulsar saldrá un alert auxiliar para llenar los campos necesarios para crear
                //el elemento en el GridView
                final CategoryDAO categ = new CategoryDAO(getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMainPrincipal.this);
                LayoutInflater inflater = ActivityMainPrincipal.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_form, null);
                builder.setView(dialogView);
                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        tvName = (EditText) dialogView.findViewById(R.id.tvName);

                        //cogemos el valor de los items que hemos instanciado
                        String name = tvName.getText().toString();
                        //obtener ultimo id
                        int ultimoId = categ.getLastId();
                        ultimoId = ultimoId + 1;

                        CategoryVO cat = new CategoryVO();
                        cat.setId(ultimoId);
                        cat.setName(name);

                        baseDatosCategory.create(cat);

                        //añadimos el boton dinamicamente
                        botonesPan = new Button(getApplicationContext());
                        botonesPan.setText(Integer.toString(ultimoId));
                        //añadimos el boton al listener
                        botonesPan.setOnClickListener(botonesPanel);
                        botonesPan.setId(ultimoId);
                        botones.add(botonesPan);

                        botPrueba.notifyDataSetChanged();
                        //reiniciamos el activity para ver nuestro nuevo elemento
                        //onRestart();

                    }
                });
                builder.show();

            }
        });

        //tvDelete borrar elementos del panel principal
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DifferentMenuActivity.class);

                startActivity(intent);
                //reiniciamos el activity para ver nuestro nuevo elemento
                //finish();
                //startActivity(getIntent());
            }
        });
    }
    View.OnClickListener botonesPanel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Donde o lo que queremos que haga al pulsar el boton
            //habria que mirar si el usuario que se loguea es admin ya que habria que lanzar otro activity o ocultar elementos
            Intent intent = new Intent(getApplicationContext(), ItemsCategoryActivity.class);
            //guardamos el id del boton
            intent.putExtra("boton", v.getId());
            startActivity(intent);
            //Log.d("ID DEL BOTON", String.valueOf(v.getId()));
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final android.support.v7.app.ActionBar supportAB = getSupportActionBar();
        supportAB.setHomeAsUpIndicator(R.drawable.ic_drawer);
        supportAB.setDisplayHomeAsUpEnabled(true);

        //drawer layout (tabbar)
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id) {
            case android.R.id.home:   ///drawer al pulsar icono si está abierto lo cierro, si está cerrado lo abro
                if(drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawers();
                else
                    drawerLayout.openDrawer(GravityCompat.START);

        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onRestart() {

        super.onRestart();
        Intent i = new Intent(ActivityMainPrincipal.this, ActivityMainPrincipal.class);
        startActivity(i);
        finish();

    }

    public int getIdPaneles(CategoryVO cat) {
        return cat.getId();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
        menu.add(0, v.getId(), 0, "Action 3");
    }
}
