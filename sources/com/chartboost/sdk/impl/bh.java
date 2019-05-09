package com.chartboost.sdk.impl;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class bh {
    /* renamed from: a */
    public static final BigInteger f3186a = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    /* renamed from: b */
    public static final BigInteger f3187b = f3186a.multiply(f3186a);
    /* renamed from: c */
    public static final BigInteger f3188c = f3186a.multiply(f3187b);
    /* renamed from: d */
    public static final BigInteger f3189d = f3186a.multiply(f3188c);
    /* renamed from: e */
    public static final BigInteger f3190e = f3186a.multiply(f3189d);
    /* renamed from: f */
    public static final BigInteger f3191f = f3186a.multiply(f3190e);
    /* renamed from: g */
    public static final BigInteger f3192g = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).multiply(BigInteger.valueOf(1152921504606846976L));
    /* renamed from: h */
    public static final BigInteger f3193h = f3186a.multiply(f3192g);
    /* renamed from: i */
    public static final File[] f3194i = new File[0];
    /* renamed from: j */
    private static final Charset f3195j = Charset.forName("UTF-8");

    /* renamed from: a */
    public static FileInputStream m3523a(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    /* renamed from: b */
    public static byte[] m3524b(File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = m3523a(file);
            byte[] a = bi.m3530a(inputStream, file.length());
            return a;
        } finally {
            bi.m3528a(inputStream);
        }
    }
}
