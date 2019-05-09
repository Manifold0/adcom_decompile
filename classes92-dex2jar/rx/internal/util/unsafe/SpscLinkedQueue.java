// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;

public final class SpscLinkedQueue<E> extends BaseLinkedQueue<E>
{
    public SpscLinkedQueue() {
        this.spProducerNode(new LinkedQueueNode<E>());
        this.spConsumerNode(this.producerNode);
        this.consumerNode.soNext(null);
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        final LinkedQueueNode<E> producerNode = new LinkedQueueNode<E>(e);
        this.producerNode.soNext(producerNode);
        this.producerNode = producerNode;
        return true;
    }
    
    @Override
    public E peek() {
        final LinkedQueueNode<E> lvNext = this.consumerNode.lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }
    
    @Override
    public E poll() {
        final LinkedQueueNode<E> lvNext = this.consumerNode.lvNext();
        if (lvNext != null) {
            final E andNullValue = lvNext.getAndNullValue();
            this.consumerNode = lvNext;
            return andNullValue;
        }
        return null;
    }
}
