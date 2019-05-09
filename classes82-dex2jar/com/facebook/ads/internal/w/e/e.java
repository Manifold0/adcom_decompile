// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.e;

import java.util.Iterator;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.v.a.a;
import android.util.Log;
import com.facebook.ads.internal.v.a.p;
import java.net.URLEncoder;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import com.facebook.ads.internal.v.a.n;
import java.util.Map;
import android.content.Context;
import java.util.Set;
import android.os.AsyncTask;

public class e extends AsyncTask<String, Void, f>
{
    private static final String a;
    private static final Set<String> b;
    private Context c;
    private Map<String, String> d;
    private Map<String, String> e;
    private n f;
    private a g;
    
    static {
        a = e.class.getSimpleName();
        (b = new HashSet<String>()).add("#");
        e.b.add("null");
    }
    
    public e(final Context context) {
        this(context, null, null);
    }
    
    public e(final Context context, final Map<String, String> map) {
        this(context, map, null);
    }
    
    public e(final Context c, final Map<String, String> map, final Map<String, String> map2) {
        final Map<String, String> map3 = null;
        this.c = c;
        HashMap<String, String> d;
        if (map != null) {
            d = new HashMap<String, String>(map);
        }
        else {
            d = null;
        }
        this.d = d;
        Map<String, String> e = map3;
        if (map2 != null) {
            e = new HashMap<String, String>(map2);
        }
        this.e = e;
    }
    
    private String a(final String s, final String s2, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s3)) {
            return s;
        }
        String s4;
        if (s.contains("?")) {
            s4 = "&";
        }
        else {
            s4 = "?";
        }
        return s + s4 + s2 + "=" + URLEncoder.encode(s3);
    }
    
    private boolean a(final String s) {
        final com.facebook.ads.internal.v.a.a a = com.facebook.ads.internal.w.e.d.a(this.c);
        try {
            if (this.e == null || this.e.size() == 0) {
                this.f = a.a(s, (p)null);
            }
            else {
                final p p = new p();
                p.a(this.e);
                this.f = a.b(s, p);
            }
            if (this.f != null && this.f.a() == 200) {
                return true;
            }
        }
        catch (Exception ex) {
            Log.e(com.facebook.ads.internal.w.e.e.a, "Error opening url: " + s, (Throwable)ex);
        }
        return false;
    }
    
    private String b(final String s) {
        try {
            return this.a(s, "analog", k.a(com.facebook.ads.internal.l.a.a()));
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    protected f a(final String... array) {
        final String s = array[0];
        if (TextUtils.isEmpty((CharSequence)s) || com.facebook.ads.internal.w.e.e.b.contains(s)) {
            return null;
        }
        String b;
        String a = b = this.b(s);
        if (this.d != null) {
            b = a;
            if (!this.d.isEmpty()) {
                for (final Map.Entry<String, String> entry : this.d.entrySet()) {
                    a = this.a(a, entry.getKey(), entry.getValue());
                }
                b = a;
            }
        }
        for (int i = 1; i <= 2; ++i) {
            if (this.a(b)) {
                return new f(this.f);
            }
        }
        return null;
    }
    
    public void a(final a g) {
        this.g = g;
    }
    
    protected void a(final f f) {
        if (this.g != null) {
            this.g.a(f);
        }
    }
    
    protected void onCancelled() {
        if (this.g != null) {
            this.g.a();
        }
    }
    
    public interface a
    {
        void a();
        
        void a(final f p0);
    }
}
