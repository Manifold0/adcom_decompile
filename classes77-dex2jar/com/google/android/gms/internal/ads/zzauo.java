// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.logging.Level;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public final class zzauo
{
    private static final Logger logger;
    private static final ConcurrentMap<String, zzaug> zzdhq;
    private static final ConcurrentMap<String, Boolean> zzdhr;
    private static final ConcurrentMap<String, zzaua> zzdhs;
    
    static {
        logger = Logger.getLogger(zzauo.class.getName());
        zzdhq = new ConcurrentHashMap<String, zzaug>();
        zzdhr = new ConcurrentHashMap<String, Boolean>();
        zzdhs = new ConcurrentHashMap<String, zzaua>();
    }
    
    public static <P> zzaum<P> zza(final zzauh zzauh, final zzaug<P> zzaug) throws GeneralSecurityException {
        zzaup.zzc(zzauh.zzwg());
        final zzaum<P> zzaum = new zzaum<P>();
        for (final zzaxr.zzb zzb : zzauh.zzwg().zzzl()) {
            if (zzb.zzzq() == zzaxl.zzdle) {
                final zzaun<P> zza = zzaum.zza(zza(zzb.zzzp().zzyw(), zzb.zzzp().zzyx()), zzb);
                if (zzb.zzzr() != zzauh.zzwg().zzzk()) {
                    continue;
                }
                zzaum.zza(zza);
            }
        }
        return zzaum;
    }
    
    public static <P> zzaxi zza(final zzaxn zzaxn) throws GeneralSecurityException {
        final zzaug<Object> zzdz = zzdz(zzaxn.zzyw());
        if (zzauo.zzdhr.get(zzaxn.zzyw())) {
            return zzdz.zzc(zzaxn.zzyx());
        }
        final String value = String.valueOf(zzaxn.zzyw());
        String concat;
        if (value.length() != 0) {
            concat = "newKey-operation not permitted for key type ".concat(value);
        }
        else {
            concat = new String("newKey-operation not permitted for key type ");
        }
        throw new GeneralSecurityException(concat);
    }
    
    public static <P> zzbcu zza(String s, final zzbcu zzbcu) throws GeneralSecurityException {
        final zzaug<Object> zzdz = zzdz(s);
        if (zzauo.zzdhr.get(s)) {
            return zzdz.zzb(zzbcu);
        }
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = "newKey-operation not permitted for key type ".concat(s);
        }
        else {
            s = new String("newKey-operation not permitted for key type ");
        }
        throw new GeneralSecurityException(s);
    }
    
    private static <P> P zza(final String s, final zzbah zzbah) throws GeneralSecurityException {
        return zzdz(s).zza(zzbah);
    }
    
    public static <P> P zza(final String s, final byte[] array) throws GeneralSecurityException {
        return zza(s, zzbah.zzo(array));
    }
    
    public static <P> void zza(final String s, zzaua<P> s2) throws GeneralSecurityException {
        while (true) {
            while (true) {
                synchronized (zzauo.class) {
                    if (!zzauo.zzdhs.containsKey(s.toLowerCase())) {
                        break;
                    }
                    Object logger = zzauo.zzdhs.get(s.toLowerCase());
                    if (s2.getClass().equals(((Logger)logger).getClass())) {
                        break;
                    }
                    logger = zzauo.logger;
                    final Level warning = Level.WARNING;
                    s2 = String.valueOf(s);
                    if (s2.length() != 0) {
                        s2 = "Attempted overwrite of a catalogueName catalogue for name ".concat(s2);
                        ((Logger)logger).logp(warning, "com.google.crypto.tink.Registry", "addCatalogue", s2);
                        throw new GeneralSecurityException(new StringBuilder(String.valueOf(s).length() + 47).append("catalogue for name ").append(s).append(" has been already registered").toString());
                    }
                }
                s2 = new String("Attempted overwrite of a catalogueName catalogue for name ");
                continue;
            }
        }
        zzauo.zzdhs.put(s.toLowerCase(), (zzaua)s2);
    }
    // monitorexit(zzauo.class)
    
    public static <P> void zza(final String s, final zzaug<P> zzaug) throws GeneralSecurityException {
        zza(s, zzaug, true);
    }
    
    public static <P> void zza(final String s, final zzaug<P> zzaug, final boolean b) throws GeneralSecurityException {
        // monitorenter(zzauo.class)
        if (zzaug == null) {
            try {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            finally {
            }
            // monitorexit(zzauo.class)
        }
        if (zzauo.zzdhq.containsKey(s)) {
            final zzaug<Object> zzdz = zzdz(s);
            final boolean booleanValue = zzauo.zzdhr.get(s);
            if (!zzaug.getClass().equals(zzdz.getClass()) || (!booleanValue && b)) {
                final Logger logger = zzauo.logger;
                final Level warning = Level.WARNING;
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Attempted overwrite of a registered key manager for key type ".concat(value);
                }
                else {
                    concat = new String("Attempted overwrite of a registered key manager for key type ");
                }
                logger.logp(warning, "com.google.crypto.tink.Registry", "registerKeyManager", concat);
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", s, zzdz.getClass().getName(), zzaug.getClass().getName()));
            }
        }
        zzauo.zzdhq.put(s, zzaug);
        zzauo.zzdhr.put(s, b);
    }
    // monitorexit(zzauo.class)
    
    public static <P> zzbcu zzb(final zzaxn zzaxn) throws GeneralSecurityException {
        final zzaug<Object> zzdz = zzdz(zzaxn.zzyw());
        if (zzauo.zzdhr.get(zzaxn.zzyw())) {
            return zzdz.zzb(zzaxn.zzyx());
        }
        final String value = String.valueOf(zzaxn.zzyw());
        String concat;
        if (value.length() != 0) {
            concat = "newKey-operation not permitted for key type ".concat(value);
        }
        else {
            concat = new String("newKey-operation not permitted for key type ");
        }
        throw new GeneralSecurityException(concat);
    }
    
    public static <P> P zzb(final String s, final zzbcu zzbcu) throws GeneralSecurityException {
        return zzdz(s).zza(zzbcu);
    }
    
    public static <P> zzaua<P> zzdy(final String s) throws GeneralSecurityException {
        if (s == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        final zzaua<P> zzaua = zzauo.zzdhs.get(s.toLowerCase());
        if (zzaua == null) {
            String s3;
            final String s2 = s3 = String.format("no catalogue found for %s. ", s);
            if (s.toLowerCase().startsWith("tinkaead")) {
                s3 = String.valueOf(s2).concat("Maybe call AeadConfig.init().");
            }
            String s4;
            if (s.toLowerCase().startsWith("tinkdeterministicaead")) {
                s4 = String.valueOf(s3).concat("Maybe call DeterministicAeadConfig.init().");
            }
            else if (s.toLowerCase().startsWith("tinkstreamingaead")) {
                s4 = String.valueOf(s3).concat("Maybe call StreamingAeadConfig.init().");
            }
            else if (s.toLowerCase().startsWith("tinkhybriddecrypt") || s.toLowerCase().startsWith("tinkhybridencrypt")) {
                s4 = String.valueOf(s3).concat("Maybe call HybridConfig.init().");
            }
            else if (s.toLowerCase().startsWith("tinkmac")) {
                s4 = String.valueOf(s3).concat("Maybe call MacConfig.init().");
            }
            else if (s.toLowerCase().startsWith("tinkpublickeysign") || s.toLowerCase().startsWith("tinkpublickeyverify")) {
                s4 = String.valueOf(s3).concat("Maybe call SignatureConfig.init().");
            }
            else {
                s4 = s3;
                if (s.toLowerCase().startsWith("tink")) {
                    s4 = String.valueOf(s3).concat("Maybe call TinkConfig.init().");
                }
            }
            throw new GeneralSecurityException(s4);
        }
        return zzaua;
    }
    
    private static <P> zzaug<P> zzdz(final String s) throws GeneralSecurityException {
        final zzaug<P> zzaug = zzauo.zzdhq.get(s);
        if (zzaug == null) {
            throw new GeneralSecurityException(new StringBuilder(String.valueOf(s).length() + 78).append("No key manager found for key type: ").append(s).append(".  Check the configuration of the registry.").toString());
        }
        return zzaug;
    }
}
