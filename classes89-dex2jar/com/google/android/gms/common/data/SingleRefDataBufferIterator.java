// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T>
{
    private T zamg;
    
    public SingleRefDataBufferIterator(final DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zall).toString());
        }
        ++this.zall;
        if (this.zall == 0) {
            this.zamg = this.zalk.get(0);
            if (!(this.zamg instanceof DataBufferRef)) {
                final String value = String.valueOf(this.zamg.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(value).length() + 44).append("DataBuffer reference of type ").append(value).append(" is not movable").toString());
            }
        }
        else {
            ((DataBufferRef)this.zamg).zag(this.zall);
        }
        return this.zamg;
    }
}
