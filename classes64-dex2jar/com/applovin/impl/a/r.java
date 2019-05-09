// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Locale;
import com.applovin.impl.sdk.gd;
import android.webkit.URLUtil;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.gf;
import android.net.Uri;

public class r
{
    private Uri a;
    private Uri b;
    private s c;
    private String d;
    private int e;
    private int f;
    private int g;
    
    private r() {
    }
    
    public static r a(final gf gf, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        try {
            final String c = gf.c();
            if (URLUtil.isValidUrl(c)) {
                final Uri parse = Uri.parse(c);
                final r r = new r();
                r.a = parse;
                r.b = parse;
                r.g = gd.e(gf.b().get("bitrate"));
                r.c = a(gf.b().get("delivery"));
                r.f = gd.e(gf.b().get("height"));
                r.e = gd.e(gf.b().get("width"));
                r.d = gf.b().get("type").toLowerCase(Locale.ENGLISH);
                return r;
            }
            appLovinSdk.getLogger().e("VastVideoFile", "Unable to create video file. Could not find URL.");
            return null;
        }
        catch (Throwable t) {
            appLovinSdk.getLogger().e("VastVideoFile", "Error occurred while initializing", t);
            return null;
        }
    }
    
    private static s a(final String s) {
        if (AppLovinSdkUtils.isValidString(s)) {
            if ("progressive".equalsIgnoreCase(s)) {
                return s.a;
            }
            if ("streaming".equalsIgnoreCase(s)) {
                return s.b;
            }
        }
        return s.a;
    }
    
    public Uri a() {
        return this.a;
    }
    
    public void a(final Uri b) {
        this.b = b;
    }
    
    public Uri b() {
        return this.b;
    }
    
    public boolean c() {
        return this.c == s.b;
    }
    
    public String d() {
        return this.d;
    }
    
    public int e() {
        return this.g;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o instanceof r) {
                final r r = (r)o;
                b3 = b2;
                if (this.e == r.e) {
                    b3 = b2;
                    if (this.f == r.f) {
                        b3 = b2;
                        if (this.g == r.g) {
                            if (this.a != null) {
                                b3 = b2;
                                if (!this.a.equals((Object)r.a)) {
                                    return b3;
                                }
                            }
                            else if (r.a != null) {
                                return false;
                            }
                            if (this.b != null) {
                                b3 = b2;
                                if (!this.b.equals((Object)r.b)) {
                                    return b3;
                                }
                            }
                            else if (r.b != null) {
                                return false;
                            }
                            b3 = b2;
                            if (this.c == r.c) {
                                boolean equals;
                                if (this.d != null) {
                                    equals = this.d.equals(r.d);
                                }
                                else {
                                    equals = b;
                                    if (r.d != null) {
                                        equals = false;
                                    }
                                }
                                return equals;
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a != null) {
            hashCode2 = this.a.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.b != null) {
            hashCode3 = this.b.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.c != null) {
            hashCode4 = this.c.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        if (this.d != null) {
            hashCode = this.d.hashCode();
        }
        return ((((hashCode4 + (hashCode3 + hashCode2 * 31) * 31) * 31 + hashCode) * 31 + this.e) * 31 + this.f) * 31 + this.g;
    }
    
    @Override
    public String toString() {
        return "VastVideoFile{sourceVideoUri=" + this.a + ", videoUri=" + this.b + ", deliveryType=" + this.c + ", fileType='" + this.d + '\'' + ", width=" + this.e + ", height=" + this.f + ", bitrate=" + this.g + '}';
    }
}
