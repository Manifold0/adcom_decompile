// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.security.spec.ECParameterSpec;
import java.security.KeyPair;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public final class zzayr
{
    private ECPublicKey zzdnj;
    
    public zzayr(final ECPublicKey zzdnj) {
        this.zzdnj = zzdnj;
    }
    
    public final zzays zza(String value, byte[] array, final byte[] array2, final int n, final zzayw zzayw) throws GeneralSecurityException {
        final KeyPair zza = zzayt.zza(this.zzdnj.getParams());
        final ECPublicKey ecPublicKey = (ECPublicKey)zza.getPublic();
        final ECPrivateKey ecPrivateKey = (ECPrivateKey)zza.getPrivate();
        final ECPublicKey zzdnj = this.zzdnj;
        final ECParameterSpec params = zzdnj.getParams();
        final ECParameterSpec params2 = ecPrivateKey.getParams();
        if (!params.getCurve().equals(params2.getCurve()) || !params.getGenerator().equals(params2.getGenerator()) || !params.getOrder().equals(params2.getOrder()) || params.getCofactor() != params2.getCofactor()) {
            throw new GeneralSecurityException("invalid public key spec");
        }
        final byte[] zza2 = zzayt.zza(ecPrivateKey, zzdnj.getW());
        final EllipticCurve curve = ecPublicKey.getParams().getCurve();
        final ECPoint w = ecPublicKey.getW();
        zzayt.zza(w, curve);
        final int zzb = zzayt.zzb(curve);
        byte[] array3 = null;
        switch (zzayu.zzdnm[zzayw.ordinal()]) {
            default: {
                value = String.valueOf(zzayw);
                throw new GeneralSecurityException(new StringBuilder(String.valueOf(value).length() + 15).append("invalid format:").append(value).toString());
            }
            case 1: {
                array3 = new byte[zzb * 2 + 1];
                final byte[] byteArray = w.getAffineX().toByteArray();
                final byte[] byteArray2 = w.getAffineY().toByteArray();
                System.arraycopy(byteArray2, 0, array3, zzb * 2 + 1 - byteArray2.length, byteArray2.length);
                System.arraycopy(byteArray, 0, array3, zzb + 1 - byteArray.length, byteArray.length);
                array3[0] = 4;
                break;
            }
            case 2: {
                array3 = new byte[zzb + 1];
                final byte[] byteArray3 = w.getAffineX().toByteArray();
                System.arraycopy(byteArray3, 0, array3, zzb + 1 - byteArray3.length, byteArray3.length);
                int n2;
                if (w.getAffineY().testBit(0)) {
                    n2 = 3;
                }
                else {
                    n2 = 2;
                }
                array3[0] = (byte)n2;
                break;
            }
        }
        final byte[] zza3 = zzayk.zza(new byte[][] { array3, zza2 });
        final Mac mac = zzayy.zzdoa.zzek(value);
        if (n > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        if (array == null || array.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], value));
        }
        else {
            mac.init(new SecretKeySpec(array, value));
        }
        final byte[] doFinal = mac.doFinal(zza3);
        array = new byte[n];
        mac.init(new SecretKeySpec(doFinal, value));
        byte[] doFinal2 = new byte[0];
        int n3 = 1;
        int n4 = 0;
        while (true) {
            mac.update(doFinal2);
            mac.update(array2);
            mac.update((byte)n3);
            doFinal2 = mac.doFinal();
            if (doFinal2.length + n4 >= n) {
                break;
            }
            System.arraycopy(doFinal2, 0, array, n4, doFinal2.length);
            n4 += doFinal2.length;
            ++n3;
        }
        System.arraycopy(doFinal2, 0, array, n4, n - n4);
        return new zzays(array3, array);
    }
}
