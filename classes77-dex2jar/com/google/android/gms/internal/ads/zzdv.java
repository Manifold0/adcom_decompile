// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdv extends zzei
{
    private long zzts;
    
    public zzdv(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 12);
        this.zzts = -1L;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdm = -1L;
        this.zztq.zzdm = (Long)this.zztz.invoke(null, this.zzps.getContext());
    }
}
