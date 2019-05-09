// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.io.InputStream;
import android.net.Uri;
import java.io.IOException;
import com.facebook.LoggingBehavior;
import android.content.Context;

class ImageResponseCache
{
    static final String TAG;
    private static FileLruCache imageCache;
    
    static {
        TAG = ImageResponseCache.class.getSimpleName();
    }
    
    static void clearCache(final Context context) {
        try {
            getCache(context).clearCache();
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, ImageResponseCache.TAG, "clearCache failed " + ex.getMessage());
        }
    }
    
    static FileLruCache getCache(final Context context) throws IOException {
        synchronized (ImageResponseCache.class) {
            if (ImageResponseCache.imageCache == null) {
                ImageResponseCache.imageCache = new FileLruCache(ImageResponseCache.TAG, new FileLruCache.Limits());
            }
            return ImageResponseCache.imageCache;
        }
    }
    
    static InputStream getCachedImageStream(final Uri uri, final Context context) {
        InputStream value = null;
        if (uri == null) {
            return value;
        }
        value = value;
        if (!isCDNURL(uri)) {
            return value;
        }
        try {
            value = getCache(context).get(uri.toString());
            return value;
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, ImageResponseCache.TAG, ex.toString());
            return null;
        }
    }
    
    static InputStream interceptAndCacheImageStream(final Context context, final HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        if (httpURLConnection.getResponseCode() != 200) {
            return inputStream;
        }
        final Uri parse = Uri.parse(httpURLConnection.getURL().toString());
        final InputStream inputStream2 = inputStream = httpURLConnection.getInputStream();
        try {
            if (isCDNURL(parse)) {
                inputStream = getCache(context).interceptAndPut(parse.toString(), new BufferedHttpInputStream(inputStream2, httpURLConnection));
            }
            return inputStream;
        }
        catch (IOException ex) {
            return inputStream2;
        }
    }
    
    private static boolean isCDNURL(final Uri uri) {
        if (uri != null) {
            final String host = uri.getHost();
            if (host.endsWith("fbcdn.net") || (host.startsWith("fbcdn") && host.endsWith("akamaihd.net"))) {
                return true;
            }
        }
        return false;
    }
    
    private static class BufferedHttpInputStream extends BufferedInputStream
    {
        HttpURLConnection connection;
        
        BufferedHttpInputStream(final InputStream inputStream, final HttpURLConnection connection) {
            super(inputStream, 8192);
            this.connection = connection;
        }
        
        @Override
        public void close() throws IOException {
            super.close();
            Utility.disconnectQuietly(this.connection);
        }
    }
}
