package com.example.tome.core.constants;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;


/**
 * 类描述    :管理所有Activity
 * 包名      : com.fecmobile.jiubeirobot.base
 * 类名称    : ActivityControl
 * 创建人    : ghy
 * 创建时间  : 2017/2/21 19:54
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public class ActivityControl {
    private Set<Activity> allActivities = new HashSet<>();
    private WeakReference<Activity> currentAtivity;

    /**
     * 描述      :  设置当前运行的Activity。
     * 方法名    :  setCurrentAtivity
     * param    :  currentAtivity 设置当前运行的Activity
     * 返回类型  :  void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:55
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void setCurrentAtivity(Activity currentAtivity) {
        this.currentAtivity = new WeakReference<>(currentAtivity);
    }

    /**
     * 描述      :  获取当前运行的Activity,有可能返回null
     * 方法名    :  getCurrentAtivity
     * param    : 无
     * 返回类型  : BaseActivity
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:56
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public Activity getCurrentAtivity() {
        return currentAtivity.get();
    }

    /**
     * 描述      : 添加Activity到管理
     * 方法名    :  addActivity
     * param    :   act Activity
     * 返回类型  : void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:57
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 描述      :从管理器移除Activity，一般在Ondestroy移除，防止强引用内存泄漏
     * 方法名    :  removeActivity
     * param    :   act Activity
     * 返回类型  : void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:57
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }


    /**
     * 描述      :关闭所有Activity
     * 方法名    :  finishiAll
     * param    :无
     * 返回类型  :void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:58
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void finishiAll() {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                act.finish();
            }
        }
    }


    /**
     * 描述      :关闭所有Activity 除了对应的activity
     * 方法名    :  finishiAll
     * param    :无
     * 返回类型  :void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 19:58
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void finishiAllExcept(Activity activity) {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                if (act!=activity){
                    act.finish();
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishiAll();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
