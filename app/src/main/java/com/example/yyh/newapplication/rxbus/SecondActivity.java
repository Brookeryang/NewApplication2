package com.example.yyh.newapplication.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yyh.newapplication.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void SendOnClick(View view) {
        //  RxBus.getmInstance().post("你好啊！ 我是SecondActivity");
        Person person = new Person();
        person.setAge(23);
        person.setName("艾燕子");
        BaseRxBus.get().post("yang", person);
        this.finish();
    }
}
