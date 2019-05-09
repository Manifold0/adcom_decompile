// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import java.net.UnknownHostException;
import java.net.SocketAddress;
import javax.net.ssl.SSLSocket;
import java.net.InetSocketAddress;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;

public class e implements LayeredSocketFactory, SocketFactory
{
    private static final TrustManager[] b;
    private SSLContext a;
    
    static {
        b = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] array, final String s) {
                }
                
                @Override
                public void checkServerTrusted(final X509Certificate[] array, final String s) {
                }
                
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            } };
    }
    
    public e() {
        this.a = null;
    }
    
    public static SSLContext a() throws IOException {
        try {
            final SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, e.b, null);
            return instance;
        }
        catch (Exception ex) {
            throw new IOException("Couldn't create SSL context: " + ex.toString());
        }
    }
    
    private SSLContext b() throws IOException {
        if (this.a == null) {
            this.a = a();
        }
        return this.a;
    }
    
    public Socket connectSocket(Socket socket, final String s, int n, final InetAddress inetAddress, final int n2, final HttpParams httpParams) throws IOException {
        final int connectionTimeout = HttpConnectionParams.getConnectionTimeout(httpParams);
        final int soTimeout = HttpConnectionParams.getSoTimeout(httpParams);
        final InetSocketAddress inetSocketAddress = new InetSocketAddress(s, n);
        if (socket == null) {
            socket = this.createSocket();
        }
        final SSLSocket sslSocket = (SSLSocket)socket;
        if (inetAddress != null || n2 > 0) {
            if ((n = n2) < 0) {
                n = 0;
            }
            sslSocket.bind(new InetSocketAddress(inetAddress, n));
        }
        sslSocket.connect(inetSocketAddress, connectionTimeout);
        sslSocket.setSoTimeout(soTimeout);
        return sslSocket;
    }
    
    public Socket createSocket() throws IOException {
        return this.b().getSocketFactory().createSocket();
    }
    
    public Socket createSocket(final Socket socket, final String s, final int n, final boolean b) throws IOException, UnknownHostException {
        return this.b().getSocketFactory().createSocket(socket, s, n, b);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o.getClass().equals(e.class);
    }
    
    @Override
    public int hashCode() {
        return e.class.hashCode();
    }
    
    public boolean isSecure(final Socket socket) throws IllegalArgumentException {
        return true;
    }
}
