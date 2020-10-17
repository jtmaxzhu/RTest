package page.rokipad;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import base.BasePage;
import framework.ParameterException;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.ClickElement;
import static lib.ComUtil.mDevice;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_leftbar extends BasePage {

    @VariableDescription("侧边栏登录")
    private UiObject2 logintip;

    @VariableDescription("侧边栏主页")
    private UiObject2 home;


    public Page_leftbar() { }

    /**
     * 点击登录
     * @throws Exception
     */
    public void clickLogin() throws Exception{
        logintip = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_person"));
        logintip.click();
        time(delayTimeMs);
    }

    /**
     * 点击主页
     * @throws Exception
     */
    public void clickHome() throws Exception{
        home = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_home_img"));
        home.click();
        time(delayTimeMs);
    }

    /**
     * 点击返回，因为没有相关定位信息，这里直接采样坐标点击
     * 输入参数为bounds范围
     */
    public void clickBack() throws Exception{
        ClickElement(4,240, 84, 320);
        time(delayTimeMs);
    }

    /**
     * 点击设置，因为没有相关定位信息，这里直接采样坐标点击
     *
     */
    public void clickSet() throws Exception{
        ClickElement(4,400, 84, 480);
        time(delayTimeMs);
    }



}

