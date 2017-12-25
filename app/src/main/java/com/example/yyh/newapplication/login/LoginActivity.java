package com.example.yyh.newapplication.login;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.yyh.newapplication.BaseApplication;
import com.example.yyh.newapplication.R;
import com.vondear.rxtools.RxAnimationTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;

import butterknife.ButterKnife;

public class LoginActivity extends FragmentActivity implements View.OnClickListener {
    ImageView logo;
    LinearLayout content;
    ScrollView scrollView;
    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //状态栏透明化
        RxBarTool.setTransparentStatusBar(this);
        RxBarTool.StatusBarLightMode(this);
        ButterKnife.bind(this);
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        initView();
        initEvent();
    }

    private void initEvent() {
        /**
         * 禁止键盘弹起的时候可以滚动
         */
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        scrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
     /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom) > keyHeight) {
                    Log.e("weizhi", "up------->" + (oldBottom - bottom));
                    int dist = content.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        RxAnimationTool.zoomIn(logo, 0.6f, dist);
                    }
                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom) > keyHeight) {
                    Log.d("wenzhinhao", "down--------->" + (bottom - oldBottom));
                    if ((content.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", content.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        RxAnimationTool.zoomOut(logo, 0.6f);
                    }
                }
            }
        });
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        content = (LinearLayout) findViewById(R.id.content);
        logo = (ImageView) findViewById(R.id.logo);
        //获取屏幕的高度
        screenHeight = BaseApplication.heightPixels;
        //弹起高度为屏幕高度的1/3
        keyHeight = screenHeight / 3;

    }

    @Override
    public void onClick(View v) {

    }
}
