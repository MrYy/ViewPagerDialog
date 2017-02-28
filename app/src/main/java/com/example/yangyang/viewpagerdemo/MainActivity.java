package com.example.yangyang.viewpagerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.yangyang.viewpagerdemo.headwear.Headwear;
import com.example.yangyang.viewpagerdemo.headwear.HeadWearDialog;
import com.example.yangyang.viewpagerdemo.headwear.IHeadwear;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用方法
 * 实现IHeadwear，
 * 如果需要某些信息，可以在 Headwear中进行 set 和 get
 */
public class MainActivity extends AppCompatActivity {
    private int mWearCount = 16;
    private HeadWearDialog mDialog;
    private IHeadwear mIHeadwear = new IHeadwear() {
        @Override
        public List<Headwear> getHeadwear() {
            //初始化头饰图片数组
            //获取 headwear 封装，目前仅封装了图片 uri 的信息，是否已经下载的信息。
            ArrayList<Headwear> list = new ArrayList<>();
            for (int i = 0; i < mWearCount; i++) {
                Headwear headwear = new Headwear();
                headwear.setUri("http://d.lanrentuku.com/down/png/1610/50space-elements/rocket-ship-2.png");
                if (i % 3 == 0) headwear.setNotDownload(true);
                list.add(headwear);
            }
            return list;
        }

        @Override
        public void selectNoneHeadwear() {
            //主线程中，取消头饰
        }

        @Override
        public void selectHeadwear(Headwear headwear) {
            //下载头饰
            //完成下载
            mDialog.finishDownload(headwear);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        final EditText mEditText = (EditText) findViewById(R.id.edit_wear_count);
        //初始化dialog，注意不要重复初始化
        mDialog = new HeadWearDialog(this, mIHeadwear);

        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editStr = mEditText.getText().toString().trim();
                if (editStr.length() > 0) mWearCount = Integer.parseInt(editStr);
                mDialog.show();
                animate();
            }

            private void animate() {
//                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.pop);
//                (findViewById(R.id.txt)).startAnimation(animation);
            }
        });


    }



}
