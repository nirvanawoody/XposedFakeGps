package com.woody.fakegps;

/**
 * Created by zhaokai on 2018/3/22.
 */

import android.location.Location;
import android.location.LocationManager;

import com.amap.api.maps.model.LatLng;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class j extends My_XC_MethodHook {
    public j(XSharedPreferences xSharedPreferences, ClassLoader classLoader, String str) {
        super(xSharedPreferences, classLoader, str);
    }

    public void hookInit() {
        XposedHelpers.findAndHookMethod(LocationManager.class, "getLastLocation", new Object[]{this});
    }

    protected void before(XC_MethodHook.MethodHookParam methodHookParam) {
    }

    protected void after(XC_MethodHook.MethodHookParam methodHookParam) {
        LatLng b = HookGPS.b();
        Location a = HookGPS.a(b.latitude, b.longitude);
        XposedBridge.log("获取上次位置getLastLocation: latitude: " + b.latitude + " longitude: " + b.longitude);
        methodHookParam.setResult(a);
    }
}