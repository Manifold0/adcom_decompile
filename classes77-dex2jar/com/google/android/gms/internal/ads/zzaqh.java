// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import android.support.annotation.Nullable;
import java.util.Map;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.api.Releasable;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzaqh implements Releasable
{
    protected Context mContext;
    private String zzcpq;
    private WeakReference<zzapw> zzdaz;
    
    public zzaqh(final zzapw zzapw) {
        this.mContext = zzapw.getContext();
        this.zzcpq = zzbv.zzek().zzm(this.mContext, zzapw.zztq().zzcw);
        this.zzdaz = new WeakReference<zzapw>(zzapw);
    }
    
    private final void zza(final String s, final Map<String, String> map) {
        final zzapw zzapw = this.zzdaz.get();
        if (zzapw != null) {
            zzapw.zza(s, map);
        }
    }
    
    private static String zzdq(final String s) {
        switch (s) {
            default: {
                return "internal";
            }
            case "contentLengthMissing":
            case "error":
            case "inProgress":
            case "interrupted":
            case "noop":
            case "playerFailed": {
                return "internal";
            }
            case "expireFailed":
            case "noCacheDir": {
                return "io";
            }
            case "badUrl":
            case "downloadTimeout": {
                return "network";
            }
            case "externalAbort":
            case "sizeExceeded": {
                return "policy";
            }
        }
    }
    
    public abstract void abort();
    
    public void release() {
    }
    
    protected final void zza(final String s, final String s2, final int n) {
        zzamu.zzsy.post((Runnable)new zzaqj(this, s, s2, n));
    }
    
    @VisibleForTesting
    public final void zza(final String s, final String s2, final String s3, @Nullable final String s4) {
        zzamu.zzsy.post((Runnable)new zzaqk(this, s, s2, s3, s4));
    }
    
    public abstract boolean zzdp(final String p0);
}
