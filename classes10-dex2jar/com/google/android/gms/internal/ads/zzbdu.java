// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;
import java.util.Iterator;

final class zzbdu implements Iterator<Object>
{
    @Override
    public final boolean hasNext() {
        return false;
    }
    
    @Override
    public final Object next() {
        throw new NoSuchElementException();
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
