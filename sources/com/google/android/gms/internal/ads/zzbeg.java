package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzbeg extends zzbee<zzbef, zzbef> {
    zzbeg() {
    }

    private static void zza(Object obj, zzbef zzbef) {
        ((zzbbo) obj).zzdtt = zzbef;
    }

    final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzbef) obj).zzb(i << 3, Long.valueOf(j));
    }

    final /* synthetic */ void zza(Object obj, int i, zzbah zzbah) {
        ((zzbef) obj).zzb((i << 3) | 2, zzbah);
    }

    final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzbef) obj).zzb((i << 3) | 3, (zzbef) obj2);
    }

    final /* synthetic */ void zza(Object obj, zzbey zzbey) throws IOException {
        ((zzbef) obj).zzb(zzbey);
    }

    final boolean zza(zzbdl zzbdl) {
        return false;
    }

    final /* synthetic */ Object zzac(Object obj) {
        return ((zzbbo) obj).zzdtt;
    }

    final /* synthetic */ Object zzad(Object obj) {
        zzbef zzbef = ((zzbbo) obj).zzdtt;
        if (zzbef != zzbef.zzagc()) {
            return zzbef;
        }
        zzbef = zzbef.zzagd();
        zza(obj, zzbef);
        return zzbef;
    }

    final /* synthetic */ int zzae(Object obj) {
        return ((zzbef) obj).zzage();
    }

    final /* synthetic */ Object zzagb() {
        return zzbef.zzagd();
    }

    final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzbef) obj).zzb((i << 3) | 1, Long.valueOf(j));
    }

    final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzbef) obj).zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    final /* synthetic */ void zzc(Object obj, zzbey zzbey) throws IOException {
        ((zzbef) obj).zza(zzbey);
    }

    final /* synthetic */ void zze(Object obj, Object obj2) {
        zza(obj, (zzbef) obj2);
    }

    final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzbef) obj2);
    }

    final /* synthetic */ Object zzg(Object obj, Object obj2) {
        zzbef zzbef = (zzbef) obj;
        zzbef zzbef2 = (zzbef) obj2;
        return zzbef2.equals(zzbef.zzagc()) ? zzbef : zzbef.zza(zzbef, zzbef2);
    }

    final void zzo(Object obj) {
        ((zzbbo) obj).zzdtt.zzaaz();
    }

    final /* synthetic */ Object zzv(Object obj) {
        zzbef zzbef = (zzbef) obj;
        zzbef.zzaaz();
        return zzbef;
    }

    final /* synthetic */ int zzy(Object obj) {
        return ((zzbef) obj).zzacw();
    }
}
