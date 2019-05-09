// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.Objects;

final class zzaz
{
    private final long zzaf;
    private final String zzca;
    
    zzaz(final String zzca, final long zzaf) {
        this.zzca = zzca;
        this.zzaf = zzaf;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzaz)) {
                return false;
            }
            final zzaz zzaz = (zzaz)o;
            if (!Objects.equal((Object)this.zzca, (Object)zzaz.zzca) || !Objects.equal((Object)this.zzaf, (Object)zzaz.zzaf)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzca, this.zzaf });
    }
    
    public final String zze() {
        return this.zzca;
    }
}
