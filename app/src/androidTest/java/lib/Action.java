package lib;

import android.os.SystemClock;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import java.util.List;

import framework.ParameterException;

/**
 * Created by Administrator on 2018/7/21.
 */

public class Action{

    public static UiDevice mDevice = ComUtil.getInstance().getDevice();


    //该函数加入循环，来控件是否存在判断，增强测试脚本稳定性
    public static List<UiObject2> WaitForObjsExists(BySelector bySelector) throws InterruptedException, ParameterException {
        List<UiObject2> obj;
        long endTimeMillis;
        long offsetTimeMillis;
        long startTimeMillis;
        startTimeMillis = SystemClock.uptimeMillis();
        while (true) {
            obj = mDevice.findObjects(bySelector);
            endTimeMillis = SystemClock.uptimeMillis();
            offsetTimeMillis = endTimeMillis-startTimeMillis;
            if (obj != null) {
                break;
            }
            if(offsetTimeMillis >10000){
                throw new ParameterException("控件不存在");
            }
        }
        return  obj;
    }

    public static UiObject2 WaitForExistsForever(BySelector bySelector) throws InterruptedException, ParameterException {
        UiObject2 obj;
        while (true) {
            obj = mDevice.findObject(bySelector);
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }


    //烟机控制页面计时页面滑动控件操作封装函数
    public static void HourMinSetting(Integer hour, Integer min) throws InterruptedException, UiObjectNotFoundException, ParameterException {
        Integer offset = 0;
        UiScrollable uisHour = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/fan8700_remindsoupsetting_wheel_hour"));
        uisHour.click();
        /*
        UiScrollable uisMin = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/fan8700_remindsoupsetting_wheel_min"));
        UiScrollable uisHour = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/fan8700_remindsoupsetting_wheel_hour"));
        uisHour.click();
        //设置垂直滚动
        uisHour.setAsVerticalList();
        uisHour.scrollBackward(1);
        */
        if(hour > 3 || hour < 0){
            throw new ParameterException("小时参数输入范围超限！");
        }
        for(int i=0;i<hour;i++){
            mDevice.drag(200,1500,200,1450,5);
        }

        if(min > 59 || hour < 0){
            throw new ParameterException("分钟参数输入范围超限！");
        }
        offset = hour == 0 ? Math.abs(min-1):Math.abs(min);

        for(int i=0;i<offset;i++){
            mDevice.drag(800,1500,800,1450,5);
            }
    }


    //烟机控制页面厨房净化滑动控件操作封装函数
    public static void KitchenCleanSetting(String string) throws InterruptedException, UiObjectNotFoundException, ParameterException {
        Integer offset = 0;
        UiScrollable uis = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/wheel_view"));
        uis.click();
        Log.i("TAG"," string:"+string);
        switch (string){
            case "温馨小厨房":  offset=0;break;
            case "豪气大厨房":  offset=1;break;
            case "开放式厨房":  offset=2;break;
            default: throw new ParameterException("请输入正确参数");
        }
        for(int i = 0; i<offset; i++){
            mDevice.drag(500,1500,500,1450,5);
        }


    }

    //烟机控制页面定时通风星期滑动控件操作封装函数
    public static void WeekSetting(String string) throws InterruptedException, UiObjectNotFoundException, ParameterException {
        Integer offset = 0;
        UiScrollable uis = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/wheel_view"));
        uis.click();
        Log.i("TAG"," string:"+string);
        switch (string){
            case "周一":  offset=0;break;
            case "周二":  offset=1;break;
            case "周三":  offset=2;break;
            case "周四":  offset=3;break;
            case "周五":  offset=4;break;
            case "周六":  offset=5;break;
            case "周日":  offset=6;break;
            default: throw new ParameterException("请输入正确参数");
        }
        for(int i = 0; i<offset; i++){
            mDevice.drag(550,1489,550,1389,20);
            ComUtil.time(200);
        }

    }

    //烟机控制页面定时通风时间滑动控件操作封装函数
    public static void VentilateHourMinSetting(Integer hour, Integer min) throws InterruptedException, UiObjectNotFoundException, ParameterException {
        Integer offset = 0;
        if (hour < 12){
            for(int i=0;i<Math.abs(12-hour);i++){
                mDevice.drag(200,1388,200,1488,20);
                ComUtil.time(200);
            }
        }else{
            for(int i=0;i<Math.abs(12-hour);i++){
                mDevice.drag(200,1488,200,1388,20);
                ComUtil.time(200);
            }
        }
        if (min < 30){
            for(int i=0;i<Math.abs(30-min);i++){
                mDevice.drag(800,1388,800,1488,20);
                ComUtil.time(200);
            }
        }else{
            for(int i=0;i<Math.abs(30-min);i++){
                mDevice.drag(800,1488,800,1388,20);
                ComUtil.time(200);
            }
        }

    }
}
