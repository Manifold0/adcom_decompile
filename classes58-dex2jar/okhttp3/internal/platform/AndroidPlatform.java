// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.platform;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.Certificate;
import java.lang.reflect.Method;
import javax.net.ssl.SSLSocketFactory;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import okhttp3.internal.Util;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import okhttp3.Protocol;
import java.util.List;
import javax.net.ssl.SSLSocket;
import java.security.cert.X509Certificate;
import okhttp3.internal.tls.CertificateChainCleaner;
import javax.net.ssl.X509TrustManager;
import java.net.Socket;

class AndroidPlatform extends Platform
{
    private static final int MAX_LOG_LENGTH = 4000;
    private final CloseGuard closeGuard;
    private final OptionalMethod<Socket> getAlpnSelectedProtocol;
    private final OptionalMethod<Socket> setAlpnProtocols;
    private final OptionalMethod<Socket> setHostname;
    private final OptionalMethod<Socket> setUseSessionTickets;
    private final Class<?> sslParametersClass;
    
    public AndroidPlatform(final Class<?> sslParametersClass, final OptionalMethod<Socket> setUseSessionTickets, final OptionalMethod<Socket> setHostname, final OptionalMethod<Socket> getAlpnSelectedProtocol, final OptionalMethod<Socket> setAlpnProtocols) {
        this.closeGuard = CloseGuard.get();
        this.sslParametersClass = sslParametersClass;
        this.setUseSessionTickets = setUseSessionTickets;
        this.setHostname = setHostname;
        this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
        this.setAlpnProtocols = setAlpnProtocols;
    }
    
    public static Platform buildIfSupported() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     5: astore_1       
        //     6: new             Lokhttp3/internal/platform/OptionalMethod;
        //     9: dup            
        //    10: aconst_null    
        //    11: ldc             "setUseSessionTickets"
        //    13: iconst_1       
        //    14: anewarray       Ljava/lang/Class;
        //    17: dup            
        //    18: iconst_0       
        //    19: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    22: aastore        
        //    23: invokespecial   okhttp3/internal/platform/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    26: astore          4
        //    28: new             Lokhttp3/internal/platform/OptionalMethod;
        //    31: dup            
        //    32: aconst_null    
        //    33: ldc             "setHostname"
        //    35: iconst_1       
        //    36: anewarray       Ljava/lang/Class;
        //    39: dup            
        //    40: iconst_0       
        //    41: ldc             Ljava/lang/String;.class
        //    43: aastore        
        //    44: invokespecial   okhttp3/internal/platform/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    47: astore          5
        //    49: aconst_null    
        //    50: astore_3       
        //    51: aconst_null    
        //    52: astore_2       
        //    53: ldc             "android.net.Network"
        //    55: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    58: pop            
        //    59: new             Lokhttp3/internal/platform/OptionalMethod;
        //    62: dup            
        //    63: ldc             [B.class
        //    65: ldc             "getAlpnSelectedProtocol"
        //    67: iconst_0       
        //    68: anewarray       Ljava/lang/Class;
        //    71: invokespecial   okhttp3/internal/platform/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    74: astore_0       
        //    75: new             Lokhttp3/internal/platform/OptionalMethod;
        //    78: dup            
        //    79: aconst_null    
        //    80: ldc             "setAlpnProtocols"
        //    82: iconst_1       
        //    83: anewarray       Ljava/lang/Class;
        //    86: dup            
        //    87: iconst_0       
        //    88: ldc             [B.class
        //    90: aastore        
        //    91: invokespecial   okhttp3/internal/platform/OptionalMethod.<init>:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
        //    94: astore_3       
        //    95: aload_3        
        //    96: astore_2       
        //    97: new             Lokhttp3/internal/platform/AndroidPlatform;
        //   100: dup            
        //   101: aload_1        
        //   102: aload           4
        //   104: aload           5
        //   106: aload_0        
        //   107: aload_2        
        //   108: invokespecial   okhttp3/internal/platform/AndroidPlatform.<init>:(Ljava/lang/Class;Lokhttp3/internal/platform/OptionalMethod;Lokhttp3/internal/platform/OptionalMethod;Lokhttp3/internal/platform/OptionalMethod;Lokhttp3/internal/platform/OptionalMethod;)V
        //   111: areturn        
        //   112: astore_0       
        //   113: ldc             "org.apache.harmony.xnet.provider.jsse.SSLParametersImpl"
        //   115: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   118: astore_1       
        //   119: goto            6
        //   122: astore_0       
        //   123: aconst_null    
        //   124: areturn        
        //   125: astore_0       
        //   126: aload_3        
        //   127: astore_0       
        //   128: goto            97
        //   131: astore_3       
        //   132: goto            97
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  0      6      112    122    Ljava/lang/ClassNotFoundException;
        //  6      49     122    125    Ljava/lang/ClassNotFoundException;
        //  53     75     125    131    Ljava/lang/ClassNotFoundException;
        //  75     95     131    135    Ljava/lang/ClassNotFoundException;
        //  97     112    122    125    Ljava/lang/ClassNotFoundException;
        //  113    119    122    125    Ljava/lang/ClassNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 79, Size: 79
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    public CertificateChainCleaner buildCertificateChainCleaner(final X509TrustManager x509TrustManager) {
        try {
            final Class<?> forName = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new AndroidCertificateChainCleaner(forName.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), forName.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        }
        catch (Exception ex) {
            return super.buildCertificateChainCleaner(x509TrustManager);
        }
    }
    
