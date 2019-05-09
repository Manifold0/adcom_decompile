// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;
import android.content.Intent;

final class zze implements Runnable
{
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ zzd zzicb;
    
    zze(final zzd zzicb, final Intent val$intent) {
        this.zzicb = zzicb;
        this.val$intent = val$intent;
    }
    
    @Override
    public final void run() {
        final String action = this.val$intent.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        this.zzicb.finish();
    }
}
