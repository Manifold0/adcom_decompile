// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import android.provider.Settings$SettingNotFoundException;

public final class zzdm extends zzei
{
    public zzdm(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 49);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzem = 2;
        try {
            final boolean booleanValue = (boolean)this.zztz.invoke(null, this.zzps.getContext());
            final zzba zztq = this.zztq;
            int n;
            if (booleanValue) {
                n = 1;
            }
            else {
                n = 0;
            }
            zztq.zzem = n;
        }
        catch (InvocationTargetException ex) {
            if (!(ex.getTargetException() instanceof Settings$SettingNotFoundException)) {
                throw ex;
            }
        }
    }
}
