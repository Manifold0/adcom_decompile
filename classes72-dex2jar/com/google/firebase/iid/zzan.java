// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.List;
import android.content.pm.PackageManager;
import android.content.Intent;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageInfo;
import java.security.NoSuchAlgorithmException;
import android.util.Log;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.KeyPair;
import com.google.firebase.FirebaseApp;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public final class zzan
{
    @GuardedBy("this")
    private String zzci;
    @GuardedBy("this")
    private String zzcj;
    @GuardedBy("this")
    private int zzck;
    @GuardedBy("this")
    private int zzcl;
    private final Context zzx;
    
    public zzan(final Context zzx) {
        this.zzcl = 0;
        this.zzx = zzx;
    }
    
    public static String zza(final FirebaseApp firebaseApp) {
        final String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        String applicationId;
        if (gcmSenderId != null) {
            applicationId = gcmSenderId;
        }
        else {
            final String s = applicationId = firebaseApp.getOptions().getApplicationId();
            if (s.startsWith("1:")) {
                final String[] split = s.split(":");
                if (split.length < 2) {
                    return null;
                }
                if ((applicationId = split[1]).isEmpty()) {
                    return null;
                }
            }
        }
        return applicationId;
    }
    
    public static String zza(final KeyPair keyPair) {
        final byte[] encoded = keyPair.getPublic().getEncoded();
        try {
            final byte[] digest = MessageDigest.getInstance("SHA1").digest(encoded);
            digest[0] = (byte)((digest[0] & 0xF) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        }
        catch (NoSuchAlgorithmException ex) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }
    
    private final void zzag() {
        synchronized (this) {
            final PackageInfo zze = this.zze(this.zzx.getPackageName());
            if (zze != null) {
                this.zzci = Integer.toString(zze.versionCode);
                this.zzcj = zze.versionName;
            }
        }
    }
    
    private final PackageInfo zze(String value) {
        try {
            return this.zzx.getPackageManager().getPackageInfo(value, 0);
        }
        catch (PackageManager$NameNotFoundException ex) {
            value = String.valueOf(ex);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(value).length() + 23).append("Failed to find package ").append(value).toString());
            return null;
        }
    }
    
    public final int zzac() {
        while (true) {
            int n = 0;
            Label_0056: {
                synchronized (this) {
                    if (this.zzcl != 0) {
                        n = this.zzcl;
                    }
                    else {
                        if (this.zzx.getPackageManager().checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") != -1) {
                            break Label_0056;
                        }
                        Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
                    }
                    return n;
                }
            }
            final PackageManager packageManager;
            if (!PlatformVersion.isAtLeastO()) {
                final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                final List queryIntentServices = packageManager.queryIntentServices(intent, 0);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    this.zzcl = 1;
                    n = this.zzcl;
                    return n;
                }
            }
            final Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
            intent2.setPackage("com.google.android.gms");
            final List queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
            if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
                this.zzcl = 2;
                n = this.zzcl;
                return n;
            }
            Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
            if (PlatformVersion.isAtLeastO()) {
                this.zzcl = 2;
            }
            else {
                this.zzcl = 1;
            }
            n = this.zzcl;
            return n;
        }
    }
    
    public final String zzad() {
        synchronized (this) {
            if (this.zzci == null) {
                this.zzag();
            }
            return this.zzci;
        }
    }
    
    public final String zzae() {
        synchronized (this) {
            if (this.zzcj == null) {
                this.zzag();
            }
            return this.zzcj;
        }
    }
    
    public final int zzaf() {
        synchronized (this) {
            if (this.zzck == 0) {
                final PackageInfo zze = this.zze("com.google.android.gms");
                if (zze != null) {
                    this.zzck = zze.versionCode;
                }
            }
            return this.zzck;
        }
    }
}
