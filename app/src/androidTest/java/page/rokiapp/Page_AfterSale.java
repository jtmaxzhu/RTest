package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import framework.ParameterException;

import static config.Config.TAG_PACKAGE;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class Page_AfterSale {
    /**
     *  定义AfterSaleActivity控件变量名称以及对应的文字标签
     *  Consulting ：  "留言咨询"
     *  AfterSale  ：  "一键售后"
     *  CallPhone  ：  "0571-86281080"
     *  back       ：  "←"
     */
    private UiObject2 Consulting;
    private UiObject2 AfterSale;
    private UiObject2 CallPhone;
    private UiObject2 back;

    public Page_AfterSale() throws ParameterException, InterruptedException, UiObjectNotFoundException {
        // 初始化控件对象
        Consulting      =   WaitForExists(By.res(TAG_PACKAGE, "ll_message_consulting"));
        AfterSale       =   WaitForExists(By.res(TAG_PACKAGE, "ll_key_after_sales"));
        CallPhone       =   WaitForExists(By.res(TAG_PACKAGE, "tv_call_phone"));
        back            =   WaitForExists(By.res(TAG_PACKAGE, "img_back"));
    }

    /**
     * 点击-一键售后-按钮
     * @param  phone  售后服务电话
     * @throws Exception
     */
    public Boolean clickAfterSaleAndVerify(String phone) throws Exception{
        AfterSale.click();
        time(delayTimeMs);
        return WaitForExists(By.res("com.android.contacts", "digits")).getText().replace(" ","").equals(phone);

    }

    /**
     * 点击-留言咨询-按钮
     * @throws Exception
     */
    public void clickConsulting() throws Exception{
        Consulting.click();
        time(delayTimeMs);
        //return WaitForExists(By.res("com.android.contacts", "digits")).getText().equals("9510 5855");

    }

    /**
     * 点击-返回-按钮
     * @throws Exception
     */
    public void clickBack() throws Exception{
        back.click();
        time(delayTimeMs);
    }

    /**
     * 点击-一键售后-按钮
     *
     * @throws Exception
     */
    public Boolean clickPhoneAndVerify() throws Exception{
        CallPhone.click();
        time(delayTimeMs);
        return WaitForExists(By.res("com.android.contacts", "digits")).getText().equals("0571 8628 1080");

    }

}
