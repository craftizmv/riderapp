package com.example.mayankverma.locationexchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        mRestaurent = (TextView) mRootView.findViewById(R.id.doctor);
        mNothing = (TextView) mRootView.findViewById(R.id.nothing);

        mTvSeeAround.setOnClickListener(this);
        mTvShpMall.setOnClickListener(this);
        mBank.setOnClickListener(this);
        mDoctor.setOnClickListener(this);
        mRestaurent.setOnClickListener(this);
        mNothing.setOnClickListener(this);

        mGreatWork = (TextView) mRootView.findViewById(R.id.great);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.see_around_tv:
                break;
            case R.id.shopping_mall:
                break;
            case R.id.bank
                bre

        }
    }
}
