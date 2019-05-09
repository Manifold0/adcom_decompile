// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import org.json.JSONArray;
import com.kongregate.android.internal.util.i;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.internal.util.p;
import com.kongregate.android.internal.util.o;

public class c extends o
{
    private final byte[] b;
    private final int c;
    
    public c(final p p) {
        this(p, null, 0);
    }
    
    public c(final p p3, final byte[] b, final int c) {
        super(p3);
        this.b = b;
        this.c = c;
    }
    
    public c(final byte[] array) {
        this(p.a, array, 200);
    }
    
    public byte[] a() {
        return this.b;
    }
    
    public String b() {
        try {
            return new String(this.b, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            j.d("Unsupported encoding!", ex);
            return null;
        }
        catch (NullPointerException ex2) {
            return null;
        }
    }
    
    public JSONObject c() {
        return i.a(this.b);
    }
    
    public JSONArray d() {
        return i.b(this.b);
    }
    
    public int e() {
        return this.c;
    }
}
