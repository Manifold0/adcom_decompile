// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zzaqt implements DialogInterface$OnClickListener
{
    private final /* synthetic */ JsPromptResult zzdbl;
    
    zzaqt(final JsPromptResult zzdbl) {
        this.zzdbl = zzdbl;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzdbl.cancel();
    }
}
