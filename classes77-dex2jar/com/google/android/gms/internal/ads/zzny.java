// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzaf;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzny extends zzob
{
    private final zzaf zzbgs;
    @Nullable
    private final String zzbgt;
    private final String zzbgu;
    
    public zzny(final zzaf zzbgs, @Nullable final String zzbgt, final String zzbgu) {
        this.zzbgs = zzbgs;
        this.zzbgt = zzbgt;
        this.zzbgu = zzbgu;
    }
    
    public final String getContent() {
        return this.zzbgu;
    }
    
    public final void recordClick() {
        this.zzbgs.zzcn();
    }
    
    public final void recordImpression() {
        this.zzbgs.zzco();
    }
    
    public final void zzg(@Nullable final IObjectWrapper objectWrapper) {
        if (objectWrapper == null) {
            return;
        }
        this.zzbgs.zzh((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final String zzjn() {
        return this.zzbgt;
    }
}
