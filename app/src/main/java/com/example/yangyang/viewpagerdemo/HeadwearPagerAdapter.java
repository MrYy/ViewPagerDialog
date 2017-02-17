package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yangyang on 2017/2/17.
 */

public class HeadwearPagerAdapter extends AbsPagerAdapter {
    private static final String TAG = HeadwearPagerAdapter.class.getSimpleName();
    private Context mContext;
    private ViewPager mViewPager;
    private List<ImageView> mIconList;
    private static final int PAGE_SIZE = 8;
    public HeadwearPagerAdapter(Context context, ViewPager viewPager) {
        super(LayoutInflater.from(context), context);
        mContext = context;
        mViewPager = viewPager;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        //采用holder模式

        HeadwearPageViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new HeadwearPageViewHolder();
            convertView = mInflater.inflate(R.layout.item_headwear_dialog_page, null);
//            viewHolder.mRecyclerView = (RecyclerView) convertView.findViewById(R.id.headwear_page_list);
//            viewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (HeadwearPageViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        int count = 0;
        int totalSize = mIconList.size();
        count += totalSize / PAGE_SIZE;
        if (totalSize % PAGE_SIZE > 0) count++;
        return count;
    }

    public void setIconList(List<ImageView> iconList) {
        mIconList = iconList;
    }
    static class HeadwearPageViewHolder{
        RecyclerView mRecyclerView;

    }
}
