package base.rokipad;


import android.app.Instrumentation;
import android.app.Notification;
import android.app.UiAutomation;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.RequiresDevice;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import framework.core.RetryRule;
import lib.ComUtil;
import lib.LogUtil;
import lib.perftool.CmdTools;
import lib.perftool.PerfUtil;
import lib.perftool.SPService;
import page.rokipad.Page_login;
import page.rokipad.Page_wifi;
import robam.rtest.MyApplication;

import static config.Config.TAG_ACTIVITYPAD;
import static config.Config.TAG_PACKAGEPAD;
import static lib.perftool.SPService.PERMISSION_GRANT_ADB;

/**
 * Created by Administrator on 2018/7/13.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@RequiresDevice
public class BaseTest {

    public static ComUtil comUtil;

    public static UiDevice mDevice;

    private static String toastMessage = "";
    private static long toastOccurTime= 0;

    @Rule
    public RetryRule retry = new RetryRule(3);


    public static String TAG = "Rlog";

    private static ScheduledExecutorService exec = null;//一池1线程
    private static volatile boolean flag=true;


    private static Map<String, List<String>> cmap  = new LinkedHashMap<>();





    //注册一个监听器抓取toast事件
    public static void initToastListener() {
        Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();
        mInstrumentation.getUiAutomation().setOnAccessibilityEventListener(new UiAutomation.OnAccessibilityEventListener() {
            public void onAccessibilityEvent(AccessibilityEvent event) {
                LogUtil.d(TAG, "事件类型"+event.getEventType()+"");
                //判断是否是通知事件
                if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
                    return;
                }
                //获取消息来源
                String sourcePackageName = (String) event.getPackageName();
                //LogUtil.d(TAG,"demo sourcePackageName:"+sourcePackageName);
                //获取事件具体信息
                Parcelable parcelable = event.getParcelableData();
                //如果不是下拉通知栏消息，则为其它通知信息，包括Toast
                if (!(parcelable instanceof Notification)) {
                    LogUtil.d(TAG,"demo event.getText().toString():"+ event.getText().toString());
                    toastMessage = (String) event.getText().get(0);
                    toastOccurTime = event.getEventTime();
                    LogUtil.d(TAG, "Latest Toast Message: " + toastMessage + " [Time: " +  toastOccurTime + ", Source: " + sourcePackageName + "]");
                }else {
                    LogUtil.d(TAG, event.getParcelableData().toString());
                    Bundle x = ((Notification) parcelable).extras;
                    x.getString("android.text");
                    String regNum =  x.getString("android.text").substring( x.getString("android.text").indexOf("：")+1,  x.getString("android.text").indexOf(","));
                    LogUtil.d(TAG,regNum);
                }
            }
        });
    }

    @BeforeClass
    public static void BaseSetup() throws Exception {
        comUtil = ComUtil.getInstance();
        mDevice = ComUtil.getDevice();
        ComUtil.grantPermission(mDevice);
        mDevice.isScreenOn();

        //注册监听器
        initToastListener();
        SPService.init(MyApplication.getContext());
        boolean b = processAdbPermission();
        if(!b){
            LogUtil.d(TAG,"网络调试连接失败，请检查是否开启5555端口");
        }else {
            LogUtil.d(TAG,"网络调试连接成功");
        }
    }

    @AfterClass
    public static void BaseTearDown(){
    }


    @Before
    public void BaseInit() throws Exception {
        comUtil.startAPP(mDevice, TAG_ACTIVITYPAD);

        //跳过APP启动时候的WiFi设置页面
        Page_wifi wifi = new Page_wifi();
        wifi.clickSkip();
        TimeUnit.SECONDS.sleep(3);
        wifi.clickSkip();
        //判断是否出现登录页面
        if (mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "title")), 3000)){
            Page_login login = new Page_login();
            login.clickSkip();
        }
    }

    /**
     * 判断Toast弹窗是否被成功抓取，成功返回True,未成功返回false
     * @param  toast   toast显示的文本内容
     * @throws Exception
     */
    public static Boolean processToast(String toast) throws Exception {
        ComUtil.time(2000);
        final long startTimeMillis = SystemClock.uptimeMillis();
        boolean isSuccessfulCatchToast = false;
        while (true) {
            long currentTimeMillis = SystemClock.uptimeMillis();
            long elapsedTimeMillis = currentTimeMillis - startTimeMillis;
            if (elapsedTimeMillis > 5000) {
                Log.i("AAA", "超过5s未能捕获到预期Toast!");
                isSuccessfulCatchToast = false;
                break;
            } else {
                isSuccessfulCatchToast = toast.equals(toastMessage);
                break;
            }
        }
        return isSuccessfulCatchToast;
    }


    /**
     * 处理ADB权限
     * @return
     */
    public static boolean processAdbPermission() {
        boolean status;
        if (SPService.getBoolean(PERMISSION_GRANT_ADB, false)){
            status = CmdTools.generateConnection();
        }else {
            status = CmdTools.isInitialized();
        }
        if (!status){
            SPService.putBoolean(PERMISSION_GRANT_ADB, true);
            boolean result;
            result = CmdTools.generateConnection();
            return result;

        }
        return true;
    }

    /**
     * 性能数据收集线程
     */
    public static Runnable task = new Runnable() {
        @Override
        public void run() {
            exec.schedule(this, 1000, TimeUnit.MILLISECONDS);
            if (flag){
                Log.i("ROKI",Thread.currentThread().getName()+"线程运行");
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = dateformat.format(System.currentTimeMillis());



                List<String> list = new ArrayList<>();
                String style = "0.00";
                DecimalFormat df = new DecimalFormat();
                df.applyPattern(style);

                float cpuInfo = PerfUtil.getCpuInfo();
                Float memInfo = PerfUtil.getMemInfo();

                if ((cpuInfo >= 0.00)){
                    String cpuUsage = df.format(cpuInfo);
                    list.add(cpuUsage);
                    Log.i("ROKI","应用CPU："+ df.format(cpuInfo));
                }
                String mem = df.format(memInfo);
                list.add(mem);

                cmap.put(dateStr, list);
            }

        }
    };


}
