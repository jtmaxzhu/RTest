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
import lib.MethodDescription;
import page.rokipad.Page_leftbar;
import page.rokipad.Page_rightbar;
import page.rokipad.Page_wifi;

import static config.Config.TAG_PACKAGEPAD;


/**
 * Created by Administrator on 2018/9/2 0002.
 * wifi相关的测试用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_wifi extends BaseTest {


    private static final String wifiOpen =  "打开网络";
    private static final String wifiClose = "关闭网络";
    private static final String wifiCheck = "请检查网络";

    @Before
    @MethodDescription("环境初始化")
    public void init() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"wifi_control")),1000)){
            //点击侧边栏登录按钮
            Page_leftbar leftbar = new Page_leftbar();
            leftbar.clickLogin();

            //点击右侧边栏智能设定
            Page_rightbar rightbar = new Page_rightbar();
            rightbar.clickWifi();
        }
    }


    @Test
    @MethodDescription("打开关闭WIFI开关")
    public void Test01_SetWifi() throws Exception {
        Page_wifi wifi = new Page_wifi();
        if (wifi.checkWiFiSwitch()){
            wifi.clickWifi();
            Assert.assertTrue("未发现"+ wifiClose +"提示信息!",  processToast(wifiClose));
            Page_rightbar rightbar = new Page_rightbar();
            rightbar.clickPersonCenter();
            Assert.assertTrue("未发现"+ wifiCheck +"提示信息!",  processToast(wifiCheck));
            rightbar.clickWifi();
            wifi.clickWifi();
            Assert.assertTrue("未发现"+ wifiOpen +"提示信息!",  processToast(wifiOpen));
        }else {
            Page_rightbar rightbar = new Page_rightbar();
            rightbar.clickPersonCenter();
            Assert.assertTrue("未发现"+ wifiCheck +"提示信息!",  processToast(wifiCheck));
            rightbar.clickWifi();
            wifi.clickWifi();
            Assert.assertTrue("未发现"+ wifiOpen +"提示信息!",  processToast(wifiOpen));
        }



    }

    @AfterClass
    public static void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }


}
