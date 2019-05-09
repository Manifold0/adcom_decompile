// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.Iterator;
import java.util.HashMap;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MapUtils
{
    @KeepForSdk
    public static void writeStringMapToJson(final StringBuilder sb, final HashMap<String, String> hashMap) {
        sb.append("{");
        final Iterator<String> iterator = hashMap.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (n == 0) {
                sb.append(",");
            }
            else {
                n = 0;
            }
            final String s2 = hashMap.get(s);
            sb.append("\"").append(s).append("\":");
            if (s2 == null) {
                sb.append("null");
            }
            else {
                sb.append("\"").append(s2).append("\"");
            }
        }
        sb.append("}");
    }
}
