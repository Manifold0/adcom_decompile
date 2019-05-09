// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.NoSuchAlgorithmException;
import java.security.GeneralSecurityException;

final class zzavh
{
    public static zzayv zza(final zzawy zzawy) throws GeneralSecurityException {
        switch (zzavi.zzdia[zzawy.ordinal()]) {
            default: {
                final String value = String.valueOf(zzawy);
                throw new GeneralSecurityException(new StringBuilder(String.valueOf(value).length() + 20).append("unknown curve type: ").append(value).toString());
            }
            case 1: {
                return zzayv.zzdno;
            }
            case 2: {
                return zzayv.zzdnp;
            }
            case 3: {
                return zzayv.zzdnq;
            }
        }
    }
    
    public static zzayw zza(final zzawk zzawk) throws GeneralSecurityException {
        switch (zzavi.zzdib[zzawk.ordinal()]) {
            default: {
                final String value = String.valueOf(zzawk);
                throw new GeneralSecurityException(new StringBuilder(String.valueOf(value).length() + 22).append("unknown point format: ").append(value).toString());
            }
            case 1: {
                return zzayw.zzdns;
            }
            case 2: {
                return zzayw.zzdnt;
            }
        }
    }
    
    public static String zza(final zzaxa zzaxa) throws NoSuchAlgorithmException {
        switch (zzavi.zzdhz[zzaxa.ordinal()]) {
            default: {
                final String value = String.valueOf(zzaxa);
                throw new NoSuchAlgorithmException(new StringBuilder(String.valueOf(value).length() + 27).append("hash unsupported for HMAC: ").append(value).toString());
            }
            case 1: {
                return "HmacSha1";
            }
            case 2: {
                return "HmacSha256";
            }
            case 3: {
                return "HmacSha512";
            }
        }
    }
    
    public static void zza(final zzawq zzawq) throws GeneralSecurityException {
        zzayt.zza(zza(zzawq.zzxu().zzyh()));
        zza(zzawq.zzxu().zzyi());
        if (zzawq.zzxw() == zzawk.zzdjc) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzauo.zza(zzawq.zzxv().zzxp());
    }
}
