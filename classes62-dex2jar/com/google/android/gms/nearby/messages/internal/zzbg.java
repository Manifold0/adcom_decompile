// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.internal.nearby.zzha;

class zzbg extends zzab
{
    private static final zzha<SubscribeCallback> zzih;
    @Nullable
    private final ListenerHolder<SubscribeCallback> zzii;
    
    static {
        zzih = new zzbh();
    }
    
    public zzbg(@Nullable final ListenerHolder<SubscribeCallback> zzii) {
        this.zzii = zzii;
    }
    
    @Override
    public void onExpired() {
        if (this.zzii != null) {
            this.zzii.notifyListener((ListenerHolder$Notifier)zzbg.zzih);
        }
    }
}
