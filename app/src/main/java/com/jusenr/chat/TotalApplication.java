package com.jusenr.chat;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.jusenr.chatlibrary.controller.ActivityManager;
import com.jusenr.chatlibrary.controller.BaseApplication;
import com.jusenr.chatlibrary.utils.Logger;
import com.jusenr.chatlibrary.utils.SDCardUtils;
import com.jusenr.chatlibrary.view.fresco.ImagePipelineFactory;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * TotalApplication
 * Created by Jusenr on 2017/3/25.
 */

public class TotalApplication extends BaseApplication {
    private static final String TAG = "ChatApp_Log";


    @Override
    public void onCreate() {
        super.onCreate();
        //创建储存文件夹
        File appDir = new File(getSdCardPath());
        if (!appDir.exists()) {
            boolean isSuccess = appDir.mkdirs();
            System.out.println("Create-" + getSdCardPath() + ":===================>" + isSuccess);
        }

        Fresco.initialize(getApplicationContext(),
                ImagePipelineFactory.imagePipelineConfig(getApplicationContext(),
                        new OkHttpClient()));

    }

    @Override
    protected void initEnvironment() {
        //初始化Service Api
        BaseApi.init(BaseApi.HOST_TEST);
    }

    @Override
    public String appDeviceId() {
        return null;
    }

    @Override
    protected boolean isDebug() {
        //根据需求更改
        return BaseApi.isInnerEnvironment();
    }

    @Override
    protected String getBuglyKey() {
        return "8cb683e78e";
    }

    @Override
    public String getPackageName() {
        return "com.jusenr.chat";
    }

    @Override
    protected String getLogTag() {
        return "chatapp_log";
    }

    @Override
    protected String getSdCardPath() {
        return SDCardUtils.getSDCardPath() + File.separator + getLogTag();
    }

    @Override
    protected String getNetworkCacheDirectoryPath() {
        return sdCardPath + File.separator + "http_cache";
    }

    @Override
    protected int getNetworkCacheSize() {
        return 20 * 1024 * 1024;
    }

    @Override
    protected int getNetworkCacheMaxAgeTime() {
        return 0;
    }

    /**
     * 捕捉到异常就退出App
     *
     * @param ex 异常信息
     */
    @Override
    protected void onCrash(Throwable ex) {
        Logger.e("APP崩溃了,错误信息是" + ex.getMessage());
        ex.printStackTrace();
        ActivityManager.getInstance().finishAllActivity();
        ActivityManager.getInstance().killProcess(this);
    }
}
