package page.rokipad;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.Until;

import java.io.IOException;

import base.BasePage;
import lib.LogUtil;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.ClickElement;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.mDevice;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_smart extends BasePage {

    private final String MIN_1 = "1";

    private UiScrollable uiScrollable ;

    @VariableDescription("恢复出厂设置")
    private UiObject2 dataReset;

    @VariableDescription("爆炒时间")
    private UiObject2 variableTime;

    @VariableDescription("换气天数")
    private UiObject2 aerationDay;

    @VariableDescription("星期")
    private UiObject2 weekDay;

    @VariableDescription("北京时间")
    private UiObject2 timeDay;

    @VariableDescription("灶具延时时间")
    private UiObject2 delayedMin;

    @VariableDescription("固定换气开关")
    private UiObject2 breath_time_switch;

    @VariableDescription("自动换气开关")
    private UiObject2 aeration_switch;

    @VariableDescription("3D手势开关")
    private UiObject2 gesture_switch;

    @VariableDescription("爆炒时间开关")
    private UiObject2 variable_time_switch;

    @VariableDescription("悬浮窗恢复出厂按钮")
    private UiObject2 reset;

    @VariableDescription("烟灶联动开关")
    private UiObject2 stove_linked_switch;

    @VariableDescription("自动爆炒开关")
    private UiObject2 stove_power_switch;

    @VariableDescription("灶具延时开关")
    private UiObject2 delayed_min_switch;

    @VariableDescription("油网清洗提示开关")
    private UiObject2 iv_fan_oil_switch;

    public Page_smart() { }


    //获取爆炒时间
    public String getVariablTime(){
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_variable_time")),1000)){
            mDevice.drag(300,200, 300,400,10);
        }
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_variable_time")).getText();
    }

    //获取星期
    public String getWeekDay(){
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_week_day")),1000)){
            mDevice.drag(300,400, 300,200,10);
        }
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_week_day")).getText();
    }

    //获取北京时间
    public String getTimeDay(){
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_time_day")).getText();
    }

    //获取通风天数
    public String getAerationDay(){
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_aeration_day")).getText();
    }

    //获取延时时间
    public String getDelayedMIn(){
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_delayed_min")).getText();
    }




    /**
     * 点击恢复出厂设置
     * @throws Exception
     */
    public void clickDataReset() throws Exception {
        dataReset =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_fan_factory_data_reset"));
        dataReset.click();
        time(delayTimeMs);
    }

    /**
     * 点击设置变频爆炒时间
     * @throws Exception
     */
    public void clickVariablTime() throws Exception {
        variableTime =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_variable_time"));
        variableTime.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("variableTime")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击设星期几
     * @throws Exception
     */
    public void clickWeekDay() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_week_day")),1000)){
            mDevice.drag(300,400, 300,200,10);
        }
        weekDay =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_week_day"));
        weekDay.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("weekDay")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击设通风时间
     * @throws Exception
     */
    public void clickTimeDay() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_time_day")),1000)){
            mDevice.drag(300,400, 300,200,10);
        }
        timeDay =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_time_day"));
        timeDay.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("timeDay")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }



    /**
     * 点击设换气天数
     * @throws Exception
     */
    public void clickAerationDay() throws Exception {
        aerationDay =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_aeration_day"));
        aerationDay.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("aerationDay")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击设置延时时间
     * @throws Exception
     */
    public void clickDelayedMin() throws Exception {
        delayedMin =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_delayed_min"));
        delayedMin.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("delayedMin")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }


    /**
     * 点击自动换气开关
     * @throws Exception
     */
    public void clickAerationSwitch() throws Exception {
        aeration_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_aeration_switch"));
        aeration_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("aeration_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击固定通风开关
     * @throws Exception
     */
    public void clickBreathTimeSwitch() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "iv_fan_breath_time_switch")),3000)){
            mDevice.drag(300,400, 300,200,10);
        }
        breath_time_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_breath_time_switch"));
        breath_time_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("breath_time_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击爆炒时间开关
     * @throws Exception
     */
    public void clickVariableSwitch() throws Exception {
        variable_time_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_variable_time_switch"));
        variable_time_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("variable_time_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击油网清洗提示开关
     * @throws Exception
     */
    public void clickOilSwitch() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "iv_fan_oil_switch")),3000)){
            mDevice.drag(300,400, 300,200,10);
        }
        iv_fan_oil_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_oil_switch"));
        iv_fan_oil_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("iv_fan_oil_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }


    /**
     * 点击对话框中的恢复出厂按钮
     * @throws Exception
     */
    public void clickReset() throws Exception {
        reset =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_cancel"));
        reset.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("reset")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }


    /**
     * 点击3D手势开关
     * @throws Exception
     */
    public void clickGestureSwitch() throws Exception {
        //正常控件获取不知为何不起作用，这里采用坐标点击
        mDevice.drag(300,200, 300,400,10);
        time(delayTimeMs);
        ClickElement(507,189,562,219);
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("gesture_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击烟灶联动开关
     * @throws Exception
     */
    public void clickLinkedSwitch() throws Exception {
        stove_linked_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_stove_linked_switch"));
        stove_linked_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("stove_linked_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击自动爆炒开关
     * @throws Exception
     */
    public void clickPowerSwitch() throws Exception {
        stove_power_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_stove_power_switch"));
        stove_power_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("stove_power_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击灶具延时开关
     * @throws Exception
     */
    public void clickDelayedSwitch() throws Exception {
        delayed_min_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_delayed_min_switch"));
        delayed_min_switch.click();
        LogUtil.d(TAG,"点击"+Page_smart.class.getDeclaredField("delayed_min_switch")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }


    /**
     * 验证AI按钮开关状态
     * @throws Exception
     */
    public Boolean checkGestureSwitch() throws Exception {
        //正常控件获取不知为何不起作用，这里采用坐标点击
        mDevice.drag(300,200, 300,400,10);
        time(delayTimeMs);
        if (gesture_switch == null){
            gesture_switch = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_3d_gesture_switch"));
        }
        return isOpen(gesture_switch);
    }

    /**
     * 验证变频爆炒按钮开关状态
     * @throws Exception
     */
    public Boolean checkVariablSwitch() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "iv_variable_time_switch")),3000)){
            mDevice.drag(300,200, 300,400,10);
        }
        variable_time_switch = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_variable_time_switch"));
        return isOpen(variable_time_switch);
    }


    /**
     * 验证固定通风开关状态
     * @throws Exception
     */
    public Boolean checkBreathTimeSwitch() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "iv_fan_breath_time_switch")),3000)){
            mDevice.drag(300,400, 300,200,10);
        }
        breath_time_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_breath_time_switch"));
        return isOpen(breath_time_switch);
    }

    /**
     * 验证自动换气开关状态
     * @throws Exception
     */
    public Boolean checkAerationSwitch() throws Exception {
        aeration_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_aeration_switch"));
        return isOpen(aeration_switch);
    }

    /**
     * 验证烟灶联动开关状态
     * @throws Exception
     */
    public Boolean checkLinkedSwitch() throws Exception {
        stove_linked_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_stove_linked_switch"));
        return isOpen(stove_linked_switch);
    }

    /**
     * 验证油网清洗开关状态
     * @throws Exception
     */
    public Boolean checkOilSwitch() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "iv_fan_oil_switch")),3000)){
            mDevice.drag(300,400, 300,200,10);
        }
        iv_fan_oil_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_oil_switch"));
        return isOpen(iv_fan_oil_switch);
    }


    /**
     *验证自动爆炒开关状态
     * @throws Exception
     */
    public Boolean checkPowerSwitch() throws Exception {
        stove_power_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_fan_stove_power_switch"));
        return isOpen(stove_power_switch);
    }

    /**
     * 验证灶具延时开关状态
     * @throws Exception
     */
    public Boolean checkDelayedSwitch() throws Exception {
        mDevice.drag(700,400, 700,200,10);
        delayed_min_switch =  WaitForExists(By.res(TAG_PACKAGEPAD, "iv_delayed_min_switch"));
        return isOpen(delayed_min_switch);
    }


    public void SetWeekDay(String day) throws InterruptedException {
        Rect rect = WaitForExists(By.res(TAG_PACKAGEPAD, "wheel_view")).getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;

        //控件的上半部分坐标
        int halftop = y1+(y2-y1)/6;
        int halfbottom = y2-(y2-y1)/6;
        int halfx = (x2+x1)/2;

        int offset = 0;
        switch (day){
            case "周一":  offset=0;break;
            case "周二":  offset=1;break;
            case "周三":  offset=2;break;
            case "周四":  offset=3;break;
            case "周五":  offset=4;break;
            case "周六":  offset=5;break;
            case "周日":  offset=6;break;
        }

        for(int i = 0; i<offset; i++){
            mDevice.click(halfx, halfbottom);
            time(delayTimeMs);
        }

        WaitForExists(By.res(TAG_PACKAGEPAD, "tv_affirm")).click();
        time(delayTimeMs);
    }

    public void SetTimeDay(int hour, int min) throws InterruptedException {
        Rect rect = WaitForExists(By.res(TAG_PACKAGEPAD, "wheel_front")).getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;

        Rect rect1 = WaitForExists(By.res(TAG_PACKAGEPAD, "wheel_later")).getVisibleBounds();
        int x3 = rect1.left;
        int x4 = rect1.right;
        int y3 = rect1.top;
        int y4 = rect1.bottom;

        //小时控件坐标
        int halftopHour = y1+(y2-y1)/6;
        int halfbottomHour = y2-(y2-y1)/6;
        int halfxHour = (x2+x1)/2;

        //分钟控件坐标
        int halftopMin = y3+(y4-y3)/6;
        int halfbottomMin = y4-(y4-y3)/6;
        int halfxMin = (x4+x3)/2;


        int offsetHour = Math.abs( hour-12);
        int offsetMin = Math.abs( min-30);

        if (hour >= 12){
            for(int i = 0; i<offsetHour; i++){
                mDevice.click(halfxHour, halfbottomHour);
                time(delayTimeMs);
            }
        }else {
            for(int i = 0; i<offsetHour; i++){
                mDevice.click(halfxHour, halftopHour);
                time(delayTimeMs);
            }
        }

        if (min >= 30){
            for(int i = 0; i<offsetMin; i++){
                mDevice.click(halfxMin, halfbottomMin);
                time(delayTimeMs);
            }
        }else {
            for(int i = 0; i<offsetMin; i++){
                mDevice.click(halfxMin, halftopMin);
                LogUtil.d(TAG,"I:"+i);
                time(delayTimeMs);
            }
        }

        WaitForExists(By.res(TAG_PACKAGEPAD, "tv_affirm")).click();
        time(delayTimeMs);
    }


    /**
     * 操作换气和爆炒空间选择参数，这两个控件类似，用同一个函数操作
     * @param var
     * @throws InterruptedException
     */
    public void SetVar(int var) throws InterruptedException {
        Rect rect = WaitForExists(By.res(TAG_PACKAGEPAD, "wheel_view")).getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;

        //控件的上半部分坐标
        int halftop = y1+(y2-y1)/6;
        int halfbottom = y2-(y2-y1)/6;
        int halfx = (x2+x1)/2;



        int offset = Math.abs( var-3);
        if (var >= 3){
            for(int i = 0; i<offset; i++){
                mDevice.click(halfx, halfbottom);
                time(delayTimeMs);
            }
        }else {
            for(int i = 0; i<offset; i++){
                mDevice.click(halfx, halftop);
                time(delayTimeMs);
            }
        }
        WaitForExists(By.res(TAG_PACKAGEPAD, "tv_affirm")).click();
        time(delayTimeMs);
    }

    /**
     * 灶具延时时间操作控件
     * @param min
     * @throws InterruptedException
     */
    public void SetDelayedMin(int min) throws Exception {
        Rect rect = WaitForExists(By.res(TAG_PACKAGEPAD, "wheel_view")).getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;

        //控件的上下半部分坐标y
        int halftop = y1+(y2-y1)/6;
        int halfbottom = y2-(y2-y1)/6;
        //控件的坐标x取中间值
        int halfx = (x2+x1)/2;


        int offset = Math.abs(min-1);

        for(int i = 0; i<offset; i++){
            mDevice.click(halfx, halfbottom);
            time(delayTimeMs);
        }

        WaitForExists(By.res(TAG_PACKAGEPAD, "tv_affirm")).click();
        time(delayTimeMs);
    }



    /**
     * 判断控件状态，通过单点像素的颜色判定，只适用于此页面控件类型, 白色为关，非白色为开
     * @param obj
     * @return true:打开 false：关闭
     */
    private static Boolean isOpen(UiObject2 obj) throws IOException {
        Rect rect = obj.getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;
        int y = (y1+y2)/2;
        String rgb = getRGB((x1 + (x1 + x2) / 2) / 2, y);
        return !rgb.equals("ffffffff");
    }


}

