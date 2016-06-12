package com.example.mayankverma.locationexchange;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RiderHomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    private Spinner mChooseOrderSpinner;
    public static final String ORDER_ID_CHOOSEN = "order_id_choosen";
    public static final String SELECTED_LANDMARK = "selected_landmark";
    private LinearLayout mBtnLl;
    private Button mHelpBtn;
    private Button mDeliveredBtn;
    private ScrollView mFragContainer;
    private LinearLayout mMainActivityLl;
    private RelativeLayout mSbmtLandRl;
    private Button mSubLandMark;

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

        mFragContainer = (ScrollView) findViewById(R.id.frag_container);
        mMainActivityLl = (LinearLayout) findViewById(R.id.main_activity_ll);

        mSbmtLandRl = (RelativeLayout) findViewById(R.id.submit_landmark);
        mSubLandMark = (Button) findViewById(R.id.submit_landmark_btn);
        mSubLandMark.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Saving the hard coded order Id in preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORDER_ID_CHOOSEN, String.valueOf(1865297));

        mBtnLl.setVisibility(View.VISIBLE);
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
                loadFragment();
                break;
            case R.id.delivered:
                //Launch the other fragment
                showLandMarkView();
                break;
            case R.id.submit_landmark_btn:
                postLandMarkData();
                break;
            default:
                return;
        }
    }

    private void postLandMarkData() {

    }

    private void showLandMarkView() {
        mSbmtLandRl.setVisibility(View.VISIBLE);
        mBtnLl.setVisibility(View.GONE);
    }

    private void callApiToPostLatLong() {

    }

    private void loadFragment() {
        mFragContainer.setVisibility(View.VISIBLE);
        LandMarkChooserFragment fragment = (LandMarkChooserFragment) getSupportFragmentManager().findFragmentByTag(LandMarkChooserFragment.TAG);
        if (fragment == null) {
            fragment = new LandMarkChooserFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, fragment, LandMarkChooserFragment.TAG)
                    .addToBackStack(null).commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackStackChanged() {
        int canPop = getSupportFragmentManager().getBackStackEntryCount();
        if (canPop > 0) {
            mFragContainer.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStack();
        }

    }
}
