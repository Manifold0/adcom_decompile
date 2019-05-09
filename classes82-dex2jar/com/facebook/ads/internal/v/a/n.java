// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class n
{
    private int a;
    private String b;
    private Map<String, List<String>> c;
    private byte[] d;
    
    public n(final HttpURLConnection httpURLConnection, final byte[] d) {
        while (true) {
            try {
                this.a = httpURLConnection.getResponseCode();
                this.b = httpURLConnection.getURL().toString();
                this.c = httpURLConnection.getHeaderFields();
                this.d = d;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public int a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public Map<String, List<String>> c() {
        return this.c;
    }
    
    public byte[] d() {
        return this.d;
    }
    
    public String e() {
        if (this.d != null) {
            return new String(this.d);
        }
        return null;
    }
}
