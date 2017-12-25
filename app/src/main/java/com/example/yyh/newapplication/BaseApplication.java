package com.example.yyh.newapplication;

import android.content.Context;
import android.os.Environment;

import com.jaydenxiao.common.util.Constants;
import com.jaydenxiao.common.util.DisplayUtils;
import com.jaydenxiao.common.widget.Common;
import com.vondear.rxtools.RxTool;

import java.io.File;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/11/6 16:26
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class BaseApplication extends com.jaydenxiao.common.baseapp.BaseApplication {
    public static int heightPixels;
    public static int widthPixels;
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取屏幕高度
        heightPixels = DisplayUtils.getScreenHeight(this);
        // 获取屏幕宽度
        widthPixels = DisplayUtils.getScreenWidth(this);
        //创建项目文件夹
        CreateFile();
        Common.init(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        //初始化
        RxTool.init(this);
    }

    public static Context getApp() {
        return baseApplication;
    }

    private void CreateFile() {
        //SDCard路径
        final String SdcardPath =
                Environment.getExternalStorageDirectory().getAbsolutePath();
        //项目文件夹
        final String RootPath = SdcardPath + Constants.ROOT_PATH;
        //image文件夹
        final String ImagePath = SdcardPath + Constants.IMAGE_PATH;
        //download文件夹
        final String DownloadPath = SdcardPath + Constants.DOWNLOAD_PATH;

        File dir = new File(RootPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File image = new File(ImagePath);
        if (!image.exists()) {
            image.mkdirs();
        }
        File download = new File(DownloadPath);

        if (!download.exists()) {
            download.mkdirs();
        }
    }
}
