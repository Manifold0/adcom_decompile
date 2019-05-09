// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.Flushable;
import java.io.Closeable;

public interface ie extends Closeable, Flushable
{
    void a(final hu p0, final long p1);
    
    void close();
    
    void flush();
}
