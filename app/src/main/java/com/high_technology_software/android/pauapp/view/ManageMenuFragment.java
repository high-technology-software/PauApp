package com.high_technology_software.android.pauapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.high_technology_software.android.pauapp.R;
import com.high_technology_software.android.pauapp.view.manage.ManageMenuCategoryActivity;

public class ManageMenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.manage_menu_activity, parent, false);

        Button categoryCrud = (Button) v.findViewById(R.id.categoryCrud);
        Button itemCrud = (Button) v.findViewById(R.id.itemCrud);
        Button itemOrder = (Button) v.findViewById(R.id.itemOrder);
        categoryCrud.setOnClickListener(buttonListener);
        itemCrud.setOnClickListener(buttonListener);
        itemOrder.setOnClickListener(buttonListener);

        return v;
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), ManageMenuCategoryActivity.class);
            startActivity(i);
//            Intent i = new Intent(getActivity(), CrimePagerActivity.class);
//            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
//            startActivity(i);
        }
    };
}
