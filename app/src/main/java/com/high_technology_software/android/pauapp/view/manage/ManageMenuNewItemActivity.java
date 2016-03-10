package com.high_technology_software.android.pauapp.view.manage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.adapter.CategoryAdapter;
import com.high_technology_software.android.pauapp.view.adapter.NewItemAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ManageMenuNewItemActivity extends AppCompatActivity {

    private List<ItemVO> mList;
    private ItemDAO mDao;
    private NewItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_new_item_activity);

        mDao = new ItemDAO(getApplicationContext());
        mList = loadNewItems();

        mAdapter = new NewItemAdapter(ManageMenuNewItemActivity.this, mList, getLayoutInflater());

        ListView listView = (ListView) findViewById(R.id.manage_menu_new_item_activity_list);
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

    private List<ItemVO> loadNewItems() {
        List<ItemVO> result = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU";
        File directory = new File(path);
        List<String> directories = new ArrayList<>();

        for (File folder : directory.listFiles()) {
            if (folder.isDirectory()) {
                directories.add(folder.getName());
            }
        }

        result = mDao.read(directories);

        return result;
    }

}
