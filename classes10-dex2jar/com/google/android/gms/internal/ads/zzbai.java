// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;
import java.util.Iterator;

final class zzbai implements Iterator
{
    private final int limit;
    private int position;
    private final /* synthetic */ zzbah zzdps;
    
    zzbai(final zzbah zzdps) {
        this.zzdps = zzdps;
        this.position = 0;
        this.limit = this.zzdps.size();
    }
    
    private final byte nextByte() {
        try {
            return this.zzdps.zzbn(this.position++);
        }
        catch (IndexOutOfBoundsException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }
    
    @Override
    public final boolean hasNext() {
        return this.position < this.limit;
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
