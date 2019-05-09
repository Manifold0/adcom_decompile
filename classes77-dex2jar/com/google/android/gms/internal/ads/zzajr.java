// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzajr
{
    private final Object mLock;
    @VisibleForTesting
    private final String zzasc;
    @VisibleForTesting
    private long zzcqd;
    @VisibleForTesting
    private long zzcqe;
    @VisibleForTesting
    @GuardedBy("mLock")
    private int zzcqf;
    @VisibleForTesting
    int zzcqg;
    @VisibleForTesting
    private long zzcqh;
    @VisibleForTesting
    @GuardedBy("mLock")
    private int zzcqi;
    @VisibleForTesting
    @GuardedBy("mLock")
    private int zzcqj;
    
    public zzajr(final String zzasc) {
        this.zzcqd = -1L;
        this.zzcqe = -1L;
        this.zzcqf = -1;
        this.zzcqg = -1;
        this.zzcqh = 0L;
        this.mLock = new Object();
        this.zzcqi = 0;
        this.zzcqj = 0;
        this.zzasc = zzasc;
    }
    
    private static boolean zzah(final Context context) {
        final int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzakb.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        final ComponentName componentName = new ComponentName(context.getPackageName(), "com.google.android.gms.ads.AdActivity");
        try {
            if (identifier == context.getPackageManager().getActivityInfo(componentName, 0).theme) {
                return true;
            }
            zzakb.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        catch (PackageManager$NameNotFoundException ex) {
            zzakb.zzdk("Fail to fetch AdActivity theme");
            zzakb.zzdj("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
    
    public final void zzb(final zzjj zzjj, final long n) {
        long currentTimeMillis;
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    final long zzrb = zzbv.zzeo().zzqh().zzrb();
                    currentTimeMillis = zzbv.zzer().currentTimeMillis();
                    if (this.zzcqe == -1L) {
                        if (currentTimeMillis - zzrb > (long)zzkb.zzik().zzd(zznk.zzayi)) {
                            this.zzcqg = -1;
                        }
                        else {
                            this.zzcqg = zzbv.zzeo().zzqh().zzrc();
                        }
                        this.zzcqe = n;
                        this.zzcqd = this.zzcqe;
                        if (zzjj != null && zzjj.extras != null && zzjj.extras.getInt("gw", 2) == 1) {
                            return;
                        }
                        break;
                    }
                }
                this.zzcqd = n;
                continue;
            }
        }
        ++this.zzcqf;
        ++this.zzcqg;
        if (this.zzcqg == 0) {
            this.zzcqh = 0L;
            zzbv.zzeo().zzqh().zzk(currentTimeMillis);
        }
        else {
            this.zzcqh = currentTimeMillis - zzbv.zzeo().zzqh().zzrd();
        }
    }
    // monitorexit(o)
    
    public final Bundle zzk(final Context context, final String s) {
        synchronized (this.mLock) {
            final Bundle bundle = new Bundle();
            bundle.putString("session_id", this.zzasc);
            bundle.putLong("basets", this.zzcqe);
            bundle.putLong("currts", this.zzcqd);
            bundle.putString("seq_num", s);
            bundle.putInt("preqs", this.zzcqf);
            bundle.putInt("preqs_in_session", this.zzcqg);
            bundle.putLong("time_in_session", this.zzcqh);
            bundle.putInt("pclick", this.zzcqi);
            bundle.putInt("pimp", this.zzcqj);
            bundle.putBoolean("support_transparent_background", zzah(context));
            return bundle;
        }
    }
    
    public final void zzpm() {
        synchronized (this.mLock) {
            ++this.zzcqj;
        }
    }
    
    public final void zzpn() {
        synchronized (this.mLock) {
            ++this.zzcqi;
        }
    }
}
