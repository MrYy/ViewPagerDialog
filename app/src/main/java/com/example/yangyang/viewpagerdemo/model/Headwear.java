package com.example.yangyang.viewpagerdemo.model;

/**
 * Created by yangyang on 2017/2/17.
 */

public class Headwear {
    private int mResId;
    private boolean isSelected;
    private boolean notDownload;

    public boolean isNotDownload() {
        return notDownload;
    }

    public void setNotDownload(boolean notDownload) {
        this.notDownload = notDownload;
    }

    public boolean isSelected() {
        return isSelected;
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setmResId(int resId) {
        mResId = resId;
    }

    public int getmResId() {
        return mResId;
    }
}
