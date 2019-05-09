// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class SpmcArrayQueueConsumerField<E> extends SpmcArrayQueueL2Pad<E>
{
    protected static final long C_INDEX_OFFSET;
    private volatile long consumerIndex;
    
    static {
        C_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueConsumerField.class, "consumerIndex");
    }
    
    public SpmcArrayQueueConsumerField(final int n) {
        super(n);
    }
    
    protected final boolean casHead(final long n, final long n2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, SpmcArrayQueueConsumerField.C_INDEX_OFFSET, n, n2);
    }
    
    protected final long lvConsumerIndex() {
        return this.consumerIndex;
    }
}
