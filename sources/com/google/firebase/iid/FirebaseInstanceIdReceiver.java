package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.drive.DriveFile;
import javax.annotation.concurrent.GuardedBy;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private static boolean zzbf = false;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzbg;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzbh;

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                zza(context, intent2, intent.getAction());
            } else {
                zza(context, intent, intent.getAction());
            }
        }
    }

    private final void zza(Context context, Intent intent, String str) {
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("google.com/iid".equals(intent.getStringExtra("from")) || "com.google.firebase.INSTANCE_ID_EVENT".equals(str)) {
            stringExtra = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(str) || "com.google.firebase.MESSAGING_EVENT".equals(str)) {
            stringExtra = "com.google.firebase.MESSAGING_EVENT";
        } else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
            stringExtra = null;
        }
        int i = -1;
        if (stringExtra != null) {
            i = zza(this, context, stringExtra, intent);
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }

    @ShowFirstParty
    @SuppressLint({"InlinedApi"})
    public static int zza(BroadcastReceiver broadcastReceiver, Context context, String str, Intent intent) {
        Object obj = 1;
        Object obj2 = (!PlatformVersion.isAtLeastO() || context.getApplicationInfo().targetSdkVersion < 26) ? null : 1;
        if ((intent.getFlags() & DriveFile.MODE_READ_ONLY) == 0) {
            obj = null;
        }
        if (obj2 != null && r1 == null) {
            return zzb(broadcastReceiver, context, str, intent);
        }
        int zzb = zzav.zzai().zzb(context, str, intent);
        if (!PlatformVersion.isAtLeastO() || zzb != 402) {
            return zzb;
        }
        zzb(broadcastReceiver, context, str, intent);
        return 403;
    }

    private static int zzb(BroadcastReceiver broadcastReceiver, Context context, String str, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String str2 = "FirebaseInstanceId";
            String str3 = "Binding to service: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        if (broadcastReceiver.isOrderedBroadcast()) {
            broadcastReceiver.setResultCode(-1);
        }
        zza(context, str).zza(intent, broadcastReceiver.goAsync());
        return -1;
    }

    private static synchronized zzh zza(Context context, String str) {
        zzh zzh;
        synchronized (FirebaseInstanceIdReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (zzbh == null) {
                    zzbh = new zzh(context, str);
                }
                zzh = zzbh;
            } else {
                if (zzbg == null) {
                    zzbg = new zzh(context, str);
                }
                zzh = zzbg;
            }
        }
        return zzh;
    }
}
