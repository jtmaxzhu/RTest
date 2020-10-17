package testcase.pad.perftest;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import base.rokiapp.BaseTest;
import lib.perftool.SPService;
import page.rokiapp.Page_Login;
import page.rokiapp.Page_Main;
import page.rokiapp.Page_MainMe;
import robam.rtest.MyApplication;


/**
 * Created by Administrator on 2018/9/1.
 */

public class ROKI_Login extends BaseTest {

    private static Page_MainMe pageMainMe;
    private static Page_Main pageMain;
    private static Page_Login pageLogin;


    @BeforeClass
    public static void init(){
        SPService.init(MyApplication.getContext());
        boolean b = processAdbPermission();
        if(!b){
            Log.i("ROKI","网络调试连接失败，请检查是否开启5555端口");
        }else {
            Log.i("ROKI","网络调试连接成功");
        }

    }

    @Before
    public void initTest() throws Exception {

        //exec.schedule(task, 0, TimeUnit.SECONDS);
    }

    @After
    public void close() throws InterruptedException, IOException {
        //测试结束后（每个@Test结束后都会运行一次），如退出应用等
        //closeAPP(mDevice, TAG_PACKAGE);
    }




    @Test
    public void test() throws Exception {
        while (true){
            TimeUnit.SECONDS.sleep(1);
           // Log.d("ROKI", "test: ");

        }
    }





}
