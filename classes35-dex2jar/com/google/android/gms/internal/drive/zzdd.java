// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzdd extends TaskApiCall<zzaw, Metadata>
{
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ DriveResource zzfo;
    
    zzdd(final zzch zzch, final MetadataChangeSet zzfb, final DriveResource zzfo) {
        this.zzfb = zzfb;
        this.zzfo = zzfo;
    }
}
