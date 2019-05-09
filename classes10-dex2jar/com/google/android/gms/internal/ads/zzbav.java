// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.logging.Level;
import java.io.IOException;
import java.util.logging.Logger;

public abstract class zzbav extends zzbag
{
    private static final Logger logger;
    private static final boolean zzdqm;
    zzbax zzdqn;
    
    static {
        logger = Logger.getLogger(zzbav.class.getName());
        zzdqm = zzbek.zzagf();
    }
    
    private zzbav() {
    }
    
    public static int zza(int zzcd, final zzbcb zzbcb) {
        zzcd = zzcd(zzcd);
        final int zzacw = zzbcb.zzacw();
        return zzcd + (zzacw + zzcf(zzacw));
    }
    
    public static int zza(final zzbcb zzbcb) {
        final int zzacw = zzbcb.zzacw();
        return zzacw + zzcf(zzacw);
    }
    
    static int zza(final zzbcu zzbcu, final zzbdm zzbdm) {
        final zzazy zzazy = (zzazy)zzbcu;
        int n;
        if ((n = zzazy.zzaaw()) == -1) {
            n = zzbdm.zzy(zzazy);
            zzazy.zzbj(n);
        }
        return n + zzcf(n);
    }
    
    public static int zzao(final zzbah zzbah) {
        final int size = zzbah.size();
        return size + zzcf(size);
    }
    
    public static int zzaq(final boolean b) {
        return 1;
    }
    
    public static int zzb(final int n, final double n2) {
        return zzcd(n) + 8;
    }
    
    public static int zzb(final int n, final float n2) {
        return zzcd(n) + 4;
    }
    
    public static int zzb(final int n, final zzbcb zzbcb) {
        return (zzcd(1) << 1) + zzr(2, n) + zza(3, zzbcb);
    }
    
    public static int zzb(final int n, final zzbcu zzbcu) {
        return (zzcd(1) << 1) + zzr(2, n) + (zzcd(3) + zzf(zzbcu));
    }
    
    static int zzb(final int n, final zzbcu zzbcu, final zzbdm zzbdm) {
        return zzcd(n) + zza(zzbcu, zzbdm);
    }
    
    public static int zzc(final double n) {
        return 8;
    }
    
    public static int zzc(final float n) {
        return 4;
    }
    
    public static int zzc(int zzcd, final zzbah zzbah) {
        zzcd = zzcd(zzcd);
        final int size = zzbah.size();
        return zzcd + (size + zzcf(size));
    }
    
    @Deprecated
    static int zzc(int n, final zzbcu zzbcu, final zzbdm zzbdm) {
        final int zzcd = zzcd(n);
        final zzazy zzazy = (zzazy)zzbcu;
        if ((n = zzazy.zzaaw()) == -1) {
            n = zzbdm.zzy(zzazy);
            zzazy.zzbj(n);
        }
        return n + (zzcd << 1);
    }
    
    public static int zzcd(final int n) {
        return zzcf(n << 3);
    }
    
    public static int zzce(final int n) {
        if (n >= 0) {
            return zzcf(n);
        }
        return 10;
    }
    
