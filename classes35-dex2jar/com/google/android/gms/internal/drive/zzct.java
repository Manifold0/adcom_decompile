// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzct extends TaskApiCall<zzaw, DriveContents>
{
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;
    
    zzct(final zzch zzch, final DriveFile zzfq, final int zzfr) {
        this.zzfq = zzfq;
        this.zzfr = zzfr;
    }
}
