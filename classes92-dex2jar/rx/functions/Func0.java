// 
// Decompiled by Procyon v0.5.34
// 

package rx.functions;

import java.util.concurrent.Callable;

public interface Func0<R> extends Function, Callable<R>
{
    R call();
}
