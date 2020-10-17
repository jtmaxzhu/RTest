package base.rokiapp;


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
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;


import org.junit.AfterClass;
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

import framework.ParameterException;
import framework.core.RetryRule;
import lib.perftool.CmdTools;
import lib.ComUtil;
import lib.perftool.PerfUtil;
import lib.perftool.SPService;
import page.rokiapp.Page_MainMe;

import static config.Config.TAG_PACKAGE;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.perftool.SPService.PERMISSION_GRANT_ADB;

/**
 * Created by Administrator on 2018/7/13.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@RequiresDevice
public class BaseTest{

    public static ComUtil comUtil;

    public static UiDevice mDevice;

    public static String toastMessage = "";
    public static long toastOccurTime= 0;

    @Rule
    public RetryRule retry = new RetryRule(1);



    public static ScheduledExecutorService exec = null;//一池1线程
    public static volatile boolean flag=true;


    public static Map<String, List<String>> cmap  = new LinkedHashMap<>();





    //注册一个监听器抓取toast事件
    public static void initToastListener() {
        Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();
        mInstrumentation.getUiAutomation().setOnAccessibilityEventListener(new UiAutomation.OnAccessibilityEventListener() {
            public void onAccessibilityEvent(AccessibilityEvent event) {
                Log.i("event",event.getEventType()+"");
                //判断是否是通知事件
                if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
                    return;
                }
                //获取消息来源
                String sourcePackageName = (String) event.getPackageName();
                Log.i("demo sourcePackageName:",sourcePackageName);
                //获取事件具体信息
                Parcelable parcelable = event.getParcelableData();
                //如果不是下拉通知栏消息，则为其它通知信息，包括Toast
                if (!(parcelable instanceof Notification)) {
                    Log.i("demo event.getText().toString():",event.getText().toString());
                    toastMessage = (String) event.getText().get(0);
                    toastOccurTime = event.getEventTime();
                    Log.i("demo", "Latest Toast Message: " + toastMessage + " [Time: " +  toastOccurTime + ", Source: " + sourcePackageName + "]");
                }else {
                    Log.i("demo",event.getParcelableData().toString());
//                    toastMessage = (String) event.getText().get(0);
//                    Log.i("demo", "Latest Toast Message: " + toastMessage);
                    Bundle x = ((Notification) parcelable).extras;
                    x.getString("android.text");
                    String regNum =  x.getString("android.text").substring( x.getString("android.text").indexOf("：")+1,  x.getString("android.text").indexOf(","));
                    Log.i("demo",regNum);
                }
            }
        });
    }

    @BeforeClass
    public static void setup() throws Exception {
        comUtil = ComUtil.getInstance();
        mDevice = ComUtil.getDevice();
        ComUtil.grantPermission(mDevice);
        mDevice = ComUtil.getDevice();
        mDevice.isScreenOn();
        if(!mDevice.isScreenOn()){  //唤醒屏幕
            mDevice.wakeUp();
            mDevice.swipe(0,500,1000,500,5);
        }
    }

    @AfterClass
    public static void teardown() throws IOException {
        //closeAPP(mDevice, TAG_PACKAGE);
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


    public void exitLogin() throws InterruptedException, ParameterException {
        WaitForExists(By.res(TAG_PACKAGE, "imgFigure")).click();
        ComUtil.time(delayTimeMs);
        WaitForExists(By.text("退出登录")).click();
        ComUtil.time(delayTimeMs);
        WaitForExists(By.text("确定")).click();
    }

    public void Login() throws InterruptedException, ParameterException {
        WaitForExists(By.text("请登录")).click();
        ComUtil.time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "edtAccount")).clear();
        ComUtil.time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "edtAccount")).setText("13655197396");
        ComUtil.time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "edtPwd")).setText("123456");
        ComUtil.time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "txtLogin")).click();
        ComUtil.time(delayTimeMs);
    }

    /**
     * 判断登录状态，已登录返回True,未登录返回false
     * @throws Exception
     */
    public Boolean isLogin() throws Exception {
        if (new Page_MainMe().getFigureText().equals("请登录")) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 初始化登录状态，如果登录则退出登录
     * @throws Exception
     */
    public void loginInitQuit() throws Exception {
        if(isLogin()){
            exitLogin();
        }
    }

    /**
     * 如果未登录则进行登录
     * @throws Exception
     */
    public void loginInit() throws Exception {
        if(!isLogin()){
            Login();
        }
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
