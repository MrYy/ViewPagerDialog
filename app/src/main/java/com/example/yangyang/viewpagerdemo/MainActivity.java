package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private HeadWearDialog headWearDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fresco.initialize(this);
        final EditText mEditText = (EditText) findViewById(R.id.edit_wear_count);
        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editStr = mEditText.getText().toString().trim();
                if (editStr.length() > 0) mWearCount = Integer.parseInt(editStr);
                showHeadwearDialog(MainActivity.this, new IHeadwear() {
                    @Override
                    public List<Headwear> getHeadwear() {
                        //获取 headwear 封装，目前仅封装了图片 uri 的信息，是否已经下载的信息。
                        List<Headwear> list = new ArrayList<>();
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
                        headWearDialog.finishDownload(headwear);

                    }




                });
            }
        });


    }

    //使用方法。
    private void showHeadwearDialog(final Context context, IHeadwear iHeadwear) {
        headWearDialog = new HeadWearDialog(context, iHeadwear);
        //设置图片资源drawable数组。
        headWearDialog.show();

    }
}