    @Override
    public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
        if (s != null) {
            this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
            this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, s);
        }
        if (this.setAlpnProtocols != null && this.setAlpnProtocols.isSupported(sslSocket)) {
            this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, Platform.concatLengthPrefixed(list));
        }
    }
    
    @Override
    public void connectSocket(final Socket socket, final InetSocketAddress inetSocketAddress, final int n) throws IOException {
        try {
            socket.connect(inetSocketAddress, n);
        }
        catch (AssertionError assertionError) {
            if (Util.isAndroidGetsocknameError(assertionError)) {
                throw new IOException(assertionError);
            }
            throw assertionError;
        }
        catch (SecurityException ex2) {
            final IOException ex = new IOException("Exception in connect");
            ex.initCause(ex2);
            throw ex;
        }
    }
    
    @Override
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        if (this.getAlpnSelectedProtocol != null && this.getAlpnSelectedProtocol.isSupported(sslSocket)) {
            final byte[] array = (byte[])this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sslSocket, new Object[0]);
            String s;
            if (array != null) {
                s = new String(array, Util.UTF_8);
            }
            else {
                s = null;
            }
            return s;
        }
        return null;
    }
    
    @Override
    public Object getStackTraceForCloseable(final String s) {
        return this.closeGuard.createAndOpen(s);
    }
    
    @Override
    public boolean isCleartextTrafficPermitted(final String s) {
        try {
            final Class<?> forName = Class.forName("android.security.NetworkSecurityPolicy");
            return (boolean)forName.getMethod("isCleartextTrafficPermitted", String.class).invoke(forName.getMethod("getInstance", (Class<?>[])new Class[0]).invoke(null, new Object[0]), s);
        }
        catch (ClassNotFoundException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (IllegalArgumentException ex3) {
            goto Label_0071;
        }
        catch (InvocationTargetException ex4) {
            goto Label_0071;
        }
        catch (NoSuchMethodException ex5) {
            goto Label_0064;
        }
    }
    
    @Override
    public void log(int i, final String s, final Throwable t) {
        int n = 5;
        if (i != 5) {
            n = 3;
        }
        String string = s;
        if (t != null) {
            string = s + '\n' + Log.getStackTraceString(t);
        }
        i = 0;
        int min;
        for (int length = string.length(); i < length; i = min + 1) {
            int index = string.indexOf(10, i);
            if (index == -1) {
                index = length;
            }
            do {
                min = Math.min(index, i + 4000);
                Log.println(n, "OkHttp", string.substring(i, min));
            } while ((i = min) < index);
        }
    }
    
    @Override
    public void logCloseableLeak(final String s, final Object o) {
        if (!this.closeGuard.warnIfOpen(o)) {
            this.log(5, s, null);
        }
    }
    
    @Override
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        while (true) {
            Object o;
            if ((o = Platform.readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters")) == null) {
                try {
                    o = Platform.readFieldOrNull(sslSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader()), "sslParameters");
                    sslSocketFactory = (SSLSocketFactory)Platform.readFieldOrNull(o, X509TrustManager.class, "x509TrustManager");
                    if (sslSocketFactory != null) {
                        return (X509TrustManager)sslSocketFactory;
                    }
                }
                catch (ClassNotFoundException ex) {
                    return super.trustManager(sslSocketFactory);
                }
                return Platform.readFieldOrNull(o, X509TrustManager.class, "trustManager");
            }
            continue;
        }
    }
    
    static final class AndroidCertificateChainCleaner extends CertificateChainCleaner
    {
        private final Method checkServerTrusted;
        private final Object x509TrustManagerExtensions;
        
        AndroidCertificateChainCleaner(final Object x509TrustManagerExtensions, final Method checkServerTrusted) {
            this.x509TrustManagerExtensions = x509TrustManagerExtensions;
            this.checkServerTrusted = checkServerTrusted;
        }
        
        @Override
        public List<Certificate> clean(final List<Certificate> list, final String s) throws SSLPeerUnverifiedException {
            try {
                return (List<Certificate>)this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, list.toArray(new X509Certificate[list.size()]), "RSA", s);
            }
            catch (InvocationTargetException ex2) {
                final SSLPeerUnverifiedException ex = new SSLPeerUnverifiedException(ex2.getMessage());
                ex.initCause(ex2);
                throw ex;
            }
            catch (IllegalAccessException ex3) {
                throw new AssertionError((Object)ex3);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof AndroidCertificateChainCleaner;
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
    
    static final class CloseGuard
    {
        private final Method getMethod;
        private final Method openMethod;
        private final Method warnIfOpenMethod;
        
        CloseGuard(final Method getMethod, final Method openMethod, final Method warnIfOpenMethod) {
            this.getMethod = getMethod;
            this.openMethod = openMethod;
            this.warnIfOpenMethod = warnIfOpenMethod;
        }
        
        static CloseGuard get() {
            try {
                final Class<?> forName = Class.forName("dalvik.system.CloseGuard");
                final Method method = forName.getMethod("get", (Class<?>[])new Class[0]);
                final Method method2 = forName.getMethod("open", String.class);
                final Method method3 = forName.getMethod("warnIfOpen", (Class<?>[])new Class[0]);
                return new CloseGuard(method, method2, method3);
            }
            catch (Exception ex) {
                final Method method = null;
                final Method method2 = null;
                final Method method3 = null;
                return new CloseGuard(method, method2, method3);
            }
        }
        
        Object createAndOpen(final String s) {
            if (this.getMethod != null) {
                try {
                    final Object invoke = this.getMethod.invoke(null, new Object[0]);
                    this.openMethod.invoke(invoke, s);
                    return invoke;
                }
                catch (Exception ex) {}
            }
            return null;
        }
        
        boolean warnIfOpen(final Object o) {
            boolean b = false;
            if (o == null) {
                return b;
            }
            try {
                this.warnIfOpenMethod.invoke(o, new Object[0]);
                b = true;
                return b;
            }
            catch (Exception ex) {
                return false;
            }
        }
    }
}
