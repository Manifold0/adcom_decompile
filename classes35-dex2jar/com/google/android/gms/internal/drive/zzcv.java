// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;

final class zzcv extends UnregisterListenerMethod<zzaw, OpenFileCallback>
{
    private final /* synthetic */ zzg zzfs;
    
    zzcv(final zzch zzch, final ListenerHolder$ListenerKey listenerHolder$ListenerKey, final zzg zzfs) {
        this.zzfs = zzfs;
        super(listenerHolder$ListenerKey);
    }
}
