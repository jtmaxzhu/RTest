package page.rokipad;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import java.io.IOException;
import java.util.List;

import base.BasePage;
import framework.ParameterException;
import lib.LogUtil;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.WaitForExistsList;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_fan extends BasePage {


    @VariableDescription("照明")
    private UiObject2 fan_light;

    @VariableDescription("弱档")
    private UiObject2 fan_small;


    @VariableDescription("中档")
    private UiObject2 fan_middle;

    @VariableDescription("爆炒档")
    private UiObject2 fan_big;

    @VariableDescription("清洗锁定")
    private UiObject2 iv_clean_lock;

    @VariableDescription("极简模式清洗锁定")
    private UiObject2 tv_clean_lock;

    @VariableDescription("清洗锁定返回")
    private UiObject2 tv_cancel;


    @VariableDescription("3D手势图标")
    private UiObject2 iv_3d_gesture_switch;

    @VariableDescription("菜谱入口")
    private UiObject2 recipe;

    @VariableDescription("确定")
    private UiObject2 OK;

    @VariableDescription("取消")
    private UiObject2 cancel;

    @VariableDescription("返回")
    private UiObject2 back;


    @VariableDescription("3D手势说明")
    private UiObject2 tv_3d_wave;



    public Page_fan() { }





    /**
     * 点击照明按钮
     * @throws Exception
     */
    public void clickLight() throws Exception{
        fan_light = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_light"));
        fan_light.click();
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("fan_light")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }


    /**
     * 点击弱档
     * @throws Exception
     */
    public void clickFanSmall() throws Exception{
        fan_small = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_small"));
        fan_small.click();
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("fan_small")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击中档
     * @throws Exception
     */
    public void clickFanMiddle() throws Exception {
        fan_middle = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_middle"));
        fan_middle.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("fan_middle")
                .getAnnotation(VariableDescription.class).value());
    }

    /**
     * 点击爆炒档
     * @throws Exception
     */
    public void clickFanBig() throws Exception {
        fan_big = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_big"));
        fan_big.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("fan_big")
                .getAnnotation(VariableDescription.class).value());
    }

    /**
     * 点击清洗锁定
     * @throws Exception
     */
    public void clickCleanLock() throws Exception {
        iv_clean_lock = WaitForExists(By.res(TAG_PACKAGEPAD, "iv_clean_lock"));
        iv_clean_lock.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("iv_clean_lock")
                .getAnnotation(VariableDescription.class).value());
    }

    /**
     * 点击极简模式的清洗锁定
     * @throws Exception
     */
    public void clickSimpleCleanLock() throws Exception {
        tv_clean_lock = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_clean_lock"));
        tv_clean_lock.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("tv_clean_lock")
                .getAnnotation(VariableDescription.class).value());
    }


    /**
     * 点击清洗锁定退出
     * @throws Exception
     */
    public void clickTvCancel() throws Exception {
        tv_cancel = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_cancel"));
        tv_cancel.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("tv_cancel")
                .getAnnotation(VariableDescription.class).value());
    }

    /**
     * 点击AI智控操作说明
     * @throws Exception
     */
    public void click3DWave() throws Exception {
        tv_3d_wave = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_3d_wave"));
        tv_3d_wave.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("tv_3d_wave")
                .getAnnotation(VariableDescription.class).value());
    }


    /**
     * 点击菜谱入口
     * @throws Exception
     */
    public void clickRecipe() throws Exception {
        recipe = WaitForExists(By.res(TAG_PACKAGEPAD, "recipe"));
        recipe.click();
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("recipe")
                .getAnnotation(VariableDescription.class).value());
    }

    /**
     * 底部tab键循环点击
     * @throws Exception
     */
    public void clickTabs() throws Exception {
        List<UiObject2> tab_name = WaitForExistsList(By.res(TAG_PACKAGEPAD, "tv_tab_name"));
        for (UiObject2 obj: tab_name) {
            obj.click();
            time(delayTimeMs);
        }
        time(delayTimeMs);
    }


    /**
     * 烟机状态恢复至初始状态
     */
    public void resetFan() throws Exception {
        UiObject2 obj1 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_light"));
        if (isOpen(obj1)){
            obj1.click();
            time(delayTimeMs);
        }
        UiObject2 obj2 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_small"));
        if (isOpen(obj2)){
            obj2.click();
            time(delayTimeMs);
        }
        UiObject2 obj3 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_middle"));
        if (isOpen(obj3)){
            obj3.click();
            time(delayTimeMs);
        }
        UiObject2 obj4 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_big"));
        if (isOpen(obj4)){
            obj4.click();
            time(delayTimeMs);
        }
        time(delayTimeMs);
        LogUtil.d(TAG,"点击"+ Page_fan.class.getDeclaredField("recipe")
                .getAnnotation(VariableDescription.class).value());
    }


    /**
     * 判断控件状态，通过单点像素的颜色判定，只适用于此页面控件类型, 白色为关，非白色为开
     * @param obj
     * @return true:打开 false：关闭
     */
    public static Boolean isOpen(UiObject2 obj) throws IOException {
        Rect rect = obj.getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;
        String rgb = getRGB( (x1 + x2) / 2, (y1+y2)/2);
        LogUtil.d("123","位置:"+(x1 + x2) / 2+","+(y1+y2)/2);
        LogUtil.d("123","rgb:"+rgb);
        return !rgb.equals("ffffffff");
    }

    /**
     * 点击对话框中的删除按钮
     * @throws Exception
     */
    public void clickOK() throws Exception {
        OK =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_ok"));
        OK.click();
        LogUtil.d(TAG,"点击"+Page_fan.class.getDeclaredField("OK")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击对话框中的取消按钮
     * @throws Exception
     */
    public void clickCancel() throws Exception {
        cancel =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_can"));
        cancel.click();
        LogUtil.d(TAG,"点击"+Page_fan.class.getDeclaredField("cancel")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }

    /**
     * 点击退出极简模式
     * @throws Exception
     */
    public void clickCancelSimple() throws Exception {
        back =  WaitForExists(By.res(TAG_PACKAGEPAD, "tv_back"));
        back.click();
        LogUtil.d(TAG,"点击"+Page_fan.class.getDeclaredField("back")
                .getAnnotation(VariableDescription.class).value());
        time(delayTimeMs);
    }






}

