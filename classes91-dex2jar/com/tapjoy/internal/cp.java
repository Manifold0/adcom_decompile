// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.nio.charset.Charset;

public final class cp
{
    public static final Charset a;
    public static final Charset b;
    public static final Charset c;
    public static final Charset d;
    public static final Charset e;
    public static final Charset f;
    
    static {
        a = Charset.forName("US-ASCII");
        b = Charset.forName("ISO-8859-1");
        c = Charset.forName("UTF-8");
        d = Charset.forName("UTF-16BE");
        e = Charset.forName("UTF-16LE");
        f = Charset.forName("UTF-16");
    }
}
