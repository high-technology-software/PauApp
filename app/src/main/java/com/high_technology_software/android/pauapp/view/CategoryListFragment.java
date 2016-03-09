package com.high_technology_software.android.pauapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.controller.CategoryDAO;
import com.high_technology_software.android.pauapp.model.CategoryVO;
import com.high_technology_software.android.pauapp.view.item.CategoryItem;

import java.util.List;

public class CategoryListFragment extends ListFragment {

    final int REQUEST_NAME = 0;
    final int REQUEST_ACTION = 1;

    private List<CategoryVO> mList;
    private CategoryDAO mDao;
    private CategoryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDao = new CategoryDAO(getContext());
        mList = mDao.read();

        for (int i = 0; i < 10; i++) {
            CategoryVO vo = new CategoryVO();
            vo.setName("Item -> " + i);
            mList.add(vo);
        }
        mAdapter = new CategoryAdapter(mList);
        setListAdapter(mAdapter);

        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                MenuCategoryFragment dialog = MenuCategoryFragment.newInstance();
//                dialog.setTargetFragment(CategoryListFragment.this, REQUEST_ACTION);
                //dialog.show(fm, "request_action");
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        CategoryVO vo = ((CategoryAdapter) getListAdapter()).getItem(position);
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        EditCategoryFragment dialog = EditCategoryFragment.newInstance(vo.getName(), position);
//        dialog.setTargetFragment(CategoryListFragment.this, REQUEST_NAME);
//        dialog.show(fm, "asd");
    }

    private class CategoryAdapter extends ArrayAdapter<CategoryVO> {

        public CategoryAdapter(List<CategoryVO> list) {
            super(getActivity(), 0, list);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            CategoryItem item = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.list_item_category, parent, false);
                item = new CategoryItem();
                item.setName((TextView) convertView.findViewById(R.id.list_item_category_text));
                item.setUp((ImageButton) convertView.findViewById(R.id.list_item_category_up));
                item.setDown((ImageButton) convertView.findViewById(R.id.list_item_category_down));
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
                    mAdapter.notifyDataSetChanged();
                }
            });
            item.getDown().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryVO current = mList.get(position);
                    CategoryVO target = mList.get(position + 1);
                    mList.set(position + 1, current);
                    mList.set(position, target);
                    mAdapter.notifyDataSetChanged();
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

            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CategoryAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_NAME) {
            String name = (String) data.getSerializableExtra(EditCategoryFragment.EXTRA_NAME);
            int position = (int) data.getSerializableExtra(EditCategoryFragment.EXTRA_POSITION);
            mList.get(position).setName(name);
            mAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_ACTION) {
        /*    String action = (String) data.getSerializableExtra(MenuCategoryFragment.EXTRA_ACTION);
            Intent i = new Intent(getContext(), ManageActivity2.class);
            startActivity(i);*/
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
