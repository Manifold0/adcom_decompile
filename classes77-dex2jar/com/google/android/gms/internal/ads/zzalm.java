// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.internal.zzbv;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class zzalm implements DialogInterface$OnClickListener
{
    private final /* synthetic */ zzall zzcst;
    
    zzalm(final zzall zzcst) {
        this.zzcst = zzcst;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        zzbv.zzek();
        zzakk.zza(this.zzcst.val$context, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
