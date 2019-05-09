// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.security.Security;
import java.util.ArrayList;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import javax.crypto.KeyAgreement;
import java.security.MessageDigest;
import java.security.Signature;
import javax.crypto.Mac;
import javax.crypto.Cipher;
import java.security.Provider;
import java.util.List;
import java.util.logging.Logger;

public final class zzayy<T_WRAPPER extends zzayz<T_ENGINE>, T_ENGINE>
{
    private static final Logger logger;
    private static final List<Provider> zzdny;
    public static final zzayy<zzaza, Cipher> zzdnz;
    public static final zzayy<zzaze, Mac> zzdoa;
    private static final zzayy<zzazg, Signature> zzdob;
    private static final zzayy<zzazf, MessageDigest> zzdoc;
    public static final zzayy<zzazb, KeyAgreement> zzdod;
    public static final zzayy<zzazd, KeyPairGenerator> zzdoe;
    public static final zzayy<zzazc, KeyFactory> zzdof;
    private T_WRAPPER zzdog;
    private List<Provider> zzdoh;
    private boolean zzdoi;
    
    static {
        logger = Logger.getLogger(zzayy.class.getName());
        if (zzazp.zzaat()) {
            final ArrayList<Provider> zzdny2 = new ArrayList<Provider>();
            for (int i = 0; i < 2; ++i) {
                final String s = (new String[] { "GmsCore_OpenSSL", "AndroidOpenSSL" })[i];
                final Provider provider = Security.getProvider(s);
                if (provider != null) {
                    zzdny2.add(provider);
                }
                else {
                    zzayy.logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", s));
                }
            }
            zzdny = zzdny2;
        }
        else {
            zzdny = new ArrayList<Provider>();
        }
        zzdnz = new zzayy<zzaza, Cipher>(new zzaza());
        zzdoa = new zzayy<zzaze, Mac>(new zzaze());
        zzdob = new zzayy<zzazg, Signature>(new zzazg());
        zzdoc = new zzayy<zzazf, MessageDigest>(new zzazf());
        zzdod = new zzayy<zzazb, KeyAgreement>(new zzazb());
        zzdoe = new zzayy<zzazd, KeyPairGenerator>(new zzazd());
        zzdof = new zzayy<zzazc, KeyFactory>(new zzazc());
    }
    
    private zzayy(final T_WRAPPER zzdog) {
        this.zzdog = zzdog;
        this.zzdoh = zzayy.zzdny;
        this.zzdoi = true;
    }
    
    private final boolean zza(final String s, final Provider provider) {
        try {
            this.zzdog.zzb(s, provider);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final T_ENGINE zzek(final String s) throws GeneralSecurityException {
        for (final Provider provider : this.zzdoh) {
            if (this.zza(s, provider)) {
                return this.zzdog.zzb(s, provider);
            }
        }
        if (this.zzdoi) {
            return this.zzdog.zzb(s, null);
        }
        throw new GeneralSecurityException("No good Provider found.");
    }
}
