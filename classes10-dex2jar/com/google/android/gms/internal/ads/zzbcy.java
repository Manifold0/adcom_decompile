// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import sun.misc.Unsafe;

final class zzbcy<T> implements zzbdm<T>
{
    private static final Unsafe zzdwf;
    private final int[] zzdwg;
    private final Object[] zzdwh;
    private final int zzdwi;
    private final int zzdwj;
    private final int zzdwk;
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final boolean zzdwn;
    private final boolean zzdwo;
    private final boolean zzdwp;
    private final int[] zzdwq;
    private final int[] zzdwr;
    private final int[] zzdws;
    private final zzbdc zzdwt;
    private final zzbce zzdwu;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;
    private final zzbcp zzdwx;
    
    static {
        zzdwf = zzbek.zzagh();
    }
    
    private zzbcy(final int[] zzdwg, final Object[] zzdwh, final int zzdwi, final int zzdwj, final int zzdwk, final zzbcu zzdwl, final boolean zzdwo, final boolean b, final int[] zzdwq, final int[] zzdwr, final int[] zzdws, final zzbdc zzdwt, final zzbce zzdwu, final zzbee<?, ?> zzdwv, final zzbbd<?> zzdww, final zzbcp zzdwx) {
        this.zzdwg = zzdwg;
        this.zzdwh = zzdwh;
        this.zzdwi = zzdwi;
        this.zzdwj = zzdwj;
        this.zzdwk = zzdwk;
        this.zzdwn = (zzdwl instanceof zzbbo);
        this.zzdwo = zzdwo;
        this.zzdwm = (zzdww != null && zzdww.zzh(zzdwl));
        this.zzdwp = false;
        this.zzdwq = zzdwq;
        this.zzdwr = zzdwr;
        this.zzdws = zzdws;
        this.zzdwt = zzdwt;
        this.zzdwu = zzdwu;
        this.zzdwv = zzdwv;
        this.zzdww = zzdww;
        this.zzdwl = zzdwl;
        this.zzdwx = zzdwx;
    }
    
    private static int zza(final int n, final byte[] array, final int n2, final int n3, final Object o, final zzbae zzbae) throws IOException {
        return zzbad.zza(n, array, n2, n3, zzz(o), zzbae);
    }
    
    private static int zza(final zzbdm<?> zzbdm, final int n, final byte[] array, int i, final int n2, final zzbbt<?> zzbbt, final zzbae zzbae) throws IOException {
        i = zza(zzbdm, array, i, n2, zzbae);
        zzbbt.add(zzbae.zzdpn);
        while (i < n2) {
            final int zza = zzbad.zza(array, i, zzbae);
            if (n != zzbae.zzdpl) {
                break;
            }
            i = zza(zzbdm, array, zza, n2, zzbae);
            zzbbt.add(zzbae.zzdpn);
        }
        return i;
    }
    
    private static int zza(final zzbdm zzbdm, final byte[] array, int zza, final int n, final int n2, final zzbae zzbae) throws IOException {
        final zzbcy<Object> zzbcy = (zzbcy<Object>)zzbdm;
        final Object instance = zzbcy.newInstance();
        zza = zzbcy.zza(instance, array, zza, n, n2, zzbae);
        zzbcy.zzo(instance);
        zzbae.zzdpn = instance;
        return zza;
    }
    
    private static int zza(final zzbdm zzbdm, final byte[] array, int zza, final int n, final zzbae zzbae) throws IOException {
        final int n2 = zza + 1;
        int zzdpl = array[zza];
        if (zzdpl < 0) {
            zza = zzbad.zza(zzdpl, array, n2, zzbae);
            zzdpl = zzbae.zzdpl;
        }
        else {
            zza = n2;
        }
        if (zzdpl < 0 || zzdpl > n - zza) {
            throw zzbbu.zzadl();
        }
        final Object instance = zzbdm.newInstance();
        zzbdm.zza(instance, array, zza, zza + zzdpl, zzbae);
        zzbdm.zzo(instance);
        zzbae.zzdpn = instance;
        return zza + zzdpl;
    }
    
    private static <UT, UB> int zza(final zzbee<UT, UB> zzbee, final T t) {
        return zzbee.zzy(zzbee.zzac(t));
    }
    
    private final int zza(final T t, final byte[] array, int n, int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final long n8, final int n9, final zzbae zzbae) throws IOException {
        final Unsafe zzdwf = zzbcy.zzdwf;
        final long n10 = this.zzdwg[n9 + 2] & 0xFFFFF;
        Label_0137: {
            switch (n7) {
                case 51: {
                    if (n5 == 1) {
                        zzdwf.putObject(t, n8, zzbad.zzg(array, n));
                        n += 8;
                        break Label_0137;
                    }
                    break;
                }
                case 52: {
                    if (n5 == 5) {
                        zzdwf.putObject(t, n8, zzbad.zzh(array, n));
                        n += 4;
                        break Label_0137;
                    }
                    break;
                }
                case 53:
                case 54: {
                    if (n5 == 0) {
                        n = zzbad.zzb(array, n, zzbae);
                        zzdwf.putObject(t, n8, zzbae.zzdpm);
                        break Label_0137;
                    }
                    break;
                }
                case 55:
                case 62: {
                    if (n5 == 0) {
                        n = zzbad.zza(array, n, zzbae);
                        zzdwf.putObject(t, n8, zzbae.zzdpl);
                        break Label_0137;
                    }
                    break;
                }
                case 56:
                case 65: {
                    if (n5 == 1) {
                        zzdwf.putObject(t, n8, zzbad.zzf(array, n));
                        n += 8;
                        break Label_0137;
                    }
                    break;
                }
                case 57:
                case 64: {
                    if (n5 == 5) {
                        zzdwf.putObject(t, n8, zzbad.zze(array, n));
                        n += 4;
                        break Label_0137;
                    }
                    break;
                }
                case 58: {
                    if (n5 == 0) {
                        n = zzbad.zzb(array, n, zzbae);
                        zzdwf.putObject(t, n8, zzbae.zzdpm != 0L);
                        break Label_0137;
                    }
                    break;
                }
                case 59: {
                    if (n5 == 2) {
                        n = zzbad.zza(array, n, zzbae);
                        n2 = zzbae.zzdpl;
                        if (n2 == 0) {
                            zzdwf.putObject(t, n8, "");
                        }
                        else {
                            if ((0x20000000 & n6) != 0x0 && !zzbem.zzf(array, n, n + n2)) {
                                throw zzbbu.zzads();
                            }
                            zzdwf.putObject(t, n8, new String(array, n, n2, zzbbq.UTF_8));
                            n += n2;
                        }
                        zzdwf.putInt(t, n10, n4);
                        return n;
                    }
                    break;
                }
                case 60: {
                    if (n5 == 2) {
                        n = zza(this.zzcq(n9), array, n, n2, zzbae);
                        Object object;
                        if (zzdwf.getInt(t, n10) == n4) {
                            object = zzdwf.getObject(t, n8);
                        }
                        else {
                            object = null;
                        }
                        if (object == null) {
                            zzdwf.putObject(t, n8, zzbae.zzdpn);
                        }
                        else {
                            zzdwf.putObject(t, n8, zzbbq.zza(object, zzbae.zzdpn));
                        }
                        zzdwf.putInt(t, n10, n4);
                        return n;
                    }
                    break;
                }
                case 61: {
                    if (n5 == 2) {
                        n = zzbad.zza(array, n, zzbae);
                        n2 = zzbae.zzdpl;
                        if (n2 == 0) {
                            zzdwf.putObject(t, n8, zzbah.zzdpq);
                        }
                        else {
                            zzdwf.putObject(t, n8, zzbah.zzc(array, n, n2));
                            n += n2;
                        }
                        zzdwf.putInt(t, n10, n4);
                        return n;
                    }
                    break;
                }
                case 63: {
                    if (n5 != 0) {
                        break;
                    }
                    n = zzbad.zza(array, n, zzbae);
                    n2 = zzbae.zzdpl;
                    final zzbbs<?> zzcs = this.zzcs(n9);
                    if (zzcs == null || zzcs.zzq(n2) != null) {
                        zzdwf.putObject(t, n8, n2);
                        break Label_0137;
                    }
                    zzz(t).zzb(n3, (long)n2);
                    return n;
                }
                case 66: {
                    if (n5 == 0) {
                        n = zzbad.zza(array, n, zzbae);
                        zzdwf.putObject(t, n8, zzbaq.zzbu(zzbae.zzdpl));
                        break Label_0137;
                    }
                    break;
                }
                case 67: {
                    if (n5 == 0) {
                        n = zzbad.zzb(array, n, zzbae);
                        zzdwf.putObject(t, n8, zzbaq.zzl(zzbae.zzdpm));
                        break Label_0137;
                    }
                    break;
                }
                case 68: {
                    if (n5 != 3) {
                        break;
                    }
                    n = zza(this.zzcq(n9), array, n, n2, (n3 & 0xFFFFFFF8) | 0x4, zzbae);
                    Object object2;
                    if (zzdwf.getInt(t, n10) == n4) {
                        object2 = zzdwf.getObject(t, n8);
                    }
                    else {
                        object2 = null;
                    }
                    if (object2 == null) {
                        zzdwf.putObject(t, n8, zzbae.zzdpn);
                        break Label_0137;
                    }
                    zzdwf.putObject(t, n8, zzbbq.zza(object2, zzbae.zzdpn));
                    break Label_0137;
                }
            }
            return n;
        }
        zzdwf.putInt(t, n10, n4);
        return n;
    }
    
