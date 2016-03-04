package com.high_technology_software.android.pauapp.view;

import android.support.v4.app.Fragment;

public class CategoryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CategoryListFragment();
    }

}
