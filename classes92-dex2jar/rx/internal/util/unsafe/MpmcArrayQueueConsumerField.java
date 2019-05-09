// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class MpmcArrayQueueConsumerField<E> extends MpmcArrayQueueL2Pad<E>
{
    private static final long C_INDEX_OFFSET;
    private volatile long consumerIndex;
    
    static {
        C_INDEX_OFFSET = UnsafeAccess.addressOf(MpmcArrayQueueConsumerField.class, "consumerIndex");
    }
    
    public MpmcArrayQueueConsumerField(final int n) {
        super(n);
    }
    
    protected final boolean casConsumerIndex(final long n, final long n2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, MpmcArrayQueueConsumerField.C_INDEX_OFFSET, n, n2);
    }
    
    protected final long lvConsumerIndex() {
        return this.consumerIndex;
    }
}
