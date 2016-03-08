package com.high_technology_software.android.pauapp.view.manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.view.adapter.CategoryAdapter;

import java.util.List;

public class ManageMenuCategoryActivity extends AppCompatActivity {

    private List<CategoryVO> mList;
    private CategoryDAO mDao;
    private CategoryAdapter mAdapter;

    private EditText mEditText;
    private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_category_activity);

        mDao = new CategoryDAO(getApplicationContext());
        mList = mDao.read();

        mAdapter = new CategoryAdapter(ManageMenuCategoryActivity.this, mList, getLayoutInflater());

        ListView listView = (ListView) findViewById(R.id.manage_menu_category_activity_list);
        listView.setAdapter(mAdapter);

        mEditText = (EditText) findViewById(R.id.manage_menu_category_activity_editText);

        mImageButton = (ImageButton) findViewById(R.id.manage_menu_category_activity_button);
        mImageButton.setOnClickListener(addListener);
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

    View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int order = mDao.sequence() + 1;

            CategoryVO vo = new CategoryVO();
            vo.setName(mEditText.getText().toString());
            vo.setOrder(order);

            long id = mDao.create(vo);

            if (id == -1) {
                Toast.makeText(getApplicationContext(), R.string.manage_menu_category_activity_unique, Toast.LENGTH_LONG).show();
            } else {
                vo.setId((int) id);
                mList.add(vo);
                mAdapter.notifyDataSetChanged();
            }

            mEditText.setText("");
        }
    };

}
