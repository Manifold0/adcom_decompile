// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.HandshakeCompletedEvent;
import java.net.InetAddress;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import android.os.Build$VERSION;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;

public final class b extends SSLSocketFactory
{
    private static volatile SSLSocketFactory c;
    private static volatile X509TrustManager d;
    private static final Object e;
    private static final Object f;
    private static final boolean g;
    private final SSLSocketFactory a;
    private final a b;
    
    static {
        final boolean b = false;
        e = new Object[0];
        f = new Object[0];
        boolean g2 = b;
        if (Build$VERSION.SDK_INT >= 16) {
            g2 = b;
            if (Build$VERSION.SDK_INT < 20) {
                g2 = true;
            }
        }
        g = g2;
    }
    
    private b(final b[] array) {
        final SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, array, null);
        this.a = instance.getSocketFactory();
        this.b = null;
    }
    
    private Socket a(final Socket socket) {
        if (socket != null && socket instanceof SSLSocket) {
            if (com.unity3d.player.b.g) {
                ((SSLSocket)socket).setEnabledProtocols(((SSLSocket)socket).getSupportedProtocols());
            }
            if (this.b != null) {
                ((SSLSocket)socket).addHandshakeCompletedListener(this.b);
            }
        }
        return socket;
    }
    
    public static SSLSocketFactory a(final b b) {
        Label_0008: {
            if (b != null) {
                break Label_0008;
            }
            try {
                return b();
                return new b(new b[] { b });
            }
            catch (Exception ex) {
                com.unity3d.player.g.Log(5, "CustomSSLSocketFactory: Failed to create SSLSocketFactory (" + ex.getMessage() + ")");
                return null;
            }
        }
    }
    
    private static SSLSocketFactory b() {
        synchronized (b.e) {
            if (b.c != null) {
                return b.c;
            }
            return b.c = new b(null);
        }
    }
    
    private static X509TrustManager c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: aload_2        
        //     5: monitorenter   
        //     6: getstatic       com/unity3d/player/b.d:Ljavax/net/ssl/X509TrustManager;
        //     9: ifnull          20
        //    12: getstatic       com/unity3d/player/b.d:Ljavax/net/ssl/X509TrustManager;
        //    15: astore_3       
        //    16: aload_2        
        //    17: monitorexit    
        //    18: aload_3        
        //    19: areturn        
        //    20: invokestatic    javax/net/ssl/TrustManagerFactory.getDefaultAlgorithm:()Ljava/lang/String;
        //    23: invokestatic    javax/net/ssl/TrustManagerFactory.getInstance:(Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;
        //    26: astore_3       
        //    27: aload_3        
        //    28: aconst_null    
        //    29: invokevirtual   javax/net/ssl/TrustManagerFactory.init:(Ljava/security/KeyStore;)V
        //    32: aload_3        
        //    33: invokevirtual   javax/net/ssl/TrustManagerFactory.getTrustManagers:()[Ljavax/net/ssl/TrustManager;
        //    36: astore_3       
        //    37: aload_3        
        //    38: arraylength    
        //    39: istore_1       
        //    40: iconst_0       
        //    41: istore_0       
        //    42: iload_0        
        //    43: iload_1        
        //    44: if_icmpge       115
        //    47: aload_3        
        //    48: iload_0        
        //    49: aaload         
        //    50: astore          4
        //    52: aload           4
        //    54: instanceof      Ljavax/net/ssl/X509TrustManager;
        //    57: ifeq            79
        //    60: aload           4
        //    62: checkcast       Ljavax/net/ssl/X509TrustManager;
        //    65: astore_3       
        //    66: aload_3        
        //    67: putstatic       com/unity3d/player/b.d:Ljavax/net/ssl/X509TrustManager;
        //    70: aload_2        
        //    71: monitorexit    
        //    72: aload_3        
        //    73: areturn        
        //    74: astore_3       
        //    75: aload_2        
        //    76: monitorexit    
        //    77: aload_3        
        //    78: athrow         
        //    79: iload_0        
        //    80: iconst_1       
        //    81: iadd           
        //    82: istore_0       
        //    83: goto            42
        //    86: astore_3       
        //    87: iconst_5       
        //    88: new             Ljava/lang/StringBuilder;
        //    91: dup            
        //    92: ldc             "CustomSSLSocketFactory: Failed to find X509TrustManager ("
        //    94: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    97: aload_3        
        //    98: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: ldc             ")"
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   112: invokestatic    com/unity3d/player/g.Log:(ILjava/lang/String;)V
        //   115: aload_2        
        //   116: monitorexit    
        //   117: aconst_null    
        //   118: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      18     74     79     Any
        //  20     40     86     115    Ljava/lang/Exception;
        //  20     40     74     79     Any
        //  52     70     86     115    Ljava/lang/Exception;
        //  52     70     74     79     Any
        //  70     72     74     79     Any
        //  75     77     74     79     Any
        //  87     115    74     79     Any
        //  115    117    74     79     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    public final Socket createSocket() {
        return this.a(this.a.createSocket());
    }
    
    @Override
    public final Socket createSocket(final String s, final int n) {
        return this.a(this.a.createSocket(s, n));
    }
    
    @Override
    public final Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) {
        return this.a(this.a.createSocket(s, n, inetAddress, n2));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n) {
        return this.a(this.a.createSocket(inetAddress, n));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) {
        return this.a(this.a.createSocket(inetAddress, n, inetAddress2, n2));
    }
    
    @Override
    public final Socket createSocket(final Socket socket, final String s, final int n, final boolean b) {
        return this.a(this.a.createSocket(socket, s, n, b));
    }
    
    @Override
    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }
    
    @Override
    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
    
    final class a implements HandshakeCompletedListener
    {
        @Override
        public final void handshakeCompleted(final HandshakeCompletedEvent handshakeCompletedEvent) {
            final SSLSession session = handshakeCompletedEvent.getSession();
            session.getCipherSuite();
            session.getProtocol();
            try {
                session.getPeerPrincipal().getName();
            }
            catch (SSLPeerUnverifiedException ex) {}
        }
    }
    
    public abstract static class b implements X509TrustManager
    {
        protected X509TrustManager a;
        
        public b() {
            this.a = c();
        }
        
        @Override
        public final void checkClientTrusted(final X509Certificate[] array, final String s) {
            this.a.checkClientTrusted(array, s);
        }
        
        @Override
        public void checkServerTrusted(final X509Certificate[] array, final String s) {
            this.a.checkServerTrusted(array, s);
        }
        
        @Override
        public final X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }
}
