// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Application;
import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Bundle;
import java.util.ArrayList;
import javax.annotation.concurrent.GuardedBy;
import java.util.List;
import android.content.Context;
import android.support.annotation.Nullable;
import android.app.Activity;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;

@TargetApi(14)
final class zzgh implements Application$ActivityLifecycleCallbacks
{
    @Nullable
    private Activity mActivity;
    private Context mContext;
    private final Object mLock;
    private boolean zzahr;
    private boolean zzahs;
    @GuardedBy("mLock")
    private final List<zzgj> zzaht;
    @GuardedBy("mLock")
    private final List<zzgw> zzahu;
    private Runnable zzahv;
    private long zzahw;
    private boolean zzzv;
    
    zzgh() {
        this.mLock = new Object();
        this.zzahr = true;
        this.zzahs = false;
        this.zzaht = new ArrayList<zzgj>();
        this.zzahu = new ArrayList<zzgw>();
        this.zzzv = false;
    }
    
    private final void setActivity(final Activity mActivity) {
        synchronized (this.mLock) {
            if (!mActivity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.mActivity = mActivity;
            }
        }
    }
    
    @Nullable
    public final Activity getActivity() {
        return this.mActivity;
    }
    
    @Nullable
    public final Context getContext() {
        return this.mContext;
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        synchronized (this.mLock) {
            if (this.mActivity == null) {
                return;
            }
            if (this.mActivity.equals(activity)) {
                this.mActivity = null;
            }
            final Iterator<zzgw> iterator = this.zzahu.iterator();
            while (iterator.hasNext()) {
                final zzgw zzgw = iterator.next();
                try {
                    if (!zzgw.zza(activity)) {
                        continue;
                    }
                    iterator.remove();
                }
                catch (Exception ex) {
                    zzbv.zzeo().zza(ex, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzane.zzb("", (Throwable)ex);
                }
            }
        }
    }
    // monitorexit(o)
    
    public final void onActivityPaused(final Activity activity) {
        this.setActivity(activity);
        synchronized (this.mLock) {
            for (final zzgw zzgw : this.zzahu) {
                try {
                    zzgw.onActivityPaused(activity);
                }
                catch (Exception ex) {
                    zzbv.zzeo().zza(ex, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzane.zzb("", (Throwable)ex);
                }
            }
        }
        // monitorexit(o)
        this.zzahs = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        zzakk.zzcrm.postDelayed(this.zzahv = new zzgi(this), this.zzahw);
    }
    
    public final void onActivityResumed(Activity iterator) {
        boolean b = false;
        this.setActivity(iterator);
        this.zzahs = false;
        if (!this.zzahr) {
            b = true;
        }
        this.zzahr = true;
        if (this.zzahv != null) {
            zzakk.zzcrm.removeCallbacks(this.zzahv);
        }
        synchronized (this.mLock) {
            for (final zzgw zzgw : this.zzahu) {
                try {
                    zzgw.onActivityResumed(iterator);
                }
                catch (Exception ex) {
                    zzbv.zzeo().zza(ex, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzane.zzb("", (Throwable)ex);
                }
            }
        }
        if (b) {
            iterator = (Activity)this.zzaht.iterator();
            while (((Iterator)iterator).hasNext()) {
                final zzgj zzgj = ((Iterator<zzgj>)iterator).next();
                try {
                    zzgj.zzh(true);
                }
                catch (Exception ex2) {
                    zzane.zzb("", (Throwable)ex2);
                }
            }
        }
        else {
            zzakb.zzck("App is still foreground.");
        }
    }
    // monitorexit(o)
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityStarted(final Activity activity) {
        this.setActivity(activity);
    }
    
    public final void onActivityStopped(final Activity activity) {
    }
    
    public final void zza(final Application mContext, final Context context) {
        if (!this.zzzv) {
            mContext.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
            if (context instanceof Activity) {
                this.setActivity((Activity)context);
            }
            this.mContext = (Context)mContext;
            this.zzahw = (long)zzkb.zzik().zzd(zznk.zzayh);
            this.zzzv = true;
        }
    }
    
    public final void zza(final zzgj zzgj) {
        synchronized (this.mLock) {
            this.zzaht.add(zzgj);
        }
    }
}
