// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzw;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
@VisibleForTesting
public final class zzss
{
    private final Context mContext;
    private final zzw zzwc;
    private final zzxn zzwh;
    private final zzang zzyf;
    
    zzss(final Context mContext, final zzxn zzwh, final zzang zzyf, final zzw zzwc) {
        this.mContext = mContext;
        this.zzwh = zzwh;
        this.zzyf = zzyf;
        this.zzwc = zzwc;
    }
    
    @VisibleForTesting
    public final Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }
    
    @VisibleForTesting
    public final zzal zzav(final String s) {
        return new zzal(this.mContext, new zzjn(), s, this.zzwh, this.zzyf, this.zzwc);
    }
    
    @VisibleForTesting
    public final zzal zzaw(final String s) {
        return new zzal(this.mContext.getApplicationContext(), new zzjn(), s, this.zzwh, this.zzyf, this.zzwc);
    }
    
    @VisibleForTesting
    public final zzss zzlc() {
        return new zzss(this.mContext.getApplicationContext(), this.zzwh, this.zzyf, this.zzwc);
    }
}
