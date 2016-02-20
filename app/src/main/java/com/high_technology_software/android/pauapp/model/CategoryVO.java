package com.high_technology_software.android.pauapp.model;

import android.database.Cursor;

import com.high_technology_software.android.pauapp.model.database.DatabaseTableCategory;

import java.util.Date;

public class CategoryVO {

    private int id;
    private String name;
    private Integer order;
    private Boolean visible;
    private boolean enable;
    private Date createDate;
    private Date modifyDate;

    public CategoryVO() {

    }

    public CategoryVO(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.ID));
        name = cursor.getString(cursor.getColumnIndex(DatabaseTableCategory.NAME));
        order = cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.ORDER));
        visible = (cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.VISIBLE)) == 1 ? true : false);
        enable = (cursor.getInt(cursor.getColumnIndex(DatabaseTableCategory.ENABLE)) == 1 ? true : false);
        createDate = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseTableCategory.CREATE_DATE)));
        modifyDate = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseTableCategory.CREATE_DATE)));
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
