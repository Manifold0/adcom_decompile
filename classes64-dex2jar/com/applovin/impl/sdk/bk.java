// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class bk implements DialogInterface$OnClickListener
{
    final /* synthetic */ bh a;
    
    bk(final bh a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
        this.a.b.b.a(this.a.a, this.a.b.e);
    }
}
