// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnClickListener;

final class zzaqr implements DialogInterface$OnClickListener
{
    private final /* synthetic */ JsResult zzdbk;
    
    zzaqr(final JsResult zzdbk) {
        this.zzdbk = zzdbk;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzdbk.confirm();
    }
}
