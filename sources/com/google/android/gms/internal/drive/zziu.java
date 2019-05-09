package com.google.android.gms.internal.drive;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zziu implements Cloneable {
    private Object value;
    private zzis<?, ?> zznc;
    private List<zziz> zznd = new ArrayList();

    zziu() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzaq()];
        zza(zzip.zzb(bArr));
        return bArr;
    }

    private final zziu zzbj() {
        zziu zziu = new zziu();
        try {
            zziu.zznc = this.zznc;
            if (this.zznd == null) {
                zziu.zznd = null;
            } else {
                zziu.zznd.addAll(this.zznd);
            }
            if (this.value != null) {
                if (this.value instanceof zzix) {
                    zziu.value = (zzix) ((zzix) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zziu.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zziu.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zziu.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zziu.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zziu.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zziu.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zziu.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzix[]) {
                    zzix[] zzixArr = (zzix[]) this.value;
                    r4 = new zzix[zzixArr.length];
                    zziu.value = r4;
                    for (r2 = 0; r2 < zzixArr.length; r2++) {
                        r4[r2] = (zzix) zzixArr[r2].clone();
                    }
                }
            }
            return zziu;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzbj();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziu)) {
            return false;
        }
        zziu zziu = (zziu) obj;
        if (this.value != null && zziu.value != null) {
            return this.zznc == zziu.zznc ? !this.zznc.zzmx.isArray() ? this.value.equals(zziu.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zziu.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zziu.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zziu.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zziu.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zziu.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zziu.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zziu.value) : false;
        } else {
            if (this.zznd != null && zziu.zznd != null) {
                return this.zznd.equals(zziu.zznd);
            }
            try {
                return Arrays.equals(toByteArray(), zziu.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzip zzip) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zziz zziz : this.zznd) {
            zzip.zzp(zziz.tag);
            zzip.zzc(zziz.zzng);
        }
    }

    final void zza(zziz zziz) throws IOException {
        if (this.zznd != null) {
            this.zznd.add(zziz);
        } else if (this.value instanceof zzix) {
            byte[] bArr = zziz.zzng;
            zzio zza = zzio.zza(bArr, 0, bArr.length);
            int zzbe = zza.zzbe();
            if (zzbe != bArr.length - zzip.zzm(zzbe)) {
                throw zziw.zzbk();
            }
            zzix zza2 = ((zzix) this.value).zza(zza);
            this.zznc = this.zznc;
            this.value = zza2;
            this.zznd = null;
        } else if (this.value instanceof zzix[]) {
            Collections.singletonList(zziz);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zziz);
            throw new NoSuchMethodError();
        }
    }

    final int zzaq() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zziz zziz : this.zznd) {
            i = (zziz.zzng.length + (zzip.zzq(zziz.tag) + 0)) + i;
        }
        return i;
    }
}
