// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;

@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T>
{
    protected final DataBuffer<T> zalk;
    protected int zall;
    
    public DataBufferIterator(final DataBuffer<T> dataBuffer) {
        this.zalk = (DataBuffer<T>)Preconditions.checkNotNull((Object)dataBuffer);
        this.zall = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.zall < this.zalk.getCount() - 1;
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zall).toString());
        }
        final DataBuffer<T> zalk = this.zalk;
        final int zall = this.zall + 1;
        this.zall = zall;
        return zalk.get(zall);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
