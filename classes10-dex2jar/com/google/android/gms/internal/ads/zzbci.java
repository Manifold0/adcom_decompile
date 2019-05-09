// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbci extends zzbab<Long> implements zzbbt<Long>, RandomAccess
{
    private static final zzbci zzdvt;
    private int size;
    private long[] zzdvu;
    
    static {
        (zzdvt = new zzbci()).zzaaz();
    }
    
    zzbci() {
        this(new long[10], 0);
    }
    
    private zzbci(final long[] zzdvu, final int size) {
        this.zzdvu = zzdvu;
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
    
    private final void zzk(final int n, final long n2) {
        this.zzaba();
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
        if (this.size < this.zzdvu.length) {
            System.arraycopy(this.zzdvu, n, this.zzdvu, n + 1, this.size - n);
        }
        else {
            final long[] zzdvu = new long[this.size * 3 / 2 + 1];
            System.arraycopy(this.zzdvu, 0, zzdvu, 0, n);
            System.arraycopy(this.zzdvu, n, zzdvu, n + 1, this.size - n);
            this.zzdvu = zzdvu;
        }
        this.zzdvu[n] = n2;
        ++this.size;
        ++this.modCount;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends Long> collection) {
        boolean addAll = false;
        this.zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbci)) {
            addAll = super.addAll(collection);
        }
        else {
            final zzbci zzbci = (zzbci)collection;
            if (zzbci.size != 0) {
                if (Integer.MAX_VALUE - this.size < zzbci.size) {
                    throw new OutOfMemoryError();
                }
                final int size = this.size + zzbci.size;
                if (size > this.zzdvu.length) {
                    this.zzdvu = Arrays.copyOf(this.zzdvu, size);
                }
                System.arraycopy(zzbci.zzdvu, 0, this.zzdvu, this.size, zzbci.size);
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
            if (!(o instanceof zzbci)) {
                return super.equals(o);
            }
            final zzbci zzbci = (zzbci)o;
            b2 = b;
            if (this.size == zzbci.size) {
                final long[] zzdvu = zzbci.zzdvu;
                for (int i = 0; i < this.size; ++i) {
                    b2 = b;
                    if (this.zzdvu[i] != zzdvu[i]) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return b2;
    }
    
    public final long getLong(final int n) {
        this.zzbk(n);
        return this.zzdvu[n];
    }
    
    @Override
    public final int hashCode() {
        int n = 1;
        for (int i = 0; i < this.size; ++i) {
            n = n * 31 + zzbbq.zzv(this.zzdvu[i]);
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
            if (o.equals(this.zzdvu[n])) {
                System.arraycopy(this.zzdvu, n + 1, this.zzdvu, n, this.size - n);
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
        System.arraycopy(this.zzdvu, n2, this.zzdvu, n, this.size - n2);
        this.size -= n2 - n;
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
    
    public final void zzw(final long n) {
        this.zzk(this.size, n);
    }
}
