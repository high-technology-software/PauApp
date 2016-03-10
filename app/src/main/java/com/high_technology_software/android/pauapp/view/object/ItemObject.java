package com.high_technology_software.android.pauapp.view.object;

import android.widget.ImageButton;
import android.widget.TextView;

public class ItemObject {
    TextView name;
    ImageButton up;
    ImageButton down;

    public ItemObject() {
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

    public ImageButton getUp() {
        return up;
    }

    public void setUp(ImageButton up) {
        this.up = up;
    }
}
