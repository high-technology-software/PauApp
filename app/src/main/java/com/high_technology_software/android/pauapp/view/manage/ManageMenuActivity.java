package com.high_technology_software.android.pauapp.view.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.high_technology_software.android.pauapp.ItemsCategoryActivity;
import com.high_technology_software.android.pauapp.R;

public class ManageMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_menu_activity);

        Button categoryCrudButton = (Button) findViewById(R.id.categoryCrud);
        categoryCrudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageMenuActivity.this, ManageMenuCategoryActivity.class);
                startActivity(i);
            }
        });

        Button newItemButton = (Button) findViewById(R.id.itemCrud);
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageMenuNewItemActivity.class);
                startActivity(i);
            }
        });

        Button itemButton = (Button) findViewById(R.id.itemOrder);
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ManageMenuItemActivity.class);
                startActivity(i);
            }
        });
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
