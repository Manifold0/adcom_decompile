// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Handler;
import android.database.ContentObserver;

final class zzhf extends ContentObserver
{
    zzhf(final Handler handler) {
        super((Handler)null);
    }
    
    public final void onChange(final boolean b) {
        zzhe.zzjq.set(true);
    }
}
