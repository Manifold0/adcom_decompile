// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3;

import java.net.Socket;

public interface Connection
{
    Handshake handshake();
    
    Protocol protocol();
    
    Route route();
    
    Socket socket();
}
