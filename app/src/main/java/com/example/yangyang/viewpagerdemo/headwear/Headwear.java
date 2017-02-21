package com.example.yangyang.viewpagerdemo.headwear;

/**
 * Created by yangyang on 2017/2/17.
 */

public class Headwear {

    //-------------对外使用字段---------------//
    //设置图片下载uri
    private String mUri;
    //如果没有下载，则设置该值
    private boolean notDownload;

    //-------------对外使用字段---------------//
    private boolean isSelected;

    public boolean isNotDownload() {
        return notDownload;
    }

    public void setNotDownload(boolean notDownload) {
        this.notDownload = notDownload;
    }

    protected boolean isSelected() {
        return isSelected;
    }


    protected void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }


}
