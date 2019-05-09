package com.vungle.warren.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtility {
    public static void printDirectoryTree(File folder) {
        if (folder.isDirectory()) {
            StringBuilder sb = new StringBuilder();
            printDirectoryTree(folder, 0, sb);
            Log.v("Vungle", sb.toString());
            return;
        }
        throw new IllegalArgumentException("folder is not a Directory");
    }

    private static void printDirectoryTree(File folder, int indent, StringBuilder sb) {
        if (folder != null) {
            if (folder.isDirectory()) {
                sb.append(getIndentString(indent));
                sb.append("+--");
                sb.append(folder.getName());
                sb.append("/");
                sb.append("\n");
                if (folder.listFiles() != null) {
                    for (File file : folder.listFiles()) {
                        if (file.isDirectory()) {
                            printDirectoryTree(file, indent + 1, sb);
                        } else {
                            printFile(file, indent + 1, sb);
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("folder is not a Directory");
        }
    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    public static void delete(File f) throws IOException {
        if (f != null && f.exists()) {
            if (f.isDirectory()) {
                for (File c : f.listFiles()) {
                    delete(c);
                }
            }
            if (!f.delete()) {
                throw new FileNotFoundException("Failed to delete file: " + f);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    @NonNull
    public static byte[] extractBytes(@Nullable File file) throws IOException {
        FileNotFoundException ex;
        if (file == null) {
            return new byte[0];
        }
        int size = (int) file.length();
        if (size > 0) {
            byte[] bytes = new byte[size];
            BufferedInputStream buffy = null;
            try {
                BufferedInputStream buffy2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    if (buffy2.read(bytes) < size) {
                        throw new IOException("Failed to read all bytes in the file, object recreation will fail");
                    }
                    buffy2.close();
                    return bytes;
                } catch (FileNotFoundException e) {
                    ex = e;
                    buffy = buffy2;
                    ex.printStackTrace();
                    closeQuietly(buffy);
                    return new byte[0];
                }
            } catch (FileNotFoundException e2) {
                ex = e2;
                ex.printStackTrace();
                closeQuietly(buffy);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
