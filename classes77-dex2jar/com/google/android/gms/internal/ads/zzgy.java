// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;

public final class zzgy
{
    final long value;
    final String zzajf;
    final int zzajg;
    
    zzgy(final long value, final String zzajf, final int zzajg) {
        this.value = value;
        this.zzajf = zzajf;
        this.zzajg = zzajg;
    }
    
    @Override
    public final boolean equals(@Nullable final Object o) {
        return o != null && o instanceof zzgy && (((zzgy)o).value == this.value && ((zzgy)o).zzajg == this.zzajg);
    }
    
    @Override
    public final int hashCode() {
        return (int)this.value;
    }
}
