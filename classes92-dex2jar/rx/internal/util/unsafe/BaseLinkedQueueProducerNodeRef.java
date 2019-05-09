// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class BaseLinkedQueueProducerNodeRef<E> extends BaseLinkedQueuePad0<E>
{
    protected static final long P_NODE_OFFSET;
    protected LinkedQueueNode<E> producerNode;
    
    static {
        P_NODE_OFFSET = UnsafeAccess.addressOf(BaseLinkedQueueProducerNodeRef.class, "producerNode");
    }
    
    protected final LinkedQueueNode<E> lpProducerNode() {
        return this.producerNode;
    }
    
    protected final LinkedQueueNode<E> lvProducerNode() {
        return (LinkedQueueNode<E>)UnsafeAccess.UNSAFE.getObjectVolatile(this, BaseLinkedQueueProducerNodeRef.P_NODE_OFFSET);
    }
    
    protected final void spProducerNode(final LinkedQueueNode<E> producerNode) {
        this.producerNode = producerNode;
    }
}
