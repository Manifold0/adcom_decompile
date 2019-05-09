// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.internal.nearby.zzha;

class zzbe extends zzv
{
    private static final zzha<PublishCallback> zzih;
    @Nullable
    private final ListenerHolder<PublishCallback> zzii;
    
    static {
        zzih = new zzbf();
    }
    
    public zzbe(@Nullable final ListenerHolder<PublishCallback> zzii) {
        this.zzii = zzii;
    }
    
    @Override
    public void onExpired() {
        if (this.zzii != null) {
            this.zzii.notifyListener((ListenerHolder$Notifier)zzbe.zzih);
        }
    }
}
