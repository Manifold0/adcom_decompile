package com.unity3d.player;

import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.player.C0240b.C0232b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

class UnityWebRequest implements Runnable {
    /* renamed from: k */
    private static final HostnameVerifier f146k = new C02311();
    /* renamed from: a */
    private long f147a;
    /* renamed from: b */
    private String f148b;
    /* renamed from: c */
    private String f149c;
    /* renamed from: d */
    private Map f150d;
    /* renamed from: e */
    private boolean f151e;
    /* renamed from: f */
    private int f152f;
    /* renamed from: g */
    private long f153g;
    /* renamed from: h */
    private long f154h;
    /* renamed from: i */
    private boolean f155i;
    /* renamed from: j */
    private boolean f156j;

    /* renamed from: com.unity3d.player.UnityWebRequest$1 */
    static class C02311 implements HostnameVerifier {
        C02311() {
        }

        public final boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* renamed from: com.unity3d.player.UnityWebRequest$2 */
    class C02332 extends C0232b {
        /* renamed from: b */
        final /* synthetic */ UnityWebRequest f145b;

        C02332(UnityWebRequest unityWebRequest) {
            this.f145b = unityWebRequest;
        }

        public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            byte[] encoded = (x509CertificateArr == null || x509CertificateArr.length <= 0) ? new byte[0] : x509CertificateArr[0].getEncoded();
            if (!this.f145b.validateCertificateCallback(encoded)) {
                throw new CertificateException();
            }
        }
    }

