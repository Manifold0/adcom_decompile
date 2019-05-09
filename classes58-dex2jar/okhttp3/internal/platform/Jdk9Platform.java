// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.platform;

import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSocketFactory;
import java.lang.reflect.InvocationTargetException;
import okhttp3.Protocol;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLParameters;
import java.lang.reflect.Method;

final class Jdk9Platform extends Platform
{
    final Method getProtocolMethod;
    final Method setProtocolMethod;
    
    public Jdk9Platform(final Method setProtocolMethod, final Method getProtocolMethod) {
        this.setProtocolMethod = setProtocolMethod;
        this.getProtocolMethod = getProtocolMethod;
    }
    
    public static Jdk9Platform buildIfSupported() {
        try {
            return new Jdk9Platform(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", (Class<?>[])new Class[0]));
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    @Override
    public void configureTlsExtensions(final SSLSocket sslSocket, final String s, final List<Protocol> list) {
        try {
            final SSLParameters sslParameters = sslSocket.getSSLParameters();
            final List<String> alpnProtocolNames = Platform.alpnProtocolNames(list);
            this.setProtocolMethod.invoke(sslParameters, alpnProtocolNames.toArray(new String[alpnProtocolNames.size()]));
            sslSocket.setSSLParameters(sslParameters);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0048;
        }
    }
    
    @Override
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        try {
            String s = (String)this.getProtocolMethod.invoke(sslSocket, new Object[0]);
            if (s == null || s.equals("")) {
                s = null;
            }
            return s;
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0036;
        }
    }
    
    @Override
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }
}
