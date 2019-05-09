// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;
import android.content.Context;

public abstract class b
{
    protected final Context a;
    protected final c b;
    protected final String c;
    
    public b(final Context a, final c b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public abstract void a();
    
    @Nullable
    public a b() {
        return null;
    }
}
