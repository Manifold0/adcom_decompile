// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

public final class zzayj implements zzatz
{
    private final SecretKey zzdna;
    
    public zzayj(final byte[] array) {
        this.zzdna = new SecretKeySpec(array, "AES");
    }
    
    @Override
    public final byte[] zzc(final byte[] array, final byte[] array2) throws GeneralSecurityException {
        if (array.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        final byte[] array3 = new byte[array.length + 12 + 16];
        final byte[] zzbh = zzazl.zzbh(12);
        System.arraycopy(zzbh, 0, array3, 0, 12);
        final Cipher cipher = zzayy.zzdnz.zzek("AES/GCM/NoPadding");
        cipher.init(1, this.zzdna, new GCMParameterSpec(128, zzbh));
        byte[] array4;
        if ((array4 = array2) == null) {
            array4 = new byte[0];
        }
        cipher.updateAAD(array4);
        cipher.doFinal(array, 0, array.length, array3, 12);
        return array3;
    }
}
