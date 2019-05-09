// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnCancelListener;

final class zzaqp implements DialogInterface$OnCancelListener
{
    private final /* synthetic */ JsResult zzdbk;
    
    zzaqp(final JsResult zzdbk) {
        this.zzdbk = zzdbk;
    }
    
    public final void onCancel(final DialogInterface dialogInterface) {
        this.zzdbk.cancel();
    }
}
