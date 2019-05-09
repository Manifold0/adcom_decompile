// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;
import java.util.Iterator;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class BaseLinkedQueue<E> extends BaseLinkedQueueConsumerNodeRef<E>
{
    long p00;
    long p01;
    long p02;
    long p03;
    long p04;
    long p05;
    long p06;
    long p07;
    long p30;
    long p31;
    long p32;
    long p33;
    long p34;
    long p35;
    long p36;
    long p37;
    
    @Override
    public final boolean isEmpty() {
        return this.lvConsumerNode() == this.lvProducerNode();
    }
    
    @Override
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
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
}
