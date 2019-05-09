// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import com.google.android.gms.common.util.zzq;
import android.util.Base64;
import android.os.Build$VERSION;
import android.content.pm.ServiceInfo;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.iid.zzh;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmReceiver extends WakefulBroadcastReceiver
{
    private static boolean zzhzg;
    private static zzh zzhzh;
    private static zzh zzhzi;
    
    static {
        GcmReceiver.zzhzg = false;
    }
    
    private final void doStartService(final Context context, final Intent intent) {
        if (this.isOrderedBroadcast()) {
            this.setResultCode(500);
        }
        final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        Label_0085: {
            if (resolveService != null && resolveService.serviceInfo != null) {
                break Label_0085;
            }
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
            ComponentName componentName;
            ServiceInfo serviceInfo;
            String s2;
            String s;
            String value;
            String value2;
            String concat;
            String packageName;
            String name;
            String value3;
            Label_0234_Outer:Block_10_Outer:
            while (true) {
                Label_0355: {
                    try {
                        if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                            componentName = startWakefulService(context, intent);
                        }
                        else {
                            componentName = context.startService(intent);
                            Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
                        }
                        if (componentName == null) {
                            Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
                            if (this.isOrderedBroadcast()) {
                                this.setResultCode(404);
                            }
                            return;
                        }
                        break Label_0355;
                        // iftrue(Label_0274:, !Log.isLoggable("GcmReceiver", 3))
                        // iftrue(Label_0234:, !s.startsWith("."))
                        // iftrue(Label_0184:, context.getPackageName().equals((Object)serviceInfo.packageName) && serviceInfo.name != null)
                        // iftrue(Label_0299:, value3.length() == 0)
                        while (true) {
                            Block_9: {
                                while (true) {
                                    Block_12: {
                                    Label_0274:
                                        while (true) {
                                            Block_11: {
                                                break Block_11;
                                                Label_0184: {
                                                    s = (s2 = serviceInfo.name);
                                                }
                                                break Block_9;
                                                s2 = value.concat(value2);
                                                continue Block_10_Outer;
                                                Label_0299:
                                                concat = new String("Restricting intent to a specific service: ");
                                                Log.d("GcmReceiver", concat);
                                                break Label_0274;
                                                serviceInfo = resolveService.serviceInfo;
                                                packageName = serviceInfo.packageName;
                                                name = serviceInfo.name;
                                                Log.e("GcmReceiver", new StringBuilder(String.valueOf(packageName).length() + 94 + String.valueOf(name).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(packageName).append("/").append(name).toString());
                                                continue Label_0234_Outer;
                                            }
                                            value3 = String.valueOf(s2);
                                            break Block_12;
                                            Label_0287: {
                                                s2 = new String(value);
                                            }
                                            continue Block_10_Outer;
                                        }
                                        intent.setClassName(context.getPackageName(), s2);
                                        continue Label_0234_Outer;
                                    }
                                    concat = "Restricting intent to a specific service: ".concat(value3);
                                    continue;
                                }
                            }
                            value = String.valueOf(context.getPackageName());
                            value2 = String.valueOf(s);
                            continue;
                        }
                    }
                    // iftrue(Label_0287:, value2.length() == 0)
                    catch (SecurityException ex) {
                        Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", (Throwable)ex);
                        if (this.isOrderedBroadcast()) {
                            this.setResultCode(401);
                        }
                        return;
                    }
                }
                if (this.isOrderedBroadcast()) {
                    break;
                }
                return;
            }
        }
        this.setResultCode(-1);
    }
    
    private final zzh zzae(final Context context, final String s) {
        synchronized (this) {
            zzh zzh;
            if ("com.google.android.c2dm.intent.RECEIVE".equals(s)) {
                if (GcmReceiver.zzhzi == null) {
                    GcmReceiver.zzhzi = new zzh(context, s);
                }
                zzh = GcmReceiver.zzhzi;
            }
            else {
                if (GcmReceiver.zzhzh == null) {
                    GcmReceiver.zzhzh = new zzh(context, s);
                }
                zzh = GcmReceiver.zzhzh;
            }
            return zzh;
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final boolean b = false;
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "received new intent");
        }
        intent.setComponent((ComponentName)null);
        intent.setPackage(context.getPackageName());
        if (Build$VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        final String stringExtra = intent.getStringExtra("from");
        if ("google.com/iid".equals(stringExtra) || "gcm.googleapis.com/refresh".equals(stringExtra)) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        final String stringExtra2 = intent.getStringExtra("gcm.rawData64");
        if (stringExtra2 != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra2, 0));
            intent.removeExtra("gcm.rawData64");
        }
        int n = b ? 1 : 0;
        if (zzq.isAtLeastO()) {
            n = (b ? 1 : 0);
            if (context.getApplicationInfo().targetSdkVersion > 25) {
                n = 1;
            }
        }
        if (n != 0) {
            if (this.isOrderedBroadcast()) {
                this.setResultCode(-1);
            }
            this.zzae(context, intent.getAction()).zza(intent, this.goAsync());
        }
        else {
            if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
                this.doStartService(context, intent);
            }
            else {
                this.doStartService(context, intent);
            }
            if (this.isOrderedBroadcast() && this.getResultCode() == 0) {
                this.setResultCode(-1);
            }
        }
    }
}
