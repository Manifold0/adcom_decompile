package com.google.android.gms.internal.drive;

public final class zzit implements Cloneable {
    private static final zziu zzmy = new zziu();
    private int mSize;
    private boolean zzmz;
    private int[] zzna;
    private zziu[] zznb;

    zzit() {
        this(10);
    }

    private zzit(int i) {
        this.zzmz = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzna = new int[idealIntArraySize];
        this.zznb = new zziu[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
        }
        return i2 / 4;
    }

    private final int zzt(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzna[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzit zzit = new zzit(i);
        System.arraycopy(this.zzna, 0, zzit.zzna, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zznb[i2] != null) {
                zzit.zznb[i2] = (zziu) this.zznb[i2].clone();
            }
        }
        zzit.mSize = i;
        return zzit;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzit)) {
            return false;
        }
        zzit zzit = (zzit) obj;
        if (this.mSize != zzit.mSize) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.zzna;
        int[] iArr2 = zzit.zzna;
        int i2 = this.mSize;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            zziu[] zziuArr = this.zznb;
            zziu[] zziuArr2 = zzit.zznb;
            i2 = this.mSize;
            for (i = 0; i < i2; i++) {
                if (!zziuArr[i].equals(zziuArr2[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzna[i2]) * 31) + this.zznb[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zziu zziu) {
        int zzt = zzt(i);
        if (zzt >= 0) {
            this.zznb[zzt] = zziu;
            return;
        }
        zzt ^= -1;
        if (zzt >= this.mSize || this.zznb[zzt] != zzmy) {
            if (this.mSize >= this.zzna.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zziu[idealIntArraySize];
                System.arraycopy(this.zzna, 0, obj, 0, this.zzna.length);
                System.arraycopy(this.zznb, 0, obj2, 0, this.zznb.length);
                this.zzna = obj;
                this.zznb = obj2;
            }
            if (this.mSize - zzt != 0) {
                System.arraycopy(this.zzna, zzt, this.zzna, zzt + 1, this.mSize - zzt);
                System.arraycopy(this.zznb, zzt, this.zznb, zzt + 1, this.mSize - zzt);
            }
            this.zzna[zzt] = i;
            this.zznb[zzt] = zziu;
            this.mSize++;
            return;
        }
        this.zzna[zzt] = i;
        this.zznb[zzt] = zziu;
    }

    final zziu zzr(int i) {
        int zzt = zzt(i);
        return (zzt < 0 || this.zznb[zzt] == zzmy) ? null : this.zznb[zzt];
    }

    final zziu zzs(int i) {
        return this.zznb[i];
    }
}
