package lib.perftool;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import robam.rtest.MyApplication;

import static config.Config.TAG_PACKAGE;

/**
 * author : liuxiaohu
 * date   : 2020/7/13 14:18
 * desc   :
 * version: 1.0
 */
public class PerfUtil {

    private static long currentJiffies = 0;
    private static long lastJiffies = 0;
    private static long currentIdle = 0;
    private static long lastIdle = 0;
    private static long lastTime = 0;
    private static SparseArray<Long> appProcessTime = new SparseArray<Long>();

    private static Long totalMeory = null;
    private static ActivityManager activityManager;
    private static Context context;

    static {
        context =  MyApplication.getContext();
        activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }



    public static Float getMemInfo(){
        if (totalMeory == null){
            totalMeory = getTotalMemory();
        }
        long l = Runtime.getRuntime().maxMemory()/(1024*1024);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            String appLines;
            int pid = PerfUtil.getPid(TAG_PACKAGE);
            appLines = CmdTools.execAdbCmd("dumpsys meminfo "+ pid,0);
            String[] origin = appLines.split("\n");
            String[] meminfos = origin[23].split("\\s+");
            Log.i("ROKI",String.format("PrivateDirty:%.2fMB | PSS:%.2fMB"+ Float.parseFloat(meminfos[3]) / 1024f, Float.parseFloat(meminfos[2]) / 1024f));
            return  Float.parseFloat(meminfos[2]) / 1024f;
        }else {
            int pid = PerfUtil.getPid(TAG_PACKAGE);
            Debug.MemoryInfo[] memoryInfos = activityManager.getProcessMemoryInfo(new int[]{pid});
            Debug.MemoryInfo info = memoryInfos[0];
            Log.i("ROKI", String.format(Locale.CHINA,"PrivateDirty:%.2fMB | PSS:%.2fMB", info.getTotalPrivateDirty() / 1024f, info.getTotalPss() / 1024f));
            return  info.getTotalPss() / 1024f;
        }
        
    }

    /**
     * 获取设备总的内存数据
     * @return
     */
    private static Long getTotalMemory() {
        context = MyApplication.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        //1M=1024*1024 BYTE
        return info.totalMem/(1024*1024);
    }

    //获取应用可用内存的大小
    public static Long getAvailMemory() {
        context = MyApplication.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (context == null){
            return 0L;
        }
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        //1M=1024*1024 BYTE
        Log.i("ROKI","单个应用最大可用内存："+ info.availMem/(1024*1024) + " MB");
        return info.availMem/(1024*1024);
    }




    /**
     * 获取应用CPU数据
     */
    public static float getCpuInfo(){
        int pid = PerfUtil.getPid(TAG_PACKAGE);
        float[] pidUsage = PerfUtil.getPidUsage(pid);
        return  pidUsage[0];
    }

    /**
     * 获取应用pid
     * @return
     */
    public static int getPid(String appName){
        int mPID = 0;
        String content = "";
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            content = CmdTools.execAdbCmd("ps | grep "+ appName,1000);
        }else {
            content = CmdTools.execAdbCmd("ps -A | grep "+ appName,1000);
        }
        String[] arr = content.split("\\s+");
        mPID = Integer.parseInt(arr[1]);
        return mPID;
    }

    /**
     * 获取CPU占用率 系统的和应用的
     * @param pid
     * @return
     */

    public static float[] getPidUsage(int pid){
        String content;
        Long time;
        String[] cpuInfos;
        /**
         * /proc/stat CPU总时间为： cpu  569146 66246 275756 15100026 11684 81 11376 0 0 0
         * 第5位为Idle时间
         * 第2-8位之和为CPU总耗时
         * 占用率为(sum - Idel) / sum
         */
        StringBuilder cmd = new StringBuilder("grep \"cpu \" /proc/stat && cat ").append("/proc/").append(pid).append("/stat ");
        time = System.currentTimeMillis();
        content = CmdTools.execAdbCmd(cmd.toString(),0);
        /**
         *content 内容如下
         * cpu  338370 3356 512347 4977655 2140 0 4489 0 0 0
         * cpu0 329640 2448 502227 4820310 1094 0 4462 0 0 0
         * root@A6802N_HLB5916S:/ # grep cpu /proc/stat && cat /proc/1241/stat
         * cpu  338555 3356 512663 4980237 2140 0 4490 0 0 0
         * cpu0 329825 2448 502543 4822892 1094 0 4463 0 0 0
         * 1241 (m.robam.rokipad) S 177 177 0 0 -1 1077936448 259198 0 240 0 10285 4438 0 0 20 0 50 0 1671 1084399616 18590 4294967295 3069435904 3069444095 3198425600 3198422848 3069085556 0 37380 0 38136 4294967295 0 0 17 0 0 0 0 0 0 3069447560 3069448192 3069616128 3198425871 3198425947 3198425947 3198427108 0
         */
        String[] origin = content.split("\n");
        String load = origin[0];
        /**
         * 整体CPU数据处理
         */
        cpuInfos = load.split("\\s+");
        currentJiffies = Long.parseLong(cpuInfos[1]) + Long.parseLong(cpuInfos[2]) + Long.parseLong(cpuInfos[3])
                + Long.parseLong(cpuInfos[4]) + Long.parseLong(cpuInfos[5]) + Long.parseLong(cpuInfos[6])
                + Long.parseLong(cpuInfos[7]);
        Long cpuRunning = currentJiffies - lastJiffies;
        currentIdle = Long.parseLong(cpuInfos[4]);
        long gapIdle = currentIdle - lastIdle;
        float totalUsage = 100 * (cpuRunning - gapIdle) / (float) cpuRunning;
        // 数据异常
        if (gapIdle < 0 | cpuRunning < 0 | lastTime <= 0) {
            lastIdle = currentIdle;
            lastJiffies = currentJiffies;
            lastTime = time;
            //Log.e("ROKI", "数据异常");
            return new float[0];
        }
        lastIdle = currentIdle;
        lastJiffies = currentJiffies;
        lastTime = time;

        /**
         * 应用CPU处理
         * /proc/<b>pid</b>/stat 应用占用情况
         * 2265 (id.XXX) S 610 609 0 0 -1 1077952832 130896 1460 185 0 683 329 3 10 14 -6 63 0 1982194 2124587008 28421 18446744073709551615 1 1 0 0 0 0 4612 0 1073798392 18446744073709551615 0 0 17 3 0 0 0 0 0 0 0 0 0 0 0 0 0
         * 第14-17位之和为应用占用CPU时间之和
         */
        SparseArray<Float> appResult = new SparseArray<>(1 + 1);

        // 第一行是全局cpu数据
        String[] splitLines = new String[origin.length - 1];
        System.arraycopy(origin, 1, splitLines, 0, origin.length - 1);
        // 处理每行获取到的数据
        SparseArray<Long> newAppProcessTime = new SparseArray<>(appProcessTime.size() + 1);
        for (String line: splitLines) {
            String[] processInfos = line.trim().split("\\s+");
            //Log.d("ROKI", Arrays.toString(processInfos));
            // 获取失败的状态
            if (processInfos.length < 17) {
                continue;
            }

            try {
                Long pidProcessTime = Long.parseLong(processInfos[13]) + Long.parseLong(processInfos[14]) + Long.parseLong(processInfos[15]) + Long.parseLong(processInfos[16]);

                Long lastProcessTime = appProcessTime.get(pid);
                newAppProcessTime.put(pid, pidProcessTime);

                // 如果没有上次记录，则跳过
                if (lastProcessTime == null) {
                    continue;
                }

                // 计算APP进程处理时间
                Long processRunning = pidProcessTime - lastProcessTime;
                appResult.put(pid, 100 * (processRunning / (float) cpuRunning));
            } catch (NumberFormatException e) {
                Log.e("ROKI", "Format for string: " + line + " failed", e);
            }
        }
        appProcessTime.clear();
        appProcessTime = newAppProcessTime;
        // 整合计算结果
        float[] result = new float[1 + 1];
        Float pidData = appResult.get(pid);
        // 如果找不到，说明数据不存在
        if (pidData != null) {
            result[0] = pidData;
        } else {
            result[0] = 0F;
        }
        result[1] = totalUsage;
        if (totalUsage < 0){
            System.out.println("111");
        }
        return result;
    }

    public static File DataInit(String source) throws IOException {
        File mFile = new File(Environment.getExternalStorageDirectory().getPath() + "/fileName");

        if (!mFile.exists())
        {
            mFile.mkdir();
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(System.currentTimeMillis());
        File saveFile = new File(mFile,dateStr+"_"+source+"_data.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile,true));
        writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF}));
        writer.write("数据来源,"+ source+ ","+"\n");
        writer.write("时间,"+ "CPU(%)," + "MEM,"+"\n");
        writer.flush();
        writer.close();
        return saveFile;
    }



    public static void DataWrite(File saveFile, Map<String, List<String>> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile,true));
        writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF}));
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String time =  entry.getKey();
            writer.write(time + ",");
            List<String> list = entry.getValue();
            for (String data:list){
                writer.write(data + ",");
            }
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }





}
