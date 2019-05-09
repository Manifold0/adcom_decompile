// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzaz extends RegisterListenerMethod<zzah, Object>
{
    private final /* synthetic */ zzak zzia;
    private final /* synthetic */ zzbd zzid;
    
    zzaz(final zzak zzia, final ListenerHolder listenerHolder, final zzbd zzid) {
        this.zzia = zzia;
        this.zzid = zzid;
        super(listenerHolder);
    }
}
