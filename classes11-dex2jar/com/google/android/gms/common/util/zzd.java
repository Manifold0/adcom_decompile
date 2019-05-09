// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import android.text.TextUtils;
import java.util.regex.Pattern;

@VisibleForTesting
public final class zzd
{
    private static final Pattern zzhi;
    
    static {
        zzhi = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
    }
    
    public static String unescape(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Matcher matcher = zzd.zzhi.matcher(s);
            StringBuffer sb = null;
            while (matcher.find()) {
                StringBuffer sb2;
                if ((sb2 = sb) == null) {
                    sb2 = new StringBuffer();
                }
                matcher.appendReplacement(sb2, new String(Character.toChars(Integer.parseInt(matcher.group().substring(2), 16))));
                sb = sb2;
            }
            if (sb != null) {
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return s;
    }
}
