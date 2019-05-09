// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zzhm extends zzir<zzhm>
{
    public int versionCode;
    public long zze;
    public long zzf;
    public long zzg;
    
    public zzhm() {
        this.versionCode = 1;
        this.zze = -1L;
        this.zzf = -1L;
        this.zzg = -1L;
        this.zzmw = null;
        this.zznf = -1;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzhm)) {
                return false;
            }
            final zzhm zzhm = (zzhm)o;
            if (this.versionCode != zzhm.versionCode) {
                return false;
            }
            if (this.zze != zzhm.zze) {
                return false;
            }
            if (this.zzf != zzhm.zzf) {
                return false;
            }
            if (this.zzg != zzhm.zzg) {
                return false;
            }
            if (this.zzmw != null && !this.zzmw.isEmpty()) {
                return this.zzmw.equals(zzhm.zzmw);
            }
            if (zzhm.zzmw != null && !zzhm.zzmw.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        final int hashCode = this.getClass().getName().hashCode();
        final int versionCode = this.versionCode;
        final int n = (int)(this.zze ^ this.zze >>> 32);
        final int n2 = (int)(this.zzf ^ this.zzf >>> 32);
        final int n3 = (int)(this.zzg ^ this.zzg >>> 32);
        int hashCode2;
        if (this.zzmw == null || this.zzmw.isEmpty()) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzmw.hashCode();
        }
        return hashCode2 + (((((hashCode + 527) * 31 + versionCode) * 31 + n) * 31 + n2) * 31 + n3) * 31;
    }
    
    @Override
    public final void zza(final zzip zzip) throws IOException {
        zzip.zzb(1, this.versionCode);
        zzip.zza(2, this.zze);
        zzip.zza(3, this.zzf);
        zzip.zza(4, this.zzg);
        super.zza(zzip);
    }
    
    @Override
    protected final int zzaq() {
        return super.zzaq() + zzip.zzc(1, this.versionCode) + zzip.zzb(2, this.zze) + zzip.zzb(3, this.zzf) + zzip.zzb(4, this.zzg);
    }
}
