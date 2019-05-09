package com.facebook.ads.internal.p021o;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.facebook.ads.internal.o.c */
public class C2060c {
    /* renamed from: a */
    public static String m5024a(InputStream inputStream) {
        StringWriter stringWriter = new StringWriter();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[4096];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read != -1) {
                stringWriter.write(cArr, 0, read);
            } else {
                String stringWriter2 = stringWriter.toString();
                stringWriter.close();
                inputStreamReader.close();
                return stringWriter2;
            }
        }
    }

    /* renamed from: a */
    public static String m5025a(byte[] bArr) {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            InputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            String a = C2060c.m5024a(gZIPInputStream);
            gZIPInputStream.close();
            byteArrayInputStream.close();
            return a;
        } catch (Throwable e) {
            C2059b.m5023a(C2058a.m5020a(e, "Error decompressing data"));
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static byte[] m5026a(String str) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return toByteArray;
        } catch (Throwable e) {
            C2059b.m5023a(C2058a.m5020a(e, "Error compressing data"));
            e.printStackTrace();
            return new byte[0];
        }
    }
}
