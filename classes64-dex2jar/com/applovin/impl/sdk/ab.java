// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.view.Display;
import android.view.WindowManager;
import android.graphics.Point;
import android.content.Intent;
import android.content.ComponentName;
import com.applovin.adview.AppLovinInterstitialActivity;
import android.content.Context;
import android.os.StrictMode;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.Build$VERSION;

public class ab
{
    static void a() {
        try {
            if (Build$VERSION.SDK_INT >= 9) {
                StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder().permitAll().build());
            }
        }
        catch (Throwable t) {}
    }
    
    public static boolean a(final Context context) {
        try {
            return gd.a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 1024);
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static boolean a(final Class<?> clazz, final Context context) {
        boolean b = false;
        if (context.getPackageManager().resolveActivity(new Intent(context, (Class)clazz), 0) != null) {
            b = true;
        }
        return b;
    }
    
    public static boolean a(final String s, final Context context) {
        return context.checkCallingOrSelfPermission(s) == 0;
    }
    
    public static boolean b() {
        return Build$VERSION.SDK_INT >= 11;
    }
    
    public static boolean b(final Context context) {
        try {
            return gd.a(context.getPackageManager().getActivityInfo(new ComponentName(context, AppLovinInterstitialActivity.class.getCanonicalName()), 0).configChanges, 128);
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static Point c(final Context context) {
        final Point point = new Point();
        point.x = 480;
        point.y = 320;
        try {
            final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            if (Build$VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
                return point;
            }
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
            return point;
        }
        catch (Throwable t) {
            return point;
        }
    }
    
    public static boolean c() {
        return Build$VERSION.SDK_INT >= 14;
    }
    
    public static boolean d() {
        return Build$VERSION.SDK_INT >= 16;
    }
    
    public static boolean e() {
        return Build$VERSION.SDK_INT >= 17;
    }
    
    public static boolean f() {
        return Build$VERSION.SDK_INT >= 19;
    }
    
    public static boolean g() {
        return Build$VERSION.SDK_INT >= 21;
    }
    
    public static boolean h() {
        return Build$VERSION.SDK_INT >= 23;
    }
}
