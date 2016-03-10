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

        Cursor cursor = sqLiteDatabase.query(DatabaseTableItem.TABLE_NAME, null, DatabaseTableItem.ID_CATEGORY + " = ?", new String[]{String.valueOf(father)}, null, null, DatabaseTableItem.ORDER);

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

    public List<ItemVO> read(List<String> list) {
        List<ItemVO> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        String array = list.toString().replace("[", "('").replace(", ", "', '").replace("]", "')");

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT FOLDER FROM ITEM WHERE FOLDER IN " + array + "", null);
        //Cursor cursor = sqLiteDatabase.query(DatabaseTableItem.TABLE_NAME, new String[]{DatabaseTableItem.FOLDER}, DatabaseTableItem.FOLDER + " IN ?", new String[]{array}, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String folder = cursor.getString(0);
                    list.remove(folder);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        sqLiteDatabase.close();

        for (String folder : list) {
            ItemVO vo = new ItemVO();
            vo.setFolder(folder);
            result.add(vo);
        }

        return result;
    }


    public long create(ItemVO vo, int idFather) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo, idFather);

        long id = sqLiteDatabase.insert(DatabaseTableItem.TABLE_NAME, null, values);

        sqLiteDatabase.close();

        return id;
    }

    public boolean update(ItemVO vo, int idFather) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues values = DatabaseTableItem.translate(vo, idFather);

        int ack = sqLiteDatabase.update(DatabaseTableItem.TABLE_NAME, values, DatabaseTableItem.ID + " = ?", new String[]{String.valueOf(vo.getId())});

        sqLiteDatabase.close();

        return ack == 1;
    }

    public boolean delete(ItemVO vo) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        int ack = sqLiteDatabase.delete(DatabaseTableItem.TABLE_NAME, DatabaseTableCategory.ID + " = ?", new String[]{String.valueOf(vo.getId())});

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
                //ojo es el elemento 0
                result = cursor.getInt(0);
            }
            cursor.close();
        }
        sqLiteDatabase.close();

        return result;

    }

    public int sequence(int idCategory) {
        int result = 0;

        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT MAX(ORDEN) FROM ITEM WHERE IDCATEGORY = " + idCategory, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getInt(0);
            }
            cursor.close();
        }
        sqLiteDatabase.close();

        return result;
    }

}
