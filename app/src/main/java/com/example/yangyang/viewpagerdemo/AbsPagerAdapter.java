package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by yangyang on 2017/2/17.
 */

public abstract class AbsPagerAdapter extends PagerAdapter {
    protected final LinkedList<View> mScrapViews = new LinkedList<>();
    protected final LayoutInflater mInflater;
    protected final Context mContext;

    public AbsPagerAdapter(LayoutInflater inflater, Context context) {
        mInflater = inflater;
        mContext = context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = null;
        if (!mScrapViews.isEmpty()) {
            convertView = mScrapViews.removeFirst();
        }
        View view = getView(position, convertView, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object == null) {
            return;
        }
        View view = (View) object;
        container.removeView(view);
        mScrapViews.add(view);
    }

    protected abstract View getView(int position, View convertView, ViewGroup parent);

}
