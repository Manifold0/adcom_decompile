// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

public interface MessagePassingQueue<M>
{
    boolean isEmpty();
    
    boolean offer(final M p0);
    
    M peek();
    
    M poll();
    
    int size();
}
