// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavd implements zzaua<zzaue>
{
    public zzavd() {
    }
    
    @Override
    public final zzaug<zzaue> zzb(final String s, final String s2, final int n) throws GeneralSecurityException {
        final int n2 = -1;
        final String lowerCase = s2.toLowerCase();
        int n3 = 0;
        Label_0035: {
            switch (lowerCase.hashCode()) {
                case 275448849: {
                    if (lowerCase.equals("hybriddecrypt")) {
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
                    case -80133005: {
                        n4 = n2;
                        if (s.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
                            n4 = 0;
                            break;
                        }
                        break;
                    }
                }
                switch (n4) {
                    default: {
                        throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", s));
                    }
                    case 0: {
                        final zzava zzava = new zzava();
                        if (n > 0) {
                            throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", s, n));
                        }
                        return zzava;
                    }
                }
                break;
            }
        }
    }
}
