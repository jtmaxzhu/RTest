package testcase.roki.uitest;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

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
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class ROKI_About extends BaseTest {
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
    }

    @After
    public void close() throws InterruptedException, IOException {
        //测试结束后（每个@Test结束后都会运行一次），如退出应用等
       // closeAPP(mDevice, TAG_PACKAGE);
    }


    /**
     * 进入关于ROKI
     * @throws Exception
     */
    @Test
    public void AboutRoki() throws Exception {
        loginInit();
        pageMainMe = new Page_MainMe();
        pageMainMe.clickAbout();
        Assert.assertTrue("TOAST未捕获!", mDevice.hasObject(By.text("关于ROKI")));
    }

    /**
     * 在菜谱搜索结果页面上，点击第一个匹配项
     * @throws Exception
     */
    public void clickCook(String cook) throws Exception {
        mDevice = comUtil.getDevice();
        mDevice.findObject(new UiSelector().text(cook)).clickAndWaitForNewWindow();
        time(delayTimeMs);
    }

    /**
     * 在菜谱详情页面上，搜索目标文本
     * @throws Exception
     */
    public void findStr(String str) throws Exception {
        UiScrollable devicePage = new UiScrollable(new UiSelector().className("android.webkit.WebView"));
        //设置垂直滚动
        devicePage.setAsVerticalList();
        devicePage.scrollTextIntoView("上传作品");
        WaitForExists(By.clazz("android.view.View").text("上传作品 ")).click();
        clickUp("很好吃");
    }

    /**
     * 点击上传作品
     * @throws Exception
     */
    public void clickUp(String str) throws Exception {
        WaitForExists(By.desc("上传作品 ")).click();
//        time(delayTimeMs);
//        WaitForExists(By.text("相册")).click();
//        time(delayTimeMs);
//        WaitForExists(By.text("pictures")).click();
//        time(delayTimeMs);
//        submit(str);
    }

    /**
     * 计算相册图片相对"取消"坐标,最大程度消除分辨率不同带来的影响，并进行提交操作
     * @throws Exception
     */
    public void submit(String str) throws Exception {
        Rect rect = mDevice.findObject(By.descContains("取消")).getVisibleBounds();
        mDevice.click(rect.left,rect.bottom + 100);
        WaitForExists(By.res("com.android.gallery3d","head_select_right")).click();
        time(delayTimeMs);
        WaitForExists(By.res("com.android.gallery3d","head_select_right")).click();
        time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "txtContent")).setText(str);
        time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGE, "txtConfirm")).click();
    }

}
