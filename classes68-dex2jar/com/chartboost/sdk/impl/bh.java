// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.io.File;
import java.math.BigInteger;

public class bh
{
    public static final BigInteger a;
    public static final BigInteger b;
    public static final BigInteger c;
    public static final BigInteger d;
    public static final BigInteger e;
    public static final BigInteger f;
    public static final BigInteger g;
    public static final BigInteger h;
    public static final File[] i;
    private static final Charset j;
    
    static {
        a = BigInteger.valueOf(1024L);
        b = bh.a.multiply(bh.a);
        c = bh.a.multiply(bh.b);
        d = bh.a.multiply(bh.c);
        e = bh.a.multiply(bh.d);
        f = bh.a.multiply(bh.e);
        g = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
        h = bh.a.multiply(bh.g);
        i = new File[0];
        j = Charset.forName("UTF-8");
    }
    
    public static FileInputStream a(final File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        }
        if (!file.canRead()) {
            throw new IOException("File '" + file + "' cannot be read");
        }
        return new FileInputStream(file);
    }
    
    public static byte[] b(final File file) throws IOException {
        InputStream a = null;
        try {
            return bi.a(a = a(file), file.length());
        }
        finally {
            bi.a(a);
        }
    }
}
