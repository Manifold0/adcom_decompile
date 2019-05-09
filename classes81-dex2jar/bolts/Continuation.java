// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

public interface Continuation<TTaskResult, TContinuationResult>
{
    TContinuationResult then(final Task<TTaskResult> p0) throws Exception;
}
