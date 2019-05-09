// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Looper;

final class zzaps implements Runnable
{
    zzaps(final zzapr zzapr) {
    }
    
    @Override
    public final void run() {
        Looper.myLooper().quit();
    }
}
