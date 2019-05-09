// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzeb extends zzei
{
    public zzeb(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 51);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zztq) {
            final zzcy zzcy = new zzcy((String)this.zztz.invoke(null, new Object[0]));
            this.zztq.zzen = zzcy.zzrr;
            this.zztq.zzeo = zzcy.zzrs;
        }
    }
}
