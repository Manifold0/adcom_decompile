// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

final class zzbdh<E> extends zzbab<E>
{
    private static final zzbdh<Object> zzdxd;
    private final List<E> zzdvp;
    
    static {
        (zzdxd = new zzbdh<Object>()).zzaaz();
    }
    
    zzbdh() {
        this((List)new ArrayList(10));
    }
    
    private zzbdh(final List<E> zzdvp) {
        this.zzdvp = zzdvp;
    }
    
    public static <E> zzbdh<E> zzaep() {
        return (zzbdh<E>)zzbdh.zzdxd;
    }
    
    @Override
    public final void add(final int n, final E e) {
        this.zzaba();
        this.zzdvp.add(n, e);
        ++this.modCount;
    }
    
    @Override
    public final E get(final int n) {
        return this.zzdvp.get(n);
    }
    
    @Override
    public final E remove(final int n) {
        this.zzaba();
        final E remove = this.zzdvp.remove(n);
        ++this.modCount;
        return remove;
    }
    
    @Override
    public final E set(final int n, final E e) {
        this.zzaba();
        final E set = this.zzdvp.set(n, e);
        ++this.modCount;
        return set;
    }
    
    @Override
    public final int size() {
        return this.zzdvp.size();
    }
}
