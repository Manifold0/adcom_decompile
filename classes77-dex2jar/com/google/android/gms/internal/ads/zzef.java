// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import android.view.View;

public final class zzef extends zzei
{
    private final View zztl;
    
    public zzef(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2, final View zztl) {
        super(zzcz, s, s2, zzba, n, 57);
        this.zztl = zztl;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl != null) {
            final zzdh zzdh = new zzdh((String)this.zztz.invoke(null, this.zztl, this.zzps.getContext().getResources().getDisplayMetrics()));
            final zzbc zzev = new zzbc();
            zzev.zzgi = zzdh.zzsx;
            zzev.zzgj = zzdh.zzgj;
            zzev.zzgk = zzdh.zzgk;
            this.zztq.zzev = zzev;
        }
    }
}
