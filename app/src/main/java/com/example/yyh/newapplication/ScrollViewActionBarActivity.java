package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yyh.newapplication.scrollview_action_bar.ActionBarClickListener;
import com.example.yyh.newapplication.scrollview_action_bar.StatusBarUtils;
import com.example.yyh.newapplication.scrollview_action_bar.widget.TranslucentActionBar;
import com.example.yyh.newapplication.scrollview_action_bar.widget.TranslucentScrollView;

/**
 * <pre>
 *     author : 杨永红
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/12/18/15:37
 *     version: 1.0
 *     desc   : 沉浸式状态栏+ScrollView顶部伸缩+ActionBar渐变
 * </pre>
 */
public class ScrollViewActionBarActivity extends AppCompatActivity implements ActionBarClickListener, TranslucentScrollView.TranslucentChangedListener{
    private TranslucentScrollView translucentScrollView;
    private TranslucentActionBar actionBar;
    private View zoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_action_bar);
        //初始化
        initview();

    }

    private void initview() {
        //设置状态栏的颜色
        StatusBarUtils.setColor(this, R.color.red);

        actionBar = (TranslucentActionBar) findViewById(R.id.actionbar);
        //初始actionBar
        actionBar.setData("我的", 0, null, 0, null, null);
        //开启渐变
        actionBar.setNeedTranslucent();
        //获取状态栏的高度
        actionBar.setStatusBarHeight(getStatusBarHeight());
        //设置状态栏高度
        actionBar.setStatusBarHeight(getStatusBarHeight());

        translucentScrollView = (TranslucentScrollView) findViewById(R.id.pullzoom_scrollview);
        //设置透明度变化监听
        translucentScrollView.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        translucentScrollView.setTransView(actionBar);

        zoomView = findViewById(R.id.lay_header);
        //关联伸缩的视图
        translucentScrollView.setPullZoomView(zoomView);


    }


    public int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        actionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }
}
