package com.high_technology_software.android.pauapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.high_technology_software.android.pauapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditCategoryFragment extends DialogFragment {

    public static final String EXTRA_NAME = "com.high_technology_software.android.pauapp.name";
    public static final String EXTRA_POSITION = "com.high_technology_software.android.pauapp.position";

    private String mName;
    private int mPosition;
    private EditText mEditText;

    public static EditCategoryFragment newInstance(String name, int position) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NAME, name);
        args.putSerializable(EXTRA_POSITION, position);

        EditCategoryFragment fragment = new EditCategoryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_NAME, mName);
        i.putExtra(EXTRA_POSITION, mPosition);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mName = (String)getArguments().getSerializable(EXTRA_NAME);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_category, null);

        mEditText = (EditText)v.findViewById(R.id.dialog_category_name);
        mEditText.setText(mName);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dialog_category_title)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mName = mEditText.getText().toString();
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .setNegativeButton(
                        android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_CANCELED);
                            }
                        })
                .create();
    }
}
