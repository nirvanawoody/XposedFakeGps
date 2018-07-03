package com.woody.fakegps;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by woody on 2018/6/21.
 */

public class MainLocation implements IXposedHookLoadPackage {
	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
		if(loadPackageParam.packageName.equals("com.tencent.mm")){
			HookGPS a = HookGPS.a();
			XSharedPreferences mysp = new XSharedPreferences("com.woody.fakegps", "fakegps");
			a.a(mysp,loadPackageParam.classLoader);
			a.a(loadPackageParam.classLoader);
		}
	}
}
