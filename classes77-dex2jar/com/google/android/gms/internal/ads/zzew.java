// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.database.ContentObserver;

final class zzew extends ContentObserver
{
    private final /* synthetic */ zzet zzafk;
    
    public zzew(final zzet zzafk, final Handler handler) {
        this.zzafk = zzafk;
        super(handler);
    }
    
    public final void onChange(final boolean b) {
        super.onChange(b);
        this.zzafk.zzgb();
    }
}
