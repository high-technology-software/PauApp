package com.high_technology_software.android.pauapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.high_technology_software.android.pauapp.R;

public class MenuCategoryFragment extends DialogFragment {

    public static final String EXTRA_ACTION = "com.high_technology_software.android.pauapp.action";
    public static final String ACTION_ADD = "action_add";
    public static final String ACTION_SAVE = "action_save";
    public static final String ACTION_CANCEL= "action_cancel";

    public static MenuCategoryFragment newInstance() {
        Bundle args = new Bundle();

        MenuCategoryFragment fragment = new MenuCategoryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(String action) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_ACTION, action);

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_category_menu, null);

        Button buttonAdd = (Button) v.findViewById(R.id.dialog_category_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(ACTION_ADD);
            }
        });
        Button buttonSave = (Button) v.findViewById(R.id.dialog_category_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(ACTION_SAVE);
            }
        });
        Button buttonCancel = (Button) v.findViewById(R.id.dialog_category_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(ACTION_CANCEL);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dialog_category_menu_title)
                .create();
    }
}
