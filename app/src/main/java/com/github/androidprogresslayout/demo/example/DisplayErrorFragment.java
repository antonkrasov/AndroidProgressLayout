package com.github.androidprogresslayout.demo.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.androidprogresslayout.ProgressLayout;
import com.github.androidprogresslayout.demo.R;

public class DisplayErrorFragment extends Fragment {

    private Handler mHandler = new Handler();

    public static DisplayErrorFragment newInstance() {
        return new DisplayErrorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_handler, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressLayout progressLayout = (ProgressLayout) view.findViewById(R.id.progress);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressLayout.showErrorText();
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();

        mHandler.removeCallbacksAndMessages(null);
    }
}
