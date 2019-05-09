// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

public final class zzalr
{
    public final int count;
    public final String name;
    private final double zzcsz;
    private final double zzcta;
    public final double zzctb;
    
    public zzalr(final String name, final double zzcta, final double zzcsz, final double zzctb, final int count) {
        this.name = name;
        this.zzcta = zzcta;
        this.zzcsz = zzcsz;
        this.zzctb = zzctb;
        this.count = count;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof zzalr) {
            final zzalr zzalr = (zzalr)o;
            if (Objects.equal((Object)this.name, (Object)zzalr.name) && this.zzcsz == zzalr.zzcsz && this.zzcta == zzalr.zzcta && this.count == zzalr.count && Double.compare(this.zzctb, zzalr.zzctb) == 0) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.name, this.zzcsz, this.zzcta, this.zzctb, this.count });
    }
    
    @Override
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("name", (Object)this.name).add("minBound", (Object)this.zzcta).add("maxBound", (Object)this.zzcsz).add("percent", (Object)this.zzctb).add("count", (Object)this.count).toString();
    }
}
