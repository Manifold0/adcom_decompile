// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

@zzadh
public final class zzagp extends zzagv
{
    private final String zzclb;
    private final int zzclc;
    
    public zzagp(final String zzclb, final int zzclc) {
        this.zzclb = zzclb;
        this.zzclc = zzclc;
    }
    
    public final boolean equals(final Object o) {
        if (o != null && o instanceof zzagp) {
            final zzagp zzagp = (zzagp)o;
            if (Objects.equal((Object)this.zzclb, (Object)zzagp.zzclb) && Objects.equal((Object)this.zzclc, (Object)zzagp.zzclc)) {
                return true;
            }
        }
        return false;
    }
    
    public final int getAmount() {
        return this.zzclc;
    }
    
    public final String getType() {
        return this.zzclb;
    }
}
