package com.high_technology_software.android.pauapp.view.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.high_technology_software.android.pauapp.R;

public class ManageMenuActivity extends Activity {

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
    }

}
