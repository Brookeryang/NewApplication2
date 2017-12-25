package com.example.yyh.newapplication.scrollview_action_bar;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/12/18 16:10
 *     version: 1.0
 *     desc   : 描述Android沉浸式状态栏 工具类
 *            博客地址：http://blog.csdn.net/androidstarjack
 * </pre>
 */
public class StatusBarUtils {
    /**
     * 设置状态的颜色
     */
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置透明的状态栏
            ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View parentView = contentFrameLayout.getChildAt(0);
            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
                parentView.setFitsSystemWindows(true);
                parentView.setBackgroundColor(color);
            }
        }

    }
}
