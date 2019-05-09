// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.o;

import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class b
{
    private static final List<a> a;
    
    static {
        a = new ArrayList<a>();
    }
    
    public static String a() {
        Object a = b.a;
        synchronized (a) {
            if (b.a.isEmpty()) {
                return "";
            }
            final ArrayList<a> list = (ArrayList<a>)new ArrayList<Object>(b.a);
            b.a.clear();
            // monitorexit(a)
            a = new JSONArray();
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                ((JSONArray)a).put((Object)iterator.next().a());
            }
        }
        return ((JSONArray)a).toString();
    }
    
    public static void a(final a a) {
        synchronized (b.a) {
            b.a.add(a);
        }
    }
}
