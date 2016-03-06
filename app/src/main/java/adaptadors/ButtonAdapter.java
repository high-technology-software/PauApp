package adaptadors;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ButtonAdapter extends BaseAdapter {
    private ArrayList<Button> mButons = null;

    public ButtonAdapter(ArrayList<Button> bot){
        this.mButons = bot;
    }


    @Override
    public int getCount() {
        return mButons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) mButons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button b;
        if (convertView == null) {
            b = (Button) mButons.get(position);
        } else {
            b = (Button) convertView;
        }
        return b;
    }

}