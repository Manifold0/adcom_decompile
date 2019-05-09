// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public final class zzbcc extends zzbab<String> implements zzbcd, RandomAccess
{
    private static final zzbcc zzdvn;
    private static final zzbcd zzdvo;
    private final List<Object> zzdvp;
    
    static {
        (zzdvn = new zzbcc()).zzaaz();
        zzdvo = zzbcc.zzdvn;
    }
    
    public zzbcc() {
        this(10);
    }
    
    public zzbcc(final int n) {
        this(new ArrayList<Object>(n));
    }
    
    private zzbcc(final ArrayList<Object> zzdvp) {
        this.zzdvp = zzdvp;
    }
    
    private static String zzq(final Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof zzbah) {
            return ((zzbah)o).zzabd();
        }
        return zzbbq.zzt((byte[])o);
    }
    
    @Override
    public final boolean addAll(final int n, final Collection<? extends String> collection) {
        this.zzaba();
        Object zzadw = collection;
        if (collection instanceof zzbcd) {
            zzadw = ((zzbcd)collection).zzadw();
        }
        final boolean addAll = this.zzdvp.addAll(n, (Collection<?>)zzadw);
        ++this.modCount;
        return addAll;
    }
    
    @Override
    public final boolean addAll(final Collection<? extends String> collection) {
        return this.addAll(this.size(), collection);
    }
    
    @Override
    public final void clear() {
        this.zzaba();
        this.zzdvp.clear();
        ++this.modCount;
    }
    
    @Override
    public final int size() {
        return this.zzdvp.size();
    }
    
    @Override
    public final List<?> zzadw() {
        return Collections.unmodifiableList((List<?>)this.zzdvp);
    }
    
    @Override
    public final zzbcd zzadx() {
        zzbcd zzbcd = this;
        if (this.zzaay()) {
            zzbcd = new zzbeh(this);
        }
        return zzbcd;
    }
    
    @Override
    public final void zzap(final zzbah zzbah) {
        this.zzaba();
        this.zzdvp.add(zzbah);
        ++this.modCount;
    }
    
    @Override
    public final Object zzcp(final int n) {
        return this.zzdvp.get(n);
    }
}
