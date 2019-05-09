// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.AbstractQueue;

abstract class BaseLinkedAtomicQueue<E> extends AbstractQueue<E>
{
    private final AtomicReference<LinkedQueueNode<E>> consumerNode;
    private final AtomicReference<LinkedQueueNode<E>> producerNode;
    
    public BaseLinkedAtomicQueue() {
        this.producerNode = new AtomicReference<LinkedQueueNode<E>>();
        this.consumerNode = new AtomicReference<LinkedQueueNode<E>>();
    }
    
    @Override
    public final boolean isEmpty() {
        return this.lvConsumerNode() == this.lvProducerNode();
    }
    
    @Override
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    
    protected final LinkedQueueNode<E> lpConsumerNode() {
        return this.consumerNode.get();
    }
    
    protected final LinkedQueueNode<E> lpProducerNode() {
        return this.producerNode.get();
    }
    
    protected final LinkedQueueNode<E> lvConsumerNode() {
        return this.consumerNode.get();
    }
    
    protected final LinkedQueueNode<E> lvProducerNode() {
        return this.producerNode.get();
    }
    
    @Override
    public final int size() {
        LinkedQueueNode<E> lvConsumerNode;
        LinkedQueueNode<E> lvProducerNode;
        int n;
        LinkedQueueNode<E> lvNext;
        for (lvConsumerNode = this.lvConsumerNode(), lvProducerNode = this.lvProducerNode(), n = 0; lvConsumerNode != lvProducerNode && n < Integer.MAX_VALUE; lvConsumerNode = lvNext, ++n) {
            do {
                lvNext = lvConsumerNode.lvNext();
            } while (lvNext == null);
        }
        return n;
    }
    
    protected final void spConsumerNode(final LinkedQueueNode<E> linkedQueueNode) {
        this.consumerNode.lazySet(linkedQueueNode);
    }
    
    protected final void spProducerNode(final LinkedQueueNode<E> linkedQueueNode) {
        this.producerNode.lazySet(linkedQueueNode);
    }
    
    protected final LinkedQueueNode<E> xchgProducerNode(final LinkedQueueNode<E> linkedQueueNode) {
        return this.producerNode.getAndSet(linkedQueueNode);
    }
}
