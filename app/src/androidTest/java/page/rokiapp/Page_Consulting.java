package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;

import java.util.List;

import framework.ParameterException;
import lib.ComUtil;

import static config.Config.TAG_PACKAGE;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class Page_Consulting {
    public static ComUtil comUtil = ComUtil.getInstance();
    UiDevice mDevice = comUtil.getDevice();
    /**
     *  定义ConsultingActivity控件变量名称以及对应的文字标签
     *  EdtSend    ：  留言输入框
     *  TxtSend    ：  "发送"
     */

    private UiObject2 EdtSend;
    private UiObject2 TxtSend;


    public Page_Consulting() throws ParameterException, InterruptedException, UiObjectNotFoundException {
        // 初始化控件对象
        EdtSend  =   WaitForExists(By.res(TAG_PACKAGE, "edtSend"));
        TxtSend  =   WaitForExists(By.res(TAG_PACKAGE, "txtSend"));
    }

    /**
     * 输入编辑内容
     * @param  str   要发送的内容
     * @throws Exception
     */
    public void setMsg(String str) throws Exception{
        EdtSend.setText(str);
        time(delayTimeMs);
    }

    /**
     * 点击-发送-按钮
     * @throws Exception
     */
    public void sendMsg() throws Exception{
        TxtSend.click();
        time(delayTimeMs);
    }

    /**
     * 获取最新发送的一句留言咨询
     * @throws Exception
     * @return msg 获取的文本
     */
    public String getLatestMsg() throws Exception{
        List<UiObject2> ui =  mDevice.findObjects(By.res(TAG_PACKAGE, "txtMsg"));
        String msg =  ui.get(ui.size()-1).getText();
        return msg;
    }







}
