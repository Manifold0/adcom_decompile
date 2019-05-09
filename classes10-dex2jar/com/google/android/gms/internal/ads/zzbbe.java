// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Map;

final class zzbbe extends zzbbd<Object>
{
    @Override
    final int zza(final Map.Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }
    
    @Override
    final Object zza(final zzbbb zzbbb, final zzbcu zzbcu, final int n) {
        return zzbbb.zza(zzbcu, n);
    }
    
    @Override
    final <UT, UB> UB zza(final zzbdl zzbdl, final Object o, final zzbbb zzbbb, final zzbbg<Object> zzbbg, final UB ub, final zzbee<UT, UB> zzbee) throws IOException {
        throw new NoSuchMethodError();
    }
    
    @Override
    final void zza(final zzbah zzbah, final Object o, final zzbbb zzbbb, final zzbbg<Object> zzbbg) throws IOException {
        throw new NoSuchMethodError();
    }
    
    @Override
    final void zza(final zzbdl zzbdl, final Object o, final zzbbb zzbbb, final zzbbg<Object> zzbbg) throws IOException {
        throw new NoSuchMethodError();
    }
    
    @Override
    final void zza(final zzbey zzbey, final Map.Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }
    
    @Override
    final void zza(final Object o, final zzbbg<Object> zzdtz) {
        ((zzbbo.zzc)o).zzdtz = zzdtz;
    }
    
    @Override
    final boolean zzh(final zzbcu zzbcu) {
        return zzbcu instanceof zzbbo.zzc;
    }
    
    @Override
    final zzbbg<Object> zzm(final Object o) {
        return ((zzbbo.zzc)o).zzdtz;
    }
    
    @Override
    final zzbbg<Object> zzn(final Object o) {
        zzbbg<Object> zzm;
        final zzbbg<Object> zzbbg = zzm = this.zzm(o);
        if (zzbbg.isImmutable()) {
            zzm = (zzbbg<Object>)zzbbg.clone();
            this.zza(o, zzm);
        }
        return zzm;
    }
    
    @Override
    final void zzo(final Object o) {
        this.zzm(o).zzaaz();
    }
}
