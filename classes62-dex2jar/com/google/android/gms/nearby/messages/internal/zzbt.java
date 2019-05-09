// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.internal.nearby.zzha;

final class zzbt extends zzv
{
    private static final zzha<PublishCallback> zzih;
    private final ListenerHolder<PublishCallback> zzii;
    
    static {
        zzih = new zzbu();
    }
    
    public zzbt(final ListenerHolder<PublishCallback> zzii) {
        this.zzii = zzii;
    }
    
    @Override
    public final void onExpired() {
        this.zzii.notifyListener((ListenerHolder$Notifier)zzbt.zzih);
    }
}
