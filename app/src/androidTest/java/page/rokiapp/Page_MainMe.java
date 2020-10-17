package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import base.BasePage;

import static config.Config.TAG_PACKAGE;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_MainMe extends BasePage{
    UiDevice uiDevice;
    UiScrollable devicePage;
    /**
     *  定义MainMeActivity控件变量名称以及对应的文字标签
     *  Figure          ：  "请登录"
     *  MyOrder         ：  "我的订单"
     *  FoodShopping    ：  "食材商城"
     *  MyCollection    ：  "我的收藏"
     *  BaskInCooking   ：  "晒过的厨艺"
     *  DeviceManage    ：  "设备管理"
     *  AfterSale       ：  "售后服务"
     *  About           ：  "关于ROKI"
     */
    private UiObject2 Figure;
    private UiObject  MyOrder;
    private UiObject  MyCart;
    private UiObject  FoodShopping;
    private UiObject  MyCollection;
    private UiObject  BaskInCooking;
    private UiObject  DeviceManage;
    private UiObject  AfterSale;
    private UiObject  About;

    public Page_MainMe() throws Exception {
        devicePage = new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/listview"));
        //设置垂直滚动
        devicePage.setAsVerticalList();
        // 初始化控件对象
        Figure = WaitForExists(By.res(TAG_PACKAGE, "txtFigure"));
    }


    /**
     * 点击-Figure-按钮,进入登录页面
     * @throws Exception
     */
    public void clickFigure() throws Exception{
        Figure.click();
        time(delayTimeMs);
    }

    /**
     * 点击-我的订单-按钮
     * @throws Exception
     */
    public void clickMyOrder() throws Exception{
        MyOrder = devicePage.getChildByText(new UiSelector().text("我的订单"),"我的订单",true);
        MyOrder.click();
        time(delayTimeMs);
    }

    /**
     * 点击-我的购物车-按钮
     * @throws Exception
     */
    public void clickMyCar() throws Exception{
        MyCart = devicePage.getChildByText(new UiSelector().text("我的购物车"),"我的购物车",true);
        MyCart.click();
        time(delayTimeMs);
    }

    /**
     * 点击-食材商城-按钮
     * @throws Exception
     */
    public void clickFoodShopping() throws Exception{
        FoodShopping = devicePage.getChildByText(new UiSelector().text("食材商城"),"食材商城",true);
        FoodShopping.click();
        time(delayTimeMs);
    }

    /**
     * 点击-我的收藏-按钮
     * @throws Exception
     */
    public void clickMyCollection() throws Exception{
        MyCollection = devicePage.getChildByText(new UiSelector().text("我的收藏"),"我的收藏",true);
        MyCollection.click();
        time(delayTimeMs);
    }

    /**
     * 点击-晒过的厨艺-按钮
     * @throws Exception
     */
    public void clickBaskInCooking() throws Exception{
        BaskInCooking = devicePage.getChildByText(new UiSelector().text("晒过的厨艺"),"晒过的厨艺",true);
        BaskInCooking.click();
        time(delayTimeMs);
    }

    /**
     * 点击-厨电管理-按钮
     * @throws Exception
     */
    public void clickDeviceManage() throws Exception{
        DeviceManage = devicePage.getChildByText(new UiSelector().text("厨电管理"),"厨电管理",true);
        DeviceManage.click();
        time(delayTimeMs);
    }


    /**
     * 点击-售后服务-按钮
     * @throws Exception
     */
    public void clickAfterSale() throws Exception{
        AfterSale = devicePage.getChildByText(new UiSelector().text("售后服务"),"售后服务",true);
        AfterSale.click();
        time(delayTimeMs);
    }

    /**
     * 点击-关于ROKI-按钮
     * @throws Exception
     */
    public void clickAbout() throws Exception{
        About = devicePage.getChildByText(new UiSelector().text("关于ROKI"),"关于ROKI",true);
        About.click();
        time(delayTimeMs);
    }


    /**
     * 获取Figure控件text文字信息
     * @throws Exception
     */
    public String getFigureText() throws Exception{
        return Figure.getText();
    }


}

