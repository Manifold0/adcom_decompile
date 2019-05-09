// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbao extends zzban
{
    protected final byte[] zzdpw;
    
    zzbao(final byte[] zzdpw) {
        this.zzdpw = zzdpw;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof zzbah)) {
            return false;
        }
        if (this.size() != ((zzbah)o).size()) {
            return false;
        }
        if (this.size() == 0) {
            return true;
        }
        if (o instanceof zzbao) {
            final zzbao zzbao = (zzbao)o;
            final int zzabg = this.zzabg();
            final int zzabg2 = zzbao.zzabg();
            return (zzabg == 0 || zzabg2 == 0 || zzabg == zzabg2) && this.zza((zzbah)o, 0, this.size());
        }
        return o.equals(this);
    }
    
    @Override
    public int size() {
        return this.zzdpw.length;
    }
    
    @Override
    protected final String zza(final Charset charset) {
        return new String(this.zzdpw, this.zzabh(), this.size(), charset);
    }
    
    @Override
    final void zza(final zzbag zzbag) throws IOException {
        zzbag.zzb(this.zzdpw, this.zzabh(), this.size());
    }
    
    @Override
    protected void zza(final byte[] array, final int n, final int n2, final int n3) {
        System.arraycopy(this.zzdpw, 0, array, 0, n3);
    }
    
    @Override
    final boolean zza(final zzbah zzbah, int n, final int n2) {
        if (n2 > zzbah.size()) {
            n = this.size();
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(n2).append(n).toString());
        }
        if (n2 > zzbah.size()) {
            n = zzbah.size();
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(n2).append(", ").append(n).toString());
        }
        if (zzbah instanceof zzbao) {
            final zzbao zzbao = (zzbao)zzbah;
            final byte[] zzdpw = this.zzdpw;
            final byte[] zzdpw2 = zzbao.zzdpw;
            int zzabh;
            int i;
            for (zzabh = this.zzabh(), i = this.zzabh(), n = zzbao.zzabh(); i < zzabh + n2; ++i, ++n) {
                if (zzdpw[i] != zzdpw2[n]) {
                    return false;
                }
            }
            return true;
        }
        return zzbah.zzk(0, n2).equals(this.zzk(0, n2));
    }
    
    @Override
    public final boolean zzabe() {
        final int zzabh = this.zzabh();
        return zzbem.zzf(this.zzdpw, zzabh, this.size() + zzabh);
    }
    
    @Override
    public final zzbaq zzabf() {
        return zzbaq.zza(this.zzdpw, this.zzabh(), this.size(), true);
    }
    
    protected int zzabh() {
        return 0;
    }
    
    @Override
    public byte zzbn(final int n) {
        return this.zzdpw[n];
    }
    
    @Override
    protected final int zzc(final int n, final int n2, final int n3) {
        return zzbbq.zza(n, this.zzdpw, this.zzabh(), n3);
    }
    
    @Override
    public final zzbah zzk(int zzd, final int n) {
        zzd = zzbah.zzd(0, n, this.size());
        if (zzd == 0) {
            return zzbah.zzdpq;
        }
        return new zzbak(this.zzdpw, this.zzabh(), zzd);
    }
}
