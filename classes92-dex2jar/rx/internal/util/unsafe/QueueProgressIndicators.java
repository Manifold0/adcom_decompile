// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

public interface QueueProgressIndicators
{
    long currentConsumerIndex();
    
    long currentProducerIndex();
}
