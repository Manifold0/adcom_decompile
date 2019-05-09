// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3;

import java.net.Proxy;
import java.net.InetSocketAddress;

public final class Route
{
    final Address address;
    final InetSocketAddress inetSocketAddress;
    final Proxy proxy;
    
    public Route(final Address address, final Proxy proxy, final InetSocketAddress inetSocketAddress) {
        if (address == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
    }
    
    public Address address() {
        return this.address;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof Route) {
            final Route route = (Route)o;
            b2 = b;
            if (this.address.equals(route.address)) {
                b2 = b;
                if (this.proxy.equals(route.proxy)) {
                    b2 = b;
                    if (this.inetSocketAddress.equals(route.inetSocketAddress)) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return ((this.address.hashCode() + 527) * 31 + this.proxy.hashCode()) * 31 + this.inetSocketAddress.hashCode();
    }
    
    public Proxy proxy() {
        return this.proxy;
    }
    
    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }
    
    public InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }
    
    @Override
    public String toString() {
        return "Route{" + this.inetSocketAddress + "}";
    }
}
