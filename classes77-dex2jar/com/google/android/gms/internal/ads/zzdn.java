// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdn extends zzei
{
    private static volatile String zztm;
    private static final Object zztn;
    
    static {
        zzdn.zztm = null;
        zztn = new Object();
    }
    
    public zzdn(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 29);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdx = "E";
        Label_0057: {
            if (zzdn.zztm != null) {
                break Label_0057;
            }
            Object o = zzdn.zztn;
            synchronized (o) {
                if (zzdn.zztm == null) {
                    zzdn.zztm = (String)this.zztz.invoke(null, this.zzps.getContext());
                }
                // monitorexit(o)
                o = this.zztq;
                // monitorenter(o)
                final zzdn zzdn = this;
                final zzba zzba = zzdn.zztq;
                final String s = com.google.android.gms.internal.ads.zzdn.zztm;
                final byte[] array = s.getBytes();
                final boolean b = true;
                final String s2 = zzbi.zza(array, b);
                zzba.zzdx = s2;
                return;
            }
        }
        try {
            final zzdn zzdn = this;
            final zzba zzba = zzdn.zztq;
            final String s = com.google.android.gms.internal.ads.zzdn.zztm;
            final byte[] array = s.getBytes();
            final boolean b = true;
            final String s2 = zzbi.zza(array, b);
            zzba.zzdx = s2;
        }
        finally {
        }
        // monitorexit(o)
    }
}
