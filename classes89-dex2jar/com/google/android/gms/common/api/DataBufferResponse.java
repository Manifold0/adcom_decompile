// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import java.util.Iterator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.DataBuffer;

@KeepForSdk
public class DataBufferResponse<T, R extends AbstractDataBuffer> extends Response<R> implements DataBuffer<T>
{
    @KeepForSdk
    public DataBufferResponse() {
    }
    
    @KeepForSdk
    public DataBufferResponse(@NonNull final R r) {
        super((Result)r);
    }
    
    public void close() {
        ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).close();
    }
    
    public T get(final int n) {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).get(n);
    }
    
    public int getCount() {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).getCount();
    }
    
    public Bundle getMetadata() {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).getMetadata();
    }
    
    public boolean isClosed() {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).isClosed();
    }
    
    public Iterator<T> iterator() {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).iterator();
    }
    
    public void release() {
        ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).release();
    }
    
    public Iterator<T> singleRefIterator() {
        return ((com.google.android.gms.common.data.AbstractDataBuffer)this.getResult()).singleRefIterator();
    }
}
