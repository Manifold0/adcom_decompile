// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdp extends zzei
{
    public zzdp(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 5);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdf = -1L;
        this.zztq.zzdg = -1L;
        final int[] array = (int[])this.zztz.invoke(null, this.zzps.getContext());
        synchronized (this.zztq) {
            this.zztq.zzdf = (long)array[0];
            this.zztq.zzdg = (long)array[1];
            if (array[2] != Integer.MIN_VALUE) {
                this.zztq.zzex = (long)array[2];
            }
        }
    }
}
