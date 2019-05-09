package com.facebook.ads.internal.p046v.p053b;

import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.ads.AudienceNetworkActivity;
import java.io.Closeable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.v.b.m */
public class C2185m {
    /* renamed from: a */
    static String m5593a(String str) {
        try {
            return URLEncoder.encode(str, AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (Throwable e) {
            throw new RuntimeException("Error encoding url", e);
        }
    }

    /* renamed from: a */
    static void m5594a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Log.e("ProxyCache", "Error closing resource", e);
            }
        }
    }

    /* renamed from: b */
    static String m5595b(String str) {
        try {
            return URLDecoder.decode(str, AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (Throwable e) {
            throw new RuntimeException("Error decoding url", e);
        }
    }

    /* renamed from: c */
    public static String m5596c(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(String.format(Locale.US, "%02x", new Object[]{Byte.valueOf(b)}));
            }
            return stringBuffer.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
