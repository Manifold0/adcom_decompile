// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Intent;
import com.google.android.gms.ads.internal.zzbv;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class zzzz implements DialogInterface$OnClickListener
{
    private final /* synthetic */ zzzy zzbvx;
    
    zzzz(final zzzy zzbvx) {
        this.zzbvx = zzbvx;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        final Intent intent = this.zzbvx.createIntent();
        zzbv.zzek();
        zzakk.zza(this.zzbvx.mContext, intent);
    }
}
