// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzec extends zzei
{
    private final zzdi zzqx;
    private long zzti;
    
    public zzec(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2, final zzdi zzqx) {
        super(zzcz, s, s2, zzba, n, 53);
        this.zzqx = zzqx;
        if (zzqx != null) {
            this.zzti = zzqx.zzap();
        }
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zzqx != null) {
            this.zztq.zzep = (Long)this.zztz.invoke(null, this.zzti);
        }
    }
}
