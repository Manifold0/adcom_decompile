// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.File;

public final class bl
{
    public static String a(final File file) {
        try {
            return a(file, ap.c);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static String a(File file, final Charset charset) {
        file = (File)new FileInputStream(file);
        try {
            return db.a(new InputStreamReader((InputStream)file, charset));
        }
        finally {
            dc.a((Closeable)file);
        }
    }
    
    public static void a(File file, final String s) {
        file = (File)new FileOutputStream(file);
        try {
            a((OutputStream)file, s);
        }
        finally {
            dc.a((Closeable)file);
        }
    }
    
    public static void a(final OutputStream outputStream, final String s) {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ap.c);
        outputStreamWriter.write(s);
        outputStreamWriter.flush();
    }
}
