package com.example.mayankverma.locationexchange;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RiderHomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner mChooseOrderSpinner;
    public static final String ORDER_ID_CHOOSEN = "order_id_choosen";
    private LinearLayout mBtnLl;
    private Button mHelpBtn;
    private Button mDeliveredBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_home);

        setControls();
    }

    private void setControls() {
        mChooseOrderSpinner = (Spinner) findViewById(R.id.choose_order_spinner);
        mChooseOrderSpinner.setOnItemSelectedListener(this);
        List<Integer> orderData = new ArrayList<>();
        orderData.add(1865297);
        orderData.add(1865278);
        orderData.add(1865279);
        orderData.add(1865280);
        orderData.add(1865281);

        ArrayAdapter<Integer> orderAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, orderData);

        mChooseOrderSpinner.setAdapter(orderAdapter);

        mBtnLl = (LinearLayout) findViewById(R.id.btns_ll);
        mHelpBtn = (Button) findViewById(R.id.help);
        mDeliveredBtn = (Button) findViewById(R.id.delivered);
        mHelpBtn.setOnClickListener(this);
        mDeliveredBtn.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Saving the hard coded order Id in preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORDER_ID_CHOOSEN, String.valueOf(1865297));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help:
                //Launch the other fragment
                break;
            case R.id.delivered:
                //Launch the other fragment
                break;
            default:
                return;
        }
    }
}
