package com.high_technology_software.android.pauapp.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.controller.ItemDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.model.ItemVO;
import com.high_technology_software.android.pauapp.view.object.NewItemObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NewItemAdapter extends ArrayAdapter<ItemVO> {

    private List<ItemVO> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ItemDAO mDAO;

    public NewItemAdapter(Context context, List<ItemVO> list, LayoutInflater layoutInflater) {
        super(context, 0, list);
        mList = list;
        mContext = context;
        mLayoutInflater = layoutInflater;
        mDAO = new ItemDAO(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        NewItemObject item = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.manage_menu_new_item_list_item, parent, false);
            item = new NewItemObject();
            item.setName((TextView) convertView.findViewById(R.id.manage_menu_new_item_list_item_name));
            convertView.setTag(item);
        }
        item = (NewItemObject) convertView.getTag();

        item.getName().setText(mList.get(position).getFolder());
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
                        long id = mDAO.create(fillContent(vo, list.get(spinner.getSelectedItemPosition()).getId()), list.get(spinner.getSelectedItemPosition()).getId());
                        if(id != -1) {
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

    private ItemVO fillContent(ItemVO vo, int idCategory) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/APLICACIO_PAU/" + vo.getFolder();
        File directory = new File(path);
        List<String> directories = new ArrayList<>();

        for (File folder : directory.listFiles()) {
            String filename = folder.getName();
            if (filename.endsWith("mp3") || filename.endsWith("m4a")) {
                vo.setAudio(folder.getAbsolutePath());
            } else if (filename.endsWith("png")) {
                vo.setImage(folder.getAbsolutePath());
            } else {
                StringBuilder sb = new StringBuilder();
                try{
                    FileInputStream is = new FileInputStream(folder);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    is.close();
                } catch(OutOfMemoryError om){
                    om.printStackTrace();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
                vo.setName(sb.toString());
            }
        }

        vo.setOrder(mDAO.sequence(idCategory) + 1);

        return vo;
    }

}