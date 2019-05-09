// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeUnit;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Vector;
import java.security.GeneralSecurityException;
import java.util.concurrent.CountDownLatch;
import java.security.MessageDigest;

final class zzbk
{
    private static boolean zzhy;
    private static MessageDigest zzhz;
    private static final Object zzia;
    private static final Object zzib;
    static CountDownLatch zzic;
    
    static {
        zzbk.zzhy = false;
        zzbk.zzhz = null;
        zzia = new Object();
        zzib = new Object();
        zzbk.zzic = new CountDownLatch(1);
    }
    
    static String zza(final zzba zzba, final String s) throws GeneralSecurityException, UnsupportedEncodingException {
        final byte[] zzb = zzbfi.zzb((zzbfi)zzba);
        byte[] array;
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbay)) {
            if (zzde.zzso == null) {
                throw new GeneralSecurityException();
            }
            byte[] bytes;
            if (s != null) {
                bytes = s.getBytes();
            }
            else {
                bytes = new byte[0];
            }
            final byte[] zzc = zzde.zzso.zzc(zzb, bytes);
            final zzbg zzbg = new zzbg();
            zzbg.zzgv = new byte[][] { zzc };
            zzbg.zzfe = 2;
            array = zzbfi.zzb((zzbfi)zzbg);
        }
        else {
            final Vector<byte[]> zza = zza(zzb, 255);
            if (zza == null || zza.size() == 0) {
                array = zza(zzbfi.zzb((zzbfi)zzc(4096L)), s, true);
            }
            else {
                final zzbg zzbg2 = new zzbg();
                zzbg2.zzgv = new byte[zza.size()][];
                final Iterator<byte[]> iterator = zza.iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    zzbg2.zzgv[n] = zza(iterator.next(), s, false);
                    ++n;
                }
                zzbg2.zzgq = zzb(zzb);
                array = zzbfi.zzb((zzbfi)zzbg2);
            }
        }
        return zzbi.zza(array, true);
    }
    
    private static Vector<byte[]> zza(final byte[] array, int n) {
        if (array == null || array.length <= 0) {
            return null;
        }
        final int n2 = (array.length + 255 - 1) / 255;
        final Vector<byte[]> vector = new Vector<byte[]>();
        n = 0;
        while (true) {
            if (n >= n2) {
                return vector;
            }
            final int n3 = n * 255;
            try {
                int length;
                if (array.length - n3 > 255) {
                    length = n3 + 255;
                }
                else {
                    length = array.length;
                }
                vector.add(Arrays.copyOfRange(array, n3, length));
                ++n;
                continue;
            }
            catch (IndexOutOfBoundsException ex) {
                return null;
            }
        }
    }
    
    private static byte[] zza(byte[] array, final String s, final boolean b) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        int n;
        if (b) {
            n = 239;
        }
        else {
            n = 255;
        }
        byte[] zzb = array;
        if (array.length > n) {
            zzb = zzbfi.zzb((zzbfi)zzc(4096L));
        }
        if (zzb.length < n) {
            array = new byte[n - zzb.length];
            new SecureRandom().nextBytes(array);
            array = ByteBuffer.allocate(n + 1).put((byte)zzb.length).put(zzb).put(array).array();
        }
        else {
            array = ByteBuffer.allocate(n + 1).put((byte)zzb.length).put(zzb).array();
        }
        byte[] array2 = array;
        if (b) {
            array2 = ByteBuffer.allocate(256).put(zzb(array)).put(array).array();
        }
        final byte[] array3 = new byte[256];
        final zzbp[] zzpq = new zzbn().zzpq;
        for (int length = zzpq.length, i = 0; i < length; ++i) {
            zzpq[i].zza(array2, array3);
        }
        if (s != null && s.length() > 0) {
            String substring = s;
            if (s.length() > 32) {
                substring = s.substring(0, 32);
            }
            new zzazx(substring.getBytes("UTF-8")).zzn(array3);
        }
        return array3;
    }
    
    public static byte[] zzb(byte[] digest) throws NoSuchAlgorithmException {
        final MessageDigest zzw;
        synchronized (zzbk.zzia) {
            zzw = zzw();
            if (zzw == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        zzw.reset();
        final byte[] array;
        zzw.update(array);
        digest = zzbk.zzhz.digest();
        // monitorexit(o)
        return digest;
    }
    
    private static zzba zzc(final long n) {
        final zzba zzba = new zzba();
        zzba.zzdu = 4096L;
        return zzba;
    }
    
    static void zzv() {
        synchronized (zzbk.zzib) {
            if (!zzbk.zzhy) {
                zzbk.zzhy = true;
                new Thread(new zzbm(null)).start();
            }
        }
    }
    
    private static MessageDigest zzw() {
        zzv();
        boolean await = false;
        while (true) {
            try {
                await = zzbk.zzic.await(2L, TimeUnit.SECONDS);
                if (await && zzbk.zzhz != null) {
                    return zzbk.zzhz;
                }
                return null;
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
}
