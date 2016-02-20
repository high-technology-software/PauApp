package com.high_technology_software.android.pauapp.model;

import android.database.Cursor;

import com.high_technology_software.android.pauapp.model.database.DatabaseTableItem;

import java.util.Date;

public class ItemVO {

    private int id;
    private String name;
    private String audio;
    private String image;
    private Integer order;
    private boolean visible;
    private CategoryVO father;
    private boolean enable;
    private Date createDate;
    private Date modifyDate;

    public ItemVO() {

    }

    public ItemVO(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ID));
        name = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.NAME));
        audio = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.AUDIO));
        image = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.IMAGE));
        order = cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ORDER));
        visible = (cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.VISIBLE)) == 1 ? true : false);
        father = new CategoryVO();
        father.setId(cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ID_CATEGORY)));
        enable = (cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ENABLE)) == 1 ? true : false);
        createDate = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseTableItem.CREATE_DATE)));
        modifyDate = new Date(cursor.getLong(cursor.getColumnIndex(DatabaseTableItem.CREATE_DATE)));
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public CategoryVO getFather() {
        return father;
    }

    public void setFather(CategoryVO father) {
        this.father = father;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
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
