package com.example.yangyang.viewpagerdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by yangyang on 2017/2/28.
 */

public class MoreDialog extends Dialog{
    private Window mWindow;

    public MoreDialog(Context context) {
        super(context, R.style.more_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_headwear);
        setCanceledOnTouchOutside(true);
        mWindow = getWindow();
        mWindow.setGravity(Gravity.TOP);
        mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, 300);
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.y = 200;
        mWindow.setAttributes(lp);
    }
}
