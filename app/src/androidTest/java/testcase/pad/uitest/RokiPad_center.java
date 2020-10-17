package testcase.pad.uitest;

import android.support.test.uiautomator.By;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.rokipad.BaseTest;
import lib.LogUtil;
import lib.MethodDescription;
import page.rokipad.Page_center;
import page.rokipad.Page_leftbar;
import page.rokipad.Page_login;

import static config.Config.TAG_PACKAGEPAD;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;


/**
 * Created by Administrator on 2018/9/2 0002.
 * 登录相关的测用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_center extends BaseTest {

    private static final String hint = "请先登录";
    private static final String hint1 = "不能删除自己哦";

    @Before
    @MethodDescription("测试环境初始化")
    public void init() throws Exception {
        LogUtil.d(TAG,"----------------"+ RokiPad_center.class.getDeclaredMethod("init")
                .getAnnotation(MethodDescription.class).value()+"----------------");
        Page_leftbar leftbar = new Page_leftbar();
        //点击左侧边栏登录按钮
        leftbar.clickLogin();
        Page_center center = new Page_center();
        //判断用户是否登录
        if (center.isLogin()){
            //如果登录则退出登录
            center.clickTvLogin();
            center.clickOK();
        }
    }


    /**
     * @throws Exception
     */
    @Test
    @MethodDescription("未登录设置分享控制权")
    public void Test02_NoLoginSetShareControl() throws Exception {
        Page_center center = new Page_center();
        //点击分享控制权
        center.clickShareControl();
        //判断Toast内容 如果失败则打印message内定义的内容
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //点击删除控制权
        center.clickDeleteControl();
        //二次点击，因为不知道什么原因点一次可能没有弹窗出来，这里判断一下，如果没有弹窗出来，再点击一次
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"))==null){
            center.clickDeleteControl();
        }
        //点击删除
        center.clickOK();
        //判断Toast内容 如果失败则打印message内定义的内容
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
    }


    /**
     * 1、先登录账号
     * 2、点击分享控制权，验证是否弹出二维码
     * 3、点击删除控制权，验证是否能删除（自己不能删除自己）
     * 4、退出登录
     * @throws Exception
     */
    @Test
    @MethodDescription("登录设置分享控制权")
    public void Test01_LoginSetShareControl() throws Exception {
        Page_center center = new Page_center();
        //点击中间登录按钮
        center.clickTvLogin();
        Page_login login = new Page_login();
        //填写用户名
        login.setAccount("13655197396");
        //填写密码
        login.setPwd("123456");
        //点击登录
        login.clickLogin();
        //需要延时，增强稳定性
        time(1000);
        //点击分享控制权
        center.clickShareControl();
        //二次点击，因为不知道说明原因点一次可能没有弹窗出来，这里判断一下，如果没有弹窗出来，再点击一次
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "img"))==null){
            center.clickShareControl();
        }
        //需要延时，增强稳定性
        time(2000);
        //判断二维码控件是否显示
        Assert.assertEquals(true,center.checkEWM());
        //滑动一下退出二维码显示界面
        mDevice.swipe(1000,200,1000,400,15);
        //需要延时，注解
        time(1000);
        center.clickDeleteControl();
        if (WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"))==null){
            center.clickDeleteControl();
        }
        center.clickOK();
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        //退出登录
        center.clickTvLogin();
        center.clickOK();
    }


    /**
     * 每个用例完成后返回到烟机主页
     * @throws Exception
     */
    @After
    public void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }







}
