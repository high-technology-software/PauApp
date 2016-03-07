package com.high_technology_software.android.pauapp.view.manage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
import com.high_technology_software.android.pauapp.view.adapter.CategoryAdapter;
import com.high_technology_software.android.pauapp.view.item.CategoryItem;

import java.util.List;

public class ManageMenuCategoryActivity extends AppCompatActivity {

    private List<CategoryVO> mList;
    private CategoryDAO mDao;
    private CategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_category_activity);

        mDao = new CategoryDAO(getApplicationContext());
        mList = mDao.read();

        for (int i = 0; i < 100; i++) {
            CategoryVO vo = new CategoryVO();
            vo.setName("Item -> " + i);
            mList.add(vo);
        }
        mAdapter = new CategoryAdapter(ManageMenuCategoryActivity.this, mList, getLayoutInflater());

        ListView listView = (ListView) findViewById(R.id.manage_menu_category_activity_list);
        listView.setAdapter(mAdapter);
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
