package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 头饰界面 recylerView adapter
 * Created by yangyang on 2017/2/17.
 */

public class HeadwearRecylerAdapter extends RecyclerView.Adapter<HeadwearRecylerAdapter.HeadwearViewHolder> {
    private final static String TAG = HeadwearRecylerAdapter.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Headwear> mSinglePageIconList;
    private ViewPager mViewPager;
    public HeadwearRecylerAdapter(Context context, ViewPager viewPager) {
        mSinglePageIconList = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mViewPager = viewPager;
    }

    @Override
    public HeadwearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeadwearViewHolder(mInflater.inflate(R.layout.item_headwear, null));
    }

    @Override
    public void onBindViewHolder(HeadwearViewHolder holder, int position) {
        holder.mIconImg.setImageResource(mSinglePageIconList.get(position).getmResId());
    }

    @Override
    public int getItemCount() {
        return mSinglePageIconList.size();
    }

    class HeadwearViewHolder extends RecyclerView.ViewHolder {

        ImageView mIconImg;
        public HeadwearViewHolder(View itemView) {
            super(itemView);
            mIconImg = (ImageView) itemView.findViewById(R.id.headwear_item);
        }
    }

    public void addForSinglePage(List<Headwear> singlePageIconList) {
        mSinglePageIconList.clear();
        mSinglePageIconList.addAll(singlePageIconList);
        notifyDataSetChanged();
    }

    private void clear() {
        mSinglePageIconList.clear();
    }
}
