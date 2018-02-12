package com.something.snackattapp.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.something.snackattapp.R;

public class SnackViewHolder {
    public TextView snackName;
    public CheckBox snackSelected;

    public SnackViewHolder(View v) {
        this.snackName = (TextView) v.findViewById(R.id.tv_snack_name);
        this.snackSelected = (CheckBox) v.findViewById(R.id.cb_snack_selected);

    }
}
