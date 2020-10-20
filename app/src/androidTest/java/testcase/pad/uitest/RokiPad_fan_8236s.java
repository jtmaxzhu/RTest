package testcase.pad.uitest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base.rokipad.BaseTest;
import lib.LogUtil;
import lib.MethodDescription;
import page.rokipad.Page_fan;
import page.rokipad.Page_leftbar;

import static config.Config.TAG_PACKAGEPAD;


/**
 * Created by Administrator on 2018/9/2 0002.
 * 登录相关的测用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_fan_8236s extends BaseTest {

    private static final String hint = "关闭";
    private static final String hint1 = "切换到弱档风量";
    private static final String hint2 = "切换至强档风量";
    private static final String hint3 = "切换至爆炒风量";

    @Before
    @MethodDescription("测试环境初始化")
    public void init() throws Exception {
        LogUtil.d(TAG,"----------------"+ RokiPad_fan_8236s.class.getDeclaredMethod("init")
                .getAnnotation(MethodDescription.class).value()+"----------------");
        //判断页面是否停留在烟机主页（如果菜谱控件存在，就说明当前页面是主页），如果不是，进入主页
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"recipe")),1000)){
            //点击侧边栏登录按钮
            Page_leftbar leftbar = new Page_leftbar();
            leftbar.clickHome();
        }

    }

    /**
     * 1、先复位烟机档位，全部关掉
     * 2、点击弱档，  验证是否打开，Toast内容是否正确
     * 3、点击强档，  验证是否打开，Toast内容是否正确
     * 4、点击爆炒挡，验证是否打开，Toast内容是否正确
     * @throws Exception
     */
    @Test
    @MethodDescription("风量照明测试")
    public void Test01_FanLight() throws Exception {
        Page_fan fan = new Page_fan();
        //复位烟机档位，保持档位关闭状态
        fan.resetFan();
        //点击照明，默认照明未开，点击关掉照明
        fan.clickLight();
        //点击弱档，此时弱档打开
        fan.clickFanSmall();
        //判断Toast内容，应该显示-切换到弱档风量
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        //点击弱档，此时弱档关闭
        fan.clickFanSmall();
        //判断Toast内容，应该显示-关闭
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //点击强档，强档打开
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint2 +"提示信息!",  processToast(hint2));
        //点击强档，强档关闭
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //点击爆炒档，爆炒档打开
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint3 +"提示信息!",  processToast(hint3));
        //点击爆炒档，爆炒档关闭
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
    }

    /**
     * 1、复位烟机档位
     * 2、点击清洗锁定
     * 3、点击确定
     * 4、点击返回
     * @throws Exception
     */
    @Test
    @MethodDescription("清洗锁定测试")
    public void Test02_CleanLock() throws Exception {
        Page_fan fan = new Page_fan();
        //复位烟机档位，保持档位关闭状态
        fan.resetFan();
        //点击清洗锁定
        fan.clickCleanLock();
        //点击确定
        fan.clickOK();
        //点击清洗完毕，返回主界面
        fan.clickTvCancel();
    }


    /**
     * 极简模式风量，逻辑参考正常模式
     * @throws Exception
     */
    @Test
    @MethodDescription("极简模式风量照明测试")
    public void Test04_SimpleFanLight() throws Exception {
        Page_fan fan = new Page_fan();
        //复位烟机档位，保持档位关闭状态
        fan.resetFan();
        Page_leftbar leftbar = new Page_leftbar();
        //进入极简模式
        leftbar.clickHome();
        //照明
        fan.clickLight();
        //弱档
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint1 +"提示信息!",  processToast(hint1));
        fan.clickFanSmall();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //强档
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint2 +"提示信息!",  processToast(hint2));
        fan.clickFanMiddle();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //爆炒档
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint3 +"提示信息!",  processToast(hint3));
        fan.clickFanBig();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
        //退出极简模式
        fan.clickCancelSimple();
        //再次进入极简模式
        leftbar.clickHome();
        //点击清洗锁定
        fan.clickSimpleCleanLock();
        //确定
        fan.clickOK();
        //退出清洗
        fan.clickTvCancel();
    }

    /**
     * 依次点击主页底部选项卡
     * @throws Exception
     */
    @Test
    @MethodDescription("底部选项卡遍历")
    public void Test05_Tab() throws Exception {
        Page_fan fan = new Page_fan();
        //依次点击底部选项卡
        fan.clickTabs();
    }

    @AfterClass
    public static void TearDown() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickSet();
        //返回主页
        leftbar.clickHome();
    }








}
