package com.high_technology_software.android.pauapp.model;

import android.database.Cursor;

import com.high_technology_software.android.pauapp.model.database.DatabaseTableItem;

public class ItemVO {

    private int id;
    private String name;
    private String audio;
    private String image;
    private Integer order;
    private CategoryVO father;
    private String folder;

    public ItemVO() {

    }

    public ItemVO(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ID));
        name = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.NAME));
        audio = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.AUDIO));
        image = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.IMAGE));
        order = cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ORDER));
        father = new CategoryVO();
        father.setId(cursor.getInt(cursor.getColumnIndex(DatabaseTableItem.ID_CATEGORY)));
        folder = cursor.getString(cursor.getColumnIndex(DatabaseTableItem.FOLDER));
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}