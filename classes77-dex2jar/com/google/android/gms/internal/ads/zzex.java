// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;

public final class zzex implements zzgd
{
    private zzoz zzafm;
    
    public zzex(final zzoz zzafm) {
        this.zzafm = zzafm;
    }
    
    @Override
    public final View zzgh() {
        if (this.zzafm != null) {
            return this.zzafm.zzkr();
        }
        return null;
    }
    
    @Override
    public final boolean zzgi() {
        return this.zzafm == null;
    }
    
    @Override
    public final zzgd zzgj() {
        return this;
    }
}
