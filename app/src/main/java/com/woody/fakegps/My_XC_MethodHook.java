package com.woody.fakegps;

/**
 * Created by zhaokai on 2018/3/22.
 */


import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;

public abstract class My_XC_MethodHook extends XC_MethodHook {
    protected XSharedPreferences xSharedPreferences;
    protected ClassLoader classLoader;
    protected String hookerName;

    public abstract void hookInit();

    protected abstract void before(MethodHookParam methodHookParam);

    protected abstract void after(MethodHookParam methodHookParam);

    public My_XC_MethodHook(XSharedPreferences xSharedPreferences, ClassLoader classLoader, String str) {
        this.xSharedPreferences = xSharedPreferences;
        this.classLoader = classLoader;
        this.hookerName = str;
    }

    protected void afterHookedMethod(MethodHookParam methodHookParam) {
        after(methodHookParam);
    }

    protected void beforeHookedMethod(MethodHookParam methodHookParam) {
        before(methodHookParam);
    }
}