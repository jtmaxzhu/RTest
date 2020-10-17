package page.rokipad;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import base.BasePage;
import framework.ParameterException;
import lib.LogUtil;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_login extends BasePage {

    UiDevice uiDevice;
    /**
     *  定义控件变量名称以及对应的文字标签
     *  Account        ：  "用户名"
     *  Pwd            ：  "密码"
     *  Login          ：  "登录"
     *  tvScan         ：  "二维码登录"
     *  tvStatus       ：  "手机登录"
     *  back           ：  "返回"
     *  skip           ：  "跳过"
     */

    @VariableDescription("用户名")
    private UiObject2 Account;

    @VariableDescription("密码")
    private UiObject2 Pwd;

    @VariableDescription("登录")
    private UiObject2 Login;

    @VariableDescription("二维码登录")
    private UiObject2 tvScan;

    @VariableDescription("手机登录")
    private UiObject2 tvStatus;

    @VariableDescription("跳过")
    private UiObject2 skip;

    @VariableDescription("返回")
    private UiObject2 back;

    public Page_login() { }

    /**
     * 设置用户名
     * @throws Exception
     */
    public void setAccount(String account) throws Exception{
        Account = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPhone_login"));
        Account.setText(account);
        LogUtil.d(TAG,"设置"+Page_login.class.getDeclaredField("Account")
                .getAnnotation(VariableDescription.class).value()+"值："+account);
        time(delayTimeMs);
    }

    /**
     * 清空用户名
     * @throws Exception
     */
    public void clearAccount() throws Exception{
        Account = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPhone_login"));
        Account.clear();
        LogUtil.d(TAG,"清空"+Page_login.class.getDeclaredField("Account")
                .getAnnotation(VariableDescription.class).value()+"内容");
        time(delayTimeMs);
    }

    /**
     * 设置密码
     * @throws Exception
     */
    public void setPwd(String pwd) throws Exception{
        Pwd = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPwd_login"));
        Pwd.setText(pwd);
        LogUtil.d(TAG,"设置"+Page_login.class.getDeclaredField("Pwd")
                .getAnnotation(VariableDescription.class).value()+"值："+pwd);
        time(delayTimeMs);
    }

    /**
     * 清空密码
     * @throws Exception
     */
    public void clearPwd() throws Exception{
        Pwd = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPwd_login"));
        Pwd.clear();
        LogUtil.d(TAG,"清空"+Page_login.class.getDeclaredField("Pwd")
                .getAnnotation(VariableDescription.class).value()+"内容");
        time(delayTimeMs);
    }

    /**
     * 点击登录按钮
     * @throws Exception
     */
    public void clickLogin() throws Exception{
        Login = WaitForExists(By.res(TAG_PACKAGEPAD, "login"));
        Login.click();
        LogUtil.d(TAG,"点击"+Page_login.class.getDeclaredField("Login")
                .getAnnotation(VariableDescription.class).value()+"成功");
        time(delayTimeMs);
    }

    /**
     * 点击二维码按钮
     * @throws Exception
     */
    public void clickScan() throws Exception{
        tvScan = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_scan"));
        tvScan.click();
        LogUtil.d(TAG,"点击"+Page_login.class.getDeclaredField("tvScan")
                .getAnnotation(VariableDescription.class).value()+"成功");
        time(delayTimeMs);
    }

    /**
     * 点击手机登录按钮
     * @throws Exception
     */
    public void clickTvLogin() throws Exception{
        tvStatus = WaitForExists(By.res(TAG_PACKAGEPAD, "tv_login_status"));
        tvStatus.click();
        LogUtil.d(TAG,"点击"+Page_login.class.getDeclaredField("tvScan")
                .getAnnotation(VariableDescription.class).value()+"成功");
        time(delayTimeMs);
    }

    /**
     * 点击返回按钮
     * @throws Exception
     */
    public void clickBack() throws Exception{
        back = WaitForExists(By.res(TAG_PACKAGEPAD, "main_back"));
        back.click();
        LogUtil.d(TAG,"点击"+Page_login.class.getDeclaredField("back")
                .getAnnotation(VariableDescription.class).value()+"成功");
        time(delayTimeMs);
    }

    /**
     * 点击跳过按钮
     * @throws Exception
     */
    public void clickSkip() throws Exception{
        skip = WaitForExists(By.res(TAG_PACKAGEPAD, "skip"));
        skip.click();
        LogUtil.d(TAG,"点击"+Page_login.class.getDeclaredField("skip")
                .getAnnotation(VariableDescription.class).value()+"成功");
        time(delayTimeMs);
    }

    //检查二维码是否存在
    public Boolean checkEWM() throws ParameterException {
        return WaitForExists(By.res(TAG_PACKAGEPAD, "scan_show")) != null;
    }

}

