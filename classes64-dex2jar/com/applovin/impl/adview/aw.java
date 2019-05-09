// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;
import android.view.View$OnClickListener;

class aw implements View$OnClickListener
{
    final /* synthetic */ ar a;
    
    aw(final ar a) {
        this.a = a;
    }
    
    public void onClick(final View view) {
        if (this.a.h.isClickable()) {
            this.a.h.performClick();
        }
    }
}
