// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

public final class SpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E>
{
    public SpscLinkedAtomicQueue() {
        final LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<E>();
        this.spProducerNode(linkedQueueNode);
        this.spConsumerNode(linkedQueueNode);
        linkedQueueNode.soNext(null);
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        final LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<E>(e);
        this.lpProducerNode().soNext(linkedQueueNode);
        this.spProducerNode(linkedQueueNode);
        return true;
    }
    
    @Override
    public E peek() {
        final LinkedQueueNode<E> lvNext = this.lpConsumerNode().lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }
    
    @Override
    public E poll() {
        final LinkedQueueNode<E> lvNext = this.lpConsumerNode().lvNext();
        if (lvNext != null) {
            final E andNullValue = lvNext.getAndNullValue();
            this.spConsumerNode(lvNext);
            return andNullValue;
        }
        return null;
    }
}
