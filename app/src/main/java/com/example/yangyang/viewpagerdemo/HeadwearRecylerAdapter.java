package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 头饰界面 recylerView adapter
 * Created by yangyang on 2017/2/17.
 */

public class HeadwearRecylerAdapter extends RecyclerView.Adapter<HeadwearRecylerAdapter.HeadwearViewHolder> implements View.OnClickListener {
    private final static String TAG = HeadwearRecylerAdapter.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Headwear> mSinglePageIconList;
    private ViewPager mViewPager;
    private IHeadwear mIHeadwear;

    public HeadwearRecylerAdapter(Context context, ViewPager viewPager, IHeadwear iHeadwear) {
        mSinglePageIconList = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mViewPager = viewPager;
        mIHeadwear = iHeadwear;
    }

    @Override
    public HeadwearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HeadwearViewHolder(mInflater.inflate(R.layout.item_headwear, null));
    }

    @Override
    public void onBindViewHolder(HeadwearViewHolder holder, int position) {
        Headwear curSelect = mSinglePageIconList.get(position);
        holder.mIconImg.setImageResource(mSinglePageIconList.get(position).getmResId());
        holder.mIconImg.setTag(position);
        holder.mIconImg.setOnClickListener(this);
        holder.mBgLayout.setBackgroundResource(curSelect.isSelected() ? R.drawable.headwear_circle : 0);

    }


    @Override
    public int getItemCount() {
        return mSinglePageIconList.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Headwear curSelect = mSinglePageIconList.get(position);
        if (curSelect.isSelected()) return;
        refreshIconStatus();
        curSelect.setSelected(true);
        if (position == 0) {
            //0的位置是不选择头饰
            mIHeadwear.selectNoneHeadwear();
        } else {
            mIHeadwear.selectHeadwear(curSelect);
        }
        notifyDataSetChanged();
    }

    // TODO: 2017/2/21  暂时遍历，后续可优化为记录上一次选择。
    private void refreshIconStatus() {
        for (Headwear headwear : mSinglePageIconList) {
            headwear.setSelected(false);
        }
    }

    class HeadwearViewHolder extends RecyclerView.ViewHolder {

        ImageView mIconImg;
        RelativeLayout mBgLayout;

        public HeadwearViewHolder(View itemView) {
            super(itemView);
            mIconImg = (ImageView) itemView.findViewById(R.id.headwear_item);
            mBgLayout = (RelativeLayout) itemView.findViewById(R.id.bg_item_headwear);
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
