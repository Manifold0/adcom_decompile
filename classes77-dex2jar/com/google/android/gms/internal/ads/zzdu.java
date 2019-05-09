// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdu extends zzei
{
    private static final Object zztn;
    private static volatile Long zztr;
    
    static {
        zzdu.zztr = null;
        zztn = new Object();
    }
    
    public zzdu(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 22);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzdu.zztr != null) {
                break Label_0038;
            }
            Object o = zzdu.zztn;
            synchronized (o) {
                if (zzdu.zztr == null) {
                    zzdu.zztr = (Long)this.zztz.invoke(null, new Object[0]);
                }
                // monitorexit(o)
                o = this.zztq;
                // monitorenter(o)
                final zzdu zzdu = this;
                final zzba zzba = zzdu.zztq;
                final Long n = com.google.android.gms.internal.ads.zzdu.zztr;
                zzba.zzdv = n;
                return;
            }
        }
        try {
            final zzdu zzdu = this;
            final zzba zzba = zzdu.zztq;
            final Long n = com.google.android.gms.internal.ads.zzdu.zztr;
            zzba.zzdv = n;
        }
        finally {
        }
        // monitorexit(o)
    }
}
