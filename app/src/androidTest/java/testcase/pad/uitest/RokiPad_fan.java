package testcase.pad.uitest;

import android.support.test.uiautomator.By;
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
import page.rokipad.Page_center;
import page.rokipad.Page_fan;
import page.rokipad.Page_leftbar;
import page.rokipad.Page_login;
import page.rokipad.Page_rightbar;

import static config.Config.TAG_PACKAGEPAD;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;


/**
 * Created by Administrator on 2018/9/2 0002.
 * 登录相关的测用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_fan extends BaseTest {

    private static final String hint = "关闭";
    private static final String hint1 = "切换到弱档风量";
    private static final String hint2 = "切换至强档风量";
    private static final String hint3 = "切换至爆炒风量";

    @Before
    @MethodDescription("测试环境初始化")
    public void init() throws Exception {
        LogUtil.d(TAG,"----------------"+ RokiPad_fan.class.getDeclaredMethod("init")
                .getAnnotation(MethodDescription.class).value()+"----------------");
        mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"recipe")),1000);
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"recipe")),1000)){
            //点击侧边栏登录按钮
            Page_leftbar leftbar = new Page_leftbar();
            leftbar.clickHome();
        }

    }

    @Test
    @MethodDescription("风量照明测试")
    public void Test01_FanLight() throws Exception {
        Page_fan fan = new Page_fan();
        fan.resetFan();
        fan.clickLight();
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint2 +"提示信息!",  processToast(hint2));
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint3 +"提示信息!",  processToast(hint3));
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
    }

    @Test
    @MethodDescription("清洗锁定测试")
    public void Test02_CleanLock() throws Exception {
        Page_fan fan = new Page_fan();
        fan.resetFan();
        fan.clickCleanLock();
        fan.clickOK();
        fan.clickTvCancel();
    }


    @Test
    @MethodDescription("3D操控指示")
    public void Test03_AIWave() throws Exception {
        Page_fan fan = new Page_fan();
        fan.resetFan();
        fan.clickFanSmall();
        fan.click3DWave();
        fan.clickOK();
    }

    @Test
    @MethodDescription("极简模式风量照明测试")
    public void Test04_SimpleFanLight() throws Exception {
        Page_fan fan = new Page_fan();
        fan.resetFan();
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
        fan.clickLight();
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint2 +"提示信息!",  processToast(hint2));
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint3 +"提示信息!",  processToast(hint3));
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        fan.clickCancelSimple();
        leftbar.clickHome();
        fan.clickSimpleCleanLock();
        fan.clickOK();
        fan.clickTvCancel();
    }

    @Test
    @MethodDescription("底部选项卡遍历")
    public void Test05_Tab() throws Exception {
        Page_fan fan = new Page_fan();
        fan.clickTabs();
    }

    @AfterClass
    public static void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickSet();
        leftbar.clickHome();
    }








}