    static {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    UnityWebRequest(long j, String str, Map map, String str2, boolean z, int i) {
        this.f147a = j;
        this.f148b = str2;
        this.f149c = str;
        this.f150d = map;
        this.f151e = z;
        this.f152f = i;
    }

    private static native void contentLengthCallback(long j, int i);

    private static native boolean downloadCallback(long j, ByteBuffer byteBuffer, int i);

    private static native void errorCallback(long j, int i, String str);

    private boolean hasTimedOut() {
        return this.f152f > 0 && System.currentTimeMillis() - this.f153g >= ((long) this.f152f);
    }

    private static native void headerCallback(long j, String str, String str2);

    private static native void responseCodeCallback(long j, int i);

    private void runSafe() {
        this.f153g = System.currentTimeMillis();
        try {
            URL url = new URL(this.f148b);
            URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(this.f152f);
            openConnection.setReadTimeout(this.f152f);
            if (openConnection instanceof HttpsURLConnection) {
                C0232b c02332;
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
                if (this.f151e) {
                    c02332 = new C02332(this);
                    httpsURLConnection.setHostnameVerifier(f146k);
                } else {
                    c02332 = null;
                }
                SSLSocketFactory a = C0240b.m148a(c02332);
                if (a != null) {
                    httpsURLConnection.setSSLSocketFactory(a);
                }
            }
            if (!url.getProtocol().equalsIgnoreCase(ParametersKeys.FILE) || url.getHost().isEmpty()) {
                HttpURLConnection httpURLConnection;
                int uploadCallback;
                if (openConnection instanceof HttpURLConnection) {
                    try {
                        httpURLConnection = (HttpURLConnection) openConnection;
                        httpURLConnection.setRequestMethod(this.f149c);
                        httpURLConnection.setInstanceFollowRedirects(false);
                        if (this.f154h > 0) {
                            if (this.f156j) {
                                httpURLConnection.setChunkedStreamingMode(0);
                            } else {
                                httpURLConnection.setFixedLengthStreamingMode((int) this.f154h);
                            }
                            if (this.f155i) {
                                httpURLConnection.addRequestProperty("Expect", "100-continue");
                            }
                        }
                    } catch (ProtocolException e) {
                        badProtocolCallback(e.toString());
                        return;
                    }
                }
                if (this.f150d != null) {
                    for (Entry entry : this.f150d.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(131072);
                if (uploadCallback(null) > 0) {
                    openConnection.setDoOutput(true);
                    try {
                        OutputStream outputStream = openConnection.getOutputStream();
                        uploadCallback = uploadCallback(allocateDirect);
                        while (uploadCallback > 0) {
                            if (hasTimedOut()) {
                                outputStream.close();
                                errorCallback(this.f147a, 14, "WebRequest timed out.");
                                return;
                            }
                            outputStream.write(allocateDirect.array(), allocateDirect.arrayOffset(), uploadCallback);
                            uploadCallback = uploadCallback(allocateDirect);
                        }
                    } catch (Exception e2) {
                        errorCallback(e2.toString());
                        return;
                    }
                }
                if (openConnection instanceof HttpURLConnection) {
                    try {
                        responseCodeCallback(((HttpURLConnection) openConnection).getResponseCode());
                    } catch (UnknownHostException e3) {
                        unknownHostCallback(e3.toString());
                        return;
                    } catch (SSLException e4) {
                        sslCannotConnectCallback(e4);
                        return;
                    } catch (SocketTimeoutException e5) {
                        errorCallback(this.f147a, 14, e5.toString());
                        return;
                    } catch (IOException e6) {
                        errorCallback(e6.toString());
                        return;
                    }
                }
                Map headerFields = openConnection.getHeaderFields();
                headerCallback(headerFields);
                if ((headerFields == null || !headerFields.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                    headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                }
                if ((headerFields == null || !headerFields.containsKey("content-type")) && openConnection.getContentType() != null) {
                    headerCallback("content-type", openConnection.getContentType());
                }
                contentLengthCallback(openConnection.getContentLength());
                try {
                    InputStream errorStream;
                    if (openConnection instanceof HttpURLConnection) {
                        httpURLConnection = (HttpURLConnection) openConnection;
                        responseCodeCallback(httpURLConnection.getResponseCode());
                        errorStream = httpURLConnection.getErrorStream();
                    } else {
                        errorStream = null;
                    }
                    if (errorStream == null) {
                        errorStream = openConnection.getInputStream();
                    }
                    ReadableByteChannel newChannel = Channels.newChannel(errorStream);
                    uploadCallback = newChannel.read(allocateDirect);
                    while (uploadCallback != -1) {
                        if (!hasTimedOut()) {
                            if (!downloadCallback(allocateDirect, uploadCallback)) {
                                break;
                            }
                            allocateDirect.clear();
                            uploadCallback = newChannel.read(allocateDirect);
                        } else {
                            newChannel.close();
                            errorCallback(this.f147a, 14, "WebRequest timed out.");
                            return;
                        }
                    }
                    newChannel.close();
                    return;
                } catch (UnknownHostException e32) {
                    unknownHostCallback(e32.toString());
                    return;
                } catch (SSLException e42) {
                    sslCannotConnectCallback(e42);
                    return;
                } catch (SocketTimeoutException e52) {
                    errorCallback(this.f147a, 14, e52.toString());
                    return;
                } catch (IOException e62) {
                    errorCallback(this.f147a, 14, e62.toString());
                    return;
                } catch (Exception e22) {
                    errorCallback(e22.toString());
                    return;
                }
            }
            malformattedUrlCallback("file:// must use an absolute path");
        } catch (MalformedURLException e7) {
            malformattedUrlCallback(e7.toString());
        } catch (IOException e622) {
            errorCallback(e622.toString());
        }
    }

    private static native int uploadCallback(long j, ByteBuffer byteBuffer);

    private static native boolean validateCertificateCallback(long j, byte[] bArr);

    protected void badProtocolCallback(String str) {
        errorCallback(this.f147a, 4, str);
    }

    protected void contentLengthCallback(int i) {
        contentLengthCallback(this.f147a, i);
    }

    protected boolean downloadCallback(ByteBuffer byteBuffer, int i) {
        return downloadCallback(this.f147a, byteBuffer, i);
    }

    protected void errorCallback(String str) {
        errorCallback(this.f147a, 2, str);
    }

    protected void headerCallback(String str, String str2) {
        headerCallback(this.f147a, str, str2);
    }

    protected void headerCallback(Map map) {
        if (map != null && map.size() != 0) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    str = "Status";
                }
                for (String headerCallback : (List) entry.getValue()) {
                    headerCallback(str, headerCallback);
                }
            }
        }
    }

    protected void malformattedUrlCallback(String str) {
        errorCallback(this.f147a, 5, str);
    }

    protected void responseCodeCallback(int i) {
        responseCodeCallback(this.f147a, i);
    }

    public void run() {
        try {
            runSafe();
        } catch (Exception e) {
            errorCallback(e.toString());
        }
    }

    void setupTransferSettings(long j, boolean z, boolean z2) {
        this.f154h = j;
        this.f155i = z;
        this.f156j = z2;
    }

    protected void sslCannotConnectCallback(SSLException sSLException) {
        String sSLException2 = sSLException.toString();
        int i = 16;
        Throwable cause;
        while (cause != null) {
            if (cause instanceof SSLKeyException) {
                i = 23;
                break;
            } else if ((cause instanceof SSLPeerUnverifiedException) || (cause instanceof CertPathValidatorException)) {
                i = 25;
                break;
            } else {
                cause = cause.getCause();
            }
        }
        errorCallback(this.f147a, i, sSLException2);
    }

    protected void unknownHostCallback(String str) {
        errorCallback(this.f147a, 7, str);
    }

    protected int uploadCallback(ByteBuffer byteBuffer) {
        return uploadCallback(this.f147a, byteBuffer);
    }

    protected boolean validateCertificateCallback(byte[] bArr) {
        return validateCertificateCallback(this.f147a, bArr);
    }
}
