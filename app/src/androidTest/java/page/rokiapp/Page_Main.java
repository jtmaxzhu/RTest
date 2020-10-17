package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import base.BasePage;
import framework.ParameterException;

import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_Main extends BasePage{
    UiDevice uiDevice;
    /**
     *  定义MainActivity控件变量名称以及对应的文字标签
     *  Intellect ：  "智能"
     *  Food      ：  "美食"
     *  Me        ：  "我"
     */
    private UiObject2 Intellect;
    private UiObject2 Food;
    private UiObject2 Me;


    public Page_Main() throws ParameterException, InterruptedException, UiObjectNotFoundException {
        // 初始化控件对象
        Intellect      =   WaitForExists(By.text("智能"));
        Food           =   WaitForExists(By.text("美食"));
        Me             =   WaitForExists(By.text("我"));
    }

    /**
     * 点击-智能-按钮
     * @throws Exception
     */
    public void clickIntellect() throws Exception{
        Intellect.click();
        time(delayTimeMs);
    }

    /**
     * 点击-美食-按钮
     * @throws Exception
     */
    public void clickFood() throws Exception{
        Food.click();
        time(delayTimeMs);
    }

    /**
     * 点击-我-按钮
     * @throws Exception
     */
    public void clickMe() throws Exception{
        Me.click();
        time(delayTimeMs);
    }

}

