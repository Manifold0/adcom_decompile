package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> zalk;
    protected int zall = -1;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.zalk = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    public boolean hasNext() {
        return this.zall < this.zalk.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.zalk;
            int i = this.zall + 1;
            this.zall = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zall);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
