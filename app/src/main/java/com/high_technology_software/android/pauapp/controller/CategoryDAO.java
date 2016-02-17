package com.high_technology_software.android.pauapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.database.DatabaseTableCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends GenericDAO {

    public CategoryDAO(Context context) {
        super(context);
    }

    public List<CategoryVO> read() {
        List<CategoryVO> result = new ArrayList<>();

        return result;
    }

    public boolean create(CategoryVO vo) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableCategory.translate(vo);

        long ack = sqLiteDatabase.insert(DatabaseTableCategory.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return ack != -1;
    }

    public boolean update(CategoryVO vo) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableCategory.translate(vo);

        int ack = sqLiteDatabase.update(DatabaseTableCategory.TABLE_NAME, values, DatabaseTableCategory.ID, new String[]{String.valueOf(vo.getId())});

        sqLiteDatabase.close();

        return ack == 1;
    }
}
