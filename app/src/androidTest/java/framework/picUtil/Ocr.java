package framework.picUtil;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by Administrator on 2018/9/4.
 * 负责图片识别算法
 */

public class Ocr {
    private static final String datapath =
            Environment.getExternalStorageDirectory() +
                    File.separator + "tessdata" + File.separator ;//训练数据文件夹

    /**
     * @param dir
     * @param language chi_sim eng
     */
    public static void checkFile(File dir, String language) throws Exception{
        //如果目前不存在则创建，然后在判断训练数据文件是否存在
        if (!dir.exists() && dir.mkdirs()) {
            copyFiles(language);
        }
        if (dir.exists()) {
            String datafilepath = datapath  + language + ".traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles(language);
            }
        }

    }


    /**
     把训练数据放到手机SD卡内存
     * @param language "chi_sim" ,"eng"
     */
    private static void copyFiles(String language) throws Exception {
        Context mContext;
        mContext = getTargetContext();
        try {
            String filepath = datapath + language + ".traineddata";
            AssetManager assetManager = mContext.getAssets();
            InputStream instream = assetManager.open(language + ".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String process() throws Exception{
        TessBaseAPI baseApi;
        baseApi = new TessBaseAPI();
        baseApi.init(datapath, "chi_sim");
        baseApi.setImage(new File("/sdcard/temp/target/StewStatus.png"));
        String result = baseApi.getUTF8Text().replace(" ", "").toLowerCase();
        return result;
    }


}
