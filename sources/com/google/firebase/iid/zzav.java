package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

public final class zzav {
    private static zzav zzcx;
    @GuardedBy("serviceClassNames")
    private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap();
    private Boolean zzcz = null;
    @VisibleForTesting
    final Queue<Intent> zzda = new ArrayDeque();
    @VisibleForTesting
    private final Queue<Intent> zzdb = new ArrayDeque();

    public static synchronized zzav zzai() {
        zzav zzav;
        synchronized (zzav.class) {
            if (zzcx == null) {
                zzcx = new zzav();
            }
            zzav = zzcx;
        }
        return zzav;
    }

    private zzav() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getBroadcast(context, i, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), 1073741824);
    }

    public static void zzb(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }

    public static void zzc(Context context, Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }

    private static Intent zza(Context context, String str, Intent intent) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return intent2;
    }

    public final Intent zzaj() {
        return (Intent) this.zzdb.poll();
    }

    public final int zzb(Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String str2 = "FirebaseInstanceId";
            String str3 = "Starting service: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    obj = null;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                this.zzda.offer(intent);
                break;
            case 1:
                this.zzdb.offer(intent);
                break;
            default:
                str2 = "FirebaseInstanceId";
                str3 = "Unknown service action: ";
                valueOf = String.valueOf(str);
                if (valueOf.length() != 0) {
                    valueOf = str3.concat(valueOf);
                } else {
                    valueOf = new String(str3);
                }
                Log.w(str2, valueOf);
                return TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzd(context, intent2);
    }

    private final int zzd(Context context, Intent intent) {
        String str;
        boolean z;
        ComponentName startWakefulService;
        synchronized (this.zzcy) {
            str = (String) this.zzcy.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                if (this.zzcz == null) {
                    if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.zzcz = Boolean.valueOf(z);
                }
                if (this.zzcz.booleanValue()) {
                    startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
                } else {
                    startWakefulService = context.startService(intent);
                    Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
                }
                if (startWakefulService == null) {
                    return -1;
                }
                Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                String str2 = serviceInfo.packageName;
                str = serviceInfo.name;
                Log.e("FirebaseInstanceId", new StringBuilder((String.valueOf(str2).length() + 94) + String.valueOf(str).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(str2).append("/").append(str).toString());
                if (this.zzcz == null) {
                    if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    this.zzcz = Boolean.valueOf(z);
                }
                if (this.zzcz.booleanValue()) {
                    startWakefulService = context.startService(intent);
                    Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
                } else {
                    startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
                }
                if (startWakefulService == null) {
                    return -1;
                }
                Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            str = serviceInfo.name;
            if (str.startsWith(".")) {
                String valueOf = String.valueOf(context.getPackageName());
                str = String.valueOf(str);
                str = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
            }
            synchronized (this.zzcy) {
                this.zzcy.put(intent.getAction(), str);
            }
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            str2 = "FirebaseInstanceId";
            String str3 = "Restricting intent to a specific service: ";
            valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        intent.setClassName(context.getPackageName(), str);
        try {
            if (this.zzcz == null) {
                if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                    z = true;
                } else {
                    z = false;
                }
                this.zzcz = Boolean.valueOf(z);
            }
            if (this.zzcz.booleanValue()) {
                startWakefulService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startWakefulService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (startWakefulService == null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (Throwable e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return 401;
        } catch (IllegalStateException e2) {
            str = String.valueOf(e2);
            Log.e("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 45).append("Failed to start service while in background: ").append(str).toString());
            return 402;
        }
    }
}
