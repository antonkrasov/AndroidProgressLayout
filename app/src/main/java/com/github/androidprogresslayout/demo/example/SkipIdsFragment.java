package com.github.androidprogresslayout.demo.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.androidprogresslayout.ProgressLayout;
import com.github.androidprogresslayout.demo.R;

import java.util.Arrays;

public class SkipIdsFragment extends Fragment {

    private Handler mHandler = new Handler();

    public static SkipIdsFragment newInstance() {
        return new SkipIdsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skip_ids, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressLayout progressLayout = (ProgressLayout) view.findViewById(R.id.progress_layout);

        // we don't want R.id.red_view to be hidden, when we show ProgressBar
        progressLayout.showProgress(Arrays.asList(R.id.red_view));

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressLayout.showContent();
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();

        mHandler.removeCallbacksAndMessages(null);
    }
}
