// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

final class o
{
    private HashMap a;
    private Class b;
    private Object c;
    
    public o(final Class b, final Object c) {
        this.a = new HashMap();
        this.b = null;
        this.c = null;
        this.b = b;
        this.c = c;
    }
    
    private void a(final String s, final a a) {
        try {
            a.b = this.b.getMethod(s, (Class[])a.a);
        }
        catch (Exception ex) {
            g.Log(6, "Exception while trying to get method " + s + ". " + ex.getLocalizedMessage());
            a.b = null;
        }
    }
    
    public final Object a(String s, final Object... array) {
        if (!this.a.containsKey(s)) {
            g.Log(6, "No definition for method " + s + " can be found");
            return null;
        }
        final a a = this.a.get(s);
        if (a.b == null) {
            this.a(s, a);
        }
        if (a.b == null) {
            g.Log(6, "Unable to create method: " + s);
            return null;
        }
        try {
            if (array.length == 0) {
                s = (String)a.b.invoke(this.c, new Object[0]);
            }
            else {
                s = (String)a.b.invoke(this.c, array);
            }
        }
        catch (Exception ex) {
            g.Log(6, "Error trying to call delegated method " + s + ". " + ex.getLocalizedMessage());
            s = null;
        }
        return s;
    }
    
    public final void a(final String s, final Class[] array) {
        this.a.put(s, new a(array));
    }
    
    final class a
    {
        public Class[] a;
        public Method b;
        
        public a(final Class[] a) {
            this.a = a;
            this.b = null;
        }
    }
}
