// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.os.Handler;
import com.chartboost.sdk.Libraries.i;
import java.util.concurrent.Executor;

public class ah
{
    private final Executor a;
    private final Executor b;
    private final ao c;
    private final ai d;
    private final i e;
    private final Handler f;
    
    public ah(final Executor b, final ao c, final ai d, final i e, final Handler f, final Executor a) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public <T> void a(final ad<T> ad) {
        this.a.execute(new an<Object>(this.b, this.c, this.d, this.e, this.f, ad));
    }
}
