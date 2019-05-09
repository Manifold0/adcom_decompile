// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbbp extends zzbab<Integer> implements zzbbt<Integer>, RandomAccess
{
    private static final zzbbp zzduo;
    private int size;
    private int[] zzdup;
    
    static {
        (zzduo = new zzbbp()).zzaaz();
    }
    
    zzbbp() {
        this(new int[10], 0);
    }
    
    private zzbbp(final int[] zzdup, final int size) {
        this.zzdup = zzdup;
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
    
    private final void zzy(final int n, final int n2) {
        this.zzaba();
        if (n < 0 || n > this.size) {
            throw new IndexOutOfBoundsException(this.zzbl(n));
        }
        if (this.size < this.zzdup.length) {
            System.arraycopy(this.zzdup, n, this.zzdup, n + 1, this.size - n);
        }
        else {
            final int[] zzdup = new int[this.size * 3 / 2 + 1];
            System.arraycopy(this.zzdup, 0, zzdup, 0, n);
            System.arraycopy(this.zzdup, n, zzdup, n + 1, this.size - n);
            this.zzdup = zzdup;
        }
        this.zzdup[n] = n2;
        ++this.size;
        ++this.modCount;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends Integer> collection) {
        boolean addAll = false;
        this.zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbbp)) {
            addAll = super.addAll(collection);
        }
        else {
            final zzbbp zzbbp = (zzbbp)collection;
            if (zzbbp.size != 0) {
                if (Integer.MAX_VALUE - this.size < zzbbp.size) {
                    throw new OutOfMemoryError();
                }
                final int size = this.size + zzbbp.size;
                if (size > this.zzdup.length) {
                    this.zzdup = Arrays.copyOf(this.zzdup, size);
                }
                System.arraycopy(zzbbp.zzdup, 0, this.zzdup, this.size, zzbbp.size);
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
            if (!(o instanceof zzbbp)) {
                return super.equals(o);
            }
            final zzbbp zzbbp = (zzbbp)o;
            b2 = b;
            if (this.size == zzbbp.size) {
                final int[] zzdup = zzbbp.zzdup;
                for (int i = 0; i < this.size; ++i) {
                    b2 = b;
                    if (this.zzdup[i] != zzdup[i]) {
                        return b2;
                    }
                }
                return true;
            }
        }
        return b2;
    }
    
    public final int getInt(final int n) {
        this.zzbk(n);
        return this.zzdup[n];
    }
    
    @Override
    public final int hashCode() {
        int n = 1;
        for (int i = 0; i < this.size; ++i) {
            n = n * 31 + this.zzdup[i];
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
            if (o.equals(this.zzdup[n])) {
                System.arraycopy(this.zzdup, n + 1, this.zzdup, n, this.size - n);
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
        System.arraycopy(this.zzdup, n2, this.zzdup, n, this.size - n2);
        this.size -= n2 - n;
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.size;
    }
    
    public final void zzco(final int n) {
        this.zzy(this.size, n);
    }
}
