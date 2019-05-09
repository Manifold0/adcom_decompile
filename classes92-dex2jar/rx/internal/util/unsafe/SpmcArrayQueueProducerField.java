// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class SpmcArrayQueueProducerField<E> extends SpmcArrayQueueL1Pad<E>
{
    protected static final long P_INDEX_OFFSET;
    private volatile long producerIndex;
    
    static {
        P_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueProducerField.class, "producerIndex");
    }
    
    public SpmcArrayQueueProducerField(final int n) {
        super(n);
    }
    
    protected final long lvProducerIndex() {
        return this.producerIndex;
    }
    
    protected final void soTail(final long n) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, SpmcArrayQueueProducerField.P_INDEX_OFFSET, n);
    }
}
