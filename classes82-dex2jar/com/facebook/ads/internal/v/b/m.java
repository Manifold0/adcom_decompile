// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.security.MessageDigest;
import java.net.URLDecoder;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class m
{
    static String a(String encode) {
        try {
            encode = URLEncoder.encode(encode, "utf-8");
            return encode;
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Error encoding url", ex);
        }
    }
    
    static void a(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.e("ProxyCache", "Error closing resource", (Throwable)ex);
        }
    }
    
    static String b(String decode) {
        try {
            decode = URLDecoder.decode(decode, "utf-8");
            return decode;
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Error decoding url", ex);
        }
    }
    
    public static String c(String string) {
        int i = 0;
        try {
            final byte[] digest = MessageDigest.getInstance("MD5").digest(string.getBytes());
            final StringBuffer sb = new StringBuffer();
            while (i < digest.length) {
                sb.append(String.format(Locale.US, "%02x", digest[i]));
                ++i;
            }
            string = sb.toString();
            return string;
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
