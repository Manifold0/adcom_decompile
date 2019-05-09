// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.os.UserHandle;
import android.os.UserManager;
import android.os.Process;
import android.os.Build$VERSION;
import java.util.concurrent.ThreadPoolExecutor;
import android.view.View;
import android.content.Context;
import android.annotation.TargetApi;
import android.app.Activity;

public class a
{
    @TargetApi(11)
    public static void a(final Activity activity) {
        if (!a()) {
            return;
        }
        try {
            activity.invalidateOptionsMenu();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            j.c("hasHoneycomb is true but invalidateOptionsMenu doesn't exist", noSuchMethodError);
        }
    }
    
    @TargetApi(11)
    public static void a(final Activity activity, final boolean b) {
        if (activity != null && !activity.isFinishing() && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            final View rootView = activity.getWindow().getDecorView().getRootView();
            if (rootView != null && a() && rootView.hasFocus()) {
                j.a("enter immersive mode");
                int n;
                if (a((Context)activity) && b) {
                    n = 4098;
                }
                else {
                    n = 0;
                }
                rootView.setSystemUiVisibility(n | 0x1 | 0x4);
            }
        }
    }
    
    @TargetApi(11)
    public static void a(final View view, final float alpha) {
        if (!a()) {
            return;
        }
        try {
            view.setAlpha(alpha);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            j.c("hasHoneycomb is true but setAlpha(float) doesn't exist");
        }
    }
    
    public static void a(final ThreadPoolExecutor threadPoolExecutor) {
        if (!a(9) || threadPoolExecutor == null) {
            return;
        }
        try {
            if (!threadPoolExecutor.allowsCoreThreadTimeOut()) {
                threadPoolExecutor.allowCoreThreadTimeOut(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {
            j.c("NoSuchMethod: allowCoreThreadTimeout");
        }
    }
    
    public static boolean a() {
        return Build$VERSION.SDK_INT >= 11;
    }
    
    public static boolean a(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    public static boolean a(final Context context) {
        return a(19) && b(context);
    }
    
    public static boolean b() {
        return Build$VERSION.SDK_INT >= 14;
    }
    
    @TargetApi(17)
    public static boolean b(final Context context) {
        if (!a(17)) {
            return true;
        }
        final UserHandle myUserHandle = Process.myUserHandle();
        final UserManager userManager = (UserManager)context.getSystemService("user");
        return userManager != null && 0L == userManager.getSerialNumberForUser(myUserHandle);
    }
    
    public static boolean c() {
        return Build$VERSION.SDK_INT >= 16;
    }
}
