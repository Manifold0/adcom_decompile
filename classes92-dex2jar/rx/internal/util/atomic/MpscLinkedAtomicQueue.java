// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

public final class MpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E>
{
    public MpscLinkedAtomicQueue() {
        final LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<E>();
        this.spConsumerNode(linkedQueueNode);
        this.xchgProducerNode(linkedQueueNode);
    }
    
    @Override
    public boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        final LinkedQueueNode<E> linkedQueueNode = new LinkedQueueNode<E>(e);
        this.xchgProducerNode(linkedQueueNode).soNext(linkedQueueNode);
        return true;
    }
    
    @Override
    public E peek() {
        final LinkedQueueNode<E> lpConsumerNode = this.lpConsumerNode();
        final LinkedQueueNode<E> lvNext = lpConsumerNode.lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        if (lpConsumerNode != this.lvProducerNode()) {
            LinkedQueueNode<E> lvNext2;
            do {
                lvNext2 = lpConsumerNode.lvNext();
            } while (lvNext2 == null);
            return lvNext2.lpValue();
        }
        return null;
    }
    
    @Override
    public E poll() {
        final LinkedQueueNode<E> lpConsumerNode = this.lpConsumerNode();
        final LinkedQueueNode<E> lvNext = lpConsumerNode.lvNext();
        if (lvNext != null) {
            final E andNullValue = lvNext.getAndNullValue();
            this.spConsumerNode(lvNext);
            return andNullValue;
        }
        if (lpConsumerNode != this.lvProducerNode()) {
            LinkedQueueNode<E> lvNext2;
            do {
                lvNext2 = lpConsumerNode.lvNext();
            } while (lvNext2 == null);
            final E andNullValue2 = lvNext2.getAndNullValue();
            this.spConsumerNode(lvNext2);
            return andNullValue2;
        }
        return null;
    }
}
