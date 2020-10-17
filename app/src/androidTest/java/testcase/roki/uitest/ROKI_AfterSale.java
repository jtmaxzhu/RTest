package testcase.roki.uitest;

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

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class ROKI_AfterSale extends BaseTest {
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
        //closeAPP(mDevice, TAG_PACKAGE);
    }


    /**
     * 留言咨询，发送咨询消息
     * @throws Exception
     */
    @Test
    public void consultingSendMsg() throws Exception {
        loginInit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickAfterSale();
        pageAfterSale = new Page_AfterSale();
        pageAfterSale.clickConsulting();
        pageConsulting = new Page_Consulting();
        pageConsulting.setMsg("咨询一个问题");
        pageConsulting.sendMsg();
        pageConsulting.getLatestMsg();
        Assert.assertTrue("咨询不符合预期!", pageConsulting.getLatestMsg().equals("你是机器人还是真人"));

    }

    /**
     * 拨打一键售后服务电话
     * @throws Exception
     */
    @Test
    public void aKeyAfterSales() throws Exception {
        loginInit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickAfterSale();
        pageAfterSale = new Page_AfterSale();
        Assert.assertTrue("咨询不符合预期!", pageAfterSale.clickAfterSaleAndVerify("95105855"));
        mDevice.pressBack();
    }

    /**
     * 拨打未开通地区售后服务电话
     * @throws Exception
     */
    @Test
    public void AfterSalesPhone() throws Exception {
        loginInit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickAfterSale();
        pageAfterSale = new Page_AfterSale();
        Assert.assertTrue("咨询不符合预期!", pageAfterSale.clickPhoneAndVerify());
        mDevice.pressBack();
    }

}
