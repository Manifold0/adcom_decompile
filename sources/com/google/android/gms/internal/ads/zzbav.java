package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbav extends zzbag {
    private static final Logger logger = Logger.getLogger(zzbav.class.getName());
    private static final boolean zzdqm = zzbek.zzagf();
    zzbax zzdqn = this;

    static class zza extends zzbav {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if (((i2 | 0) | (bArr.length - (i2 + 0))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            } else {
                this.buffer = bArr;
                this.offset = 0;
                this.position = 0;
                this.limit = i2 + 0;
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzl(i, 0);
            zzm(j);
        }

        public final void zza(int i, zzbah zzbah) throws IOException {
            zzl(i, 2);
            zzan(zzbah);
        }

        public final void zza(int i, zzbcu zzbcu) throws IOException {
            zzl(1, 3);
            zzn(2, i);
            zzl(3, 2);
            zze(zzbcu);
            zzl(1, 4);
        }

        final void zza(int i, zzbcu zzbcu, zzbdm zzbdm) throws IOException {
            zzl(i, 2);
            zzazy zzazy = (zzazy) zzbcu;
            int zzaaw = zzazy.zzaaw();
            if (zzaaw == -1) {
                zzaaw = zzbdm.zzy(zzazy);
                zzazy.zzbj(zzaaw);
            }
            zzca(zzaaw);
            zzbdm.zza(zzbcu, this.zzdqn);
        }

        public final int zzack() {
            return this.limit - this.position;
        }

        public final void zzan(zzbah zzbah) throws IOException {
            zzca(zzbah.size());
            zzbah.zza((zzbag) this);
        }

        public final void zzb(int i, zzbah zzbah) throws IOException {
            zzl(1, 3);
            zzn(2, i);
            zza(3, zzbah);
            zzl(1, 4);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzbz(int i) throws IOException {
            if (i >= 0) {
                zzca(i);
            } else {
                zzm((long) i);
            }
        }

        public final void zzc(int i, long j) throws IOException {
            zzl(i, 1);
            zzo(j);
        }

        public final void zzca(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!zzbav.zzdqm || zzack() < 10) {
                while ((i & -128) != 0) {
                    try {
                        bArr = this.buffer;
                        i2 = this.position;
                        this.position = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (Throwable e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                return;
            }
            while ((i & -128) != 0) {
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                zzbek.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzbek.zza(bArr, (long) i2, (byte) i);
        }

        public final void zzcc(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 8);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 16);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = i >> 24;
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zze(zzbcu zzbcu) throws IOException {
            zzca(zzbcu.zzacw());
            zzbcu.zzb(this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzca(i2);
            write(bArr, 0, i2);
        }

        public final void zzen(String str) throws IOException {
            int i = this.position;
            try {
                int zzcf = zzbav.zzcf(str.length() * 3);
                int zzcf2 = zzbav.zzcf(str.length());
                if (zzcf2 == zzcf) {
                    this.position = i + zzcf2;
                    zzcf = zzbem.zza(str, this.buffer, this.position, zzack());
                    this.position = i;
                    zzca((zzcf - i) - zzcf2);
                    this.position = zzcf;
                    return;
                }
                zzca(zzbem.zza(str));
                this.position = zzbem.zza(str, this.buffer, this.position, zzack());
            } catch (zzbep e) {
                this.position = i;
                zza(str, e);
            } catch (Throwable e2) {
                throw new zzb(e2);
            }
        }

        public final void zzf(int i, String str) throws IOException {
            zzl(i, 2);
            zzen(str);
        }

        public final void zzf(int i, boolean z) throws IOException {
            int i2 = 0;
            zzl(i, 0);
            if (z) {
                i2 = 1;
            }
            zza((byte) i2);
        }

        public final void zzl(int i, int i2) throws IOException {
            zzca((i << 3) | i2);
        }

        public final void zzm(int i, int i2) throws IOException {
            zzl(i, 0);
            zzbz(i2);
        }

        public final void zzm(long j) throws IOException {
            byte[] bArr;
            int i;
            if (!zzbav.zzdqm || zzack() < 10) {
                while ((j & -128) != 0) {
                    try {
                        bArr = this.buffer;
                        i = this.position;
                        this.position = i + 1;
                        bArr[i] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (Throwable e) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                    }
                }
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                return;
            }
            while ((j & -128) != 0) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                zzbek.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzbek.zza(bArr, (long) i, (byte) ((int) j));
        }

        public final void zzn(int i, int i2) throws IOException {
            zzl(i, 0);
            zzca(i2);
        }

        public final void zzo(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 8));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 16));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 24));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 32));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 40));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 48));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 56));
            } catch (Throwable e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzp(int i, int i2) throws IOException {
            zzl(i, 5);
            zzcc(i2);
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzbav() {
    }

    public static int zza(int i, zzbcb zzbcb) {
        int zzcd = zzcd(i);
        int zzacw = zzbcb.zzacw();
        return zzcd + (zzacw + zzcf(zzacw));
    }

    public static int zza(zzbcb zzbcb) {
        int zzacw = zzbcb.zzacw();
        return zzacw + zzcf(zzacw);
    }

    static int zza(zzbcu zzbcu, zzbdm zzbdm) {
        zzazy zzazy = (zzazy) zzbcu;
        int zzaaw = zzazy.zzaaw();
        if (zzaaw == -1) {
            zzaaw = zzbdm.zzy(zzazy);
            zzazy.zzbj(zzaaw);
        }
        return zzaaw + zzcf(zzaaw);
    }

    public static int zzao(zzbah zzbah) {
        int size = zzbah.size();
        return size + zzcf(size);
    }

    public static int zzaq(boolean z) {
        return 1;
    }

    public static int zzb(int i, double d) {
        return zzcd(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzcd(i) + 4;
    }

    public static int zzb(int i, zzbcb zzbcb) {
        return ((zzcd(1) << 1) + zzr(2, i)) + zza(3, zzbcb);
    }

    public static int zzb(int i, zzbcu zzbcu) {
        return ((zzcd(1) << 1) + zzr(2, i)) + (zzcd(3) + zzf(zzbcu));
    }

    static int zzb(int i, zzbcu zzbcu, zzbdm zzbdm) {
        return zzcd(i) + zza(zzbcu, zzbdm);
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzc(float f) {
        return 4;
    }

    public static int zzc(int i, zzbah zzbah) {
        int zzcd = zzcd(i);
        int size = zzbah.size();
        return zzcd + (size + zzcf(size));
    }

    @Deprecated
    static int zzc(int i, zzbcu zzbcu, zzbdm zzbdm) {
        int zzcd = zzcd(i) << 1;
        zzazy zzazy = (zzazy) zzbcu;
        int zzaaw = zzazy.zzaaw();
        if (zzaaw == -1) {
            zzaaw = zzbdm.zzy(zzazy);
            zzazy.zzbj(zzaaw);
        }
        return zzaaw + zzcd;
    }

    public static int zzcd(int i) {
        return zzcf(i << 3);
    }

    public static int zzce(int i) {
        return i >= 0 ? zzcf(i) : 10;
    }

    public static int zzcf(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzcg(int i) {
        return zzcf(zzck(i));
    }

    public static int zzch(int i) {
        return 4;
    }

    public static int zzci(int i) {
        return 4;
    }

    public static int zzcj(int i) {
        return zzce(i);
    }

    private static int zzck(int i) {
        return (i << 1) ^ (i >> 31);
    }

    @Deprecated
    public static int zzcl(int i) {
        return zzcf(i);
    }

    public static int zzd(int i, long j) {
        return zzcd(i) + zzq(j);
    }

    public static int zzd(int i, zzbah zzbah) {
        return ((zzcd(1) << 1) + zzr(2, i)) + zzc(3, zzbah);
    }

    public static int zze(int i, long j) {
        return zzcd(i) + zzq(j);
    }

    public static int zzeo(String str) {
        int zza;
        try {
            zza = zzbem.zza(str);
        } catch (zzbep e) {
            zza = str.getBytes(zzbbq.UTF_8).length;
        }
        return zza + zzcf(zza);
    }

    public static int zzf(int i, long j) {
        return zzcd(i) + zzq(zzu(j));
    }

    public static int zzf(zzbcu zzbcu) {
        int zzacw = zzbcu.zzacw();
        return zzacw + zzcf(zzacw);
    }

    public static int zzg(int i, long j) {
        return zzcd(i) + 8;
    }

    public static int zzg(int i, String str) {
        return zzcd(i) + zzeo(str);
    }

    public static int zzg(int i, boolean z) {
        return zzcd(i) + 1;
    }

    @Deprecated
    public static int zzg(zzbcu zzbcu) {
        return zzbcu.zzacw();
    }

    public static int zzh(int i, long j) {
        return zzcd(i) + 8;
    }

    public static int zzp(long j) {
        return zzq(j);
    }

    public static int zzq(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public static int zzq(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        long j2;
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static zzbav zzq(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzr(int i, int i2) {
        return zzcd(i) + zzcf(i2);
    }

    public static int zzr(long j) {
        return zzq(zzu(j));
    }

    public static int zzr(byte[] bArr) {
        int length = bArr.length;
        return length + zzcf(length);
    }

    public static int zzs(int i, int i2) {
        return zzcd(i) + zzcf(zzck(i2));
    }

    public static int zzs(long j) {
        return 8;
    }

    public static int zzt(int i, int i2) {
        return zzcd(i) + 4;
    }

    public static int zzt(long j) {
        return 8;
    }

    public static int zzu(int i, int i2) {
        return zzcd(i) + 4;
    }

    private static long zzu(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzv(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzp(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbah zzbah) throws IOException;

    public abstract void zza(int i, zzbcu zzbcu) throws IOException;

    abstract void zza(int i, zzbcu zzbcu, zzbdm zzbdm) throws IOException;

    final void zza(String str, zzbep zzbep) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzbep);
        byte[] bytes = str.getBytes(zzbbq.UTF_8);
        try {
            zzca(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public abstract int zzack();

    public final void zzacl() {
        if (zzack() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzan(zzbah zzbah) throws IOException;

    public final void zzap(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public final void zzb(double d) throws IOException {
        zzo(Double.doubleToRawLongBits(d));
    }

    public final void zzb(float f) throws IOException {
        zzcc(Float.floatToRawIntBits(f));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzu(j));
    }

    public abstract void zzb(int i, zzbah zzbah) throws IOException;

    public abstract void zzbz(int i) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzca(int i) throws IOException;

    public final void zzcb(int i) throws IOException {
        zzca(zzck(i));
    }

    public abstract void zzcc(int i) throws IOException;

    public abstract void zze(zzbcu zzbcu) throws IOException;

    abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzen(String str) throws IOException;

    public abstract void zzf(int i, String str) throws IOException;

    public abstract void zzf(int i, boolean z) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzm(int i, int i2) throws IOException;

    public abstract void zzm(long j) throws IOException;

    public abstract void zzn(int i, int i2) throws IOException;

    public final void zzn(long j) throws IOException {
        zzm(zzu(j));
    }

    public final void zzo(int i, int i2) throws IOException {
        zzn(i, zzck(i2));
    }

    public abstract void zzo(long j) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;
}
