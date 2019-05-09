// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.connection;

import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.ws.RealWebSocket;
import java.net.SocketException;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http.HttpCodec;
import java.net.SocketTimeoutException;
import okhttp3.internal.Version;
import okio.Source;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.HttpUrl;
import okhttp3.Request;
import java.net.ProtocolException;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Util;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.tls.OkHostnameVerifier;
import java.security.cert.Certificate;
import okhttp3.CertificatePinner;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import okhttp3.Address;
import java.net.ConnectException;
import okio.Okio;
import okhttp3.internal.platform.Platform;
import java.net.Proxy;
import java.util.ArrayList;
import okio.BufferedSource;
import okio.BufferedSink;
import okhttp3.Route;
import java.net.Socket;
import okhttp3.Protocol;
import okhttp3.Handshake;
import okhttp3.ConnectionPool;
import java.lang.ref.Reference;
import java.util.List;
import okhttp3.Connection;
import okhttp3.internal.http2.Http2Connection;

public final class RealConnection extends Listener implements Connection
{
    public int allocationLimit;
    public final List<Reference<StreamAllocation>> allocations;
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;
    
    public RealConnection(final ConnectionPool connectionPool, final Route route) {
        this.allocationLimit = 1;
        this.allocations = new ArrayList<Reference<StreamAllocation>>();
        this.idleAtNanos = Long.MAX_VALUE;
        this.connectionPool = connectionPool;
        this.route = route;
    }
    
