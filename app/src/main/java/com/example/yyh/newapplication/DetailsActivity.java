package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //设置滑动返回
        Slidr.attach(this);
    }
}
