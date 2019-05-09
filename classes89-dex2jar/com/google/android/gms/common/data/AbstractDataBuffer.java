// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T>
{
    protected final DataHolder mDataHolder;
    
    @KeepForSdk
    protected AbstractDataBuffer(final DataHolder mDataHolder) {
        this.mDataHolder = mDataHolder;
    }
    
    @Deprecated
    @Override
    public final void close() {
        this.release();
    }
    
    @Override
    public abstract T get(final int p0);
    
    @Override
    public int getCount() {
        if (this.mDataHolder == null) {
            return 0;
        }
        return this.mDataHolder.getCount();
    }
    
    @Override
    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }
    
    @Deprecated
    @Override
    public boolean isClosed() {
        return this.mDataHolder == null || this.mDataHolder.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new DataBufferIterator<T>(this);
    }
    
    @Override
    public void release() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
    
    @Override
    public Iterator<T> singleRefIterator() {
        return new SingleRefDataBufferIterator<T>(this);
    }
}
