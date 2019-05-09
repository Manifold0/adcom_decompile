// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Parcelable;
import android.util.Log;
import android.util.Base64;
import android.os.Build$VERSION;
import android.content.ComponentName;
import com.google.android.gms.common.internal.ShowFirstParty;
import android.annotation.SuppressLint;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import javax.annotation.concurrent.GuardedBy;
import android.support.v4.content.WakefulBroadcastReceiver;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver
{
    private static boolean zzbf;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzbg;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzbh;
    
    static {
        FirebaseInstanceIdReceiver.zzbf = false;
    }
    
    @SuppressLint({ "InlinedApi" })
    @ShowFirstParty
    public static int zza(final BroadcastReceiver broadcastReceiver, final Context context, final String s, final Intent intent) {
        boolean b = true;
        boolean b2;
        if (PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if ((intent.getFlags() & 0x10000000) == 0x0) {
            b = false;
        }
        int n;
        if (b2 && !b) {
            n = zzb(broadcastReceiver, context, s, intent);
        }
        else {
            n = zzav.zzai().zzb(context, s, intent);
            if (PlatformVersion.isAtLeastO() && (n = n) == 402) {
                zzb(broadcastReceiver, context, s, intent);
                return 403;
            }
        }
        return n;
    }
    
    private static zzh zza(final Context context, final String s) {
        synchronized (FirebaseInstanceIdReceiver.class) {
            zzh zzh;
            if ("com.google.firebase.MESSAGING_EVENT".equals(s)) {
                if (FirebaseInstanceIdReceiver.zzbh == null) {
                    FirebaseInstanceIdReceiver.zzbh = new zzh(context, s);
                }
                zzh = FirebaseInstanceIdReceiver.zzbh;
            }
            else {
                if (FirebaseInstanceIdReceiver.zzbg == null) {
                    FirebaseInstanceIdReceiver.zzbg = new zzh(context, s);
                }
                zzh = FirebaseInstanceIdReceiver.zzbg;
            }
            return zzh;
        }
    }
    
    private final void zza(final Context context, final Intent intent, String s) {
        intent.setComponent((ComponentName)null);
        intent.setPackage(context.getPackageName());
        if (Build$VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        final String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("google.com/iid".equals(intent.getStringExtra("from")) || "com.google.firebase.INSTANCE_ID_EVENT".equals(s)) {
            s = "com.google.firebase.INSTANCE_ID_EVENT";
        }
        else if ("com.google.android.c2dm.intent.RECEIVE".equals(s) || "com.google.firebase.MESSAGING_EVENT".equals(s)) {
            s = "com.google.firebase.MESSAGING_EVENT";
        }
        else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
            s = null;
        }
        int zza = -1;
        if (s != null) {
            zza = zza((BroadcastReceiver)this, context, s, intent);
        }
        if (this.isOrderedBroadcast()) {
            this.setResultCode(zza);
        }
    }
    
    private static int zzb(final BroadcastReceiver broadcastReceiver, final Context context, final String s, final Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "Binding to service: ".concat(value);
            }
            else {
                concat = new String("Binding to service: ");
            }
            Log.d("FirebaseInstanceId", concat);
        }
        if (broadcastReceiver.isOrderedBroadcast()) {
            broadcastReceiver.setResultCode(-1);
        }
        zza(context, s).zza(intent, broadcastReceiver.goAsync());
        return -1;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        if (intent == null) {
            return;
        }
        final Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
        Intent intent2;
        if (parcelableExtra instanceof Intent) {
            intent2 = (Intent)parcelableExtra;
        }
        else {
            intent2 = null;
        }
        if (intent2 != null) {
            this.zza(context, intent2, intent.getAction());
            return;
        }
        this.zza(context, intent, intent.getAction());
    }
}
