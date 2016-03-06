package com.high_technology_software.android.pauapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;

import java.util.List;

public class DifferentMenuActivity extends AppCompatActivity {

    private List<CategoryVO> mCategoryList;
    private AppAdapter mAdapter;

    private CategoryDAO baseDatosCategory = new CategoryDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //lista de items que tenemos en la base de datos
        CategoryDAO categoryDAO = new CategoryDAO(this);
        mCategoryList = categoryDAO.read();


        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);
        mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                createMenu2(menu);

            }

            private void createMenu2(SwipeMenu menu) {

                SwipeMenuItem item2 = new SwipeMenuItem(getApplicationContext());

                item2.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));

                item2.setWidth(dp2px(90));

                item2.setIcon(R.drawable.ic_action_discard);

                menu.addMenuItem(item2);
            }

        };
        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //Log.d("POSICION ROW", String.valueOf(position));
                CategoryVO categoryVO = mCategoryList.get(position);
                //Log.d("POSICION ID", String.valueOf(categoryVO.getId()));
                mCategoryList.remove(position);
                baseDatosCategory.delete(categoryVO);
                onRestart();

                //Log.d("POSITION", String.valueOf(position));

                return true;

            }
        });

    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCategoryList.size();
        }

        @Override
        public CategoryVO getItem(int position) {
            return mCategoryList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            CategoryVO item = getItem(position);
            holder.tv_name.setText(item.getName());
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i = new Intent(DifferentMenuActivity.this, DifferentMenuActivity.class);
        startActivity(i);
        finish();

    }
}
