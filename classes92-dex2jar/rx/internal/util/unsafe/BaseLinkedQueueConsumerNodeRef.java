// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
abstract class BaseLinkedQueueConsumerNodeRef<E> extends BaseLinkedQueuePad1<E>
{
    protected static final long C_NODE_OFFSET;
    protected LinkedQueueNode<E> consumerNode;
    
    static {
        C_NODE_OFFSET = UnsafeAccess.addressOf(BaseLinkedQueueConsumerNodeRef.class, "consumerNode");
    }
    
    protected final LinkedQueueNode<E> lpConsumerNode() {
        return this.consumerNode;
    }
    
    protected final LinkedQueueNode<E> lvConsumerNode() {
        return (LinkedQueueNode<E>)UnsafeAccess.UNSAFE.getObjectVolatile(this, BaseLinkedQueueConsumerNodeRef.C_NODE_OFFSET);
    }
    
    protected final void spConsumerNode(final LinkedQueueNode<E> consumerNode) {
        this.consumerNode = consumerNode;
    }
}
