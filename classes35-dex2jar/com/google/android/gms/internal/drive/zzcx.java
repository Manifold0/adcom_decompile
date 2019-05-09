// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzcx extends TaskApiCall<zzaw, DriveContents>
{
    private final /* synthetic */ DriveContents zzfv;
    
    zzcx(final zzch zzch, final DriveContents zzfv) {
        this.zzfv = zzfv;
    }
}
