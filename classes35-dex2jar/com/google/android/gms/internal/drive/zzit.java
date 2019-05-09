// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

public final class zzit implements Cloneable
{
    private static final zziu zzmy;
    private int mSize;
    private boolean zzmz;
    private int[] zzna;
    private zziu[] zznb;
    
    static {
        zzmy = new zziu();
    }
    
    zzit() {
        this(10);
    }
    
    private zzit(int idealIntArraySize) {
        this.zzmz = false;
        idealIntArraySize = idealIntArraySize(idealIntArraySize);
        this.zzna = new int[idealIntArraySize];
        this.zznb = new zziu[idealIntArraySize];
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
    
    private final int zzt(final int n) {
        final int mSize = this.mSize;
        int i = 0;
        int n2 = mSize - 1;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int n4 = this.zzna[n3];
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
            if (!(o instanceof zzit)) {
                return false;
            }
            final zzit zzit = (zzit)o;
            if (this.mSize != zzit.mSize) {
                return false;
            }
            final int[] zzna = this.zzna;
            final int[] zzna2 = zzit.zzna;
            while (true) {
                for (int mSize = this.mSize, i = 0; i < mSize; ++i) {
                    if (zzna[i] != zzna2[i]) {
                        final int n = 0;
                        if (n != 0) {
                            final zziu[] zznb = this.zznb;
                            final zziu[] zznb2 = zzit.zznb;
                            final int mSize2 = this.mSize;
                            int j = 0;
                            while (true) {
                                while (j < mSize2) {
                                    if (!zznb[j].equals(zznb2[j])) {
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
            n = (n * 31 + this.zzna[i]) * 31 + this.zznb[i].hashCode();
        }
        return n;
    }
    
    public final boolean isEmpty() {
        return this.mSize == 0;
    }
    
    final int size() {
        return this.mSize;
    }
    
    final void zza(final int n, final zziu zziu) {
        final int zzt = this.zzt(n);
        if (zzt >= 0) {
            this.zznb[zzt] = zziu;
            return;
        }
        final int n2 = ~zzt;
        if (n2 < this.mSize && this.zznb[n2] == zzit.zzmy) {
            this.zzna[n2] = n;
            this.zznb[n2] = zziu;
            return;
        }
        if (this.mSize >= this.zzna.length) {
            final int idealIntArraySize = idealIntArraySize(this.mSize + 1);
            final int[] zzna = new int[idealIntArraySize];
            final zziu[] zznb = new zziu[idealIntArraySize];
            System.arraycopy(this.zzna, 0, zzna, 0, this.zzna.length);
            System.arraycopy(this.zznb, 0, zznb, 0, this.zznb.length);
            this.zzna = zzna;
            this.zznb = zznb;
        }
        if (this.mSize - n2 != 0) {
            System.arraycopy(this.zzna, n2, this.zzna, n2 + 1, this.mSize - n2);
            System.arraycopy(this.zznb, n2, this.zznb, n2 + 1, this.mSize - n2);
        }
        this.zzna[n2] = n;
        this.zznb[n2] = zziu;
        ++this.mSize;
    }
    
    final zziu zzr(int zzt) {
        zzt = this.zzt(zzt);
        if (zzt < 0 || this.zznb[zzt] == zzit.zzmy) {
            return null;
        }
        return this.zznb[zzt];
    }
    
    final zziu zzs(final int n) {
        return this.zznb[n];
    }
}
