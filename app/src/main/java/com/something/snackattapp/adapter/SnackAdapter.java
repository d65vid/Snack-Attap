package com.something.snackattapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.something.snackattapp.R;
import com.something.snackattapp.snack.Snack;

import java.util.ArrayList;

public class SnackAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Snack> snacks;

    public SnackAdapter(Context context, ArrayList<Snack> snacks) {
        this.context = context;
        this.snacks = snacks;
    }

    @Override
    public int getCount() {
        return this.snacks.size();
    }

    @Override
    public Snack getItem(int position) {
        return this.snacks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        final Snack item = getItem(position);

        if(item != null) {
            final SnackViewHolder holder;
            if(v == null) {
                LayoutInflater inf = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inf.inflate(R.layout.snack_item, null);

                //Init view holder
                holder = new SnackViewHolder(v);
                v.setTag(holder);
            } else {
                //Get holder from v
                holder = (SnackViewHolder) v.getTag();
            }

            // Set snack item values
            holder.snackSelected.setChecked(item.isSelected());
            holder.snackName.setText(item.getName());
            switch(item.getType()) {
                case VEGGIE:
                    holder.snackName.setTextColor(this.context.getResources().getColor(R.color.snack_veggie));
                    break;
                case NON_VEGGIE:
                    holder.snackName.setTextColor(this.context.getResources().getColor(R.color.snack_non_veggie));
                    break;
            }

            holder.snackSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setSelected(isChecked);
                }
            });
            holder.snackName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.snackSelected.toggle();
                }
            });
        }

        return v;
    }

    public void updateSnacks(ArrayList<Snack> snacks) {
        this.snacks = snacks;
        notifyDataSetChanged();
    }
}
