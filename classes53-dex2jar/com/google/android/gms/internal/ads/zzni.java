// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import android.content.Context;
import android.support.annotation.Nullable;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzni
{
    private final Object mLock;
    private final ConditionVariable zzatv;
    @Nullable
    private SharedPreferences zzatw;
    private Context zzatx;
    private volatile boolean zzzv;
    
    public zzni() {
        this.mLock = new Object();
        this.zzatv = new ConditionVariable();
        this.zzzv = false;
        this.zzatw = null;
    }
    
    public final void initialize(Context o) {
        if (this.zzzv) {
            return;
        }
        synchronized (this.mLock) {
            if (this.zzzv) {
                return;
            }
        }
        Label_0080: {
            if (((Context)o).getApplicationContext() != null) {
                break Label_0080;
            }
            Object zzatx = o;
        Label_0066_Outer:
            while (true) {
                this.zzatx = (Context)zzatx;
                while (true) {
                    Label_0133: {
                        try {
                            zzatx = GooglePlayServicesUtilLight.getRemoteContext((Context)o);
                            if (zzatx != null || o == null) {
                                break Label_0133;
                            }
                            zzatx = ((Context)o).getApplicationContext();
                            if (zzatx != null) {
                                o = zzatx;
                            }
                            if (o == null) {
                                this.zzatv.open();
                                // monitorexit(o2)
                                return;
                            }
                            zzkb.zzii();
                            this.zzatw = ((Context)o).getSharedPreferences("google_ads_flags", 0);
                            this.zzzv = true;
                            this.zzatv.open();
                            // monitorexit(o2)
                            return;
                            zzatx = ((Context)o).getApplicationContext();
                            continue Label_0066_Outer;
                        }
                        finally {
                            this.zzatv.open();
                        }
                    }
                    o = zzatx;
                    continue;
                }
            }
        }
    }
    
    public final <T> T zzd(final zzna<T> zzna) {
        if (!this.zzatv.block(5000L)) {
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
        Label_0069: {
            if (this.zzzv && this.zzatw != null) {
                break Label_0069;
            }
            synchronized (this.mLock) {
                if (!this.zzzv || this.zzatw == null) {
                    return zzna.zzja();
                }
                // monitorexit(this.mLock)
                return zzaml.zza(this.zzatx, (Callable<T>)new zznj(this, zzna));
            }
        }
    }
}
