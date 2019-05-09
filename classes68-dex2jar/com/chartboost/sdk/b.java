// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import android.content.pm.ActivityInfo;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import com.chartboost.sdk.Tracking.a;
import android.content.SharedPreferences;
import org.json.JSONObject;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.impl.s;
import android.content.Context;
import android.app.Activity;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.e;

public final class b
{
    public static String a(final e e) {
        if (!e.y) {
            return "native";
        }
        return "web";
    }
    
    static void a(final String b) {
        if (i.d == null) {
            CBLogging.b("CBConfig", "Set a valid CBFramework first");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)b)) {
            CBLogging.b("CBConfig", "Invalid Version String");
            return;
        }
        i.b = b;
    }
    
    public static boolean a() {
        return b() && c();
    }
    
    static boolean a(final Activity activity) {
        if (activity == null) {
            try {
                throw new Exception("Invalid activity context: Host Activity object is null, Please send a valid activity object");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public static boolean a(final Context context) {
        final boolean b = true;
        boolean b2;
        if (context == null) {
            try {
                throw new RuntimeException("Invalid activity context passed during intitalization");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                b2 = false;
            }
        }
        else {
            int n;
            int n2;
            int n3;
            int n4;
            int n5;
            if (s.a().a(23)) {
                n = context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                n2 = context.checkSelfPermission("android.permission.ACCESS_NETWORK_STATE");
                n3 = context.checkSelfPermission("android.permission.INTERNET");
                n4 = context.checkSelfPermission("android.permission.READ_PHONE_STATE");
                n5 = context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE");
            }
            else {
                n = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
                n2 = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
                n3 = context.checkCallingOrSelfPermission("android.permission.INTERNET");
                n4 = context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE");
                n5 = context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE");
            }
            i.n = (n != 0);
            i.o = (n3 != 0);
            i.p = (n2 != 0);
            i.q = (n4 != 0);
            i.r = (n5 != 0);
            if (i.o) {
                throw new RuntimeException("Please add the permission : android.permission.INTERNET in your android manifest.xml");
            }
            b2 = b;
            if (i.p) {
                throw new RuntimeException("Please add the permission : android.permission.ACCESS_NETWORK_STATE in your android manifest.xml");
            }
        }
        return b2;
    }
    
    public static boolean a(final AtomicReference<e> atomicReference, final JSONObject jsonObject, final SharedPreferences sharedPreferences) {
        try {
            atomicReference.set(new e(jsonObject));
            return true;
        }
        catch (Exception ex) {
            a.a(b.class, "updateConfig", ex);
            return false;
        }
    }
    
    static boolean b() {
        try {
            if (h.a() == null) {
                throw new Exception("SDK Initialization error. SDK seems to be not initialized properly, check for any integration issues");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        if (i.m == null) {
            throw new Exception("SDK Initialization error. Activity context seems to be not initialized properly, host activity or application context is being sent as null");
        }
        if (TextUtils.isEmpty((CharSequence)i.k)) {
            throw new Exception("SDK Initialization error. AppId is missing");
        }
        if (TextUtils.isEmpty((CharSequence)i.l)) {
            throw new Exception("SDK Initialization error. AppSignature is missing");
        }
        return true;
    }
    
    public static boolean b(final Context context) {
        final List queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent(context, (Class)CBImpressionActivity.class), 0);
        if (queryIntentActivities.isEmpty()) {
            return false;
        }
        final ActivityInfo activityInfo = queryIntentActivities.get(0).activityInfo;
        return (activityInfo.flags & 0x200) != 0x0 && (activityInfo.flags & 0x20) != 0x0 && (activityInfo.configChanges & 0x80) != 0x0 && (activityInfo.configChanges & 0x20) != 0x0 && (activityInfo.configChanges & 0x400) != 0x0;
    }
    
    private static boolean c() {
        final h a = h.a();
        if (a == null) {
            return false;
        }
        if (a.q.d == null) {
            try {
                throw new Exception("Chartboost Weak Activity reference is null");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
