// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;

final class zzba extends UnregisterListenerMethod<zzah, Object>
{
    private final /* synthetic */ zzak zzia;
    private final /* synthetic */ zzbd zzie;
    
    zzba(final zzak zzia, final ListenerHolder$ListenerKey listenerHolder$ListenerKey, final zzbd zzie) {
        this.zzia = zzia;
        this.zzie = zzie;
        super(listenerHolder$ListenerKey);
    }
}
