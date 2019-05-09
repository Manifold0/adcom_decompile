// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.content.Intent;

final class zzc implements Runnable
{
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ Intent zzibw;
    private /* synthetic */ zzb zzibx;
    
    zzc(final zzb zzibx, final Intent val$intent, final Intent zzibw) {
        this.zzibx = zzibx;
        this.val$intent = val$intent;
        this.zzibw = zzibw;
    }
    
    @Override
    public final void run() {
        this.zzibx.handleIntent(this.val$intent);
        this.zzibx.zzh(this.zzibw);
    }
}
