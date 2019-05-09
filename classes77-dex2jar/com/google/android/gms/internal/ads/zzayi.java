// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;
import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class zzayi implements zzatz
{
    private final SecretKeySpec zzdmu;
    private final byte[] zzdmx;
    private final byte[] zzdmy;
    private final int zzdmz;
    
    public zzayi(final byte[] array, final int zzdmz) throws GeneralSecurityException {
        if (zzdmz != 12 && zzdmz != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.zzdmz = zzdmz;
        this.zzdmu = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
        instance.init(1, this.zzdmu);
        this.zzdmx = zzl(instance.doFinal(new byte[16]));
        this.zzdmy = zzl(this.zzdmx);
    }
    
    private final byte[] zza(final Cipher cipher, int i, byte[] array, final int n, final int n2) throws IllegalBlockSizeException, BadPaddingException {
        final int n3 = 0;
        final byte[] array2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte)i };
        if (n2 == 0) {
            return cipher.doFinal(zzd(array2, this.zzdmx));
        }
        byte[] array3 = cipher.doFinal(array2);
        int j;
        for (i = 0; n2 - i > 16; i += 16) {
            for (j = 0; j < 16; ++j) {
                array3[j] ^= array[n + i + j];
            }
            array3 = cipher.doFinal(array3);
        }
        final byte[] copyOfRange = Arrays.copyOfRange(array, n + i, n + n2);
        if (copyOfRange.length == 16) {
            array = zzd(copyOfRange, this.zzdmx);
        }
        else {
            array = Arrays.copyOf(this.zzdmy, 16);
            for (i = n3; i < copyOfRange.length; ++i) {
                array[i] ^= copyOfRange[i];
            }
            array[copyOfRange.length] ^= (byte)128;
        }
        return cipher.doFinal(zzd(array3, array));
    }
    
    private static byte[] zzd(final byte[] array, final byte[] array2) {
        final int length = array.length;
        final byte[] array3 = new byte[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
        return array3;
    }
    
    private static byte[] zzl(final byte[] array) {
        final int n = 0;
        final byte[] array2 = new byte[16];
        for (int i = 0; i < 15; ++i) {
            array2[i] = (byte)(array[i] << 1 ^ (array[i + 1] & 0xFF) >>> 7);
        }
        final byte b = array[15];
        int n2;
        if ((array[0] & 0x80) == 0x0) {
            n2 = n;
        }
        else {
            n2 = 135;
        }
        array2[15] = (byte)(n2 ^ b << 1);
        return array2;
    }
    
    @Override
    public final byte[] zzc(final byte[] array, byte[] zza) throws GeneralSecurityException {
        int i = 0;
        if (array.length > Integer.MAX_VALUE - this.zzdmz - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        final byte[] array2 = new byte[this.zzdmz + array.length + 16];
        final byte[] zzbh = zzazl.zzbh(this.zzdmz);
        System.arraycopy(zzbh, 0, array2, 0, this.zzdmz);
        final Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
        instance.init(1, this.zzdmu);
        final byte[] zza2 = this.zza(instance, 0, zzbh, 0, zzbh.length);
        if (zza == null) {
            zza = new byte[0];
        }
        zza = this.zza(instance, 1, zza, 0, zza.length);
        final Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
        instance2.init(1, this.zzdmu, new IvParameterSpec(zza2));
        instance2.doFinal(array, 0, array.length, array2, this.zzdmz);
        final byte[] zza3 = this.zza(instance, 2, array2, this.zzdmz, array.length);
        final int length = array.length;
        final int zzdmz = this.zzdmz;
        while (i < 16) {
            array2[length + zzdmz + i] = (byte)(zza[i] ^ zza2[i] ^ zza3[i]);
            ++i;
        }
        return array2;
    }
}
