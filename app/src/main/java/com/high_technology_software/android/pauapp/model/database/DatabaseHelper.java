package com.high_technology_software.android.pauapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseTableCategory.CREATE_STATEMENT);
        db.execSQL(DatabaseTableItem.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseTableItem.DROP_STATEMENT);
        db.execSQL(DatabaseTableCategory.DROP_STATEMENT);
        onCreate(db);
    }
}
