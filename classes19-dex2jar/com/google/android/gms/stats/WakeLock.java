// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.stats;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import android.util.Log;
import java.util.List;
import android.text.TextUtils;
import android.annotation.SuppressLint;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import android.os.PowerManager;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Future;
import java.util.Set;
import java.util.Map;
import android.content.Context;
import android.os.WorkSource;
import android.os.PowerManager$WakeLock;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.ThreadSafe;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
@ThreadSafe
public class WakeLock
{
    private static ScheduledExecutorService zzn;
    private static volatile zza zzo;
    private final Object zza;
    private final PowerManager$WakeLock zzb;
    private WorkSource zzc;
    private final int zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final Context zzh;
    private boolean zzi;
    private final Map<String, Integer[]> zzj;
    private final Set<Future<?>> zzk;
    private int zzl;
    private AtomicInteger zzm;
    
    static {
        WakeLock.zzo = (zza)new com.google.android.gms.stats.zza();
    }
    
    @KeepForSdk
    public WakeLock(@NonNull final Context context, final int n, @NonNull final String s) {
        String packageName;
        if (context == null) {
            packageName = null;
        }
        else {
            packageName = context.getPackageName();
        }
        this(context, n, s, null, packageName);
    }
    
    private WakeLock(@NonNull final Context context, final int n, @NonNull final String s, @Nullable final String s2, @NonNull final String s3) {
        this(context, n, s, null, s3, null);
    }
    
    @SuppressLint({ "UnwrappedWakeLock" })
    private WakeLock(@NonNull Context ex, final int zzd, @NonNull String packageName, @Nullable String zze, @NonNull final String s, @Nullable String value) {
        this.zza = this;
        this.zzi = true;
        this.zzj = new HashMap<String, Integer[]>();
        this.zzk = Collections.synchronizedSet(new HashSet<Future<?>>());
        this.zzm = new AtomicInteger(0);
        Preconditions.checkNotNull((Object)ex, (Object)"WakeLock: context must not be null");
        Preconditions.checkNotEmpty(packageName, (Object)"WakeLock: wakeLockName must not be empty");
        this.zzd = zzd;
        this.zzf = null;
        this.zzg = null;
        this.zzh = ((Context)ex).getApplicationContext();
        while (true) {
        Label_0234_Outer:
            while (true) {
                while (true) {
                    Label_0136: {
                        Label_0266: {
                            if ("com.google.android.gms".equals(((Context)ex).getPackageName())) {
                                break Label_0266;
                            }
                            zze = String.valueOf("*gcore*:");
                            value = String.valueOf(packageName);
                            if (value.length() != 0) {
                                zze = zze.concat(value);
                                break Label_0266;
                            }
                            Label_0252: {
                                break Label_0252;
                                while (true) {
                                    ex = (IllegalArgumentException)this.zzc;
                                    try {
                                        this.zzb.setWorkSource((WorkSource)ex);
                                        if (WakeLock.zzn == null) {
                                            WakeLock.zzn = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
                                        }
                                        return;
                                        this.zzc = (WorkSource)ex;
                                        continue Label_0234_Outer;
                                        this.zze = packageName;
                                        break Label_0136;
                                        zze = new String(zze);
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException ex2) {}
                                    catch (IllegalArgumentException ex) {
                                        goto Label_0283;
                                    }
                                }
                            }
                        }
                        this.zze = zze;
                    }
                    this.zzb = ((PowerManager)((Context)ex).getSystemService("power")).newWakeLock(zzd, packageName);
                    if (!WorkSourceUtil.hasWorkSourcePermission((Context)ex)) {
                        continue;
                    }
                    packageName = s;
                    if (Strings.isEmptyOrWhitespace(s)) {
                        packageName = ((Context)ex).getPackageName();
                    }
                    this.zzc = WorkSourceUtil.fromPackage((Context)ex, packageName);
                    ex = (IllegalArgumentException)this.zzc;
                    if (ex == null || !WorkSourceUtil.hasWorkSourcePermission(this.zzh)) {
                        continue;
                    }
                    break;
                }
                if (this.zzc != null) {
                    this.zzc.add((WorkSource)ex);
                    continue Label_0234_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    private final String zza(final String s) {
        if (!this.zzi) {
            return this.zzf;
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return s;
        }
        return this.zzf;
    }
    
    private final List<String> zza() {
        return (List<String>)WorkSourceUtil.getNames(this.zzc);
    }
    
    private final void zza(final int n) {
        if (!this.zzb.isHeld()) {
            return;
        }
        while (true) {
            try {
                this.zzb.release();
                this.zzb.isHeld();
            }
            catch (RuntimeException ex) {
                if (ex.getClass().equals(RuntimeException.class)) {
                    Log.e("WakeLock", String.valueOf(this.zze).concat(" was already released!"), (Throwable)ex);
                    continue;
                }
                throw ex;
            }
            break;
        }
    }
    
    @KeepForSdk
    public void acquire(final long n) {
    Label_0137_Outer:
        while (true) {
            this.zzm.incrementAndGet();
            final String zza = this.zza(null);
            while (true) {
                while (true) {
                    boolean b;
                    synchronized (this.zza) {
                        if ((!this.zzj.isEmpty() || this.zzl > 0) && !this.zzb.isHeld()) {
                            this.zzj.clear();
                            this.zzl = 0;
                        }
                        if (!this.zzi) {
                            if (!this.zzi && this.zzl == 0) {
                                WakeLockTracker.getInstance().registerEvent(this.zzh, StatsUtils.getEventKey(this.zzb, zza), 7, this.zze, zza, (String)null, this.zzd, (List)this.zza(), n);
                                ++this.zzl;
                            }
                            // monitorexit(this.zza)
                            this.zzb.acquire();
                            if (n > 0L) {
                                WakeLock.zzn.schedule(new zzb(this), n, TimeUnit.MILLISECONDS);
                            }
                            return;
                        }
                        final Integer[] array = this.zzj.get(zza);
                        if (array == null) {
                            this.zzj.put(zza, new Integer[] { 1 });
                            b = true;
                        }
                        else {
                            ++array[0];
                            b = false;
                        }
                    }
                    if (!b) {
                        continue Label_0137_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @KeepForSdk
    public boolean isHeld() {
        return this.zzb.isHeld();
    }
    
    @KeepForSdk
    public void release() {
        if (this.zzm.decrementAndGet() < 0) {
            Log.e("WakeLock", String.valueOf(this.zze).concat(" release without a matched acquire!"));
        }
    Label_0089_Outer:
        while (true) {
            final String zza = this.zza(null);
            while (true) {
                while (true) {
                    boolean b;
                    synchronized (this.zza) {
                        if (!this.zzi) {
                            if (!this.zzi && this.zzl == 1) {
                                WakeLockTracker.getInstance().registerEvent(this.zzh, StatsUtils.getEventKey(this.zzb, zza), 8, this.zze, zza, (String)null, this.zzd, (List)this.zza());
                                --this.zzl;
                            }
                            // monitorexit(this.zza)
                            this.zza(0);
                            return;
                        }
                        final Integer[] array = this.zzj.get(zza);
                        if (array == null) {
                            b = false;
                        }
                        else if (array[0] == 1) {
                            this.zzj.remove(zza);
                            b = true;
                        }
                        else {
                            --array[0];
                            b = false;
                        }
                    }
                    if (!b) {
                        continue Label_0089_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @KeepForSdk
    public void setReferenceCounted(final boolean b) {
        this.zzb.setReferenceCounted(b);
        this.zzi = b;
    }
    
    public interface zza
    {
    }
}
