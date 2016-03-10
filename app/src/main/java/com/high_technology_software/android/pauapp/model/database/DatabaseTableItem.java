package com.high_technology_software.android.pauapp.model.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.high_technology_software.android.pauapp.model.ItemVO;

public abstract class DatabaseTableItem implements BaseColumns {

    public static final String TABLE_NAME = "item";
    public static final String ID = "id";
    public static final String ID_CATEGORY = "idCategory";
    public static final String NAME = "name";
    public static final String AUDIO = "audio";
    public static final String IMAGE = "image";
    public static final String ORDER = "orden";
    public static final String FOLDER = "folder";

    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID_CATEGORY + " INTEGER REFERENCES " + DatabaseTableCategory.TABLE_NAME + "(" + DatabaseTableCategory.ID + "), " +
            FOLDER + " TEXT UNIQUE, " +
            NAME + " TEXT, " +
            AUDIO + " TEXT, " +
            IMAGE + " TEXT, " +
            ORDER + " INTEGER)";

    public static final String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static ContentValues translate(ItemVO vo, int idFather) {
        ContentValues result = new ContentValues();

        result.put(ID_CATEGORY, idFather);
        result.put(FOLDER, vo.getFolder());
        result.put(NAME, vo.getName());
        result.put(AUDIO, vo.getAudio());
        result.put(IMAGE, vo.getImage());
        result.put(ORDER, vo.getOrder());
        return result;
    }
}
