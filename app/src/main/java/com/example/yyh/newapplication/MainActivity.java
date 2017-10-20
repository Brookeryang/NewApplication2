package com.example.yyh.newapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zhl.cbdialog.CBDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Onnext(View v) {
        startActivity(new Intent(this, DetailsActivity.class));
    }

    public void showDialog(View view) {
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)//当触摸到空白时，设置是否取消对话框
                .showCancelButton(true)//set weather show cancel button, if true,the Dialog show two buttons
                .setTitle("温馨提示！")
                .setMessage("是否确认不使用仓储系统出库？")
                .setConfirmButtonText("确认")
                .setCancelButtonText("取消")
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)//弹出对话框的动画
                .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                    @Override
                    public void onDialogbtnClick(Context context, Dialog dialog, int i) {
                        switch (i) {
                            case BUTTON_CONFIRM://确认
                                Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                                break;

                            case BUTTON_CANCEL://取消
                                Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .create().show();
    }
}
