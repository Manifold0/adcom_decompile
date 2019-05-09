package com.vungle.warren.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipUtility {
    private static final int BUFFER_SIZE = 4096;

    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (entry.isDirectory()) {
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            } else {
                extractFile(zipIn, filePath);
            }
            zipIn.closeEntry();
        }
        zipIn.close();
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        File dest;
        File folder;
        Throwable th;
        BufferedOutputStream bos = null;
        try {
            BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream(filePath));
            try {
                byte[] bytesIn = new byte[4096];
                while (true) {
                    int read = zipIn.read(bytesIn);
                    if (read != -1) {
                        bos2.write(bytesIn, 0, read);
                    } else {
                        FileUtility.closeQuietly(bos2);
                        bos = bos2;
                        return;
                    }
                }
            } catch (FileNotFoundException e) {
                bos = bos2;
                try {
                    dest = new File(filePath);
                    if (!dest.getParentFile().exists()) {
                        for (folder = dest.getParentFile(); !folder.mkdir(); folder = folder.getParentFile()) {
                        }
                        extractFile(zipIn, filePath);
                    }
                    FileUtility.closeQuietly(bos);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtility.closeQuietly(bos);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bos = bos2;
                FileUtility.closeQuietly(bos);
                throw th;
            }
        } catch (FileNotFoundException e2) {
            dest = new File(filePath);
            if (dest.getParentFile().exists()) {
                for (folder = dest.getParentFile(); !folder.mkdir(); folder = folder.getParentFile()) {
                }
                extractFile(zipIn, filePath);
            }
            FileUtility.closeQuietly(bos);
        }
    }
}
