// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.utility;

import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Closeable;
import java.io.File;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

public class UnzipUtility
{
    private static final int BUFFER_SIZE = 4096;
    
    private static void extractFile(final ZipInputStream zipInputStream, final String s) throws IOException {
        byte[] array = null;
        Object o = null;
        while (true) {
            try {
                try {
                    final Object o2 = new BufferedOutputStream(new FileOutputStream(s));
                    Label_0112: {
                        try {
                            array = new byte[4096];
                            while (true) {
                                final int read = zipInputStream.read(array);
                                if (read == -1) {
                                    break Label_0112;
                                }
                                ((BufferedOutputStream)o2).write(array, 0, read);
                            }
                        }
                        catch (FileNotFoundException ex) {
                            array = (byte[])o2;
                            o = new File(s);
                            array = (byte[])o2;
                            if (!((File)o).getParentFile().exists()) {
                                array = (byte[])o2;
                                o = ((File)o).getParentFile();
                                while (true) {
                                    array = (byte[])o2;
                                    if (((File)o).mkdir()) {
                                        break;
                                    }
                                    array = (byte[])o2;
                                    o = ((File)o).getParentFile();
                                }
                                array = (byte[])o2;
                                extractFile(zipInputStream, s);
                            }
                            FileUtility.closeQuietly((Closeable)o2);
                            return;
                            FileUtility.closeQuietly((Closeable)o2);
                            return;
                            FileUtility.closeQuietly((Closeable)(Object)array);
                            throw zipInputStream;
                        }
                        finally {
                            array = (byte[])o2;
                        }
                    }
                }
                finally {}
            }
            catch (FileNotFoundException array) {
                final Object o2 = o;
                continue;
            }
            break;
        }
    }
    
    public static void unzip(final String s, final String s2) throws IOException {
        final File file = new File(s2);
        if (!file.exists()) {
            file.mkdir();
        }
        final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
        for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
            final String string = s2 + File.separator + zipEntry.getName();
            if (zipEntry.isDirectory()) {
                final File file2 = new File(string);
                if (!file2.exists()) {
                    file2.mkdir();
                }
            }
            else {
                extractFile(zipInputStream, string);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }
}
