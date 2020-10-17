package testcase.roki.uitest;

import android.util.Log;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import base.rokiapp.BaseTest;
import lib.LogUtil;
import page.rokiapp.Page_Login;
import page.rokiapp.Page_Main;
import page.rokiapp.Page_MainMe;

import static config.Config.TAG_ACTIVITY;
import static config.Config.TAG_PACKAGE;


/**
 * Created by Administrator on 2018/9/1.
 */

public class ROKI_Login extends BaseTest {

    private static Page_MainMe pageMainMe;
    private static Page_Main pageMain;
    private static Page_Login pageLogin;


    @Before
    public void initTest() throws Exception {
        initToastListener();
        //测试开始前运行（每个@Test运行前都会运行一次），如打开应用等
        comUtil.closeAPP(mDevice, TAG_PACKAGE);
        comUtil.startAPP(mDevice, TAG_ACTIVITY);
        pageMain = new Page_Main();
        pageMain.clickMe();

    }

    @After
    public void close() throws InterruptedException, IOException {
        //测试结束后（每个@Test结束后都会运行一次），如退出应用等
        //closeAPP(mDevice, TAG_PACKAGE);
    }


    /**
     * 正常登录流程，用户名密码均正确
     * @throws
     */
    @Test
    public void loginNormal() throws Exception {
        loginInitQuit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickFigure();
        pageLogin = new Page_Login();
        pageLogin.setAccount("13655197396");
        pageLogin.setPwd("123456");
        pageLogin.clickLogin();
        Assert.assertTrue("TOAST未捕获!",  processToast("登录成功"));

    }


    @Test
    public void test() throws Exception {
        while (true){
            TimeUnit.SECONDS.sleep(1);
            Log.d("SMSReceiver", "test: ");
            LogUtil.d("SMSReceiver", "test:LogUtil ");

        }
    }

    /**
     * 用户名为空
     * @throws Exception
     */
    @Test
    public void loginUserNotFill() throws Exception {
        loginInitQuit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickFigure();
        pageLogin = new Page_Login();
        pageLogin.clearAccount();
        pageLogin.setPwd("123456");
        pageLogin.clickLogin();
        Assert.assertTrue("TOAST未捕获!",  processToast("不支持的手机格式"));
    }

    /**
     * 密码为空
     * @throws Exception
     */
    @Test
    public void loginPassNotFill() throws Exception {
        loginInitQuit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickFigure();
        pageLogin = new Page_Login();
        pageLogin.setAccount("13655197396");
        pageLogin.clearPwd();
        pageLogin.clickLogin();
        Assert.assertTrue("TOAST未捕获!",  processToast("未填写密码"));
    }

    /**
     * 错误密码
     * @throws Exception
     */
    @Test
    public void  loginPassMistake() throws Exception {
        loginInitQuit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickFigure();
        pageLogin = new Page_Login();
        pageLogin.setAccount("13655197396");
        pageLogin.setPwd("1234567");
        pageLogin.clickLogin();
        Assert.assertTrue("TOAST未捕获!",  processToast("账号或密码错误"));
    }
}
