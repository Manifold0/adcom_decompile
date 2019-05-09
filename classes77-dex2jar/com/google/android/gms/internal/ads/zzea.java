// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzea extends zzei
{
    private final StackTraceElement[] zztv;
    
    public zzea(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2, final StackTraceElement[] zztv) {
        super(zzcz, s, s2, zzba, n, 45);
        this.zztv = zztv;
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zztv != null) {
            while (true) {
                final zzcx zzcx = new zzcx((String)this.zztz.invoke(null, this.zztv));
                while (true) {
                    Label_0101: {
                        synchronized (this.zztq) {
                            this.zztq.zzek = zzcx.zzro;
                            if (zzcx.zzrp) {
                                final zzba zztq = this.zztq;
                                if (!zzcx.zzrq) {
                                    break Label_0101;
                                }
                                final int n = 0;
                                zztq.zzes = n;
                            }
                            return;
                        }
                        break;
                    }
                    final int n = 1;
                    continue;
                }
            }
        }
    }
}
