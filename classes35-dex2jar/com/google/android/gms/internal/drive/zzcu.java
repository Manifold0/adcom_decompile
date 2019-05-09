// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken$Stub;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzcu extends RegisterListenerMethod<zzaw, OpenFileCallback>
{
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;
    private final /* synthetic */ zzg zzfs;
    private final /* synthetic */ ListenerHolder zzft;
    private final /* synthetic */ zzch zzfu;
    
    zzcu(final zzch zzfu, final ListenerHolder listenerHolder, final DriveFile zzfq, final int zzfr, final zzg zzfs, final ListenerHolder zzft) {
        this.zzfu = zzfu;
        this.zzfq = zzfq;
        this.zzfr = zzfr;
        this.zzfs = zzfs;
        this.zzft = zzft;
        super(listenerHolder);
    }
}
