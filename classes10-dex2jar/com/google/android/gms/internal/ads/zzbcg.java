// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

final class zzbcg extends zzbce
{
    private static final Class<?> zzdvs;
    
    static {
        zzdvs = Collections.unmodifiableList(Collections.emptyList()).getClass();
    }
    
    private zzbcg() {
        super(null);
    }
    
    private static <L> List<L> zza(final Object o, final long n, final int n2) {
        final List<L> zzc = (List<L>)zzc(o, n);
        Object o2;
        if (zzc.isEmpty()) {
            if (zzc instanceof zzbcd) {
                o2 = new zzbcc(n2);
            }
            else {
                o2 = new ArrayList<L>(n2);
            }
            zzbek.zza(o, n, o2);
        }
        else {
            if (zzbcg.zzdvs.isAssignableFrom(zzc.getClass())) {
                final ArrayList list = new ArrayList<Object>(zzc.size() + n2);
                list.addAll(zzc);
                zzbek.zza(o, n, list);
                return (List<L>)list;
            }
            o2 = zzc;
            if (zzc instanceof zzbeh) {
                final zzbcc zzbcc = new zzbcc(zzc.size() + n2);
                ((zzbab<Object>)zzbcc).addAll(zzc);
                zzbek.zza(o, n, zzbcc);
                return (List<L>)zzbcc;
            }
        }
        return (List<L>)o2;
    }
    
    private static <E> List<E> zzc(final Object o, final long n) {
        return (List<E>)zzbek.zzp(o, n);
    }
    
    @Override
    final <L> List<L> zza(final Object o, final long n) {
        return zza(o, n, 10);
    }
    
    @Override
    final <E> void zza(final Object o, final Object o2, final long n) {
        List<?> zzc = zzc(o2, n);
        final List<Object> zza = zza(o, n, zzc.size());
        final int size = zza.size();
        final int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza;
        }
        zzbek.zza(o, n, zzc);
    }
    
    @Override
    final void zzb(final Object o, final long n) {
        final List list = (List)zzbek.zzp(o, n);
        List<Object> list2;
        if (list instanceof zzbcd) {
            list2 = (List<Object>)((zzbcd)list).zzadx();
        }
        else {
            if (zzbcg.zzdvs.isAssignableFrom(list.getClass())) {
                return;
            }
            list2 = Collections.unmodifiableList((List<?>)list);
        }
        zzbek.zza(o, n, list2);
    }
}
