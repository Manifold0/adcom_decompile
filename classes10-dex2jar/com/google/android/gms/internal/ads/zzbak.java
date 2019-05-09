// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbak extends zzbao
{
    private final int zzdpt;
    private final int zzdpu;
    
    zzbak(final byte[] array, final int zzdpt, final int zzdpu) {
        super(array);
        zzbah.zzd(zzdpt, zzdpt + zzdpu, array.length);
        this.zzdpt = zzdpt;
        this.zzdpu = zzdpu;
    }
    
    @Override
    public final int size() {
        return this.zzdpu;
    }
    
    @Override
    protected final void zza(final byte[] array, final int n, final int n2, final int n3) {
        System.arraycopy(this.zzdpw, this.zzabh(), array, 0, n3);
    }
    
    @Override
    protected final int zzabh() {
        return this.zzdpt;
    }
    
    @Override
    public final byte zzbn(final int n) {
        final int size = this.size();
        if ((size - (n + 1) | n) >= 0) {
            return this.zzdpw[this.zzdpt + n];
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(22).append("Index < 0: ").append(n).toString());
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuilder(40).append("Index > length: ").append(n).append(", ").append(size).toString());
    }
}