    public static int zzcf(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public static int zzcg(final int n) {
        return zzcf(zzck(n));
    }
    
    public static int zzch(final int n) {
        return 4;
    }
    
    public static int zzci(final int n) {
        return 4;
    }
    
    public static int zzcj(final int n) {
        return zzce(n);
    }
    
    private static int zzck(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    @Deprecated
    public static int zzcl(final int n) {
        return zzcf(n);
    }
    
    public static int zzd(final int n, final long n2) {
        return zzcd(n) + zzq(n2);
    }
    
    public static int zzd(final int n, final zzbah zzbah) {
        return (zzcd(1) << 1) + zzr(2, n) + zzc(3, zzbah);
    }
    
    public static int zze(final int n, final long n2) {
        return zzcd(n) + zzq(n2);
    }
    
    public static int zzeo(final String s) {
        try {
            final int n = zzbem.zza(s);
            return n + zzcf(n);
        }
        catch (zzbep zzbep) {
            final int n = s.getBytes(zzbbq.UTF_8).length;
            return n + zzcf(n);
        }
    }
    
    public static int zzf(final int n, final long n2) {
        return zzcd(n) + zzq(zzu(n2));
    }
    
    public static int zzf(final zzbcu zzbcu) {
        final int zzacw = zzbcu.zzacw();
        return zzacw + zzcf(zzacw);
    }
    
    public static int zzg(final int n, final long n2) {
        return zzcd(n) + 8;
    }
    
    public static int zzg(final int n, final String s) {
        return zzcd(n) + zzeo(s);
    }
    
    public static int zzg(final int n, final boolean b) {
        return zzcd(n) + 1;
    }
    
    @Deprecated
    public static int zzg(final zzbcu zzbcu) {
        return zzbcu.zzacw();
    }
    
    public static int zzh(final int n, final long n2) {
        return zzcd(n) + 8;
    }
    
    public static int zzp(final long n) {
        return zzq(n);
    }
    
    public static int zzq(final int n, final int n2) {
        return zzcd(n) + zzce(n2);
    }
    
    public static int zzq(long n) {
        int n2;
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            n2 = 1;
        }
        else {
            if (n < 0L) {
                return 10;
            }
            int n3 = 2;
            if ((0xFFFFFFF800000000L & n) != 0x0L) {
                n3 = 6;
                n >>>= 28;
            }
            int n4 = n3;
            long n5 = n;
            if ((0xFFFFFFFFFFE00000L & n) != 0x0L) {
                n4 = n3 + 2;
                n5 = n >>> 14;
            }
            n2 = n4;
            if ((n5 & 0xFFFFFFFFFFFFC000L) != 0x0L) {
                return n4 + 1;
            }
        }
        return n2;
    }
    
    public static zzbav zzq(final byte[] array) {
        return new zza(array, 0, array.length);
    }
    
    public static int zzr(final int n, final int n2) {
        return zzcd(n) + zzcf(n2);
    }
    
    public static int zzr(final long n) {
        return zzq(zzu(n));
    }
    
    public static int zzr(final byte[] array) {
        final int length = array.length;
        return length + zzcf(length);
    }
    
    public static int zzs(final int n, final int n2) {
        return zzcd(n) + zzcf(zzck(n2));
    }
    
    public static int zzs(final long n) {
        return 8;
    }
    
    public static int zzt(final int n, final int n2) {
        return zzcd(n) + 4;
    }
    
    public static int zzt(final long n) {
        return 8;
    }
    
    public static int zzu(final int n, final int n2) {
        return zzcd(n) + 4;
    }
    
    private static long zzu(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static int zzv(final int n, final int n2) {
        return zzcd(n) + zzce(n2);
    }
    
    public abstract void write(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void zza(final byte p0) throws IOException;
    
    public final void zza(final int n, final double n2) throws IOException {
        this.zzc(n, Double.doubleToRawLongBits(n2));
    }
    
    public final void zza(final int n, final float n2) throws IOException {
        this.zzp(n, Float.floatToRawIntBits(n2));
    }
    
    public abstract void zza(final int p0, final long p1) throws IOException;
    
    public abstract void zza(final int p0, final zzbah p1) throws IOException;
    
    public abstract void zza(final int p0, final zzbcu p1) throws IOException;
    
    abstract void zza(final int p0, final zzbcu p1, final zzbdm p2) throws IOException;
    
    final void zza(final String s, final zzbep zzbep) throws IOException {
        zzbav.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzbep);
        final byte[] bytes = s.getBytes(zzbbq.UTF_8);
        try {
            this.zzca(bytes.length);
            this.zzb(bytes, 0, bytes.length);
        }
        catch (IndexOutOfBoundsException ex) {
            throw new zzb(ex);
        }
        catch (zzb zzb) {
            throw zzb;
        }
    }
    
    public abstract int zzack();
    
    public final void zzacl() {
        if (this.zzack() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public abstract void zzan(final zzbah p0) throws IOException;
    
    public final void zzap(final boolean b) throws IOException {
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.zza((byte)(b2 ? 1 : 0));
    }
    
    public final void zzb(final double n) throws IOException {
        this.zzo(Double.doubleToRawLongBits(n));
    }
    
    public final void zzb(final float n) throws IOException {
        this.zzcc(Float.floatToRawIntBits(n));
    }
    
    public final void zzb(final int n, final long n2) throws IOException {
        this.zza(n, zzu(n2));
    }
    
    public abstract void zzb(final int p0, final zzbah p1) throws IOException;
    
    public abstract void zzbz(final int p0) throws IOException;
    
    public abstract void zzc(final int p0, final long p1) throws IOException;
    
    public abstract void zzca(final int p0) throws IOException;
    
    public final void zzcb(final int n) throws IOException {
        this.zzca(zzck(n));
    }
    
    public abstract void zzcc(final int p0) throws IOException;
    
    public abstract void zze(final zzbcu p0) throws IOException;
    
    abstract void zze(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract void zzen(final String p0) throws IOException;
    
    public abstract void zzf(final int p0, final String p1) throws IOException;
    
    public abstract void zzf(final int p0, final boolean p1) throws IOException;
    
    public abstract void zzl(final int p0, final int p1) throws IOException;
    
    public abstract void zzm(final int p0, final int p1) throws IOException;
    
    public abstract void zzm(final long p0) throws IOException;
    
    public abstract void zzn(final int p0, final int p1) throws IOException;
    
    public final void zzn(final long n) throws IOException {
        this.zzm(zzu(n));
    }
    
    public final void zzo(final int n, final int n2) throws IOException {
        this.zzn(n, zzck(n2));
    }
    
    public abstract void zzo(final long p0) throws IOException;
    
    public abstract void zzp(final int p0, final int p1) throws IOException;
    
    static final class zza extends zzbav
    {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;
        
        zza(final byte[] buffer, final int n, final int n2) {
            super(null);
            if (buffer == null) {
                throw new NullPointerException("buffer");
            }
            if ((n2 | 0x0 | buffer.length - (n2 + 0)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", buffer.length, 0, n2));
            }
            this.buffer = buffer;
            this.offset = 0;
            this.position = 0;
            this.limit = n2 + 0;
        }
        
        @Override
        public final void write(final byte[] array, final int n, final int n2) throws IOException {
            try {
                System.arraycopy(array, n, this.buffer, this.position, n2);
                this.position += n2;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, n2), ex);
            }
        }
        
        @Override
        public final void zza(final byte b) throws IOException {
            try {
                this.buffer[this.position++] = b;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), ex);
            }
        }
        
        @Override
        public final void zza(final int n, final long n2) throws IOException {
            this.zzl(n, 0);
            this.zzm(n2);
        }
        
        @Override
        public final void zza(final int n, final zzbah zzbah) throws IOException {
            this.zzl(n, 2);
            this.zzan(zzbah);
        }
        
        @Override
        public final void zza(final int n, final zzbcu zzbcu) throws IOException {
            this.zzl(1, 3);
            this.zzn(2, n);
            this.zzl(3, 2);
            this.zze(zzbcu);
            this.zzl(1, 4);
        }
        
        @Override
        final void zza(int n, final zzbcu zzbcu, final zzbdm zzbdm) throws IOException {
            this.zzl(n, 2);
            final zzazy zzazy = (zzazy)zzbcu;
            if ((n = zzazy.zzaaw()) == -1) {
                n = zzbdm.zzy(zzazy);
                zzazy.zzbj(n);
            }
            this.zzca(n);
            zzbdm.zza(zzbcu, this.zzdqn);
        }
        
        @Override
        public final int zzack() {
            return this.limit - this.position;
        }
        
        @Override
        public final void zzan(final zzbah zzbah) throws IOException {
            this.zzca(zzbah.size());
            zzbah.zza(this);
        }
        
        @Override
        public final void zzb(final int n, final zzbah zzbah) throws IOException {
            this.zzl(1, 3);
            this.zzn(2, n);
            this.zza(3, zzbah);
            this.zzl(1, 4);
        }
        
        @Override
        public final void zzb(final byte[] array, final int n, final int n2) throws IOException {
            this.write(array, n, n2);
        }
        
        @Override
        public final void zzbz(final int n) throws IOException {
            if (n >= 0) {
                this.zzca(n);
                return;
            }
            this.zzm(n);
        }
        
        @Override
        public final void zzc(final int n, final long n2) throws IOException {
            this.zzl(n, 1);
            this.zzo(n2);
        }
        
        @Override
        public final void zzca(int n) throws IOException {
            int n2 = n;
            if (zzbav.zzdqm) {
                n2 = n;
                if (this.zzack() >= 10) {
                    while ((n & 0xFFFFFF80) != 0x0) {
                        zzbek.zza(this.buffer, this.position++, (byte)((n & 0x7F) | 0x80));
                        n >>>= 7;
                    }
                    zzbek.zza(this.buffer, this.position++, (byte)n);
                    return;
                }
            }
            try {
                do {
                    final byte[] buffer = this.buffer;
                    n = this.position++;
                    buffer[n] = (byte)((n2 & 0x7F) | 0x80);
                    n2 >>>= 7;
                } while ((n2 & 0xFFFFFF80) != 0x0);
                final byte[] buffer2 = this.buffer;
                n = this.position++;
                buffer2[n] = (byte)n2;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), ex);
            }
        }
        
        @Override
        public final void zzcc(final int n) throws IOException {
            try {
                this.buffer[this.position++] = (byte)n;
                this.buffer[this.position++] = (byte)(n >> 8);
                this.buffer[this.position++] = (byte)(n >> 16);
                this.buffer[this.position++] = (byte)(n >> 24);
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), ex);
            }
        }
        
        @Override
        public final void zze(final zzbcu zzbcu) throws IOException {
            this.zzca(zzbcu.zzacw());
            zzbcu.zzb(this);
        }
        
        public final void zze(final byte[] array, final int n, final int n2) throws IOException {
            this.zzca(n2);
            this.write(array, 0, n2);
        }
        
        @Override
        public final void zzen(final String s) throws IOException {
            final int position = this.position;
            try {
                final int zzcf = zzbav.zzcf(s.length() * 3);
                final int zzcf2 = zzbav.zzcf(s.length());
                if (zzcf2 == zzcf) {
                    this.position = position + zzcf2;
                    final int zza = zzbem.zza(s, this.buffer, this.position, this.zzack());
                    this.zzca(zza - (this.position = position) - zzcf2);
                    this.position = zza;
                    return;
                }
                this.zzca(zzbem.zza(s));
                this.position = zzbem.zza(s, this.buffer, this.position, this.zzack());
            }
            catch (zzbep zzbep) {
                this.position = position;
                this.zza(s, zzbep);
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(ex);
            }
        }
        
        @Override
        public final void zzf(final int n, final String s) throws IOException {
            this.zzl(n, 2);
            this.zzen(s);
        }
        
        @Override
        public final void zzf(int n, final boolean b) throws IOException {
            final int n2 = 0;
            this.zzl(n, 0);
            n = n2;
            if (b) {
                n = 1;
            }
            this.zza((byte)n);
        }
        
        @Override
        public final void zzl(final int n, final int n2) throws IOException {
            this.zzca(n << 3 | n2);
        }
        
        @Override
        public final void zzm(final int n, final int n2) throws IOException {
            this.zzl(n, 0);
            this.zzbz(n2);
        }
        
        @Override
        public final void zzm(long n) throws IOException {
            long n2 = n;
            if (zzbav.zzdqm) {
                n2 = n;
                if (this.zzack() >= 10) {
                    while ((n & 0xFFFFFFFFFFFFFF80L) != 0x0L) {
                        zzbek.zza(this.buffer, this.position++, (byte)(((int)n & 0x7F) | 0x80));
                        n >>>= 7;
                    }
                    zzbek.zza(this.buffer, this.position++, (byte)n);
                    return;
                }
            }
            try {
                do {
                    this.buffer[this.position++] = (byte)(((int)n2 & 0x7F) | 0x80);
                    n2 >>>= 7;
                } while ((n2 & 0xFFFFFFFFFFFFFF80L) != 0x0L);
                this.buffer[this.position++] = (byte)n2;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), ex);
            }
        }
        
        @Override
        public final void zzn(final int n, final int n2) throws IOException {
            this.zzl(n, 0);
            this.zzca(n2);
        }
        
        @Override
        public final void zzo(final long n) throws IOException {
            try {
                this.buffer[this.position++] = (byte)n;
                this.buffer[this.position++] = (byte)(n >> 8);
                this.buffer[this.position++] = (byte)(n >> 16);
                this.buffer[this.position++] = (byte)(n >> 24);
                this.buffer[this.position++] = (byte)(n >> 32);
                this.buffer[this.position++] = (byte)(n >> 40);
                this.buffer[this.position++] = (byte)(n >> 48);
                this.buffer[this.position++] = (byte)(n >> 56);
            }
            catch (IndexOutOfBoundsException ex) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), ex);
            }
        }
        
        @Override
        public final void zzp(final int n, final int n2) throws IOException {
            this.zzl(n, 5);
            this.zzcc(n2);
        }
    }
    
    public static final class zzb extends IOException
    {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
        
        zzb(String s, final Throwable t) {
            final String value = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = value.concat(s);
            }
            else {
                s = new String(value);
            }
            super(s, t);
        }
        
        zzb(final Throwable t) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", t);
        }
    }
}
