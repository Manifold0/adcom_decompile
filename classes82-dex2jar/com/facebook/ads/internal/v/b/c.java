// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import com.facebook.ads.internal.v.b.a.a;
import java.io.File;

class c
{
    public final File a;
    public final com.facebook.ads.internal.v.b.a.c b;
    public final a c;
    
    c(final File a, final com.facebook.ads.internal.v.b.a.c b, final a c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    File a(String a) {
        a = this.b.a(a);
        return new File(this.a, a);
    }
}
