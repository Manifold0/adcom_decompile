// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.internal.nearby.zzha;

final class zzbw extends zzab
{
    private static final zzha<SubscribeCallback> zzih;
    private final ListenerHolder<SubscribeCallback> zzii;
    
    static {
        zzih = new zzbx();
    }
    
    public zzbw(final ListenerHolder<SubscribeCallback> zzii) {
        this.zzii = zzii;
    }
    
    @Override
    public final void onExpired() {
        this.zzii.notifyListener((ListenerHolder$Notifier)zzbw.zzih);
    }
}
