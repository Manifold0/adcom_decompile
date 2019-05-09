// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.io.IOException;

final class zzbas extends zzbaq
{
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzdqd;
    private int zzdqe;
    private int zzdqf;
    private int zzdqg;
    private int zzdqh;
    
    private zzbas(final byte[] buffer, final int pos, final int n, final boolean zzdqd) {
        super(null);
        this.zzdqh = Integer.MAX_VALUE;
        this.buffer = buffer;
        this.limit = pos + n;
        this.pos = pos;
        this.zzdqf = this.pos;
        this.zzdqd = zzdqd;
    }
    
    private final int zzacc() throws IOException {
        final int pos = this.pos;
        if (this.limit != pos) {
            final byte[] buffer = this.buffer;
            final int pos2 = pos + 1;
            final byte b = buffer[pos];
            if (b >= 0) {
                this.pos = pos2;
                return b;
            }
            if (this.limit - pos2 >= 9) {
                int pos3 = pos2 + 1;
                final int n = b ^ buffer[pos2] << 7;
                int n2 = 0;
                Label_0073: {
                    if (n < 0) {
                        n2 = (n ^ 0xFFFFFF80);
                    }
                    else {
                        final int n3 = pos3 + 1;
                        final int n4 = n ^ buffer[pos3] << 14;
                        if (n4 >= 0) {
                            final int n5 = n4 ^ 0x3F80;
                            pos3 = n3;
                            n2 = n5;
                        }
                        else {
                            pos3 = n3 + 1;
                            final int n6 = n4 ^ buffer[n3] << 21;
                            if (n6 < 0) {
                                n2 = (n6 ^ 0xFFE03F80);
                            }
                            else {
                                final int n7 = pos3 + 1;
                                final byte b2 = buffer[pos3];
                                final int n8 = n6 ^ b2 << 28 ^ 0xFE03F80;
                                pos3 = n7;
                                if (b2 < 0) {
                                    final int n9 = n7 + 1;
                                    n2 = n8;
                                    pos3 = n9;
                                    if (buffer[n7] >= 0) {
                                        break Label_0073;
                                    }
                                    final int n10 = pos3 = n9 + 1;
                                    if (buffer[n9] < 0) {
                                        final int n11 = n10 + 1;
                                        n2 = n8;
                                        pos3 = n11;
                                        if (buffer[n10] >= 0) {
                                            break Label_0073;
                                        }
                                        final int n12 = pos3 = n11 + 1;
                                        if (buffer[n11] < 0) {
                                            pos3 = n12 + 1;
                                            n2 = n8;
                                            if (buffer[n12] < 0) {
                                                return (int)this.zzabz();
                                            }
                                            break Label_0073;
                                        }
                                    }
                                }
                                n2 = n8;
                            }
                        }
                    }
                }
                this.pos = pos3;
                return n2;
            }
        }
        return (int)this.zzabz();
    }
    
