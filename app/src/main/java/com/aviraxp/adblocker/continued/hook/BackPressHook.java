package com.aviraxp.adblocker.continued.hook;

import com.aviraxp.adblocker.continued.helper.PreferencesHelper;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

class BackPressHook {

    public void hook() {

        if (!PreferencesHelper.isBackPressHookEnabled()) {
            return;
        }

        XC_MethodHook backPressHook = new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) {
                param.args[0] = true;
            }
        };

        XposedBridge.hookAllMethods(android.app.Dialog.class, "setCancelable", backPressHook);
        XposedBridge.hookAllMethods(android.app.Dialog.class, "setCanceledOnTouchOutside", backPressHook);
        XposedBridge.hookAllMethods(android.app.AlertDialog.Builder.class, "setCancelable", backPressHook);
        XposedBridge.hookAllMethods(android.app.Activity.class, "setFinishOnTouchOutside", backPressHook);
    }
}