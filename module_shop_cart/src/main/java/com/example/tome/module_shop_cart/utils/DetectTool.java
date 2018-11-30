package com.example.tome.module_shop_cart.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import com.example.tome.core.util.L;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author liubp
 * @function 检测工具类
 */
public class DetectTool {
    static TelephonyManager tm;

    /**
     * 检测当前网络状态
     *
     * @param act 上下文对象
     * @return
     */
    public static boolean getNetState(Activity act) {
        ConnectivityManager manage = (ConnectivityManager) act.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == manage) {
            return false;
        }

        NetworkInfo networkinfo = manage.getActiveNetworkInfo();

        if (null == networkinfo || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }


    public static int getVersionSdk() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取版本名称(非版本号versoinCode)
     *
     * @param act 上下文对象
     * @return
     */
    public static String getVersionCode(Activity act) {

        PackageManager manager = (PackageManager) act.getPackageManager();

        PackageInfo packageInfo = null;

        try {
            packageInfo = manager.getPackageInfo(act.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (null != packageInfo) {
            return packageInfo.versionName;
        }

        return null;

    }

    /**
     * 获得一个UUID 16位
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTS() {
//        return "1489399741834";
        return System.currentTimeMillis() + "";
    }

    /**
     * 获取手机唯一串号IMEI
     *
     * @return imei
     */
    public static String getIMEI() {
        String deviceId="";
//        if (CommonApplication.PHONE_STATE) {
//            if (null == tm) {
//                tm = (TelephonyManager) CommonApplication.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
//            }
//            deviceId = tm.getDeviceId();
//        } else {
//            deviceId = (String) SPUtil.get(CommonApplication.getApplication(), "deviceId", "");
//            if (TextUtils.isEmpty(deviceId)) {
//                deviceId = UUID.randomUUID().toString();
//                SPUtil.put(CommonApplication.getApplication(), "deviceId", deviceId);
//            }
//        }
        return deviceId;
    }


    public static String getSign(HashMap<String, String> params) {

        L.i("11111111111111111", params.toString());
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();


        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(chinaToUnicode(param.getValue()));
        }
        L.i("11111111111111111", basestring.toString());
        return getMD5String(basestring.toString());
    }

    @NonNull
    public static String getMD5String(String basestring) {
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(basestring.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        //返回md5加密后的字符串注意统一转化为大写
        return hex.toString().toUpperCase();
    }

    /**
     * 获取屏幕横向(宽度)分辨率
     *
     * @param context
     * @return
     */
    public static int getResolutionX(Context context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        return width;
    }


    /**
     * 获取屏幕纵向(高度)分辨率
     *
     * @param context
     * @return
     */
    public static int getResolutionY(Context context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);

        int height = mDisplayMetrics.heightPixels;

        Log.i("DetectTool", height + "");

        return height;
    }

    /**
     * 如果软键盘打开状态，隐藏软键盘。
     *
     * @param activity 上下文对象
     */
    public static void hideSoftInput(Activity activity) {
        InputMethodManager mm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (mm.isActive() && activity.getCurrentFocus() != null) {
            mm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取凭证(Token)
     *
     * @return
     */
    public static String getToken() {
        return "123";
    }

    /**
     * 写死的版本号，对应versionName
     *
     * @return
     */
    public static String getVersionName() {
        return "1.0.0";
    }

    /**
     * 获取应用类型
     *
     * @return
     */
    public static String getAppType() {
        return "0";
    }

    /**
     * 获取设备类型，1-Android，2-IOS
     *
     * @return
     */
    public static String getType() {
        return "1";
    }

    /**
     * 中文转Unicode编码
     *
     * @param str 字符串
     * @return 如果是中文则返回对应的Unicode编码字符串
     */
    private static String chinaToUnicode(String str) {
        String result = "";
        String regEx = "[^\\x00-\\xff]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.matches();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = str.charAt(i);
            if (rs) {
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

}
