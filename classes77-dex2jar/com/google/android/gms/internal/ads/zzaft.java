// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import android.content.Context;

@zzadh
public final class zzaft
{
    private final Context mContext;
    private final Object mLock;
    private String zzchp;
    private String zzchq;
    private zzaoj<zzafz> zzchr;
    private final zzv<Object> zzchs;
    private final zzv<Object> zzcht;
    private final zzv<Object> zzchu;
    
    public zzaft(final Context mContext, final String zzchp, final String zzchq) {
        this.mLock = new Object();
        this.zzchr = new zzaoj<zzafz>();
        this.zzchs = new zzafu(this);
        this.zzcht = new zzafv(this);
        this.zzchu = new zzafw(this);
        this.mContext = mContext;
        this.zzchq = zzchq;
        this.zzchp = zzchp;
    }
}
