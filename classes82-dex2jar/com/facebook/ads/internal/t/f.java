// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.view.View;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RelativeLayout;

public abstract class f extends RelativeLayout
{
    public f(final Context context) {
        super(context);
    }
    
    public f(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public f(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @RequiresApi(api = 21)
    public f(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    protected abstract View getAdContentsView();
}
