// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzcz extends TaskApiCall<zzaw, MetadataBuffer>
{
    private final /* synthetic */ Query zzds;
    
    zzcz(final zzch zzch, final Query zzds) {
        this.zzds = zzds;
    }
}
