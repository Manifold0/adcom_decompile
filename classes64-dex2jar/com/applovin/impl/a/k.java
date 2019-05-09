// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.gf;

public class k
{
    private String a;
    private String b;
    
    private k() {
    }
    
    public static k a(final gf gf, k k, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        while (true) {
            if (k != null) {
                try {
                    while (true) {
                        if (!AppLovinSdkUtils.isValidString(k.a)) {
                            final String c = gf.c();
                            if (AppLovinSdkUtils.isValidString(c)) {
                                k.a = c;
                            }
                        }
                        if (AppLovinSdkUtils.isValidString(k.b)) {
                            return k;
                        }
                        final String b = gf.b().get("version");
                        if (AppLovinSdkUtils.isValidString(b)) {
                            k.b = b;
                            return k;
                        }
                        return k;
                        k = new k();
                        continue;
                    }
                }
                catch (Throwable t) {
                    appLovinSdk.getLogger().e("VastSystemInfo", "Error occurred while initializing", t);
                    return null;
                }
                return k;
            }
            continue;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof k)) {
                return false;
            }
            final k k = (k)o;
            Label_0051: {
                if (this.a != null) {
                    if (this.a.equals(k.a)) {
                        break Label_0051;
                    }
                }
                else if (k.a == null) {
                    break Label_0051;
                }
                return false;
            }
            if (this.b != null) {
                return this.b.equals(k.b);
            }
            if (k.b != null) {
                return false;
            }
        }
        return true;
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
        if (this.b != null) {
            hashCode = this.b.hashCode();
        }
        return hashCode2 * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "VastSystemInfo{name='" + this.a + '\'' + ", version='" + this.b + '\'' + '}';
    }
}
