package com.unity3d.player;

import android.os.Build.VERSION;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.unity3d.player.b */
public final class C0240b extends SSLSocketFactory {
    /* renamed from: c */
    private static volatile SSLSocketFactory f176c;
    /* renamed from: d */
    private static volatile X509TrustManager f177d;
    /* renamed from: e */
    private static final Object f178e = new Object[0];
    /* renamed from: f */
    private static final Object f179f = new Object[0];
    /* renamed from: g */
    private static final boolean f180g;
    /* renamed from: a */
    private final SSLSocketFactory f181a;
    /* renamed from: b */
    private final C0239a f182b = null;

    /* renamed from: com.unity3d.player.b$b */
    public static abstract class C0232b implements X509TrustManager {
        /* renamed from: a */
        protected X509TrustManager f144a = C0240b.m151c();

        public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f144a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f144a.checkServerTrusted(x509CertificateArr, str);
        }

        public final X509Certificate[] getAcceptedIssuers() {
            return this.f144a.getAcceptedIssuers();
        }
    }

    /* renamed from: com.unity3d.player.b$a */
    class C0239a implements HandshakeCompletedListener {
        public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            SSLSession session = handshakeCompletedEvent.getSession();
            session.getCipherSuite();
            session.getProtocol();
            try {
                session.getPeerPrincipal().getName();
            } catch (SSLPeerUnverifiedException e) {
            }
        }
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 20) {
            z = true;
        }
        f180g = z;
    }

    private C0240b(C0232b[] c0232bArr) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, c0232bArr, null);
        this.f181a = instance.getSocketFactory();
    }

    /* renamed from: a */
    private Socket m147a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            if (f180g) {
                ((SSLSocket) socket).setEnabledProtocols(((SSLSocket) socket).getSupportedProtocols());
            }
            if (this.f182b != null) {
                ((SSLSocket) socket).addHandshakeCompletedListener(this.f182b);
            }
        }
        return socket;
    }

    /* renamed from: a */
    public static SSLSocketFactory m148a(C0232b c0232b) {
        if (c0232b == null) {
            try {
                return C0240b.m150b();
            } catch (Exception e) {
                C0243g.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + e.getMessage() + ")");
                return null;
            }
        }
        return new C0240b(new C0232b[]{c0232b});
    }

    /* renamed from: b */
    private static SSLSocketFactory m150b() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (f178e) {
            if (f176c != null) {
                sSLSocketFactory = f176c;
            } else {
                sSLSocketFactory = new C0240b(null);
                f176c = sSLSocketFactory;
            }
        }
        return sSLSocketFactory;
    }

    /* renamed from: c */
    private static X509TrustManager m151c() {
        synchronized (f179f) {
            if (f177d != null) {
                return f177d;
            }
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init(null);
                for (TrustManager trustManager : instance.getTrustManagers()) {
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        f177d = x509TrustManager;
                        return x509TrustManager;
                    }
                }
            } catch (Exception e) {
                C0243g.Log(5, "CustomSSLSocketFactory: Failed to find X509TrustManager (" + e.getMessage() + ")");
                return null;
            }
        }
    }

    public final Socket createSocket() {
        return m147a(this.f181a.createSocket());
    }

    public final Socket createSocket(String str, int i) {
        return m147a(this.f181a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m147a(this.f181a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m147a(this.f181a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m147a(this.f181a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m147a(this.f181a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.f181a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f181a.getSupportedCipherSuites();
    }
}
