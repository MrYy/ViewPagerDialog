package com.example.yangyang.viewpagerdemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yangyang.viewpagerdemo.R;

import java.util.ArrayList;

/**
 * Created by yangyang on 2017/2/17.
 */

public class ViewPagerShower extends LinearLayout {

    private ArrayList<ImageView> mAl = new ArrayList<ImageView>();
    private int mNow = -1;
    private Drawable mNormalDrawable;
    private Drawable mSelectedDrawable;
    private final LayoutParams mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public ViewPagerShower(Context context) {
        super(context);
    }

    public ViewPagerShower(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initDrawable(Drawable normalDrawable, Drawable selectedDrawable) {
        mNormalDrawable = normalDrawable;
        mSelectedDrawable = selectedDrawable;
    }

    public void initViews(int total, int begin) {
        this.removeAllViews();
        mAl.clear();
        for (int i = 0; i < total; i++) {
            ImageView imageView = new ImageView(this.getContext());
            if (mNormalDrawable != null) {
                imageView.setImageDrawable(mNormalDrawable);
            } else {
                imageView.setImageResource(R.drawable.dot_gray);
            }
            if (i < total - 1) {
                mLayoutParams.setMargins(0, 0, (int) dip2Px(getContext(), 10), 0);
                addView(imageView, mLayoutParams);
            } else {
                addView(imageView);
            }
            mAl.add(imageView);
        }
        onPageSelect(begin);
    }

    public void onPageSelect(int position) {
        if (mAl == null || mAl.isEmpty())
            return;
        if (mNow >= 0 && mNow < mAl.size()) {
            if (mNormalDrawable != null) {
                mAl.get(mNow).setImageDrawable(mNormalDrawable);
            } else {
                mAl.get(mNow).setImageResource(R.drawable.dot_gray);
            }
        }
        if (position >= 0 && position < mAl.size()) {
            if (mSelectedDrawable != null) {
                mAl.get(position).setImageDrawable(mSelectedDrawable);
            } else {
                mAl.get(position).setImageResource(R.drawable.dot_colour);
            }
            mNow = position;
        }
    }

    public static float dip2Px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }
}

