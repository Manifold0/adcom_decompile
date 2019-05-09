// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import java.util.List;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzdf extends TaskApiCall<zzaw, Void>
{
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ List zzfz;
    
    zzdf(final zzch zzch, final DriveResource zzfo, final List zzfz) {
        this.zzfo = zzfo;
        this.zzfz = zzfz;
    }
}
