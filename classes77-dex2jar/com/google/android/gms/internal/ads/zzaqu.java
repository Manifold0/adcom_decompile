// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.widget.EditText;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zzaqu implements DialogInterface$OnClickListener
{
    private final /* synthetic */ JsPromptResult zzdbl;
    private final /* synthetic */ EditText zzdbm;
    
    zzaqu(final JsPromptResult zzdbl, final EditText zzdbm) {
        this.zzdbl = zzdbl;
        this.zzdbm = zzdbm;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzdbl.confirm(this.zzdbm.getText().toString());
    }
}
