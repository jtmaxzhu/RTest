package lib.pictool;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.graphics.Rect;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.Contacts;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;


import lib.ComUtil;
import lib.LogUtil;
import lib.perftool.CmdTools;
import lib.perftool.SPService;
import page.rokipad.Page_smart;
import robam.rtest.MyApplication;


import static base.rokipad.BaseTest.processAdbPermission;
import static config.Config.TAG_PACKAGE;
import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static framework.picUtil.Ocr.checkFile;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.WaitForExistsList;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.time;

/**
 * Created by Administrator on 2018/7/25.
 * 该工具类是用来截取图片对比源文件，存放与手机SD卡对应路径中，路径在PicPath中，采用junit，直接运行对应Test即可
 */
@RunWith(AndroidJUnit4.class)
public class PicTool  {
    public static ComUtil comUtil = ComUtil.getInstance();
    public static UiDevice mDevice = ComUtil.getDevice();

    private static final String datapath =
            Environment.getExternalStorageDirectory() +
                    File.separator;//训练数据文件夹

    private static final String picsourcepath =
            Environment.getExternalStorageDirectory() +
                    File.separator + "temp" + File.separator + "source" + File.separator;//图片文件夹

    private static final String pictarpath =
            Environment.getExternalStorageDirectory() +
                    File.separator + "temp" + File.separator + "target" + File.separator;//图片文件夹

    /**
     */
    private static Boolean checkFilePic(File source, File target) throws Exception {
        //如果目前不存在则创建，然后在判断训练数据文件是否存在
        if (!source.exists() && source.mkdirs() ||!target.exists() && target.mkdirs()) {
            return true;
        }else{
            return  false;
        }

    }

    @Test
    public void pic() throws Exception {

        List<UiObject2> list = mDevice.findObjects(By.res(TAG_PACKAGE, "iv_model_img"));

        Rect rect = list.get(0).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.fry_close, rect);
        list.get(0).click();
        comUtil.time(1000);
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.fry_open, rect);

        Rect rect1 = list.get(1).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.decoct_close, rect1);
        list.get(1).click();
        comUtil.time(1000);
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.decoct_open, rect1);

        Rect rect2 = list.get(2).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.stew_close, rect2);
        list.get(2).click();
        comUtil.time(1000);
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.stew_open, rect2);

    }

    @Test
    public void pic2() throws Exception {
        checkFilePic(new File(picsourcepath), new File(pictarpath));
        List<UiObject2> list = mDevice.findObjects(By.res(TAG_PACKAGE, "img_recipe"));

        Rect rect = list.get(0).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPicBin(PicPath.temp, PicPath.test1, rect);




        Rect rect1 = list.get(1).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPicBin(PicPath.temp, PicPath.test2, rect1);


        Rect rect2 = list.get(2).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPicBin(PicPath.temp, PicPath.test3, rect2);


        Rect rect3 = list.get(3).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPicBin(PicPath.temp, PicPath.test4, rect3);


    }
    @Test
    public void pic3() throws Exception {
        checkFilePic(new File(picsourcepath), new File(pictarpath));
        List<UiObject2> list = mDevice.findObjects(By.res(TAG_PACKAGE, "img_recipe"));

        Rect rect = list.get(0).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.test5, rect);



        Rect rect1 = list.get(1).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.test6, rect1);


        Rect rect2 = list.get(2).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.test7, rect2);


        Rect rect3 = list.get(3).getVisibleBounds();
        mDevice.takeScreenshot(new File(PicPath.temp));
        comUtil.processPic(PicPath.temp, PicPath.test8, rect3);


    }

    @Test
    public void pic1() throws Exception {
        checkFile(new File(datapath), "chi_sim");
        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.init(datapath, "chi_sim");
        baseApi.setImage(new File(PicPath.test1));
        String result = baseApi.getUTF8Text();
/*        baseApi.setImage(new File(PicPath.test3));
        String result1 = baseApi.getUTF8Text().replace(" ", "").toLowerCase();
        log.i("识别结果是：" + result1);
        baseApi.setImage(new File(PicPath.test2));
        String result2 = baseApi.getUTF8Text().replace(" ", "").toLowerCase();
        log.i("识别结果是：" + result2);
        baseApi.setImage(new File(PicPath.test1));
        String result3 = baseApi.getUTF8Text().replace(" ", "").toLowerCase();
        log.i("识别结果是：" + result3);*/
    }


    /**
     * 截取智能设定imageBUtton图片开关状态
     * @throws Exception
     */
    @Test
    public void pic4() throws Exception {
       /* long endTimeMillis;
        long offsetTimeMillis;
        long startTimeMillis;
        startTimeMillis = SystemClock.uptimeMillis();
        WaitForExists(By.res(TAG_PACKAGEPAD, "tv_login"));
        endTimeMillis = SystemClock.uptimeMillis();
        offsetTimeMillis = endTimeMillis-startTimeMillis;
        offsetTimeMillis = endTimeMillis-startTimeMillis;*/

        UiObject2 uiObject2 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_light"));
        UiObject2 uiObject3 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_small"));
        UiObject2 uiObject4 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_middle"));
        UiObject2 uiObject5 = WaitForExists(By.res(TAG_PACKAGEPAD, "fan_big"));
        Boolean open = isOpen(uiObject2);
        uiObject2.click();
        time(2000);
        Boolean open1 = isOpen(uiObject2);
        uiObject2.click();


        isOpen(uiObject3);
        uiObject3.click();
        time(2000);
        isOpen(uiObject3);
        uiObject3.click();

        isOpen(uiObject4);
        uiObject4.click();
        time(2000);
        isOpen(uiObject4);
        uiObject4.click();

        isOpen(uiObject5);
        uiObject5.click();
        time(2000);
        isOpen(uiObject5);
        uiObject5.click();




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
        String rgb = getRGB( (x1 + x2) / 2, (y1+y2)/2);
        LogUtil.d("123","位置:"+(x1 + x2) / 2+","+(y1+y2)/2);
        LogUtil.d("123","rgb:"+rgb);
        return !rgb.equals("ffffffff");
    }

    @Test
    public void pic6() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiAutomation uiAutomation = instrumentation.getUiAutomation();
//        SPService.init(MyApplication.getContext());
//        boolean b = processAdbPermission();
//        if(!b){
//            Log.i("123","网络调试连接失败，请检查是否开启5555端口");
//        }else {
//            Log.i("123","网络调试连接成功");
//        }
//        /*while (true){
//            final String adb_devices = UiDevice.getInstance(instrumentation).executeShellCommand("ps -a ");
//            LogUtil.d("123","adb_devices---"+adb_devices);
//            time(1000);
//            String adb_devices1 = CmdTools.execAdbCmd("ps | grep rokipad", 0);
//            LogUtil.d("123","adb_devices1---"+adb_devices1);
//        }*/
//        CmdTools.execAdbCmd("pm clear c", 0);
    }





}
