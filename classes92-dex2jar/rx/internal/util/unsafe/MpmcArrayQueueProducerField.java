// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class MpmcArrayQueueProducerField<E> extends MpmcArrayQueueL1Pad<E>
{
    private static final long P_INDEX_OFFSET;
    private volatile long producerIndex;
    
    static {
        P_INDEX_OFFSET = UnsafeAccess.addressOf(MpmcArrayQueueProducerField.class, "producerIndex");
    }
    
    public MpmcArrayQueueProducerField(final int n) {
        super(n);
    }
    
    protected final boolean casProducerIndex(final long n, final long n2) {
        return UnsafeAccess.UNSAFE.compareAndSwapLong(this, MpmcArrayQueueProducerField.P_INDEX_OFFSET, n, n2);
    }
    
    protected final long lvProducerIndex() {
        return this.producerIndex;
    }
}
