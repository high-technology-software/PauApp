package com.high_technology_software.android.pauapp.model.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.high_technology_software.android.pauapp.model.CategoryVO;

public abstract class DatabaseTableCategory implements BaseColumns {

    public static final String TABLE_NAME = "category";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ORDER = "order";
    public static final String VISIBLE = "visible";
    public static final String ENABLE = "enable";
    public static final String CREATE_DATE = "createDate";
    public static final String MODIFY_DATE = "modifyDate";

    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            ORDER + " INTEGER, " +
            VISIBLE + " INTEGER)" +
            ENABLE + " INTEGER, " +
            CREATE_DATE + " INTEGER, " +
            MODIFY_DATE + " INTEGER)";

    public static final String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static ContentValues translate(CategoryVO vo) {
        ContentValues result = new ContentValues();

        result.put(ID, vo.getId());
        result.put(NAME, vo.getName());
        result.put(ORDER, vo.getOrder());
        result.put(VISIBLE, vo.isVisible());
        result.put(ENABLE, vo.isEnable());
        result.put(CREATE_DATE, vo.getCreateDate().getTime());
        result.put(MODIFY_DATE, vo.getModifyDate().getTime());

        return result;
    }
}
