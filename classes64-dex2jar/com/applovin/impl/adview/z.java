// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;
import android.view.View$OnLongClickListener;

class z implements View$OnLongClickListener
{
    final /* synthetic */ n a;
    
    z(final n a) {
        this.a = a;
    }
    
    public boolean onLongClick(final View view) {
        this.a.a.d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
