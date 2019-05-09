// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okhttp3.Request;
import java.io.IOException;

public interface Call<T> extends Cloneable
{
    void cancel();
    
    Call<T> clone();
    
    void enqueue(final Callback<T> p0);
    
    Response<T> execute() throws IOException;
    
    boolean isCanceled();
    
    boolean isExecuted();
    
    Request request();
}
