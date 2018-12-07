package com.example.tome.core.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.tome.core.constants.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author by TOME .
 * @data on      2018/6/25 11:12
 * @describe ${App相关工具类}
 */

public class AppUtils {


    /**
     * 获取 App 包名
     *
     * @return the application's package name
     */
    public static String getAppPackageName() {
        return BaseApplication.getAppContext().getPackageName();
    }


    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verName;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verCode;
    }


    /**
     * 对比本地与线上的版本号
     * */
    public static boolean needUpdateV2(String local, String online) {


        boolean need = false;

        if (local != null && online != null) {

            String[] onlines = online.split("\\.");
            L.e(Arrays.toString(onlines));
            String[] locals = local.split("\\.");
            L.e(Arrays.toString(locals));

            // 2.0.0-1.0.0
            // 2.1.0-2.0.0
            // 2.1.1-2.1.0


            //3.0.0-4.0.0
            //3.1.0-3.2.0
            //3.1.1-3.2.0

            if (Integer.parseInt(onlines[0]) > Integer.parseInt(locals[0])) {
                need = true;
//                Logger.e("1-1");

            } else if (Integer.parseInt(onlines[0]) == Integer.parseInt(locals[0])) {
//                Logger.e("1-2");
                if (Integer.parseInt(onlines[1]) > Integer.parseInt(locals[1])) {
                    need = true;
//                    Logger.e("1-2-1");

                } else if (Integer.parseInt(onlines[1]) == Integer.parseInt(locals[1])) {
//                    Logger.e("1-2-2");
                    if (Integer.parseInt(onlines[2]) > Integer.parseInt(locals[2])) {
//                        Logger.e("1-2-2-1");
                        need = true;
                    } else {
//                        Logger.e("1-2-2-2");
                        need = false;
                    }

                } else if (Integer.parseInt(onlines[1]) < Integer.parseInt(locals[1])) {
                    need = false;
//                    Logger.e("1-2-3");
                }


            } else if (Integer.parseInt(onlines[0]) < Integer.parseInt(locals[0])) {
                need = false;
//                Logger.e("1-3");
            }

        }
        return need;


    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
