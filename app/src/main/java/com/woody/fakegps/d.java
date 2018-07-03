package com.woody.fakegps;

/**
 * Created by zhaokai on 2018/3/22.
 */

import android.location.GpsStatus.Listener;
import android.location.LocationManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class d extends My_XC_MethodHook {
    public d(XSharedPreferences xSharedPreferences, ClassLoader classLoader, String str) {
        super(xSharedPreferences, classLoader, str);
    }

    public void hookInit() {
        XposedHelpers.findAndHookMethod(LocationManager.class, "addGpsStatusListener", new Object[]{Listener.class, this});
    }

    protected void before(XC_MethodHook.MethodHookParam methodHookParam) {
    }

    protected void after(XC_MethodHook.MethodHookParam methodHookParam) {
        if (((Boolean) methodHookParam.getResult()).booleanValue()) {
            Listener listener = (Listener) methodHookParam.args[0];
            if (listener != null) {
                XposedHelpers.callMethod(listener, "onGpsStatusChanged", new Object[]{Integer.valueOf(1)});
                XposedHelpers.callMethod(listener, "onGpsStatusChanged", new Object[]{Integer.valueOf(3)});
                XposedBridge.log("LM:agsl addGpsStatusListener:伪装GPS状态 " + this.hookerName);
            }
        }
    }
}