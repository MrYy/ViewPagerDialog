package com.example.yangyang.viewpagerdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yangyang on 2017/2/17.
 */

public class HeadWearDialog  extends Dialog{
    private static final String TAG = HeadWearDialog.class.getSimpleName();

    public static final int PAGE_SIZE = 8;
    // TODO: 2017/2/17 主要逻辑先放在dialog中，后续抽离presenter。
    private SSViewPager mHeadwearPager;
    private ViewPagerShower mViewPagerShower;
    //获取头饰icons。
    private IGetHeadwearImg mGetHeadwearImg;
    private List<ImageView> mIconList;
    private Context mContext;
    public void setGetHeadwearImg(IGetHeadwearImg getHeadwearImg) {
        mGetHeadwearImg = getHeadwearImg;
    }

    public HeadWearDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_headwear);
        //设置dialog的外层window的大小。
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        modelProcess();
        initView();
    }

    private void modelProcess() {
        mIconList = mGetHeadwearImg.getImg();
    }

    private void initView() {
        mViewPagerShower = (ViewPagerShower) findViewById(R.id.headwear_pager_shower);

        mHeadwearPager = (SSViewPager) findViewById(R.id.headwear_pager);
        HeadwearPagerAdapter headwearPagerAdapter = new HeadwearPagerAdapter(mContext, mHeadwearPager);
        headwearPagerAdapter.setIconList(mIconList);
        mViewPagerShower.initViews(headwearPagerAdapter.getPageCount(), 0);
        mHeadwearPager.setAdapter(headwearPagerAdapter);
        mHeadwearPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, String.valueOf(position));
                mViewPagerShower.onPageSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
