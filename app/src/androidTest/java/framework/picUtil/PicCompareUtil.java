package framework.picUtil;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.test.uiautomator.UiDevice;

import lib.ComUtil;
import lib.pictool.PicPath;

import static config.Config.TAG_PACKAGE;


public class PicCompareUtil {
  
  public static boolean sameAs (String path1, String path2) throws FileNotFoundException {
    boolean res = false;
    FileInputStream fis1 = new FileInputStream(path1);
    Bitmap bitmap1  = BitmapFactory.decodeStream(fis1);
    
    FileInputStream fis2 = new FileInputStream(path2);
    Bitmap bitmap2  = BitmapFactory.decodeStream(fis2);
    
    res = sameAs(bitmap1,bitmap2);
  
    return res;
      
  }
  
  public static boolean sameAs (String path1, String path2,double percent) throws FileNotFoundException {
    FileInputStream fis1 = new FileInputStream(path1);
    Bitmap bitmap1  = BitmapFactory.decodeStream(fis1);
    
    FileInputStream fis2 = new FileInputStream(path2);
    Bitmap bitmap2  = BitmapFactory.decodeStream(fis2);
    
    return sameAs(bitmap1,bitmap2,percent);
      
  }
  
  public static boolean sameAs (Bitmap bitmap1, Bitmap bitmap2, double percent) {
    if(bitmap1.getHeight() != bitmap2.getHeight())
      return false;
    
    System.out.print(" \n" +bitmap1.getHeight());
    System.out.print(" \n" +bitmap2.getHeight());
    
    if(bitmap1.getWidth() != bitmap2.getWidth())
      return false;
    
    System.out.print(" \n" +bitmap1.getWidth());
    System.out.print(" \n" +bitmap2.getWidth());
    
    
    if(bitmap1.getConfig() != bitmap2.getConfig())
      return false;
    
    System.out.print(" \n" +bitmap1.getConfig());
    System.out.print(" \n" +bitmap2.getConfig());

    int width = bitmap1.getWidth();
    int height = bitmap2.getHeight();

    int numDiffPixels = 0;
       
       for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {
           if (bitmap1.getPixel(x, y) != bitmap2.getPixel(x, y)) {
             numDiffPixels++;
           }
         }
       }
       double numberPixels = height * width;
       double diffPercent = numDiffPixels / numberPixels;
       System.out.print("\n"+diffPercent);
       System.out.print("\n"+numDiffPixels);
       System.out.print("\n"+numberPixels);
       return percent <= 1.0D - diffPercent;
  }
  
  public static boolean sameAs (Bitmap bmp1, Bitmap bmp2) throws FileNotFoundException {
    boolean res = false;
    
    res = bmp1.sameAs(bmp2);
   // System.out.print(" \n" +res);
    return res;		
  }
  
  public static Bitmap getSubImage(String path,int x,int y,int width,int height) throws FileNotFoundException {
    
    FileInputStream fis = new FileInputStream(path);
    Bitmap bitmap  = BitmapFactory.decodeStream(fis);
        
    Bitmap res = Bitmap.createBitmap(bitmap, x, y, width, height);
    
    return res;
    
  }
  
  public  static void saveBitmapFile(Bitmap bitmap, String path){
      File file=new File(path);//将要保存图片的路径
      try {
              BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
              bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
              bos.flush();
              bos.close();
      } catch (IOException e) {
              e.printStackTrace();
      }
  }






}

