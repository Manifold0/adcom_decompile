// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import android.view.View;
import android.app.Activity;

public final class zzdl extends zzei
{
    private final Activity zztk;
    private final View zztl;
    
    public zzdl(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2, final View zztl, final Activity zztk) {
        super(zzcz, s, s2, zzba, n, 62);
        this.zztl = zztl;
        this.zztk = zztk;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztl == null) {
            return;
        }
        final boolean booleanValue = (boolean)zzkb.zzik().zzd(zznk.zzbas);
        final Object[] array = (Object[])this.zztz.invoke(null, this.zztl, this.zztk, booleanValue);
        synchronized (this.zztq) {
            this.zztq.zzfa = (long)array[0];
            this.zztq.zzfb = (long)array[1];
            if (booleanValue) {
                this.zztq.zzfc = (String)array[2];
            }
        }
    }
}
