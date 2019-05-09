// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.AbstractList;

public final class zzbeh extends AbstractList<String> implements zzbcd, RandomAccess
{
    private final zzbcd zzdyz;
    
    public zzbeh(final zzbcd zzdyz) {
        this.zzdyz = zzdyz;
    }
    
    @Override
    public final Iterator<String> iterator() {
        return new zzbej(this);
    }
    
    @Override
    public final ListIterator<String> listIterator(final int n) {
        return new zzbei(this, n);
    }
    
    @Override
    public final int size() {
        return this.zzdyz.size();
    }
    
    @Override
    public final List<?> zzadw() {
        return this.zzdyz.zzadw();
    }
    
    @Override
    public final zzbcd zzadx() {
        return this;
    }
    
    @Override
    public final void zzap(final zzbah zzbah) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final Object zzcp(final int n) {
        return this.zzdyz.zzcp(n);
    }
}
