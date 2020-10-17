package testcase.roki.perftest;

import android.util.Log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import base.rokiapp.BaseTest;
import lib.perftool.SPService;
import page.rokiapp.Page_Login;
import page.rokiapp.Page_Main;
import page.rokiapp.Page_MainMe;
import robam.rtest.MyApplication;

import static config.Config.TAG_ACTIVITY;
import static config.Config.TAG_PACKAGE;
import static lib.perftool.PerfUtil.DataInit;
import static lib.perftool.PerfUtil.DataWrite;


/**
 * Created by Administrator on 2018/9/1.
 */

public class ROKI_Login extends BaseTest {

    private static Page_MainMe pageMainMe;
    private static Page_Main pageMain;
    private static Page_Login pageLogin;
    private static File file = null;



    @BeforeClass
    public static void init(){
        SPService.init(MyApplication.getContext());
        boolean b = processAdbPermission();
        if(!b){
            Log.i("ROKI","网络调试连接失败，请检查是否开启5555端口");
        }else {
            Log.i("ROKI","网络调试连接成功");
        }
        //线程池shutdown后需要new一个新的出来，否则报错
        Log.i("ROKI",Thread.currentThread().getName()+"创建线程池");
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.schedule(task, 1, TimeUnit.SECONDS);
    }

    @Before
    public void initTest() throws Exception {
        initToastListener();
        //测试开始前运行（每个@Test运行前都会运行一次），如打开应用等
        comUtil.closeAPP(mDevice, TAG_PACKAGE);
        comUtil.startAPP(mDevice, TAG_ACTIVITY);
        pageMain = new Page_Main();
        pageMain.clickMe();
    }

    @AfterClass
    public static void close() throws InterruptedException, IOException {
        if (exec != null && !exec.isShutdown()){
            exec.shutdown();
        }
        Log.i("ROKI",Thread.currentThread().getName()+"线程结束1");
    }


    @After
    public void after() throws IOException, InterruptedException {

        Log.i("ROKI","数据写入文件");

        DataWrite(file, cmap);
        cmap.clear();
    }


    /**
     * 正常登录流程，用户名密码均正确
     * @throws
     */
    @Test
    public void loginNomal() throws Exception {
        //获取当前方法名称
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        //初始化文件
        file = DataInit(methodName);
        flag = true;
        for (int i = 0; i < 1; i++) {
            loginInitQuit();
            pageMainMe = new Page_MainMe();
            pageMainMe.clickFigure();
            pageLogin = new Page_Login();
            pageLogin.setAccount("13655197396");
            pageLogin.setPwd("123456");
            pageLogin.clickLogin();
        }
        flag = false;
    }

    /**
     * 正常登录流程，用户名密码均正确
     * @throws
     */
    @Test
    public void loginNomal1() throws Exception {
        //获取当前方法名称
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        //初始化文件
        file = DataInit(methodName);
        flag = true;
        for (int i = 0; i < 1; i++) {
            loginInitQuit();
            pageMainMe = new Page_MainMe();
            pageMainMe.clickFigure();
            pageLogin = new Page_Login();
            pageLogin.setAccount("13655197396");
            pageLogin.setPwd("123456");
            pageLogin.clickLogin();
        }
        flag = false;
    }



}
