// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;

final class zzbch extends zzbce
{
    private zzbch() {
        super(null);
    }
    
    private static <E> zzbbt<E> zzd(final Object o, final long n) {
        return (zzbbt<E>)zzbek.zzp(o, n);
    }
    
    @Override
    final <L> List<L> zza(final Object o, final long n) {
        final zzbbt<L> zzd = (zzbbt<L>)zzd(o, n);
        if (!zzd.zzaay()) {
            final int size = zzd.size();
            int n2;
            if (size == 0) {
                n2 = 10;
            }
            else {
                n2 = size << 1;
            }
            final zzbbt<L> zzbm = zzd.zzbm(n2);
            zzbek.zza(o, n, zzbm);
            return zzbm;
        }
        return zzd;
    }
    
    @Override
    final <E> void zza(final Object o, final Object o2, final long n) {
        final zzbbt<Object> zzd = zzd(o, n);
        final zzbbt<Object> zzd2 = (zzbbt<Object>)zzd(o2, n);
        final int size = zzd.size();
        final int size2 = zzd2.size();
        zzbbt<Object> zzbm = zzd;
        if (size > 0) {
            zzbm = zzd;
            if (size2 > 0) {
                zzbm = zzd;
                if (!zzd.zzaay()) {
                    zzbm = zzd.zzbm(size2 + size);
                }
                zzbm.addAll(zzd2);
            }
        }
        if (size <= 0) {
            zzbm = zzd2;
        }
        zzbek.zza(o, n, zzbm);
    }
    
    @Override
    final void zzb(final Object o, final long n) {
        zzd(o, n).zzaaz();
    }
}
