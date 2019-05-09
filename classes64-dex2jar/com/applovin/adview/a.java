// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class a implements DialogInterface$OnClickListener
{
    final /* synthetic */ AppLovinConfirmationActivity a;
    
    a(final AppLovinConfirmationActivity a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
        this.a.finish();
    }
}
