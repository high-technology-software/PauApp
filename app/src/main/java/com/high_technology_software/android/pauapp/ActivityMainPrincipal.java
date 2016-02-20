package com.high_technology_software.android.pauapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import basesDeDatos.SqlControllerBaseDatosBotones;

public class ActivityMainPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    //variables referentes al xml
    private GridView gvPanelPal;
    private TextView tvAdd;
    private EditText tvName, tvOther,COMMENT;
    //base de datos
    private SqlControllerBaseDatosBotones sqlControllerBDB =  new SqlControllerBaseDatosBotones(this);
    //botones
    private ArrayList<Button> botones = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main_principal);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        tvAdd = (TextView) findViewById(R.id.tabBarAdd);
        gvPanelPal = (GridView) findViewById(R.id.gridPrincipal);

        Log.d("ASD", "HERE");
        CategoryDAO dao = new CategoryDAO(this);
        List<CategoryVO> list = dao.read();

        //pruebas del Adaptador de Botones
        Button botonesPan = null;
        for (int i = 0; i < 10; i++){
            botonesPan = new Button(this);
            botonesPan.setText(Integer.toString(i));
            //botonesPan.setOnClickListener();
            botonesPan.setId(i);
            botones.add(botonesPan);

        }
        gvPanelPal.setAdapter(new ButtonAdapter(botones));

        //cliclando sobre el textView permitira añadir un elemento en el gridView
        tvAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //al pulsar saldrá un alert auxiliar para llenar los campos necesarios para crear
                //el elemento en el GridView
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMainPrincipal.this);
                LayoutInflater inflater = ActivityMainPrincipal.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_form, null);
                builder.setView(dialogView);
                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        tvName = (EditText) dialogView.findViewById(R.id.tvName);
                        tvOther = (EditText) dialogView.findViewById(R.id.tvOther);
                        //cogemos el valor de los items que hemos instanciado
                        String name = tvName.getText().toString();
                        String other = tvOther.getText().toString();
                        //los metemos en la Base de Datos
                        try {
                            sqlControllerBDB.open();
                            sqlControllerBDB.insertData(name, other, "blue");

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                });
                builder.show();
            }
        });
    }



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


}
