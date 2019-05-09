// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.o;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.InputStream;

public class c
{
    public static String a(final InputStream inputStream) {
        final StringWriter stringWriter = new StringWriter();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final char[] array = new char[4096];
        while (true) {
            final int read = inputStreamReader.read(array);
            if (read == -1) {
                break;
            }
            stringWriter.write(array, 0, read);
        }
        final String string = stringWriter.toString();
        stringWriter.close();
        inputStreamReader.close();
        return string;
    }
    
    public static String a(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            final String a = a(gzipInputStream);
            gzipInputStream.close();
            byteArrayInputStream.close();
            return a;
        }
        catch (Exception ex) {
            b.a(a.a(ex, "Error decompressing data"));
            ex.printStackTrace();
            return "";
        }
    }
    
    public static byte[] a(final String s) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(s.length());
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(s.getBytes());
            gzipOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        }
        catch (Exception ex) {
            b.a(a.a(ex, "Error compressing data"));
            ex.printStackTrace();
            return new byte[0];
        }
    }
}
