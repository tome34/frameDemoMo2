package com.example.tome.projectCore.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.preference.PreferenceManager;


import com.example.tome.core.util.SPUtil;

import java.util.Locale;

/*
 * @version V1.0
 * @project: JiaBaoMo
 * @author: Administrator
 * @date: 2017-06-16 09:23
 * @desc 语言切换
 */
public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static Locale currentLocale;//当前语言

    public static Context onAttach(Context context) {
        if (currentLocale != null) {
            return setLocale(context, currentLocale);
        }

        return setLocale(context, getPersistedLocal(context, Locale.getDefault()));
    }

    public static Context onAttach(Context context, Locale locale) {
        return setLocale(context, locale);
    }

    public static Locale getLanguage(Context context) {
//        String lang = getPersistedData(context, Locale.getDefault());
//        return new Locale(lang);
        //埃及语言
        //Locale locale = new Locale("ar","EG");
        Locale locale = new Locale("zh","");
        return getPersistedLocal(context, locale);
    }

    public static Context setLocale(Context context, Locale locale) {
        persistLocal(context, locale);
        currentLocale = locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, locale);
        }

        return updateResourcesLegacy(context, locale);
    }

    private static String getPersistedData(Context context, Locale defaultLocale) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLocale.getLanguage());
    }

    private static void persist(Context context, Locale locale) {
        SharedPreferences        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor      = preferences.edit();

        editor.putString(SELECTED_LANGUAGE, locale.getLanguage());
        editor.apply();
    }

    private static void persistLocal(Context context, Locale locale) {
        SPUtil.saveObject(context, SELECTED_LANGUAGE, locale);
    }

    private static Locale getPersistedLocal(Context context, Locale defaultLocale) {
        return (Locale) SPUtil.readObject(context, SELECTED_LANGUAGE, defaultLocale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, Locale locale) {
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }


    @SuppressWarnings("deprecation")
    public static void changeAppLanguage(Context context, Locale locale) {
        persistLocal(context, locale);
        updateResourcesLegacy(context, locale);
    }
}
