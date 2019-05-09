// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdq extends zzei
{
    private static volatile Long zzej;
    private static final Object zztn;
    
    static {
        zzdq.zzej = null;
        zztn = new Object();
    }
    
    public zzdq(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 44);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzdq.zzej != null) {
                break Label_0038;
            }
            Object o = zzdq.zztn;
            synchronized (o) {
                if (zzdq.zzej == null) {
                    zzdq.zzej = (Long)this.zztz.invoke(null, new Object[0]);
                }
                // monitorexit(o)
                o = this.zztq;
                // monitorenter(o)
                final zzdq zzdq = this;
                final zzba zzba = zzdq.zztq;
                final Long n = com.google.android.gms.internal.ads.zzdq.zzej;
                zzba.zzej = n;
                return;
            }
        }
        try {
            final zzdq zzdq = this;
            final zzba zzba = zzdq.zztq;
            final Long n = com.google.android.gms.internal.ads.zzdq.zzej;
            zzba.zzej = n;
        }
        finally {
        }
        // monitorexit(o)
    }
}
