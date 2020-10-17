package testcase.roki.uitest;

import android.support.test.uiautomator.By;

import org.junit.Test;

import static config.Config.TAG_PACKAGE;
import static lib.ComUtil.WaitForExists;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class ROKI_About1{
    @Test
    public void  test11() throws Exception {
        WaitForExists(By.res(TAG_PACKAGE, "tv_about_roki_name")).click();
        WaitForExists(By.res(TAG_PACKAGE, "tv_wifi_name")).click();
        WaitForExists(By.res(TAG_PACKAGE, "tv_after_service_name")).click();
        WaitForExists(By.res(TAG_PACKAGE, "tv_smart_name")).click();
        WaitForExists(By.res(TAG_PACKAGE, "tv_personal_center_name")).click();
    }
}
