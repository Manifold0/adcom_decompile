// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import javax.annotation.concurrent.GuardedBy;
import java.math.BigInteger;

@zzadh
public final class zzajt
{
    @GuardedBy("this")
    private BigInteger zzcqk;
    
    public zzajt() {
        this.zzcqk = BigInteger.ONE;
    }
    
    public final String zzql() {
        synchronized (this) {
            final String string = this.zzcqk.toString();
            this.zzcqk = this.zzcqk.add(BigInteger.ONE);
            return string;
        }
    }
}
