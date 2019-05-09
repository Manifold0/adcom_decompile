// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.view.View;

public class ab extends View
{
    private aa a;
    
    public ab(final Context context, final aa a) {
        super(context);
        this.a = a;
        this.setLayoutParams(new ViewGroup$LayoutParams(0, 0));
    }
    
    public void onWindowVisibilityChanged(final int n) {
        if (this.a != null) {
            this.a.a(n);
        }
    }
}
