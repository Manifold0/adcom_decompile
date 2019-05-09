// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzdw extends zzei
{
    private static volatile String zzdc;
    private static final Object zztn;
    
    static {
        zzdw.zzdc = null;
        zztn = new Object();
    }
    
    public zzdw(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 1);
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzdc = "E";
        Label_0047: {
            if (zzdw.zzdc != null) {
                break Label_0047;
            }
            Object o = zzdw.zztn;
            synchronized (o) {
                if (zzdw.zzdc == null) {
                    zzdw.zzdc = (String)this.zztz.invoke(null, new Object[0]);
                }
                // monitorexit(o)
                o = this.zztq;
                // monitorenter(o)
                final zzdw zzdw = this;
                final zzba zzba = zzdw.zztq;
                final String s = com.google.android.gms.internal.ads.zzdw.zzdc;
                zzba.zzdc = s;
                return;
            }
        }
        try {
            final zzdw zzdw = this;
            final zzba zzba = zzdw.zztq;
            final String s = com.google.android.gms.internal.ads.zzdw.zzdc;
            zzba.zzdc = s;
        }
        finally {
        }
        // monitorexit(o)
    }
}
