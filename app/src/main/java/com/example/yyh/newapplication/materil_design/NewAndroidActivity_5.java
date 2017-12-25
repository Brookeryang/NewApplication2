package com.example.yyh.newapplication.materil_design;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yyh.newapplication.R;
import com.example.yyh.newapplication.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * <pre>
 *     author : 杨永红
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/12/21/13:45
 *     version: 1.0
 *     desc   : 描述:Snackbar
 * </pre>
 */
public class NewAndroidActivity_5 extends AppCompatActivity {

    @BindView(R.id.but_snackbar)
    Button mButSnackbar;
    @BindView(R.id.activity_main)
    ConstraintLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_android_5);
        ButterKnife.bind(this);
        mButSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });
    }

    private void showSnackbar() {
        Snackbar.make(mActivityMain, "标题", Snackbar.LENGTH_LONG)
                .setAction("点这里", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToastDefault(NewAndroidActivity_5.this, "你点击了");
                    }
                }).setDuration(Snackbar.LENGTH_SHORT).show();

    }
}
