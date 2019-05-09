package com.chartboost.sdk.impl;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class bi {
    /* renamed from: a */
    public static final char f3196a = File.separatorChar;
    /* renamed from: b */
    public static final String f3197b;

    static {
        Writer bkVar = new bk(4);
        PrintWriter printWriter = new PrintWriter(bkVar);
        printWriter.println();
        f3197b = bkVar.toString();
        printWriter.close();
    }

    /* renamed from: a */
    public static void m3528a(InputStream inputStream) {
        m3527a((Closeable) inputStream);
    }

    /* renamed from: a */
    public static void m3527a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: b */
    public static byte[] m3532b(InputStream inputStream) throws IOException {
        OutputStream bjVar = new bj();
        m3525a(inputStream, bjVar);
        return bjVar.m3534a();
    }

    /* renamed from: a */
    public static byte[] m3530a(InputStream inputStream, long j) throws IOException {
        if (j <= 2147483647L) {
            return m3529a(inputStream, (int) j);
        }
        throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + j);
    }

    /* renamed from: a */
    public static byte[] m3529a(InputStream inputStream, int i) throws IOException {
        int i2 = 0;
        if (i < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + i);
        } else if (i == 0) {
            return new byte[0];
        } else {
            byte[] bArr = new byte[i];
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == i) {
                return bArr;
            }
            throw new IOException("Unexpected readed size. current: " + i2 + ", excepted: " + i);
        }
    }

    /* renamed from: a */
    public static int m3525a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long b = m3531b(inputStream, outputStream);
        if (b > 2147483647L) {
            return -1;
        }
        return (int) b;
    }

    /* renamed from: b */
    public static long m3531b(InputStream inputStream, OutputStream outputStream) throws IOException {
        return m3526a(inputStream, outputStream, new byte[4096]);
    }

    /* renamed from: a */
    public static long m3526a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }
}
