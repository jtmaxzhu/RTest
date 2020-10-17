package testcase.pad.uitest;

import android.support.test.uiautomator.By;

import org.junit.After;
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
import page.rokipad.Page_leftbar;
import page.rokipad.Page_login;

import static config.Config.TAG_PACKAGEPAD;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;


/**
 * Created by Administrator on 2018/9/2 0002.
 * 登录相关的测用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_center extends BaseTest {

    private static final String hint = "请先登录";
    private static final String hint1 = "不能删除自己哦";

    @Before
    @MethodDescription("测试环境初始化")
    public void init() throws Exception {
        LogUtil.d(TAG,"----------------"+ RokiPad_center.class.getDeclaredMethod("init")
                .getAnnotation(MethodDescription.class).value()+"----------------");
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickLogin();
        Page_center center = new Page_center();
        if (center.isLogin()){
            //退出登录
            center.clickTvLogin();
            center.clickOK();
        }
    }


    @Test
    @MethodDescription("未登录设置分享控制权")
    public void Test02_NoLoginSetShareControl() throws Exception {
        Page_center center = new Page_center();
        center.clickShareControl();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        center.clickDeleteControl();
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"))==null){
            center.clickDeleteControl();
        }
        center.clickOK();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
    }

    @Test
    @MethodDescription("登录设置分享控制权")
    public void Test01_LoginSetShareControl() throws Exception {
        Page_center center = new Page_center();
        //点击中间登录按钮
        center.clickTvLogin();
        Page_login login = new Page_login();
        login.setAccount("13655197396");
        login.setPwd("123456");
        login.clickLogin();
        time(1000);
        center.clickShareControl();
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "img"))==null){
            center.clickShareControl();
        }
        time(2000);
        Assert.assertEquals(true,center.checkEWM());
        mDevice.swipe(1000,200,1000,400,15);
        time(1000);
        center.clickDeleteControl();
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"))==null){
            center.clickDeleteControl();
        }
        center.clickOK();
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        //退出登录
        center.clickTvLogin();
        center.clickOK();
    }


    @After
    public void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }







}
