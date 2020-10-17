package page.rokipad;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.Until;

import java.io.IOException;
import java.util.List;

import base.BasePage;
import framework.ParameterException;
import lib.LogUtil;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.ClickElement;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.WaitForExistsList;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.mDevice;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_center extends BasePage {

    /**
     *  定义控件变量名称以及对应的文字标签
     *  tvlogin        ：  登录
     *  addDevice      ：  添加新设备
     */
    @VariableDescription("主页面登录")
    private UiObject2 tvlogin;

    @VariableDescription("添加新设备")
    private UiObject2 addDevice;


    //该页面上可能会同时存在多个相同resourceid的控件，使用list列表处理
    @VariableDescription("分享控制权")
    private List<UiObject2> shareControl;

    @VariableDescription("删除控制权")
    private List<UiObject2> deleteControl;

    @VariableDescription("删除")
    private UiObject2 OK;

    @VariableDescription("取消")
    private UiObject2 cancel;



    public Page_center() { }

    //获取控件名
    public String getNickName() throws ParameterException {
        return WaitForExists(By.res(TAG_PACKAGEPAD, "tv_nickname")).getText();
    }




    /**
     * 点击手机登录按钮
     * @throws Exception
     */
    public void clickTvLogin() throws Exception{
        tvlogin = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_login"));
        tvlogin.click();
        time(delayTimeMs);
    }


    /**
     * 点击添加设备
     * @throws Exception
     */
    public void clickAddDevice() throws Exception{
        addDevice = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_add_device"));
        addDevice.click();
        time(delayTimeMs);
    }

    /**
     * 点击分享控制权按钮
     * 该控件clickable属性为false不可点击，会有概率点击无效，这里采取坐标
     * @throws Exception
     */
    public void clickShareControl() throws Exception {
        shareControl =  WaitForExistsList(By.res(TAG_PACKAGEPAD, "tv_share_control_power"));
        shareControl.get(0).click();
        Rect rect = shareControl.get(0).getVisibleBounds();
        ClickElement(rect.left, rect.top, rect.right, rect.bottom);
        LogUtil.d(TAG,"点击"+Page_center.class.getDeclaredField("shareControl")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击删除控制权按钮
     * @throws Exception
     */
    public void clickDeleteControl() throws Exception {
        deleteControl =  WaitForExistsList(By.res(TAG_PACKAGEPAD, "tv_delete_control_power"));
        deleteControl.get(0).click();
        Rect rect = deleteControl.get(0).getVisibleBounds();
        ClickElement(rect.left, rect.top, rect.right, rect.bottom);
        LogUtil.d(TAG,"点击"+Page_center.class.getDeclaredField("deleteControl")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 判断用户是否已经登录
     * @return true:已登录 false：未登录
     */
    public Boolean isLogin() throws ParameterException {
        String tv_nickname = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_nickname")).getText();
        return !tv_nickname.equals("未登录");
    }


    /**
     * 点击对话框中的删除按钮
     * @throws Exception
     */
    public void clickOK() throws Exception {
        OK =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"));
        OK.click();
        LogUtil.d(TAG,"点击"+Page_center.class.getDeclaredField("OK")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击对话框中的取消按钮
     * @throws Exception
     */
    public void clickCancel() throws Exception {
        cancel =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_cancel"));
        cancel.click();
        LogUtil.d(TAG,"点击"+Page_center.class.getDeclaredField("cancel")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    //检查二维码是否存在
    public Boolean checkEWM() throws ParameterException {
        return WaitForExists(By.res(TAG_PACKAGEPAD, "img")) != null;
    }

}

