// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;

class br implements Runnable
{
    final /* synthetic */ bm a;
    
    br(final bm a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.a.b);
        alertDialog$Builder.setTitle((CharSequence)this.a.a.get(ea.al));
        alertDialog$Builder.setMessage((CharSequence)this.a.a.get(ea.am));
        alertDialog$Builder.setCancelable(false);
        alertDialog$Builder.setPositiveButton((CharSequence)this.a.a.get(ea.ao), (DialogInterface$OnClickListener)null);
        alertDialog$Builder.setNegativeButton((CharSequence)this.a.a.get(ea.an), (DialogInterface$OnClickListener)new bs(this));
        this.a.c = alertDialog$Builder.show();
    }
}
