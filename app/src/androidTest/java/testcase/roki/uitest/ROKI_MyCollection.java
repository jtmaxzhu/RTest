package testcase.roki.uitest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import base.rokiapp.BaseTest;
import page.rokiapp.Page_Main;
import page.rokiapp.Page_MainFood;
import page.rokiapp.Page_MainMe;

import static config.Config.TAG_ACTIVITY;
import static config.Config.TAG_PACKAGE;
import static lib.ComUtil.WaitForExists;

/**
 * Created by Administrator on 2018/9/3.
 */

public class ROKI_MyCollection extends BaseTest {

    public static Page_MainMe pageMainMe;
    public static Page_Main pageMain;
    public static Page_MainFood pageMainFood;

    public static  String testCook = "红烧肉";




    @Before
    public void initTest() throws Exception {
        initToastListener();
        //测试开始前运行（每个@Test运行前都会运行一次），如打开应用等
        comUtil.closeAPP(mDevice, TAG_PACKAGE);
        comUtil.startAPP(mDevice, TAG_ACTIVITY);
        pageMain = new Page_Main();
        pageMain.clickMe();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickMyCollection();
        UiObject2 obj = mDevice.findObject(By.text(testCook));
        if(obj != null){
            obj.longClick();
            comUtil.time(1000);
            WaitForExists(By.text("确定")).click();
            comUtil.time(1000);
            mDevice.pressBack();
        }
        mDevice.pressBack();
    }

    @After
    public void close() throws InterruptedException, IOException {
        //测试结束后（每个@Test结束后都会运行一次），如退出应用等
        //closeAPP(mDevice, TAG_PACKAGE);
    }


    /**
     * 添加取消菜谱收藏
     * @throws Exception
     */
    @Test
    public void addDelCollection() throws Exception {
        loginInit();
        pageMain.clickFood();
        pageMainFood = new Page_MainFood();
        pageMainFood.clickRecipeSearch(testCook);
        clickCook(testCook);
        Assert.assertTrue("收藏成功TOAST未捕获!",  processToast("收藏成功!"));
        mDevice.pressBack();
        comUtil.time(1000);
        mDevice.pressBack();
        pageMain.clickMe();
        pageMainMe.clickMyCollection();
        comUtil.time(1000);
        //长按删除
        WaitForExists(By.text(testCook+"（灶具）")).click();
        comUtil.time(1000);
        clickFavor();
        comUtil.time(1000);
        Assert.assertTrue("取消收藏成功TOAST未捕获!",  processToast("取消收藏成功!"));
    }





    /**
     * 拨打一键售后电话
     * @throws Exception
     */
    public void loginInit() throws Exception {
        if(!isLogin()){
            Login();
        }
    }





    /**
     * 在菜谱搜索结果页面上，点击第一个匹配项,点击收藏图标
     * @throws Exception
     */
    public void clickCook(String cook) throws Exception {
        mDevice = comUtil.getDevice();
        WaitForExists(By.text(cook+"（灶具）")).click();
        comUtil.time(1000);
        clickFavor();
    }

    /**
     * 点击收藏红心图标
     * @throws Exception
     */
    public void clickFavor() throws Exception {
        WaitForExists(By.res(TAG_PACKAGE, "imgFavority")).click();//点击心形图标收藏菜谱
        comUtil.time(2000);
    }


}
