package com.example.yangyang.viewpagerdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                showHeadwearDialog(MainActivity.this,new IHeadwear() {
                    @Override
                    public List<Headwear> getHeadwear() {

                        List<Headwear> list = new ArrayList<>();
                        for (int i = 0; i < mWearCount; i++) {
                            Headwear headwear = new Headwear();
                            headwear.setmResId(R.drawable.ic_tab_user);
                            list.add(headwear);
                        }
                        return list;
                    }

                    @Override
                    public void selectNoneHeadwear() {
                        Toast.makeText(MainActivity.this, "取消头饰选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void selectHeadwear(Headwear headwear) {
                        Toast.makeText(MainActivity.this, "选择头饰：" + headwear.getmResId(), Toast.LENGTH_SHORT).show();
                    }


                });
            }
        });



    }

    private void showHeadwearDialog(final Context context, IHeadwear iHeadwear) {
        HeadWearDialog headWearDialog = new HeadWearDialog(context, iHeadwear);
        //设置图片资源drawable数组。
        headWearDialog.show();

    }
}
