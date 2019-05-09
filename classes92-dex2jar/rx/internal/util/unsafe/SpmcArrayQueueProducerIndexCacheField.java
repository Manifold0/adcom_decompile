// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E>
{
    private volatile long producerIndexCache;
    
    public SpmcArrayQueueProducerIndexCacheField(final int n) {
        super(n);
    }
    
    protected final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }
    
    protected final void svProducerIndexCache(final long producerIndexCache) {
        this.producerIndexCache = producerIndexCache;
    }
}