    private final int zza(final T t, final byte[] array, int i, int n, final int n2, int n3, int zza, final int n4, final long n5, int n6, final long n7, final zzbae zzbae) throws IOException {
        zzbbt<Double> zzbm = (zzbbt<Double>)zzbcy.zzdwf.getObject(t, n7);
        if (!zzbm.zzaay()) {
            final int size = zzbm.size();
            int n8;
            if (size == 0) {
                n8 = 10;
            }
            else {
                n8 = size << 1;
            }
            zzbm = zzbm.zzbm(n8);
            zzbcy.zzdwf.putObject(t, n7, zzbm);
        }
        Label_0211: {
            switch (n6) {
                default: {
                    n6 = i;
                    break;
                }
                case 18:
                case 35: {
                    if (zza == 2) {
                        final zzbay zzbay = (zzbay)zzbm;
                        for (i = zzbad.zza(array, i, zzbae), n = zzbae.zzdpl + i; i < n; i += 8) {
                            zzbay.zzd(zzbad.zzg(array, i));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 1) {
                            break;
                        }
                        final zzbay zzbay2 = (zzbay)zzbm;
                        zzbay2.zzd(zzbad.zzg(array, i));
                        i += 8;
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            zzbay2.zzd(zzbad.zzg(array, n3));
                            i = n3 + 8;
                        }
                    }
                    break;
                }
                case 19:
                case 36: {
                    if (zza == 2) {
                        final zzbbm zzbbm = (zzbbm)zzbm;
                        for (i = zzbad.zza(array, i, zzbae), n = zzbae.zzdpl + i; i < n; i += 4) {
                            zzbbm.zzd(zzbad.zzh(array, i));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 5) {
                            break;
                        }
                        final zzbbm zzbbm2 = (zzbbm)zzbm;
                        zzbbm2.zzd(zzbad.zzh(array, i));
                        i += 4;
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            zzbbm2.zzd(zzbad.zzh(array, n3));
                            i = n3 + 4;
                        }
                    }
                    break;
                }
                case 20:
                case 21:
                case 37:
                case 38: {
                    if (zza == 2) {
                        final zzbci zzbci = (zzbci)zzbm;
                        i = zzbad.zza(array, i, zzbae);
                        n = zzbae.zzdpl + i;
                        while (i < n) {
                            i = zzbad.zzb(array, i, zzbae);
                            zzbci.zzw(zzbae.zzdpm);
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 0) {
                            break;
                        }
                        final zzbci zzbci2 = (zzbci)zzbm;
                        i = zzbad.zzb(array, i, zzbae);
                        zzbci2.zzw(zzbae.zzdpm);
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            i = zzbad.zzb(array, n3, zzbae);
                            zzbci2.zzw(zzbae.zzdpm);
                        }
                    }
                    break;
                }
                case 22:
                case 29:
                case 39:
                case 43: {
                    if (zza == 2) {
                        return zzbad.zza(array, i, zzbm, zzbae);
                    }
                    n6 = i;
                    if (zza == 0) {
                        return zzbad.zza(n2, array, i, n, zzbm, zzbae);
                    }
                    break;
                }
                case 23:
                case 32:
                case 40:
                case 46: {
                    if (zza == 2) {
                        final zzbci zzbci3 = (zzbci)zzbm;
                        for (i = zzbad.zza(array, i, zzbae), n = zzbae.zzdpl + i; i < n; i += 8) {
                            zzbci3.zzw(zzbad.zzf(array, i));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 1) {
                            break;
                        }
                        final zzbci zzbci4 = (zzbci)zzbm;
                        zzbci4.zzw(zzbad.zzf(array, i));
                        i += 8;
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            zzbci4.zzw(zzbad.zzf(array, n3));
                            i = n3 + 8;
                        }
                    }
                    break;
                }
                case 24:
                case 31:
                case 41:
                case 45: {
                    if (zza == 2) {
                        final zzbbp zzbbp = (zzbbp)zzbm;
                        for (i = zzbad.zza(array, i, zzbae), n = zzbae.zzdpl + i; i < n; i += 4) {
                            zzbbp.zzco(zzbad.zze(array, i));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 5) {
                            break;
                        }
                        final zzbbp zzbbp2 = (zzbbp)zzbm;
                        zzbbp2.zzco(zzbad.zze(array, i));
                        i += 4;
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            zzbbp2.zzco(zzbad.zze(array, n3));
                            i = n3 + 4;
                        }
                    }
                    break;
                }
                case 25:
                case 42: {
                    if (zza == 2) {
                        final zzbaf zzbaf = (zzbaf)zzbm;
                        i = zzbad.zza(array, i, zzbae);
                        n = i + zzbae.zzdpl;
                        while (i < n) {
                            i = zzbad.zzb(array, i, zzbae);
                            zzbaf.addBoolean(zzbae.zzdpm != 0L);
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 0) {
                            break;
                        }
                        final zzbaf zzbaf2 = (zzbaf)zzbm;
                        i = zzbad.zzb(array, i, zzbae);
                        zzbaf2.addBoolean(zzbae.zzdpm != 0L);
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            i = zzbad.zzb(array, n3, zzbae);
                            zzbaf2.addBoolean(zzbae.zzdpm != 0L);
                        }
                    }
                    break;
                }
                case 26: {
                    n6 = i;
                    if (zza != 2) {
                        break;
                    }
                    if ((0x20000000L & n5) != 0x0L) {
                        i = zzbad.zza(array, i, zzbae);
                        n3 = zzbae.zzdpl;
                        if (n3 == 0) {
                            zzbm.add((Double)"");
                        }
                        else {
                            if (!zzbem.zzf(array, i, i + n3)) {
                                throw zzbbu.zzads();
                            }
                            zzbm.add((Double)new String(array, i, n3, zzbbq.UTF_8));
                            i += n3;
                        }
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            i = zzbad.zza(array, n3, zzbae);
                            n3 = zzbae.zzdpl;
                            if (n3 == 0) {
                                zzbm.add((Double)"");
                            }
                            else {
                                if (!zzbem.zzf(array, i, i + n3)) {
                                    break;
                                }
                                zzbm.add((Double)new String(array, i, n3, zzbbq.UTF_8));
                                i += n3;
                            }
                        }
                        throw zzbbu.zzads();
                    }
                    i = zzbad.zza(array, i, zzbae);
                    n3 = zzbae.zzdpl;
                    if (n3 == 0) {
                        zzbm.add((Double)"");
                    }
                    else {
                        zzbm.add((Double)new String(array, i, n3, zzbbq.UTF_8));
                        i += n3;
                    }
                    while (true) {
                        n6 = i;
                        if (i >= n) {
                            break Label_0211;
                        }
                        n3 = zzbad.zza(array, i, zzbae);
                        n6 = i;
                        if (n2 != zzbae.zzdpl) {
                            break Label_0211;
                        }
                        i = zzbad.zza(array, n3, zzbae);
                        n3 = zzbae.zzdpl;
                        if (n3 == 0) {
                            zzbm.add((Double)"");
                        }
                        else {
                            zzbm.add((Double)new String(array, i, n3, zzbbq.UTF_8));
                            i += n3;
                        }
                    }
                    break;
                }
                case 27: {
                    n6 = i;
                    if (zza == 2) {
                        return zza(this.zzcq(n4), n2, array, i, n, zzbm, zzbae);
                    }
                    break;
                }
                case 28: {
                    n6 = i;
                    if (zza != 2) {
                        break;
                    }
                    i = zzbad.zza(array, i, zzbae);
                    n3 = zzbae.zzdpl;
                    if (n3 == 0) {
                        zzbm.add((Double)zzbah.zzdpq);
                    }
                    else {
                        zzbm.add((Double)zzbah.zzc(array, i, n3));
                        i += n3;
                    }
                    while (true) {
                        n6 = i;
                        if (i >= n) {
                            break Label_0211;
                        }
                        n3 = zzbad.zza(array, i, zzbae);
                        n6 = i;
                        if (n2 != zzbae.zzdpl) {
                            break Label_0211;
                        }
                        i = zzbad.zza(array, n3, zzbae);
                        n3 = zzbae.zzdpl;
                        if (n3 == 0) {
                            zzbm.add((Double)zzbah.zzdpq);
                        }
                        else {
                            zzbm.add((Double)zzbah.zzc(array, i, n3));
                            i += n3;
                        }
                    }
                    break;
                }
                case 30:
                case 44: {
                    if (zza == 2) {
                        i = zzbad.zza(array, i, zzbm, zzbae);
                    }
                    else {
                        n6 = i;
                        if (zza != 0) {
                            break;
                        }
                        i = zzbad.zza(n2, array, i, n, zzbm, zzbae);
                    }
                    zzbef zzdtt;
                    if ((zzdtt = ((zzbbo)t).zzdtt) == zzbef.zzagc()) {
                        zzdtt = null;
                    }
                    final zzbef zzdtt2 = zzbdo.zza(n3, (List<Integer>)zzbm, this.zzcs(n4), zzdtt, this.zzdwv);
                    if (zzdtt2 != null) {
                        ((zzbbo)t).zzdtt = zzdtt2;
                        return i;
                    }
                    return i;
                }
                case 33:
                case 47: {
                    if (zza == 2) {
                        final zzbbp zzbbp3 = (zzbbp)zzbm;
                        i = zzbad.zza(array, i, zzbae);
                        n = zzbae.zzdpl + i;
                        while (i < n) {
                            i = zzbad.zza(array, i, zzbae);
                            zzbbp3.zzco(zzbaq.zzbu(zzbae.zzdpl));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 0) {
                            break;
                        }
                        final zzbbp zzbbp4 = (zzbbp)zzbm;
                        i = zzbad.zza(array, i, zzbae);
                        zzbbp4.zzco(zzbaq.zzbu(zzbae.zzdpl));
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            i = zzbad.zza(array, n3, zzbae);
                            zzbbp4.zzco(zzbaq.zzbu(zzbae.zzdpl));
                        }
                    }
                    break;
                }
                case 34:
                case 48: {
                    if (zza == 2) {
                        final zzbci zzbci5 = (zzbci)zzbm;
                        i = zzbad.zza(array, i, zzbae);
                        n = zzbae.zzdpl + i;
                        while (i < n) {
                            i = zzbad.zzb(array, i, zzbae);
                            zzbci5.zzw(zzbaq.zzl(zzbae.zzdpm));
                        }
                        if ((n6 = i) != n) {
                            throw zzbbu.zzadl();
                        }
                        break;
                    }
                    else {
                        n6 = i;
                        if (zza != 0) {
                            break;
                        }
                        final zzbci zzbci6 = (zzbci)zzbm;
                        i = zzbad.zzb(array, i, zzbae);
                        zzbci6.zzw(zzbaq.zzl(zzbae.zzdpm));
                        while (true) {
                            n6 = i;
                            if (i >= n) {
                                break Label_0211;
                            }
                            n3 = zzbad.zza(array, i, zzbae);
                            n6 = i;
                            if (n2 != zzbae.zzdpl) {
                                break Label_0211;
                            }
                            i = zzbad.zzb(array, n3, zzbae);
                            zzbci6.zzw(zzbaq.zzl(zzbae.zzdpm));
                        }
                    }
                    break;
                }
                case 49: {
                    n6 = i;
                    if (zza != 3) {
                        break;
                    }
                    final zzbdm zzcq = this.zzcq(n4);
                    n3 = ((n2 & 0xFFFFFFF8) | 0x4);
                    i = zza(zzcq, array, i, n, n3, zzbae);
                    zzbm.add((Double)zzbae.zzdpn);
                    while (true) {
                        n6 = i;
                        if (i >= n) {
                            break Label_0211;
                        }
                        zza = zzbad.zza(array, i, zzbae);
                        n6 = i;
                        if (n2 != zzbae.zzdpl) {
                            break Label_0211;
                        }
                        i = zza(zzcq, array, zza, n, n3, zzbae);
                        zzbm.add((Double)zzbae.zzdpn);
                    }
                    break;
                }
            }
        }
        return n6;
    }
    
    private final <K, V> int zza(final T t, final byte[] array, int i, final int n, int n2, int n3, final long n4, final zzbae zzbae) throws IOException {
        final Unsafe zzdwf = zzbcy.zzdwf;
        final Object zzcr = this.zzcr(n2);
        final Object object = zzdwf.getObject(t, n4);
        Object o;
        if (this.zzdwx.zzu(object)) {
            final Object zzw = this.zzdwx.zzw(zzcr);
            this.zzdwx.zzb(zzw, object);
            zzdwf.putObject(t, n4, zzw);
            o = zzw;
        }
        else {
            o = object;
        }
        final zzbcn<?, ?> zzx = this.zzdwx.zzx(zzcr);
        final Map<?, ?> zzs = this.zzdwx.zzs(o);
        i = zzbad.zza(array, i, zzbae);
        n2 = zzbae.zzdpl;
        if (n2 < 0 || n2 > n - i) {
            throw zzbbu.zzadl();
        }
        final int n5 = i + n2;
        Object o2 = zzx.zzdvz;
        Object o3 = zzx.zzdwb;
        while (i < n5) {
            n3 = i + 1;
            final int n6 = n2 = array[i];
            i = n3;
            if (n6 < 0) {
                i = zzbad.zza(n6, array, n3, zzbae);
                n2 = zzbae.zzdpl;
            }
            n3 = (n2 & 0x7);
            switch (n2 >>> 3) {
                case 1: {
                    if (n3 == zzx.zzdvy.zzagm()) {
                        i = zza(array, i, n, zzx.zzdvy, null, zzbae);
                        o2 = zzbae.zzdpn;
                        continue;
                    }
                    break;
                }
                case 2: {
                    if (n3 == zzx.zzdwa.zzagm()) {
                        i = zza(array, i, n, zzx.zzdwa, zzx.zzdwb.getClass(), zzbae);
                        o3 = zzbae.zzdpn;
                        continue;
                    }
                    break;
                }
            }
            i = zzbad.zza(n2, array, i, n, zzbae);
        }
        if (i != n5) {
            throw zzbbu.zzadr();
        }
        zzs.put(o2, o3);
        return n5;
    }
    
    private final int zza(final T t, final byte[] array, int i, final int n, final int n2, final zzbae zzbae) throws IOException {
        final Unsafe zzdwf = zzbcy.zzdwf;
        int n3 = -1;
        final int n4 = 0;
        int n5 = 0;
        int j = i;
        i = n4;
        while (true) {
        Label_0307_Outer:
            while (j < n) {
                final int n6 = j + 1;
                final byte b = array[j];
                int zza = n6;
                int zzdpl;
                if ((zzdpl = b) < 0) {
                    zza = zzbad.zza(b, array, n6, zzbae);
                    zzdpl = zzbae.zzdpl;
                }
                final int n7 = zzdpl >>> 3;
                final int n8 = zzdpl & 0x7;
                final int zzcw = this.zzcw(n7);
                while (true) {
                    Label_1599: {
                        if (zzcw == -1) {
                            break Label_1599;
                        }
                        final int n9 = this.zzdwg[zzcw + 1];
                        final int n10 = (0xFF00000 & n9) >>> 20;
                        final long n11 = 0xFFFFF & n9;
                        int n17;
                        int n18;
                        if (n10 <= 17) {
                            final int n12 = this.zzdwg[zzcw + 2];
                            final int n13 = 1 << (n12 >>> 20);
                            final int n14 = n12 & 0xFFFFF;
                            int int1 = i;
                            int n15;
                            if (n14 != (n15 = n3)) {
                                if (n3 != -1) {
                                    zzdwf.putInt(t, (long)n3, i);
                                }
                                int1 = zzdwf.getInt(t, (long)n14);
                                n15 = n14;
                            }
                            switch (n10) {
                                case 0: {
                                    if (n8 == 1) {
                                        zzbek.zza(t, n11, zzbad.zzg(array, zza));
                                        j = zza + 8;
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 1: {
                                    if (n8 == 5) {
                                        zzbek.zza(t, n11, zzbad.zzh(array, zza));
                                        j = zza + 4;
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 2:
                                case 3: {
                                    if (n8 == 0) {
                                        j = zzbad.zzb(array, zza, zzbae);
                                        zzdwf.putLong(t, n11, zzbae.zzdpm);
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 4:
                                case 11: {
                                    if (n8 == 0) {
                                        j = zzbad.zza(array, zza, zzbae);
                                        zzdwf.putInt(t, n11, zzbae.zzdpl);
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 5:
                                case 14: {
                                    if (n8 == 1) {
                                        zzdwf.putLong(t, n11, zzbad.zzf(array, zza));
                                        j = zza + 8;
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 6:
                                case 13: {
                                    if (n8 == 5) {
                                        zzdwf.putInt(t, n11, zzbad.zze(array, zza));
                                        j = zza + 4;
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 7: {
                                    if (n8 == 0) {
                                        j = zzbad.zzb(array, zza, zzbae);
                                        zzbek.zza(t, n11, zzbae.zzdpm != 0L);
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 8: {
                                    if (n8 == 2) {
                                        if ((0x20000000 & n9) == 0x0) {
                                            i = zzbad.zzc(array, zza, zzbae);
                                        }
                                        else {
                                            i = zzbad.zzd(array, zza, zzbae);
                                        }
                                        zzdwf.putObject(t, n11, zzbae.zzdpn);
                                        final int n16 = int1 | n13;
                                        n5 = zzdpl;
                                        j = i;
                                        i = n16;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 9: {
                                    if (n8 == 2) {
                                        j = zza(this.zzcq(zzcw), array, zza, n, zzbae);
                                        if ((int1 & n13) == 0x0) {
                                            zzdwf.putObject(t, n11, zzbae.zzdpn);
                                        }
                                        else {
                                            zzdwf.putObject(t, n11, zzbbq.zza(zzdwf.getObject(t, n11), zzbae.zzdpn));
                                        }
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 10: {
                                    if (n8 == 2) {
                                        j = zzbad.zze(array, zza, zzbae);
                                        zzdwf.putObject(t, n11, zzbae.zzdpn);
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 12: {
                                    if (n8 != 0) {
                                        break;
                                    }
                                    j = zzbad.zza(array, zza, zzbae);
                                    i = zzbae.zzdpl;
                                    final zzbbs<?> zzcs = this.zzcs(zzcw);
                                    if (zzcs == null || zzcs.zzq(i) != null) {
                                        zzdwf.putInt(t, n11, i);
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    zzz(t).zzb(zzdpl, (long)i);
                                    n5 = zzdpl;
                                    i = int1;
                                    n3 = n15;
                                    continue Label_0307_Outer;
                                }
                                case 15: {
                                    if (n8 == 0) {
                                        j = zzbad.zza(array, zza, zzbae);
                                        zzdwf.putInt(t, n11, zzbaq.zzbu(zzbae.zzdpl));
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 16: {
                                    if (n8 == 0) {
                                        j = zzbad.zzb(array, zza, zzbae);
                                        zzdwf.putLong(t, n11, zzbaq.zzl(zzbae.zzdpm));
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                                case 17: {
                                    if (n8 == 3) {
                                        j = zza(this.zzcq(zzcw), array, zza, n, n7 << 3 | 0x4, zzbae);
                                        if ((int1 & n13) == 0x0) {
                                            zzdwf.putObject(t, n11, zzbae.zzdpn);
                                        }
                                        else {
                                            zzdwf.putObject(t, n11, zzbbq.zza(zzdwf.getObject(t, n11), zzbae.zzdpn));
                                        }
                                        i = (int1 | n13);
                                        n5 = zzdpl;
                                        n3 = n15;
                                        continue Label_0307_Outer;
                                    }
                                    break;
                                }
                            }
                            i = int1;
                            n17 = n15;
                            n18 = zza;
                        }
                        else if (n10 == 27) {
                            if (n8 == 2) {
                                zzbbt<?> zzbm = (zzbbt<?>)zzdwf.getObject(t, n11);
                                if (!zzbm.zzaay()) {
                                    final int size = zzbm.size();
                                    int n19;
                                    if (size == 0) {
                                        n19 = 10;
                                    }
                                    else {
                                        n19 = size << 1;
                                    }
                                    zzbm = zzbm.zzbm(n19);
                                    zzdwf.putObject(t, n11, zzbm);
                                }
                                j = zza(this.zzcq(zzcw), zzdpl, array, zza, n, zzbm, zzbae);
                                n5 = zzdpl;
                                continue Label_0307_Outer;
                            }
                            break Label_1599;
                        }
                        else if (n10 <= 49) {
                            final int zza2 = this.zza(t, array, zza, n, zzdpl, n7, n8, zzcw, n9, n10, n11, zzbae);
                            n5 = zzdpl;
                            if ((j = zza2) != zza) {
                                continue Label_0307_Outer;
                            }
                            n17 = n3;
                            n18 = zza2;
                        }
                        else if (n10 == 50) {
                            if (n8 != 2) {
                                break Label_1599;
                            }
                            final int zza3 = this.zza(t, array, zza, n, zzcw, n7, n11, zzbae);
                            n5 = zzdpl;
                            if ((j = zza3) != zza) {
                                continue Label_0307_Outer;
                            }
                            n17 = n3;
                            n18 = zza3;
                        }
                        else {
                            final int zza4 = this.zza(t, array, zza, n, zzdpl, n7, n8, n9, n10, n11, zzcw, zzbae);
                            n5 = zzdpl;
                            if ((j = zza4) != zza) {
                                continue Label_0307_Outer;
                            }
                            n17 = n3;
                            n18 = zza4;
                        }
                        if (zzdpl == n2) {
                            final int n20 = i;
                            final int n21 = n17;
                            n5 = zzdpl;
                            final int n22 = n18;
                            if (n2 != 0) {
                                if (n21 != -1) {
                                    zzdwf.putInt(t, (long)n21, n20);
                                }
                                if (this.zzdwr != null) {
                                    final int[] zzdwr = this.zzdwr;
                                    final int length = zzdwr.length;
                                    Object o = null;
                                    for (i = 0; i < length; ++i) {
                                        o = this.zza(t, zzdwr[i], o, this.zzdwv);
                                    }
                                    if (o != null) {
                                        this.zzdwv.zzf(t, o);
                                    }
                                }
                                if (n2 == 0) {
                                    if (n22 != n) {
                                        throw zzbbu.zzadr();
                                    }
                                }
                                else if (n22 > n || n5 != n2) {
                                    throw zzbbu.zzadr();
                                }
                                return n22;
                            }
                        }
                        final int zza5 = zza(zzdpl, array, n18, n, t, zzbae);
                        n3 = n17;
                        n5 = zzdpl;
                        j = zza5;
                        continue Label_0307_Outer;
                    }
                    int n17 = n3;
                    int n18 = zza;
                    continue;
                }
            }
            final int n22 = j;
            final int n21 = n3;
            final int n20 = i;
            continue;
        }
    }
    
    private static int zza(final byte[] array, int n, final int n2, final zzbes zzbes, final Class<?> clazz, final zzbae zzbae) throws IOException {
        switch (zzbcz.zzdql[zzbes.ordinal()]) {
            default: {
                throw new RuntimeException("unsupported field type.");
            }
            case 1: {
                n = zzbad.zzb(array, n, zzbae);
                zzbae.zzdpn = (zzbae.zzdpm != 0L);
                return n;
            }
            case 2: {
                return zzbad.zze(array, n, zzbae);
            }
            case 3: {
                zzbae.zzdpn = zzbad.zzg(array, n);
                return n + 8;
            }
            case 4:
            case 5: {
                zzbae.zzdpn = zzbad.zze(array, n);
                return n + 4;
            }
            case 6:
            case 7: {
                zzbae.zzdpn = zzbad.zzf(array, n);
                return n + 8;
            }
            case 8: {
                zzbae.zzdpn = zzbad.zzh(array, n);
                return n + 4;
            }
            case 9:
            case 10:
            case 11: {
                n = zzbad.zza(array, n, zzbae);
                zzbae.zzdpn = zzbae.zzdpl;
                return n;
            }
            case 12:
            case 13: {
                n = zzbad.zzb(array, n, zzbae);
                zzbae.zzdpn = zzbae.zzdpm;
                return n;
            }
            case 14: {
                return zza(zzbdg.zzaeo().zze(clazz), array, n, n2, zzbae);
            }
            case 15: {
                n = zzbad.zza(array, n, zzbae);
                zzbae.zzdpn = zzbaq.zzbu(zzbae.zzdpl);
                return n;
            }
            case 16: {
                n = zzbad.zzb(array, n, zzbae);
                zzbae.zzdpn = zzbaq.zzl(zzbae.zzdpm);
                return n;
            }
            case 17: {
                return zzbad.zzd(array, n, zzbae);
            }
        }
    }
    
    static <T> zzbcy<T> zza(final Class<T> clazz, final zzbcs zzbcs, final zzbdc zzbdc, final zzbce zzbce, final zzbee<?, ?> zzbee, final zzbbd<?> zzbbd, final zzbcp zzbcp) {
        if (zzbcs instanceof zzbdi) {
            final zzbdi zzbdi = (zzbdi)zzbcs;
            final boolean b = zzbdi.zzaeh() == zzbbo.zze.zzduj;
            int zzaer;
            int zzaes;
            int zzaew;
            if (zzbdi.getFieldCount() == 0) {
                zzaer = 0;
                zzaes = 0;
                zzaew = 0;
            }
            else {
                zzaer = zzbdi.zzaer();
                zzaes = zzbdi.zzaes();
                zzaew = zzbdi.zzaew();
            }
            final int[] array = new int[zzaew << 2];
            final Object[] array2 = new Object[zzaew << 1];
            int[] array3;
            if (zzbdi.zzaet() > 0) {
                array3 = new int[zzbdi.zzaet()];
            }
            else {
                array3 = null;
            }
            int[] array4;
            if (zzbdi.zzaeu() > 0) {
                array4 = new int[zzbdi.zzaeu()];
            }
            else {
                array4 = null;
            }
            int n = 0;
            int n2 = 0;
            final zzbdj zzaeq = zzbdi.zzaeq();
            if (zzaeq.next()) {
                int zzaci = zzaeq.zzaci();
                int n3 = 0;
                while (true) {
                    int zzaci2;
                    int n5;
                    int n6;
                    if (zzaci < zzbdi.zzaex() && n3 < zzaci - zzaer << 2) {
                        int n4 = 0;
                        while (true) {
                            zzaci2 = zzaci;
                            n5 = n2;
                            n6 = n;
                            if (n4 >= 4) {
                                break;
                            }
                            array[n3 + n4] = -1;
                            ++n4;
                        }
                    }
                    else {
                        int n7;
                        int n8;
                        int zzafh;
                        if (zzaeq.zzafb()) {
                            n7 = (int)zzbek.zza(zzaeq.zzafc());
                            n8 = (int)zzbek.zza(zzaeq.zzafd());
                            zzafh = 0;
                        }
                        else {
                            n7 = (int)zzbek.zza(zzaeq.zzafe());
                            if (zzaeq.zzaff()) {
                                n8 = (int)zzbek.zza(zzaeq.zzafg());
                                zzafh = zzaeq.zzafh();
                            }
                            else {
                                n8 = 0;
                                zzafh = 0;
                            }
                        }
                        array[n3] = zzaeq.zzaci();
                        int n9;
                        if (zzaeq.zzafj()) {
                            n9 = 536870912;
                        }
                        else {
                            n9 = 0;
                        }
                        int n10;
                        if (zzaeq.zzafi()) {
                            n10 = 268435456;
                        }
                        else {
                            n10 = 0;
                        }
                        array[n3 + 1] = (n7 | (n9 | n10 | zzaeq.zzaez() << 20));
                        array[n3 + 2] = (n8 | zzafh << 20);
                        if (zzaeq.zzafm() != null) {
                            array2[n3 / 4 << 1] = zzaeq.zzafm();
                            if (zzaeq.zzafk() != null) {
                                array2[(n3 / 4 << 1) + 1] = zzaeq.zzafk();
                            }
                            else if (zzaeq.zzafl() != null) {
                                array2[(n3 / 4 << 1) + 1] = zzaeq.zzafl();
                            }
                        }
                        else if (zzaeq.zzafk() != null) {
                            array2[(n3 / 4 << 1) + 1] = zzaeq.zzafk();
                        }
                        else if (zzaeq.zzafl() != null) {
                            array2[(n3 / 4 << 1) + 1] = zzaeq.zzafl();
                        }
                        final int zzaez = zzaeq.zzaez();
                        int n11;
                        if (zzaez == zzbbj.zzdta.ordinal()) {
                            array3[n] = n3;
                            n6 = n + 1;
                            n11 = n2;
                        }
                        else {
                            n11 = n2;
                            n6 = n;
                            if (zzaez >= 18) {
                                n11 = n2;
                                n6 = n;
                                if (zzaez <= 49) {
                                    array4[n2] = (array[n3 + 1] & 0xFFFFF);
                                    n11 = n2 + 1;
                                    n6 = n;
                                }
                            }
                        }
                        if (!zzaeq.next()) {
                            break;
                        }
                        zzaci2 = zzaeq.zzaci();
                        n5 = n11;
                    }
                    n3 += 4;
                    zzaci = zzaci2;
                    n2 = n5;
                    n = n6;
                }
            }
            return new zzbcy<T>(array, array2, zzaer, zzaes, zzbdi.zzaex(), zzbdi.zzaej(), b, false, zzbdi.zzaev(), array3, array4, zzbdc, zzbce, zzbee, zzbbd, zzbcp);
        }
        ((zzbdz)zzbcs).zzaeh();
        throw new NoSuchMethodError();
    }
    
    private final <K, V, UT, UB> UB zza(final int n, final int n2, final Map<K, V> map, final zzbbs<?> zzbbs, UB ub, final zzbee<UT, UB> zzbee) {
        final zzbcn<?, ?> zzx = this.zzdwx.zzx(this.zzcr(n));
        final Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<K, V> entry = iterator.next();
            if (zzbbs.zzq((int)entry.getValue()) == null) {
                UB zzagb;
                if ((zzagb = ub) == null) {
                    zzagb = zzbee.zzagb();
                }
                final zzbam zzbo = zzbah.zzbo(zzbcm.zza(zzx, entry.getKey(), entry.getValue()));
                final zzbav zzabj = zzbo.zzabj();
                try {
                    zzbcm.zza(zzabj, zzx, entry.getKey(), entry.getValue());
                    zzbee.zza(zzagb, n2, zzbo.zzabi());
                    iterator.remove();
                    ub = zzagb;
                    continue;
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
        }
        return ub;
    }
    
    private final <UT, UB> UB zza(Object zzp, final int n, final UB ub, final zzbee<UT, UB> zzbee) {
        final int n2 = this.zzdwg[n];
        zzp = zzbek.zzp(zzp, this.zzct(n) & 0xFFFFF);
        if (zzp != null) {
            final zzbbs<?> zzcs = this.zzcs(n);
            if (zzcs != null) {
                return this.zza(n, n2, this.zzdwx.zzs(zzp), zzcs, ub, zzbee);
            }
        }
        return ub;
    }
    
    private static void zza(final int n, final Object o, final zzbey zzbey) throws IOException {
        if (o instanceof String) {
            zzbey.zzf(n, (String)o);
            return;
        }
        zzbey.zza(n, (zzbah)o);
    }
    
    private static <UT, UB> void zza(final zzbee<UT, UB> zzbee, final T t, final zzbey zzbey) throws IOException {
        zzbee.zza(zzbee.zzac(t), zzbey);
    }
    
    private final <K, V> void zza(final zzbey zzbey, final int n, final Object o, final int n2) throws IOException {
        if (o != null) {
            zzbey.zza(n, this.zzdwx.zzx(this.zzcr(n2)), this.zzdwx.zzt(o));
        }
    }
    
    private final void zza(final Object o, final int n, final zzbdl zzbdl) throws IOException {
        if (zzcv(n)) {
            zzbek.zza(o, n & 0xFFFFF, zzbdl.zzabr());
            return;
        }
        if (this.zzdwn) {
            zzbek.zza(o, n & 0xFFFFF, zzbdl.readString());
            return;
        }
        zzbek.zza(o, n & 0xFFFFF, zzbdl.zzabs());
    }
    
    private final void zza(final T t, final T t2, final int n) {
        final long n2 = this.zzct(n) & 0xFFFFF;
        if (this.zza(t2, n)) {
            final Object zzp = zzbek.zzp(t, n2);
            final Object zzp2 = zzbek.zzp(t2, n2);
            if (zzp != null && zzp2 != null) {
                zzbek.zza(t, n2, zzbbq.zza(zzp, zzp2));
                this.zzb(t, n);
                return;
            }
            if (zzp2 != null) {
                zzbek.zza(t, n2, zzp2);
                this.zzb(t, n);
            }
        }
    }
    
    private final boolean zza(final T t, int n) {
        if (!this.zzdwo) {
            n = this.zzcu(n);
            return (zzbek.zzk(t, n & 0xFFFFF) & 1 << (n >>> 20)) != 0x0;
        }
        n = this.zzct(n);
        final long n2 = n & 0xFFFFF;
        switch ((n & 0xFF00000) >>> 20) {
            default: {
                throw new IllegalArgumentException();
            }
            case 0: {
                return zzbek.zzo(t, n2) != 0.0;
            }
            case 1: {
                return zzbek.zzn(t, n2) != 0.0f;
            }
            case 2: {
                return zzbek.zzl(t, n2) != 0L;
            }
            case 3: {
                return zzbek.zzl(t, n2) != 0L;
            }
            case 4: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 5: {
                return zzbek.zzl(t, n2) != 0L;
            }
            case 6: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 7: {
                return zzbek.zzm(t, n2);
            }
            case 8: {
                final Object zzp = zzbek.zzp(t, n2);
                if (zzp instanceof String) {
                    return !((String)zzp).isEmpty();
                }
                if (zzp instanceof zzbah) {
                    return !zzbah.zzdpq.equals(zzp);
                }
                throw new IllegalArgumentException();
            }
            case 9: {
                return zzbek.zzp(t, n2) != null;
            }
            case 10: {
                return !zzbah.zzdpq.equals(zzbek.zzp(t, n2));
            }
            case 11: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 12: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 13: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 14: {
                return zzbek.zzl(t, n2) != 0L;
            }
            case 15: {
                return zzbek.zzk(t, n2) != 0;
            }
            case 16: {
                return zzbek.zzl(t, n2) != 0L;
            }
            case 17: {
                return zzbek.zzp(t, n2) != null;
            }
        }
    }
    
    private final boolean zza(final T t, final int n, final int n2) {
        return zzbek.zzk(t, this.zzcu(n2) & 0xFFFFF) == n;
    }
    
    private final boolean zza(final T t, final int n, final int n2, final int n3) {
        if (this.zzdwo) {
            return this.zza(t, n);
        }
        return (n2 & n3) != 0x0;
    }
    
    private static boolean zza(final Object o, final int n, final zzbdm zzbdm) {
        return zzbdm.zzaa(zzbek.zzp(o, 0xFFFFF & n));
    }
    
    private final void zzb(final T t, int zzcu) {
        if (this.zzdwo) {
            return;
        }
        zzcu = this.zzcu(zzcu);
        final long n = zzcu & 0xFFFFF;
        zzbek.zzb(t, n, zzbek.zzk(t, n) | 1 << (zzcu >>> 20));
    }
    
    private final void zzb(final T t, final int n, final int n2) {
        zzbek.zzb(t, this.zzcu(n2) & 0xFFFFF, n);
    }
    
    private final void zzb(final T t, final zzbey zzbey) throws IOException {
        final Iterator<Map.Entry> iterator = null;
        Map.Entry<?, ?> entry2;
        final Map.Entry<?, ?> entry = entry2 = null;
        Object iterator2 = iterator;
        if (this.zzdwm) {
            final zzbbg<?> zzm = this.zzdww.zzm(t);
            entry2 = entry;
            iterator2 = iterator;
            if (!zzm.isEmpty()) {
                iterator2 = zzm.iterator();
                entry2 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator2).next();
            }
        }
        final int length = this.zzdwg.length;
        final Unsafe zzdwf = zzbcy.zzdwf;
        int i = 0;
        int n = -1;
        int int1 = 0;
        while (i < length) {
            final int zzct = this.zzct(i);
            final int n2 = this.zzdwg[i];
            final int n3 = (0xFF00000 & zzct) >>> 20;
            int n4 = 0;
            int n8;
            int n9;
            if (!this.zzdwo && n3 <= 17) {
                final int n5 = this.zzdwg[i + 2];
                final int n6 = 0xFFFFF & n5;
                if (n6 != n) {
                    int1 = zzdwf.getInt(t, (long)n6);
                    n = n6;
                }
                n4 = 1 << (n5 >>> 20);
                final int n7 = int1;
                n8 = n;
                n9 = n7;
            }
            else {
                final int n10 = n;
                n9 = int1;
                n8 = n10;
            }
            while (entry2 != null && this.zzdww.zza(entry2) <= n2) {
                this.zzdww.zza(zzbey, entry2);
                if (((Iterator)iterator2).hasNext()) {
                    entry2 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator2).next();
                }
                else {
                    entry2 = null;
                }
            }
            final long n11 = 0xFFFFF & zzct;
            switch (n3) {
                case 0: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zza(n2, zzbek.zzo(t, n11));
                        break;
                    }
                    break;
                }
                case 1: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zza(n2, zzbek.zzn(t, n11));
                        break;
                    }
                    break;
                }
                case 2: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzi(n2, zzdwf.getLong(t, n11));
                        break;
                    }
                    break;
                }
                case 3: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zza(n2, zzdwf.getLong(t, n11));
                        break;
                    }
                    break;
                }
                case 4: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzm(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 5: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzc(n2, zzdwf.getLong(t, n11));
                        break;
                    }
                    break;
                }
                case 6: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzp(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 7: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzf(n2, zzbek.zzm(t, n11));
                        break;
                    }
                    break;
                }
                case 8: {
                    if ((n9 & n4) != 0x0) {
                        zza(n2, zzdwf.getObject(t, n11), zzbey);
                        break;
                    }
                    break;
                }
                case 9: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zza(n2, zzdwf.getObject(t, n11), this.zzcq(i));
                        break;
                    }
                    break;
                }
                case 10: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zza(n2, (zzbah)zzdwf.getObject(t, n11));
                        break;
                    }
                    break;
                }
                case 11: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzn(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 12: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzx(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 13: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzw(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 14: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzj(n2, zzdwf.getLong(t, n11));
                        break;
                    }
                    break;
                }
                case 15: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzo(n2, zzdwf.getInt(t, n11));
                        break;
                    }
                    break;
                }
                case 16: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzb(n2, zzdwf.getLong(t, n11));
                        break;
                    }
                    break;
                }
                case 17: {
                    if ((n9 & n4) != 0x0) {
                        zzbey.zzb(n2, zzdwf.getObject(t, n11), this.zzcq(i));
                        break;
                    }
                    break;
                }
                case 18: {
                    zzbdo.zza(this.zzdwg[i], (List<Double>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 19: {
                    zzbdo.zzb(this.zzdwg[i], (List<Float>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 20: {
                    zzbdo.zzc(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 21: {
                    zzbdo.zzd(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 22: {
                    zzbdo.zzh(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 23: {
                    zzbdo.zzf(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 24: {
                    zzbdo.zzk(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 25: {
                    zzbdo.zzn(this.zzdwg[i], (List<Boolean>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 26: {
                    zzbdo.zza(this.zzdwg[i], (List<String>)zzdwf.getObject(t, n11), zzbey);
                    break;
                }
                case 27: {
                    zzbdo.zza(this.zzdwg[i], (List<?>)zzdwf.getObject(t, n11), zzbey, this.zzcq(i));
                    break;
                }
                case 28: {
                    zzbdo.zzb(this.zzdwg[i], (List<zzbah>)zzdwf.getObject(t, n11), zzbey);
                    break;
                }
                case 29: {
                    zzbdo.zzi(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 30: {
                    zzbdo.zzm(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 31: {
                    zzbdo.zzl(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 32: {
                    zzbdo.zzg(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 33: {
                    zzbdo.zzj(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 34: {
                    zzbdo.zze(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, false);
                    break;
                }
                case 35: {
                    zzbdo.zza(this.zzdwg[i], (List<Double>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 36: {
                    zzbdo.zzb(this.zzdwg[i], (List<Float>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 37: {
                    zzbdo.zzc(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 38: {
                    zzbdo.zzd(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 39: {
                    zzbdo.zzh(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 40: {
                    zzbdo.zzf(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 41: {
                    zzbdo.zzk(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 42: {
                    zzbdo.zzn(this.zzdwg[i], (List<Boolean>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 43: {
                    zzbdo.zzi(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 44: {
                    zzbdo.zzm(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 45: {
                    zzbdo.zzl(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 46: {
                    zzbdo.zzg(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 47: {
                    zzbdo.zzj(this.zzdwg[i], (List<Integer>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 48: {
                    zzbdo.zze(this.zzdwg[i], (List<Long>)zzdwf.getObject(t, n11), zzbey, true);
                    break;
                }
                case 49: {
                    zzbdo.zzb(this.zzdwg[i], (List<?>)zzdwf.getObject(t, n11), zzbey, this.zzcq(i));
                    break;
                }
                case 50: {
                    this.zza(zzbey, n2, zzdwf.getObject(t, n11), i);
                    break;
                }
                case 51: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zza(n2, zzf(t, n11));
                        break;
                    }
                    break;
                }
                case 52: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zza(n2, zzg(t, n11));
                        break;
                    }
                    break;
                }
                case 53: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzi(n2, zzi(t, n11));
                        break;
                    }
                    break;
                }
                case 54: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zza(n2, zzi(t, n11));
                        break;
                    }
                    break;
                }
                case 55: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzm(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 56: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzc(n2, zzi(t, n11));
                        break;
                    }
                    break;
                }
                case 57: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzp(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 58: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzf(n2, zzj(t, n11));
                        break;
                    }
                    break;
                }
                case 59: {
                    if (this.zza(t, n2, i)) {
                        zza(n2, zzdwf.getObject(t, n11), zzbey);
                        break;
                    }
                    break;
                }
                case 60: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zza(n2, zzdwf.getObject(t, n11), this.zzcq(i));
                        break;
                    }
                    break;
                }
                case 61: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zza(n2, (zzbah)zzdwf.getObject(t, n11));
                        break;
                    }
                    break;
                }
                case 62: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzn(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 63: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzx(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 64: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzw(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 65: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzj(n2, zzi(t, n11));
                        break;
                    }
                    break;
                }
                case 66: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzo(n2, zzh(t, n11));
                        break;
                    }
                    break;
                }
                case 67: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzb(n2, zzi(t, n11));
                        break;
                    }
                    break;
                }
                case 68: {
                    if (this.zza(t, n2, i)) {
                        zzbey.zzb(n2, zzdwf.getObject(t, n11), this.zzcq(i));
                        break;
                    }
                    break;
                }
            }
            final int n12 = i + 4;
            final int n13 = n8;
            int1 = n9;
            n = n13;
            i = n12;
        }
        while (entry2 != null) {
            this.zzdww.zza(zzbey, entry2);
            if (((Iterator)iterator2).hasNext()) {
                entry2 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator2).next();
            }
            else {
                entry2 = null;
            }
        }
        zza(this.zzdwv, t, zzbey);
    }
    
    private final void zzb(final T t, final T t2, final int n) {
        final int zzct = this.zzct(n);
        final int n2 = this.zzdwg[n];
        final long n3 = zzct & 0xFFFFF;
        if (this.zza(t2, n2, n)) {
            final Object zzp = zzbek.zzp(t, n3);
            final Object zzp2 = zzbek.zzp(t2, n3);
            if (zzp != null && zzp2 != null) {
                zzbek.zza(t, n3, zzbbq.zza(zzp, zzp2));
                this.zzb(t, n2, n);
                return;
            }
            if (zzp2 != null) {
                zzbek.zza(t, n3, zzp2);
                this.zzb(t, n2, n);
            }
        }
    }
    
    private final boolean zzc(final T t, final T t2, final int n) {
        return this.zza(t, n) == this.zza(t2, n);
    }
    
    private final zzbdm zzcq(int n) {
        n = n / 4 << 1;
        final zzbdm zzbdm = (zzbdm)this.zzdwh[n];
        if (zzbdm != null) {
            return zzbdm;
        }
        return (zzbdm)(this.zzdwh[n] = zzbdg.zzaeo().zze((Class<Object>)this.zzdwh[n + 1]));
    }
    
    private final Object zzcr(final int n) {
        return this.zzdwh[n / 4 << 1];
    }
    
    private final zzbbs<?> zzcs(final int n) {
        return (zzbbs<?>)this.zzdwh[(n / 4 << 1) + 1];
    }
    
    private final int zzct(final int n) {
        return this.zzdwg[n + 1];
    }
    
    private final int zzcu(final int n) {
        return this.zzdwg[n + 2];
    }
    
    private static boolean zzcv(final int n) {
        return (0x20000000 & n) != 0x0;
    }
    
    private final int zzcw(final int n) {
        if (n >= this.zzdwi) {
            int n2;
            if (n < this.zzdwk) {
                n2 = n - this.zzdwi << 2;
                if (this.zzdwg[n2] != n) {
                    return -1;
                }
            }
            else {
                if (n <= this.zzdwj) {
                    final int zzdwk = this.zzdwk;
                    final int zzdwi = this.zzdwi;
                    final int n3 = this.zzdwg.length / 4;
                    int i = zzdwk - zzdwi;
                    int n4 = n3 - 1;
                    while (i <= n4) {
                        final int n5 = n4 + i >>> 1;
                        n2 = n5 << 2;
                        final int n6 = this.zzdwg[n2];
                        if (n == n6) {
                            return n2;
                        }
                        if (n < n6) {
                            n4 = n5 - 1;
                        }
                        else {
                            i = n5 + 1;
                        }
                    }
                    return -1;
                }
                return -1;
            }
            return n2;
        }
        return -1;
    }
    
    private static <E> List<E> zze(final Object o, final long n) {
        return (List<E>)zzbek.zzp(o, n);
    }
    
    private static <T> double zzf(final T t, final long n) {
        return (double)zzbek.zzp(t, n);
    }
    
    private static <T> float zzg(final T t, final long n) {
        return (float)zzbek.zzp(t, n);
    }
    
    private static <T> int zzh(final T t, final long n) {
        return (int)zzbek.zzp(t, n);
    }
    
    private static <T> long zzi(final T t, final long n) {
        return (long)zzbek.zzp(t, n);
    }
    
    private static <T> boolean zzj(final T t, final long n) {
        return (boolean)zzbek.zzp(t, n);
    }
    
    private static zzbef zzz(final Object o) {
        zzbef zzdtt;
        if ((zzdtt = ((zzbbo)o).zzdtt) == zzbef.zzagc()) {
            zzdtt = zzbef.zzagd();
            ((zzbbo)o).zzdtt = zzdtt;
        }
        return zzdtt;
    }
    
    @Override
    public final boolean equals(final T t, final T t2) {
        for (int length = this.zzdwg.length, i = 0; i < length; i += 4) {
            final int zzct = this.zzct(i);
            final long n = zzct & 0xFFFFF;
            int n2 = 0;
            Label_0331: {
                switch ((zzct & 0xFF00000) >>> 20) {
                    case 0: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 1: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 2: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 3: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 4: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 5: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 6: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 7: {
                        if (!this.zzc(t, t2, i) || zzbek.zzm(t, n) != zzbek.zzm(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 8: {
                        if (!this.zzc(t, t2, i) || !zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n))) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 9: {
                        if (!this.zzc(t, t2, i) || !zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n))) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 10: {
                        if (!this.zzc(t, t2, i) || !zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n))) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 11: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 12: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 13: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 14: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 15: {
                        if (!this.zzc(t, t2, i) || zzbek.zzk(t, n) != zzbek.zzk(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 16: {
                        if (!this.zzc(t, t2, i) || zzbek.zzl(t, n) != zzbek.zzl(t2, n)) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 17: {
                        if (!this.zzc(t, t2, i) || !zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n))) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49: {
                        n2 = (zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n)) ? 1 : 0);
                        break Label_0331;
                    }
                    case 50: {
                        n2 = (zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n)) ? 1 : 0);
                        break Label_0331;
                    }
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68: {
                        final int zzcu = this.zzcu(i);
                        if (zzbek.zzk(t, zzcu & 0xFFFFF) != zzbek.zzk(t2, zzcu & 0xFFFFF) || !zzbdo.zzd(zzbek.zzp(t, n), zzbek.zzp(t2, n))) {
                            n2 = 0;
                            break Label_0331;
                        }
                        break;
                    }
                }
                n2 = 1;
            }
            if (n2 == 0) {
                return false;
            }
        }
        if (this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2))) {
            return !this.zzdwm || this.zzdww.zzm(t).equals(this.zzdww.zzm(t2));
        }
        return false;
    }
    
    @Override
    public final int hashCode(final T t) {
        final int length = this.zzdwg.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final int zzct = this.zzct(i);
            final int n2 = this.zzdwg[i];
            final long n3 = 0xFFFFF & zzct;
            switch ((zzct & 0xFF00000) >>> 20) {
                case 0: {
                    n = n * 53 + zzbbq.zzv(Double.doubleToLongBits(zzbek.zzo(t, n3)));
                    break;
                }
                case 1: {
                    n = n * 53 + Float.floatToIntBits(zzbek.zzn(t, n3));
                    break;
                }
                case 2: {
                    n = n * 53 + zzbbq.zzv(zzbek.zzl(t, n3));
                    break;
                }
                case 3: {
                    n = n * 53 + zzbbq.zzv(zzbek.zzl(t, n3));
                    break;
                }
                case 4: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 5: {
                    n = n * 53 + zzbbq.zzv(zzbek.zzl(t, n3));
                    break;
                }
                case 6: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 7: {
                    n = n * 53 + zzbbq.zzar(zzbek.zzm(t, n3));
                    break;
                }
                case 8: {
                    n = ((String)zzbek.zzp(t, n3)).hashCode() + n * 53;
                    break;
                }
                case 9: {
                    final Object zzp = zzbek.zzp(t, n3);
                    int hashCode;
                    if (zzp != null) {
                        hashCode = zzp.hashCode();
                    }
                    else {
                        hashCode = 37;
                    }
                    n = hashCode + n * 53;
                    break;
                }
                case 10: {
                    n = n * 53 + zzbek.zzp(t, n3).hashCode();
                    break;
                }
                case 11: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 12: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 13: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 14: {
                    n = n * 53 + zzbbq.zzv(zzbek.zzl(t, n3));
                    break;
                }
                case 15: {
                    n = n * 53 + zzbek.zzk(t, n3);
                    break;
                }
                case 16: {
                    n = n * 53 + zzbbq.zzv(zzbek.zzl(t, n3));
                    break;
                }
                case 17: {
                    final Object zzp2 = zzbek.zzp(t, n3);
                    int hashCode2;
                    if (zzp2 != null) {
                        hashCode2 = zzp2.hashCode();
                    }
                    else {
                        hashCode2 = 37;
                    }
                    n = hashCode2 + n * 53;
                    break;
                }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49: {
                    n = n * 53 + zzbek.zzp(t, n3).hashCode();
                    break;
                }
                case 50: {
                    n = n * 53 + zzbek.zzp(t, n3).hashCode();
                    break;
                }
                case 51: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(Double.doubleToLongBits(zzf(t, n3)));
                        break;
                    }
                    break;
                }
                case 52: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + Float.floatToIntBits(zzg(t, n3));
                        break;
                    }
                    break;
                }
                case 53: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(zzi(t, n3));
                        break;
                    }
                    break;
                }
                case 54: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(zzi(t, n3));
                        break;
                    }
                    break;
                }
                case 55: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 56: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(zzi(t, n3));
                        break;
                    }
                    break;
                }
                case 57: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 58: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzar(zzj(t, n3));
                        break;
                    }
                    break;
                }
                case 59: {
                    if (this.zza(t, n2, i)) {
                        n = ((String)zzbek.zzp(t, n3)).hashCode() + n * 53;
                        break;
                    }
                    break;
                }
                case 60: {
                    if (this.zza(t, n2, i)) {
                        n = zzbek.zzp(t, n3).hashCode() + n * 53;
                        break;
                    }
                    break;
                }
                case 61: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbek.zzp(t, n3).hashCode();
                        break;
                    }
                    break;
                }
                case 62: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 63: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 64: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 65: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(zzi(t, n3));
                        break;
                    }
                    break;
                }
                case 66: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzh(t, n3);
                        break;
                    }
                    break;
                }
                case 67: {
                    if (this.zza(t, n2, i)) {
                        n = n * 53 + zzbbq.zzv(zzi(t, n3));
                        break;
                    }
                    break;
                }
                case 68: {
                    if (this.zza(t, n2, i)) {
                        n = zzbek.zzp(t, n3).hashCode() + n * 53;
                        break;
                    }
                    break;
                }
            }
            i += 4;
        }
        int n4 = n * 53 + this.zzdwv.zzac(t).hashCode();
        if (this.zzdwm) {
            n4 = n4 * 53 + this.zzdww.zzm(t).hashCode();
        }
        return n4;
    }
    
    @Override
    public final T newInstance() {
        return (T)this.zzdwt.newInstance(this.zzdwl);
    }
    
    @Override
    public final void zza(final T t, zzbdl zzbdl, final zzbbb zzbbb) throws IOException {
        if (zzbbb == null) {
            throw new NullPointerException();
        }
        final zzbee<?, ?> zzdwv = this.zzdwv;
        final zzbbd<?> zzdww = this.zzdww;
        Object o = null;
        Object o2 = null;
        Object zza;
        int zzaci;
        int zzcw;
        Object o3;
        int[] zzdwr;
        int length;
        int n;
        Object o4;
        Object zzn;
        boolean zza2;
        zzbbg<zzbbi> zzbbg;
        int[] zzdwr2;
        int length2;
        int n2;
        int zzct = 0;
        boolean zza3;
        int[] zzdwr3;
        int length3;
        int n3;
        long n4;
        boolean zza4;
        zzbbg<zzbbi> zzbbg2;
        int[] zzdwr4;
        int length4;
        int n5;
        long n6;
        int length5;
        int n7;
        Object zzcr;
        int zzabu;
        int zzabu2;
        long n8;
        Object zzw;
        long n9;
        long n10;
        Label_0926_Outer:Label_3818_Outer:Label_3202_Outer:
        while (true) {
            zza = o;
        Label_3202:
            while (true) {
                Label_4290: {
                    try {
                        zzaci = zzbdl.zzaci();
                        zza = o;
                        zzcw = this.zzcw(zzaci);
                        if (zzcw < 0) {
                            if (zzaci == Integer.MAX_VALUE) {
                                o3 = o;
                                if (this.zzdwr != null) {
                                    zzdwr = this.zzdwr;
                                    length = zzdwr.length;
                                    n = 0;
                                    while (true) {
                                        o3 = o;
                                        if (n >= length) {
                                            break;
                                        }
                                        o = this.zza(t, zzdwr[n], o, zzdwv);
                                        ++n;
                                    }
                                }
                                if (o3 != null) {
                                    zzdwv.zzf(t, (List<Integer>)o3);
                                }
                            }
                            else {
                                zza = o;
                                if (!this.zzdwm) {
                                    o4 = null;
                                }
                                else {
                                    zza = o;
                                    o4 = zzdww.zza(zzbbb, this.zzdwl, zzaci);
                                }
                                if (o4 != null) {
                                    if ((zzn = o2) == null) {
                                        zza = o;
                                        zzn = zzdww.zzn(t);
                                    }
                                    zza = o;
                                    o = zzdww.zza(zzbdl, o4, zzbbb, (zzbbg<?>)zzn, o, zzdwv);
                                    o2 = zzn;
                                    continue Label_3202_Outer;
                                }
                                zza = o;
                                zzdwv.zza(zzbdl);
                                if ((o4 = o) == null) {
                                    zza = o;
                                    o4 = zzdwv.zzad(t);
                                }
                                zza = o4;
                                zza2 = zzdwv.zza((zzbbg<zzbbi>)o4, zzbdl);
                                o = o4;
                                if (zza2) {
                                    continue Label_3202_Outer;
                                }
                                zzbbg = (zzbbg<zzbbi>)o4;
                                if (this.zzdwr != null) {
                                    zzdwr2 = this.zzdwr;
                                    length2 = zzdwr2.length;
                                    n2 = 0;
                                    while (true) {
                                        zzbbg = (zzbbg<zzbbi>)o4;
                                        if (n2 >= length2) {
                                            break;
                                        }
                                        o4 = this.zza(t, zzdwr2[n2], o4, zzdwv);
                                        ++n2;
                                    }
                                }
                                if (zzbbg != null) {
                                    zzdwv.zzf(t, zzbbg);
                                    return;
                                }
                            }
                            Label_0134: {
                                return;
                            }
                        }
                        zza = o;
                        zzct = this.zzct(zzcw);
                        Label_0684: {
                            while (true) {
                                switch ((0xFF00000 & zzct) >>> 20) {
                                    default: {
                                        o4 = o;
                                        if (o == null) {
                                            break Label_0684;
                                        }
                                        break Label_0684;
                                    }
                                    case 0: {
                                        Label_0767: {
                                            break Label_0767;
                                            zzn = o;
                                            zza = o;
                                            try {
                                                o4 = zzdwv.zzagb();
                                                zzn = o4;
                                                zza = o4;
                                                zza3 = zzdwv.zza((zzbbg<zzbbi>)o4, zzbdl);
                                                o = o4;
                                                if (zza3) {
                                                    continue Label_0926_Outer;
                                                }
                                                zzbdl = (zzbdl)o4;
                                                if (this.zzdwr == null) {
                                                    break Label_4290;
                                                }
                                                zzdwr3 = this.zzdwr;
                                                length3 = zzdwr3.length;
                                                n3 = 0;
                                                while (true) {
                                                    zzbdl = (zzbdl)o4;
                                                    if (n3 >= length3) {
                                                        break Label_4290;
                                                    }
                                                    o4 = this.zza(t, zzdwr3[n3], o4, zzdwv);
                                                    ++n3;
                                                }
                                                n4 = (0xFFFFF & zzct);
                                                zzn = o;
                                                zza = o;
                                                zzbek.zza(t, n4, zzbdl.readDouble());
                                                zzn = o;
                                                zza = o;
                                                this.zzb(t, zzcw);
                                            }
                                            catch (zzbbv o) {
                                                zza = zzn;
                                                zzdwv.zza(zzbdl);
                                                o4 = zzn;
                                                if (zzn == null) {
                                                    zza = zzn;
                                                    o4 = zzdwv.zzad(t);
                                                }
                                                zza = o4;
                                                zza4 = zzdwv.zza((zzbbg<zzbbi>)o4, zzbdl);
                                                o = o4;
                                                if (zza4) {
                                                    continue Label_0926_Outer;
                                                }
                                                zzbbg2 = (zzbbg<zzbbi>)o4;
                                                if (this.zzdwr == null) {
                                                    break Label_4290;
                                                }
                                                zzdwr4 = this.zzdwr;
                                                length4 = zzdwr4.length;
                                                n5 = 0;
                                                while (true) {
                                                    zzbbg2 = (zzbbg<zzbbi>)o4;
                                                    if (n5 >= length4) {
                                                        break Label_4290;
                                                    }
                                                    o4 = this.zza(t, zzdwr4[n5], o4, zzdwv);
                                                    ++n5;
                                                }
                                                n6 = (0xFFFFF & zzct);
                                                zzn = o;
                                                zza = o;
                                                zzbek.zza(t, n6, zzbdl.readFloat());
                                                zzn = o;
                                                zza = o;
                                                this.zzb(t, zzcw);
                                            }
                                        }
                                        continue Label_0926_Outer;
                                    }
                                    case 1: {
                                        continue Label_3202_Outer;
                                    }
                                    case 2: {
                                        break Label_4290;
                                    }
                                    case 3: {
                                        break Label_4290;
                                    }
                                    case 4: {
                                        break Label_4290;
                                    }
                                    case 5: {
                                        break Label_4290;
                                    }
                                    case 6: {
                                        break Label_4290;
                                    }
                                    case 7: {
                                        break Label_4290;
                                    }
                                    case 8: {
                                        break Label_4290;
                                    }
                                    case 9: {
                                        break Label_4290;
                                    }
                                    case 10: {
                                        break Label_4290;
                                    }
                                    case 11: {
                                        break Label_4290;
                                    }
                                    case 12: {
                                        break Label_4290;
                                    }
                                    case 13: {
                                        break Label_4290;
                                    }
                                    case 14: {
                                        break Label_4290;
                                    }
                                    case 15: {
                                        break Label_4290;
                                    }
                                    case 16: {
                                        break Label_4290;
                                    }
                                    case 17: {
                                        break Label_4290;
                                    }
                                    case 18: {
                                        break Label_4290;
                                    }
                                    case 19: {
                                        break Label_4290;
                                    }
                                    case 20: {
                                        break Label_4290;
                                    }
                                    case 21: {
                                        break Label_4290;
                                    }
                                    case 22: {
                                        break Label_4290;
                                    }
                                    case 23: {
                                        break Label_4290;
                                    }
                                    case 24: {
                                        break Label_4290;
                                    }
                                    case 25: {
                                        break Label_4290;
                                    }
                                    case 26: {
                                        break Label_4290;
                                    }
                                    case 27: {
                                        break Label_4290;
                                    }
                                    case 28: {
                                        break Label_4290;
                                    }
                                    case 29: {
                                        break Label_4290;
                                    }
                                    case 30: {
                                        break Label_4290;
                                    }
                                    case 31: {
                                        break Label_4290;
                                    }
                                    case 32: {
                                        break Label_4290;
                                    }
                                    case 33: {
                                        break Label_4290;
                                    }
                                    case 34: {
                                        break Label_4290;
                                    }
                                    case 35: {
                                        break Label_4290;
                                    }
                                    case 36: {
                                        break Label_4290;
                                    }
                                    case 37: {
                                        break Label_4290;
                                    }
                                    case 38: {
                                        break Label_4290;
                                    }
                                    case 39: {
                                        break Label_4290;
                                    }
                                    case 40: {
                                        break Label_4290;
                                    }
                                    case 41: {
                                        break Label_4290;
                                    }
                                    case 42: {
                                        break Label_4290;
                                    }
                                    case 43: {
                                        break Label_4290;
                                    }
                                    case 44: {
                                        break Label_4290;
                                    }
                                    case 45: {
                                        break Label_4290;
                                    }
                                    case 46: {
                                        break Label_4290;
                                    }
                                    case 47: {
                                        break Label_4290;
                                    }
                                    case 48: {
                                        break Label_4290;
                                    }
                                    case 49: {
                                        break Label_4290;
                                    }
                                    case 50: {
                                        break Label_4290;
                                    }
                                    case 51: {
                                        break Label_4290;
                                    }
                                    case 52: {
                                        break Label_4290;
                                    }
                                    case 53: {
                                        break Label_4290;
                                    }
                                    case 54: {
                                        break Label_4290;
                                    }
                                    case 55: {
                                        break Label_4290;
                                    }
                                    case 56: {
                                        break Label_4290;
                                    }
                                    case 57: {
                                        break Label_4290;
                                    }
                                    case 58: {
                                        break Label_4290;
                                    }
                                    case 59: {
                                        break Label_4290;
                                    }
                                    case 60: {
                                        break Label_4290;
                                    }
                                    case 61: {
                                        break Label_4290;
                                    }
                                    case 62: {
                                        break Label_4290;
                                    }
                                    case 63: {
                                        break Label_4290;
                                    }
                                    case 64: {
                                        break Label_4290;
                                    }
                                    case 65: {
                                        break Label_4290;
                                    }
                                    case 66: {
                                        break Label_4290;
                                    }
                                    case 67: {
                                        break Label_4290;
                                    }
                                    case 68: {
                                        break Label_4290;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    finally {
                        zzbdl = (zzbdl)zza;
                        if (this.zzdwr != null) {
                            o = this.zzdwr;
                            length5 = ((zzbbg<zzbbi>)o).length;
                            n7 = 0;
                            while (true) {
                                zzbdl = (zzbdl)zza;
                                if (n7 >= length5) {
                                    break;
                                }
                                zza = this.zza((Object)t, o[n7], (zzbdl)zza, (zzbee<Object, zzbdl>)zzdwv);
                                ++n7;
                            }
                        }
                        if (zzbdl != null) {
                            zzdwv.zzf(t, zzbdl);
                        }
                        zzn = o;
                        zzbdl.zzae((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                        continue Label_3818_Outer;
                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabn());
                        zzn = o;
                        this.zzb(t, zzaci, zzcw);
                        continue Label_3818_Outer;
                        this.zza(t, zzct, zzbdl);
                        zzn = o;
                        this.zzb(t, zzcw);
                        continue Label_3818_Outer;
                        zzn = o;
                        zzbdl.zzw((List<Boolean>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                        continue Label_3818_Outer;
                        // iftrue(Label_1896:, !this.zza(t, zzcw))
                        // iftrue(Label_1399:, !this.zza(t, zzcw))
                        // iftrue(Label_0134:, zzbdl == null)
                        // iftrue(Label_4378:, !this.zzdwx.zzu(o4))
                        // iftrue(Label_4078:, o4 != null && o4.zzq(zzabu) == null)
                        // iftrue(Label_1627:, o4 != null && o4.zzq(zzabu2) == null)
                        // iftrue(Label_3242:, o4 != null)
                        // iftrue(Label_0134:, zzbbg2 == null)
                        // iftrue(Label_3838:, !this.zza(t, zzaci, zzcw))
                    Block_41_Outer:
                        while (true) {
                            zzn = o;
                            this.zzb(t, zzaci, zzcw);
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzq((List<Float>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzbek.zza(t, zzct & 0xFFFFF, zzbdl.zzb((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                            zzn = o;
                            this.zzb(t, zzaci, zzcw);
                            continue Label_3818_Outer;
                            zzbek.zzb(t, 0xFFFFF & zzct, zzbdl.zzabv());
                            zzn = o;
                            this.zzb(t, zzcw);
                            continue Label_3818_Outer;
                            Block_37: {
                                Block_32: {
                                    Label_4035: {
                                        while (true) {
                                            Block_36: {
                                                Block_40: {
                                                    Block_34: {
                                                        break Block_34;
                                                        zzn = o;
                                                        zzbdl.zzv((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzae((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabq());
                                                        zzn = o;
                                                        this.zzb(t, zzaci, zzcw);
                                                        continue Label_3818_Outer;
                                                        zzdwv.zzf(t, (zzbdl)zzbbg2);
                                                        return;
                                                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabm());
                                                        zzn = o;
                                                        this.zzb(t, zzaci, zzcw);
                                                        continue Label_3818_Outer;
                                                        zzbek.zzb(t, 0xFFFFF & zzct, zzbdl.zzabx());
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                        this.zza(t, zzct, zzbdl);
                                                        zzn = o;
                                                        this.zzb(t, zzaci, zzcw);
                                                        continue Label_3818_Outer;
                                                        zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabs());
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                        Label_3838: {
                                                            zzbek.zza(t, zzct & 0xFFFFF, zzbdl.zza((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                                                        }
                                                        this.zzb(t, zzcw);
                                                        continue Block_41_Outer;
                                                        zzn = o;
                                                        zzbdl.zzad((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzt((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        break Block_32;
                                                        o4 = this.zzdwu.zza(t, zzct & 0xFFFFF);
                                                        zzbdl.zzaa((List<Integer>)o4);
                                                        zzn = o;
                                                        o = zzbdo.zza(zzaci, (List<Integer>)o4, this.zzcs(zzcw), o, zzdwv);
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zza(this.zzdwx.zzs(o4), this.zzdwx.zzx(zzcr), zzbbb);
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzac((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        Label_2240:
                                                        zzn = o;
                                                        zzbdl.readStringList((List<String>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzz((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzw((List<Boolean>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzn = o;
                                                        zzbdl.zzab((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzbek.zza(t, zzct & 0xFFFFF, zzbdl.zzabs());
                                                        zzn = o;
                                                        this.zzb(t, zzaci, zzcw);
                                                        continue Label_3818_Outer;
                                                        break Block_40;
                                                        Label_3242:
                                                        zzn = o;
                                                        break Block_37;
                                                        zzabu = zzbdl.zzabu();
                                                        o4 = this.zzcs(zzcw);
                                                        break Label_4035;
                                                        zzn = o;
                                                        zzbdl.zzr((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                                        continue Label_3818_Outer;
                                                        zzabu2 = zzbdl.zzabu();
                                                        o4 = this.zzcs(zzcw);
                                                        zzbek.zzb(t, 0xFFFFF & zzct, zzabu2);
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                        Label_1896:
                                                        zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzb((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabp());
                                                        zzn = o;
                                                        this.zzb(t, zzaci, zzcw);
                                                        continue Label_3818_Outer;
                                                        zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabo());
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                        Label_1399:
                                                        zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zza((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                                                        zzn = o;
                                                        this.zzb(t, zzcw);
                                                        continue Label_3818_Outer;
                                                    }
                                                    o4 = zzbbq.zza(zzbek.zzp(t, 0xFFFFF & zzct), zzbdl.zzb((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                                                    zzn = o;
                                                    zzbek.zza(t, 0xFFFFF & zzct, o4);
                                                    continue Label_3818_Outer;
                                                    zzcr = this.zzcr(zzcw);
                                                    n8 = (this.zzct(zzcw) & 0xFFFFF);
                                                    o4 = zzbek.zzp(t, n8);
                                                    break Block_36;
                                                }
                                                zzdwv.zzf(t, (zzbbg<zzbbi>)zzbdl);
                                                return;
                                            }
                                            o4 = this.zzdwx.zzw(zzcr);
                                            zzbek.zza(t, n8, o4);
                                            continue Label_3202;
                                            continue Label_3202_Outer;
                                        }
                                        zzn = o;
                                        zzbdl.zzr((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                        continue Label_3818_Outer;
                                    }
                                    zzbek.zza(t, zzct & 0xFFFFF, (Object)zzabu);
                                    zzn = o;
                                    this.zzb(t, zzaci, zzcw);
                                    continue Label_3818_Outer;
                                    zzn = o;
                                    zzbdl.zzad((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                    continue Label_3818_Outer;
                                    zzn = o;
                                    zzbdl.zzt((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                    continue Label_3818_Outer;
                                    zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzaby());
                                    zzn = o;
                                    this.zzb(t, zzaci, zzcw);
                                    continue Label_3818_Outer;
                                    zzbek.zzb(t, 0xFFFFF & zzct, zzbdl.zzabp());
                                    zzn = o;
                                    this.zzb(t, zzcw);
                                    continue Label_3818_Outer;
                                }
                                o4 = zzbbq.zza(zzbek.zzp(t, 0xFFFFF & zzct), zzbdl.zza((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                                zzn = o;
                                zzbek.zza(t, 0xFFFFF & zzct, o4);
                                continue Label_3818_Outer;
                                zzn = o;
                                zzbdl.zzp((List<Double>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                continue Label_3818_Outer;
                            }
                            zzw = this.zzdwx.zzw(zzcr);
                            this.zzdwx.zzb(zzw, o4);
                            zzbek.zza(t, n8, zzw);
                            o4 = zzw;
                            continue Label_3202;
                            zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabl());
                            zzn = o;
                            this.zzb(t, zzaci, zzcw);
                            continue Label_3818_Outer;
                            zzbek.zzb(t, 0xFFFFF & zzct, zzbdl.zzabn());
                            zzn = o;
                            this.zzb(t, zzcw);
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzs((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzab((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzu((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            o4 = this.zzcq(zzcw);
                            n9 = (0xFFFFF & zzct);
                            zzn = o;
                            zzbdl.zza(this.zzdwu.zza(t, n9), (zzbdm<Object>)o4, zzbbb);
                            continue Label_3818_Outer;
                            zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabq());
                            zzn = o;
                            this.zzb(t, zzcw);
                            continue Label_3818_Outer;
                            o4 = this.zzdwu.zza(t, zzct & 0xFFFFF);
                            zzbdl.zzaa((List<Integer>)o4);
                            zzn = o;
                            o = zzbdo.zza(zzaci, (List<Integer>)o4, this.zzcs(zzcw), o, zzdwv);
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzu((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzy((List<zzbah>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabm());
                            zzn = o;
                            this.zzb(t, zzcw);
                            continue Label_3818_Outer;
                            zzbek.zzb(t, 0xFFFFF & zzct, zzbdl.zzabt());
                            zzn = o;
                            this.zzb(t, zzcw);
                            continue Label_3818_Outer;
                            zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabx());
                            zzn = o;
                            this.zzb(t, zzaci, zzcw);
                            continue Label_3818_Outer;
                            Label_4078: {
                                zzn = o;
                            }
                            o = zzbdo.zza(zzaci, zzabu, o, zzdwv);
                            continue Label_3818_Outer;
                            Block_38: {
                                break Block_38;
                                zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabo());
                                zzn = o;
                                this.zzb(t, zzaci, zzcw);
                                continue Label_3818_Outer;
                                zzn = o;
                                zzbdl.zzs((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                continue Label_3818_Outer;
                                zzn = o;
                                zzbdl.zzz((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                                continue Label_3818_Outer;
                                zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabw());
                                zzn = o;
                                this.zzb(t, zzcw);
                                continue Label_3818_Outer;
                                zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.readDouble());
                                zzn = o;
                                this.zzb(t, zzaci, zzcw);
                                continue Label_3818_Outer;
                                zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzabl());
                                zzn = o;
                                this.zzb(t, zzcw);
                                continue Label_3818_Outer;
                                zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.readFloat());
                                zzn = o;
                                this.zzb(t, zzaci, zzcw);
                                continue Label_3818_Outer;
                            }
                            o4 = zzbbq.zza(zzbek.zzp(t, 0xFFFFF & zzct), zzbdl.zza((zzbdm<Object>)this.zzcq(zzcw), zzbbb));
                            zzbek.zza(t, zzct & 0xFFFFF, o4);
                            continue Block_41_Outer;
                        }
                        zzbek.zza(t, 0xFFFFF & zzct, zzbdl.zzaby());
                        zzn = o;
                        this.zzb(t, zzcw);
                        continue Label_3818_Outer;
                        zzn = o;
                        zzbdl.zzq((List<Float>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                        continue Label_3818_Outer;
                        // iftrue(Label_2240:, !zzcv(zzct))
                        Block_35: {
                            break Block_35;
                            zzn = o;
                            zzbdl.zzac((List<Long>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzp((List<Double>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            Label_1627: {
                                zzn = o;
                            }
                            o = zzbdo.zza(zzaci, zzabu2, o, zzdwv);
                            continue Label_3818_Outer;
                            zzn = o;
                            zzbdl.zzv((List<Integer>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                            continue Label_3818_Outer;
                            n10 = (0xFFFFF & zzct);
                            o4 = this.zzcq(zzcw);
                            zzn = o;
                            zzbdl.zzb(this.zzdwu.zza(t, n10), (zzbdm<Object>)o4, zzbbb);
                            continue Label_3818_Outer;
                            zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabv());
                            zzn = o;
                            this.zzb(t, zzaci, zzcw);
                            continue Label_3818_Outer;
                        }
                        zzn = o;
                        zzbdl.zzx((List<String>)this.zzdwu.zza(t, 0xFFFFF & zzct));
                        continue Label_3818_Outer;
                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabt());
                        zzn = o;
                        this.zzb(t, zzaci, zzcw);
                        continue Label_3818_Outer;
                        zzbek.zza(t, zzct & 0xFFFFF, (Object)zzbdl.zzabw());
                        zzn = o;
                        this.zzb(t, zzaci, zzcw);
                        continue Label_3818_Outer;
                    }
                }
                Label_4378: {
                    continue Label_3202;
                }
            }
        }
    }
    
    @Override
    public final void zza(final T t, final zzbey zzbey) throws IOException {
        if (zzbey.zzacn() == zzbbo.zze.zzdum) {
            zza(this.zzdwv, t, zzbey);
            final Iterator<Map.Entry> iterator = null;
            Map.Entry<?, ?> entry2;
            final Map.Entry<?, ?> entry = entry2 = null;
            Object descendingIterator = iterator;
            if (this.zzdwm) {
                final zzbbg<?> zzm = this.zzdww.zzm(t);
                entry2 = entry;
                descendingIterator = iterator;
                if (!zzm.isEmpty()) {
                    descendingIterator = zzm.descendingIterator();
                    entry2 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)descendingIterator).next();
                }
            }
            int n = this.zzdwg.length - 4;
            Map.Entry<?, ?> entry3;
            while (true) {
                entry3 = entry2;
                if (n < 0) {
                    break;
                }
                final int zzct = this.zzct(n);
                final int n2 = this.zzdwg[n];
                while (entry2 != null && this.zzdww.zza(entry2) > n2) {
                    this.zzdww.zza(zzbey, entry2);
                    if (((Iterator)descendingIterator).hasNext()) {
                        entry2 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)descendingIterator).next();
                    }
                    else {
                        entry2 = null;
                    }
                }
                switch ((0xFF00000 & zzct) >>> 20) {
                    case 0: {
                        if (this.zza(t, n)) {
                            zzbey.zza(n2, zzbek.zzo(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.zza(t, n)) {
                            zzbey.zza(n2, zzbek.zzn(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.zza(t, n)) {
                            zzbey.zzi(n2, zzbek.zzl(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.zza(t, n)) {
                            zzbey.zza(n2, zzbek.zzl(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.zza(t, n)) {
                            zzbey.zzm(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.zza(t, n)) {
                            zzbey.zzc(n2, zzbek.zzl(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.zza(t, n)) {
                            zzbey.zzp(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if (this.zza(t, n)) {
                            zzbey.zzf(n2, zzbek.zzm(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if (this.zza(t, n)) {
                            zza(n2, zzbek.zzp(t, 0xFFFFF & zzct), zzbey);
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if (this.zza(t, n)) {
                            zzbey.zza(n2, zzbek.zzp(t, 0xFFFFF & zzct), this.zzcq(n));
                            break;
                        }
                        break;
                    }
                    case 10: {
                        if (this.zza(t, n)) {
                            zzbey.zza(n2, (zzbah)zzbek.zzp(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 11: {
                        if (this.zza(t, n)) {
                            zzbey.zzn(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 12: {
                        if (this.zza(t, n)) {
                            zzbey.zzx(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 13: {
                        if (this.zza(t, n)) {
                            zzbey.zzw(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 14: {
                        if (this.zza(t, n)) {
                            zzbey.zzj(n2, zzbek.zzl(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 15: {
                        if (this.zza(t, n)) {
                            zzbey.zzo(n2, zzbek.zzk(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 16: {
                        if (this.zza(t, n)) {
                            zzbey.zzb(n2, zzbek.zzl(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 17: {
                        if (this.zza(t, n)) {
                            zzbey.zzb(n2, zzbek.zzp(t, 0xFFFFF & zzct), this.zzcq(n));
                            break;
                        }
                        break;
                    }
                    case 18: {
                        zzbdo.zza(this.zzdwg[n], (List<Double>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 19: {
                        zzbdo.zzb(this.zzdwg[n], (List<Float>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 20: {
                        zzbdo.zzc(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 21: {
                        zzbdo.zzd(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 22: {
                        zzbdo.zzh(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 23: {
                        zzbdo.zzf(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 24: {
                        zzbdo.zzk(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 25: {
                        zzbdo.zzn(this.zzdwg[n], (List<Boolean>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 26: {
                        zzbdo.zza(this.zzdwg[n], (List<String>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey);
                        break;
                    }
                    case 27: {
                        zzbdo.zza(this.zzdwg[n], (List<?>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, this.zzcq(n));
                        break;
                    }
                    case 28: {
                        zzbdo.zzb(this.zzdwg[n], (List<zzbah>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey);
                        break;
                    }
                    case 29: {
                        zzbdo.zzi(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 30: {
                        zzbdo.zzm(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 31: {
                        zzbdo.zzl(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 32: {
                        zzbdo.zzg(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 33: {
                        zzbdo.zzj(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 34: {
                        zzbdo.zze(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, false);
                        break;
                    }
                    case 35: {
                        zzbdo.zza(this.zzdwg[n], (List<Double>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 36: {
                        zzbdo.zzb(this.zzdwg[n], (List<Float>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 37: {
                        zzbdo.zzc(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 38: {
                        zzbdo.zzd(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 39: {
                        zzbdo.zzh(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 40: {
                        zzbdo.zzf(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 41: {
                        zzbdo.zzk(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 42: {
                        zzbdo.zzn(this.zzdwg[n], (List<Boolean>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 43: {
                        zzbdo.zzi(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 44: {
                        zzbdo.zzm(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 45: {
                        zzbdo.zzl(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 46: {
                        zzbdo.zzg(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 47: {
                        zzbdo.zzj(this.zzdwg[n], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 48: {
                        zzbdo.zze(this.zzdwg[n], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, true);
                        break;
                    }
                    case 49: {
                        zzbdo.zzb(this.zzdwg[n], (List<?>)zzbek.zzp(t, 0xFFFFF & zzct), zzbey, this.zzcq(n));
                        break;
                    }
                    case 50: {
                        this.zza(zzbey, n2, zzbek.zzp(t, 0xFFFFF & zzct), n);
                        break;
                    }
                    case 51: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zza(n2, zzf(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 52: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zza(n2, zzg(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 53: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzi(n2, zzi(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 54: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zza(n2, zzi(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 55: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzm(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 56: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzc(n2, zzi(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 57: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzp(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 58: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzf(n2, zzj(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 59: {
                        if (this.zza(t, n2, n)) {
                            zza(n2, zzbek.zzp(t, 0xFFFFF & zzct), zzbey);
                            break;
                        }
                        break;
                    }
                    case 60: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zza(n2, zzbek.zzp(t, 0xFFFFF & zzct), this.zzcq(n));
                            break;
                        }
                        break;
                    }
                    case 61: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zza(n2, (zzbah)zzbek.zzp(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 62: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzn(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 63: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzx(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 64: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzw(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 65: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzj(n2, zzi(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 66: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzo(n2, zzh(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 67: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzb(n2, zzi(t, 0xFFFFF & zzct));
                            break;
                        }
                        break;
                    }
                    case 68: {
                        if (this.zza(t, n2, n)) {
                            zzbey.zzb(n2, zzbek.zzp(t, 0xFFFFF & zzct), this.zzcq(n));
                            break;
                        }
                        break;
                    }
                }
                n -= 4;
            }
            while (entry3 != null) {
                this.zzdww.zza(zzbey, entry3);
                if (((Iterator)descendingIterator).hasNext()) {
                    entry3 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)descendingIterator).next();
                }
                else {
                    entry3 = null;
                }
            }
        }
        else {
            if (!this.zzdwo) {
                this.zzb(t, zzbey);
                return;
            }
            final Iterator<Map.Entry> iterator2 = null;
            Map.Entry<?, ?> entry5;
            final Map.Entry<?, ?> entry4 = entry5 = null;
            Object iterator3 = iterator2;
            if (this.zzdwm) {
                final zzbbg<?> zzm2 = this.zzdww.zzm(t);
                entry5 = entry4;
                iterator3 = iterator2;
                if (!zzm2.isEmpty()) {
                    iterator3 = zzm2.iterator();
                    entry5 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator3).next();
                }
            }
            final int length = this.zzdwg.length;
            int n3 = 0;
            Map.Entry<?, ?> entry6;
            while (true) {
                entry6 = entry5;
                if (n3 >= length) {
                    break;
                }
                final int zzct2 = this.zzct(n3);
                final int n4 = this.zzdwg[n3];
                while (entry5 != null && this.zzdww.zza(entry5) <= n4) {
                    this.zzdww.zza(zzbey, entry5);
                    if (((Iterator)iterator3).hasNext()) {
                        entry5 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator3).next();
                    }
                    else {
                        entry5 = null;
                    }
                }
                switch ((0xFF00000 & zzct2) >>> 20) {
                    case 0: {
                        if (this.zza(t, n3)) {
                            zzbey.zza(n4, zzbek.zzo(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.zza(t, n3)) {
                            zzbey.zza(n4, zzbek.zzn(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.zza(t, n3)) {
                            zzbey.zzi(n4, zzbek.zzl(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.zza(t, n3)) {
                            zzbey.zza(n4, zzbek.zzl(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.zza(t, n3)) {
                            zzbey.zzm(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.zza(t, n3)) {
                            zzbey.zzc(n4, zzbek.zzl(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.zza(t, n3)) {
                            zzbey.zzp(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if (this.zza(t, n3)) {
                            zzbey.zzf(n4, zzbek.zzm(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if (this.zza(t, n3)) {
                            zza(n4, zzbek.zzp(t, 0xFFFFF & zzct2), zzbey);
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if (this.zza(t, n3)) {
                            zzbey.zza(n4, zzbek.zzp(t, 0xFFFFF & zzct2), this.zzcq(n3));
                            break;
                        }
                        break;
                    }
                    case 10: {
                        if (this.zza(t, n3)) {
                            zzbey.zza(n4, (zzbah)zzbek.zzp(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 11: {
                        if (this.zza(t, n3)) {
                            zzbey.zzn(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 12: {
                        if (this.zza(t, n3)) {
                            zzbey.zzx(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 13: {
                        if (this.zza(t, n3)) {
                            zzbey.zzw(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 14: {
                        if (this.zza(t, n3)) {
                            zzbey.zzj(n4, zzbek.zzl(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 15: {
                        if (this.zza(t, n3)) {
                            zzbey.zzo(n4, zzbek.zzk(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 16: {
                        if (this.zza(t, n3)) {
                            zzbey.zzb(n4, zzbek.zzl(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 17: {
                        if (this.zza(t, n3)) {
                            zzbey.zzb(n4, zzbek.zzp(t, 0xFFFFF & zzct2), this.zzcq(n3));
                            break;
                        }
                        break;
                    }
                    case 18: {
                        zzbdo.zza(this.zzdwg[n3], (List<Double>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 19: {
                        zzbdo.zzb(this.zzdwg[n3], (List<Float>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 20: {
                        zzbdo.zzc(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 21: {
                        zzbdo.zzd(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 22: {
                        zzbdo.zzh(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 23: {
                        zzbdo.zzf(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 24: {
                        zzbdo.zzk(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 25: {
                        zzbdo.zzn(this.zzdwg[n3], (List<Boolean>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 26: {
                        zzbdo.zza(this.zzdwg[n3], (List<String>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey);
                        break;
                    }
                    case 27: {
                        zzbdo.zza(this.zzdwg[n3], (List<?>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, this.zzcq(n3));
                        break;
                    }
                    case 28: {
                        zzbdo.zzb(this.zzdwg[n3], (List<zzbah>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey);
                        break;
                    }
                    case 29: {
                        zzbdo.zzi(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 30: {
                        zzbdo.zzm(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 31: {
                        zzbdo.zzl(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 32: {
                        zzbdo.zzg(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 33: {
                        zzbdo.zzj(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 34: {
                        zzbdo.zze(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, false);
                        break;
                    }
                    case 35: {
                        zzbdo.zza(this.zzdwg[n3], (List<Double>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 36: {
                        zzbdo.zzb(this.zzdwg[n3], (List<Float>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 37: {
                        zzbdo.zzc(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 38: {
                        zzbdo.zzd(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 39: {
                        zzbdo.zzh(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 40: {
                        zzbdo.zzf(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 41: {
                        zzbdo.zzk(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 42: {
                        zzbdo.zzn(this.zzdwg[n3], (List<Boolean>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 43: {
                        zzbdo.zzi(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 44: {
                        zzbdo.zzm(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 45: {
                        zzbdo.zzl(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 46: {
                        zzbdo.zzg(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 47: {
                        zzbdo.zzj(this.zzdwg[n3], (List<Integer>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 48: {
                        zzbdo.zze(this.zzdwg[n3], (List<Long>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, true);
                        break;
                    }
                    case 49: {
                        zzbdo.zzb(this.zzdwg[n3], (List<?>)zzbek.zzp(t, 0xFFFFF & zzct2), zzbey, this.zzcq(n3));
                        break;
                    }
                    case 50: {
                        this.zza(zzbey, n4, zzbek.zzp(t, 0xFFFFF & zzct2), n3);
                        break;
                    }
                    case 51: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zza(n4, zzf(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 52: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zza(n4, zzg(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 53: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzi(n4, zzi(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 54: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zza(n4, zzi(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 55: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzm(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 56: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzc(n4, zzi(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 57: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzp(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 58: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzf(n4, zzj(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 59: {
                        if (this.zza(t, n4, n3)) {
                            zza(n4, zzbek.zzp(t, 0xFFFFF & zzct2), zzbey);
                            break;
                        }
                        break;
                    }
                    case 60: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zza(n4, zzbek.zzp(t, 0xFFFFF & zzct2), this.zzcq(n3));
                            break;
                        }
                        break;
                    }
                    case 61: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zza(n4, (zzbah)zzbek.zzp(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 62: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzn(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 63: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzx(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 64: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzw(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 65: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzj(n4, zzi(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 66: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzo(n4, zzh(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 67: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzb(n4, zzi(t, 0xFFFFF & zzct2));
                            break;
                        }
                        break;
                    }
                    case 68: {
                        if (this.zza(t, n4, n3)) {
                            zzbey.zzb(n4, zzbek.zzp(t, 0xFFFFF & zzct2), this.zzcq(n3));
                            break;
                        }
                        break;
                    }
                }
                n3 += 4;
            }
            while (entry6 != null) {
                this.zzdww.zza(zzbey, entry6);
                if (((Iterator)iterator3).hasNext()) {
                    entry6 = (Map.Entry<?, ?>)((Iterator<Map.Entry>)iterator3).next();
                }
                else {
                    entry6 = null;
                }
            }
            zza(this.zzdwv, t, zzbey);
        }
    }
    
    @Override
    public final void zza(final T t, final byte[] array, int i, final int n, final zzbae zzbae) throws IOException {
        if (this.zzdwo) {
            final Unsafe zzdwf = zzbcy.zzdwf;
        Label_0203_Outer:
            while (i < n) {
                final int n2 = i + 1;
                i = array[i];
                int zza = n2;
                int zzdpl;
                if ((zzdpl = i) < 0) {
                    zza = zzbad.zza(i, array, n2, zzbae);
                    zzdpl = zzbae.zzdpl;
                }
                i = zzdpl >>> 3;
                final int n3 = zzdpl & 0x7;
                final int zzcw = this.zzcw(i);
                while (true) {
                    Label_0954: {
                        if (zzcw < 0) {
                            break Label_0954;
                        }
                        final int n4 = this.zzdwg[zzcw + 1];
                        final int n5 = (0xFF00000 & n4) >>> 20;
                        final long n6 = 0xFFFFF & n4;
                        if (n5 <= 17) {
                            switch (n5) {
                                default: {
                                    i = zza;
                                    break;
                                }
                                case 0: {
                                    if (n3 == 1) {
                                        zzbek.zza(t, n6, zzbad.zzg(array, zza));
                                        i = zza + 8;
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 1: {
                                    if (n3 == 5) {
                                        zzbek.zza(t, n6, zzbad.zzh(array, zza));
                                        i = zza + 4;
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 2:
                                case 3: {
                                    if (n3 == 0) {
                                        i = zzbad.zzb(array, zza, zzbae);
                                        zzdwf.putLong(t, n6, zzbae.zzdpm);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 4:
                                case 11: {
                                    if (n3 == 0) {
                                        i = zzbad.zza(array, zza, zzbae);
                                        zzdwf.putInt(t, n6, zzbae.zzdpl);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 5:
                                case 14: {
                                    if (n3 == 1) {
                                        zzdwf.putLong(t, n6, zzbad.zzf(array, zza));
                                        i = zza + 8;
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 6:
                                case 13: {
                                    if (n3 == 5) {
                                        zzdwf.putInt(t, n6, zzbad.zze(array, zza));
                                        i = zza + 4;
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 7: {
                                    if (n3 == 0) {
                                        i = zzbad.zzb(array, zza, zzbae);
                                        zzbek.zza(t, n6, zzbae.zzdpm != 0L);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 8: {
                                    if (n3 == 2) {
                                        if ((0x20000000 & n4) == 0x0) {
                                            i = zzbad.zzc(array, zza, zzbae);
                                        }
                                        else {
                                            i = zzbad.zzd(array, zza, zzbae);
                                        }
                                        zzdwf.putObject(t, n6, zzbae.zzdpn);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 9: {
                                    if (n3 != 2) {
                                        break Label_0954;
                                    }
                                    i = zza(this.zzcq(zzcw), array, zza, n, zzbae);
                                    final Object object = zzdwf.getObject(t, n6);
                                    if (object == null) {
                                        zzdwf.putObject(t, n6, zzbae.zzdpn);
                                        continue Label_0203_Outer;
                                    }
                                    zzdwf.putObject(t, n6, zzbbq.zza(object, zzbae.zzdpn));
                                    continue Label_0203_Outer;
                                }
                                case 10: {
                                    if (n3 == 2) {
                                        i = zzbad.zze(array, zza, zzbae);
                                        zzdwf.putObject(t, n6, zzbae.zzdpn);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 12: {
                                    if (n3 == 0) {
                                        i = zzbad.zza(array, zza, zzbae);
                                        zzdwf.putInt(t, n6, zzbae.zzdpl);
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 15: {
                                    if (n3 == 0) {
                                        i = zzbad.zza(array, zza, zzbae);
                                        zzdwf.putInt(t, n6, zzbaq.zzbu(zzbae.zzdpl));
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                                case 16: {
                                    if (n3 == 0) {
                                        i = zzbad.zzb(array, zza, zzbae);
                                        zzdwf.putLong(t, n6, zzbaq.zzl(zzbae.zzdpm));
                                        continue Label_0203_Outer;
                                    }
                                    break Label_0954;
                                }
                            }
                        }
                        else if (n5 == 27) {
                            if (n3 == 2) {
                                zzbbt<?> zzbm = (zzbbt<?>)zzdwf.getObject(t, n6);
                                if (!zzbm.zzaay()) {
                                    i = zzbm.size();
                                    if (i == 0) {
                                        i = 10;
                                    }
                                    else {
                                        i <<= 1;
                                    }
                                    zzbm = zzbm.zzbm(i);
                                    zzdwf.putObject(t, n6, zzbm);
                                }
                                i = zza(this.zzcq(zzcw), zzdpl, array, zza, n, zzbm, zzbae);
                                continue Label_0203_Outer;
                            }
                            break Label_0954;
                        }
                        else if (n5 <= 49) {
                            final int zza2 = this.zza(t, array, zza, n, zzdpl, i, n3, zzcw, n4, n5, n6, zzbae);
                            if ((i = zza2) != zza) {
                                continue Label_0203_Outer;
                            }
                            i = zza2;
                        }
                        else if (n5 == 50) {
                            if (n3 != 2) {
                                break Label_0954;
                            }
                            final int zza3 = this.zza(t, array, zza, n, zzcw, i, n6, zzbae);
                            if ((i = zza3) != zza) {
                                continue Label_0203_Outer;
                            }
                            i = zza3;
                        }
                        else {
                            final int zza4 = this.zza(t, array, zza, n, zzdpl, i, n3, n4, n5, n6, zzcw, zzbae);
                            if ((i = zza4) != zza) {
                                continue Label_0203_Outer;
                            }
                            i = zza4;
                        }
                        i = zza(zzdpl, array, i, n, t, zzbae);
                        continue Label_0203_Outer;
                    }
                    i = zza;
                    continue;
                }
            }
            if (i != n) {
                throw zzbbu.zzadr();
            }
        }
        else {
            this.zza(t, array, i, n, 0, zzbae);
        }
    }
    
    @Override
    public final boolean zzaa(final T t) {
        if (this.zzdwq == null || this.zzdwq.length == 0) {
            return true;
        }
        int n = -1;
        int int1 = 0;
        final int[] zzdwq = this.zzdwq;
        final int length = zzdwq.length;
        int i = 0;
    Label_0123_Outer:
        while (i < length) {
            final int n2 = zzdwq[i];
            final int zzcw = this.zzcw(n2);
            final int zzct = this.zzct(zzcw);
            int n3 = 0;
            while (true) {
                Label_0576: {
                    if (this.zzdwo) {
                        break Label_0576;
                    }
                    final int n4 = this.zzdwg[zzcw + 2];
                    final int n5 = 0xFFFFF & n4;
                    final int n6 = n3 = 1 << (n4 >>> 20);
                    if (n5 == n) {
                        break Label_0576;
                    }
                    int1 = zzbcy.zzdwf.getInt(t, (long)n5);
                    final int n7 = n5;
                    boolean b;
                    if ((0x10000000 & zzct) != 0x0) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (b && !this.zza(t, zzcw, int1, n6)) {
                        return false;
                    }
                    switch ((0xFF00000 & zzct) >>> 20) {
                        case 9:
                        case 17: {
                            if (this.zza(t, zzcw, int1, n6) && !zza(t, zzct, this.zzcq(zzcw))) {
                                return false;
                            }
                            break;
                        }
                        case 27:
                        case 49: {
                            final List list = (List)zzbek.zzp(t, 0xFFFFF & zzct);
                        Label_0348:
                            while (true) {
                                if (!list.isEmpty()) {
                                    final zzbdm zzcq = this.zzcq(zzcw);
                                    for (int j = 0; j < list.size(); ++j) {
                                        if (!zzcq.zzaa(list.get(j))) {
                                            final int n8 = 0;
                                            break Label_0348;
                                        }
                                    }
                                }
                                Label_0364: {
                                    break Label_0364;
                                    final int n8;
                                    if (n8 == 0) {
                                        return false;
                                    }
                                    break;
                                }
                                final int n8 = 1;
                                continue Label_0348;
                            }
                        }
                        case 60:
                        case 68: {
                            if (this.zza(t, n2, zzcw) && !zza(t, zzct, this.zzcq(zzcw))) {
                                return false;
                            }
                            break;
                        }
                        case 50: {
                            final Map<?, ?> zzt = this.zzdwx.zzt(zzbek.zzp(t, 0xFFFFF & zzct));
                        Label_0538:
                            while (true) {
                                if (!zzt.isEmpty() && this.zzdwx.zzx(this.zzcr(zzcw)).zzdwa.zzagl() == zzbex.zzebd) {
                                    zzbdm<?> zzbdm = null;
                                    for (final Object next : zzt.values()) {
                                        zzbdm<?> zze;
                                        if ((zze = zzbdm) == null) {
                                            zze = zzbdg.zzaeo().zze(next.getClass());
                                        }
                                        zzbdm = zze;
                                        if (!zze.zzaa(next)) {
                                            final int n9 = 0;
                                            break Label_0538;
                                        }
                                    }
                                }
                                Label_0545: {
                                    break Label_0545;
                                    final int n9;
                                    if (n9 == 0) {
                                        return false;
                                    }
                                    break;
                                }
                                final int n9 = 1;
                                continue Label_0538;
                            }
                        }
                    }
                    ++i;
                    n = n7;
                    continue Label_0123_Outer;
                }
                final int n6 = n3;
                final int n7 = n;
                continue;
            }
        }
        return !this.zzdwm || this.zzdww.zzm(t).isInitialized();
    }
    
    @Override
    public final void zzc(final T t, final T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzdwg.length; i += 4) {
            final int zzct = this.zzct(i);
            final long n = 0xFFFFF & zzct;
            final int n2 = this.zzdwg[i];
            switch ((zzct & 0xFF00000) >>> 20) {
                case 0: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzo(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 1: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzn(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzl(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 3: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzl(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 5: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzl(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 6: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 7: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzm(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 8: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzp(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 9: {
                    this.zza(t, t2, i);
                    break;
                }
                case 10: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzp(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 11: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 12: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 13: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 14: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzl(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 15: {
                    if (this.zza(t2, i)) {
                        zzbek.zzb(t, n, zzbek.zzk(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 16: {
                    if (this.zza(t2, i)) {
                        zzbek.zza(t, n, zzbek.zzl(t2, n));
                        this.zzb(t, i);
                        break;
                    }
                    break;
                }
                case 17: {
                    this.zza(t, t2, i);
                    break;
                }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49: {
                    this.zzdwu.zza(t, t2, n);
                    break;
                }
                case 50: {
                    zzbdo.zza(this.zzdwx, t, t2, n);
                    break;
                }
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59: {
                    if (this.zza(t2, n2, i)) {
                        zzbek.zza(t, n, zzbek.zzp(t2, n));
                        this.zzb(t, n2, i);
                        break;
                    }
                    break;
                }
                case 60: {
                    this.zzb(t, t2, i);
                    break;
                }
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67: {
                    if (this.zza(t2, n2, i)) {
                        zzbek.zza(t, n, zzbek.zzp(t2, n));
                        this.zzb(t, n2, i);
                        break;
                    }
                    break;
                }
                case 68: {
                    this.zzb(t, t2, i);
                    break;
                }
            }
        }
        if (!this.zzdwo) {
            zzbdo.zza(this.zzdwv, t, t2);
            if (this.zzdwm) {
                zzbdo.zza(this.zzdww, t, t2);
            }
        }
    }
    
    @Override
    public final void zzo(final T t) {
        final int n = 0;
        if (this.zzdwr != null) {
            final int[] zzdwr = this.zzdwr;
            for (int length = zzdwr.length, i = 0; i < length; ++i) {
                final long n2 = this.zzct(zzdwr[i]) & 0xFFFFF;
                final Object zzp = zzbek.zzp(t, n2);
                if (zzp != null) {
                    zzbek.zza(t, n2, this.zzdwx.zzv(zzp));
                }
            }
        }
        if (this.zzdws != null) {
            final int[] zzdws = this.zzdws;
            for (int length2 = zzdws.length, j = n; j < length2; ++j) {
                this.zzdwu.zzb(t, zzdws[j]);
            }
        }
        this.zzdwv.zzo(t);
        if (this.zzdwm) {
            this.zzdww.zzo(t);
        }
    }
    
    @Override
    public final int zzy(final T t) {
        int n7;
        if (this.zzdwo) {
            final Unsafe zzdwf = zzbcy.zzdwf;
            int n = 0;
            int n6 = 0;
            for (int i = 0; i < this.zzdwg.length; i += 4, n = n6) {
                final int zzct = this.zzct(i);
                final int n2 = (0xFF00000 & zzct) >>> 20;
                final int n3 = this.zzdwg[i];
                final long n4 = zzct & 0xFFFFF;
                int n5;
                if (n2 >= zzbbj.zzdsl.id() && n2 <= zzbbj.zzdsy.id()) {
                    n5 = (this.zzdwg[i + 2] & 0xFFFFF);
                }
                else {
                    n5 = 0;
                }
                switch (n2) {
                    default: {
                        n6 = n;
                        break;
                    }
                    case 0: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzb(n3, 0.0);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzb(n3, 0.0f);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzd(n3, zzbek.zzl(t, n4));
                            break;
                        }
                        break;
                    }
                    case 3: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zze(n3, zzbek.zzl(t, n4));
                            break;
                        }
                        break;
                    }
                    case 4: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzq(n3, zzbek.zzk(t, n4));
                            break;
                        }
                        break;
                    }
                    case 5: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzg(n3, 0L);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzt(n3, 0);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzg(n3, true);
                            break;
                        }
                        break;
                    }
                    case 8: {
                        n6 = n;
                        if (!this.zza(t, i)) {
                            break;
                        }
                        final Object zzp = zzbek.zzp(t, n4);
                        if (zzp instanceof zzbah) {
                            n6 = n + zzbav.zzc(n3, (zzbah)zzp);
                            break;
                        }
                        n6 = n + zzbav.zzg(n3, (String)zzp);
                        break;
                    }
                    case 9: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbdo.zzc(n3, zzbek.zzp(t, n4), this.zzcq(i));
                            break;
                        }
                        break;
                    }
                    case 10: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzc(n3, (zzbah)zzbek.zzp(t, n4));
                            break;
                        }
                        break;
                    }
                    case 11: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzr(n3, zzbek.zzk(t, n4));
                            break;
                        }
                        break;
                    }
                    case 12: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzv(n3, zzbek.zzk(t, n4));
                            break;
                        }
                        break;
                    }
                    case 13: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzu(n3, 0);
                            break;
                        }
                        break;
                    }
                    case 14: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzh(n3, 0L);
                            break;
                        }
                        break;
                    }
                    case 15: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzs(n3, zzbek.zzk(t, n4));
                            break;
                        }
                        break;
                    }
                    case 16: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzf(n3, zzbek.zzl(t, n4));
                            break;
                        }
                        break;
                    }
                    case 17: {
                        n6 = n;
                        if (this.zza(t, i)) {
                            n6 = n + zzbav.zzc(n3, (zzbcu)zzbek.zzp(t, n4), this.zzcq(i));
                            break;
                        }
                        break;
                    }
                    case 18: {
                        n6 = n + zzbdo.zzw(n3, zze(t, n4), false);
                        break;
                    }
                    case 19: {
                        n6 = n + zzbdo.zzv(n3, zze(t, n4), false);
                        break;
                    }
                    case 20: {
                        n6 = n + zzbdo.zzo(n3, zze(t, n4), false);
                        break;
                    }
                    case 21: {
                        n6 = n + zzbdo.zzp(n3, zze(t, n4), false);
                        break;
                    }
                    case 22: {
                        n6 = n + zzbdo.zzs(n3, zze(t, n4), false);
                        break;
                    }
                    case 23: {
                        n6 = n + zzbdo.zzw(n3, zze(t, n4), false);
                        break;
                    }
                    case 24: {
                        n6 = n + zzbdo.zzv(n3, zze(t, n4), false);
                        break;
                    }
                    case 25: {
                        n6 = n + zzbdo.zzx(n3, zze(t, n4), false);
                        break;
                    }
                    case 26: {
                        n6 = n + zzbdo.zzc(n3, zze(t, n4));
                        break;
                    }
                    case 27: {
                        n6 = n + zzbdo.zzc(n3, zze(t, n4), this.zzcq(i));
                        break;
                    }
                    case 28: {
                        n6 = n + zzbdo.zzd(n3, zze(t, n4));
                        break;
                    }
                    case 29: {
                        n6 = n + zzbdo.zzt(n3, zze(t, n4), false);
                        break;
                    }
                    case 30: {
                        n6 = n + zzbdo.zzr(n3, zze(t, n4), false);
                        break;
                    }
                    case 31: {
                        n6 = n + zzbdo.zzv(n3, zze(t, n4), false);
                        break;
                    }
                    case 32: {
                        n6 = n + zzbdo.zzw(n3, zze(t, n4), false);
                        break;
                    }
                    case 33: {
                        n6 = n + zzbdo.zzu(n3, zze(t, n4), false);
                        break;
                    }
                    case 34: {
                        n6 = n + zzbdo.zzq(n3, zze(t, n4), false);
                        break;
                    }
                    case 35: {
                        final int zzan = zzbdo.zzan((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzan > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzan);
                            }
                            n6 = n + (zzan + (zzbav.zzcd(n3) + zzbav.zzcf(zzan)));
                            break;
                        }
                        break;
                    }
                    case 36: {
                        final int zzam = zzbdo.zzam((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzam > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzam);
                            }
                            n6 = n + (zzam + (zzbav.zzcd(n3) + zzbav.zzcf(zzam)));
                            break;
                        }
                        break;
                    }
                    case 37: {
                        final int zzaf = zzbdo.zzaf((List<Long>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzaf > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzaf);
                            }
                            n6 = n + (zzaf + (zzbav.zzcd(n3) + zzbav.zzcf(zzaf)));
                            break;
                        }
                        break;
                    }
                    case 38: {
                        final int zzag = zzbdo.zzag((List<Long>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzag > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzag);
                            }
                            n6 = n + (zzag + (zzbav.zzcd(n3) + zzbav.zzcf(zzag)));
                            break;
                        }
                        break;
                    }
                    case 39: {
                        final int zzaj = zzbdo.zzaj((List<Integer>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzaj > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzaj);
                            }
                            n6 = n + (zzaj + (zzbav.zzcd(n3) + zzbav.zzcf(zzaj)));
                            break;
                        }
                        break;
                    }
                    case 40: {
                        final int zzan2 = zzbdo.zzan((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzan2 > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzan2);
                            }
                            n6 = n + (zzan2 + (zzbav.zzcd(n3) + zzbav.zzcf(zzan2)));
                            break;
                        }
                        break;
                    }
                    case 41: {
                        final int zzam2 = zzbdo.zzam((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzam2 > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzam2);
                            }
                            n6 = n + (zzam2 + (zzbav.zzcd(n3) + zzbav.zzcf(zzam2)));
                            break;
                        }
                        break;
                    }
                    case 42: {
                        final int zzao = zzbdo.zzao((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzao > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzao);
                            }
                            n6 = n + (zzao + (zzbav.zzcd(n3) + zzbav.zzcf(zzao)));
                            break;
                        }
                        break;
                    }
                    case 43: {
                        final int zzak = zzbdo.zzak((List<Integer>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzak > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzak);
                            }
                            n6 = n + (zzak + (zzbav.zzcd(n3) + zzbav.zzcf(zzak)));
                            break;
                        }
                        break;
                    }
                    case 44: {
                        final int zzai = zzbdo.zzai((List<Integer>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzai > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzai);
                            }
                            n6 = n + (zzai + (zzbav.zzcd(n3) + zzbav.zzcf(zzai)));
                            break;
                        }
                        break;
                    }
                    case 45: {
                        final int zzam3 = zzbdo.zzam((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzam3 > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzam3);
                            }
                            n6 = n + (zzam3 + (zzbav.zzcd(n3) + zzbav.zzcf(zzam3)));
                            break;
                        }
                        break;
                    }
                    case 46: {
                        final int zzan3 = zzbdo.zzan((List<?>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzan3 > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzan3);
                            }
                            n6 = n + (zzan3 + (zzbav.zzcd(n3) + zzbav.zzcf(zzan3)));
                            break;
                        }
                        break;
                    }
                    case 47: {
                        final int zzal = zzbdo.zzal((List<Integer>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzal > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzal);
                            }
                            n6 = n + (zzal + (zzbav.zzcd(n3) + zzbav.zzcf(zzal)));
                            break;
                        }
                        break;
                    }
                    case 48: {
                        final int zzah = zzbdo.zzah((List<Long>)zzdwf.getObject(t, n4));
                        n6 = n;
                        if (zzah > 0) {
                            if (this.zzdwp) {
                                zzdwf.putInt(t, (long)n5, zzah);
                            }
                            n6 = n + (zzah + (zzbav.zzcd(n3) + zzbav.zzcf(zzah)));
                            break;
                        }
                        break;
                    }
                    case 49: {
                        n6 = n + zzbdo.zzd(n3, zze(t, n4), this.zzcq(i));
                        break;
                    }
                    case 50: {
                        n6 = n + this.zzdwx.zzb(n3, zzbek.zzp(t, n4), this.zzcr(i));
                        break;
                    }
                    case 51: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzb(n3, 0.0);
                            break;
                        }
                        break;
                    }
                    case 52: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzb(n3, 0.0f);
                            break;
                        }
                        break;
                    }
                    case 53: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzd(n3, zzi(t, n4));
                            break;
                        }
                        break;
                    }
                    case 54: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zze(n3, zzi(t, n4));
                            break;
                        }
                        break;
                    }
                    case 55: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzq(n3, zzh(t, n4));
                            break;
                        }
                        break;
                    }
                    case 56: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzg(n3, 0L);
                            break;
                        }
                        break;
                    }
                    case 57: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzt(n3, 0);
                            break;
                        }
                        break;
                    }
                    case 58: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzg(n3, true);
                            break;
                        }
                        break;
                    }
                    case 59: {
                        n6 = n;
                        if (!this.zza(t, n3, i)) {
                            break;
                        }
                        final Object zzp2 = zzbek.zzp(t, n4);
                        if (zzp2 instanceof zzbah) {
                            n6 = n + zzbav.zzc(n3, (zzbah)zzp2);
                            break;
                        }
                        n6 = n + zzbav.zzg(n3, (String)zzp2);
                        break;
                    }
                    case 60: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbdo.zzc(n3, zzbek.zzp(t, n4), this.zzcq(i));
                            break;
                        }
                        break;
                    }
                    case 61: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzc(n3, (zzbah)zzbek.zzp(t, n4));
                            break;
                        }
                        break;
                    }
                    case 62: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzr(n3, zzh(t, n4));
                            break;
                        }
                        break;
                    }
                    case 63: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzv(n3, zzh(t, n4));
                            break;
                        }
                        break;
                    }
                    case 64: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzu(n3, 0);
                            break;
                        }
                        break;
                    }
                    case 65: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzh(n3, 0L);
                            break;
                        }
                        break;
                    }
                    case 66: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzs(n3, zzh(t, n4));
                            break;
                        }
                        break;
                    }
                    case 67: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzf(n3, zzi(t, n4));
                            break;
                        }
                        break;
                    }
                    case 68: {
                        n6 = n;
                        if (this.zza(t, n3, i)) {
                            n6 = n + zzbav.zzc(n3, (zzbcu)zzbek.zzp(t, n4), this.zzcq(i));
                            break;
                        }
                        break;
                    }
                }
            }
            n7 = zza(this.zzdwv, t) + n;
        }
        else {
            final Unsafe zzdwf2 = zzbcy.zzdwf;
            int n8 = -1;
            int n9 = 0;
            int j = 0;
            int int1 = 0;
            while (j < this.zzdwg.length) {
                final int zzct2 = this.zzct(j);
                final int n10 = this.zzdwg[j];
                final int n11 = (0xFF00000 & zzct2) >>> 20;
                int n12 = 0;
                int n15;
                if (n11 <= 17) {
                    n12 = this.zzdwg[j + 2];
                    final int n13 = 0xFFFFF & n12;
                    int n14;
                    if (n13 != (n14 = n8)) {
                        int1 = zzdwf2.getInt(t, (long)n13);
                        n14 = n13;
                    }
                    n15 = 1 << (n12 >>> 20);
                    n8 = n14;
                }
                else if (this.zzdwp && n11 >= zzbbj.zzdsl.id() && n11 <= zzbbj.zzdsy.id()) {
                    n12 = (this.zzdwg[j + 2] & 0xFFFFF);
                    n15 = 0;
                }
                else {
                    n15 = 0;
                }
                final long n16 = 0xFFFFF & zzct2;
                int n17 = 0;
                switch (n11) {
                    default: {
                        n17 = n9;
                        break;
                    }
                    case 0: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzb(n10, 0.0);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzb(n10, 0.0f);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzd(n10, zzdwf2.getLong(t, n16));
                            break;
                        }
                        break;
                    }
                    case 3: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zze(n10, zzdwf2.getLong(t, n16));
                            break;
                        }
                        break;
                    }
                    case 4: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzq(n10, zzdwf2.getInt(t, n16));
                            break;
                        }
                        break;
                    }
                    case 5: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzg(n10, 0L);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzt(n10, 0);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzg(n10, true);
                            break;
                        }
                        break;
                    }
                    case 8: {
                        n17 = n9;
                        if ((n15 & int1) == 0x0) {
                            break;
                        }
                        final Object object = zzdwf2.getObject(t, n16);
                        if (object instanceof zzbah) {
                            n17 = n9 + zzbav.zzc(n10, (zzbah)object);
                            break;
                        }
                        n17 = n9 + zzbav.zzg(n10, (String)object);
                        break;
                    }
                    case 9: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbdo.zzc(n10, zzdwf2.getObject(t, n16), this.zzcq(j));
                            break;
                        }
                        break;
                    }
                    case 10: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzc(n10, (zzbah)zzdwf2.getObject(t, n16));
                            break;
                        }
                        break;
                    }
                    case 11: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzr(n10, zzdwf2.getInt(t, n16));
                            break;
                        }
                        break;
                    }
                    case 12: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzv(n10, zzdwf2.getInt(t, n16));
                            break;
                        }
                        break;
                    }
                    case 13: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzu(n10, 0);
                            break;
                        }
                        break;
                    }
                    case 14: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzh(n10, 0L);
                            break;
                        }
                        break;
                    }
                    case 15: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzs(n10, zzdwf2.getInt(t, n16));
                            break;
                        }
                        break;
                    }
                    case 16: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzf(n10, zzdwf2.getLong(t, n16));
                            break;
                        }
                        break;
                    }
                    case 17: {
                        n17 = n9;
                        if ((n15 & int1) != 0x0) {
                            n17 = n9 + zzbav.zzc(n10, (zzbcu)zzdwf2.getObject(t, n16), this.zzcq(j));
                            break;
                        }
                        break;
                    }
                    case 18: {
                        n17 = n9 + zzbdo.zzw(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 19: {
                        n17 = n9 + zzbdo.zzv(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 20: {
                        n17 = n9 + zzbdo.zzo(n10, (List<Long>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 21: {
                        n17 = n9 + zzbdo.zzp(n10, (List<Long>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 22: {
                        n17 = n9 + zzbdo.zzs(n10, (List<Integer>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 23: {
                        n17 = n9 + zzbdo.zzw(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 24: {
                        n17 = n9 + zzbdo.zzv(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 25: {
                        n17 = n9 + zzbdo.zzx(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 26: {
                        n17 = n9 + zzbdo.zzc(n10, (List<?>)zzdwf2.getObject(t, n16));
                        break;
                    }
                    case 27: {
                        n17 = n9 + zzbdo.zzc(n10, (List<?>)zzdwf2.getObject(t, n16), this.zzcq(j));
                        break;
                    }
                    case 28: {
                        n17 = n9 + zzbdo.zzd(n10, (List<zzbah>)zzdwf2.getObject(t, n16));
                        break;
                    }
                    case 29: {
                        n17 = n9 + zzbdo.zzt(n10, (List<Integer>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 30: {
                        n17 = n9 + zzbdo.zzr(n10, (List<Integer>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 31: {
                        n17 = n9 + zzbdo.zzv(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 32: {
                        n17 = n9 + zzbdo.zzw(n10, (List<?>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 33: {
                        n17 = n9 + zzbdo.zzu(n10, (List<Integer>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 34: {
                        n17 = n9 + zzbdo.zzq(n10, (List<Long>)zzdwf2.getObject(t, n16), false);
                        break;
                    }
                    case 35: {
                        final int zzan4 = zzbdo.zzan((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzan4 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzan4);
                            }
                            n17 = n9 + (zzan4 + (zzbav.zzcd(n10) + zzbav.zzcf(zzan4)));
                            break;
                        }
                        break;
                    }
                    case 36: {
                        final int zzam4 = zzbdo.zzam((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzam4 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzam4);
                            }
                            n17 = n9 + (zzam4 + (zzbav.zzcd(n10) + zzbav.zzcf(zzam4)));
                            break;
                        }
                        break;
                    }
                    case 37: {
                        final int zzaf2 = zzbdo.zzaf((List<Long>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzaf2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzaf2);
                            }
                            n17 = n9 + (zzaf2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzaf2)));
                            break;
                        }
                        break;
                    }
                    case 38: {
                        final int zzag2 = zzbdo.zzag((List<Long>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzag2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzag2);
                            }
                            n17 = n9 + (zzag2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzag2)));
                            break;
                        }
                        break;
                    }
                    case 39: {
                        final int zzaj2 = zzbdo.zzaj((List<Integer>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzaj2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzaj2);
                            }
                            n17 = n9 + (zzaj2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzaj2)));
                            break;
                        }
                        break;
                    }
                    case 40: {
                        final int zzan5 = zzbdo.zzan((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzan5 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzan5);
                            }
                            n17 = n9 + (zzan5 + (zzbav.zzcd(n10) + zzbav.zzcf(zzan5)));
                            break;
                        }
                        break;
                    }
                    case 41: {
                        final int zzam5 = zzbdo.zzam((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzam5 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzam5);
                            }
                            n17 = n9 + (zzam5 + (zzbav.zzcd(n10) + zzbav.zzcf(zzam5)));
                            break;
                        }
                        break;
                    }
                    case 42: {
                        final int zzao2 = zzbdo.zzao((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzao2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzao2);
                            }
                            n17 = n9 + (zzao2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzao2)));
                            break;
                        }
                        break;
                    }
                    case 43: {
                        final int zzak2 = zzbdo.zzak((List<Integer>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzak2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzak2);
                            }
                            n17 = n9 + (zzak2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzak2)));
                            break;
                        }
                        break;
                    }
                    case 44: {
                        final int zzai2 = zzbdo.zzai((List<Integer>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzai2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzai2);
                            }
                            n17 = n9 + (zzai2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzai2)));
                            break;
                        }
                        break;
                    }
                    case 45: {
                        final int zzam6 = zzbdo.zzam((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzam6 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzam6);
                            }
                            n17 = n9 + (zzam6 + (zzbav.zzcd(n10) + zzbav.zzcf(zzam6)));
                            break;
                        }
                        break;
                    }
                    case 46: {
                        final int zzan6 = zzbdo.zzan((List<?>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzan6 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzan6);
                            }
                            n17 = n9 + (zzan6 + (zzbav.zzcd(n10) + zzbav.zzcf(zzan6)));
                            break;
                        }
                        break;
                    }
                    case 47: {
                        final int zzal2 = zzbdo.zzal((List<Integer>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzal2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzal2);
                            }
                            n17 = n9 + (zzal2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzal2)));
                            break;
                        }
                        break;
                    }
                    case 48: {
                        final int zzah2 = zzbdo.zzah((List<Long>)zzdwf2.getObject(t, n16));
                        n17 = n9;
                        if (zzah2 > 0) {
                            if (this.zzdwp) {
                                zzdwf2.putInt(t, (long)n12, zzah2);
                            }
                            n17 = n9 + (zzah2 + (zzbav.zzcd(n10) + zzbav.zzcf(zzah2)));
                            break;
                        }
                        break;
                    }
                    case 49: {
                        n17 = n9 + zzbdo.zzd(n10, (List<zzbcu>)zzdwf2.getObject(t, n16), this.zzcq(j));
                        break;
                    }
                    case 50: {
                        n17 = n9 + this.zzdwx.zzb(n10, zzdwf2.getObject(t, n16), this.zzcr(j));
                        break;
                    }
                    case 51: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzb(n10, 0.0);
                            break;
                        }
                        break;
                    }
                    case 52: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzb(n10, 0.0f);
                            break;
                        }
                        break;
                    }
                    case 53: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzd(n10, zzi(t, n16));
                            break;
                        }
                        break;
                    }
                    case 54: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zze(n10, zzi(t, n16));
                            break;
                        }
                        break;
                    }
                    case 55: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzq(n10, zzh(t, n16));
                            break;
                        }
                        break;
                    }
                    case 56: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzg(n10, 0L);
                            break;
                        }
                        break;
                    }
                    case 57: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzt(n10, 0);
                            break;
                        }
                        break;
                    }
                    case 58: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzg(n10, true);
                            break;
                        }
                        break;
                    }
                    case 59: {
                        n17 = n9;
                        if (!this.zza(t, n10, j)) {
                            break;
                        }
                        final Object object2 = zzdwf2.getObject(t, n16);
                        if (object2 instanceof zzbah) {
                            n17 = n9 + zzbav.zzc(n10, (zzbah)object2);
                            break;
                        }
                        n17 = n9 + zzbav.zzg(n10, (String)object2);
                        break;
                    }
                    case 60: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbdo.zzc(n10, zzdwf2.getObject(t, n16), this.zzcq(j));
                            break;
                        }
                        break;
                    }
                    case 61: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzc(n10, (zzbah)zzdwf2.getObject(t, n16));
                            break;
                        }
                        break;
                    }
                    case 62: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzr(n10, zzh(t, n16));
                            break;
                        }
                        break;
                    }
                    case 63: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzv(n10, zzh(t, n16));
                            break;
                        }
                        break;
                    }
                    case 64: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzu(n10, 0);
                            break;
                        }
                        break;
                    }
                    case 65: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzh(n10, 0L);
                            break;
                        }
                        break;
                    }
                    case 66: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzs(n10, zzh(t, n16));
                            break;
                        }
                        break;
                    }
                    case 67: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzf(n10, zzi(t, n16));
                            break;
                        }
                        break;
                    }
                    case 68: {
                        n17 = n9;
                        if (this.zza(t, n10, j)) {
                            n17 = n9 + zzbav.zzc(n10, (zzbcu)zzdwf2.getObject(t, n16), this.zzcq(j));
                            break;
                        }
                        break;
                    }
                }
                j += 4;
                n9 = n17;
            }
            n7 = zza(this.zzdwv, t) + n9;
            if (this.zzdwm) {
                return n7 + this.zzdww.zzm(t).zzacw();
            }
        }
        return n7;
    }
}
