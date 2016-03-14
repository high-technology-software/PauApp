package adaptadors;



import android.animation.FloatArrayEvaluator;
import android.content.Context;

import android.util.DisplayMetrics;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.high_technology_software.android.pauapp.ActivityMainPrincipal;

import java.util.ArrayList;

public class ButtonAdapter extends BaseAdapter {
    private static ArrayList<Button> mButons = null;
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
        //return mButons.get(position).getId();
    }



    ActivityMainPrincipal ac = new ActivityMainPrincipal();
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Button b = null;
        /*
        for (Button d:mButons) {
            Log.d("VALOR", String.valueOf(d.getId()));
        }*/

        if (convertView == null) {
            b = (Button) mButons.get(position);
            b.setHeight(pxToDp(400));

        } else {
            b = (Button) convertView;
            b.setHeight(pxToDp(400));
        }

        return b;




    /*
        final RecyclerView.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item, parent,
                    false);
            viewHolder = new RecyclerView.ViewHolder();
            viewHolder.imageView = (ScaleImageView) convertView
                    .findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RecyclerView.ViewHolder) convertView.getTag();
        }
        final RecentPhotos recentPhotos = getItem(position);
        Log.v("database", recentPhotos.id);
        viewHolder.imageView.setImageResource(R.drawable.capsule_image_holder);
        if (recentPhotos.imageUrl != null) {
            CapsuleApplication.getLoader().displayImage(recentPhotos.imageUrl,
                    viewHolder.imageView, options, animateFirstListener);
            Log.v("Load images", "load images");
        } else {
            viewHolder.imageView.setImageResource(R.drawable.error_img);
        }

        return convertView;
*/
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