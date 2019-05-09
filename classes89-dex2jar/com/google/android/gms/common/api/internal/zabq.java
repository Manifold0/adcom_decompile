// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class zabq extends BroadcastReceiver
{
    private Context mContext;
    private final zabr zaji;
    
    public zabq(final zabr zaji) {
        this.zaji = zaji;
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        Object schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(schemeSpecificPart)) {
            this.zaji.zas();
            this.unregister();
        }
    }
    
    public final void unregister() {
        synchronized (this) {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver((BroadcastReceiver)this);
            }
            this.mContext = null;
        }
    }
    
    public final void zac(final Context mContext) {
        this.mContext = mContext;
    }
}
