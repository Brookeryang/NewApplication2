package com.example.yyh.newapplication.http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.yyh.newapplication.R;

public class DataActivity_02 extends AppCompatActivity implements View.OnClickListener {
    private Intent mIntent;
    //回复码
    private final int ReplyCode = 1;
    private TextView Tv002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_02);
        mIntent = new Intent();
        Tv002 = (TextView) findViewById(R.id.tv_002);
        Tv002.setOnClickListener(this);
        mIntent.putExtra("Data_01", "我是回传来的数据!");
        //设置回传数据
        setResult(ReplyCode, mIntent);
//        this.finish();
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
