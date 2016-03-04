package com.high_technology_software.android.pauapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.high_technology_software.android.pauapp.R;

public class ManageMenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_manage_menu, parent, false);

        Button categoryCrud = (Button) v.findViewById(R.id.categoryCrud);
        Button categoryOrder = (Button) v.findViewById(R.id.categoryOrder);
        Button itemCrud = (Button) v.findViewById(R.id.itemCrud);
        Button itemOrder = (Button) v.findViewById(R.id.itemOrder);
        categoryCrud.setOnClickListener(buttonListener);
        categoryOrder.setOnClickListener(buttonListener);
        itemCrud.setOnClickListener(buttonListener);
        itemOrder.setOnClickListener(buttonListener);

        return v;
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), CategoryActivity.class);
            startActivity(i);
//            Intent i = new Intent(getActivity(), CrimePagerActivity.class);
//            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
//            startActivity(i);
        }
    };
}
