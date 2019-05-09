// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzdc extends TaskApiCall<zzaw, Metadata>
{
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ boolean zzfy;
    
    zzdc(final zzch zzch, final DriveResource zzfo, final boolean b) {
        this.zzfo = zzfo;
        this.zzfy = false;
    }
}
