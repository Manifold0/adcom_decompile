// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzcp extends RegisterListenerMethod<zzaw, zzdi>
{
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;
    
    zzcp(final zzch zzch, final ListenerHolder listenerHolder, final DriveResource zzfo, final zzdi zzfp) {
        this.zzfo = zzfo;
        this.zzfp = zzfp;
        super(listenerHolder);
    }
}
