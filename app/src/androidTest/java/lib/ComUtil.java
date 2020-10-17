package lib;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import framework.ParameterException;
import framework.picUtil.PicBin;
import framework.picUtil.PicCompareUtil;
import lib.pictool.PicPath;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;



/**
 * Created by Administrator on 2018/7/21.
 */

public class ComUtil {
    private static String TAG = ComUtil.class.getSimpleName();
    private static int waitTime = 10 * 1000;

    public static UiDevice mDevice = getDevice();

    private static volatile ComUtil instance;

    public static ComUtil getInstance(){
        if (instance == null ){
            synchronized (ComUtil.class){
                if(instance == null){
                    instance = new ComUtil();
                }
            }
        }
        return instance;
    }

    public static UiDevice getDevice() {
        return UiDevice.getInstance(getInstrumentation());
    }


    public static void grantPermission(UiDevice uiDevice) throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            uiDevice.executeShellCommand("pm grant " + getTargetContext().getPackageName() + " android.permission.WRITE_EXTERNAL_STORAGE");
            uiDevice.executeShellCommand("pm grant " + getTargetContext().getPackageName() + " android.permission.READ_EXTERNAL_STORAGE");
        }
    }


    //清除app的数据和缓存
    public static void clearData(String string) {
        try {
            mDevice.executeShellCommand("pm clear " + string);
            LogUtil.d(TAG, "清除app的数据和缓存成功");
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "清除app的数据和缓存失败");
        }

    }

    //关闭APP
    public void closeAPP(UiDevice uiDevice, String sPackageName) throws IOException {
        uiDevice.executeShellCommand("am force-stop " + sPackageName);//通过命令行关闭app
        LogUtil.d(TAG, "清除app的数据和缓存失败");
    }

    //获取控件地址
    public static UiObject2 WaitForExists(BySelector bySelector){
/*        UiObject2 obj;
        long endTimeMillis;
        long offsetTimeMillis;
        long startTimeMillis;
        startTimeMillis = SystemClock.uptimeMillis();
        while (true) {
            obj = mDevice.findObject(bySelector);
            endTimeMillis = SystemClock.uptimeMillis();
            offsetTimeMillis = endTimeMillis-startTimeMillis;
            if (obj != null) {
                break;
            }
            if(offsetTimeMillis >6000){
                throw new ParameterException("控件不存在");
            }
        }
        return  obj;*/
        return mDevice.wait(Until.findObject(bySelector), waitTime);
    }

    //获取控件地址
    public static List<UiObject2> WaitForExistsList(BySelector bySelector){
        return mDevice.wait(Until.findObjects(bySelector), waitTime);
    }

    /**
     * bounds坐标，使用坐标进行点击
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public static void ClickElement(int x1, int y1, int x2, int y2){
        mDevice.click((x1 + x2) / 2,(y1 + y2) / 2);
    }



    /**
     * 获取当前界面中指定坐标点的RGB值，结果以16进制输出（例：ffffff）
     * @param x       像素点的横坐标值
     * @param y       像素点的纵坐标值
     * @return 16进制的RGB值，以String类型返回。例：ffffff
     * @author ZeKyll
     */
    public static String getRGB(int x, int y) throws IOException {
        File file = new File(PicPath.path);
        if (!file.exists()){
            file.mkdir();
        }
        mDevice.takeScreenshot(new File(PicPath.temp));
        FileInputStream fis = new FileInputStream(PicPath.temp);
        Bitmap bitmap  = BitmapFactory.decodeStream(fis);
        int color = bitmap.getPixel(x, y);//获取坐标点像素颜色
        String A = Integer.toHexString(Color.alpha(color));
        String R = Integer.toHexString(Color.red(color));
        String G = Integer.toHexString(Color.green(color));
        String B = Integer.toHexString(Color.blue(color));
        return A + R + G + B;
    }



    /*
        截取控件区域截图，传入Rect对象
     */
    public void processPic(String path1, String path2, Rect rect)throws IOException {
        Bitmap sub1 = PicCompareUtil.getSubImage(path1, rect.left, rect.top, rect.right-rect.left, rect.bottom-rect.top);
        PicCompareUtil.saveBitmapFile(sub1, path2);
    }

    /*
        截取控件区域截图，进行二值化、锐化处理，图片用于图像识别
     */
    public void processPicBin(String path1, String path2, Rect rect)throws IOException {
        Bitmap sub1 = PicCompareUtil.getSubImage(path1, rect.left, rect.top, rect.right-rect.left, rect.bottom-rect.top);
        Bitmap bitmap = PicBin.convertToBlackWhite(sub1);
        PicCompareUtil.saveBitmapFile( PicBin.binProcess(bitmap), path2);

    }



    //第一种启动方式
    public void startAPP(UiDevice uiDevice, String Activity) throws IOException {
        uiDevice.executeShellCommand("am start " + Activity);//通过命令行开启app
    }

    //第二种启动方式，8.0好像有时候无效，建议采用第一种方式
    protected void startAPP1 (UiDevice mDevice,String TAG_PACKAGE) throws IOException {
        Context mContext;
        mContext = getTargetContext();
        Intent myIntent = mContext.getPackageManager().getLaunchIntentForPackage(TAG_PACKAGE);  //启动app
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        mContext.startActivity(myIntent);
        mDevice.wait(Until.hasObject(By.pkg(TAG_PACKAGE).depth(0)), 10000);
    }

    public void checkPic(String path1, String path2)throws IOException{
        Assert.assertTrue(PicCompareUtil.sameAs(path1, path2,0.99));
    }

    public void processPic(String path1, String path2)throws IOException{
        Bitmap sub1 = PicCompareUtil.getSubImage(path1, 0, 200, 1080, 1720);
        PicCompareUtil.saveBitmapFile(sub1, path2);
    }


    public static void time(int time)throws InterruptedException{
        Thread.sleep(time);
    }



    /**
     * 获取当前日期是星期几<br>
     *
     * @param
     * @return 当前日期是星期几
     */
    public String getCurrentWeek(){
        SimpleDateFormat format;
        //获取系统当前时间，星期
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        //获取星期
        format=new SimpleDateFormat("E");
        return format.format(date);
    }

    //获取当前时间小时
    public String getCurrentHour(){
        SimpleDateFormat format;
        String times;
        //获取系统当前时间，星期
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        format=new SimpleDateFormat("HH:mm:ss");
        times = format.format(date);
        String[] str = times.split(":");
        return str[0];
    }

    //获取当前时间分钟
    public String getCurrentMin(){
        SimpleDateFormat format;
        String times;
        //获取系统当前时间，星期
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        format=new SimpleDateFormat("HH:mm:ss");
        times = format.format(date);
        String[] str = times.split(":");
        return str[1];
    }

    //考虑到结果验证，定时通风时间设定为系统当前时间之后的2分钟，否则来不及进行验证
    public String SetTestTime() throws ParseException {
        SimpleDateFormat format;
        String times;
        //获取系统当前时间，星期
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        format=new SimpleDateFormat("HH:mm:ss");
        times = format.format(date);
        Date date1 = format.parse(times);//抛异常

        //然后使用Calendar操作日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.MINUTE,2);//对小时数进行+2操作,同理,减2为-2

        //把得到的日期格式化成字符串类型的时间
        String finaldate = format.format(calendar.getTime());
        Log.i("time","finaldate:"+finaldate);
        return finaldate;

    }
}
