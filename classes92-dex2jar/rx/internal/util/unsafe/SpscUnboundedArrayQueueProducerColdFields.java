// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

abstract class SpscUnboundedArrayQueueProducerColdFields<E> extends SpscUnboundedArrayQueueProducerFields<E>
{
    protected E[] producerBuffer;
    protected long producerLookAhead;
    protected int producerLookAheadStep;
    protected long producerMask;
}
