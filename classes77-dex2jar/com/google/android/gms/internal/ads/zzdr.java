// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdr extends zzei
{
    private long startTime;
    
    public zzdr(final zzcz zzcz, final String s, final String s2, final zzba zzba, final long startTime, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 25);
        this.startTime = startTime;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        final long longValue = (long)this.zztz.invoke(null, new Object[0]);
        synchronized (this.zztq) {
            this.zztq.zzfm = longValue;
            if (this.startTime != 0L) {
                this.zztq.zzdr = longValue - this.startTime;
                this.zztq.zzdw = this.startTime;
            }
        }
    }
}
