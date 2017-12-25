package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yyh.newapplication.utils.SpannUtils;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/10/20 16:04
 *     version: 1.0
 *     desc   : 描述
 * </pre>
 */
public class SettingTextViewActivity extends AppCompatActivity {
    private TextView Tv_001;
    private TextView Tv_002;
    private TextView Tv_003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_textview_layout);
        initViews();
        String str = "热烈庆祝十九大顺利召开，跟着党走是我的信念";
        setView(str);
    }

    private void initViews() {
        Tv_001 = (TextView) findViewById(R.id.text_01);
        Tv_002 = (TextView) findViewById(R.id.text_02);
        Tv_003 = (TextView) findViewById(R.id.text_03);
    }

    private void setView(String str) {
        Tv_001.setText(SpannUtils.setSpanWithIncludeSizeAll(str, 2, 8,18));
        Tv_002.setText(SpannUtils.setSpanWithIncludeAll("$"+str,SettingTextViewActivity.this,R.color.colorPrimaryDark));
//        Tv_003.setText(SpannUtils.setSpanWithIncludeAll(" ¥ "+str,SettingTextViewActivity.this,1,3,R.color.colorAccent));
        Tv_003.setText(SpannUtils.setSpanWithIncludeAll(" ¥ "+"666",this,1,2,R.color.colorAccent,19));
    }
}
