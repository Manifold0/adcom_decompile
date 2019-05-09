// 
// Decompiled by Procyon v0.5.34
// 

package rx;

public interface Subscription
{
    boolean isUnsubscribed();
    
    void unsubscribe();
}
