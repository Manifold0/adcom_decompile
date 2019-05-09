// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.RandomAccess;
import java.util.List;
import java.util.Collection;
import java.util.AbstractList;

abstract class zzbab<E> extends AbstractList<E> implements zzbbt<E>
{
    private boolean zzdpi;
    
    zzbab() {
        this.zzdpi = true;
    }
    
    @Override
    public void add(final int n, final E e) {
        this.zzaba();
        super.add(n, e);
    }
    
    @Override
    public boolean add(final E e) {
        this.zzaba();
        return super.add(e);
    }
    
    @Override
    public boolean addAll(final int n, final Collection<? extends E> collection) {
        this.zzaba();
        return super.addAll(n, collection);
    }
    
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        this.zzaba();
        return super.addAll(collection);
    }
    
    @Override
    public void clear() {
        this.zzaba();
        super.clear();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof List)) {
                return false;
            }
            if (!(o instanceof RandomAccess)) {
                return super.equals(o);
            }
            final List list = (List)o;
            final int size = this.size();
            if (size != list.size()) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                if (!this.get(i).equals(list.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int size = this.size();
        int n = 1;
        for (int i = 0; i < size; ++i) {
            n = n * 31 + this.get(i).hashCode();
        }
        return n;
    }
    
    @Override
    public E remove(final int n) {
        this.zzaba();
        return super.remove(n);
    }
    
    @Override
    public boolean remove(final Object o) {
        this.zzaba();
        return super.remove(o);
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        this.zzaba();
        return super.removeAll(collection);
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        this.zzaba();
        return super.retainAll(collection);
    }
    
    @Override
    public E set(final int n, final E e) {
        this.zzaba();
        return super.set(n, e);
    }
    
    @Override
    public boolean zzaay() {
        return this.zzdpi;
    }
    
    @Override
    public final void zzaaz() {
        this.zzdpi = false;
    }
    
    protected final void zzaba() {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
    }
}
