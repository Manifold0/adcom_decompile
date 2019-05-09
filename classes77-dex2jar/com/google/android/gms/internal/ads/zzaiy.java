// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

@zzadh
public final class zzaiy
{
    private final AtomicReference<ThreadPoolExecutor> zzcnp;
    private final Object zzcnq;
    @Nullable
    @GuardedBy("mGmpAppIdLock")
    private String zzcnr;
    @VisibleForTesting
    private final AtomicBoolean zzcns;
    @VisibleForTesting
    private final AtomicInteger zzcnt;
    private final AtomicReference<Object> zzcnu;
    private final AtomicReference<Object> zzcnv;
    private ConcurrentMap<String, Method> zzcnw;
    
    public zzaiy() {
        this.zzcnp = new AtomicReference<ThreadPoolExecutor>(null);
        this.zzcnq = new Object();
        this.zzcnr = null;
        this.zzcns = new AtomicBoolean(false);
        this.zzcnt = new AtomicInteger(-1);
        this.zzcnu = new AtomicReference<Object>(null);
        this.zzcnv = new AtomicReference<Object>(null);
        this.zzcnw = new ConcurrentHashMap<String, Method>(9);
    }
    
    private static Bundle zza(final Context context, final String o, final boolean b) {
        final Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong((String)o));
            if (b) {
                bundle.putInt("_r", 1);
            }
            return bundle;
        }
        catch (NullPointerException ex) {}
        catch (NumberFormatException var_0_48) {
            goto Label_0032;
        }
    }
    
    private final Object zza(final String s, final Context context) {
        if (!this.zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            return null;
        }
        final Method zzi = this.zzi(context, s);
        try {
            return zzi.invoke(this.zzcnu.get(), new Object[0]);
        }
        catch (Exception ex) {
            this.zza(ex, s, true);
            return null;
        }
    }
    
    private final void zza(final Context context, final String s, final Bundle bundle) {
        if (!this.zzs(context) || !this.zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            return;
        }
        final Method zzac = this.zzac(context);
        try {
            zzac.invoke(this.zzcnu.get(), "am", s, bundle);
        }
        catch (Exception ex) {
            this.zza(ex, "logEventInternal", true);
        }
    }
    
    private final void zza(final Exception ex, final String s, final boolean b) {
        if (!this.zzcns.get()) {
            zzakb.zzdk(new StringBuilder(String.valueOf(s).length() + 30).append("Invoke Firebase method ").append(s).append(" error.").toString());
            if (b) {
                zzakb.zzdk("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.zzcns.set(true);
            }
        }
    }
    
    private final boolean zza(final Context context, final String s, final AtomicReference<Object> atomicReference, final boolean b) {
        if (atomicReference.get() != null) {
            return true;
        }
        try {
            atomicReference.compareAndSet(null, context.getClassLoader().loadClass(s).getDeclaredMethod("getInstance", Context.class).invoke(null, context));
            return true;
        }
        catch (Exception ex) {
            this.zza(ex, "getInstance", b);
            return false;
        }
    }
    
    private final Method zzac(final Context context) {
        final Method method = this.zzcnw.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            final Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
            this.zzcnw.put("logEventInternal", declaredMethod);
            return declaredMethod;
        }
        catch (Exception ex) {
            this.zza(ex, "logEventInternal", true);
            return null;
        }
    }
    
    private final void zzb(final Context context, final String s, final String s2) {
        if (!this.zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
            return;
        }
        final Method zzh = this.zzh(context, s2);
        try {
            zzh.invoke(this.zzcnu.get(), s);
            zzakb.v(new StringBuilder(String.valueOf(s2).length() + 37 + String.valueOf(s).length()).append("Invoke Firebase method ").append(s2).append(", Ad Unit Id: ").append(s).toString());
        }
        catch (Exception ex) {
            this.zza(ex, s2, false);
        }
    }
    
    private final Method zzh(final Context context, final String s) {
        final Method method = this.zzcnw.get(s);
        if (method != null) {
            return method;
        }
        try {
            final Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(s, String.class);
            this.zzcnw.put(s, declaredMethod);
            return declaredMethod;
        }
        catch (Exception ex) {
            this.zza(ex, s, false);
            return null;
        }
    }
    
    private final Method zzi(final Context context, final String s) {
        final Method method = this.zzcnw.get(s);
        if (method != null) {
            return method;
        }
        try {
            final Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(s, (Class<?>[])new Class[0]);
            this.zzcnw.put(s, declaredMethod);
            return declaredMethod;
        }
        catch (Exception ex) {
            this.zza(ex, s, false);
            return null;
        }
    }
    
    private final Method zzj(final Context context, final String s) {
        final Method method = this.zzcnw.get(s);
        if (method != null) {
            return method;
        }
        try {
            final Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(s, Activity.class, String.class, String.class);
            this.zzcnw.put(s, declaredMethod);
            return declaredMethod;
        }
        catch (Exception ex) {
            this.zza(ex, s, false);
            return null;
        }
    }
    
    public final void zza(final Context context, final String s, final String s2) {
        if (!this.zzs(context)) {
            return;
        }
        this.zza(context, s, zza(context, s2, "_ac".equals(s)));
    }
    
    public final void zza(final Context context, final String s, final String s2, final String s3, final int n) {
        if (!this.zzs(context)) {
            return;
        }
        final Bundle zza = zza(context, s, false);
        zza.putString("_ai", s2);
        zza.putString("type", s3);
        zza.putInt("value", n);
        this.zza(context, "_ar", zza);
        zzakb.v(new StringBuilder(String.valueOf(s3).length() + 75).append("Log a Firebase reward video event, reward type: ").append(s3).append(", reward value: ").append(n).toString());
    }
    
    @Nullable
    public final String zzaa(Context submit) {
        if (!this.zzs(submit)) {
            return null;
        }
        final long longValue = (long)zzkb.zzik().zzd(zznk.zzaxt);
        if (longValue < 0L) {
            return (String)this.zza("getAppInstanceId", submit);
        }
        if (this.zzcnp.get() == null) {
            this.zzcnp.compareAndSet(null, new ThreadPoolExecutor((int)zzkb.zzik().zzd(zznk.zzaxu), (int)zzkb.zzik().zzd(zznk.zzaxu), 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new zzaja(this)));
        }
        submit = (Context)this.zzcnp.get().submit((Callable<Object>)new zzaiz(this, submit));
        try {
            return (String)((Future<Object>)submit).get(longValue, TimeUnit.MILLISECONDS);
        }
        catch (Exception ex) {
            ((Future)submit).cancel(true);
            if (ex instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }
    
    @Nullable
    public final String zzab(final Context context) {
        if (this.zzs(context)) {
            final Object zza = this.zza("generateEventId", context);
            if (zza != null) {
                return zza.toString();
            }
        }
        return null;
    }
    
    public final void zzb(final Context context, final String s) {
        if (!this.zzs(context)) {
            return;
        }
        this.zzb(context, s, "beginAdUnitExposure");
    }
    
    public final void zzc(final Context context, final String s) {
        if (!this.zzs(context)) {
            return;
        }
        this.zzb(context, s, "endAdUnitExposure");
    }
    
    public final void zzd(final Context context, final String s) {
        if (!this.zzs(context) || !(context instanceof Activity) || !this.zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzcnv, false)) {
            return;
        }
        final Method zzj = this.zzj(context, "setCurrentScreen");
        try {
            zzj.invoke(this.zzcnv.get(), (Activity)context, s, context.getPackageName());
        }
        catch (Exception ex) {
            this.zza(ex, "setCurrentScreen", false);
        }
    }
    
    public final void zze(final Context context, final String s) {
        this.zza(context, "_ac", s);
    }
    
    public final void zzf(final Context context, final String s) {
        this.zza(context, "_ai", s);
    }
    
    public final void zzg(final Context context, final String s) {
        this.zza(context, "_aq", s);
    }
    
    public final boolean zzs(final Context context) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzaxj) || this.zzcns.get()) {
            return false;
        }
        if (this.zzcnt.get() == -1) {
            zzkb.zzif();
            if (!zzamu.zzbe(context)) {
                zzkb.zzif();
                if (zzamu.zzbh(context)) {
                    zzakb.zzdk("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.zzcnt.set(0);
                    return this.zzcnt.get() == 1;
                }
            }
            this.zzcnt.set(1);
        }
        return this.zzcnt.get() == 1;
    }
    
    public final boolean zzt(final Context context) {
        return (boolean)zzkb.zzik().zzd(zznk.zzaxk) && this.zzs(context);
    }
    
    public final boolean zzu(final Context context) {
        return (boolean)zzkb.zzik().zzd(zznk.zzaxl) && this.zzs(context);
    }
    
    public final boolean zzv(final Context context) {
        return (boolean)zzkb.zzik().zzd(zznk.zzaxm) && this.zzs(context);
    }
    
    public final boolean zzw(final Context context) {
        return (boolean)zzkb.zzik().zzd(zznk.zzaxn) && this.zzs(context);
    }
    
    public final boolean zzx(final Context context) {
        return (boolean)zzkb.zzik().zzd(zznk.zzaxq) && this.zzs(context);
    }
    
    public final String zzy(final Context context) {
        String s;
        if (!this.zzs(context)) {
            s = "";
        }
        else {
            if (!this.zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzcnu, true)) {
                return "";
            }
            try {
                String s2;
                if ((s2 = (String)this.zzi(context, "getCurrentScreenName").invoke(this.zzcnu.get(), new Object[0])) == null) {
                    s2 = (String)this.zzi(context, "getCurrentScreenClass").invoke(this.zzcnu.get(), new Object[0]);
                }
                if ((s = s2) == null) {
                    return "";
                }
            }
            catch (Exception ex) {
                this.zza(ex, "getCurrentScreenName", false);
                return "";
            }
        }
        return s;
    }
    
    @Nullable
    public final String zzz(final Context context) {
        if (!this.zzs(context)) {
            return null;
        }
        synchronized (this.zzcnq) {
            if (this.zzcnr != null) {
                return this.zzcnr;
            }
        }
        final Context context2;
        this.zzcnr = (String)this.zza("getGmpAppId", context2);
        // monitorexit(o)
        return this.zzcnr;
    }
}
