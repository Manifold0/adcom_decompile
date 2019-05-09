// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b.a;

import android.text.TextUtils;
import com.facebook.ads.internal.v.b.m;

public class f implements c
{
    @Override
    public String a(String c) {
        final int lastIndex = c.lastIndexOf(46);
        final int lastIndex2 = c.lastIndexOf(47);
        String substring;
        if (lastIndex != -1 && lastIndex > lastIndex2 && lastIndex + 2 + 4 > c.length()) {
            substring = c.substring(lastIndex + 1, c.length());
        }
        else {
            substring = "";
        }
        c = m.c(c);
        if (TextUtils.isEmpty((CharSequence)substring)) {
            return c;
        }
        return c + "." + substring;
    }
}
