// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzav extends zzbe
{
    private final /* synthetic */ ListenerHolder zzhz;
    private final /* synthetic */ zzak zzia;
    
    zzav(final zzak zzia, final ListenerHolder listenerHolder, final ListenerHolder zzhz) {
        this.zzia = zzia;
        this.zzhz = zzhz;
        super((ListenerHolder<PublishCallback>)listenerHolder);
    }
    
    @Override
    public final void onExpired() {
        this.zzia.doUnregisterEventListener(this.zzhz.getListenerKey());
        super.onExpired();
    }
}
