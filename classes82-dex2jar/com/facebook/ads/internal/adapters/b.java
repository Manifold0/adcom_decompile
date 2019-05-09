// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import java.util.Map;
import java.util.HashMap;
import android.content.Context;
import com.facebook.ads.internal.x.a;

public abstract class b
{
    protected final c a;
    protected final a b;
    private final Context c;
    private boolean d;
    
    public b(final Context c, final c a, final a b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public final void a() {
        if (!this.d) {
            if (this.a != null) {
                this.a.a();
            }
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (this.b != null) {
                this.b.a(hashMap);
            }
            this.a(hashMap);
            this.d = true;
            com.facebook.ads.internal.w.b.c.a(this.c, "Impression logged");
            if (this.a != null) {
                this.a.b();
            }
        }
    }
    
    protected abstract void a(final Map<String, String> p0);
}
