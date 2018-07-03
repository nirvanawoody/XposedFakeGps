package com.woody.fakegps;

/**
 * Created by zhaokai on 2018/3/22.
 */


import android.location.GpsStatus;
import android.location.LocationManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class h extends My_XC_MethodHook {
    public h(XSharedPreferences xSharedPreferences, ClassLoader classLoader, String str) {
        super(xSharedPreferences, classLoader, str);
    }

    public void hookInit() {
        XposedHelpers.findAndHookMethod(LocationManager.class, "getGpsStatus", new Object[]{GpsStatus.class, this});
    }

    protected void before(XC_MethodHook.MethodHookParam methodHookParam) {
    }

    protected void after(XC_MethodHook.MethodHookParam methodHookParam) {
        XposedBridge.log("LM:gps status=on : " + this.hookerName);
        GpsStatus gpsStatus = (GpsStatus) methodHookParam.getResult();
        methodHookParam.args[0] = gpsStatus;
        methodHookParam.setResult(gpsStatus);
    }
}