package com.github.androidprogresslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ProgressLayout extends RelativeLayout {

    private static final String KEY_SAVE_ORIGINAL_STATE = "ProgressLayout.KEY_SAVE_ORIGINAL_STATE";
    private static final String KEY_SAVE_IS_PROGRESS = "ProgressLayout.KEY_SAVE_IS_PROGRESS";

    private static final String TAG_PROGRESS = "ProgressLayout.TAG_PROGRESS";

    private View mProgressView;
    private List<View> mContentViews = new ArrayList<View>();

    private int mBackgroundColor;
    private boolean mStartFromProgress;

    public ProgressLayout(Context context) {
        super(context);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressLayout);
        mBackgroundColor = a.getColor(R.styleable.ProgressLayout_progressBackground, Color.TRANSPARENT);
        mStartFromProgress = a.getBoolean(R.styleable.ProgressLayout_progress, false);
        a.recycle();


        LayoutParams layoutParams;

        // if progressBackground color == Color.TRANSPARENT just add progress bar
        if (mBackgroundColor == Color.TRANSPARENT) {
            mProgressView = new ProgressBar(getContext());

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(CENTER_IN_PARENT);
        } else { // else wrap progress bar in LinearLayout and set background color to LinearLayout
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setBackgroundColor(mBackgroundColor);

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            ProgressBar progressBar = new ProgressBar(getContext());
            linearLayout.addView(progressBar);

            mProgressView = linearLayout;
        }

        mProgressView.setTag(TAG_PROGRESS);
        if (!mStartFromProgress) {
            mProgressView.setVisibility(View.GONE);
        }
        addView(mProgressView, layoutParams);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || !child.getTag().equals(TAG_PROGRESS)) {
            mContentViews.add(child);
        }
    }

    public boolean isProgress() {
        return mProgressView.getVisibility() == View.VISIBLE;
    }

    public void setProgress(boolean visible) {
        mProgressView.setVisibility(visible ? View.VISIBLE : View.GONE);

        for (View v : mContentViews) {
            v.setVisibility(visible ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SAVE_ORIGINAL_STATE, super.onSaveInstanceState());
        bundle.putBoolean(KEY_SAVE_IS_PROGRESS, isProgress());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            setProgress(bundle.getBoolean(KEY_SAVE_IS_PROGRESS));
            Parcelable originalState = bundle.getParcelable(KEY_SAVE_ORIGINAL_STATE);
            super.onRestoreInstanceState(originalState);
        }
    }

}
