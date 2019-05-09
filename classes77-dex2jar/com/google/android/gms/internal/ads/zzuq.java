// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public final class zzuq
{
    private Context mContext;
    
    public final void initialize(Context applicationContext) {
        if (this.mContext != null) {
            return;
        }
        if (applicationContext.getApplicationContext() != null) {
            applicationContext = applicationContext.getApplicationContext();
        }
        this.mContext = applicationContext;
    }
}
