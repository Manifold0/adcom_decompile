// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.utility;

import java.io.FilterInputStream;
import android.util.Log;
import android.support.annotation.NonNull;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import android.support.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.Closeable;

public class FileUtility
{
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static void delete(final File file) throws IOException {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                final File[] listFiles = file.listFiles();
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    delete(listFiles[i]);
                }
            }
            if (!file.delete()) {
                throw new FileNotFoundException("Failed to delete file: " + file);
            }
        }
    }
    
    @NonNull
    public static byte[] extractBytes(@Nullable File file) throws IOException {
        if (file == null) {
            return new byte[0];
        }
        final int n = (int)file.length();
        Label_0068: {
            if (n <= 0) {
                break Label_0068;
            }
            final byte[] array = new byte[n];
            Throwable t = null;
            while (true) {
                try {
                    file = (File)new BufferedInputStream(new FileInputStream(file));
                    Label_0072: {
                        try {
                            if (((FilterInputStream)file).read(array) < n) {
                                throw new IOException("Failed to read all bytes in the file, object recreation will fail");
                            }
                            break Label_0072;
                        }
                        catch (FileNotFoundException ex2) {}
                        t.printStackTrace();
                        closeQuietly((Closeable)file);
                        return new byte[0];
                    }
                    ((BufferedInputStream)file).close();
                    return array;
                }
                catch (FileNotFoundException ex) {
                    file = (File)t;
                    t = ex;
                    continue;
                }
                break;
            }
        }
    }
    
    private static String getIndentString(final int n) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append("|  ");
        }
        return sb.toString();
    }
    
    public static void printDirectoryTree(final File file) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        final StringBuilder sb = new StringBuilder();
        printDirectoryTree(file, 0, sb);
        Log.v("Vungle", sb.toString());
    }
    
    private static void printDirectoryTree(final File file, final int n, final StringBuilder sb) {
        if (file != null) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("folder is not a Directory");
            }
            sb.append(getIndentString(n));
            sb.append("+--");
            sb.append(file.getName());
            sb.append("/");
            sb.append("\n");
            if (file.listFiles() != null) {
                final File[] listFiles = file.listFiles();
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    final File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        printDirectoryTree(file2, n + 1, sb);
                    }
                    else {
                        printFile(file2, n + 1, sb);
                    }
                }
            }
        }
    }
    
    private static void printFile(final File file, final int n, final StringBuilder sb) {
        sb.append(getIndentString(n));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }
}
