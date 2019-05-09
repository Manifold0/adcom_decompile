// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.internal.zzy;

public final class zzhb extends zzy
{
    private final ListenerHolder<StatusCallback> zzjj;
    
    public zzhb(final ListenerHolder<StatusCallback> zzjj) {
        this.zzjj = zzjj;
    }
    
    @Override
    public final void onPermissionChanged(final boolean b) {
        this.zzjj.notifyListener((ListenerHolder$Notifier)new zzhc(this, b));
    }
}
