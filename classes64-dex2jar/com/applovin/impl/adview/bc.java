// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;
import android.view.View$OnClickListener;

class bc implements View$OnClickListener
{
    final /* synthetic */ az a;
    
    bc(final az a) {
        this.a = a;
    }
    
    public void onClick(final View view) {
        this.a.toggleMute();
    }
}
