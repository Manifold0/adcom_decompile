// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;

class bo implements Runnable
{
    final /* synthetic */ bm a;
    
    bo(final bm a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.a.b);
        alertDialog$Builder.setTitle((CharSequence)this.a.a.get(ea.ag));
        alertDialog$Builder.setMessage((CharSequence)this.a.a.get(ea.ah));
        alertDialog$Builder.setCancelable(false);
        alertDialog$Builder.setPositiveButton((CharSequence)this.a.a.get(ea.aj), (DialogInterface$OnClickListener)new bp(this));
        alertDialog$Builder.setNegativeButton((CharSequence)this.a.a.get(ea.ai), (DialogInterface$OnClickListener)new bq(this));
        this.a.c = alertDialog$Builder.show();
    }
}
