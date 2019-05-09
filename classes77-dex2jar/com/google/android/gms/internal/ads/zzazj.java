// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import javax.crypto.Mac;

public final class zzazj implements zzauk
{
    private Mac zzdoj;
    private final int zzdok;
    private final String zzdol;
    private final Key zzdom;
    
    public zzazj(String zzdol, final Key zzdom, final int zzdok) throws GeneralSecurityException {
        if (zzdok < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        switch (zzdol) {
            default: {
                zzdol = String.valueOf(zzdol);
                if (zzdol.length() != 0) {
                    zzdol = "unknown Hmac algorithm: ".concat(zzdol);
                }
                else {
                    zzdol = new String("unknown Hmac algorithm: ");
                }
                throw new NoSuchAlgorithmException(zzdol);
            }
            case "HMACSHA1": {
                if (zzdok > 20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
            case "HMACSHA256": {
                if (zzdok > 32) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
            case "HMACSHA512": {
                if (zzdok > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            }
        }
        this.zzdol = zzdol;
        this.zzdok = zzdok;
        this.zzdom = zzdom;
        (this.zzdoj = zzayy.zzdoa.zzek(zzdol)).init(zzdom);
    }
    
    @Override
    public final byte[] zzg(byte[] array) throws GeneralSecurityException {
        while (true) {
            try {
                final Mac mac = (Mac)this.zzdoj.clone();
                mac.update(array);
                array = new byte[this.zzdok];
                System.arraycopy(mac.doFinal(), 0, array, 0, this.zzdok);
                return array;
            }
            catch (CloneNotSupportedException ex) {
                final Mac mac = zzayy.zzdoa.zzek(this.zzdol);
                mac.init(this.zzdom);
                continue;
            }
            break;
        }
    }
}
