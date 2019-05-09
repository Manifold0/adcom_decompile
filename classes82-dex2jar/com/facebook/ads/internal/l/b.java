// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.l;

import com.facebook.ads.internal.w.b.d;
import android.text.TextUtils;
import com.facebook.ads.internal.w.h.a;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.telephony.TelephonyManager;
import android.os.Build;
import android.os.Build$VERSION;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;

public class b
{
    public static final String a;
    private final Context b;
    private final AtomicBoolean c;
    
    static {
        a = Build$VERSION.RELEASE;
    }
    
    public b(final Context context) {
        this.c = new AtomicBoolean();
        this.b = context.getApplicationContext();
    }
    
    public String a() {
        if (Build.MANUFACTURER != null && Build.MANUFACTURER.length() > 0) {
            return Build.MANUFACTURER;
        }
        return "";
    }
    
    public String b() {
        if (Build.MODEL != null && Build.MODEL.length() > 0) {
            return Build.MODEL;
        }
        return "";
    }
    
    public String c() {
        final TelephonyManager telephonyManager = (TelephonyManager)this.b.getSystemService("phone");
        if (telephonyManager != null) {
            final String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null && networkOperatorName.length() > 0) {
                return networkOperatorName;
            }
        }
        return "";
    }
    
    public String d() {
        try {
            final CharSequence applicationLabel = this.b.getPackageManager().getApplicationLabel(this.b.getPackageManager().getApplicationInfo(this.f(), 0));
            if (applicationLabel != null && applicationLabel.length() > 0) {
                return applicationLabel.toString();
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return "";
    }
    
    public String e() {
        try {
            final String f = this.f();
            if (f != null && f.length() >= 0) {
                final String installerPackageName = this.b.getPackageManager().getInstallerPackageName(f);
                if (installerPackageName != null && installerPackageName.length() > 0) {
                    return installerPackageName;
                }
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    public String f() {
        final PendingIntent activity = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
        if (activity == null) {
            if (!this.c.getAndSet(true)) {
                com.facebook.ads.internal.w.h.a.b(this.b, "generic", com.facebook.ads.internal.w.h.b.B, new Exception("PI_NULL"));
            }
            return "";
        }
        if (Build$VERSION.SDK_INT >= 17) {
            return activity.getCreatorPackage();
        }
        return activity.getTargetPackage();
    }
    
    public String g() {
        CharSequence versionName = null;
        while (true) {
            try {
                versionName = this.b.getPackageManager().getPackageInfo(this.f(), 0).versionName;
                if (!TextUtils.isEmpty(versionName)) {
                    return (String)versionName;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        return "";
    }
    
    public int h() {
        try {
            return this.b.getPackageManager().getPackageInfo(this.f(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public boolean i() {
        return this.b.checkCallingOrSelfPermission("android.permission.BIND_ACCESSIBILITY_SERVICE") == 0;
    }
    
    public int j() {
        return d.a(this.b);
    }
}
