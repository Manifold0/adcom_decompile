// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

abstract class SpscUnboundedArrayQueueConsumerColdField<E> extends SpscUnboundedArrayQueueL2Pad<E>
{
    protected E[] consumerBuffer;
    protected long consumerMask;
}
