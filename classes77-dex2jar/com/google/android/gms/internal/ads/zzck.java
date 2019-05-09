// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public final class zzck
{
    private static Cipher zzrf;
    private static final Object zzrg;
    private static final Object zzrh;
    private final SecureRandom zzre;
    
    static {
        zzck.zzrf = null;
        zzrg = new Object();
        zzrh = new Object();
    }
    
    public zzck(final SecureRandom secureRandom) {
        this.zzre = null;
    }
    
    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        synchronized (zzck.zzrh) {
            if (zzck.zzrf == null) {
                zzck.zzrf = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            return zzck.zzrf;
        }
    }
    
    public final byte[] zza(final byte[] array, final String s) throws zzcl {
        if (array.length != 16) {
            throw new zzcl(this);
        }
        try {
            if (zzbi.zza(s, false).length <= 16) {
                throw new zzcl(this);
            }
            goto Label_0049;
        }
        catch (NoSuchAlgorithmException ex) {
            throw new zzcl(this, ex);
        }
        catch (InvalidKeyException ex2) {
            throw new zzcl(this, ex2);
        }
        catch (IllegalBlockSizeException ex3) {
            throw new zzcl(this, ex3);
        }
        catch (NoSuchPaddingException ex4) {
            throw new zzcl(this, ex4);
        }
        catch (BadPaddingException ex5) {
            throw new zzcl(this, ex5);
        }
        catch (InvalidAlgorithmParameterException ex6) {
            throw new zzcl(this, ex6);
        }
        catch (IllegalArgumentException ex7) {
            throw new zzcl(this, ex7);
        }
        try {
            final SecretKeySpec secretKeySpec;
            final byte[] array2;
            getCipher().init(2, secretKeySpec, new IvParameterSpec(array2));
            final byte[] array3;
            return getCipher().doFinal(array3);
        }
        finally {}
    }
    
    public final String zzb(byte[] array, byte[] doFinal) throws zzcl {
        if (array.length != 16) {
            throw new zzcl(this);
        }
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
            array = (byte[])zzck.zzrg;
            synchronized (array) {
                getCipher().init(1, secretKeySpec, (SecureRandom)null);
                doFinal = getCipher().doFinal(doFinal);
                final byte[] iv = getCipher().getIV();
                // monitorexit(array)
                final int n = doFinal.length + iv.length;
                array = (byte[])(Object)ByteBuffer.allocate(n);
                ((ByteBuffer)(Object)array).put(iv).put(doFinal);
                ((Buffer)(Object)array).flip();
                doFinal = new byte[n];
                ((ByteBuffer)(Object)array).get(doFinal);
                array = (byte[])(Object)zzbi.zza(doFinal, false);
                return (String)(Object)array;
            }
        }
        catch (NoSuchAlgorithmException ex) {
            throw new zzcl(this, ex);
        }
        catch (InvalidKeyException ex2) {
            throw new zzcl(this, ex2);
        }
        catch (IllegalBlockSizeException ex3) {
            throw new zzcl(this, ex3);
        }
        catch (NoSuchPaddingException ex4) {
            throw new zzcl(this, ex4);
        }
        catch (BadPaddingException ex5) {
            throw new zzcl(this, ex5);
        }
    }
    
    public final byte[] zzl(final String s) throws zzcl {
        int i = 0;
        byte[] zza;
        try {
            zza = zzbi.zza(s, false);
            if (zza.length != 32) {
                throw new zzcl(this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw new zzcl(this, ex);
        }
        final ByteBuffer wrap = ByteBuffer.wrap(zza, 4, 16);
        final byte[] array = new byte[16];
        wrap.get(array);
        while (i < 16) {
            array[i] ^= 0x44;
            ++i;
        }
        return array;
    }
}
