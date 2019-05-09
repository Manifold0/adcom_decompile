// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class fm extends Observable
{
    public final List b;
    
    public fm() {
        this.b = new ArrayList();
    }
    
    private static long a(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        if (o instanceof String) {
            return Long.parseLong((String)o);
        }
        throw new IllegalArgumentException();
    }
    
    protected final a a(final String s) {
        final a a = new a(s);
        this.b.add(a);
        return a;
    }
    
    public final boolean a(final String s, final boolean b) {
        final Iterator iterator = this.b.iterator();
        boolean booleanValue;
        while (true) {
            booleanValue = b;
            if (!iterator.hasNext()) {
                break;
            }
            final Object a = iterator.next().a(s);
            if (a == null) {
                continue;
            }
            if (a instanceof Boolean) {
                booleanValue = (boolean)a;
                break;
            }
            if (!(a instanceof String)) {
                continue;
            }
            if ("true".equals(a)) {
                return true;
            }
            if ("false".equals(a)) {
                return false;
            }
        }
        return booleanValue;
    }
    
    public final boolean b(final String s) {
        return this.a(s, false);
    }
    
    public final long c(final String s) {
        final Iterator<a> iterator = (Iterator<a>)this.b.iterator();
        Object a;
        while (true) {
            if (!iterator.hasNext()) {
                return 0L;
            }
            a = iterator.next().a(s);
            if (a == null) {
                continue;
            }
            if (a instanceof Number) {
                break;
            }
            if (!(a instanceof String)) {
                continue;
            }
            try {
                return Long.parseLong((String)a);
            }
            catch (IllegalArgumentException ex) {}
        }
        return ((Number)a).longValue();
    }
    
    public final fl d(final String s) {
        final Iterator<a> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            final Object a = iterator.next().a(s);
            if (a instanceof List) {
                final List<Object> list = (List<Object>)a;
                try {
                    final long a2 = a((Object)list.get(0));
                    final long a3 = a((Object)list.get(1));
                    final long a4 = a((Object)list.get(2));
                    final Object value = list.get(3);
                    double n;
                    if (value instanceof Number) {
                        n = ((Number)value).doubleValue();
                    }
                    else {
                        if (!(value instanceof String)) {
                            throw new IllegalArgumentException();
                        }
                        n = Double.parseDouble((String)value);
                    }
                    return new fl(a2, a3, a4, n);
                }
                catch (RuntimeException ex) {
                    continue;
                }
                break;
            }
        }
        return fl.a;
    }
    
    @Override
    protected void setChanged() {
        super.setChanged();
        this.notifyObservers();
    }
    
    public final class a
    {
        public final String a;
        public volatile Map b;
        
        a(final String a) {
            this.a = a;
        }
        
        public final Object a(final String s) {
            final Map b = this.b;
            if (b != null) {
                return b.get(s);
            }
            return null;
        }
    }
}
