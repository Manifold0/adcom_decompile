// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class zzaag implements DialogInterface$OnClickListener
{
    private final /* synthetic */ zzaae zzbwq;
    
    zzaag(final zzaae zzbwq) {
        this.zzbwq = zzbwq;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzbwq.zzbw("User canceled the download.");
    }
}
