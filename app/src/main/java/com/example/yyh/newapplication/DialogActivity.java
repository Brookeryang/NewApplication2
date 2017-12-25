package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.yyh.newapplication.utils.ToastUtil;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : 杨永红
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/10/31/10:10
 *     version: 1.0
 *     desc   : Android 高仿 IOS 滚轮选择控件
 * </pre>
 */
public class DialogActivity extends AppCompatActivity {

    private Button showBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        showBut = (Button) findViewById(R.id.show_but);
        showBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }


    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_view, null);
        dialogBuilder.setView(dialogView);
        //找到滚动的View的控件
        LoopView loopView = (LoopView) dialogView.findViewById(R.id.loopView);
        //设置数据
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("第" + i + "条数据");
        }
        //条目滚动的监听事件
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                ToastUtil.showToastDefault(DialogActivity.this, "第" + index + "条数据");
            }
        });
        //设置原始数据
        loopView.setItems(list);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
