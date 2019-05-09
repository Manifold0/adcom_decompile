// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

public interface CacheRequest
{
    void abort();
    
    Sink body() throws IOException;
}
