// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzee extends zzei
{
    public zzee(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 48);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzel = 2;
        final boolean booleanValue = (boolean)this.zztz.invoke(null, this.zzps.getContext());
        final zzba zztq = this.zztq;
        // monitorenter(zztq)
        Label_0066: {
            if (!booleanValue) {
                break Label_0066;
            }
            try {
                this.zztq.zzel = 1;
                return;
                this.zztq.zzel = 0;
            }
            finally {
            }
            // monitorexit(zztq)
        }
    }
}
