// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;

public final class zzey implements zzgd
{
    @Nullable
    private final View mView;
    @Nullable
    private final zzajh zzafn;
    
    public zzey(final View mView, final zzajh zzafn) {
        this.mView = mView;
        this.zzafn = zzafn;
    }
    
    @Override
    public final View zzgh() {
        return this.mView;
    }
    
    @Override
    public final boolean zzgi() {
        return this.zzafn == null || this.mView == null;
    }
    
    @Override
    public final zzgd zzgj() {
        return this;
    }
}
