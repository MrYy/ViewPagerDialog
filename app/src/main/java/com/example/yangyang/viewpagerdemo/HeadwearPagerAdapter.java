package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyang on 2017/2/17.
 */

public class HeadwearPagerAdapter extends AbsPagerAdapter {
    private static final String TAG = HeadwearPagerAdapter.class.getSimpleName();
    private Context mContext;
    private ViewPager mViewPager;
    private List<ImageView> mIconList;
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
            convertView = mInflater.inflate(R.layout.list_headwear_dialog_page, null);
            viewHolder.mRecyclerView = (RecyclerView) convertView.findViewById(R.id.headwear_page_list);
            viewHolder.mRecylerAdapter = new HeadwearRecylerAdapter(mContext, mViewPager);
            viewHolder.mRecyclerView.setAdapter(viewHolder.mRecylerAdapter);
            viewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (HeadwearPageViewHolder) convertView.getTag();
        }
        viewHolder.mRecylerAdapter.addForSinglePage(getIconsForCurPage(position));
        return convertView;
    }

    private List<ImageView> getIconsForCurPage(int position) {
        int start = position * HeadWearDialog.PAGE_SIZE;
        int end = Math.min(start + HeadWearDialog.PAGE_SIZE, mIconList.size());
        List<ImageView> tmpList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            tmpList.add(mIconList.get(i));
        }
        return tmpList;
    }

    public int getPageCount() {
        return getCount();
    }

    @Override
    public int getCount() {
        int count = 0;
        int totalSize = mIconList.size();
        count += totalSize / HeadWearDialog.PAGE_SIZE;
        if (totalSize % HeadWearDialog.PAGE_SIZE > 0) count++;
        return count;
    }

    public void setIconList(List<ImageView> iconList) {
        mIconList = iconList;
    }
    static class HeadwearPageViewHolder{
        RecyclerView mRecyclerView;
        HeadwearRecylerAdapter mRecylerAdapter;
    }
}
