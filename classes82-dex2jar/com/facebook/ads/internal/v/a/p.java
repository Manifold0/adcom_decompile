// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class p implements Map<String, String>
{
    private Map<String, String> a;
    
    public p() {
        this.a = new HashMap<String, String>();
    }
    
    public p a(final Map<? extends String, ? extends String> map) {
        this.putAll(map);
        return this;
    }
    
    public String a() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.a.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(s);
            final String s2 = this.a.get(s);
            if (s2 != null) {
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(s2, "UTF-8"));
                }
                catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    
    public String a(final Object o) {
        return this.a.get(o);
    }
    
    public String a(final String s, final String s2) {
        return this.a.put(s, s2);
    }
    
    public p b(final String s, final String s2) {
        this.a.put(s, s2);
        return this;
    }
    
    public String b(final Object o) {
        return this.a.remove(o);
    }
    
    public byte[] b() {
        try {
            return this.a().getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void clear() {
        this.a.clear();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.a.containsKey(o);
    }
    
    @Override
    public boolean containsValue(final Object o) {
        return this.a.containsValue(o);
    }
    
    @Override
    public Set<Entry<String, String>> entrySet() {
        return this.a.entrySet();
    }
    
    @Override
    public boolean isEmpty() {
        return this.a.isEmpty();
    }
    
    @Override
    public Set<String> keySet() {
        return this.a.keySet();
    }
    
    @Override
    public void putAll(final Map<? extends String, ? extends String> map) {
        this.a.putAll(map);
    }
    
    @Override
    public int size() {
        return this.a.size();
    }
    
    @Override
    public Collection<String> values() {
        return this.a.values();
    }
}
