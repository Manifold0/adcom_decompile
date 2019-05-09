// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzbfe implements Cloneable
{
    private static final zzbff zzebm;
    private int mSize;
    private boolean zzebn;
    private int[] zzebo;
    private zzbff[] zzebp;
    
    static {
        zzebm = new zzbff();
    }
    
    zzbfe() {
        this(10);
    }
    
    private zzbfe(int idealIntArraySize) {
        this.zzebn = false;
        idealIntArraySize = idealIntArraySize(idealIntArraySize);
        this.zzebo = new int[idealIntArraySize];
        this.zzebp = new zzbff[idealIntArraySize];
        this.mSize = 0;
    }
    
    private static int idealIntArraySize(int n) {
        final int n2 = n << 2;
        n = 4;
        int n3;
        while (true) {
            n3 = n2;
            if (n >= 32) {
                break;
            }
            if (n2 <= (1 << n) - 12) {
                n3 = (1 << n) - 12;
                break;
            }
            ++n;
        }
        return n3 / 4;
    }
    
    private final int zzdh(final int n) {
        final int mSize = this.mSize;
        int i = 0;
        int n2 = mSize - 1;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int n4 = this.zzebo[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                final int n5 = n3;
                if (n4 <= n) {
                    return n5;
                }
                n2 = n3 - 1;
            }
        }
        return ~i;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzbfe)) {
                return false;
            }
            final zzbfe zzbfe = (zzbfe)o;
            if (this.mSize != zzbfe.mSize) {
                return false;
            }
            final int[] zzebo = this.zzebo;
            final int[] zzebo2 = zzbfe.zzebo;
            while (true) {
                for (int mSize = this.mSize, i = 0; i < mSize; ++i) {
                    if (zzebo[i] != zzebo2[i]) {
                        final int n = 0;
                        if (n != 0) {
                            final zzbff[] zzebp = this.zzebp;
                            final zzbff[] zzebp2 = zzbfe.zzebp;
                            final int mSize2 = this.mSize;
                            int j = 0;
                            while (true) {
                                while (j < mSize2) {
                                    if (!zzebp[j].equals(zzebp2[j])) {
                                        final int n2 = 0;
                                        if (n2 == 0) {
                                            return false;
                                        }
                                        return true;
                                    }
                                    else {
                                        ++j;
                                    }
                                }
                                final int n2 = 1;
                                continue;
                            }
                        }
                        return false;
                    }
                }
                final int n = 1;
                continue;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int n = 17;
        for (int i = 0; i < this.mSize; ++i) {
            n = (n * 31 + this.zzebo[i]) * 31 + this.zzebp[i].hashCode();
        }
        return n;
    }
    
    final int size() {
        return this.mSize;
    }
    
    final void zza(final int n, final zzbff zzbff) {
        final int zzdh = this.zzdh(n);
        if (zzdh >= 0) {
            this.zzebp[zzdh] = zzbff;
            return;
        }
        final int n2 = ~zzdh;
        if (n2 < this.mSize && this.zzebp[n2] == zzbfe.zzebm) {
            this.zzebo[n2] = n;
            this.zzebp[n2] = zzbff;
            return;
        }
        if (this.mSize >= this.zzebo.length) {
            final int idealIntArraySize = idealIntArraySize(this.mSize + 1);
            final int[] zzebo = new int[idealIntArraySize];
            final zzbff[] zzebp = new zzbff[idealIntArraySize];
            System.arraycopy(this.zzebo, 0, zzebo, 0, this.zzebo.length);
            System.arraycopy(this.zzebp, 0, zzebp, 0, this.zzebp.length);
            this.zzebo = zzebo;
            this.zzebp = zzebp;
        }
        if (this.mSize - n2 != 0) {
            System.arraycopy(this.zzebo, n2, this.zzebo, n2 + 1, this.mSize - n2);
            System.arraycopy(this.zzebp, n2, this.zzebp, n2 + 1, this.mSize - n2);
        }
        this.zzebo[n2] = n;
        this.zzebp[n2] = zzbff;
        ++this.mSize;
    }
    
    final zzbff zzdf(int zzdh) {
        zzdh = this.zzdh(zzdh);
        if (zzdh < 0 || this.zzebp[zzdh] == zzbfe.zzebm) {
            return null;
        }
        return this.zzebp[zzdh];
    }
    
    final zzbff zzdg(final int n) {
        return this.zzebp[n];
    }
}
