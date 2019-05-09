// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import java.util.Iterator;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import java.util.Map;
import android.net.Uri;
import android.content.Context;

public class c
{
    private static final String a;
    
    static {
        a = c.class.getSimpleName();
    }
    
    @Nullable
    public static b a(final Context context, final com.facebook.ads.internal.s.c c, final String s, final Uri uri, final Map<String, String> map) {
        return a(context, c, s, uri, map, false, false);
    }
    
    @Nullable
    public static b a(final Context context, final com.facebook.ads.internal.s.c c, final String s, final Uri uri, final Map<String, String> map, final boolean b, final boolean b2) {
        if (uri == null || uri.getAuthority() == null) {
            return null;
        }
        final String authority = uri.getAuthority();
        final String queryParameter = uri.getQueryParameter("video_url");
        if (!TextUtils.isEmpty((CharSequence)uri.getQueryParameter("data"))) {
            try {
                final JSONObject jsonObject = new JSONObject(uri.getQueryParameter("data"));
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    map.put(s2, jsonObject.getString(s2));
                }
            }
            catch (JSONException ex) {
                Log.w(c.a, "Unable to parse json data in AdActionFactory.", (Throwable)ex);
            }
        }
        final m a = m.a(c, com.facebook.ads.internal.w.a.b.a());
        switch (authority) {
            default: {
                return new l(context, c, s, uri);
            }
            case "store": {
                if (queryParameter != null) {
                    return null;
                }
                return new f(context, c, s, uri, map, a, b);
            }
            case "open_link": {
                if (b2) {
                    return new i(context, c, s, uri, map);
                }
                return new j(context, c, s, uri, map, a);
            }
            case "passthrough": {
                return new k(context, c, s, uri, map);
            }
        }
    }
    
    public static boolean a(final String s) {
        return "store".equalsIgnoreCase(s) || "open_link".equalsIgnoreCase(s);
    }
}
