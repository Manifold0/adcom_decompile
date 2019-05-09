// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;

final class zzcq extends UnregisterListenerMethod<zzaw, zzdi>
{
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;
    
    zzcq(final zzch zzch, final ListenerHolder$ListenerKey listenerHolder$ListenerKey, final DriveResource zzfo, final zzdi zzfp) {
        this.zzfo = zzfo;
        this.zzfp = zzfp;
        super(listenerHolder$ListenerKey);
    }
}
