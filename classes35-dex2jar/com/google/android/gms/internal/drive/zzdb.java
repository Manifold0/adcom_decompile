// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzdb extends TaskApiCall<zzaw, DriveFolder>
{
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveFolder zzfx;
    
    zzdb(final zzch zzch, final MetadataChangeSet zzfb, final DriveFolder zzfx) {
        this.zzfb = zzfb;
        this.zzfx = zzfx;
    }
}
