// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.a;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.a;
import android.widget.RelativeLayout;

public abstract class c extends RelativeLayout implements b
{
    @Nullable
    private a a;
    
    public c(final Context context) {
        super(context);
    }
    
    public c(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    protected void a() {
    }
    
    public void a(final a a) {
        this.a = a;
        this.a();
    }
    
    protected void b() {
    }
    
    public void b(final a a) {
        this.b();
        this.a = null;
    }
    
    @Nullable
    protected a getVideoView() {
        return this.a;
    }
}
