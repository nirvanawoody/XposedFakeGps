package com.woody.fakegps;

/**
 * Created by zhaokai on 2018/3/22.
 */


import android.location.Location;

import com.amap.api.maps.model.LatLng;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class f extends My_XC_MethodHook {
    public f(XSharedPreferences xSharedPreferences, ClassLoader classLoader, String str) {
        super(xSharedPreferences, classLoader, str);
    }

    public void hookInit() {
        XposedHelpers.findAndHookMethod("com.android.server.LocationManagerService$Receiver", this.classLoader, "callLocationChangedLocked", new Object[]{Location.class, this});
    }

    protected void before(XC_MethodHook.MethodHookParam methodHookParam) {
        if (((String) XposedHelpers.getObjectField(methodHookParam.thisObject, "mPackageName")) != null) {
            LatLng b = HookGPS.b();
            Location a = HookGPS.a(b.latitude, b.longitude);
            XposedBridge.log("LocationManagerService$Receiver: status=on latitude: " + b.latitude + " longitude: " + b.longitude);
            methodHookParam.args[0] = a;
        }
    }

    protected void after(XC_MethodHook.MethodHookParam methodHookParam) {
    }
}