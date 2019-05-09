// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdx extends zzei
{
    public zzdx(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 3);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            final zzcm zzcm = new zzcm((String)this.zztz.invoke(null, this.zzps.getContext()));
            synchronized (this.zztq) {
                this.zztq.zzdd = zzcm.zzri;
                this.zztq.zzey = zzcm.zzrj;
            }
        }
    }
}
