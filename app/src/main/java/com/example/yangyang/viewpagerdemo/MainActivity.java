package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int mWearCount = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText mEditText = (EditText) findViewById(R.id.edit_wear_count);
        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editStr = mEditText.getText().toString().trim();
                if (editStr.length() > 0) mWearCount = Integer.parseInt(editStr);
                showHeadwearDialog(MainActivity.this,new IGetHeadwearImg() {
                    @Override
                    public List<Headwear> getImg() {

                        List<Headwear> list = new ArrayList<>();
                        for (int i = 0; i < mWearCount; i++) {
                            Headwear headwear = new Headwear();
                            headwear.setmResId(R.drawable.ic_tab_user);
                            list.add(headwear);
                        }
                        return list;
                    }


                });
            }
        });



    }

    private void showHeadwearDialog(final Context context, IGetHeadwearImg getHeadwearImg) {
        HeadWearDialog headWearDialog = new HeadWearDialog(context, R.style.headwear_dialog);
        //设置图片资源drawable数组。
        headWearDialog.setGetHeadwearImg(getHeadwearImg);
        headWearDialog.setCanceledOnTouchOutside(true);
        headWearDialog.show();

    }
}
