// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbay extends zzbab<Double> implements zzbbt<Double>, RandomAccess
{
    private static final zzbay zzdqo;
    private int size;
    private double[] zzdqp;
    
    static {
        (zzdqo = new zzbay()).zzaaz();
    }
    
    zzbay() {
        this(new double[10], 0);
    }
    
    private zzbay(final double[] zzdqp, final int size) {
        this.zzdqp = zzdqp;
        this.size = size;
    }
    
    private final void zzbk(final int n) {
        if (n < 0 || n >= this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
    }
    
    private final String zzbl(final int n) {
        return new StringBuilder(35).append("Index:").append(n).append(", Size:").append(this.size).toString();
    }
    
    private final void zzc(final int n, final double n2) {
        this.zzaba();
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
        if (this.size < this.zzdqp.length) {
            System.arraycopy(this.zzdqp, n, this.zzdqp, n + 1, this.size - n);
        }
        else {
            final double[] zzdqp = new double[this.size * 3 / 2 + 1];
            System.arraycopy(this.zzdqp, 0, zzdqp, 0, n);
            System.arraycopy(this.zzdqp, n, zzdqp, n + 1, this.size - n);
            this.zzdqp = zzdqp;
        }
        this.zzdqp[n] = n2;
        ++this.size;
        ++this.modCount;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends Double> collection) {
        boolean addAll = false;
        this.zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbay)) {
            addAll = super.addAll(collection);
        }
        else {
            final zzbay zzbay = (zzbay)collection;
            if (zzbay.size != 0) {
                if (Integer.MAX_VALUE - this.size < zzbay.size) {
                    throw new OutOfMemoryError();
                }
                final int size = this.size + zzbay.size;
                if (size > this.zzdqp.length) {
                    this.zzdqp = Arrays.copyOf(this.zzdqp, size);
                }
                System.arraycopy(zzbay.zzdqp, 0, this.zzdqp, this.size, zzbay.size);
                this.size = size;
                ++this.modCount;
                return true;
            }
        }
        return addAll;
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            if (!(o instanceof zzbay)) {
                return super.equals(o);
            }
            final zzbay zzbay = (zzbay)o;
            b2 = b;
            if (this.size == zzbay.size) {
                final double[] zzdqp = zzbay.zzdqp;
                for (int i = 0; i < this.size; ++i) {
                    b2 = b;
                    if (this.zzdqp[i] != zzdqp[i]) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        int n = 1;
        for (int i = 0; i < this.size; ++i) {
            n = n * 31 + zzbbq.zzv(Double.doubleToLongBits(this.zzdqp[i]));
        }
        return n;
    }
    
    @Override
    public final boolean remove(final Object o) {
        final boolean b = false;
        this.zzaba();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.size) {
                break;
            }
            if (o.equals(this.zzdqp[n])) {
                System.arraycopy(this.zzdqp, n + 1, this.zzdqp, n, this.size - n);
                --this.size;
                ++this.modCount;
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    protected final void removeRange(final int n, final int n2) {
        this.zzaba();
        if (n2 < n) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzdqp, n2, this.zzdqp, n, this.size - n2);
        this.size -= n2 - n;
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
    
    public final void zzd(final double n) {
        this.zzc(this.size, n);
    }
}
