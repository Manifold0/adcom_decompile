package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzbbe extends zzbbd<Object> {
    zzbbe() {
    }

    final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    final Object zza(zzbbb zzbbb, zzbcu zzbcu, int i) {
        return zzbbb.zza(zzbcu, i);
    }

    final <UT, UB> UB zza(zzbdl zzbdl, Object obj, zzbbb zzbbb, zzbbg<Object> zzbbg, UB ub, zzbee<UT, UB> zzbee) throws IOException {
        throw new NoSuchMethodError();
    }

    final void zza(zzbah zzbah, Object obj, zzbbb zzbbb, zzbbg<Object> zzbbg) throws IOException {
        throw new NoSuchMethodError();
    }

    final void zza(zzbdl zzbdl, Object obj, zzbbb zzbbb, zzbbg<Object> zzbbg) throws IOException {
        throw new NoSuchMethodError();
    }

    final void zza(zzbey zzbey, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    final void zza(Object obj, zzbbg<Object> zzbbg) {
        ((zzc) obj).zzdtz = zzbbg;
    }

    final boolean zzh(zzbcu zzbcu) {
        return zzbcu instanceof zzc;
    }

    final zzbbg<Object> zzm(Object obj) {
        return ((zzc) obj).zzdtz;
    }

    final zzbbg<Object> zzn(Object obj) {
        zzbbg<Object> zzm = zzm(obj);
        if (!zzm.isImmutable()) {
            return zzm;
        }
        zzbbg zzbbg = (zzbbg) zzm.clone();
        zza(obj, zzbbg);
        return zzbbg;
    }

    final void zzo(Object obj) {
        zzm(obj).zzaaz();
    }
}
