package testcase.pad.uitest;

import android.support.test.uiautomator.By;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;


/**
 * Created by Administrator on 2018/9/2 0002.
 * 登录相关的测用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_login extends BaseTest {

    private static final String hintUser = "请输入有效的手机号";
    private static final String hintPwd = "密码不能为空";
    private static final String hintLogin = "用户登录成功";
    private static final String hinterr = "账号或密码错误";





    @BeforeClass
    public static void initTest()  {

    }

    @Before
    @MethodDescription("测试环境初始化")
    public void init() throws Exception {
        LogUtil.d(TAG,"----------------"+RokiPad_login.class.getDeclaredMethod("init")
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

        //点击中间登录按钮
        center.clickTvLogin();
    }

    /**
     * 正常登录流程，用户名密码均正确
     * @throws
     */
    @Test
    @MethodDescription("正常登录")
    public void Test01_loginNormal() throws Exception {
        Page_login login = new Page_login();
        login.setAccount("13655197396");
        login.setPwd("123456");
        login.clickLogin();
        Assert.assertTrue("未发现"+ hintLogin +"提示信息!",  processToast(hintLogin));
        //退出登录
        Page_center center = new Page_center();
        center.clickTvLogin();
        center.clickOK();
    }

    /**
     * 用户名为空
     * @throws Exception
     */
    @Test
    @MethodDescription("用户名为空")
    public void Test02_loginUserNotFill() throws Exception {
        Page_login login = new Page_login();
        login.setPwd("123456");
        login.clickLogin();
        final String warning = WaitForExists(By.res(TAG_PACKAGEPAD, "warning")).getText();
        Assert.assertEquals(hintUser, warning);
        login.clickBack();

    }

    /**
     * 密码为空
     * @throws Exception
     */
    @Test
    @MethodDescription("密码为空")
    public void Test03_loginPassNotFill() throws Exception {
        Page_login login = new Page_login();
        login = new Page_login();
        login.setAccount("13655197396");
        login.clickLogin();
        Assert.assertTrue("未发现“用户登录成功”提示信息!",  processToast(hintPwd));
        login.clickBack();
    }

    /**
     * 错误密码
     * @throws Exception
     */
    @Test
    @MethodDescription("错误密码")
    public void Test04_loginPassMistake() throws Exception {
        Page_login login = new Page_login();
        login.setAccount("13655197396");
        login.setPwd("1234567");
        login.clickLogin();
        Assert.assertTrue("未发现"+ hinterr +"提示信息!",  processToast(hinterr));
        //退出登录
        login.clickBack();
    }

    /**
     * 二维码登录
     * @throws Exception
     */
    @Test
    @MethodDescription("二维码登录")
    public void Test05_loginEWM() throws Exception {
        Page_login login = new Page_login();
        login.clickScan();
        Assert.assertEquals(true,login.checkEWM());
        //连续两次返回
        login.clickBack();
        login.clickBack();
    }




    @AfterClass
    public static void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }





}
