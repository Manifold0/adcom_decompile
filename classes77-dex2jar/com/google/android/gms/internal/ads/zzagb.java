// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.annotation.TargetApi;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.media.AudioManager;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.util.PlatformVersion;
import android.os.Build;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.pm.PackageManager;
import com.google.android.gms.common.util.DeviceProperties;
import java.util.Locale;
import android.content.Context;

public final class zzagb
{
    private float zzagu;
    private int zzcde;
    private int zzcdf;
    private int zzcjk;
    private boolean zzcjl;
    private boolean zzcjm;
    private String zzcjn;
    private String zzcjo;
    private boolean zzcjp;
    private boolean zzcjq;
    private boolean zzcjr;
    private boolean zzcjs;
    private String zzcjt;
    private String zzcju;
    private String zzcjv;
    private int zzcjw;
    private int zzcjx;
    private int zzcjy;
    private int zzcjz;
    private int zzcka;
    private int zzckb;
    private double zzckc;
    private boolean zzckd;
    private boolean zzcke;
    private int zzckf;
    private String zzckg;
    private String zzckh;
    private boolean zzcki;
    
    public zzagb(final Context context) {
        final boolean b = true;
        final PackageManager packageManager = context.getPackageManager();
        this.zzn(context);
        this.zzo(context);
        this.zzp(context);
        final Locale default1 = Locale.getDefault();
        this.zzcjl = (zza(packageManager, "geo:0,0?q=donuts") != null);
        this.zzcjm = (zza(packageManager, "http://www.google.com") != null && b);
        this.zzcjo = default1.getCountry();
        zzkb.zzif();
        this.zzcjp = zzamu.zzsg();
        this.zzcjq = DeviceProperties.isSidewinder(context);
        this.zzcjt = default1.getLanguage();
        this.zzcju = zzb(context, packageManager);
        this.zzcjv = zza(context, packageManager);
        final Resources resources = context.getResources();
        if (resources != null) {
            final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.zzagu = displayMetrics.density;
                this.zzcde = displayMetrics.widthPixels;
                this.zzcdf = displayMetrics.heightPixels;
            }
        }
    }
    
    public zzagb(final Context context, final zzaga zzaga) {
        context.getPackageManager();
        this.zzn(context);
        this.zzo(context);
        this.zzp(context);
        this.zzckg = Build.FINGERPRINT;
        this.zzckh = Build.DEVICE;
        this.zzcki = (PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzoh.zzh(context));
        this.zzcjl = zzaga.zzcjl;
        this.zzcjm = zzaga.zzcjm;
        this.zzcjo = zzaga.zzcjo;
        this.zzcjp = zzaga.zzcjp;
        this.zzcjq = zzaga.zzcjq;
        this.zzcjt = zzaga.zzcjt;
        this.zzcju = zzaga.zzcju;
        this.zzcjv = zzaga.zzcjv;
        this.zzagu = zzaga.zzagu;
        this.zzcde = zzaga.zzcde;
        this.zzcdf = zzaga.zzcdf;
    }
    
    private static ResolveInfo zza(final PackageManager packageManager, final String s) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)), 65536);
        }
        catch (Throwable t) {
            zzbv.zzeo().zza(t, "DeviceInfo.getResolveInfo");
            return null;
        }
    }
    
    private static String zza(final Context context, final PackageManager packageManager) {
        final String s = null;
        try {
            final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            String string = s;
            if (packageInfo != null) {
                final int versionCode = packageInfo.versionCode;
                final String packageName = packageInfo.packageName;
                string = new StringBuilder(String.valueOf(packageName).length() + 12).append(versionCode).append(".").append(packageName).toString();
            }
            return string;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static String zzb(final Context context, final PackageManager packageManager) {
        final ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza != null) {
            final ActivityInfo activityInfo = zza.activityInfo;
            if (activityInfo != null) {
                try {
                    final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
                    if (packageInfo != null) {
                        final int versionCode = packageInfo.versionCode;
                        final String packageName = activityInfo.packageName;
                        return new StringBuilder(String.valueOf(packageName).length() + 12).append(versionCode).append(".").append(packageName).toString();
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    private final void zzn(final Context context) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        if (audioManager != null) {
            try {
                this.zzcjk = audioManager.getMode();
                this.zzcjr = audioManager.isMusicActive();
                this.zzcjs = audioManager.isSpeakerphoneOn();
                this.zzcjw = audioManager.getStreamVolume(3);
                this.zzcka = audioManager.getRingerMode();
                this.zzckb = audioManager.getStreamVolume(2);
                return;
            }
            catch (Throwable t) {
                zzbv.zzeo().zza(t, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzcjk = -2;
        this.zzcjr = false;
        this.zzcjs = false;
        this.zzcjw = 0;
        this.zzcka = 0;
        this.zzckb = 0;
    }
    
    @TargetApi(16)
    private final void zzo(final Context context) {
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        this.zzcjn = telephonyManager.getNetworkOperator();
        this.zzcjy = telephonyManager.getNetworkType();
        this.zzcjz = telephonyManager.getPhoneType();
        this.zzcjx = -2;
        this.zzcke = false;
        this.zzckf = -1;
        zzbv.zzek();
        if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE")) {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzcjx = activeNetworkInfo.getType();
                this.zzckf = activeNetworkInfo.getDetailedState().ordinal();
            }
            else {
                this.zzcjx = -1;
            }
            if (Build$VERSION.SDK_INT >= 16) {
                this.zzcke = connectivityManager.isActiveNetworkMetered();
            }
        }
    }
    
    private final void zzp(final Context context) {
        boolean zzckd = false;
        final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            final int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzckc = registerReceiver.getIntExtra("level", -1) / (float)registerReceiver.getIntExtra("scale", -1);
            if (intExtra == 2 || intExtra == 5) {
                zzckd = true;
            }
            this.zzckd = zzckd;
            return;
        }
        this.zzckc = -1.0;
        this.zzckd = false;
    }
    
    public final zzaga zzoo() {
        return new zzaga(this.zzcjk, this.zzcjl, this.zzcjm, this.zzcjn, this.zzcjo, this.zzcjp, this.zzcjq, this.zzcjr, this.zzcjs, this.zzcjt, this.zzcju, this.zzcjv, this.zzcjw, this.zzcjx, this.zzcjy, this.zzcjz, this.zzcka, this.zzckb, this.zzagu, this.zzcde, this.zzcdf, this.zzckc, this.zzckd, this.zzcke, this.zzckf, this.zzckg, this.zzcki, this.zzckh);
    }
}
