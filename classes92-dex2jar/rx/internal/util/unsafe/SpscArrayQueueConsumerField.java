// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class SpscArrayQueueConsumerField<E> extends SpscArrayQueueL2Pad<E>
{
    protected static final long C_INDEX_OFFSET;
    protected long consumerIndex;
    
    static {
        C_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueConsumerField.class, "consumerIndex");
    }
    
    public SpscArrayQueueConsumerField(final int n) {
        super(n);
    }
}
