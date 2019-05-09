// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzauq implements zzaua<zzatz>
{
    public zzauq() {
    }
    
    @Override
    public final zzaug<zzatz> zzb(final String s, final String s2, final int n) throws GeneralSecurityException {
        final int n2 = -1;
        final String lowerCase = s2.toLowerCase();
        int n3 = 0;
        Label_0035: {
            switch (lowerCase.hashCode()) {
                case 2989895: {
                    if (lowerCase.equals("aead")) {
                        n3 = 0;
                        break Label_0035;
                    }
                    break;
                }
            }
            n3 = -1;
        }
        switch (n3) {
            default: {
                throw new GeneralSecurityException(String.format("No support for primitive '%s'.", s2));
            }
            case 0: {
                int n4 = 0;
                switch (s.hashCode()) {
                    default: {
                        n4 = n2;
                        break;
                    }
                    case 1215885937: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                            n4 = 0;
                            break;
                        }
                        break;
                    }
                    case 1797113348: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
                            n4 = 1;
                            break;
                        }
                        break;
                    }
                    case 1855890991: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                            n4 = 2;
                            break;
                        }
                        break;
                    }
                    case 360753376: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
                            n4 = 3;
                            break;
                        }
                        break;
                    }
                    case 1469984853: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                            n4 = 4;
                            break;
                        }
                        break;
                    }
                    case 2079211877: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
                            n4 = 5;
                            break;
                        }
                        break;
                    }
                }
                zzaug<zzatz> zzaug = null;
                switch (n4) {
                    default: {
                        throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", s));
                    }
                    case 0: {
                        zzaug = new zzaus();
                        break;
                    }
                    case 1: {
                        zzaug = new zzauu();
                        break;
                    }
                    case 2: {
                        zzaug = new zzauv();
                        break;
                    }
                    case 3: {
                        zzaug = new zzauw();
                        break;
                    }
                    case 4: {
                        zzaug = new zzaux();
                        break;
                    }
                    case 5: {
                        zzaug = new zzauz();
                        break;
                    }
                }
                if (zzaug.getVersion() < n) {
                    throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", s, n));
                }
                return zzaug;
            }
        }
    }
}
