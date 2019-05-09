// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public final class MpscLinkedQueue<E> extends BaseLinkedQueue<E>
{
    public MpscLinkedQueue() {
        this.xchgProducerNode((LinkedQueueNode<E>)(this.consumerNode = (LinkedQueueNode<E>)new LinkedQueueNode<Object>()));
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
        final LinkedQueueNode<E> consumerNode = this.consumerNode;
        final LinkedQueueNode<E> lvNext = consumerNode.lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        if (consumerNode != this.lvProducerNode()) {
            LinkedQueueNode<E> lvNext2;
            do {
                lvNext2 = consumerNode.lvNext();
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
            this.consumerNode = lvNext2;
            return andNullValue2;
        }
        return null;
    }
    
    protected LinkedQueueNode<E> xchgProducerNode(final LinkedQueueNode<E> linkedQueueNode) {
        LinkedQueueNode<E> producerNode;
        do {
            producerNode = this.producerNode;
        } while (!UnsafeAccess.UNSAFE.compareAndSwapObject(this, MpscLinkedQueue.P_NODE_OFFSET, producerNode, linkedQueueNode));
        return producerNode;
    }
}
