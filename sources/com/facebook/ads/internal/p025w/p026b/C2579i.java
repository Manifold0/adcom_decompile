package com.facebook.ads.internal.p025w.p026b;

import android.support.annotation.Nullable;
import com.adjust.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.facebook.ads.internal.w.b.i */
public class C2579i {
    @Nullable
    /* renamed from: a */
    public static String m6640a(File file) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
                byte[] bArr = new byte[1024];
                int read;
                do {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    }
                } while (read != -1);
                String b = C2579i.m6642b(instance.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
                return b;
            } catch (NoSuchAlgorithmException e2) {
                throw new Exception("No such algorithm.");
            } catch (IOException e3) {
                throw new Exception("IO exception.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                }
            }
        } catch (FileNotFoundException e5) {
            throw new Exception("File not found or not accessible.");
        }
    }

    /* renamed from: a */
    public static String m6641a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                char c = (i < 0 || i > 9) ? (char) ((i - 10) + 97) : (char) (i + 48);
                stringBuilder.append(c);
                int i3 = b & 15;
                i = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i;
                i = i3;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private static String m6642b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
