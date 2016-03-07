package com.high_technology_software.android.pauapp.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.view.item.CategoryItem;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryVO> {

    private List<CategoryVO> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CategoryAdapter(Context context, List<CategoryVO> list, LayoutInflater layoutInflater) {
        super(context, 0, list);
        mList = list;
        mContext = context;
        mLayoutInflater = layoutInflater;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        CategoryItem item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_category_list_item, parent, false);
            item = new CategoryItem();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_category_list_item_name));
            item.setUp((ImageButton) convertView.findViewById(R.id.manage_menu_category_list_item_up));
            item.setDown((ImageButton) convertView.findViewById(R.id.manage_menu_category_list_item_down));
            convertView.setTag(item);
        }
        item = (CategoryItem) convertView.getTag();
        item.getUp().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryVO current = mList.get(position);
                CategoryVO target = mList.get(position - 1);
                mList.set(position - 1, current);
                mList.set(position, target);
                notifyDataSetChanged();
            }
        });
        item.getDown().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryVO current = mList.get(position);
                CategoryVO target = mList.get(position + 1);
                mList.set(position + 1, current);
                mList.set(position, target);
                notifyDataSetChanged();
            }
        });

        if (position == 0) {
            item.getUp().setVisibility(View.INVISIBLE);
        } else {
            item.getUp().setVisibility(View.VISIBLE);
        }
        if (position == mList.size() - 1) {
            item.getDown().setVisibility(View.INVISIBLE);
        } else {
            item.getDown().setVisibility(View.VISIBLE);
        }
        item.getName().setText(mList.get(position).getName());
        item.getName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                final View dialogView = mLayoutInflater.inflate(R.layout.category_dialog, null);
                dialogBuilder.setView(dialogView);

                final CategoryVO vo = mList.get(position);

                final EditText editText = (EditText) dialogView.findViewById(R.id.category_dialog_editText);
                editText.setText(vo.getName());

                dialogBuilder.setTitle(R.string.dialog_category_title);
                dialogBuilder.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        vo.setName(editText.getText().toString());
                        mList.set(position, vo);
                        notifyDataSetChanged();
                    }
                });
                dialogBuilder.setNegativeButton("Cancel·lar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                android.app.AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });
        item.getName().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                dialogBuilder.setTitle(R.string.manage_menu_category_dialog_delete);
                dialogBuilder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
                return true;
            }
        });

        return convertView;
    }

}