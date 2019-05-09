package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public final class bl {
    /* renamed from: a */
    public static String m7200a(File file, Charset charset) {
        Closeable fileInputStream = new FileInputStream(file);
        try {
            String a = db.m7356a(new InputStreamReader(fileInputStream, charset));
            return a;
        } finally {
            dc.m7357a(fileInputStream);
        }
    }

    /* renamed from: a */
    public static void m7201a(File file, String str) {
        OutputStream fileOutputStream = new FileOutputStream(file);
        try {
            m7202a(fileOutputStream, str);
        } finally {
            dc.m7357a(fileOutputStream);
        }
    }

    /* renamed from: a */
    public static void m7202a(OutputStream outputStream, String str) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ap.f7205c);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }

    /* renamed from: a */
    public static String m7199a(File file) {
        try {
            return m7200a(file, ap.f7205c);
        } catch (IOException e) {
            return null;
        }
    }
}
