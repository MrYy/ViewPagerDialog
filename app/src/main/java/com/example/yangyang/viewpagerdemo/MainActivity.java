package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yangyang.viewpagerdemo.model.Headwear;
import com.example.yangyang.viewpagerdemo.widget.HeadWearDialog;
import com.example.yangyang.viewpagerdemo.widget.IHeadwear;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用方法。
 * 实例化HeadwearDialog
 * 给入接口，获取headwear，目前是设置resource ID，
 */
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
                showHeadwearDialog(MainActivity.this, new IHeadwear() {
                    @Override
                    public List<Headwear> getHeadwear() {
                        List<Headwear> list = new ArrayList<>();
                        for (int i = 0; i < mWearCount; i++) {
                            Headwear headwear = new Headwear();
                            headwear.setmResId(R.drawable.ic_tab_user);
                            if (i % 3 == 0) headwear.setNotDownload(true);
                            list.add(headwear);
                        }
                        return list;
                    }

                    @Override
                    public void selectNoneHeadwear() {
                    }

                    @Override
                    public void selectHeadwear(Headwear headwear) {
                    }


                });
            }
        });


    }

    //使用方法。
    private void showHeadwearDialog(final Context context, IHeadwear iHeadwear) {
        HeadWearDialog headWearDialog = new HeadWearDialog(context, iHeadwear);
        //设置图片资源drawable数组。
        headWearDialog.show();

    }
}
