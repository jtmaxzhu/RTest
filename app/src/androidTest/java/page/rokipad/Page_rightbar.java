package page.rokipad;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import base.BasePage;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_rightbar extends BasePage {
    /**
     *  定义控件变量名称以及对应的文字标签
     *  personCenter   ：  个人中心
     *  smartSet       ：  智能设定
     *  afterSale      ：  售后服务
     *  wifiSet        ：  wifi连接
     *  aboutRoki      ：  关于ROKI
     */
    @VariableDescription("个人中心")
    private UiObject2 personCenter;

    @VariableDescription("智能设定")
    private UiObject2 smartSet;

    @VariableDescription("售后服务")
    private UiObject2 afterSale;

    @VariableDescription("wifi连接")
    private UiObject2 wifiSet;

    @VariableDescription("关于ROKI")
    private UiObject2 aboutRoki;

    public Page_rightbar() { }

    /**
     * 点击个人中心
     * @throws Exception
     */
    public void clickPersonCenter() throws Exception{
        personCenter = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_personal_center_name"));
        personCenter.click();
        time(delayTimeMs);
    }

    /**
     * 点击智能设定
     * @throws Exception
     */
    public void clickSmartSet() throws Exception{
        smartSet = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_smart_name"));
        smartSet.click();
        time(delayTimeMs);
    }

    /**
     * 点击售后服务
     * @throws Exception
     */
    public void clickAfterSale() throws Exception{
        afterSale = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_after_service_name"));
        afterSale.click();
        time(delayTimeMs);
    }

    /**
     * 点击WiFi
     * @throws Exception
     */
    public void clickWifi() throws Exception{
        wifiSet = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_wifi_name"));
        wifiSet.click();
        time(delayTimeMs);
    }

    /**
     * 点击关于ROKI
     * @throws Exception
     */
    public void clickAboutRoki() throws Exception{
        aboutRoki = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_about_roki_name"));
        aboutRoki.click();
        time(delayTimeMs);
    }

}

