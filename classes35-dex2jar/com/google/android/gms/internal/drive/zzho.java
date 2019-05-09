// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zzho extends zzir<zzho>
{
    public long zzac;
    public long zzf;
    
    public zzho() {
        this.zzac = -1L;
        this.zzf = -1L;
        this.zzmw = null;
        this.zznf = -1;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzho)) {
                return false;
            }
            final zzho zzho = (zzho)o;
            if (this.zzac != zzho.zzac) {
                return false;
            }
            if (this.zzf != zzho.zzf) {
                return false;
            }
            if (this.zzmw != null && !this.zzmw.isEmpty()) {
                return this.zzmw.equals(zzho.zzmw);
            }
            if (zzho.zzmw != null && !zzho.zzmw.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        final int hashCode = this.getClass().getName().hashCode();
        final int n = (int)(this.zzac ^ this.zzac >>> 32);
        final int n2 = (int)(this.zzf ^ this.zzf >>> 32);
        int hashCode2;
        if (this.zzmw == null || this.zzmw.isEmpty()) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzmw.hashCode();
        }
        return hashCode2 + (((hashCode + 527) * 31 + n) * 31 + n2) * 31;
    }
    
    @Override
    public final void zza(final zzip zzip) throws IOException {
        zzip.zza(1, this.zzac);
        zzip.zza(2, this.zzf);
        super.zza(zzip);
    }
    
    @Override
    protected final int zzaq() {
        return super.zzaq() + zzip.zzb(1, this.zzac) + zzip.zzb(2, this.zzf);
    }
}
