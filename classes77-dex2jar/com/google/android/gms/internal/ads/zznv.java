// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznv
{
    private final long zzbgj;
    @Nullable
    private final String zzbgk;
    @Nullable
    private final zznv zzbgl;
    
    public zznv(final long zzbgj, @Nullable final String zzbgk, @Nullable final zznv zzbgl) {
        this.zzbgj = zzbgj;
        this.zzbgk = zzbgk;
        this.zzbgl = zzbgl;
    }
    
    public final long getTime() {
        return this.zzbgj;
    }
    
    public final String zzjg() {
        return this.zzbgk;
    }
    
    @Nullable
    public final zznv zzjh() {
        return this.zzbgl;
    }
}
