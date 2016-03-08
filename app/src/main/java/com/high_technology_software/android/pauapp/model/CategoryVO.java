package com.high_technology_software.android.pauapp.model;

import android.database.Cursor;

import com.high_technology_software.android.pauapp.model.database.DatabaseTableCategory;

public class CategoryVO {

    private int id;
    private String name;
    private int order;

    public CategoryVO() {

    }

    public CategoryVO(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.ID));
        name = cursor.getString(cursor.getColumnIndex(DatabaseTableCategory.NAME));
        order = cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.ORDER));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
