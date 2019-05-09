package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zzio {
    private final byte[] buffer;
    private final int zzmn;
    private final int zzmo;
    private int zzmp;
    private int zzmq;
    private int zzmr;
    private int zzms = Integer.MAX_VALUE;
    private int zzmt = 64;
    private int zzmu = 67108864;

    private zzio(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzmn = i;
        int i3 = i + i2;
        this.zzmp = i3;
        this.zzmo = i3;
        this.zzmq = i;
    }

    public static zzio zza(byte[] bArr, int i, int i2) {
        return new zzio(bArr, 0, i2);
    }

    private final byte zzbg() throws IOException {
        if (this.zzmq == this.zzmp) {
            throw zziw.zzbk();
        }
        byte[] bArr = this.buffer;
        int i = this.zzmq;
        this.zzmq = i + 1;
        return bArr[i];
    }

    private final void zzl(int i) throws IOException {
        if (i < 0) {
            throw zziw.zzbl();
        } else if (this.zzmq + i > this.zzms) {
            zzl(this.zzms - this.zzmq);
            throw zziw.zzbk();
        } else if (i <= this.zzmp - this.zzmq) {
            this.zzmq += i;
        } else {
            throw zziw.zzbk();
        }
    }

    public final int getPosition() {
        return this.zzmq - this.zzmn;
    }

    public final String readString() throws IOException {
        int zzbe = zzbe();
        if (zzbe < 0) {
            throw zziw.zzbl();
        } else if (zzbe > this.zzmp - this.zzmq) {
            throw zziw.zzbk();
        } else {
            String str = new String(this.buffer, this.zzmq, zzbe, zziv.UTF_8);
            this.zzmq = zzbe + this.zzmq;
            return str;
        }
    }

    public final byte[] zza(int i, int i2) {
        if (i2 == 0) {
            return zzja.zzns;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.buffer, this.zzmn + i, obj, 0, i2);
        return obj;
    }

    public final int zzbd() throws IOException {
        if (this.zzmq == this.zzmp) {
            this.zzmr = 0;
            return 0;
        }
        this.zzmr = zzbe();
        if (this.zzmr != 0) {
            return this.zzmr;
        }
        throw new zziw("Protocol message contained an invalid tag (zero).");
    }

    public final int zzbe() throws IOException {
        byte zzbg = zzbg();
        if (zzbg >= (byte) 0) {
            return zzbg;
        }
        int i = zzbg & 127;
        byte zzbg2 = zzbg();
        if (zzbg2 >= (byte) 0) {
            return i | (zzbg2 << 7);
        }
        i |= (zzbg2 & 127) << 7;
        zzbg2 = zzbg();
        if (zzbg2 >= (byte) 0) {
            return i | (zzbg2 << 14);
        }
        i |= (zzbg2 & 127) << 14;
        zzbg2 = zzbg();
        if (zzbg2 >= (byte) 0) {
            return i | (zzbg2 << 21);
        }
        i |= (zzbg2 & 127) << 21;
        zzbg2 = zzbg();
        i |= zzbg2 << 28;
        if (zzbg2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (zzbg() >= (byte) 0) {
                return i;
            }
        }
        throw zziw.zzbm();
    }

    public final long zzbf() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzbg = zzbg();
            j |= ((long) (zzbg & 127)) << i;
            if ((zzbg & 128) == 0) {
                return j;
            }
        }
        throw zziw.zzbm();
    }

    public final void zzj(int i) throws zziw {
        if (this.zzmr != i) {
            throw new zziw("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzk(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzbe();
                return true;
            case 1:
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                return true;
            case 2:
                zzl(zzbe());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzbg();
                zzbg();
                zzbg();
                zzbg();
                return true;
            default:
                throw new zziw("Protocol message tag had invalid wire type.");
        }
        int zzbd;
        do {
            zzbd = zzbd();
            if (zzbd != 0) {
            }
            zzj(((i >>> 3) << 3) | 4);
            return true;
        } while (zzk(zzbd));
        zzj(((i >>> 3) << 3) | 4);
        return true;
    }
}
