// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbez
{
    private final byte[] buffer;
    private int zzdpx;
    private int zzdpy;
    private int zzdpz;
    private int zzdqe;
    private int zzdqg;
    private int zzdqh;
    private final int zzebf;
    private final int zzebg;
    private int zzebh;
    private int zzebi;
    
    private zzbez(final byte[] buffer, final int n, int n2) {
        this.zzdqh = Integer.MAX_VALUE;
        this.zzdpy = 64;
        this.zzdpz = 67108864;
        this.buffer = buffer;
        this.zzebf = n;
        n2 += n;
        this.zzebh = n2;
        this.zzebg = n2;
        this.zzebi = n;
    }
    
    private final void zzacg() {
        this.zzebh += this.zzdqe;
        final int zzebh = this.zzebh;
        if (zzebh > this.zzdqh) {
            this.zzdqe = zzebh - this.zzdqh;
            this.zzebh -= this.zzdqe;
            return;
        }
        this.zzdqe = 0;
    }
    
    private final byte zzach() throws IOException {
        if (this.zzebi == this.zzebh) {
            throw zzbfh.zzagq();
        }
        return this.buffer[this.zzebi++];
    }
    
    private final void zzbt(final int n) throws IOException {
        if (n < 0) {
            throw zzbfh.zzagr();
        }
        if (this.zzebi + n > this.zzdqh) {
            this.zzbt(this.zzdqh - this.zzebi);
            throw zzbfh.zzagq();
        }
        if (n <= this.zzebh - this.zzebi) {
            this.zzebi += n;
            return;
        }
        throw zzbfh.zzagq();
    }
    
    public static zzbez zzi(final byte[] array, final int n, final int n2) {
        return new zzbez(array, 0, n2);
    }
    
    public final int getPosition() {
        return this.zzebi - this.zzebf;
    }
    
    public final byte[] readBytes() throws IOException {
        final int zzacc = this.zzacc();
        if (zzacc < 0) {
            throw zzbfh.zzagr();
        }
        if (zzacc == 0) {
            return zzbfl.zzecf;
        }
        if (zzacc > this.zzebh - this.zzebi) {
            throw zzbfh.zzagq();
        }
        final byte[] array = new byte[zzacc];
        System.arraycopy(this.buffer, this.zzebi, array, 0, zzacc);
        this.zzebi += zzacc;
        return array;
    }
    
    public final String readString() throws IOException {
        final int zzacc = this.zzacc();
        if (zzacc < 0) {
            throw zzbfh.zzagr();
        }
        if (zzacc > this.zzebh - this.zzebi) {
            throw zzbfh.zzagq();
        }
        final String s = new String(this.buffer, this.zzebi, zzacc, zzbfg.UTF_8);
        this.zzebi += zzacc;
        return s;
    }
    
    public final void zza(final zzbfi zzbfi) throws IOException {
        final int zzacc = this.zzacc();
        if (this.zzdpx >= this.zzdpy) {
            throw new zzbfh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        final int zzbr = this.zzbr(zzacc);
        ++this.zzdpx;
        zzbfi.zza(this);
        this.zzbp(0);
        --this.zzdpx;
        this.zzbs(zzbr);
    }
    
    public final byte[] zzab(final int n, final int n2) {
        if (n2 == 0) {
            return zzbfl.zzecf;
        }
        final byte[] array = new byte[n2];
        System.arraycopy(this.buffer, this.zzebf + n, array, 0, n2);
        return array;
    }
    
    public final int zzabk() throws IOException {
        if (this.zzebi == this.zzebh) {
            return this.zzdqg = 0;
        }
        this.zzdqg = this.zzacc();
        if (this.zzdqg == 0) {
            throw new zzbfh("Protocol message contained an invalid tag (zero).");
        }
        return this.zzdqg;
    }
    
    public final long zzabm() throws IOException {
        return this.zzacd();
    }
    
    public final int zzabn() throws IOException {
        return this.zzacc();
    }
    
    public final boolean zzabq() throws IOException {
        return this.zzacc() != 0;
    }
    
    final void zzac(final int n, int zzebi) {
        if (n > this.zzebi - this.zzebf) {
            zzebi = this.zzebi;
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(n).append(" is beyond current ").append(zzebi - this.zzebf).toString());
        }
        if (n < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(n).toString());
        }
        this.zzebi = this.zzebf + n;
        this.zzdqg = zzebi;
    }
    
    public final int zzacc() throws IOException {
        int zzach = this.zzach();
        if (zzach < 0) {
            final int n = zzach & 0x7F;
            final byte zzach2 = this.zzach();
            if (zzach2 >= 0) {
                return n | zzach2 << 7;
            }
            final int n2 = n | (zzach2 & 0x7F) << 7;
            final byte zzach3 = this.zzach();
            if (zzach3 >= 0) {
                return n2 | zzach3 << 14;
            }
            final int n3 = n2 | (zzach3 & 0x7F) << 14;
            final byte zzach4 = this.zzach();
            if (zzach4 >= 0) {
                return n3 | zzach4 << 21;
            }
            final byte zzach5 = this.zzach();
            final int n4 = zzach = (n3 | (zzach4 & 0x7F) << 21 | zzach5 << 28);
            if (zzach5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    zzach = n4;
                    if (this.zzach() >= 0) {
                        return zzach;
                    }
                }
                throw zzbfh.zzags();
            }
        }
        return zzach;
    }
    
    public final long zzacd() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte zzach = this.zzach();
            n |= (long)(zzach & 0x7F) << i;
            if ((zzach & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw zzbfh.zzags();
    }
    
    public final int zzagn() {
        if (this.zzdqh == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzdqh - this.zzebi;
    }
    
    public final void zzbp(final int n) throws zzbfh {
        if (this.zzdqg != n) {
            throw new zzbfh("Protocol message end-group tag did not match expected tag.");
        }
    }
    
    public final boolean zzbq(final int n) throws IOException {
        switch (n & 0x7) {
            default: {
                throw new zzbfh("Protocol message tag had invalid wire type.");
            }
            case 0: {
                this.zzacc();
                return true;
            }
            case 1: {
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                return true;
            }
            case 2: {
                this.zzbt(this.zzacc());
                return true;
            }
            case 3: {
                int zzabk;
                do {
                    zzabk = this.zzabk();
                } while (zzabk != 0 && this.zzbq(zzabk));
                this.zzbp(n >>> 3 << 3 | 0x4);
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzach();
                this.zzach();
                this.zzach();
                this.zzach();
                return true;
            }
        }
    }
    
    public final int zzbr(int zzdqh) throws zzbfh {
        if (zzdqh < 0) {
            throw zzbfh.zzagr();
        }
        zzdqh += this.zzebi;
        final int zzdqh2 = this.zzdqh;
        if (zzdqh > zzdqh2) {
            throw zzbfh.zzagq();
        }
        this.zzdqh = zzdqh;
        this.zzacg();
        return zzdqh2;
    }
    
    public final void zzbs(final int zzdqh) {
        this.zzdqh = zzdqh;
        this.zzacg();
    }
    
    public final void zzdc(final int n) {
        this.zzac(n, this.zzdqg);
    }
}
