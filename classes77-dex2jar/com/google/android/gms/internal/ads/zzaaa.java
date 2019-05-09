// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class zzaaa implements DialogInterface$OnClickListener
{
    private final /* synthetic */ zzzy zzbvx;
    
    zzaaa(final zzzy zzbvx) {
        this.zzbvx = zzbvx;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzbvx.zzbw("Operation denied by user.");
    }
}
