package testcase.roki.uitest;

import android.support.test.uiautomator.By;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import base.rokiapp.BaseTest;
import page.rokiapp.Page_AfterSale;
import page.rokiapp.Page_Consulting;
import page.rokiapp.Page_Main;
import page.rokiapp.Page_MainMe;

import static config.Config.TAG_ACTIVITY;
import static config.Config.TAG_PACKAGE;
import static lib.ComUtil.WaitForExists;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class ROKI_FoodShop extends BaseTest {
    public static Page_MainMe pageMainMe;
    public static Page_Main pageMain;
    public static Page_AfterSale pageAfterSale;
    public static Page_Consulting pageConsulting;



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
       // closeAPP(mDevice, TAG_PACKAGE);
    }


    /**
     * 进入美食商城
     * @throws Exception
     */
    @Test
    public void FoodShop() throws Exception {
        loginInit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickFoodShopping();
        String title = WaitForExists(By.res(TAG_PACKAGE, "tv_title")).getText();
        Boolean result = title.equals("ROKI精选生鲜 - ROKI智能烹饪");
        Assert.assertTrue("美食商城title与预期值不符!", result);

    }

}
