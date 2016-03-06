package com.high_technology_software.android.pauapp.model.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.high_technology_software.android.pauapp.model.CategoryVO;



public abstract class DatabaseTableCategory implements BaseColumns {

    public static final String TABLE_NAME = "category";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ORDER = "orden";




    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            ORDER + " INTEGER)";

    public static final String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static ContentValues translate(CategoryVO vo) {
        ContentValues result = new ContentValues();

        result.put(ID, vo.getId());
        result.put(NAME, vo.getName());
        result.put(ORDER, vo.getOrder());

        return result;
    }
}
