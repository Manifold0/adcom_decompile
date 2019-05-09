// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.text.TextUtils;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;

public class aa
{
    static String a(final Collection<String> collection, final int n) {
        return a(collection, ",", n);
    }
    
    static String a(final Collection<String> collection, final String s, final int n) {
        if (s == null) {
            throw new IllegalArgumentException("No glue specified");
        }
        if (collection == null || collection.size() < 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator = collection.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            final String s2 = iterator.next();
            if (n2 >= n) {
                break;
            }
            ++n2;
            sb.append(s2).append(s);
        }
        if (sb.length() > s.length()) {
            sb.setLength(sb.length() - s.length());
        }
        return sb.toString();
    }
    
    public static List<String> a(final String s) {
        return a(s, ",\\s*");
    }
    
    public static List<String> a(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return Collections.emptyList();
        }
        return Arrays.asList(s.split(s2));
    }
    
    static List<String> a(final List<String> list) {
        if (list == null) {
            return null;
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String trim = iterator.next().trim();
            if (!TextUtils.isEmpty((CharSequence)trim)) {
                list2.add(trim);
            }
        }
        return list2;
    }
}
