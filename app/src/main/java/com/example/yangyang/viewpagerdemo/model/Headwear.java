package com.example.yangyang.viewpagerdemo.model;

/**
 * Created by yangyang on 2017/2/17.
 */

public class Headwear {

    private String mUri;
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

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }


}
