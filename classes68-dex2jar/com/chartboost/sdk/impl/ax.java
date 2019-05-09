// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.a;
import android.content.Context;
import android.view.View;

public final class ax extends View
{
    private boolean a;
    
    public ax(final Context context) {
        super(context);
        this.setFocusable(this.a = false);
        this.setBackgroundColor(-1442840576);
    }
    
    public void a(final aw aw, final a a) {
        if (!this.a) {
            aw.a(true, this, a);
            this.a = true;
        }
    }
}
