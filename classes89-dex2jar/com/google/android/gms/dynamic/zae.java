// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.view.View$OnClickListener;

final class zae implements View$OnClickListener
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Intent zaro;
    
    zae(final Context val$context, final Intent zaro) {
        this.val$context = val$context;
        this.zaro = zaro;
    }
    
    public final void onClick(final View view) {
        try {
            this.val$context.startActivity(this.zaro);
        }
        catch (ActivityNotFoundException ex) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", (Throwable)ex);
        }
    }
}
