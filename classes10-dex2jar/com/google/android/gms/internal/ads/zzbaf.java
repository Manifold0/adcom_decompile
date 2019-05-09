// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbaf extends zzbab<Boolean> implements zzbbt<Boolean>, RandomAccess
{
    private static final zzbaf zzdpo;
    private int size;
    private boolean[] zzdpp;
    
    static {
        (zzdpo = new zzbaf()).zzaaz();
    }
    
    zzbaf() {
        this(new boolean[10], 0);
    }
    
    private zzbaf(final boolean[] zzdpp, final int size) {
        this.zzdpp = zzdpp;
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
    
    private final void zze(final int n, final boolean b) {
        this.zzaba();
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
        if (this.size < this.zzdpp.length) {
            System.arraycopy(this.zzdpp, n, this.zzdpp, n + 1, this.size - n);
        }
        else {
            final boolean[] zzdpp = new boolean[this.size * 3 / 2 + 1];
            System.arraycopy(this.zzdpp, 0, zzdpp, 0, n);
            System.arraycopy(this.zzdpp, n, zzdpp, n + 1, this.size - n);
            this.zzdpp = zzdpp;
        }
        this.zzdpp[n] = b;
        ++this.size;
        ++this.modCount;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends Boolean> collection) {
        boolean addAll = false;
        this.zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbaf)) {
            addAll = super.addAll(collection);
        }
        else {
            final zzbaf zzbaf = (zzbaf)collection;
            if (zzbaf.size != 0) {
                if (Integer.MAX_VALUE - this.size < zzbaf.size) {
                    throw new OutOfMemoryError();
                }
                final int size = this.size + zzbaf.size;
                if (size > this.zzdpp.length) {
                    this.zzdpp = Arrays.copyOf(this.zzdpp, size);
                }
                System.arraycopy(zzbaf.zzdpp, 0, this.zzdpp, this.size, zzbaf.size);
                this.size = size;
                ++this.modCount;
                return true;
            }
        }
        return addAll;
    }
    
    public final void addBoolean(final boolean b) {
        this.zze(this.size, b);
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            if (!(o instanceof zzbaf)) {
                return super.equals(o);
            }
            final zzbaf zzbaf = (zzbaf)o;
            b2 = b;
            if (this.size == zzbaf.size) {
                final boolean[] zzdpp = zzbaf.zzdpp;
                for (int i = 0; i < this.size; ++i) {
                    b2 = b;
                    if (this.zzdpp[i] != zzdpp[i]) {
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
            n = n * 31 + zzbbq.zzar(this.zzdpp[i]);
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
            if (o.equals(this.zzdpp[n])) {
                System.arraycopy(this.zzdpp, n + 1, this.zzdpp, n, this.size - n);
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
        System.arraycopy(this.zzdpp, n2, this.zzdpp, n, this.size - n2);
        this.size -= n2 - n;
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
}
