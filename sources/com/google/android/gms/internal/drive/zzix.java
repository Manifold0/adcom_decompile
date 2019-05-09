package com.google.android.gms.internal.drive;

import java.io.IOException;

public abstract class zzix {
    protected volatile int zznf = -1;

    public static final <T extends zzix> T zza(T t, byte[] bArr, int i, int i2) throws zziw {
        try {
            zzio zza = zzio.zza(bArr, 0, i2);
            t.zza(zza);
            zza.zzj(0);
            return t;
        } catch (zziw e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zza(zzix zzix) {
        int zzaq = zzix.zzaq();
        zzix.zznf = zzaq;
        byte[] bArr = new byte[zzaq];
        try {
            zzip zzb = zzip.zzb(bArr, 0, bArr.length);
            zzix.zza(zzb);
            zzb.zzbh();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzbi();
    }

    public String toString() {
        return zziy.zzb(this);
    }

    public abstract zzix zza(zzio zzio) throws IOException;

    public void zza(zzip zzip) throws IOException {
    }

    protected int zzaq() {
        return 0;
    }

    public zzix zzbi() throws CloneNotSupportedException {
        return (zzix) super.clone();
    }
}
