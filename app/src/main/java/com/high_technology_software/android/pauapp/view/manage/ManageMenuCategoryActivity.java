package com.high_technology_software.android.pauapp.view.manage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.view.item.CategoryItem;

import java.util.List;

public class ManageMenuCategoryActivity extends AppCompatActivity {

    private List<CategoryVO> mList;
    private CategoryDAO mDao;
    private CategoryAdapter mAdapter;

    //toolbar
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_category_activity);

        //toolbar
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_manage_cat_atc);

        mDao = new CategoryDAO(getApplicationContext());
        mList = mDao.read();

        for (int i = 0; i < 100; i++) {
            CategoryVO vo = new CategoryVO();
            vo.setName("Item -> " + i);
            mList.add(vo);
        }
        mAdapter = new CategoryAdapter(mList);
        ListView listView = (ListView) findViewById(R.id.manage_menu_category_activity_list);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ManageMenuCategoryActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.category_dialog, null);
                dialogBuilder.setView(dialogView);

                final CategoryVO vo = mList.get(position);

                final EditText editText = (EditText) dialogView.findViewById(R.id.category_dialog_editText);
                editText.setText(vo.getName());

                dialogBuilder.setTitle(R.string.dialog_category_title);
                dialogBuilder.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        vo.setName(editText.getText().toString());
                        mList.set(position, vo);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                dialogBuilder.setNegativeButton("Cancel·lar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ManageMenuCategoryActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.category_menu_dialog, null);
                dialogBuilder.setView(dialogView);

                EditText editText = (EditText) dialogView.findViewById(R.id.dialog_menu_category_add);
                /*
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: implementar crear un nuevo dialogo para crear una nueva categoria
                    }
                });
                */
                dialogBuilder.setTitle(R.string.dialog_category_menu_title);
                dialogBuilder.setPositiveButton(R.string.category_menu_dialog_save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //TODO: implentar guardar todo el array en bbdd (mList) y luego salir
                        //importante provar el update de cada item y sino funciona hacer el insert ya que pueden haber nuevos items
                    }
                });
                dialogBuilder.setNegativeButton(R.string.category_menu_dialog_exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //TODO : implementar volver al ManageMenuActivity
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "TAG");
    }

    private class CategoryAdapter extends ArrayAdapter<CategoryVO> {

        public CategoryAdapter(List<CategoryVO> list) {
            super(getApplicationContext(), 0, list);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            CategoryItem item = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_category, parent, false);
                item = new CategoryItem();
                item.setName((TextView) convertView.findViewById(R.id.list_item_category_text));
                item.setRemove((ImageButton) convertView.findViewById(R.id.list_item_category_remove));
                item.setUp((ImageButton) convertView.findViewById(R.id.list_item_category_up));
                item.setDown((ImageButton) convertView.findViewById(R.id.list_item_category_down));
                convertView.setTag(item);
            }
            item = (CategoryItem) convertView.getTag();
            item.getRemove().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    mAdapter.notifyDataSetChanged();
                }
            });
            item.getUp().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryVO current = mList.get(position);
                    CategoryVO target = mList.get(position - 1);
                    mList.set(position - 1, current);
                    mList.set(position, target);
                    mAdapter.notifyDataSetChanged();
                }
            });
            item.getDown().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryVO current = mList.get(position);
                    CategoryVO target = mList.get(position + 1);
                    mList.set(position + 1, current);
                    mList.set(position, target);
                    mAdapter.notifyDataSetChanged();
                }
            });

            if (position == 0) {
                item.getUp().setVisibility(View.INVISIBLE);
            } else {
                item.getUp().setVisibility(View.VISIBLE);
            }
            if (position == mList.size() - 1) {
                item.getDown().setVisibility(View.INVISIBLE);
            } else {
                item.getDown().setVisibility(View.VISIBLE);
            }
            item.getName().setText(mList.get(position).getName());

            return convertView;
        }
    }


    //toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final android.support.v7.app.ActionBar supportAB = getSupportActionBar();
        supportAB.setHomeAsUpIndicator(R.drawable.ic_change_history_black_24dp);
        supportAB.setDisplayHomeAsUpEnabled(true);

        //drawer layout (tabbar)
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_manage_cat_atc);

        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id= item.getItemId();
//
//
//        if (id == android.R.id.home){
//            Log.d("BUSCAR", "HOLA");
//            Intent intent = new Intent(getApplicationContext(), SingleFragmentActivity.class);
//            this.startActivity(intent);
//
//        }
//
//
//        /*
//        switch (id) {
//            case android.R.id.home:   ///drawer al pulsar icono si está abierto lo cierro, si está cerrado lo abro
//                if(drawerLayout.isDrawerOpen(GravityCompat.START))
//                    drawerLayout.closeDrawers();
//                else
//                    drawerLayout.openDrawer(GravityCompat.START);
//
//        }
//        */
//
//        //return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
