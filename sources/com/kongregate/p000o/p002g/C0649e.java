package com.kongregate.p000o.p002g;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* renamed from: com.kongregate.o.g.e */
public class C0649e implements LayeredSocketFactory, SocketFactory {
    /* renamed from: b */
    private static final TrustManager[] f1030b = new TrustManager[]{new C06481()};
    /* renamed from: a */
    private SSLContext f1031a = null;

    /* renamed from: com.kongregate.o.g.e$1 */
    static class C06481 implements X509TrustManager {
        C06481() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }
    }

    /* renamed from: a */
    public static SSLContext m1101a() throws IOException {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, f1030b, null);
            return instance;
        } catch (Exception e) {
            throw new IOException("Couldn't create SSL context: " + e.toString());
        }
    }

    /* renamed from: b */
    private SSLContext m1102b() throws IOException {
        if (this.f1031a == null) {
            this.f1031a = C0649e.m1101a();
        }
        return this.f1031a;
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) throws IOException {
        int connectionTimeout = HttpConnectionParams.getConnectionTimeout(httpParams);
        int soTimeout = HttpConnectionParams.getSoTimeout(httpParams);
        SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
        SSLSocket sSLSocket = (SSLSocket) (socket != null ? socket : createSocket());
        if (inetAddress != null || i2 > 0) {
            if (i2 < 0) {
                i2 = 0;
            }
            sSLSocket.bind(new InetSocketAddress(inetAddress, i2));
        }
        sSLSocket.connect(inetSocketAddress, connectionTimeout);
        sSLSocket.setSoTimeout(soTimeout);
        return sSLSocket;
    }

    public Socket createSocket() throws IOException {
        return m1102b().getSocketFactory().createSocket();
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return true;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return m1102b().getSocketFactory().createSocket(socket, str, i, z);
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(C0649e.class);
    }

    public int hashCode() {
        return C0649e.class.hashCode();
    }
}
