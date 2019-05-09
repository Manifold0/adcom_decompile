// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class bs implements DialogInterface$OnClickListener
{
    final /* synthetic */ br a;
    
    bs(final br a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.a.a.b.dismiss();
    }
}
