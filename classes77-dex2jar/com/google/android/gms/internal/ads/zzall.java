// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.content.Context;

final class zzall implements Runnable
{
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcsq;
    private final /* synthetic */ boolean zzcsr;
    private final /* synthetic */ boolean zzcss;
    
    zzall(final zzalk zzalk, final Context val$context, final String zzcsq, final boolean zzcsr, final boolean zzcss) {
        this.val$context = val$context;
        this.zzcsq = zzcsq;
        this.zzcsr = zzcsr;
        this.zzcss = zzcss;
    }
    
    @Override
    public final void run() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.val$context);
        alertDialog$Builder.setMessage((CharSequence)this.zzcsq);
        if (this.zzcsr) {
            alertDialog$Builder.setTitle((CharSequence)"Error");
        }
        else {
            alertDialog$Builder.setTitle((CharSequence)"Info");
        }
        if (this.zzcss) {
            alertDialog$Builder.setNeutralButton((CharSequence)"Dismiss", (DialogInterface$OnClickListener)null);
        }
        else {
            alertDialog$Builder.setPositiveButton((CharSequence)"Learn More", (DialogInterface$OnClickListener)new zzalm(this));
            alertDialog$Builder.setNegativeButton((CharSequence)"Dismiss", (DialogInterface$OnClickListener)null);
        }
        alertDialog$Builder.create().show();
    }
}
