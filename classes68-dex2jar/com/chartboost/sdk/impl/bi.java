// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.File;

public class bi
{
    public static final char a;
    public static final String b;
    
    static {
        a = File.separatorChar;
        final bk bk = new bk(4);
        final PrintWriter printWriter = new PrintWriter(bk);
        printWriter.println();
        b = bk.toString();
        printWriter.close();
    }
    
    public static int a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final long b = b(inputStream, outputStream);
        if (b > 2147483647L) {
            return -1;
        }
        return (int)b;
    }
    
    public static long a(final InputStream inputStream, final OutputStream outputStream, final byte[] array) throws IOException {
        long n = 0L;
        while (true) {
            final int read = inputStream.read(array);
            if (-1 == read) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    public static void a(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static void a(final InputStream inputStream) {
        a((Closeable)inputStream);
    }
    
    public static byte[] a(final InputStream inputStream, final int n) throws IOException {
        int i = 0;
        if (n < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + n);
        }
        byte[] array;
        if (n == 0) {
            array = new byte[0];
        }
        else {
            final byte[] array2 = new byte[n];
            while (i < n) {
                final int read = inputStream.read(array2, i, n - i);
                if (read == -1) {
                    break;
                }
                i += read;
            }
            array = array2;
            if (i != n) {
                throw new IOException("Unexpected readed size. current: " + i + ", excepted: " + n);
            }
        }
        return array;
    }
    
    public static byte[] a(final InputStream inputStream, final long n) throws IOException {
        if (n > 2147483647L) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + n);
        }
        return a(inputStream, (int)n);
    }
    
    public static long b(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return a(inputStream, outputStream, new byte[4096]);
    }
    
    public static byte[] b(final InputStream inputStream) throws IOException {
        final bj bj = new bj();
        a(inputStream, bj);
        return bj.a();
    }
}
