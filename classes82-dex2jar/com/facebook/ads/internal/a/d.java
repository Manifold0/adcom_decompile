// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import java.util.Locale;
import android.text.TextUtils;

public enum d
{
    a, 
    b, 
    c;
    
    public static d a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return com.facebook.ads.internal.a.d.a;
        }
        try {
            return valueOf(s.toUpperCase(Locale.US));
        }
        catch (IllegalArgumentException ex) {
            return com.facebook.ads.internal.a.d.a;
        }
    }
}
