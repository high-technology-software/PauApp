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
    public static final String ORDER = "order";
    public static final String VISIBLE = "visible";
    public static final String ENABLE = "enable";
    public static final String CREATE_DATE = "createDate";
    public static final String MODIFY_DATE = "modifyDate";

    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID_CATEGORY + " INTEGER REFERENCES " + DatabaseTableCategory.TABLE_NAME + "(" + DatabaseTableCategory.ID + "), " +
            NAME + " TEXT, " +
            AUDIO + " TEXT, " +
            IMAGE + " TEXT, " +
            ORDER + " INTEGER, " +
            VISIBLE + " INTEGER, " +
            ENABLE + " INTEGER, " +
            CREATE_DATE + " INTEGER, " +
            MODIFY_DATE + " INTEGER)";

    public static final String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static ContentValues translate(ItemVO vo) {
        ContentValues result = new ContentValues();

        result.put(ID, vo.getId());
        result.put(ID_CATEGORY, vo.getFather().getId());
        result.put(NAME, vo.getName());
        result.put(AUDIO, vo.getAudio());
        result.put(IMAGE, vo.getImage());
        result.put(ORDER, vo.getOrder());
        result.put(VISIBLE, vo.isVisible());
        result.put(ENABLE, vo.isEnable());
        result.put(CREATE_DATE, vo.getCreateDate().getTime());
        result.put(MODIFY_DATE, vo.getModifyDate().getTime());

        return result;
    }
}
