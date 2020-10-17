package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import base.BasePage;
import framework.ParameterException;
import lib.LogUtil;
import page.rokipad.Page_login;

import static config.Config.TAG_PACKAGE;
import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_Login extends BasePage{
    private static String TAG = Page_login.class.getSimpleName();
    UiDevice uiDevice;
    /**
     *  定义LoginActivity控件变量名称以及对应的文字标签
     *  Account        ：  "用户名"
     *  Pwd            ：  "密码"
     *  Login          ：  "登录"
     *  Regist         ：  "注册"
     *  RetrievePwd    ：  "忘记密码"
     *  ExpressLogin   ：  "快捷登录"
     */
    private UiObject2 Account;
    private UiObject2 Pwd;
    private UiObject2 Login;
    private UiObject2 Regist;
    private UiObject2 RetrievePwd;
    private UiObject2 ExpressLogin;

    public Page_Login() throws ParameterException, InterruptedException, UiObjectNotFoundException {
        // 初始化控件对象
        Account      =   WaitForExists(By.res(TAG_PACKAGE, "edtAccount"));
        Pwd          =   WaitForExists(By.res(TAG_PACKAGE, "edtPwd"));
        Login        =   WaitForExists(By.res(TAG_PACKAGE, "txtLogin"));
        Regist       =   WaitForExists(By.res(TAG_PACKAGE, "txtRegist"));
        RetrievePwd  =   WaitForExists(By.res(TAG_PACKAGE, "txtRetrievePwd"));
        ExpressLogin =   WaitForExists(By.res(TAG_PACKAGE, "txtExpressLogin"));
    }

    /**
     * 设置用户名
     * @throws Exception
     */
    public void setAccount(String account) throws Exception{
        if (Account == null){
            Account = WaitForExists(By.res(TAG_PACKAGEPAD, "edtAccount"));
        }
        Account.setText(account);
        LogUtil.d("myTest","清空"+Account.getText()+"成功");
        time(delayTimeMs);
    }

    /**
     * 清空用户名
     * @throws Exception
     */
    public void clearAccount() throws Exception{
        if (Account == null){
            Account = WaitForExists(By.res(TAG_PACKAGEPAD, "edtAccount"));
        }
        Account.clear();
        LogUtil.d(TAG,"清空"+Account.getText()+"成功");
        time(delayTimeMs);
    }

    /**
     * 设置密码
     * @throws Exception
     */
    public void setPwd(String pwd) throws Exception{
        if (Pwd == null){
            Pwd = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPwd"));
        }
        Pwd.setText(pwd);
        LogUtil.d(TAG,"设置"+Pwd.getText()+"成功");
        time(delayTimeMs);
    }

    /**
     * 清空密码
     * @throws Exception
     */
    public void clearPwd() throws Exception{
        if (Pwd == null){
            Pwd = WaitForExists(By.res(TAG_PACKAGEPAD, "edtPwd"));
        }
        Pwd.clear();
        LogUtil.d(TAG,"清空"+Pwd.getText()+"成功");
        time(delayTimeMs);
    }

    /**
     * 点击-登录-按钮
     * @throws Exception
     */
    public void clickLogin() throws Exception{
        if (Login == null){
            Login = WaitForExists(By.res(TAG_PACKAGEPAD, "txtLogin"));
        }
        Login.click();
        LogUtil.d(TAG,"点击"+Login.getText()+"成功");
        time(delayTimeMs);
    }

}

