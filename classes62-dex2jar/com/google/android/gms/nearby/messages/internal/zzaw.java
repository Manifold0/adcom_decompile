// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzaw extends zzbg
{
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ zzak zzia;
    
    zzaw(final zzak zzia, final ListenerHolder listenerHolder, final ListenerHolder zzco) {
        this.zzia = zzia;
        this.zzco = zzco;
        super((ListenerHolder<SubscribeCallback>)listenerHolder);
    }
    
    @Override
    public final void onExpired() {
        this.zzia.doUnregisterEventListener(this.zzco.getListenerKey());
        super.onExpired();
    }
}
