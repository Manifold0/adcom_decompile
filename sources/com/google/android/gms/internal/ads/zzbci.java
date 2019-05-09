package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbci extends zzbab<Long> implements zzbbt<Long>, RandomAccess {
    private static final zzbci zzdvt;
    private int size;
    private long[] zzdvu;

    static {
        zzbab zzbci = new zzbci();
        zzdvt = zzbci;
        zzbci.zzaaz();
    }

    zzbci() {
        this(new long[10], 0);
    }

    private zzbci(long[] jArr, int i) {
        this.zzdvu = jArr;
        this.size = i;
    }

    private final void zzbk(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
    }

    private final String zzbl(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    private final void zzk(int i, long j) {
        zzaba();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzbl(i));
        }
        if (this.size < this.zzdvu.length) {
            System.arraycopy(this.zzdvu, i, this.zzdvu, i + 1, this.size - i);
        } else {
            Object obj = new long[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzdvu, 0, obj, 0, i);
            System.arraycopy(this.zzdvu, i, obj, i + 1, this.size - i);
            this.zzdvu = obj;
        }
        this.zzdvu[i] = j;
        this.size++;
        this.modCount++;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzaba();
        zzbbq.checkNotNull(collection);
        if (!(collection instanceof zzbci)) {
            return super.addAll(collection);
        }
        zzbci zzbci = (zzbci) collection;
        if (zzbci.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzbci.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzbci.size;
        if (i > this.zzdvu.length) {
            this.zzdvu = Arrays.copyOf(this.zzdvu, i);
        }
        System.arraycopy(zzbci.zzdvu, 0, this.zzdvu, this.size, zzbci.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbci)) {
            return super.equals(obj);
        }
        zzbci zzbci = (zzbci) obj;
        if (this.size != zzbci.size) {
            return false;
        }
        long[] jArr = zzbci.zzdvu;
        for (int i = 0; i < this.size; i++) {
            if (this.zzdvu[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    public final long getLong(int i) {
        zzbk(i);
        return this.zzdvu[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzbbq.zzv(this.zzdvu[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzaba();
        zzbk(i);
        long j = this.zzdvu[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzdvu, i + 1, this.zzdvu, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        zzaba();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzdvu[i]))) {
                System.arraycopy(this.zzdvu, i + 1, this.zzdvu, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    protected final void removeRange(int i, int i2) {
        zzaba();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzdvu, i2, this.zzdvu, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzaba();
        zzbk(i);
        long j = this.zzdvu[i];
        this.zzdvu[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i >= this.size) {
            return new zzbci(Arrays.copyOf(this.zzdvu, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzw(long j) {
        zzk(this.size, j);
    }
}
