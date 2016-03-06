package com.high_technology_software.android.pauapp.view.item;

import android.widget.ImageButton;
import android.widget.TextView;

public class CategoryItem {
    TextView name;
    ImageButton remove;
    ImageButton up;
    ImageButton down;

    public CategoryItem() {
    }

    public ImageButton getDown() {
        return down;
    }

    public void setDown(ImageButton down) {
        this.down = down;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public ImageButton getRemove() {
        return remove;
    }

    public void setRemove(ImageButton remove) {
        this.remove = remove;
    }

    public ImageButton getUp() {
        return up;
    }

    public void setUp(ImageButton up) {
        this.up = up;
    }
}
