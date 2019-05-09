package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzbff implements Cloneable {
    private Object value;
    private zzbfd<?, ?> zzebq;
    private List<zzbfk> zzebr = new ArrayList();

    zzbff() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzr()];
        zza(zzbfa.zzu(bArr));
        return bArr;
    }

    private final zzbff zzagp() {
        zzbff zzbff = new zzbff();
        try {
            zzbff.zzebq = this.zzebq;
            if (this.zzebr == null) {
                zzbff.zzebr = null;
            } else {
                zzbff.zzebr.addAll(this.zzebr);
            }
            if (this.value != null) {
                if (this.value instanceof zzbfi) {
                    zzbff.value = (zzbfi) ((zzbfi) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbff.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zzbff.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbff.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbff.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbff.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbff.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbff.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbfi[]) {
                    zzbfi[] zzbfiArr = (zzbfi[]) this.value;
                    r4 = new zzbfi[zzbfiArr.length];
                    zzbff.value = r4;
                    for (r2 = 0; r2 < zzbfiArr.length; r2++) {
                        r4[r2] = (zzbfi) zzbfiArr[r2].clone();
                    }
                }
            }
            return zzbff;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzagp();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbff)) {
            return false;
        }
        zzbff zzbff = (zzbff) obj;
        if (this.value != null && zzbff.value != null) {
            return this.zzebq == zzbff.zzebq ? !this.zzebq.zzebl.isArray() ? this.value.equals(zzbff.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbff.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbff.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbff.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbff.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbff.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbff.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbff.value) : false;
        } else {
            if (this.zzebr != null && zzbff.zzebr != null) {
                return this.zzebr.equals(zzbff.zzebr);
            }
            try {
                return Arrays.equals(toByteArray(), zzbff.toByteArray());
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

    final void zza(zzbfa zzbfa) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zzbfk zzbfk : this.zzebr) {
            zzbfa.zzde(zzbfk.tag);
            zzbfa.zzw(zzbfk.zzdpw);
        }
    }

    final void zza(zzbfk zzbfk) throws IOException {
        if (this.zzebr != null) {
            this.zzebr.add(zzbfk);
        } else if (this.value instanceof zzbfi) {
            byte[] bArr = zzbfk.zzdpw;
            zzbez zzi = zzbez.zzi(bArr, 0, bArr.length);
            int zzacc = zzi.zzacc();
            if (zzacc != bArr.length - zzbfa.zzce(zzacc)) {
                throw zzbfh.zzagq();
            }
            zzbfi zza = ((zzbfi) this.value).zza(zzi);
            this.zzebq = this.zzebq;
            this.value = zza;
            this.zzebr = null;
        } else if (this.value instanceof zzbfi[]) {
            Collections.singletonList(zzbfk);
            throw new NoSuchMethodError();
        } else {
            Collections.singletonList(zzbfk);
            throw new NoSuchMethodError();
        }
    }

    final int zzr() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int i = 0;
        for (zzbfk zzbfk : this.zzebr) {
            i = (zzbfk.zzdpw.length + (zzbfa.zzcl(zzbfk.tag) + 0)) + i;
        }
        return i;
    }
}