    private void connectSocket(final int n, final int soTimeout) throws IOException {
        final Proxy proxy = this.route.proxy();
        final Address address = this.route.address();
        Label_0106: {
            if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.HTTP) {
                break Label_0106;
            }
            Socket socket = address.socketFactory().createSocket();
            while (true) {
                (this.rawSocket = socket).setSoTimeout(soTimeout);
                try {
                    Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), n);
                    this.source = Okio.buffer(Okio.source(this.rawSocket));
                    this.sink = Okio.buffer(Okio.sink(this.rawSocket));
                    return;
                    socket = new Socket(proxy);
                }
                catch (ConnectException ex2) {
                    final ConnectException ex = new ConnectException("Failed to connect to " + this.route.socketAddress());
                    ex.initCause(ex2);
                    throw ex;
                }
            }
        }
    }
    
    private void connectTls(final ConnectionSpecSelector connectionSpecSelector) throws IOException {
        final Address address = this.route.address();
        Object sslSocketFactory = address.sslSocketFactory();
        SSLSocket sslSocket = null;
        SSLSocket sslSocket2 = null;
        Handshake value;
        try {
            sslSocketFactory = (sslSocket = (sslSocket2 = (SSLSocket)((SSLSocketFactory)sslSocketFactory).createSocket(this.rawSocket, address.url().host(), address.url().port(), true)));
            final ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket((SSLSocket)sslSocketFactory);
            sslSocket2 = (SSLSocket)sslSocketFactory;
            sslSocket = (SSLSocket)sslSocketFactory;
            if (configureSecureSocket.supportsTlsExtensions()) {
                sslSocket2 = (SSLSocket)sslSocketFactory;
                sslSocket = (SSLSocket)sslSocketFactory;
                Platform.get().configureTlsExtensions((SSLSocket)sslSocketFactory, address.url().host(), address.protocols());
            }
            sslSocket2 = (SSLSocket)sslSocketFactory;
            sslSocket = (SSLSocket)sslSocketFactory;
            ((SSLSocket)sslSocketFactory).startHandshake();
            sslSocket2 = (SSLSocket)sslSocketFactory;
            sslSocket = (SSLSocket)sslSocketFactory;
            value = Handshake.get(((SSLSocket)sslSocketFactory).getSession());
            sslSocket2 = (SSLSocket)sslSocketFactory;
            sslSocket = (SSLSocket)sslSocketFactory;
            if (!address.hostnameVerifier().verify(address.url().host(), ((SSLSocket)sslSocketFactory).getSession())) {
                sslSocket2 = (SSLSocket)sslSocketFactory;
                sslSocket = (SSLSocket)sslSocketFactory;
                final X509Certificate x509Certificate = value.peerCertificates().get(0);
                sslSocket2 = (SSLSocket)sslSocketFactory;
                sslSocket = (SSLSocket)sslSocketFactory;
                throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
            }
        }
        catch (AssertionError assertionError) {
            sslSocket = sslSocket2;
            if (Util.isAndroidGetsocknameError(assertionError)) {
                sslSocket = sslSocket2;
                throw new IOException(assertionError);
            }
            return;
        }
        finally {
            if (sslSocket != null) {
                Platform.get().afterHandshake(sslSocket);
            }
            if (!false) {
                Util.closeQuietly(sslSocket);
            }
        }
        address.certificatePinner().check(address.url().host(), value.peerCertificates());
        final ConnectionSpec connectionSpec;
        String selectedProtocol;
        if (connectionSpec.supportsTlsExtensions()) {
            selectedProtocol = Platform.get().getSelectedProtocol((SSLSocket)sslSocketFactory);
        }
        else {
            selectedProtocol = null;
        }
        this.socket = (Socket)sslSocketFactory;
        this.source = Okio.buffer(Okio.source(this.socket));
        this.sink = Okio.buffer(Okio.sink(this.socket));
        this.handshake = value;
        Protocol protocol;
        if (selectedProtocol != null) {
            protocol = Protocol.get(selectedProtocol);
        }
        else {
            protocol = Protocol.HTTP_1_1;
        }
        this.protocol = protocol;
        if (sslSocketFactory != null) {
            Platform.get().afterHandshake((SSLSocket)sslSocketFactory);
        }
        if (!true) {
            Util.closeQuietly((Socket)sslSocketFactory);
        }
    }
    
    private void connectTunnel(final int n, final int n2, final int n3) throws IOException {
        Request request = this.createTunnelRequest();
        final HttpUrl url = request.url();
        int n4 = 0;
        while (true) {
            ++n4;
            if (n4 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: " + 21);
            }
            this.connectSocket(n, n2);
            request = this.createTunnel(n2, n3, request, url);
            if (request == null) {
                return;
            }
            Util.closeQuietly(this.rawSocket);
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
        }
    }
    
    private Request createTunnel(final int n, final int n2, Request request, final HttpUrl httpUrl) throws IOException {
        final Request request2 = null;
        final String string = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        Response build;
        Request authenticate = null;
    Label_0336:
        do {
            final Http1Codec http1Codec = new Http1Codec(null, null, this.source, this.sink);
            this.source.timeout().timeout((long)n, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long)n2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), string);
            http1Codec.finishRequest();
            build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength;
            if ((contentLength = HttpHeaders.contentLength(build)) == -1L) {
                contentLength = 0L;
            }
            final Source fixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(fixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            fixedLengthSource.close();
            switch (build.code()) {
                default: {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
                case 200: {
                    if (this.source.buffer().exhausted()) {
                        authenticate = request2;
                        if (this.sink.buffer().exhausted()) {
                            break Label_0336;
                        }
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                }
                case 407: {
                    authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    if (authenticate == null) {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                    request = authenticate;
                    continue;
                }
            }
        } while (!"close".equalsIgnoreCase(build.header("Connection")));
        return authenticate;
    }
    
    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }
    
    private void establishProtocol(final ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
        }
        else {
            this.connectTls(connectionSpecSelector);
            if (this.protocol == Protocol.HTTP_2) {
                this.socket.setSoTimeout(0);
                (this.http2Connection = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).build()).start();
            }
        }
    }
    
    public static RealConnection testConnection(final ConnectionPool connectionPool, final Route route, final Socket socket, final long idleAtNanos) {
        final RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = idleAtNanos;
        return realConnection;
    }
    
    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }
    
    public void connect(final int p0, final int p1, final int p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        okhttp3/internal/connection/RealConnection.protocol:Lokhttp3/Protocol;
        //     4: ifnull          18
        //     7: new             Ljava/lang/IllegalStateException;
        //    10: dup            
        //    11: ldc_w           "already connected"
        //    14: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: aconst_null    
        //    19: astore          6
        //    21: aload_0        
        //    22: getfield        okhttp3/internal/connection/RealConnection.route:Lokhttp3/Route;
        //    25: invokevirtual   okhttp3/Route.address:()Lokhttp3/Address;
        //    28: invokevirtual   okhttp3/Address.connectionSpecs:()Ljava/util/List;
        //    31: astore          8
        //    33: new             Lokhttp3/internal/connection/ConnectionSpecSelector;
        //    36: dup            
        //    37: aload           8
        //    39: invokespecial   okhttp3/internal/connection/ConnectionSpecSelector.<init>:(Ljava/util/List;)V
        //    42: astore          7
        //    44: aload           6
        //    46: astore          5
        //    48: aload_0        
        //    49: getfield        okhttp3/internal/connection/RealConnection.route:Lokhttp3/Route;
        //    52: invokevirtual   okhttp3/Route.address:()Lokhttp3/Address;
        //    55: invokevirtual   okhttp3/Address.sslSocketFactory:()Ljavax/net/ssl/SSLSocketFactory;
        //    58: ifnonnull       164
        //    61: aload           8
        //    63: getstatic       okhttp3/ConnectionSpec.CLEARTEXT:Lokhttp3/ConnectionSpec;
        //    66: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //    71: ifne            92
        //    74: new             Lokhttp3/internal/connection/RouteException;
        //    77: dup            
        //    78: new             Ljava/net/UnknownServiceException;
        //    81: dup            
        //    82: ldc_w           "CLEARTEXT communication not enabled for client"
        //    85: invokespecial   java/net/UnknownServiceException.<init>:(Ljava/lang/String;)V
        //    88: invokespecial   okhttp3/internal/connection/RouteException.<init>:(Ljava/io/IOException;)V
        //    91: athrow         
        //    92: aload_0        
        //    93: getfield        okhttp3/internal/connection/RealConnection.route:Lokhttp3/Route;
        //    96: invokevirtual   okhttp3/Route.address:()Lokhttp3/Address;
        //    99: invokevirtual   okhttp3/Address.url:()Lokhttp3/HttpUrl;
        //   102: invokevirtual   okhttp3/HttpUrl.host:()Ljava/lang/String;
        //   105: astore          8
        //   107: aload           6
        //   109: astore          5
        //   111: invokestatic    okhttp3/internal/platform/Platform.get:()Lokhttp3/internal/platform/Platform;
        //   114: aload           8
        //   116: invokevirtual   okhttp3/internal/platform/Platform.isCleartextTrafficPermitted:(Ljava/lang/String;)Z
        //   119: ifne            164
        //   122: new             Lokhttp3/internal/connection/RouteException;
        //   125: dup            
        //   126: new             Ljava/net/UnknownServiceException;
        //   129: dup            
        //   130: new             Ljava/lang/StringBuilder;
        //   133: dup            
        //   134: invokespecial   java/lang/StringBuilder.<init>:()V
        //   137: ldc_w           "CLEARTEXT communication to "
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload           8
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: ldc_w           " not permitted by network security policy"
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokespecial   java/net/UnknownServiceException.<init>:(Ljava/lang/String;)V
        //   160: invokespecial   okhttp3/internal/connection/RouteException.<init>:(Ljava/io/IOException;)V
        //   163: athrow         
        //   164: aload_0        
        //   165: getfield        okhttp3/internal/connection/RealConnection.route:Lokhttp3/Route;
        //   168: invokevirtual   okhttp3/Route.requiresTunnel:()Z
        //   171: ifeq            218
        //   174: aload_0        
        //   175: iload_1        
        //   176: iload_2        
        //   177: iload_3        
        //   178: invokespecial   okhttp3/internal/connection/RealConnection.connectTunnel:(III)V
        //   181: aload_0        
        //   182: aload           7
        //   184: invokespecial   okhttp3/internal/connection/RealConnection.establishProtocol:(Lokhttp3/internal/connection/ConnectionSpecSelector;)V
        //   187: aload_0        
        //   188: getfield        okhttp3/internal/connection/RealConnection.http2Connection:Lokhttp3/internal/http2/Http2Connection;
        //   191: ifnull          217
        //   194: aload_0        
        //   195: getfield        okhttp3/internal/connection/RealConnection.connectionPool:Lokhttp3/ConnectionPool;
        //   198: astore          5
        //   200: aload           5
        //   202: monitorenter   
        //   203: aload_0        
        //   204: aload_0        
        //   205: getfield        okhttp3/internal/connection/RealConnection.http2Connection:Lokhttp3/internal/http2/Http2Connection;
        //   208: invokevirtual   okhttp3/internal/http2/Http2Connection.maxConcurrentStreams:()I
        //   211: putfield        okhttp3/internal/connection/RealConnection.allocationLimit:I
        //   214: aload           5
        //   216: monitorexit    
        //   217: return         
        //   218: aload_0        
        //   219: iload_1        
        //   220: iload_2        
        //   221: invokespecial   okhttp3/internal/connection/RealConnection.connectSocket:(II)V
        //   224: goto            181
        //   227: astore          8
        //   229: aload_0        
        //   230: getfield        okhttp3/internal/connection/RealConnection.socket:Ljava/net/Socket;
        //   233: invokestatic    okhttp3/internal/Util.closeQuietly:(Ljava/net/Socket;)V
        //   236: aload_0        
        //   237: getfield        okhttp3/internal/connection/RealConnection.rawSocket:Ljava/net/Socket;
        //   240: invokestatic    okhttp3/internal/Util.closeQuietly:(Ljava/net/Socket;)V
        //   243: aload_0        
        //   244: aconst_null    
        //   245: putfield        okhttp3/internal/connection/RealConnection.socket:Ljava/net/Socket;
        //   248: aload_0        
        //   249: aconst_null    
        //   250: putfield        okhttp3/internal/connection/RealConnection.rawSocket:Ljava/net/Socket;
        //   253: aload_0        
        //   254: aconst_null    
        //   255: putfield        okhttp3/internal/connection/RealConnection.source:Lokio/BufferedSource;
        //   258: aload_0        
        //   259: aconst_null    
        //   260: putfield        okhttp3/internal/connection/RealConnection.sink:Lokio/BufferedSink;
        //   263: aload_0        
        //   264: aconst_null    
        //   265: putfield        okhttp3/internal/connection/RealConnection.handshake:Lokhttp3/Handshake;
        //   268: aload_0        
        //   269: aconst_null    
        //   270: putfield        okhttp3/internal/connection/RealConnection.protocol:Lokhttp3/Protocol;
        //   273: aload_0        
        //   274: aconst_null    
        //   275: putfield        okhttp3/internal/connection/RealConnection.http2Connection:Lokhttp3/internal/http2/Http2Connection;
        //   278: aload           5
        //   280: ifnonnull       316
        //   283: new             Lokhttp3/internal/connection/RouteException;
        //   286: dup            
        //   287: aload           8
        //   289: invokespecial   okhttp3/internal/connection/RouteException.<init>:(Ljava/io/IOException;)V
        //   292: astore          6
        //   294: iload           4
        //   296: ifeq            313
        //   299: aload           6
        //   301: astore          5
        //   303: aload           7
        //   305: aload           8
        //   307: invokevirtual   okhttp3/internal/connection/ConnectionSpecSelector.connectionFailed:(Ljava/io/IOException;)Z
        //   310: ifne            164
        //   313: aload           6
        //   315: athrow         
        //   316: aload           5
        //   318: aload           8
        //   320: invokevirtual   okhttp3/internal/connection/RouteException.addConnectException:(Ljava/io/IOException;)V
        //   323: aload           5
        //   325: astore          6
        //   327: goto            294
        //   330: astore          6
        //   332: aload           5
        //   334: monitorexit    
        //   335: aload           6
        //   337: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  164    181    227    330    Ljava/io/IOException;
        //  181    187    227    330    Ljava/io/IOException;
        //  203    217    330    338    Any
        //  218    224    227    330    Ljava/io/IOException;
        //  332    335    330    338    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0217:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public Handshake handshake() {
        return this.handshake;
    }
    
    public boolean isEligible(final Address address) {
        return this.allocations.size() < this.allocationLimit && address.equals(this.route().address()) && !this.noNewStreams;
    }
    
    public boolean isHealthy(final boolean b) {
        boolean b2 = true;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            b2 = false;
        }
        else if (this.http2Connection != null) {
            if (this.http2Connection.isShutdown()) {
                return false;
            }
        }
        else if (b) {
            try {
                final int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted();
                }
                finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            }
            catch (IOException ex) {
                return false;
            }
            catch (SocketTimeoutException ex2) {
                return true;
            }
        }
        return b2;
    }
    
    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }
    
    public HttpCodec newCodec(final OkHttpClient okHttpClient, final StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(okHttpClient.readTimeoutMillis());
        this.source.timeout().timeout((long)okHttpClient.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long)okHttpClient.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }
    
    public RealWebSocket.Streams newWebSocketStreams(final StreamAllocation streamAllocation) {
        return new RealWebSocket.Streams(true, this.source, this.sink) {
            @Override
            public void close() throws IOException {
                streamAllocation.streamFinished(true, streamAllocation.codec());
            }
        };
    }
    
    @Override
    public void onSettings(final Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }
    
    @Override
    public void onStream(final Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }
    
    @Override
    public Protocol protocol() {
        return this.protocol;
    }
    
    @Override
    public Route route() {
        return this.route;
    }
    
    @Override
    public Socket socket() {
        return this.socket;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Connection{").append(this.route.address().url().host()).append(":").append(this.route.address().url().port()).append(", proxy=").append(this.route.proxy()).append(" hostAddress=").append(this.route.socketAddress()).append(" cipherSuite=");
        Object cipherSuite;
        if (this.handshake != null) {
            cipherSuite = this.handshake.cipherSuite();
        }
        else {
            cipherSuite = "none";
        }
        return append.append(cipherSuite).append(" protocol=").append(this.protocol).append('}').toString();
    }
}
