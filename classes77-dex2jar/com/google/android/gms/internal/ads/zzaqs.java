// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnCancelListener;

final class zzaqs implements DialogInterface$OnCancelListener
{
    private final /* synthetic */ JsPromptResult zzdbl;
    
    zzaqs(final JsPromptResult zzdbl) {
        this.zzdbl = zzdbl;
    }
    
    public final void onCancel(final DialogInterface dialogInterface) {
        this.zzdbl.cancel();
    }
}
