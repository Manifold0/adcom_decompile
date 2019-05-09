// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbbm extends zzbab<Float> implements zzbbt<Float>, RandomAccess
{
    private static final zzbbm zzdtq;
    private int size;
    private float[] zzdtr;
    
    static {
        (zzdtq = new zzbbm()).zzaaz();
    }
    
    zzbbm() {
        this(new float[10], 0);
    }
    
    private zzbbm(final float[] zzdtr, final int size) {
        this.zzdtr = zzdtr;
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
    
    private final void zzc(final int n, final float n2) {
        this.zzaba();
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
        if (this.size < this.zzdtr.length) {
            System.arraycopy(this.zzdtr, n, this.zzdtr, n + 1, this.size - n);
        }
        else {
            final float[] zzdtr = new float[this.size * 3 / 2 + 1];
            System.arraycopy(this.zzdtr, 0, zzdtr, 0, n);
            System.arraycopy(this.zzdtr, n, zzdtr, n + 1, this.size - n);
            this.zzdtr = zzdtr;
        }
        this.zzdtr[n] = n2;
        ++this.size;
        ++this.modCount;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends Float> collection) {
        boolean addAll = false;
        this.zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbbm)) {
            addAll = super.addAll(collection);
        }
        else {
            final zzbbm zzbbm = (zzbbm)collection;
            if (zzbbm.size != 0) {
                if (Integer.MAX_VALUE - this.size < zzbbm.size) {
                    throw new OutOfMemoryError();
                }
                final int size = this.size + zzbbm.size;
                if (size > this.zzdtr.length) {
                    this.zzdtr = Arrays.copyOf(this.zzdtr, size);
                }
                System.arraycopy(zzbbm.zzdtr, 0, this.zzdtr, this.size, zzbbm.size);
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
            if (!(o instanceof zzbbm)) {
                return super.equals(o);
            }
            final zzbbm zzbbm = (zzbbm)o;
            b2 = b;
            if (this.size == zzbbm.size) {
                final float[] zzdtr = zzbbm.zzdtr;
                for (int i = 0; i < this.size; ++i) {
                    b2 = b;
                    if (this.zzdtr[i] != zzdtr[i]) {
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
            n = n * 31 + Float.floatToIntBits(this.zzdtr[i]);
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
            if (o.equals(this.zzdtr[n])) {
                System.arraycopy(this.zzdtr, n + 1, this.zzdtr, n, this.size - n);
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
        System.arraycopy(this.zzdtr, n2, this.zzdtr, n, this.size - n2);
        this.size -= n2 - n;
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
    
    public final void zzd(final float n) {
        this.zzc(this.size, n);
    }
}
