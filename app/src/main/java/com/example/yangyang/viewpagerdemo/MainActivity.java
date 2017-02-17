package com.example.yangyang.viewpagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        ((ViewPagerShower) findViewById(R.id.desktop_shower)).initViews(2, 0);
        final HeadWearDialog headWearDialog = new HeadWearDialog(mActivity, R.style.headwear_dialog);
        headWearDialog.setGetHeadwearImg(new IGetHeadwearImg() {
            @Override
            public List<ImageView> getImg() {

                List<ImageView> list = new ArrayList<>();
                for (int i = 0;i<16;i++) {
                    list.add(getSingleImg());
                }
                return list;
            }

            private ImageView getSingleImg() {
                ImageView img = new ImageView(mActivity);
                img.setImageResource(R.drawable.ic_tab_user);
                img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return img;
            }
        });
        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headWearDialog.setCanceledOnTouchOutside(true);
                headWearDialog.show();
            }
        });
    }
}
