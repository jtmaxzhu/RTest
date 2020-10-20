package testcase.pad.uitest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.SearchCondition;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.Until;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.rokipad.BaseTest;
import lib.LogUtil;
import lib.MethodDescription;
import page.rokipad.Page_leftbar;
import page.rokipad.Page_rightbar;
import page.rokipad.Page_smart;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.mDevice;
import static lib.ComUtil.time;

/**
 * 适配5916s智能设定页面
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_smart_5916s extends BaseTest {

    private static final String hint = "您没开启此功能不可设置";

    @Before
    @MethodDescription("环境初始化")
    public void init() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"tv_fan_factory_data_reset")),1000)){
            //点击侧边栏登录按钮
            Page_leftbar leftbar = new Page_leftbar();
            leftbar.clickLogin();

            //点击右侧边栏智能设定
            Page_rightbar rightbar = new Page_rightbar();
            rightbar.clickSmartSet();
        }
    }

    @Test
    @MethodDescription("设置爆炒时间")
    public void Test02_SetVariablTime() throws Exception {
        Page_smart smart = new Page_smart();
        //点击爆炒时间
        smart.clickVariablTime();
        //判断开关是否打开，如果没打开就打开
        if (processToast(hint)){
            smart.clickVariableSwitch();
            smart.clickVariablTime();
        }
        //设置参数1分钟
        smart.SetVar(1);
        Assert.assertEquals("1", smart.getVariablTime());
        //再次点击爆炒时间
        smart.clickVariablTime();
        //设置参数9分钟
        smart.SetVar(9);
        Assert.assertEquals("9", smart.getVariablTime());
        //再次点击爆炒时间
        smart.clickVariablTime();
        //设置参数3分钟
        smart.SetVar(3);
        Assert.assertEquals("3", smart.getVariablTime());
    }

    @Test
    @MethodDescription("设置每周固定通风时间")
    public void Test04_SetBreathDay() throws Exception {
        Page_smart smart = new Page_smart();
        smart.clickWeekDay();
        //判断开关是否打开，如果没打开就打开
        if (processToast(hint)){
            smart.clickBreathTimeSwitch();
            smart.clickWeekDay();
        }
        smart.SetWeekDay("周六");
        smart.clickTimeDay();
        smart.SetTimeDay(16,52);
        Assert.assertEquals("周六", smart.getWeekDay());
        Assert.assertEquals("16:52", smart.getTimeDay());

        smart.clickWeekDay();
        smart.SetWeekDay("周三");
        smart.clickTimeDay();
        smart.SetTimeDay(12,30);
    }

    @Test
    @MethodDescription("设置换气天数")
    public void Test03_SetAerationDay() throws Exception {
        Page_smart smart = new Page_smart();
        smart.clickAerationDay();
        //判断开关是否打开，如果没打开就打开
        if (processToast(hint)){
            smart.clickAerationSwitch();
            smart.clickAerationDay();
        }
        smart.SetVar(9);
        Assert.assertEquals("9", smart.getAerationDay());
        smart.clickAerationDay();
        smart.SetVar(1);
        Assert.assertEquals("1", smart.getAerationDay());
        smart.clickAerationDay();
        smart.SetVar(3);
        Assert.assertEquals("3", smart.getAerationDay());
    }


    /**
     * 1、点击主页
     * 2、判断主页面是不是存在“智能设定”字样，如果有说明3D手势（AI隔空智控）未开启，直接点击进入设置页面进行开关操作
     * 3、如果没有，点击智能设定选项卡进入设置页面进行开关操作
     * @throws Exception
     */
    @Test
    @MethodDescription("设置3D手势")
    public void Test01_Set3D() throws Exception {
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
        //如果主页有智能设定字样，说明智能设定没有开启
        if (mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_smart_set")),1000)){
            LogUtil.d(TAG,"3D手势未开启");
            //点击主页智能设定，跳转进入智能设定页面
            WaitForExists(By.res(TAG_PACKAGEPAD, "tv_smart_set")).click();
            Page_smart smart = new Page_smart();
            smart.clickGestureSwitch();
            leftbar.clickHome();
            time(delayTimeMs);
            Assert.assertTrue(!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD, "tv_smart_set")),1000));
        }else {
            LogUtil.d(TAG,"3D手势已开启");
            leftbar.clickSet();
            Page_rightbar rightbar = new Page_rightbar();
            rightbar.clickSmartSet();
            Page_smart smart = new Page_smart();
            smart.clickGestureSwitch();
            leftbar.clickHome();
        }
    }


    @Test
    @MethodDescription("恢复出厂设置")
    public void Test08_SetDataReset() throws Exception {
        Page_smart smart = new Page_smart();
        smart.clickDataReset();
        smart.clickReset();
        Assert.assertEquals(true, smart.checkGestureSwitch());
        Assert.assertEquals(false, smart.checkVariablSwitch());
        Assert.assertEquals(false, smart.checkBreathTimeSwitch());
        Assert.assertEquals(false, smart.checkAerationSwitch());
        Assert.assertEquals(true, smart.checkLinkedSwitch());
        Assert.assertEquals(true, smart.checkPowerSwitch());
        Assert.assertEquals(true, smart.checkDelayedSwitch());
        Assert.assertEquals("3", smart.getVariablTime());
        Assert.assertEquals("3", smart.getAerationDay());
        Assert.assertEquals("周一", smart.getWeekDay());
        Assert.assertEquals("12:30", smart.getTimeDay());
        Assert.assertEquals("1", smart.getDelayedMIn());
    }

    @Test
    @MethodDescription("设置烟灶联动、自动爆炒开关")
    public void Test06_SetStoveLinked() throws Exception {
        Page_smart smart = new Page_smart();
        if (smart.checkLinkedSwitch()){
            smart.clickLinkedSwitch();
            Assert.assertEquals(false, smart.checkLinkedSwitch());
            smart.clickLinkedSwitch();
            Assert.assertEquals(true, smart.checkLinkedSwitch());
        }else {
            smart.clickLinkedSwitch();
            Assert.assertEquals(true, smart.checkLinkedSwitch());
            smart.clickLinkedSwitch();
            Assert.assertEquals(false, smart.checkLinkedSwitch());
        }
        if (smart.checkPowerSwitch()){
            smart.clickPowerSwitch();
            Assert.assertEquals(false, smart.checkPowerSwitch());
            smart.clickPowerSwitch();
            Assert.assertEquals(true, smart.checkPowerSwitch());
        }else {
            smart.clickPowerSwitch();
            Assert.assertEquals(true, smart.checkPowerSwitch());
            smart.clickPowerSwitch();
            Assert.assertEquals(false, smart.checkPowerSwitch());
        }
    }


    @Test
    @MethodDescription("设置灶具延时时间")
    public void Test07_SetDelayedMin() throws Exception {
        Page_smart smart = new Page_smart();
        smart.clickDelayedMin();
        //判断开关是否打开，如果没打开就打开
        if (processToast(hint)){
            smart.clickDelayedSwitch();
            smart.clickDelayedMin();
        }
        smart.SetDelayedMin(1);
        Assert.assertEquals("1", smart.getDelayedMIn());
        smart.clickDelayedMin();
        smart.SetDelayedMin(3);
        Assert.assertEquals("3", smart.getDelayedMIn());
        smart.clickDelayedMin();
        smart.SetDelayedMin(5);
        Assert.assertEquals("5", smart.getDelayedMIn());

    }

    @Test
    @MethodDescription("设置油网清洗")
    public void Test05_SetOil() throws Exception {
        Page_smart smart = new Page_smart();
        if (smart.checkOilSwitch()){
            smart.clickOilSwitch();
            Assert.assertEquals(false, smart.checkOilSwitch());
            smart.clickOilSwitch();
            Assert.assertEquals(true, smart.checkOilSwitch());
        }else {
            smart.clickOilSwitch();
            Assert.assertEquals(true, smart.checkOilSwitch());
            smart.clickOilSwitch();
            Assert.assertEquals(false, smart.checkOilSwitch());
        }
    }

    @AfterClass
    public static void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }







}
