// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class zzayh implements zzazi
{
    private final SecretKeySpec zzdmu;
    private final int zzdmv;
    private final int zzdmw;
    
    public zzayh(final byte[] array, final int zzdmv) throws GeneralSecurityException {
        this.zzdmu = new SecretKeySpec(array, "AES");
        this.zzdmw = zzayy.zzdnz.zzek("AES/CTR/NoPadding").getBlockSize();
        if (zzdmv < 12 || zzdmv > this.zzdmw) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzdmv = zzdmv;
    }
    
    @Override
    public final byte[] zzk(final byte[] array) throws GeneralSecurityException {
        if (array.length > Integer.MAX_VALUE - this.zzdmv) {
            throw new GeneralSecurityException(new StringBuilder(43).append("plaintext length can not exceed ").append(Integer.MAX_VALUE - this.zzdmv).toString());
        }
        final byte[] array2 = new byte[this.zzdmv + array.length];
        final byte[] zzbh = zzazl.zzbh(this.zzdmv);
        System.arraycopy(zzbh, 0, array2, 0, this.zzdmv);
        final int length = array.length;
        final int zzdmv = this.zzdmv;
        final Cipher cipher = zzayy.zzdnz.zzek("AES/CTR/NoPadding");
        final byte[] array3 = new byte[this.zzdmw];
        System.arraycopy(zzbh, 0, array3, 0, this.zzdmv);
        cipher.init(1, this.zzdmu, new IvParameterSpec(array3));
        if (cipher.doFinal(array, 0, length, array2, zzdmv) != length) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
        return array2;
    }
}
