// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import android.content.res.Resources;
import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import android.os.Looper;
import com.google.android.gms.common.util.PlatformVersion;
import android.support.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzajm implements zzakh
{
    private Context mContext;
    private final Object mLock;
    @Nullable
    private zzgf zzahz;
    private final zzajt zzcpl;
    private final zzakd zzcpm;
    @Nullable
    private zznn zzcpn;
    @Nullable
    private zzgk zzcpo;
    @Nullable
    private Boolean zzcpp;
    private String zzcpq;
    private final AtomicInteger zzcpr;
    private final zzajp zzcps;
    private final Object zzcpt;
    @GuardedBy("mGrantedPermissionLock")
    private zzanz<ArrayList<String>> zzcpu;
    private zzes zzvy;
    private zzang zzyf;
    private boolean zzzv;
    
    public zzajm() {
        this.mLock = new Object();
        this.zzcpl = new zzajt();
        this.zzcpm = new zzakd();
        this.zzzv = false;
        this.zzcpn = null;
        this.zzcpo = null;
        this.zzahz = null;
        this.zzcpp = null;
        this.zzcpr = new AtomicInteger(0);
        this.zzcps = new zzajp(null);
        this.zzcpt = new Object();
    }
    
    @Nullable
    private final zzgk zza(@Nullable final Context context, final boolean b, final boolean b2) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzawk)) {
            return null;
        }
        if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
            return null;
        }
        if (!(boolean)zzkb.zzik().zzd(zznk.zzaws) && !(boolean)zzkb.zzik().zzd(zznk.zzawq)) {
            return null;
        }
        if (b && b2) {
            return null;
        }
        synchronized (this.mLock) {
            if (Looper.getMainLooper() == null || context == null) {
                return null;
            }
            if (this.zzahz == null) {
                this.zzahz = new zzgf();
            }
            if (this.zzcpo == null) {
                this.zzcpo = new zzgk(this.zzahz, zzadb.zzc(context, this.zzyf));
            }
            this.zzcpo.zzgw();
            zzakb.zzdj("start fetching content...");
            return this.zzcpo;
        }
    }
    
    @TargetApi(16)
    private static ArrayList<String> zzag(final Context context) {
        final ArrayList<String> list = new ArrayList<String>();
        PackageInfo packageInfo;
        try {
            packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissionsFlags == null) {
                return list;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            return list;
        }
        for (int i = 0; i < packageInfo.requestedPermissions.length; ++i) {
            if ((packageInfo.requestedPermissionsFlags[i] & 0x2) != 0x0) {
                list.add(packageInfo.requestedPermissions[i]);
            }
        }
        return list;
    }
    
    @Nullable
    public final Context getApplicationContext() {
        return this.mContext;
    }
    
    @Nullable
    public final Resources getResources() {
        Resources resources = null;
        if (this.zzyf.zzcvg) {
            resources = this.mContext.getResources();
        }
        else {
            try {
                final DynamiteModule load = DynamiteModule.load(this.mContext, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite");
                if (load != null) {
                    return load.getModuleContext().getResources();
                }
            }
            catch (DynamiteModule$LoadingException ex) {
                zzakb.zzc("Cannot load resource from dynamite apk or local jar", (Throwable)ex);
                return null;
            }
        }
        return resources;
    }
    
    public final void zza(final Boolean zzcpp) {
        synchronized (this.mLock) {
            this.zzcpp = zzcpp;
        }
    }
    
    public final void zza(final Throwable t, final String s) {
        zzadb.zzc(this.mContext, this.zzyf).zza(t, s);
    }
    
    public final void zzaa(final boolean b) {
        this.zzcps.zzaa(b);
    }
    
    @Nullable
    public final zzgk zzaf(@Nullable final Context context) {
        return this.zza(context, this.zzcpm.zzqu(), this.zzcpm.zzqw());
    }
    
    public final void zzb(final Throwable t, final String s) {
        zzadb.zzc(this.mContext, this.zzyf).zza(t, s, (float)zzkb.zzik().zzd(zznk.zzaul));
    }
    
    @TargetApi(23)
    public final void zzd(final Context context, final zzang zzyf) {
        synchronized (this.mLock) {
            if (!this.zzzv) {
                this.mContext = context.getApplicationContext();
                this.zzyf = zzyf;
                zzbv.zzen().zza(zzbv.zzep());
                this.zzcpm.initialize(this.mContext);
                this.zzcpm.zza(this);
                zzadb.zzc(this.mContext, this.zzyf);
                this.zzcpq = zzbv.zzek().zzm(context, zzyf.zzcw);
                this.zzvy = new zzes(context.getApplicationContext(), this.zzyf);
                zzbv.zzet();
                zznn zzcpn;
                if (!(boolean)zzkb.zzik().zzd(zznk.zzawh)) {
                    zzakb.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                    zzcpn = null;
                }
                else {
                    zzcpn = new zznn();
                }
                this.zzcpn = zzcpn;
                zzanm.zza((zzanz)new zzajo(this).zznt(), "AppState.registerCsiReporter");
                this.zzzv = true;
                this.zzqi();
            }
        }
    }
    
    @Override
    public final void zzd(final Bundle bundle) {
        if (bundle.containsKey("content_url_opted_out") && bundle.containsKey("content_vertical_opted_out")) {
            this.zza(this.mContext, bundle.getBoolean("content_url_opted_out"), bundle.getBoolean("content_vertical_opted_out"));
        }
    }
    
    public final zzajt zzpx() {
        return this.zzcpl;
    }
    
    @Nullable
    public final zznn zzpy() {
        synchronized (this.mLock) {
            return this.zzcpn;
        }
    }
    
    public final Boolean zzpz() {
        synchronized (this.mLock) {
            return this.zzcpp;
        }
    }
    
    public final boolean zzqa() {
        return this.zzcps.zzqa();
    }
    
    public final boolean zzqb() {
        return this.zzcps.zzqb();
    }
    
    public final void zzqc() {
        this.zzcps.zzqc();
    }
    
    public final zzes zzqd() {
        return this.zzvy;
    }
    
    public final void zzqe() {
        this.zzcpr.incrementAndGet();
    }
    
    public final void zzqf() {
        this.zzcpr.decrementAndGet();
    }
    
    public final int zzqg() {
        return this.zzcpr.get();
    }
    
    public final zzakd zzqh() {
        synchronized (this.mLock) {
            return this.zzcpm;
        }
    }
    
    public final zzanz<ArrayList<String>> zzqi() {
        if (this.mContext == null || !PlatformVersion.isAtLeastJellyBean() || (boolean)zzkb.zzik().zzd(zznk.zzbau)) {
            return (zzanz<ArrayList<String>>)zzano.zzi(new ArrayList());
        }
        synchronized (this.zzcpt) {
            if (this.zzcpu != null) {
                return this.zzcpu;
            }
        }
        final zzanz<ArrayList<String>> zza = zzaki.zza((Callable<ArrayList<String>>)new zzajn(this));
        this.zzcpu = zza;
        // monitorexit(o)
        return zza;
    }
}
