package com.high_technology_software.android.pauapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.model.database.DatabaseTableCategory;
import com.high_technology_software.android.pauapp.model.database.DatabaseTableItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends GenericDAO {

    public ItemDAO(Context context) {
        super(context);
    }

    public List<ItemVO> read(int father) {
        List<ItemVO> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(DatabaseTableItem.TABLE_NAME, null, DatabaseTableItem.ID_CATEGORY + " = ?", new String[]{String.valueOf(father)}, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ItemVO vo = new ItemVO(cursor);
                    result.add(vo);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        sqLiteDatabase.close();

        return result;
    }

    public boolean create(ItemVO vo, int idFather) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo, idFather);

        long ack = sqLiteDatabase.insert(DatabaseTableItem.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return ack != -1;
    }

    public boolean update(ItemVO vo, int idFather) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo, idFather);

        int ack = sqLiteDatabase.update(DatabaseTableItem.TABLE_NAME, values, DatabaseTableItem.ID, new String[]{String.valueOf(vo.getId())});

        sqLiteDatabase.close();

        return ack == 1;
    }


    public int getLastId (){

        int result = 0;
        //CategoryVO result;
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        //optimizar
        Cursor cursor = sqLiteDatabase.rawQuery("Select Max(id) from item", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getInt(1);
            }
            cursor.close();
        }
        sqLiteDatabase.close();

        return result;

    }

}
