package lib.perftool;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.HashMap;
import java.util.Map;

/**
 * author : liuxiaohu
 * date   : 2019/8/9 11:19
 * desc   :
 * version: 1.0
 */
public class SPService {
    private static final String COMMON_CONFIG = "CommonConfig";

    public static final String KEY_HIDE_LOG = "KEY_HIDE_LOG";

    public static final String PERMISSION_GRANT_ADB = "grantAdb";

    private static SharedPreferences preferences;

    private static Map<String, Object> CACHED_PARAMS = new HashMap<>();

    public static void init(Context context) {
        preferences = context.getSharedPreferences(COMMON_CONFIG, Context.MODE_PRIVATE);
    }

    /**
     * 存储String
     * @param key
     * @param content
     */
    public static void putString(String key, String content) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, content).apply();
        CACHED_PARAMS.put(key, content);
    }

    /**
     * 获取String，默认为空字符
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获取String
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        if (CACHED_PARAMS.containsKey(key)) {
            return (String) CACHED_PARAMS.get(key);
        }
        String content = preferences.getString(key, defaultValue);
        CACHED_PARAMS.put(key, content);
        return content;
    }

    /**
     * 存储Boolean值
     * @param key
     * @param content
     */
    public static void putBoolean(String key, boolean content) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, content).apply();
        CACHED_PARAMS.put(key, content);
    }

    /**
     * 获取Boolean值
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        if (CACHED_PARAMS.containsKey(key)) {
            return (boolean) CACHED_PARAMS.get(key);
        }

        boolean value = preferences.getBoolean(key, defaultValue);
        CACHED_PARAMS.put(key, value);
        return value;
    }

    /**
     * 存储long值
     * @param key
     * @param value
     */
    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value).apply();
        CACHED_PARAMS.put(key, value);
    }

    /**
     * 获取long值
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String key, long defValue) {
        if (CACHED_PARAMS.containsKey(key)) {
            return (long) CACHED_PARAMS.get(key);
        }

        // 缓存下
        long value = preferences.getLong(key, defValue);
        CACHED_PARAMS.put(key, value);
        return value;
    }

    /**
     * 存储int值
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value).apply();
        CACHED_PARAMS.put(key, value);
    }

    /**
     * 获取int值
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String key, int defValue) {
        if (CACHED_PARAMS.containsKey(key)) {
            return (int) CACHED_PARAMS.get(key);
        }

        // 缓存
        int result = preferences.getInt(key, defValue);
        CACHED_PARAMS.put(key, result);
        return result;
    }



}
