// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzed extends zzei
{
    private static final Object zztn;
    private static volatile Long zztw;
    
    static {
        zzed.zztw = null;
        zztn = new Object();
    }
    
    public zzed(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 33);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzed.zztw != null) {
                break Label_0038;
            }
            Object o = zzed.zztn;
            synchronized (o) {
                if (zzed.zztw == null) {
                    zzed.zztw = (Long)this.zztz.invoke(null, new Object[0]);
                }
                // monitorexit(o)
                o = this.zztq;
                // monitorenter(o)
                final zzed zzed = this;
                final zzba zzba = zzed.zztq;
                final Long n = com.google.android.gms.internal.ads.zzed.zztw;
                zzba.zzea = n;
                return;
            }
        }
        try {
            final zzed zzed = this;
            final zzba zzba = zzed.zztq;
            final Long n = com.google.android.gms.internal.ads.zzed.zztw;
            zzba.zzea = n;
        }
        finally {
        }
        // monitorexit(o)
    }
}
