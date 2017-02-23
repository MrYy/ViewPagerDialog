package com.example.yangyang.viewpagerdemo.headwear;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangyang on 2017/2/23.
 */

public class HeadwearItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int left = 0, right = 0, top = 50, bottom = 0;
        outRect.set(left, top, right, bottom);
    }
}
