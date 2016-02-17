package com.high_technology_software.android.pauapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.model.database.DatabaseTableItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends GenericDAO {

    public ItemDAO(Context context) {
        super(context);
    }

    public List<ItemVO> read(int father) {
        List<ItemVO> result = new ArrayList<>();

        return result;
    }

    public boolean create(ItemVO vo) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo);

        long ack = sqLiteDatabase.insert(DatabaseTableItem.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return ack != -1;
    }

    public boolean update(ItemVO vo) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo);

        int ack = sqLiteDatabase.update(DatabaseTableItem.TABLE_NAME, values, DatabaseTableItem.ID, new String[]{String.valueOf(vo.getId())});

        sqLiteDatabase.close();

        return ack == 1;
    }
}
