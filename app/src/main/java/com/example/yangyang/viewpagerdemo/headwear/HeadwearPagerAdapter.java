package com.example.yangyang.viewpagerdemo.headwear;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangyang.viewpagerdemo.sdk.AbsPagerAdapter;
import com.example.yangyang.viewpagerdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头饰 viewPager adapter
 * Created by yangyang on 2017/2/17.
 */

public class HeadwearPagerAdapter extends AbsPagerAdapter {
    private static final int LINE_SIZE = 4;
    private static final String TAG = HeadwearPagerAdapter.class.getSimpleName();
    private Context mContext;
    private ViewPager mViewPager;
    private List<Headwear> mIconList;
    private IHeadwear mIHeadwear;
    //每行有多少icon。

    //每一页recylerview的adapter
    private Map<Integer, HeadwearRecylerAdapter> mPageAdapterMap;
    //上一次选择的头饰。
    private Headwear mLastSelectHeadwear;

    private SelectHeadwearInterface mISelectHeadwear;

    interface SelectHeadwearInterface {
        void select(Headwear headwear);
    }
    public HeadwearPagerAdapter(Context context, ViewPager viewPager, IHeadwear headwearInterface) {
        super(LayoutInflater.from(context), context);
        mContext = context;
        mViewPager = viewPager;
        mIHeadwear = headwearInterface;
        init();
    }

    private void init() {
        mPageAdapterMap = new HashMap<>();
        mISelectHeadwear = new SelectHeadwearInterface() {
            @Override
            public void select(Headwear headwear) {
                mLastSelectHeadwear.setSelected(false);
                headwear.setSelected(true);
                mLastSelectHeadwear = headwear;
                for (HeadwearRecylerAdapter adapter : mPageAdapterMap.values()) {
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        //采用holder模式
        HeadwearPageViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new HeadwearPageViewHolder();
            convertView = mInflater.inflate(R.layout.list_headwear_dialog_page, null);
            viewHolder.mRecyclerView = (RecyclerView) convertView.findViewById(R.id.headwear_page_list);
            viewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, LINE_SIZE, GridLayoutManager.VERTICAL, false));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (HeadwearPageViewHolder) convertView.getTag();
        }

        if (!mPageAdapterMap.containsKey(position)){
            //每个页面仅有一个adapter, adapter 不可以复用
            mPageAdapterMap.put(position, new HeadwearRecylerAdapter(position, mContext, mViewPager, mIHeadwear, mISelectHeadwear));
        }
        viewHolder.mRecylerAdapter = mPageAdapterMap.get(position);
        viewHolder.mRecylerAdapter.addForSinglePage(getIconsForCurPage(position));
        viewHolder.mRecyclerView.setAdapter(viewHolder.mRecylerAdapter);
        return convertView;
    }

    private List<Headwear> getIconsForCurPage(int position) {
        int start = position * (HeadWearDialog.PAGE_SIZE);
        int end = Math.min(start + HeadWearDialog.PAGE_SIZE, mIconList.size());
        List<Headwear> tmpList = new ArrayList<>();

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
        //每页第一个为默认icon。即不选择头饰。
        int count = 0;
        int totalSize = mIconList.size();
        count += totalSize / HeadWearDialog.PAGE_SIZE;
        if (totalSize % HeadWearDialog.PAGE_SIZE > 0) count++;
        return count;
    }

    public void setIconList(List<Headwear> iconList) {
        mIconList = iconList;
        mLastSelectHeadwear = iconList.get(0);
    }

    public void finishDownload(int selected, Headwear headwear) {
        if (!headwear.isNotDownload()) return;
        headwear.setNotDownload(false);
        mPageAdapterMap.get(selected).notifyDataSetChanged();
    }

    static class HeadwearPageViewHolder{
        RecyclerView mRecyclerView;
        HeadwearRecylerAdapter mRecylerAdapter;
    }
}
