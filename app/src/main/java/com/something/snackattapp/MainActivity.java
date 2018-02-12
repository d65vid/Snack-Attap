package com.something.snackattapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.something.snackattapp.adapter.SnackAdapter;
import com.something.snackattapp.snack.Snack;
import com.something.snackattapp.snack.SnackHelper;
import com.something.snackattapp.snack.SnackType;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<Snack> snacksAll;
    ArrayList<Snack> snacksVisible;
    HashMap<SnackType, Boolean> filterMap;

    CheckBox cbVeggie;
    CheckBox cbNonVeggie;
    SnackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.action_bar_main);
        setSupportActionBar(actionBar);

        // Init snacks
        this.snacksAll = SnackHelper.defaultSnacks();
        this.snacksVisible = snacksAll;
        this.filterMap = SnackHelper.defaultSnackTypeFilter();

        // Set up ListView
        final ListView lv = (ListView) findViewById(R.id.lv_main);
        this.adapter = new SnackAdapter(this, this.snacksVisible);
        lv.setAdapter(this.adapter);

        // Set up filter CheckBoxes
        this.cbVeggie = (CheckBox) findViewById(R.id.cb_filter_veggie);
        this.cbNonVeggie = (CheckBox) findViewById(R.id.cb_filter_non_veggie);
        this.cbVeggie.setChecked(filterMap.get(SnackType.VEGGIE));
        this.cbNonVeggie.setChecked(filterMap.get(SnackType.NON_VEGGIE));
        this.cbVeggie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterMap.put(SnackType.VEGGIE, isChecked);
                applyFiltering();
            }
        });
        this.cbNonVeggie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterMap.put(SnackType.NON_VEGGIE, isChecked);
                applyFiltering();
            }
        });

        // Set up submit button
        Button btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnacksDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                showAddSnackDialog();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void applyFiltering() {
        ArrayList<Snack> filteredSnacks = new ArrayList<>();
        for(Snack snack : this.snacksAll) {
            if(this.filterMap.get(snack.getType())) {
                filteredSnacks.add(snack);
            }
        }

        this.snacksVisible = filteredSnacks;
        this.adapter.updateSnacks(this.snacksVisible);
    }

    private void showSnacksDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String message = "";
        for(Snack snack : this.snacksVisible) {
            if(snack.isSelected()) {
                message += snack.getName() + "\n";
            }
        }

        if(message.length() > 0) {
            // At least one snack is selected
            builder.setTitle(SnackHelper.getSnackDialogTitlePhrase());
            builder.setMessage(message);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetSnacks();
                }
            });
        } else {
            // No snacks are selected
            builder.setMessage(R.string.dialog_submit_empty);
            builder.setPositiveButton(android.R.string.ok, null);
        }

        builder.create().show();
    }

    private void showAddSnackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new snack");

        // Create custom dialog layout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        // Create custom dialog spinner
        LinearLayout llType = new LinearLayout(this);
        llType.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv = new TextView(this);
        tv.setText(R.string.dialog_add_snack_type);
        final Spinner spinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        for(SnackType type : SnackType.values()) {
            adapter.insert(type.displayName(), type.id());
        }
        spinner.setAdapter(adapter);
        llType.addView(tv);
        llType.addView(spinner);
        // Create custom dialog edit text
        final EditText et = new EditText(this);

        ll.addView(llType);
        ll.addView(et);

        builder.setView(ll);
        builder.setPositiveButton(R.string.dialog_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //FIXME - write this
                if(et.getText().length() > 0) {
                    Snack newSnack = new Snack(et.getText().toString(), SnackType.values()[(spinner.getSelectedItemPosition())]);
                    snacksAll.add(newSnack);
                    applyFiltering();

                    Toast.makeText(MainActivity.this, "Added new snack: " + newSnack.getName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "No snack to add", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        builder.show();
    }

    private void resetSnacks() {
        // Reset selected snacks
        for(Snack snack : this.snacksAll) {
            snack.setSelected(false);
        }

        // Reset filter
        this.cbVeggie.setChecked(true);
        this.cbNonVeggie.setChecked(true);
        this.filterMap = SnackHelper.defaultSnackTypeFilter();
        applyFiltering();
    }
}
