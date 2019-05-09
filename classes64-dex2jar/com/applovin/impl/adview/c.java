// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;

class c implements DialogInterface$OnDismissListener
{
    final /* synthetic */ b a;
    
    c(final b a) {
        this.a = a;
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        this.a.a.contractAd();
    }
}
