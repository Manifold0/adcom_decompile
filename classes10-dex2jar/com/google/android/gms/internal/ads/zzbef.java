// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

public final class zzbef
{
    private static final zzbef zzdyx;
    private int count;
    private boolean zzdpi;
    private int zzdtu;
    private Object[] zzdwh;
    private int[] zzdyy;
    
    static {
        zzdyx = new zzbef(0, new int[0], new Object[0], false);
    }
    
    private zzbef() {
        this(0, new int[8], new Object[8], true);
    }
    
    private zzbef(final int count, final int[] zzdyy, final Object[] zzdwh, final boolean zzdpi) {
        this.zzdtu = -1;
        this.count = count;
        this.zzdyy = zzdyy;
        this.zzdwh = zzdwh;
        this.zzdpi = zzdpi;
    }
    
    static zzbef zza(final zzbef zzbef, final zzbef zzbef2) {
        final int n = zzbef.count + zzbef2.count;
        final int[] copy = Arrays.copyOf(zzbef.zzdyy, n);
        System.arraycopy(zzbef2.zzdyy, 0, copy, zzbef.count, zzbef2.count);
        final Object[] copy2 = Arrays.copyOf(zzbef.zzdwh, n);
        System.arraycopy(zzbef2.zzdwh, 0, copy2, zzbef.count, zzbef2.count);
        return new zzbef(n, copy, copy2, true);
    }
    
    public static zzbef zzagc() {
        return zzbef.zzdyx;
    }
    
    static zzbef zzagd() {
        return new zzbef();
    }
    
    private static void zzb(final int n, final Object o, final zzbey zzbey) throws IOException {
        final int n2 = n >>> 3;
        switch (n & 0x7) {
            default: {
                throw new RuntimeException(zzbbu.zzadq());
            }
            case 0: {
                zzbey.zzi(n2, (long)o);
            }
            case 5: {
                zzbey.zzp(n2, (int)o);
            }
            case 1: {
                zzbey.zzc(n2, (long)o);
            }
            case 2: {
                zzbey.zza(n2, (zzbah)o);
            }
            case 3: {
                if (zzbey.zzacn() == zzbbo.zze.zzdul) {
                    zzbey.zzcm(n2);
                    ((zzbef)o).zzb(zzbey);
                    zzbey.zzcn(n2);
                    return;
                }
                zzbey.zzcn(n2);
                ((zzbef)o).zzb(zzbey);
                zzbey.zzcm(n2);
            }
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof zzbef)) {
                return false;
            }
            final zzbef zzbef = (zzbef)o;
            if (this.count == zzbef.count) {
                final int[] zzdyy = this.zzdyy;
                final int[] zzdyy2 = zzbef.zzdyy;
                final int count = this.count;
                int i = 0;
                while (true) {
                    while (i < count) {
                        if (zzdyy[i] != zzdyy2[i]) {
                            final int n = 0;
                            if (n != 0) {
                                final Object[] zzdwh = this.zzdwh;
                                final Object[] zzdwh2 = zzbef.zzdwh;
                                final int count2 = this.count;
                                int j = 0;
                                while (true) {
                                    while (j < count2) {
                                        if (!zzdwh[j].equals(zzdwh2[j])) {
                                            final int n2 = 0;
                                            if (n2 == 0) {
                                                return false;
                                            }
                                            return true;
                                        }
                                        else {
                                            ++j;
                                        }
                                    }
                                    final int n2 = 1;
                                    continue;
                                }
                            }
                            return false;
                        }
                        else {
                            ++i;
                        }
                    }
                    final int n = 1;
                    continue;
                }
            }
            return false;
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int n = 17;
        final int n2 = 0;
        final int count = this.count;
        final int[] zzdyy = this.zzdyy;
        final int count2 = this.count;
        int i = 0;
        int n3 = 17;
        while (i < count2) {
            n3 = n3 * 31 + zzdyy[i];
            ++i;
        }
        final Object[] zzdwh = this.zzdwh;
        for (int count3 = this.count, j = n2; j < count3; ++j) {
            n = n * 31 + zzdwh[j].hashCode();
        }
        return ((count + 527) * 31 + n3) * 31 + n;
    }
    
    final void zza(final zzbey zzbey) throws IOException {
        if (zzbey.zzacn() == zzbbo.zze.zzdum) {
            for (int i = this.count - 1; i >= 0; --i) {
                zzbey.zza(this.zzdyy[i] >>> 3, this.zzdwh[i]);
            }
        }
        else {
            for (int j = 0; j < this.count; ++j) {
                zzbey.zza(this.zzdyy[j] >>> 3, this.zzdwh[j]);
            }
        }
    }
    
    final void zza(final StringBuilder sb, final int n) {
        for (int i = 0; i < this.count; ++i) {
            zzbcx.zza(sb, n, String.valueOf(this.zzdyy[i] >>> 3), this.zzdwh[i]);
        }
    }
    
    public final void zzaaz() {
        this.zzdpi = false;
    }
    
    public final int zzacw() {
        final int zzdtu = this.zzdtu;
        if (zzdtu != -1) {
            return zzdtu;
        }
        int i = 0;
        int zzdtu2 = 0;
        while (i < this.count) {
            final int n = this.zzdyy[i];
            final int n2 = n >>> 3;
            switch (n & 0x7) {
                default: {
                    throw new IllegalStateException(zzbbu.zzadq());
                }
                case 0: {
                    zzdtu2 += zzbav.zze(n2, (long)this.zzdwh[i]);
                    break;
                }
                case 5: {
                    zzdtu2 += zzbav.zzt(n2, (int)this.zzdwh[i]);
                    break;
                }
                case 1: {
                    zzdtu2 += zzbav.zzg(n2, (long)this.zzdwh[i]);
                    break;
                }
                case 2: {
                    zzdtu2 += zzbav.zzc(n2, (zzbah)this.zzdwh[i]);
                    break;
                }
                case 3: {
                    zzdtu2 += ((zzbef)this.zzdwh[i]).zzacw() + (zzbav.zzcd(n2) << 1);
                    break;
                }
            }
            ++i;
        }
        return this.zzdtu = zzdtu2;
    }
    
    public final int zzage() {
        final int zzdtu = this.zzdtu;
        if (zzdtu != -1) {
            return zzdtu;
        }
        int i = 0;
        int zzdtu2 = 0;
        while (i < this.count) {
            zzdtu2 += zzbav.zzd(this.zzdyy[i] >>> 3, (zzbah)this.zzdwh[i]);
            ++i;
        }
        return this.zzdtu = zzdtu2;
    }
    
    final void zzb(final int n, final Object o) {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
        if (this.count == this.zzdyy.length) {
            int n2;
            if (this.count < 4) {
                n2 = 8;
            }
            else {
                n2 = this.count >> 1;
            }
            final int n3 = n2 + this.count;
            this.zzdyy = Arrays.copyOf(this.zzdyy, n3);
            this.zzdwh = Arrays.copyOf(this.zzdwh, n3);
        }
        this.zzdyy[this.count] = n;
        this.zzdwh[this.count] = o;
        ++this.count;
    }
    
    public final void zzb(final zzbey zzbey) throws IOException {
        if (this.count != 0) {
            if (zzbey.zzacn() == zzbbo.zze.zzdul) {
                for (int i = 0; i < this.count; ++i) {
                    zzb(this.zzdyy[i], this.zzdwh[i], zzbey);
                }
            }
            else {
                for (int j = this.count - 1; j >= 0; --j) {
                    zzb(this.zzdyy[j], this.zzdwh[j], zzbey);
                }
            }
        }
    }
}
