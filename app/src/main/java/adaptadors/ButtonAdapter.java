package adaptadors;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ButtonAdapter extends BaseAdapter {
    private ArrayList<Button> mButons = null;
    private Context mContext;

    public ButtonAdapter(ArrayList<Button> bot, Context context){
        this.mContext = context;
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
        b.setHeight(pxToDp(400));
        return b;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}