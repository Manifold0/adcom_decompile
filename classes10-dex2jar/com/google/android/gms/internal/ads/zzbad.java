// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzbad
{
    static int zza(int n, final byte[] array, int zzb, final int n2, final zzbae zzbae) throws zzbbu {
        if (n >>> 3 == 0) {
            throw zzbbu.zzado();
        }
        switch (n & 0x7) {
            default: {
                throw zzbbu.zzado();
            }
            case 0: {
                zzb = zzb(array, zzb, zzbae);
                break;
            }
            case 5: {
                return zzb + 4;
            }
            case 1: {
                return zzb + 8;
            }
            case 2: {
                return zza(array, zzb, zzbae) + zzbae.zzdpl;
            }
            case 3: {
                final int n3 = (n & 0xFFFFFFF8) | 0x4;
                final int n4 = 0;
                n = zzb;
                zzb = n4;
                int zzdpl;
                while (true) {
                    zzdpl = zzb;
                    zzb = n;
                    if (n >= n2) {
                        break;
                    }
                    final int zza = zza(array, n, zzbae);
                    n = (zzdpl = zzbae.zzdpl);
                    zzb = zza;
                    if (n == n3) {
                        break;
                    }
                    final int zza2 = zza(n, array, zza, n2, zzbae);
                    zzb = n;
                    n = zza2;
                }
                if (zzb > n2 || zzdpl != n3) {
                    throw zzbbu.zzadr();
                }
                break;
            }
        }
        return zzb;
    }
    
    static int zza(final int n, final byte[] array, int i, final int n2, final zzbbt<?> zzbbt, final zzbae zzbae) {
        final zzbbp zzbbp = (zzbbp)zzbbt;
        i = zza(array, i, zzbae);
        zzbbp.zzco(zzbae.zzdpl);
        while (i < n2) {
            final int zza = zza(array, i, zzbae);
            if (n != zzbae.zzdpl) {
                break;
            }
            i = zza(array, zza, zzbae);
            zzbbp.zzco(zzbae.zzdpl);
        }
        return i;
    }
    
    static int zza(final int n, final byte[] array, int n2, int zzdpl, final zzbef zzbef, final zzbae zzbae) throws IOException {
        if (n >>> 3 == 0) {
            throw zzbbu.zzado();
        }
        switch (n & 0x7) {
            default: {
                throw zzbbu.zzado();
            }
            case 0: {
                n2 = zzb(array, n2, zzbae);
                zzbef.zzb(n, zzbae.zzdpm);
                return n2;
            }
            case 5: {
                zzbef.zzb(n, zze(array, n2));
                return n2 + 4;
            }
            case 1: {
                zzbef.zzb(n, zzf(array, n2));
                return n2 + 8;
            }
            case 2: {
                n2 = zza(array, n2, zzbae);
                zzdpl = zzbae.zzdpl;
                if (zzdpl == 0) {
                    zzbef.zzb(n, zzbah.zzdpq);
                }
                else {
                    zzbef.zzb(n, zzbah.zzc(array, n2, zzdpl));
                }
                return n2 + zzdpl;
            }
            case 3: {
                final zzbef zzagd = zzbef.zzagd();
                final int n3 = (n & 0xFFFFFFF8) | 0x4;
                int n4 = 0;
                int zzdpl2;
                int n5;
                while (true) {
                    zzdpl2 = n4;
                    n5 = n2;
                    if (n2 >= zzdpl) {
                        break;
                    }
                    n2 = zza(array, n2, zzbae);
                    n4 = (zzdpl2 = zzbae.zzdpl);
                    n5 = n2;
                    if (n4 == n3) {
                        break;
                    }
                    n2 = zza(n4, array, n2, zzdpl, zzagd, zzbae);
                }
                if (n5 > zzdpl || zzdpl2 != n3) {
                    throw zzbbu.zzadr();
                }
                zzbef.zzb(n, zzagd);
                return n5;
            }
        }
    }
    
    static int zza(int n, final byte[] array, int n2, final zzbae zzbae) {
        final int n3 = n & 0x7F;
        n = n2 + 1;
        n2 = array[n2];
        if (n2 >= 0) {
            zzbae.zzdpl = (n3 | n2 << 7);
            return n;
        }
        final int n4 = (n2 & 0x7F) << 7 | n3;
        n2 = n + 1;
        n = array[n];
        if (n >= 0) {
            zzbae.zzdpl = (n << 14 | n4);
            return n2;
        }
        n = ((n & 0x7F) << 14 | n4);
        final int n5 = n2 + 1;
        n2 = array[n2];
        if (n2 >= 0) {
            zzbae.zzdpl = (n | n2 << 21);
            return n5;
        }
        final int n6 = (n2 & 0x7F) << 21 | n;
        n = n5 + 1;
        final byte b = array[n5];
        if (b >= 0) {
            zzbae.zzdpl = (n6 | b << 28);
            return n;
        }
        while (true) {
            n2 = n + 1;
            if (array[n] >= 0) {
                break;
            }
            n = n2;
        }
        zzbae.zzdpl = ((b & 0x7F) << 28 | n6);
        return n2;
    }
    
    static int zza(final byte[] array, int zzdpl, final zzbae zzbae) {
        final int n = zzdpl + 1;
        zzdpl = array[zzdpl];
        if (zzdpl >= 0) {
            zzbae.zzdpl = zzdpl;
            return n;
        }
        return zza(zzdpl, array, n, zzbae);
    }
    
    static int zza(final byte[] array, int i, final zzbbt<?> zzbbt, final zzbae zzbae) throws IOException {
        final zzbbp zzbbp = (zzbbp)zzbbt;
        i = zza(array, i, zzbae);
        final int n = zzbae.zzdpl + i;
        while (i < n) {
            i = zza(array, i, zzbae);
            zzbbp.zzco(zzbae.zzdpl);
        }
        if (i != n) {
            throw zzbbu.zzadl();
        }
        return i;
    }
    
    static int zzb(final byte[] array, int n, final zzbae zzbae) {
        int n2 = 7;
        final int n3 = n + 1;
        final long zzdpm = array[n];
        if (zzdpm >= 0L) {
            zzbae.zzdpm = zzdpm;
            return n3;
        }
        byte b = array[n3];
        final long n4 = b & 0x7F;
        long zzdpm2;
        for (n = n3 + 1, zzdpm2 = ((zzdpm & 0x7FL) | n4 << 7); b < 0; b = array[n], n2 += 7, zzdpm2 |= (long)(b & 0x7F) << n2, ++n) {}
        zzbae.zzdpm = zzdpm2;
        return n;
    }
    
    static int zzc(final byte[] array, int zza, final zzbae zzbae) {
        zza = zza(array, zza, zzbae);
        final int zzdpl = zzbae.zzdpl;
        if (zzdpl == 0) {
            zzbae.zzdpn = "";
            return zza;
        }
        zzbae.zzdpn = new String(array, zza, zzdpl, zzbbq.UTF_8);
        return zza + zzdpl;
    }
    
    static int zzd(final byte[] array, int zza, final zzbae zzbae) throws IOException {
        zza = zza(array, zza, zzbae);
        final int zzdpl = zzbae.zzdpl;
        if (zzdpl == 0) {
            zzbae.zzdpn = "";
            return zza;
        }
        if (!zzbem.zzf(array, zza, zza + zzdpl)) {
            throw zzbbu.zzads();
        }
        zzbae.zzdpn = new String(array, zza, zzdpl, zzbbq.UTF_8);
        return zza + zzdpl;
    }
    
    static int zze(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | (array[n + 3] & 0xFF) << 24;
    }
    
    static int zze(final byte[] array, int zza, final zzbae zzbae) {
        zza = zza(array, zza, zzbae);
        final int zzdpl = zzbae.zzdpl;
        if (zzdpl == 0) {
            zzbae.zzdpn = zzbah.zzdpq;
            return zza;
        }
        zzbae.zzdpn = zzbah.zzc(array, zza, zzdpl);
        return zza + zzdpl;
    }
    
    static long zzf(final byte[] array, final int n) {
        return ((long)array[n] & 0xFFL) | ((long)array[n + 1] & 0xFFL) << 8 | ((long)array[n + 2] & 0xFFL) << 16 | ((long)array[n + 3] & 0xFFL) << 24 | ((long)array[n + 4] & 0xFFL) << 32 | ((long)array[n + 5] & 0xFFL) << 40 | ((long)array[n + 6] & 0xFFL) << 48 | ((long)array[n + 7] & 0xFFL) << 56;
    }
    
    static double zzg(final byte[] array, final int n) {
        return Double.longBitsToDouble(zzf(array, n));
    }
    
    static float zzh(final byte[] array, final int n) {
        return Float.intBitsToFloat(zze(array, n));
    }
}
