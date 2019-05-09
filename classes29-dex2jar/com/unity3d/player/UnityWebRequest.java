// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.security.cert.CertPathValidatorException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLKeyException;
import java.nio.channels.ReadableByteChannel;
import java.io.InputStream;
import java.util.List;
import java.io.OutputStream;
import java.util.Iterator;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.net.URLConnection;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLException;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.net.ssl.SSLSession;
import java.net.CookieManager;
import java.net.CookieHandler;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;

class UnityWebRequest implements Runnable
{
    private static final HostnameVerifier k;
    private long a;
    private String b;
    private String c;
    private Map d;
    private boolean e;
    private int f;
    private long g;
    private long h;
    private boolean i;
    private boolean j;
    
    static {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
        k = new HostnameVerifier() {
            @Override
            public final boolean verify(final String s, final SSLSession sslSession) {
                return true;
            }
        };
    }
    
    UnityWebRequest(final long a, final String c, final Map d, final String b, final boolean e, final int f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    private static native void contentLengthCallback(final long p0, final int p1);
    
    private static native boolean downloadCallback(final long p0, final ByteBuffer p1, final int p2);
    
    private static native void errorCallback(final long p0, final int p1, final String p2);
    
    private boolean hasTimedOut() {
        return this.f > 0 && System.currentTimeMillis() - this.g >= this.f;
    }
    
    private static native void headerCallback(final long p0, final String p1, final String p2);
    
    private static native void responseCodeCallback(final long p0, final int p1);
    
    private void runSafe() {
        URLConnection openConnection;
        while (true) {
            this.g = System.currentTimeMillis();
            while (true) {
                Label_0734: {
                    try {
                        final URL url = new URL(this.b);
                        openConnection = url.openConnection();
                        openConnection.setConnectTimeout(this.f);
                        openConnection.setReadTimeout(this.f);
                        if (openConnection instanceof HttpsURLConnection) {
                            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)openConnection;
                            if (!this.e) {
                                break Label_0734;
                            }
                            final X509TrustManager x509TrustManager = new b.b() {
                                @Override
                                public final void checkServerTrusted(final X509Certificate[] array, final String s) {
                                    byte[] encoded;
                                    if (array != null && array.length > 0) {
                                        encoded = array[0].getEncoded();
                                    }
                                    else {
                                        encoded = new byte[0];
                                    }
                                    if (!UnityWebRequest.this.validateCertificateCallback(encoded)) {
                                        throw new CertificateException();
                                    }
                                }
                            };
                            httpsURLConnection.setHostnameVerifier(UnityWebRequest.k);
                            final SSLSocketFactory a = com.unity3d.player.b.a((b.b)x509TrustManager);
                            if (a != null) {
                                httpsURLConnection.setSSLSocketFactory(a);
                            }
                        }
                        if (url.getProtocol().equalsIgnoreCase("file") && !url.getHost().isEmpty()) {
                            this.malformattedUrlCallback("file:// must use an absolute path");
                            return;
                        }
                    }
                    catch (MalformedURLException ex) {
                        this.malformattedUrlCallback(ex.toString());
                        return;
                    }
                    catch (IOException ex2) {
                        this.errorCallback(ex2.toString());
                        return;
                    }
                    break;
                }
                final X509TrustManager x509TrustManager = null;
                continue;
            }
        }
        Label_0209: {
            if (!(openConnection instanceof HttpURLConnection)) {
                break Label_0209;
            }
            try {
                final HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection)openConnection;
                httpsURLConnection2.setRequestMethod(this.c);
                httpsURLConnection2.setInstanceFollowRedirects(false);
                if (this.h > 0L) {
                    if (this.j) {
                        httpsURLConnection2.setChunkedStreamingMode(0);
                    }
                    else {
                        httpsURLConnection2.setFixedLengthStreamingMode((int)this.h);
                    }
                    if (this.i) {
                        httpsURLConnection2.addRequestProperty("Expect", "100-continue");
                    }
                }
                if (this.d != null) {
                    for (final Map.Entry<String, V> entry : this.d.entrySet()) {
                        openConnection.addRequestProperty(entry.getKey(), (String)entry.getValue());
                    }
                }
            }
            catch (ProtocolException ex3) {
                this.badProtocolCallback(ex3.toString());
                return;
            }
        }
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(131072);
        if (this.uploadCallback(null) > 0) {
            while (true) {
                openConnection.setDoOutput(true);
                while (true) {
                    OutputStream outputStream;
                    int n;
                    try {
                        outputStream = openConnection.getOutputStream();
                        n = this.uploadCallback(allocateDirect);
                        if (n <= 0) {
                            break;
                        }
                        if (this.hasTimedOut()) {
                            outputStream.close();
                            errorCallback(this.a, 14, "WebRequest timed out.");
                            return;
                        }
                    }
                    catch (Exception ex4) {
                        this.errorCallback(ex4.toString());
                        return;
                    }
                    outputStream.write(allocateDirect.array(), allocateDirect.arrayOffset(), n);
                    n = this.uploadCallback(allocateDirect);
                    continue;
                }
            }
        }
        Label_0416: {
            if (!(openConnection instanceof HttpURLConnection)) {
                break Label_0416;
            }
            while (true) {
                final HttpsURLConnection httpsURLConnection3 = (HttpsURLConnection)openConnection;
                while (true) {
                    Label_0729: {
                        try {
                            this.responseCodeCallback(httpsURLConnection3.getResponseCode());
                            final Map<String, List<String>> headerFields = openConnection.getHeaderFields();
                            this.headerCallback(headerFields);
                            if ((headerFields == null || !headerFields.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                                this.headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                            }
                            if ((headerFields == null || !headerFields.containsKey("content-type")) && openConnection.getContentType() != null) {
                                this.headerCallback("content-type", openConnection.getContentType());
                            }
                            this.contentLengthCallback(openConnection.getContentLength());
                            try {
                                if (!(openConnection instanceof HttpURLConnection)) {
                                    break Label_0729;
                                }
                                final HttpsURLConnection httpsURLConnection4 = (HttpsURLConnection)openConnection;
                                this.responseCodeCallback(httpsURLConnection4.getResponseCode());
                                final InputStream errorStream = httpsURLConnection4.getErrorStream();
                                InputStream inputStream = errorStream;
                                if (errorStream == null) {
                                    inputStream = openConnection.getInputStream();
                                }
                                final ReadableByteChannel channel = Channels.newChannel(inputStream);
                                if (channel.read(allocateDirect) != -1 && this.hasTimedOut()) {
                                    channel.close();
                                    errorCallback(this.a, 14, "WebRequest timed out.");
                                    return;
                                }
                                goto Label_0675;
                            }
                            catch (UnknownHostException ex5) {
                                this.unknownHostCallback(ex5.toString());
                                return;
                            }
                            catch (SSLException ex6) {
                                this.sslCannotConnectCallback(ex6);
                                return;
                            }
                            catch (SocketTimeoutException ex7) {
                                errorCallback(this.a, 14, ex7.toString());
                                return;
                            }
                            catch (IOException ex8) {
                                errorCallback(this.a, 14, ex8.toString());
                                return;
                            }
                            catch (Exception ex9) {
                                this.errorCallback(ex9.toString());
                                return;
                            }
                        }
                        catch (UnknownHostException ex10) {}
                        catch (SSLException ex11) {}
                        catch (SocketTimeoutException ex12) {}
                        catch (IOException ex13) {}
                    }
                    final InputStream errorStream = null;
                    continue;
                }
            }
        }
    }
    
