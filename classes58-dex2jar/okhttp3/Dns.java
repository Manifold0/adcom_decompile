// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3;

import java.util.Arrays;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.List;

public interface Dns
{
    public static final Dns SYSTEM = new Dns() {
        @Override
        public List<InetAddress> lookup(final String s) throws UnknownHostException {
            if (s == null) {
                throw new UnknownHostException("hostname == null");
            }
            return Arrays.asList(InetAddress.getAllByName(s));
        }
    };
    
    List<InetAddress> lookup(final String p0) throws UnknownHostException;
}
