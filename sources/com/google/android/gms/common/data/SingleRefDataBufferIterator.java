package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.NoSuchElementException;

@KeepForSdk
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T zamg;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (hasNext()) {
            this.zall++;
            if (this.zall == 0) {
                this.zamg = this.zalk.get(0);
                if (!(this.zamg instanceof DataBufferRef)) {
                    String valueOf = String.valueOf(this.zamg.getClass());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
                }
            }
            ((DataBufferRef) this.zamg).zag(this.zall);
            return this.zamg;
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zall);
    }
}
