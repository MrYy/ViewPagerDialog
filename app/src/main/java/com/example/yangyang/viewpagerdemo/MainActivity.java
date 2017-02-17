package com.example.yangyang.viewpagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        findViewById(R.id.btn_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeadWearDialog headWearDialog = new HeadWearDialog(mActivity, R.style.headwear_dialog);
                headWearDialog.setCanceledOnTouchOutside(true);
                headWearDialog.show();
            }
        });
    }
}
