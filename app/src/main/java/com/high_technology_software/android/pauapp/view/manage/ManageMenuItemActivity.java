package com.high_technology_software.android.pauapp.view.manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.adapter.CategoryAdapter;
import com.high_technology_software.android.pauapp.view.adapter.ItemAdapter;
import com.high_technology_software.android.pauapp.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManageMenuItemActivity extends AppCompatActivity {

    private List<ItemVO> mList;
    private ItemDAO mDao;
    private ItemAdapter mAdapter;

    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_item_activity);

        mDao = new ItemDAO(getApplicationContext());
        mList = new ArrayList<>();

        CategoryDAO dao = new CategoryDAO(getApplicationContext());
        final List<CategoryVO> list = dao.selector();

        mSpinner = (Spinner) findViewById(R.id.manage_menu_item_activity_spinner);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getApplicationContext(), list, getLayoutInflater());
        mSpinner.setAdapter(spinnerAdapter);

        mAdapter = new ItemAdapter(ManageMenuItemActivity.this, mList, getLayoutInflater());

        ListView listView = (ListView) findViewById(R.id.manage_menu_item_activity_list);
        listView.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mList.clear();
                mList.addAll(mDao.read(list.get(position).getId()));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
