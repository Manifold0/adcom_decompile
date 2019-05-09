// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zzio
{
    private final byte[] buffer;
    private final int zzmn;
    private final int zzmo;
    private int zzmp;
    private int zzmq;
    private int zzmr;
    private int zzms;
    private int zzmt;
    private int zzmu;
    
    private zzio(final byte[] buffer, final int n, int n2) {
        this.zzms = Integer.MAX_VALUE;
        this.zzmt = 64;
        this.zzmu = 67108864;
        this.buffer = buffer;
        this.zzmn = n;
        n2 += n;
        this.zzmp = n2;
        this.zzmo = n2;
        this.zzmq = n;
    }
    
    public static zzio zza(final byte[] array, final int n, final int n2) {
        return new zzio(array, 0, n2);
    }
    
    private final byte zzbg() throws IOException {
        if (this.zzmq == this.zzmp) {
            throw zziw.zzbk();
        }
        return this.buffer[this.zzmq++];
    }
    
    private final void zzl(final int n) throws IOException {
        if (n < 0) {
            throw zziw.zzbl();
        }
        if (this.zzmq + n > this.zzms) {
            this.zzl(this.zzms - this.zzmq);
            throw zziw.zzbk();
        }
        if (n <= this.zzmp - this.zzmq) {
            this.zzmq += n;
            return;
        }
        throw zziw.zzbk();
    }
    
    public final int getPosition() {
        return this.zzmq - this.zzmn;
    }
    
    public final String readString() throws IOException {
        final int zzbe = this.zzbe();
        if (zzbe < 0) {
            throw zziw.zzbl();
        }
        if (zzbe > this.zzmp - this.zzmq) {
            throw zziw.zzbk();
        }
        final String s = new String(this.buffer, this.zzmq, zzbe, zziv.UTF_8);
        this.zzmq += zzbe;
        return s;
    }
    
    public final byte[] zza(final int n, final int n2) {
        if (n2 == 0) {
            return zzja.zzns;
        }
        final byte[] array = new byte[n2];
        System.arraycopy(this.buffer, this.zzmn + n, array, 0, n2);
        return array;
    }
    
    public final int zzbd() throws IOException {
        if (this.zzmq == this.zzmp) {
            return this.zzmr = 0;
        }
        this.zzmr = this.zzbe();
        if (this.zzmr == 0) {
            throw new zziw("Protocol message contained an invalid tag (zero).");
        }
        return this.zzmr;
    }
    
    public final int zzbe() throws IOException {
        int zzbg = this.zzbg();
        if (zzbg < 0) {
            final int n = zzbg & 0x7F;
            final byte zzbg2 = this.zzbg();
            if (zzbg2 >= 0) {
                return n | zzbg2 << 7;
            }
            final int n2 = n | (zzbg2 & 0x7F) << 7;
            final byte zzbg3 = this.zzbg();
            if (zzbg3 >= 0) {
                return n2 | zzbg3 << 14;
            }
            final int n3 = n2 | (zzbg3 & 0x7F) << 14;
            final byte zzbg4 = this.zzbg();
            if (zzbg4 >= 0) {
                return n3 | zzbg4 << 21;
            }
            final byte zzbg5 = this.zzbg();
            final int n4 = zzbg = (n3 | (zzbg4 & 0x7F) << 21 | zzbg5 << 28);
            if (zzbg5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    zzbg = n4;
                    if (this.zzbg() >= 0) {
                        return zzbg;
                    }
                }
                throw zziw.zzbm();
            }
        }
        return zzbg;
    }
    
    public final long zzbf() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte zzbg = this.zzbg();
            n |= (long)(zzbg & 0x7F) << i;
            if ((zzbg & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw zziw.zzbm();
    }
    
    public final void zzj(final int n) throws zziw {
        if (this.zzmr != n) {
            throw new zziw("Protocol message end-group tag did not match expected tag.");
        }
    }
    
    public final boolean zzk(final int n) throws IOException {
        switch (n & 0x7) {
            default: {
                throw new zziw("Protocol message tag had invalid wire type.");
            }
            case 0: {
                this.zzbe();
                return true;
            }
            case 1: {
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                return true;
            }
            case 2: {
                this.zzl(this.zzbe());
                return true;
            }
            case 3: {
                int zzbd;
                do {
                    zzbd = this.zzbd();
                } while (zzbd != 0 && this.zzk(zzbd));
                this.zzj(n >>> 3 << 3 | 0x4);
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzbg();
                this.zzbg();
                this.zzbg();
                this.zzbg();
                return true;
            }
        }
    }
}
