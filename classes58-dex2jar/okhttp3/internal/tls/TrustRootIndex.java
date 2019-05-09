// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.tls;

import java.security.PublicKey;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.TrustAnchor;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public abstract class TrustRootIndex
{
    public static TrustRootIndex get(final X509TrustManager x509TrustManager) {
        try {
            final Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new AndroidTrustRootIndex(x509TrustManager, declaredMethod);
        }
        catch (NoSuchMethodException ex) {
            return get(x509TrustManager.getAcceptedIssuers());
        }
    }
    
    public static TrustRootIndex get(final X509Certificate... array) {
        return new BasicTrustRootIndex(array);
    }
    
    public abstract X509Certificate findByIssuerAndSignature(final X509Certificate p0);
    
    static final class AndroidTrustRootIndex extends TrustRootIndex
    {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;
        
        AndroidTrustRootIndex(final X509TrustManager trustManager, final Method findByIssuerAndSignatureMethod) {
            this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
            this.trustManager = trustManager;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof AndroidTrustRootIndex)) {
                    return false;
                }
                final AndroidTrustRootIndex androidTrustRootIndex = (AndroidTrustRootIndex)o;
                if (!this.trustManager.equals(androidTrustRootIndex.trustManager) || !this.findByIssuerAndSignatureMethod.equals(androidTrustRootIndex.findByIssuerAndSignatureMethod)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public X509Certificate findByIssuerAndSignature(X509Certificate trustedCert) {
            final X509Certificate x509Certificate = null;
            try {
                final TrustAnchor trustAnchor = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, trustedCert);
                trustedCert = x509Certificate;
                if (trustAnchor != null) {
                    trustedCert = trustAnchor.getTrustedCert();
                }
                return trustedCert;
            }
            catch (IllegalAccessException ex) {
                throw new AssertionError();
            }
            catch (InvocationTargetException ex2) {
                return null;
            }
        }
        
        @Override
        public int hashCode() {
            return this.trustManager.hashCode() + this.findByIssuerAndSignatureMethod.hashCode() * 31;
        }
    }
    
    static final class BasicTrustRootIndex extends TrustRootIndex
    {
        private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;
        
        public BasicTrustRootIndex(final X509Certificate... array) {
            this.subjectToCaCerts = new LinkedHashMap<X500Principal, Set<X509Certificate>>();
            for (int length = array.length, i = 0; i < length; ++i) {
                final X509Certificate x509Certificate = array[i];
                final X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                Set<X509Certificate> set;
                if ((set = this.subjectToCaCerts.get(subjectX500Principal)) == null) {
                    set = new LinkedHashSet<X509Certificate>(1);
                    this.subjectToCaCerts.put(subjectX500Principal, set);
                }
                set.add(x509Certificate);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            return o == this || (o instanceof BasicTrustRootIndex && ((BasicTrustRootIndex)o).subjectToCaCerts.equals(this.subjectToCaCerts));
        }
        
        @Override
        public X509Certificate findByIssuerAndSignature(final X509Certificate x509Certificate) {
            final Set<X509Certificate> set = this.subjectToCaCerts.get(x509Certificate.getIssuerX500Principal());
            if (set == null) {
                return null;
            }
            for (final X509Certificate x509Certificate2 : set) {
                final PublicKey publicKey = x509Certificate2.getPublicKey();
                try {
                    x509Certificate.verify(publicKey);
                    return x509Certificate2;
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
            return null;
        }
        
        @Override
        public int hashCode() {
            return this.subjectToCaCerts.hashCode();
        }
    }
}
