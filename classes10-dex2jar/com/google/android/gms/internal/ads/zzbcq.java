// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

final class zzbcq implements zzbcp
{
    @Override
    public final int zzb(final int n, final Object o, final Object o2) {
        final zzbco zzbco = (zzbco)o;
        if (!zzbco.isEmpty()) {
            final Iterator iterator = zzbco.entrySet().iterator();
            if (iterator.hasNext()) {
                final Map.Entry entry = iterator.next();
                entry.getKey();
                entry.getValue();
                throw new NoSuchMethodError();
            }
        }
        return 0;
    }
    
    @Override
    public final Object zzb(final Object o, final Object o2) {
        final zzbco zzbco = (zzbco)o;
        final zzbco zzbco2 = (zzbco)o2;
        zzbco zzaec = zzbco;
        if (!zzbco2.isEmpty()) {
            zzaec = zzbco;
            if (!zzbco.isMutable()) {
                zzaec = zzbco.zzaec();
            }
            zzaec.zza(zzbco2);
        }
        return zzaec;
    }
    
    @Override
    public final Map<?, ?> zzs(final Object o) {
        return (Map<?, ?>)o;
    }
    
    @Override
    public final Map<?, ?> zzt(final Object o) {
        return (Map<?, ?>)o;
    }
    
    @Override
    public final boolean zzu(final Object o) {
        return !((zzbco)o).isMutable();
    }
    
    @Override
    public final Object zzv(final Object o) {
        ((zzbco)o).zzaaz();
        return o;
    }
    
    @Override
    public final Object zzw(final Object o) {
        return zzbco.zzaeb().zzaec();
    }
    
    @Override
    public final zzbcn<?, ?> zzx(final Object o) {
        throw new NoSuchMethodError();
    }
}
