// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import android.webkit.URLUtil;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.gf;
import android.net.Uri;

public class i
{
    private j a;
    private Uri b;
    private String c;
    
    private i() {
    }
    
    static i a(final gf gf, i i, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        while (true) {
            if (i != null) {
                Label_0108: {
                    try {
                        while (true) {
                            if (i.b != null || AppLovinSdkUtils.isValidString(i.c)) {
                                return i;
                            }
                            final String a = a(gf, "StaticResource");
                            if (URLUtil.isValidUrl(a)) {
                                i.b = Uri.parse(a);
                                i.a = j.b;
                                return i;
                            }
                            break Label_0108;
                            i = new i();
                            continue;
                        }
                    }
                    catch (Throwable t) {
                        appLovinSdk.getLogger().e("VastNonVideoResource", "Error occurred while initializing", t);
                        return null;
                    }
                }
                final String a2 = a(gf, "IFrameResource");
                if (AppLovinSdkUtils.isValidString(a2)) {
                    i.a = j.c;
                    if (URLUtil.isValidUrl(a2)) {
                        i.b = Uri.parse(a2);
                        return i;
                    }
                    i.c = a2;
                    return i;
                }
                else {
                    final String a3 = a(gf, "HTMLResource");
                    if (AppLovinSdkUtils.isValidString(a3)) {
                        i.a = j.d;
                        if (URLUtil.isValidUrl(a3)) {
                            i.b = Uri.parse(a3);
                            return i;
                        }
                        i.c = a3;
                    }
                }
                return i;
            }
            continue;
        }
    }
    
    private static String a(gf b, final String s) {
        b = b.b(s);
        if (b != null) {
            return b.c();
        }
        return null;
    }
    
    public j a() {
        return this.a;
    }
    
    public void a(final Uri b) {
        this.b = b;
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public Uri b() {
        return this.b;
    }
    
    public String c() {
        return this.c;
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
            if (o instanceof i) {
                final i i = (i)o;
                b3 = b2;
                if (this.a == i.a) {
                    if (this.b != null) {
                        b3 = b2;
                        if (!this.b.equals((Object)i.b)) {
                            return b3;
                        }
                    }
                    else if (i.b != null) {
                        return false;
                    }
                    boolean equals;
                    if (this.c != null) {
                        equals = this.c.equals(i.c);
                    }
                    else {
                        equals = b;
                        if (i.c != null) {
                            equals = false;
                        }
                    }
                    return equals;
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
        if (this.c != null) {
            hashCode = this.c.hashCode();
        }
        return (hashCode3 + hashCode2 * 31) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "VastNonVideoResource{type=" + this.a + ", resourceUri=" + this.b + ", resourceContents='" + this.c + '\'' + '}';
    }
}
