// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import android.app.Activity;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgg
{
    private final Object zzaho;
    @GuardedBy("mActivityTrackerLock")
    private zzgh zzahp;
    @GuardedBy("mActivityTrackerLock")
    private boolean zzahq;
    
    public zzgg() {
        this.zzaho = new Object();
        this.zzahp = null;
        this.zzahq = false;
    }
    
    @Nullable
    public final Activity getActivity() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp != null) {
                return this.zzahp.getActivity();
            }
        }
        // monitorexit(o)
        return null;
    }
    
    @Nullable
    public final Context getContext() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp != null) {
                return this.zzahp.getContext();
            }
        }
        // monitorexit(o)
        return null;
    }
    
    public final void initialize(final Context context) {
        Label_0126: {
            synchronized (this.zzaho) {
                if (this.zzahq) {
                    break Label_0126;
                }
                if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                    return;
                }
                if (!(boolean)zzkb.zzik().zzd(zznk.zzayg)) {
                    return;
                }
            }
            final Context context2;
            Object applicationContext;
            if ((applicationContext = context2.getApplicationContext()) == null) {
                applicationContext = context2;
            }
            Application application;
            if (applicationContext instanceof Application) {
                application = (Application)applicationContext;
            }
            else {
                application = null;
            }
            if (application == null) {
                zzakb.zzdk("Can not cast Context to Application");
                // monitorexit(o)
                return;
            }
            if (this.zzahp == null) {
                this.zzahp = new zzgh();
            }
            this.zzahp.zza(application, context2);
            this.zzahq = true;
        }
    }
    // monitorexit(o)
    
    public final void zza(final zzgj zzgj) {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return;
            }
            if (!(boolean)zzkb.zzik().zzd(zznk.zzayg)) {
                return;
            }
        }
        if (this.zzahp == null) {
            this.zzahp = new zzgh();
        }
        final zzgj zzgj2;
        this.zzahp.zza(zzgj2);
    }
    // monitorexit(o)
}
