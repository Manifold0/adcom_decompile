// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import com.applovin.sdk.AppLovinAd;

class bh implements Runnable
{
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ bg b;
    
    bh(final bg b, final AppLovinAd a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.b.c);
        alertDialog$Builder.setTitle((CharSequence)this.b.a.get(ea.V));
        alertDialog$Builder.setMessage((CharSequence)this.b.a.get(ea.W));
        alertDialog$Builder.setCancelable(false);
        alertDialog$Builder.setPositiveButton((CharSequence)this.b.a.get(ea.X), (DialogInterface$OnClickListener)new bi(this));
        alertDialog$Builder.setNegativeButton((CharSequence)this.b.a.get(ea.Y), (DialogInterface$OnClickListener)new bk(this));
        alertDialog$Builder.show();
    }
}
