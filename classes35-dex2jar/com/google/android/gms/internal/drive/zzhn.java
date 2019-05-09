// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zzhn extends zzir<zzhn>
{
    public int versionCode;
    public String zzab;
    public long zzac;
    public int zzad;
    public long zzf;
    
    public zzhn() {
        this.versionCode = 1;
        this.zzab = "";
        this.zzac = -1L;
        this.zzf = -1L;
        this.zzad = -1;
        this.zzmw = null;
        this.zznf = -1;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzhn)) {
                return false;
            }
            final zzhn zzhn = (zzhn)o;
            if (this.versionCode != zzhn.versionCode) {
                return false;
            }
            if (this.zzab == null) {
                if (zzhn.zzab != null) {
                    return false;
                }
            }
            else if (!this.zzab.equals(zzhn.zzab)) {
                return false;
            }
            if (this.zzac != zzhn.zzac) {
                return false;
            }
            if (this.zzf != zzhn.zzf) {
                return false;
            }
            if (this.zzad != zzhn.zzad) {
                return false;
            }
            if (this.zzmw != null && !this.zzmw.isEmpty()) {
                return this.zzmw.equals(zzhn.zzmw);
            }
            if (zzhn.zzmw != null && !zzhn.zzmw.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        final int n = 0;
        final int hashCode = this.getClass().getName().hashCode();
        final int versionCode = this.versionCode;
        int hashCode2;
        if (this.zzab == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzab.hashCode();
        }
        final int n2 = (int)(this.zzac ^ this.zzac >>> 32);
        final int n3 = (int)(this.zzf ^ this.zzf >>> 32);
        final int zzad = this.zzad;
        int hashCode3 = n;
        if (this.zzmw != null) {
            if (this.zzmw.isEmpty()) {
                hashCode3 = n;
            }
            else {
                hashCode3 = this.zzmw.hashCode();
            }
        }
        return ((((hashCode2 + ((hashCode + 527) * 31 + versionCode) * 31) * 31 + n2) * 31 + n3) * 31 + zzad) * 31 + hashCode3;
    }
    
    @Override
    public final void zza(final zzip zzip) throws IOException {
        zzip.zzb(1, this.versionCode);
        final String zzab = this.zzab;
        zzip.zzd(2, 2);
        zzip.zzh(zzab);
        zzip.zza(3, this.zzac);
        zzip.zza(4, this.zzf);
        if (this.zzad != -1) {
            zzip.zzb(5, this.zzad);
        }
        super.zza(zzip);
    }
    
    @Override
    protected final int zzaq() {
        int n = super.zzaq() + zzip.zzc(1, this.versionCode) + (zzip.zzi(this.zzab) + zzip.zzo(2)) + zzip.zzb(3, this.zzac) + zzip.zzb(4, this.zzf);
        if (this.zzad != -1) {
            n += zzip.zzc(5, this.zzad);
        }
        return n;
    }
}
