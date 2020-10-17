package robam.rtest;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;


/**
 * author : liuxiaohu
 * date   : 2019/8/3 15:54
 * desc   :
 * version: 1.0
 */
public class MyApplication extends Application {

    private final static String TAG = MyApplication.class.getSimpleName();
    private static volatile boolean appInt = false;

    public static final String SHOW_LOADING_DIALOG = "showLoadingDialog";
    public static final String DISMISS_LOADING_DIALOG = "dismissLoadingDialog";


    protected static MyApplication appInstance;

    /**
     * 是否是DEBUG
     */
    public static boolean DEBUG = false;


    /**
     * 获取实例
     * @return
     */
    public static MyApplication getInstance(){
        return appInstance;
    }


    /**
     * 获得Context
     * @return
     */
    public static Context getContext(){
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        //判断是否是调试模式
        ApplicationInfo info = getApplicationInfo();
        DEBUG = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        initialLogger();
    }

    protected void initialLogger() {
        SimpleFormatStrategy formatStrategy = new SimpleFormatStrategy();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

}
