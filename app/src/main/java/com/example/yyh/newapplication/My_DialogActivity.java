package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yyh.newapplication.my_dialog.DialogEditSureCancel;
import com.example.yyh.newapplication.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : 杨永红
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/12/13/16:13
 *     version: 1.0
 *     desc   : 自定义的Dialog
 * </pre>
 */
public class My_DialogActivity extends AppCompatActivity {

    @BindView(R.id.show_dialog)
    Button mShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__dialog);
        ButterKnife.bind(this);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogEditSureCancel dialogEditSureCancel = new DialogEditSureCancel(My_DialogActivity.this);
                //确认的点击事件
                dialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = dialogEditSureCancel.getEditText().getText().toString().trim();
                        ToastUtil.showToastDefault(My_DialogActivity.this, "确认的点击事件" + content);
                        dialogEditSureCancel.cancel();
                    }
                });
                //取消的点击事件
                dialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToastDefault(My_DialogActivity.this, "取消的点击事件");
                        dialogEditSureCancel.cancel();
                    }
                });
                dialogEditSureCancel.show();
            }
        });
    }
}
