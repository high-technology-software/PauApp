package com.high_technology_software.android.pauapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.view.object.SpinnerObject;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<CategoryVO> {

    private List<CategoryVO> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SpinnerAdapter(Context context, List<CategoryVO> list, LayoutInflater layoutInflater) {
        super(context, 0, list);
        mList = list;
        mContext = context;
        mLayoutInflater = layoutInflater;
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerObject item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_spinner_category, parent, false);
            item = new SpinnerObject();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_spinner_category_name));
            convertView.setTag(item);
        }
        item = (SpinnerObject) convertView.getTag();

        item.getName().setText(mList.get(position).getName());

        return convertView;
    }

    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        SpinnerObject item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_spinner_category, parent, false);
            item = new SpinnerObject();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_spinner_category_name));
            convertView.setTag(item);
        }
        item = (SpinnerObject) convertView.getTag();

        item.getName().setText(mList.get(position).getName());

        return convertView;
    }


}
