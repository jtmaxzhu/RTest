package page.rokipad;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import base.BasePage;
import framework.ParameterException;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_wifi extends BasePage {
    UiDevice uiDevice;
    /**
     *  定义控件变量名称以及对应的文字标签
     *  wifiContr      ：  "WiFi开关按钮"
     *  skip           ：  "跳过"
     */
    @VariableDescription("WiFi开关")
    private UiObject2 wifiContr;

    @VariableDescription("跳过")
    private UiObject2 skip;


    public Page_wifi() {
    }

    /**
     * 点击wifi按钮
     * @throws Exception
     */
    public void clickWifi() throws Exception{
        wifiContr = WaitForExists(By.res(TAG_PACKAGEPAD, "wifi_control"));
        wifiContr.click();
        time(delayTimeMs);
    }

    /**
     * 点击跳过按钮
     * @throws Exception
     */
    public void clickSkip() throws Exception{
        skip = WaitForExists(By.res(TAG_PACKAGEPAD, "guid_wifi_skip"));
        skip.click();
        time(delayTimeMs);
    }

    /**
     * 验证WIFI开关状态
     * @throws Exception
     */
    public Boolean checkWiFiSwitch() throws Exception {
        wifiContr = WaitForExists(By.res(TAG_PACKAGEPAD, "wifi_control"));
        return isOpen(wifiContr);
    }


    /**
     * 判断控件状态，通过单点像素的颜色判定，只适用于此页面控件类型, 白色为关，非白色为开
     * @param obj
     * @return true:打开 false：关闭
     */
    private static Boolean isOpen(UiObject2 obj) throws IOException {
        Rect rect = obj.getVisibleBounds();
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;
        int i = (x1 + (x1 + x2)/2)/2;
        int y = (y1+y2)/2;
        String rgb = getRGB((x1 + (x1 + x2) / 2) / 2, y);
        return !rgb.equals("ffffffff");
    }


}