    private static native int uploadCallback(final long p0, final ByteBuffer p1);
    
    private static native boolean validateCertificateCallback(final long p0, final byte[] p1);
    
    protected void badProtocolCallback(final String s) {
        errorCallback(this.a, 4, s);
    }
    
    protected void contentLengthCallback(final int n) {
        contentLengthCallback(this.a, n);
    }
    
    protected boolean downloadCallback(final ByteBuffer byteBuffer, final int n) {
        return downloadCallback(this.a, byteBuffer, n);
    }
    
    protected void errorCallback(final String s) {
        errorCallback(this.a, 2, s);
    }
    
    protected void headerCallback(final String s, final String s2) {
        headerCallback(this.a, s, s2);
    }
    
    protected void headerCallback(final Map map) {
        if (map != null && map.size() != 0) {
            for (final Map.Entry<String, V> entry : map.entrySet()) {
                String s;
                if ((s = entry.getKey()) == null) {
                    s = "Status";
                }
                final Iterator iterator2 = ((List)entry.getValue()).iterator();
                while (iterator2.hasNext()) {
                    this.headerCallback(s, iterator2.next());
                }
            }
        }
    }
    
    protected void malformattedUrlCallback(final String s) {
        errorCallback(this.a, 5, s);
    }
    
    protected void responseCodeCallback(final int n) {
        responseCodeCallback(this.a, n);
    }
    
    @Override
    public void run() {
        try {
            this.runSafe();
        }
        catch (Exception ex) {
            this.errorCallback(ex.toString());
        }
    }
    
    void setupTransferSettings(final long h, final boolean i, final boolean j) {
        this.h = h;
        this.i = i;
        this.j = j;
    }
    
    protected void sslCannotConnectCallback(SSLException cause) {
        final String string = cause.toString();
        final int n = 16;
        while (true) {
            Label_0050: {
                int n2;
                while (true) {
                    n2 = n;
                    if (cause == null) {
                        break;
                    }
                    if (cause instanceof SSLKeyException) {
                        n2 = 23;
                        break;
                    }
                    if (cause instanceof SSLPeerUnverifiedException || cause instanceof CertPathValidatorException) {
                        break Label_0050;
                    }
                    cause = (SSLException)cause.getCause();
                }
                errorCallback(this.a, n2, string);
                return;
            }
            int n2 = 25;
            continue;
        }
    }
    
    protected void unknownHostCallback(final String s) {
        errorCallback(this.a, 7, s);
    }
    
    protected int uploadCallback(final ByteBuffer byteBuffer) {
        return uploadCallback(this.a, byteBuffer);
    }
    
    protected boolean validateCertificateCallback(final byte[] array) {
        return validateCertificateCallback(this.a, array);
    }
}
