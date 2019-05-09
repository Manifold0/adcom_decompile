// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzdy extends zzei
{
    private List<Long> zztt;
    
    public zzdy(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 31);
        this.zztt = null;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdy = -1L;
        this.zztq.zzdz = -1L;
        if (this.zztt == null) {
            this.zztt = (List<Long>)this.zztz.invoke(null, this.zzps.getContext());
        }
        if (this.zztt != null && this.zztt.size() == 2) {
            synchronized (this.zztq) {
                this.zztq.zzdy = this.zztt.get(0);
                this.zztq.zzdz = this.zztt.get(1);
            }
        }
    }
}
