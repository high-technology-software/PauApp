package com.high_technology_software.android.pauapp.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.object.CategoryObject;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryVO> {

    private List<CategoryVO> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private CategoryDAO mDAO;

    public CategoryAdapter(Context context, List<CategoryVO> list, LayoutInflater layoutInflater) {
        super(context, 0, list);
        mList = list;
        mContext = context;
        mLayoutInflater = layoutInflater;
        mDAO = new CategoryDAO(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        CategoryObject item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_category_list_item, parent, false);
            item = new CategoryObject();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_category_list_item_name));
            item.setUp((ImageButton) convertView.findViewById(R.id.manage_menu_category_list_item_up));
            item.setDown((ImageButton) convertView.findViewById(R.id.manage_menu_category_list_item_down));
            convertView.setTag(item);
        }
        item = (CategoryObject) convertView.getTag();
        item.getUp().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap(position, position - 1);
            }
        });
        item.getDown().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap(position, position + 1);
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
                        String backupName = vo.getName();
                        vo.setName(editText.getText().toString());

                        if (mDAO.update(vo)) {
                            mList.set(position, vo);
                            notifyDataSetChanged();
                        } else {
                            vo.setName(backupName);
                            Toast.makeText(mContext, R.string.manage_menu_category_activity_unique, Toast.LENGTH_LONG).show();
                        }
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
                        if (mDAO.delete(mList.get(position))) {
                            ItemDAO dao = new ItemDAO(mContext);
                            for (ItemVO itemVO : dao.read(mList.get(position).getId())) {
                                dao.delete(itemVO);
                            }
                            mList.remove(position);
                            notifyDataSetChanged();

                            for (int index = position; index < mList.size(); index++) {
                                CategoryVO vo = mList.get(index);
                                vo.setOrder(index);
                                mDAO.update(vo);
                            }
                        }
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

    private void swap(int from, int to) {
        CategoryVO fromVO = mList.get(from);
        CategoryVO toVO = mList.get(to);

        fromVO.setOrder(to);
        toVO.setOrder(from);

        mDAO.update(fromVO);
        mDAO.update(toVO);

        mList.set(from, toVO);
        mList.set(to, fromVO);
        notifyDataSetChanged();
    }

}