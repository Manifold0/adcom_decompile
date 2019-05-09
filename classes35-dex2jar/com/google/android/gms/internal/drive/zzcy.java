// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.zzn;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzcy extends TaskApiCall<zzaw, Void>
{
    private final /* synthetic */ MetadataChangeSet zzeu;
    private final /* synthetic */ DriveContents zzfv;
    private final /* synthetic */ zzn zzfw;
    
    zzcy(final zzch zzch, final zzn zzfw, final DriveContents zzfv, final MetadataChangeSet zzeu) {
        this.zzfw = zzfw;
        this.zzfv = zzfv;
        this.zzeu = zzeu;
    }
}
