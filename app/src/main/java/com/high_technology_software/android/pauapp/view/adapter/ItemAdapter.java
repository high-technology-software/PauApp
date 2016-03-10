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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.object.CategoryObject;
import com.high_technology_software.android.pauapp.view.object.ItemObject;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemVO> {

    private List<ItemVO> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ItemDAO mDAO;

    public ItemAdapter(Context context, List<ItemVO> list, LayoutInflater layoutInflater) {
        super(context, 0, list);
        mList = list;
        mContext = context;
        mLayoutInflater = layoutInflater;
        mDAO = new ItemDAO(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemObject item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_item_list_item, parent, false);
            item = new ItemObject();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_item_list_item_name));
            item.setUp((ImageButton) convertView.findViewById(R.id.manage_menu_item_list_item_up));
            item.setDown((ImageButton) convertView.findViewById(R.id.manage_menu_item_list_item_down));
            convertView.setTag(item);
        }
        item = (ItemObject) convertView.getTag();
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
                final View dialogView = mLayoutInflater.inflate(R.layout.item_dialog, null);
                dialogBuilder.setView(dialogView);

                final ItemVO vo = mList.get(position);

                CategoryDAO dao = new CategoryDAO(mContext);
                final List<CategoryVO> list = dao.selector();

                final Spinner spinner = (Spinner) dialogView.findViewById(R.id.item_dialog_spinner);
                final SpinnerAdapter spinnerAdapter = new SpinnerAdapter(mContext, list, mLayoutInflater);
                spinner.setAdapter(spinnerAdapter);

                dialogBuilder.setTitle(R.string.dialog_item_title);
                dialogBuilder.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (vo.getFather().getId() != list.get(spinner.getSelectedItemPosition()).getId()) {
                            mDAO.update(vo, list.get(spinner.getSelectedItemPosition()).getId());
                            mList.remove(vo);
                            notifyDataSetChanged();
                        }
                    }
                });
                dialogBuilder.setNegativeButton("Cancel·lar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });

        return convertView;
    }

    private void swap(int from, int to) {
        ItemVO fromVO = mList.get(from);
        ItemVO toVO = mList.get(to);

        fromVO.setOrder(to);
        toVO.setOrder(from);

        mDAO.update(fromVO, fromVO.getFather().getId());
        mDAO.update(toVO, toVO.getFather().getId());

        mList.set(from, toVO);
        mList.set(to, fromVO);
        notifyDataSetChanged();
    }

}