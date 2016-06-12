package com.example.mayankverma.locationexchange;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.GsonRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by mayankverma on 12/06/16.
 */
public class LandMarkChooserFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    private TextView mTvSeeAround;
    private TextView mTvShpMall;
    private TextView mBank;
    private TextView mDoctor;
    private TextView mRestaurent;
    private TextView mNothing;
    private TextView mGreatWork;

    public static final String TAG = LandMarkChooserFragment.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private LinearLayout mTypeLl;
    private LinearLayout mLandLl;

    private LayoutInflater mLayoutInflator;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_landmark_chooser, container, false);

        initControls();

        return mRootView;
    }

    private void initControls() {
        mTvSeeAround = (TextView) mRootView.findViewById(R.id.see_around_tv);
        mTvShpMall = (TextView) mRootView.findViewById(R.id.shopping_mall);
        mBank = (TextView) mRootView.findViewById(R.id.bank);
        mDoctor = (TextView) mRootView.findViewById(R.id.doctor);
        mRestaurent = (TextView) mRootView.findViewById(R.id.restaurent);
        mNothing = (TextView) mRootView.findViewById(R.id.nothing);

        mTvShpMall.setOnClickListener(this);
        mBank.setOnClickListener(this);
        mDoctor.setOnClickListener(this);
        mRestaurent.setOnClickListener(this);
        mNothing.setOnClickListener(this);

        mTypeLl = (LinearLayout) mRootView.findViewById(R.id.type_ll);
        mLandLl = (LinearLayout) mRootView.findViewById(R.id.landmark_view);


        mGreatWork = (TextView) mRootView.findViewById(R.id.great);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_mall:
                callApiWithLandMark("shopping_mall");
                break;
            case R.id.bank:
                callApiWithLandMark("bank");
                break;
            case R.id.doctor:
                callApiWithLandMark("doctor");
                break;
            case R.id.restaurent:
                callApiWithLandMark("restaurant");
                break;
            case R.id.nothing:
                callApiWithLandMark("");
                break;
            default:
                break;
        }
    }

    private void callApiWithLandMark(String filter) {
        //Show the Api Results and Update the view
        String orderId = mSharedPreferences.getString(RiderHomeActivity.ORDER_ID_CHOOSEN, "");
        if (!TextUtils.isEmpty(orderId)) {
            String endPointUrl = "http://10.0.1.2:5000/landmarks?order_id=1865297&type=bank";
            ArrayMap<String, String> params = new ArrayMap<>();
            params.put("order_id", orderId);
            params.put("type", filter);
            GsonRequest<ResponeLandmark> stringRequest = new GsonRequest<ResponeLandmark>(endPointUrl, ResponeLandmark.class, params, new Response.Listener<ResponeLandmark>() {
                @Override
                public void onResponse(ResponeLandmark response) {
                    updateUI(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }
    }

    private void updateUI(ResponeLandmark response) {
        mTypeLl.setVisibility(View.GONE);
        mLayoutInflator = LayoutInflater.from(getActivity());
        for (int i = 0; i < response.landmarks.size(); i++) {
            final View view = mLayoutInflator.inflate(R.layout.landmark_view, mLandLl, false);
            TextView landmarkname = (TextView) view.findViewById(R.id.landmark_name);
            landmarkname.setText(response.landmarks.get(0));
            mTypeLl.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                TextView tv = (TextView) view;

                @Override
                public void onClick(View v) {
                    mEditor.putString(RiderHomeActivity.SELECTED_LANDMARK, tv.getText().toString());
                }
            });
        }
    }
}
