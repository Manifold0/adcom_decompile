// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3;

import okhttp3.internal.Util;
import javax.net.ssl.SSLSocketFactory;
import javax.net.SocketFactory;
import java.net.ProxySelector;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import java.util.List;

public final class Address
{
    final CertificatePinner certificatePinner;
    final List<ConnectionSpec> connectionSpecs;
    final Dns dns;
    final HostnameVerifier hostnameVerifier;
    final List<Protocol> protocols;
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final HttpUrl url;
    
    public Address(final String s, final int n, final Dns dns, final SocketFactory socketFactory, final SSLSocketFactory sslSocketFactory, final HostnameVerifier hostnameVerifier, final CertificatePinner certificatePinner, final Authenticator proxyAuthenticator, final Proxy proxy, final List<Protocol> list, final List<ConnectionSpec> list2, final ProxySelector proxySelector) {
        final HttpUrl.Builder builder = new HttpUrl.Builder();
        String s2;
        if (sslSocketFactory != null) {
            s2 = "https";
        }
        else {
            s2 = "http";
        }
        this.url = builder.scheme(s2).host(s).port(n).build();
        if (dns == null) {
            throw new NullPointerException("dns == null");
        }
        this.dns = dns;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.socketFactory = socketFactory;
        if (proxyAuthenticator == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.proxyAuthenticator = proxyAuthenticator;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.protocols = Util.immutableList(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.connectionSpecs = Util.immutableList(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.proxySelector = proxySelector;
        this.proxy = proxy;
        this.sslSocketFactory = sslSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.certificatePinner = certificatePinner;
    }
    
    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }
    
    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }
    
    public Dns dns() {
        return this.dns;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof Address) {
            final Address address = (Address)o;
            b2 = b;
            if (this.url.equals(address.url)) {
                b2 = b;
                if (this.dns.equals(address.dns)) {
                    b2 = b;
                    if (this.proxyAuthenticator.equals(address.proxyAuthenticator)) {
                        b2 = b;
                        if (this.protocols.equals(address.protocols)) {
                            b2 = b;
                            if (this.connectionSpecs.equals(address.connectionSpecs)) {
                                b2 = b;
                                if (this.proxySelector.equals(address.proxySelector)) {
                                    b2 = b;
                                    if (Util.equal(this.proxy, address.proxy)) {
                                        b2 = b;
                                        if (Util.equal(this.sslSocketFactory, address.sslSocketFactory)) {
                                            b2 = b;
                                            if (Util.equal(this.hostnameVerifier, address.hostnameVerifier)) {
                                                b2 = b;
                                                if (Util.equal(this.certificatePinner, address.certificatePinner)) {
                                                    b2 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = this.url.hashCode();
        final int hashCode3 = this.dns.hashCode();
        final int hashCode4 = this.proxyAuthenticator.hashCode();
        final int hashCode5 = this.protocols.hashCode();
        final int hashCode6 = this.connectionSpecs.hashCode();
        final int hashCode7 = this.proxySelector.hashCode();
        int hashCode8;
        if (this.proxy != null) {
            hashCode8 = this.proxy.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        int hashCode9;
        if (this.sslSocketFactory != null) {
            hashCode9 = this.sslSocketFactory.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        int hashCode10;
        if (this.hostnameVerifier != null) {
            hashCode10 = this.hostnameVerifier.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        if (this.certificatePinner != null) {
            hashCode = this.certificatePinner.hashCode();
        }
        return (((((((((hashCode2 + 527) * 31 + hashCode3) * 31 + hashCode4) * 31 + hashCode5) * 31 + hashCode6) * 31 + hashCode7) * 31 + hashCode8) * 31 + hashCode9) * 31 + hashCode10) * 31 + hashCode;
    }
    
    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }
    
    public List<Protocol> protocols() {
        return this.protocols;
    }
    
    public Proxy proxy() {
        return this.proxy;
    }
    
    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }
    
    public ProxySelector proxySelector() {
        return this.proxySelector;
    }
    
    public SocketFactory socketFactory() {
        return this.socketFactory;
    }
    
    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Address{").append(this.url.host()).append(":").append(this.url.port());
        if (this.proxy != null) {
            append.append(", proxy=").append(this.proxy);
        }
        else {
            append.append(", proxySelector=").append(this.proxySelector);
        }
        append.append("}");
        return append.toString();
    }
    
    public HttpUrl url() {
        return this.url;
    }
}
