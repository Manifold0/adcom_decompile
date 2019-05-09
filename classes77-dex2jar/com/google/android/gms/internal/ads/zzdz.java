// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdz extends zzei
{
    private final boolean zztu;
    
    public zzdz(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 61);
        this.zztu = zzcz.zzai();
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        final long longValue = (long)this.zztz.invoke(null, this.zzps.getContext(), this.zztu);
        synchronized (this.zztq) {
            this.zztq.zzez = longValue;
        }
    }
}
