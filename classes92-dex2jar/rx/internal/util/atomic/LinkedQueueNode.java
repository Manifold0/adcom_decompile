// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.atomic;

import java.util.concurrent.atomic.AtomicReference;

public final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>>
{
    private static final long serialVersionUID = 2404266111789071508L;
    private E value;
    
    public LinkedQueueNode() {
    }
    
    public LinkedQueueNode(final E e) {
        this.spValue(e);
    }
    
    public E getAndNullValue() {
        final E lpValue = this.lpValue();
        this.spValue(null);
        return lpValue;
    }
    
    public E lpValue() {
        return this.value;
    }
    
    public LinkedQueueNode<E> lvNext() {
        return this.get();
    }
    
    public void soNext(final LinkedQueueNode<E> linkedQueueNode) {
        this.lazySet(linkedQueueNode);
    }
    
    public void spValue(final E value) {
        this.value = value;
    }
}