    private final long zzacd() throws IOException {
        final int pos = this.pos;
        if (this.limit != pos) {
            final byte[] buffer = this.buffer;
            final int pos2 = pos + 1;
            final byte b = buffer[pos];
            if (b >= 0) {
                this.pos = pos2;
                return b;
            }
            if (this.limit - pos2 >= 9) {
                int pos3 = pos2 + 1;
                final int n = b ^ buffer[pos2] << 7;
                long n2;
                if (n < 0) {
                    n2 = (n ^ 0xFFFFFF80);
                }
                else {
                    final int n3 = pos3 + 1;
                    final int n4 = n ^ buffer[pos3] << 14;
                    if (n4 >= 0) {
                        n2 = (n4 ^ 0x3F80);
                        pos3 = n3;
                    }
                    else {
                        pos3 = n3 + 1;
                        final int n5 = n4 ^ buffer[n3] << 21;
                        if (n5 < 0) {
                            n2 = (n5 ^ 0xFFE03F80);
                        }
                        else {
                            final long n6 = n5;
                            final int n7 = pos3 + 1;
                            final long n8 = n6 ^ (long)buffer[pos3] << 28;
                            if (n8 >= 0L) {
                                n2 = (n8 ^ 0xFE03F80L);
                                pos3 = n7;
                            }
                            else {
                                pos3 = n7 + 1;
                                final long n9 = n8 ^ (long)buffer[n7] << 35;
                                if (n9 < 0L) {
                                    n2 = (n9 ^ 0xFFFFFFF80FE03F80L);
                                }
                                else {
                                    final int n10 = pos3 + 1;
                                    final long n11 = n9 ^ (long)buffer[pos3] << 42;
                                    if (n11 >= 0L) {
                                        n2 = (n11 ^ 0x3F80FE03F80L);
                                        pos3 = n10;
                                    }
                                    else {
                                        pos3 = n10 + 1;
                                        final long n12 = n11 ^ (long)buffer[n10] << 49;
                                        if (n12 < 0L) {
                                            n2 = (n12 ^ 0xFFFE03F80FE03F80L);
                                        }
                                        else {
                                            final int n13 = pos3 + 1;
                                            n2 = (n12 ^ (long)buffer[pos3] << 56 ^ 0xFE03F80FE03F80L);
                                            if (n2 < 0L) {
                                                pos3 = n13 + 1;
                                                if (buffer[n13] < 0L) {
                                                    return this.zzabz();
                                                }
                                            }
                                            else {
                                                pos3 = n13;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.pos = pos3;
                return n2;
            }
        }
        return this.zzabz();
    }
    
    private final int zzace() throws IOException {
        final int pos = this.pos;
        if (this.limit - pos < 4) {
            throw zzbbu.zzadl();
        }
        final byte[] buffer = this.buffer;
        this.pos = pos + 4;
        return (buffer[pos + 3] & 0xFF) << 24 | ((buffer[pos] & 0xFF) | (buffer[pos + 1] & 0xFF) << 8 | (buffer[pos + 2] & 0xFF) << 16);
    }
    
    private final long zzacf() throws IOException {
        final int pos = this.pos;
        if (this.limit - pos < 8) {
            throw zzbbu.zzadl();
        }
        final byte[] buffer = this.buffer;
        this.pos = pos + 8;
        return ((long)buffer[pos + 7] & 0xFFL) << 56 | (((long)buffer[pos] & 0xFFL) | ((long)buffer[pos + 1] & 0xFFL) << 8 | ((long)buffer[pos + 2] & 0xFFL) << 16 | ((long)buffer[pos + 3] & 0xFFL) << 24 | ((long)buffer[pos + 4] & 0xFFL) << 32 | ((long)buffer[pos + 5] & 0xFFL) << 40 | ((long)buffer[pos + 6] & 0xFFL) << 48);
    }
    
    private final void zzacg() {
        this.limit += this.zzdqe;
        final int n = this.limit - this.zzdqf;
        if (n > this.zzdqh) {
            this.zzdqe = n - this.zzdqh;
            this.limit -= this.zzdqe;
            return;
        }
        this.zzdqe = 0;
    }
    
    private final byte zzach() throws IOException {
        if (this.pos == this.limit) {
            throw zzbbu.zzadl();
        }
        return this.buffer[this.pos++];
    }
    
    @Override
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(this.zzacf());
    }
    
    @Override
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(this.zzace());
    }
    
    @Override
    public final String readString() throws IOException {
        final int zzacc = this.zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            final String s = new String(this.buffer, this.pos, zzacc, zzbbq.UTF_8);
            this.pos += zzacc;
            return s;
        }
        if (zzacc == 0) {
            return "";
        }
        if (zzacc < 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }
    
    @Override
    public final int zzabk() throws IOException {
        if (this.zzaca()) {
            return this.zzdqg = 0;
        }
        this.zzdqg = this.zzacc();
        if (this.zzdqg >>> 3 == 0) {
            throw zzbbu.zzado();
        }
        return this.zzdqg;
    }
    
    @Override
    public final long zzabl() throws IOException {
        return this.zzacd();
    }
    
    @Override
    public final long zzabm() throws IOException {
        return this.zzacd();
    }
    
    @Override
    public final int zzabn() throws IOException {
        return this.zzacc();
    }
    
    @Override
    public final long zzabo() throws IOException {
        return this.zzacf();
    }
    
    @Override
    public final int zzabp() throws IOException {
        return this.zzace();
    }
    
    @Override
    public final boolean zzabq() throws IOException {
        return this.zzacd() != 0L;
    }
    
    @Override
    public final String zzabr() throws IOException {
        final int zzacc = this.zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            if (!zzbem.zzf(this.buffer, this.pos, this.pos + zzacc)) {
                throw zzbbu.zzads();
            }
            final int pos = this.pos;
            this.pos += zzacc;
            return new String(this.buffer, pos, zzacc, zzbbq.UTF_8);
        }
        else {
            if (zzacc == 0) {
                return "";
            }
            if (zzacc <= 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        }
    }
    
    @Override
    public final zzbah zzabs() throws IOException {
        final int zzacc = this.zzacc();
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            final zzbah zzc = zzbah.zzc(this.buffer, this.pos, zzacc);
            this.pos += zzacc;
            return zzc;
        }
        if (zzacc == 0) {
            return zzbah.zzdpq;
        }
        byte[] array;
        if (zzacc > 0 && zzacc <= this.limit - this.pos) {
            final int pos = this.pos;
            this.pos += zzacc;
            array = Arrays.copyOfRange(this.buffer, pos, this.pos);
        }
        else {
            if (zzacc > 0) {
                throw zzbbu.zzadl();
            }
            if (zzacc != 0) {
                throw zzbbu.zzadm();
            }
            array = zzbbq.zzduq;
        }
        return zzbah.zzp(array);
    }
    
    @Override
    public final int zzabt() throws IOException {
        return this.zzacc();
    }
    
    @Override
    public final int zzabu() throws IOException {
        return this.zzacc();
    }
    
    @Override
    public final int zzabv() throws IOException {
        return this.zzace();
    }
    
    @Override
    public final long zzabw() throws IOException {
        return this.zzacf();
    }
    
    @Override
    public final int zzabx() throws IOException {
        return zzbaq.zzbu(this.zzacc());
    }
    
    @Override
    public final long zzaby() throws IOException {
        return zzbaq.zzl(this.zzacd());
    }
    
    @Override
    final long zzabz() throws IOException {
        long n = 0L;
        for (int i = 0; i < 64; i += 7) {
            final byte zzach = this.zzach();
            n |= (long)(zzach & 0x7F) << i;
            if ((zzach & 0x80) == 0x0) {
                return n;
            }
        }
        throw zzbbu.zzadn();
    }
    
    @Override
    public final boolean zzaca() throws IOException {
        return this.pos == this.limit;
    }
    
    @Override
    public final int zzacb() {
        return this.pos - this.zzdqf;
    }
    
    @Override
    public final void zzbp(final int n) throws zzbbu {
        if (this.zzdqg != n) {
            throw zzbbu.zzadp();
        }
    }
    
    @Override
    public final boolean zzbq(int i) throws IOException {
        final int n = 0;
        final int n2 = 0;
        Label_0142: {
            switch (i & 0x7) {
                default: {
                    throw zzbbu.zzadq();
                }
                case 0: {
                    i = n;
                    if (this.limit - this.pos >= 10) {
                        for (i = n2; i < 10; ++i) {
                            if (this.buffer[this.pos++] >= 0) {
                                break Label_0142;
                            }
                        }
                        throw zzbbu.zzadn();
                    }
                    while (i < 10) {
                        if (this.zzach() >= 0) {
                            break Label_0142;
                        }
                        ++i;
                    }
                    throw zzbbu.zzadn();
                }
                case 1: {
                    this.zzbt(8);
                    break;
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
                    this.zzbp(i >>> 3 << 3 | 0x4);
                    return true;
                }
                case 4: {
                    return false;
                }
                case 5: {
                    this.zzbt(4);
                    return true;
                }
            }
        }
        return true;
    }
    
    @Override
    public final int zzbr(int zzdqh) throws zzbbu {
        if (zzdqh < 0) {
            throw zzbbu.zzadm();
        }
        zzdqh += this.zzacb();
        final int zzdqh2 = this.zzdqh;
        if (zzdqh > zzdqh2) {
            throw zzbbu.zzadl();
        }
        this.zzdqh = zzdqh;
        this.zzacg();
        return zzdqh2;
    }
    
    @Override
    public final void zzbs(final int zzdqh) {
        this.zzdqh = zzdqh;
        this.zzacg();
    }
    
    @Override
    public final void zzbt(final int n) throws IOException {
        if (n >= 0 && n <= this.limit - this.pos) {
            this.pos += n;
            return;
        }
        if (n < 0) {
            throw zzbbu.zzadm();
        }
        throw zzbbu.zzadl();
    }
}
