// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E>
{
    protected static final long P_INDEX_OFFSET;
    protected long producerIndex;
    protected long producerLookAhead;
    
    static {
        P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    }
    
    public SpscArrayQueueProducerFields(final int n) {
        super(n);
    }
}
