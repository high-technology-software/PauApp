package com.high_technology_software.android.pauapp.controller;

import android.content.Context;

import com.high_technology_software.android.pauapp.model.database.DatabaseHelper;

public class GenericDAO {

    protected DatabaseHelper mDatabaseHelper;

    public GenericDAO(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

}
